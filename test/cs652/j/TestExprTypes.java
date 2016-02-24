package cs652.j;

import cs652.j.parser.JLexer;
import cs652.j.parser.JParser;
import cs652.j.semantics.ComputeTypes;
import cs652.j.semantics.DefineScopesAndSymbols;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Utils;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestExprTypes extends CommonBaseTest {
	public TestExprTypes(String filename) {
		super(filename);
	}

	@Test
	public void testCGen() throws Exception {
		checkSymbolDefs(filename);
	}

	public void checkSymbolDefs(String filename) throws Exception {
		URL testFolderURL = TestSymbolDefs.class.getClassLoader().getResource(SAMPLES_DIR);
		String testFolder = testFolderURL.getPath();

		String J_pathToFile = testFolder+"/"+filename;
		String Types_filename = basename(filename)+".types";

		GlobalScope globals = new GlobalScope(null);

		ANTLRInputStream input = new ANTLRFileStream(J_pathToFile);
		JLexer l = new JLexer(input);
		TokenStream tokens = new CommonTokenStream(l);

		JParser parser = new JParser(tokens);
		parser.removeErrorListeners();
		int[] errors = {0};
		parser.addErrorListener(new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				errors[0]++;
			}
		});
		ParserRuleContext tree = parser.file();
		assertTrue(tree!=null);
		assertEquals(Token.EOF, tree.getStop().getType());
		assertEquals(0, errors[0]);

		DefineScopesAndSymbols def = new DefineScopesAndSymbols(globals);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(def, tree);

		ComputeTypes computeTypes = new ComputeTypes(globals);
		walker = new ParseTreeWalker();
		walker.walk(computeTypes, tree);

		String expecting = readFile(new File(testFolder,Types_filename).getPath());
		String result = computeTypes.getRefOutput();
		assertEquals(expecting, result);
	}
}
