package cs652.j.codegen;


import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import cs652.j.codegen.model.*;
import cs652.j.parser.JBaseVisitor;
import cs652.j.parser.JParser;
import cs652.j.semantics.JClass;
import cs652.j.semantics.JField;
import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;

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
		MainMethod main = (MainMethod) visit(ctx.main());
		CFile cfile = new CFile(fileName);
		cfile.main = main;
		return cfile;
	}

	@Override
	public OutputModelObject visitMain(JParser.MainContext ctx) {
//Code that works for int x;
		MainMethod mainMethod = new MainMethod();
		mainMethod.body = (Block) visit(ctx.block());
		return mainMethod;




//        MainMethod main = new MainMethod(); 
//        List<JParser.BlockStatContext> blockStatements = (List<JParser.BlockStatContext>) ctx.block(); 
//        for(JParser.BlockStatContext stat : blockStatements){ 
//            main.statements.add( visitBlockStatement(stat)); 
//        } 
//        return main;


//        MainMethod main = new MainMethod();
//        List<JParser.BlockStatContext> blockStatements = (List<JParser.BlockStatContext>)ctx.block();
//        for(JParser.BlockStatContext bs : blockStatements){
//            if(visitBlockStatement(bs) instanceof VarDef){
//                if(main.declarations == null)
//                    main.declarations = new ArrayList<>();
//                main.declarations.add((VarDef) visitBlockStatement(bs));
//
//            } else
//                main.statements.add((Stat)visitBlockStatement(bs));
//        }
//        return main;

	}

	@Override
	public OutputModelObject visitBlock(JParser.BlockContext ctx) {
		Block block = new Block();
		for (JParser.StatementContext stat : ctx.statement()){
			OutputModelObject smt = visit(stat);
			block.locals.add(smt);
		}
		return block;
	}

	@Override
	public OutputModelObject visitLocalVarStat(JParser.LocalVarStatContext ctx) {
		return visitLocalVariableDeclaration(ctx.localVariableDeclaration());
	}

	@Override
	public OutputModelObject visitLocalVariableDeclaration(JParser.LocalVariableDeclarationContext ctx) {
		VarDef var = new VarDef(ctx.ID().getText(),ctx.jType().getText());
		return var;
	}

    @Override
    public OutputModelObject visitAssignStat(JParser.AssignStatContext ctx) {
        AssignStat asStat = new AssignStat();
//        asStat.left = (Expr) visit(ctx.expression(0));
//        asStat.right = (Expr)visit(ctx.expression(1));
        for(JParser.ExpressionContext expressionContext: ctx.expression()){
            OutputModelObject outputModelObject = visit(expressionContext);
            if(outputModelObject instanceof VarRef){
                asStat.left = expressionContext.getText();
            }
            if(outputModelObject instanceof LiteralRef){
                asStat.right = expressionContext.getText();
            }
        }
        return asStat;
    }



//    @Override
//    public OutputModelObject visitPrintStat(JParser.PrintStatContext ctx) {
//
//            PrintStat printStat = new PrintStat(ctx.STRING().getText());
//            List<JParser.ExpressionContext> exprs= ctx.expressionList().expression();
//            for(JParser.ExpressionContext arg : exprs){
//                printStat.args.add((Expr) visit(arg));
//            }
//            return printStat;
//
//
//    }


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

//    @Override
//    public OutputModelObject visitIfStat(JParser.IfStatContext ctx) {
//
//    }
}
