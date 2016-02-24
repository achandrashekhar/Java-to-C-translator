package cs652.j.semantics;

import org.antlr.symtab.ClassSymbol;
import org.antlr.symtab.MemberSymbol;
import org.antlr.symtab.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class JClass extends ClassSymbol {
	public JClass(String name, ParserRuleContext tree) {
		super(name);
		setDefNode(tree);
	}
}
