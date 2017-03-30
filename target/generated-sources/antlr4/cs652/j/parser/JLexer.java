// Generated from cs652/j/parser/J.g4 by ANTLR 4.6
package cs652.j.parser;

import cs652.j.semantics.*;
import org.antlr.symtab.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, INT=22, FLOAT=23, ID=24, STRING=25, 
		COMMENT=26, LINE_COMMENT=27, WS=28;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "INT", "FLOAT", "EXP", "ID", "STRING", 
		"COMMENT", "LINE_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'extends'", "'{'", "'}'", "';'", "'void'", "'int'", 
		"'float'", "'('", "')'", "','", "'if'", "'else'", "'while'", "'return'", 
		"'='", "'printf'", "'.'", "'new'", "'this'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "INT", "FLOAT", 
		"ID", "STRING", "COMMENT", "LINE_COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "J.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\36\u00d7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\26\3\26\3\26\3\27\6\27\u0096\n\27\r\27\16\27\u0097\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\5\31\u00a0\n\31\3\31\3\31\3\32\3\32\7\32"+
		"\u00a6\n\32\f\32\16\32\u00a9\13\32\3\33\3\33\3\33\3\33\7\33\u00af\n\33"+
		"\f\33\16\33\u00b2\13\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00ba\n\34"+
		"\f\34\16\34\u00bd\13\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\7"+
		"\35\u00c8\n\35\f\35\16\35\u00cb\13\35\3\35\3\35\3\35\3\35\3\36\6\36\u00d2"+
		"\n\36\r\36\16\36\u00d3\3\36\3\36\3\u00bb\2\37\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\2\63\32\65\33\67\349\35;\36\3\2\n\3\2\62;\4\2GGgg\4\2"+
		"--//\5\2C\\aac|\6\2\62;C\\aac|\3\2$$\3\2\f\f\5\2\13\f\17\17\"\"\u00dd"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2"+
		"\2\2\5C\3\2\2\2\7K\3\2\2\2\tM\3\2\2\2\13O\3\2\2\2\rQ\3\2\2\2\17V\3\2\2"+
		"\2\21Z\3\2\2\2\23`\3\2\2\2\25b\3\2\2\2\27d\3\2\2\2\31f\3\2\2\2\33i\3\2"+
		"\2\2\35n\3\2\2\2\37t\3\2\2\2!{\3\2\2\2#}\3\2\2\2%\u0084\3\2\2\2\'\u0086"+
		"\3\2\2\2)\u008a\3\2\2\2+\u008f\3\2\2\2-\u0095\3\2\2\2/\u0099\3\2\2\2\61"+
		"\u009d\3\2\2\2\63\u00a3\3\2\2\2\65\u00aa\3\2\2\2\67\u00b5\3\2\2\29\u00c3"+
		"\3\2\2\2;\u00d1\3\2\2\2=>\7e\2\2>?\7n\2\2?@\7c\2\2@A\7u\2\2AB\7u\2\2B"+
		"\4\3\2\2\2CD\7g\2\2DE\7z\2\2EF\7v\2\2FG\7g\2\2GH\7p\2\2HI\7f\2\2IJ\7u"+
		"\2\2J\6\3\2\2\2KL\7}\2\2L\b\3\2\2\2MN\7\177\2\2N\n\3\2\2\2OP\7=\2\2P\f"+
		"\3\2\2\2QR\7x\2\2RS\7q\2\2ST\7k\2\2TU\7f\2\2U\16\3\2\2\2VW\7k\2\2WX\7"+
		"p\2\2XY\7v\2\2Y\20\3\2\2\2Z[\7h\2\2[\\\7n\2\2\\]\7q\2\2]^\7c\2\2^_\7v"+
		"\2\2_\22\3\2\2\2`a\7*\2\2a\24\3\2\2\2bc\7+\2\2c\26\3\2\2\2de\7.\2\2e\30"+
		"\3\2\2\2fg\7k\2\2gh\7h\2\2h\32\3\2\2\2ij\7g\2\2jk\7n\2\2kl\7u\2\2lm\7"+
		"g\2\2m\34\3\2\2\2no\7y\2\2op\7j\2\2pq\7k\2\2qr\7n\2\2rs\7g\2\2s\36\3\2"+
		"\2\2tu\7t\2\2uv\7g\2\2vw\7v\2\2wx\7w\2\2xy\7t\2\2yz\7p\2\2z \3\2\2\2{"+
		"|\7?\2\2|\"\3\2\2\2}~\7r\2\2~\177\7t\2\2\177\u0080\7k\2\2\u0080\u0081"+
		"\7p\2\2\u0081\u0082\7v\2\2\u0082\u0083\7h\2\2\u0083$\3\2\2\2\u0084\u0085"+
		"\7\60\2\2\u0085&\3\2\2\2\u0086\u0087\7p\2\2\u0087\u0088\7g\2\2\u0088\u0089"+
		"\7y\2\2\u0089(\3\2\2\2\u008a\u008b\7v\2\2\u008b\u008c\7j\2\2\u008c\u008d"+
		"\7k\2\2\u008d\u008e\7u\2\2\u008e*\3\2\2\2\u008f\u0090\7p\2\2\u0090\u0091"+
		"\7w\2\2\u0091\u0092\7n\2\2\u0092\u0093\7n\2\2\u0093,\3\2\2\2\u0094\u0096"+
		"\t\2\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098.\3\2\2\2\u0099\u009a\5-\27\2\u009a\u009b\7\60\2\2"+
		"\u009b\u009c\5-\27\2\u009c\60\3\2\2\2\u009d\u009f\t\3\2\2\u009e\u00a0"+
		"\t\4\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\5-\27\2\u00a2\62\3\2\2\2\u00a3\u00a7\t\5\2\2\u00a4\u00a6\t\6\2"+
		"\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\64\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00b0\7$\2\2\u00ab"+
		"\u00af\n\7\2\2\u00ac\u00ad\7^\2\2\u00ad\u00af\7$\2\2\u00ae\u00ab\3\2\2"+
		"\2\u00ae\u00ac\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1"+
		"\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7$\2\2\u00b4"+
		"\66\3\2\2\2\u00b5\u00b6\7\61\2\2\u00b6\u00b7\7,\2\2\u00b7\u00bb\3\2\2"+
		"\2\u00b8\u00ba\13\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00be\u00bf\7,\2\2\u00bf\u00c0\7\61\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\u00c2\b\34\2\2\u00c28\3\2\2\2\u00c3\u00c4\7\61\2\2\u00c4\u00c5\7\61\2"+
		"\2\u00c5\u00c9\3\2\2\2\u00c6\u00c8\n\b\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb"+
		"\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c9\3\2\2\2\u00cc\u00cd\7\f\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b\35"+
		"\2\2\u00cf:\3\2\2\2\u00d0\u00d2\t\t\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3"+
		"\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00d6\b\36\2\2\u00d6<\3\2\2\2\13\2\u0097\u009f\u00a7\u00ae\u00b0\u00bb"+
		"\u00c9\u00d3\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}