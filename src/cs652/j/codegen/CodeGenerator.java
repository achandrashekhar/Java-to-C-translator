package cs652.j.codegen;


import cs652.j.codegen.model.*;
import cs652.j.parser.JBaseVisitor;
import cs652.j.parser.JParser;
import cs652.j.semantics.JClass;
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
		MainMethod mainMethod = new MainMethod();
		mainMethod.body = (Block) visit(ctx.block());
		return mainMethod;
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
		asStat.left = (Expr)visit(ctx.expression(0));
		asStat.right = (Expr)visit(ctx.expression(1));
		return asStat;
	}

}
