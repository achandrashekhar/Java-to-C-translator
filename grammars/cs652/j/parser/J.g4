grammar J;

@header {
import cs652.j.semantics.*; // You will need these for stuff in "returns" clauses
import org.antlr.symbols.*;
}

file returns [GlobalScope scope] // this allows us to annotate trees with symtab info
    :   ... EOF
    ;

expression returns [Type type] // annotate all expression nodes with type info
    :   ...
    ;

// ...
