package cs652.j.semantics;

import cs652.j.parser.JParser;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.Type;
import org.antlr.symtab.TypedSymbol;
import org.antlr.symtab.VariableSymbol;

public class ComputeTypes extends JBaseListener {
	protected StringBuilder buf = new StringBuilder();

	public static final Type JINT_TYPE = new JPrimitiveType("int");
	public static final Type JFLOAT_TYPE = new JPrimitiveType("float");
	public static final Type JSTRING_TYPE = new JPrimitiveType("string");
	public static final Type JVOID_TYPE = new JPrimitiveType("void");

	public ComputeTypes(GlobalScope globals) {
		this.currentScope = globals;
	}

	// ...

	// S U P P O R T

	public String getRefOutput() {
		return buf.toString();
	}
}

