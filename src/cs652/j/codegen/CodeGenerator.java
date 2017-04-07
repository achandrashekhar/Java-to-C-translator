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
		    if(stat instanceof JParser.LocalVarStatContext) {
                OutputModelObject smt = visit(stat);
                block.locals.add((VarDef) smt);
            } else {
                OutputModelObject smt = visit(stat);
                block.instrs.add((Stat) smt);
            }
		}
		currentScope = currentScope.getEnclosingScope();
		return block;
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
        return new VarRef(ctx.ID().getText());
    }

    @Override
    public OutputModelObject visitLiteralRef(JParser.LiteralRefContext ctx) {

	    return new LiteralRef(ctx.getText());
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
        wStat.stat = ctx.statement().getText();
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
        methodDef.returnType = jMethod.getType().getName();
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

        for(JParser.ClassBodyDeclarationContext classBodyDeclarationContext : ctx.classBody().classBodyDeclaration()){
            OutputModelObject outputModelObject = visit(classBodyDeclarationContext);
            if(outputModelObject instanceof VarDef)
            {
                classDef.fields.add( (VarDef) outputModelObject);
            }
        }

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
        CtorCall ctorCall = new CtorCall();
        ctorCall.className = ctx.ID().getText();
        return ctorCall;
    }

    @Override
    public OutputModelObject visitFieldRef(JParser.FieldRefContext ctx) {
        FieldRef fieldRef = new FieldRef();
        fieldRef.fieldName = ctx.ID().getText();
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





}
