package cs652.j.semantics;

import cs652.j.parser.JBaseListener;
import cs652.j.parser.JParser;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.LocalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Type;
import org.antlr.symtab.VariableSymbol;

public class DefineScopesAndSymbols extends JBaseListener {
	public Scope currentScope;

	public DefineScopesAndSymbols(GlobalScope globals) {
		currentScope = globals;
	}
}
