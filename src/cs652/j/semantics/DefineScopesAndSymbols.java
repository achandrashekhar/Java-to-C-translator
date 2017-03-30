package cs652.j.semantics;

import cs652.j.parser.JBaseListener;
import cs652.j.parser.JParser;
import org.antlr.symtab.*;
import org.antlr.symtab.Scope;

public class DefineScopesAndSymbols extends JBaseListener {
	public Scope currentScope;

	public DefineScopesAndSymbols(GlobalScope globals) {
		currentScope = globals;
		currentScope.define((Symbol)ComputeTypes.JINT_TYPE);
		currentScope.define((Symbol)ComputeTypes.JFLOAT_TYPE);
		currentScope.define((Symbol)ComputeTypes.JSTRING_TYPE);
		currentScope.define((Symbol)ComputeTypes.JVOID_TYPE);
	}

	@Override
	public void enterFile(JParser.FileContext ctx) {
		ctx.scope = (GlobalScope)currentScope;
	}

	@Override
	public void exitFile(JParser.FileContext ctx) {
		popScope();
	}

	@Override
	public void enterClassDeclaration(JParser.ClassDeclarationContext ctx) {
		JClass jClass = new JClass(ctx.name.getText(),ctx);
		jClass.setEnclosingScope(currentScope);
		if (ctx.superClass != null){
			jClass.setSuperClass(ctx.superClass.getText());
		}
		currentScope.define(jClass);
		ctx.scope = jClass;
		pushScope(jClass);

	}

	@Override
	public void exitClassDeclaration(JParser.ClassDeclarationContext ctx) {
		popScope();
	}

	@Override
	public void enterMethodDeclaration(JParser.MethodDeclarationContext ctx) {
		JMethod jmethod = new JMethod(ctx.ID().getText(),ctx);
		jmethod.setEnclosingScope(currentScope);
		currentScope.define(jmethod);
		//	jmethod.setType((Type) currentScope.resolve(ctx.jType().getText()));
		//This took a while to figure out
		Type retType;
		if(ctx.jType() != null)
			retType = (Type)currentScope.resolve(ctx.jType().getText());
		else
			retType = (Type)currentScope.resolve("void");
		jmethod.setType(retType);
		ctx.scope=jmethod;
		pushScope(jmethod);
		JField jField = new JField("this");
		currentScope.define(jField);
		jField.setType((JClass) currentScope.getEnclosingScope());
	}

	@Override
	public void exitMethodDeclaration(JParser.MethodDeclarationContext ctx) {
		popScope();
	}

	@Override
	public void enterMain(JParser.MainContext ctx) {
//		System.out.println(ctx.toString());
		JMethod jmethod = new JMethod("main",ctx);
		jmethod.setType(ComputeTypes.JVOID_TYPE);
		jmethod.setEnclosingScope(currentScope);
		currentScope.define(jmethod);
		ctx.scope=jmethod;
		pushScope(jmethod);
	}

	@Override
	public void exitMain(JParser.MainContext ctx) {
		popScope();
	}

	@Override
	public void enterBlock(JParser.BlockContext ctx) {
		LocalScope localscope = new LocalScope(currentScope);
		ctx.scope = localscope;
		pushScope(localscope);

	}

	@Override
	public void enterFormalParameter(JParser.FormalParameterContext ctx) {
		JArg jArg = new JArg(ctx.ID().getText());
		jArg.setScope(currentScope);
		currentScope.define(jArg);
		jArg.setType((Type) currentScope.resolve(ctx.jType().getText()));

	}


	@Override
	public void enterFieldDeclaration(JParser.FieldDeclarationContext ctx) {
		JField jField = new JField(ctx.ID().getText());
		jField.setScope(currentScope);
		currentScope.define(jField);
		jField.setType((Type) currentScope.resolve(ctx.jType().getText()));
	}

	@Override
	public void enterLocalVariableDeclaration(JParser.LocalVariableDeclarationContext ctx) {
		JVar jvar = new JVar(ctx.ID().getText());
		jvar.setScope(currentScope);
		currentScope.define(jvar);
		jvar.setType((Type) currentScope.resolve(ctx.jType().getText()));
	}

	@Override
	public void exitBlock(JParser.BlockContext ctx) {
		popScope();
	}

	private void pushScope(Scope s) {
		if (!(s instanceof Symbol)){
			currentScope.nest(s);
		}
		currentScope = s;

	}

	private void popScope() {
		currentScope = currentScope.getEnclosingScope();
	}
}
