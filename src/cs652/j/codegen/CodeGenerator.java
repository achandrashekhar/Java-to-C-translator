package cs652.j.codegen;


import cs652.j.codegen.model.*;
import cs652.j.parser.JBaseVisitor;
import cs652.j.parser.JParser;
import cs652.j.semantics.JClass;
import cs652.j.semantics.JField;
import cs652.j.semantics.JMethod;
import org.antlr.symtab.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CodeGenerator extends JBaseVisitor<OutputModelObject> {
	public STGroup templates;
	public String fileName;

	public Scope currentScope;
	public JClass currentClass;

	public CodeGenerator(String fileName) {
		this.fileName = fileName;
		templates = new STGroupFile("cs652/j/templates/C.stg");
	}

	public CFile generate(ParserRuleContext tree) {
		CFile file = (CFile)visit(tree);
		return file;
	}

	@Override
	public OutputModelObject visitFile(JParser.FileContext ctx) {
		currentScope = ctx.scope;
        System.out.println(ctx.scope.getName());
        MainMethod main = (MainMethod) visit(ctx.main());
		CFile cfile = new CFile(fileName);
		cfile.main = main;
        for(JParser.ClassDeclarationContext cdc : ctx.classDeclaration()){
            OutputModelObject outputModelObject = visit(cdc);
            cfile.classes.add((ClassDef) outputModelObject);
        }
        currentScope = currentScope.getEnclosingScope();
		return cfile;
	}

	@Override
	public OutputModelObject visitMain(JParser.MainContext ctx) {
	    currentScope = ctx.scope;
		MainMethod mainMethod = new MainMethod();
		mainMethod.body = (Block) visit(ctx.block());
        currentScope = currentScope.getEnclosingScope();
		return mainMethod;

	}

	@Override
	public OutputModelObject visitBlock(JParser.BlockContext ctx) {
	    currentScope = ctx.scope;
		Block block = new Block();
		for (JParser.StatementContext stat : ctx.statement()){
		    OutputModelObject outputModelObject = visit(stat);
		    if(outputModelObject instanceof VarDef) {
                block.locals.add((VarDef) outputModelObject);
            } else {
                block.instrs.add((Stat) outputModelObject);
            }
		}
		currentScope = currentScope.getEnclosingScope();
		return block;
	}

    @Override
    public OutputModelObject visitBlockStat(JParser.BlockStatContext ctx) {
        return visit(ctx.block());
    }

    @Override
	public OutputModelObject visitLocalVarStat(JParser.LocalVarStatContext ctx) {
		return visitLocalVariableDeclaration(ctx.localVariableDeclaration());
	}

	@Override
	public OutputModelObject visitLocalVariableDeclaration(JParser.LocalVariableDeclarationContext ctx) {
        TypeSpec typeSpec;
        if(isClassName(ctx.jType().getText())){
            typeSpec = new ObjectTypeSpec(ctx.jType().getText());
        }
        else {
            typeSpec = new PrimitiveTypeSpec(ctx.jType().getText());
        }
        return new VarDef(ctx.ID().getText(),typeSpec);

	}

    @Override
    public OutputModelObject visitAssignStat(JParser.AssignStatContext ctx) {
        AssignStat asStat = new AssignStat();

        asStat.left = (Expr) visit(ctx.expression(0));
        asStat.right = (Expr)visit(ctx.expression(1));
        return asStat;
    }

    @Override
    public OutputModelObject visitPrintStat(JParser.PrintStatContext ctx) {
      PrintStat pStat = new PrintStat(ctx.STRING().getText());
//        List<JParser.ExpressionContext> expressions = ctx.expressionList().expression();
        List<OutputModelObject> temp = new ArrayList<>();
        for(JParser.ExpressionContext arg : ctx.expressionList().expression()) {
            OutputModelObject ar = visit(arg);
            temp.add(ar);
        }
        pStat.args = temp;
        return pStat;
    }

    @Override
    public OutputModelObject visitIdRef(JParser.IdRefContext ctx) {
        String idname = ctx.ID().getText();
        TypedSymbol jf = (TypedSymbol) currentScope.resolve(idname);
        TypeSpec typeSpec;
        if(isClassName(jf.getType().getName())){
            typeSpec = new ObjectTypeSpec(jf.getType().getName());
        }
        else{
            typeSpec = new PrimitiveTypeSpec(jf.getType().getName());
        }
        //do this to have something like this->x
        if(jf instanceof JField){
            idname = "this->"+idname;
        }

        return new VarRef(typeSpec,idname);

    }

    @Override
    public OutputModelObject visitLiteralRef(JParser.LiteralRefContext ctx) {
        String fname = ctx.getText();
        TypeSpec typeSpec;
        if(ctx.INT()!=null){
            typeSpec = new PrimitiveTypeSpec(ctx.INT().getText());
        }
        else {
            typeSpec = new PrimitiveTypeSpec(ctx.FLOAT().getText());
        }

        return new LiteralRef(typeSpec,fname);

    }

    @Override
    public OutputModelObject visitIfStat(JParser.IfStatContext ctx) {
        IfStat ifStat = new IfStat();
        ifStat.condition = (Expr) visit(ctx.parExpression());
        ifStat.stat = (Stat) visit(ctx.statement(0));
        if(ctx.statement(1) != null){
            IfElseStat ifElseStat = new IfElseStat();
            ifElseStat.condition = (Expr) visit(ctx.parExpression());
            ifElseStat.stat = (Stat) visit(ctx.statement(0));
            ifElseStat.elseStat = (Stat) visit(ctx.statement(1));
            return ifElseStat;
        }
        return ifStat;

    }

    @Override
    public OutputModelObject visitParExpression(JParser.ParExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public OutputModelObject visitReturnStat(JParser.ReturnStatContext ctx) {
        ReturnStat rStat = new ReturnStat();
        rStat.expr = (Expr) visit(ctx.expression());
        return rStat;
    }

    @Override
    public OutputModelObject visitPrintStringStat(JParser.PrintStringStatContext ctx) {
        PrintStringStat psStat = new PrintStringStat(ctx.STRING().getText());
        return psStat;

    }

    @Override
    public OutputModelObject visitWhileStat(JParser.WhileStatContext ctx) {
        WhileStat wStat = new WhileStat();
        wStat.condition = (Expr) visit(ctx.parExpression());
        wStat.stat = (Stat)visit(ctx.statement());
        return wStat;
        //Maven test
    }

    @Override
    public OutputModelObject visitMethodDeclaration(JParser.MethodDeclarationContext ctx) {
	    currentScope = ctx.scope;
        MethodDef methodDef = new MethodDef();
        methodDef.funcName = currentClass.getName()+"_"+ctx.ID().getText();
        System.out.println(currentClass.getName());
       JMethod jMethod = (JMethod) currentScope.resolve(ctx.ID().getText());
        //System.out.println(jMethod.getType().getName());

        TypeSpec typeSpec1;

        if(isClassName(jMethod.getType().getName())){
            typeSpec1 = new ObjectTypeSpec(jMethod.getType().getName());
        }
        else {
            typeSpec1 = new PrimitiveTypeSpec(jMethod.getType().getName());
        }

        methodDef.returnType = typeSpec1;
        List<VarDef> mArgs = new ArrayList<>();

        //method args
        TypeSpec typeSpec;
        if(isClassName(currentClass.getName())){
            typeSpec = new ObjectTypeSpec(currentClass.getName());
        }
        else {
            typeSpec = new PrimitiveTypeSpec(currentClass.getName());
        }
        VarDef varDef = new VarDef("this",typeSpec); //add *this
        mArgs.add(varDef);
        if(ctx.formalParameters().formalParameterList() != null){
            List<JParser.FormalParameterContext> parameters =
                    ctx.formalParameters().formalParameterList().formalParameter();
            for(int i=0; i<parameters.size(); i++){
                mArgs.add((VarDef) visit(ctx.formalParameters().formalParameterList().formalParameter(i)));
            }
            System.out.println("size is "+parameters.size());
        }
        methodDef.args = mArgs;

        //Method body

        methodDef.body = (Block) visit(ctx.methodBody().block());

        currentScope = currentScope.getEnclosingScope();
        return methodDef;
    }


    @Override
    public OutputModelObject visitClassDeclaration(JParser.ClassDeclarationContext ctx) {
	    currentScope = ctx.scope;
	    currentClass = (JClass) ctx.scope;
        ClassDef classDef = new ClassDef(currentClass);
        classDef.name = ctx.ID(0).toString();
        Set<MethodSymbol> visibleMethods = classDef.jclazz.getMethods();
        for(MemberSymbol ms : visibleMethods){
            if(ms instanceof JMethod){
                FuncName funcName  = new FuncName((JMethod) ms, ((JMethod) ms).getEnclosingScope().getName());
                classDef.hashdefinelist.add("#define "+ctx.ID(0).toString()+"_"+ms.getName()+"_SLOT " + ms.getSlotNumber());
                classDef.vtable.add(funcName);
            }
        }

        //add the fields from the parent

        List<FieldSymbol> parentFields = (List<FieldSymbol>) classDef.jclazz.getFields();
        for(FieldSymbol fieldSymbol : parentFields){
            TypeSpec typeSpec;
            if(isClassName(fieldSymbol.getType().getName())){
                typeSpec = new ObjectTypeSpec(fieldSymbol.getType().getName());
            }
            else{
                typeSpec = new PrimitiveTypeSpec(fieldSymbol.getType().getName());
            }
            VarDef parentField = new VarDef(fieldSymbol.getName(),typeSpec);
            classDef.fields.add(parentField);
        }

        //add fields from the current class

//        for(JParser.ClassBodyDeclarationContext classBodyDeclarationContext : ctx.classBody().classBodyDeclaration()){
//            OutputModelObject outputModelObject = visit(classBodyDeclarationContext);
//            if(outputModelObject instanceof VarDef)
//            {
//                classDef.fields.add( (VarDef) outputModelObject);
//            }
//        }

        List<JParser.ClassBodyDeclarationContext> cbDeclaration = ctx.classBody().classBodyDeclaration();
        for(int i=0;i<cbDeclaration.size();i++){
            if(ctx.classBody().classBodyDeclaration(i).methodDeclaration() != null){
                classDef.methods.add((MethodDef) visit(ctx.classBody().classBodyDeclaration(i).methodDeclaration()));
            }
        }
        currentScope = currentScope.getEnclosingScope();
        return classDef;
    }

    @Override
    public OutputModelObject visitFieldDeclaration(JParser.FieldDeclarationContext ctx) {
        TypeSpec typeSpec;
        if(isClassName(ctx.jType().getText())){
            typeSpec = new ObjectTypeSpec(ctx.jType().getText());
        }
        else {
            typeSpec = new PrimitiveTypeSpec(ctx.jType().getText());
        }
        return new VarDef(ctx.ID().getText(),typeSpec);
    }

    @Override
    public OutputModelObject visitCtorCall(JParser.CtorCallContext ctx) {
        String fname = ctx.ID().getText();
        JClass jf = (JClass) currentScope.resolve(fname);
        TypeSpec typeSpec;
        if(isClassName(jf.getName())){
            typeSpec = new ObjectTypeSpec(jf.getName());
        }
        else{
            typeSpec = new PrimitiveTypeSpec(jf.getName());
        }

        CtorCall ctorCall = new CtorCall(typeSpec);
        ctorCall.className = fname;
        return ctorCall;
    }

    @Override
    public OutputModelObject visitFieldRef(JParser.FieldRefContext ctx) {
        String fname = ctx.ID().getText();
        JClass jc = (JClass) currentScope.resolve(ctx.expression().type.getName());
        TypedSymbol typedSymbol = (TypedSymbol) jc.resolveMember(fname);
        TypeSpec typeSpec;
        if(isClassName(typedSymbol.getType().getName())){
            typeSpec = new ObjectTypeSpec(typedSymbol.getType().getName());
        }
        else{
            typeSpec = new PrimitiveTypeSpec(typedSymbol.getType().getName());
        }

        FieldRef fieldRef = new FieldRef(typeSpec);
        fieldRef.fieldName = fname;
        fieldRef.object = (Expr) visit(ctx.expression());
        return fieldRef;
    }

    @Override
    public OutputModelObject visitFormalParameter(JParser.FormalParameterContext ctx) {
        TypeSpec typeSpec;
        if(isClassName(ctx.jType().getText())){
            typeSpec = new ObjectTypeSpec(ctx.jType().getText());
        }
        else {
            typeSpec = new PrimitiveTypeSpec(ctx.jType().getText());
        }
        return new VarDef(ctx.ID().getText(),typeSpec);
    }

    /** Pretend we have type information */
    public boolean isClassName(String typename) {
        return Character.isUpperCase(typename.charAt(0));
    }

    @Override
    public OutputModelObject visitCallStat(JParser.CallStatContext ctx) {
        CallStat callStat = new CallStat();
        callStat.call = (Expr) visit(ctx.expression());
        return callStat;
    }

    @Override
    public OutputModelObject visitQMethodCall(JParser.QMethodCallContext ctx) {
        String functionname = ctx.ID().getText();
        JClass jClass = (JClass) currentScope.resolve(ctx.expression().type.getName());
        JMethod jMethod = (JMethod) jClass.resolveMember(ctx.ID().getText());
        TypeSpec typeSpec3;
        if(isClassName(ctx.expression().type.getName())){
            typeSpec3 = new ObjectTypeSpec(ctx.expression().type.getName());
        }
        else {
            typeSpec3 = new PrimitiveTypeSpec(ctx.expression().type.getName());
        }

        MethodCall methodCall = new MethodCall(typeSpec3,functionname,jClass.getName());

        Expr left = (Expr) visit(ctx.expression());
        methodCall.receiver = left;
        methodCall.receiverType = left.type;

        FuncPtrType fpt = new FuncPtrType();
        TypeSpec t;
        if(isClassName(jMethod.getType().getName())){
            t = new ObjectTypeSpec(jMethod.getType().getName());
        }
        else {
            t = new PrimitiveTypeSpec(jMethod.getType().getName());
        }
        fpt.returnType = t;

        fpt.argTypes.add(left.type);

        if(ctx.expressionList()!=null){
            List<JParser.ExpressionContext> arguments = ctx.expressionList().expression();
            TypeSpec t6;
            for(int temp1 = 0;temp1<arguments.size();temp1++){
                if(isClassName(arguments.get(temp1).type.getName())){
                    t6 = new ObjectTypeSpec(arguments.get(temp1).type.getName());
                }
                else {
                    t6 = new PrimitiveTypeSpec(arguments.get(temp1).type.getName());
                }
                fpt.argTypes.add(t6);
                methodCall.args.add((Expr) visit(ctx.expressionList().expression(temp1)));
            }
        }
        methodCall.fptrType = fpt;
        return methodCall;

    }
}
