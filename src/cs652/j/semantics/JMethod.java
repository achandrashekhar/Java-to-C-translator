package cs652.j.semantics;

import org.antlr.symtab.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;

public class JMethod extends MethodSymbol {
	public JMethod(String name, ParserRuleContext tree) {
		super(name);
		setDefNode(tree);
	}
}
