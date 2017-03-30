package cs652.j.semantics;

import cs652.j.parser.JBaseListener;
import cs652.j.parser.JParser;
import org.antlr.symtab.*;

public class ComputeTypes extends JBaseListener {
	private  Scope currentScope;
	protected StringBuilder buf = new StringBuilder();

	public static final Type JINT_TYPE = new JPrimitiveType("int");
	public static final Type JFLOAT_TYPE = new JPrimitiveType("float");
	public static final Type JSTRING_TYPE = new JPrimitiveType("string");
	public static final Type JVOID_TYPE = new JPrimitiveType("void");

	public ComputeTypes(GlobalScope globals) {
		this.currentScope = globals;
	}


	// S U P P O R T
	//Just pushing and popping all the current scopes to be able to resolve them
	@Override
	public void enterFile(JParser.FileContext ctx) {
		currentScope = ctx.scope;
	}

	@Override
	public void exitFile(JParser.FileContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterMain(JParser.MainContext ctx) {

		currentScope = ctx.scope;
	}

	@Override
	public void exitMain(JParser.MainContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterMethodDeclaration(JParser.MethodDeclarationContext ctx) {
		currentScope = ctx.scope;
	}

	@Override
	public void exitMethodDeclaration(JParser.MethodDeclarationContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterBlock(JParser.BlockContext ctx) {
		currentScope = ctx.scope;
	}

	@Override
	public void exitBlock(JParser.BlockContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterClassDeclaration(JParser.ClassDeclarationContext ctx) {
		currentScope = ctx.scope;
	}

	@Override
	public void exitClassDeclaration(JParser.ClassDeclarationContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	//push pop of scopes ends here

	//All the good stuff starts here
	@Override
	public void exitQMethodCall(JParser.QMethodCallContext ctx) {
		Type type = ctx.expression().type;
		if (type != null) {
			ctx.type = ((TypedSymbol) ((JClass) type).resolve(ctx.ID().getText())).getType();
			buf.append(ctx.getText() + " is " + ctx.type.getName() + "\n");
		}
	}


	@Override
	public void exitNullRef(JParser.NullRefContext ctx) {
		ctx.type =JVOID_TYPE;
		buf.append("null is " + JVOID_TYPE.getName() + "\n");
	}

	@Override
	public void exitCtorCall(JParser.CtorCallContext ctx) {
		JClass symbol = (JClass)currentScope.resolve(ctx.ID().getText());
		ctx.type = symbol;
		buf.append(ctx.getText() + " is " + ctx.type.getName() + "\n");
	}

	@Override
	public void exitLiteralRef(JParser.LiteralRefContext ctx) {
		if(ctx.INT() != null){
			ctx.type = JINT_TYPE;
		}
		else {
			ctx.type=JFLOAT_TYPE;
		}

		buf.append(ctx.getText() + " is " +  ctx.type.getName()  + "\n");
	}


	@Override
	public void exitIdRef(JParser.IdRefContext ctx) {
		VariableSymbol jVar = (VariableSymbol) currentScope.resolve(ctx.ID().getText());
		ctx.type = jVar.getType();
		buf.append(ctx.ID().getText()+" is "+ctx.type.getName()+"\n");
	}

	@Override
	public void exitMethodCall(JParser.MethodCallContext ctx) {
		if (ctx.ID().getText() != null) {
			ctx.type = ((TypedSymbol) currentScope.resolve(ctx.ID().getText())).getType();
			buf.append(ctx.getText() + " is " + ctx.type.getName() + "\n");
		}

	}

	@Override
	public void exitFieldRef(JParser.FieldRefContext ctx) {
		Type type = ctx.expression().type;
		if (type != null) {
			ctx.type = ((TypedSymbol) ((JClass) type).resolve(ctx.ID().getText())).getType();
			buf.append(ctx.getText() + " is " + ctx.type.getName() + "\n");
		}
	}


	@Override
	public void exitThisRef(JParser.ThisRefContext ctx) {
		//twiddle starts
		Scope s = currentScope;
		boolean jclassfound = false;
		while (s != null){
			if(s instanceof JClass){
				ctx.type = (JClass)s;
				jclassfound = true;
				break;
			}
			s = s.getEnclosingScope();
		}
		if (!jclassfound) {
			ctx.type = null;
		}
		if(ctx.type != null) {
			buf.append(ctx.getText() + " is " + ctx.type.getName() + "\n");
		}

	}

	public String getRefOutput() {
		return buf.toString();
	}
}

