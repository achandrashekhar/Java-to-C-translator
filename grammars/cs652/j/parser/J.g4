grammar J;

@header {
import cs652.j.semantics.*;
import org.antlr.symtab.*;
}

file returns [GlobalScope scope]
    :   classDeclaration* main EOF
    ;

main returns [JMethod scope]
    :   block
    ;

classDeclaration returns [JClass scope]
    :   'class' name=ID ('extends' superClass=ID)? classBody
    ;

classBody
    :   '{' classBodyDeclaration* '}'
    ;

classBodyDeclaration
    :   ';'
    |   methodDeclaration
    |   fieldDeclaration
    ;

methodDeclaration returns [JMethod scope]
    :   'void' ID formalParameters methodBody
    |   jType  ID formalParameters methodBody
    ;

fieldDeclaration
    :   jType ID ';'
    ;

jType
    :   ID
    |   'int'
    |   'float'
    ;

formalParameters
    :   '(' formalParameterList? ')'
    ;

formalParameterList
    :   formalParameter (',' formalParameter)*
    ;

formalParameter
    :   jType ID
    ;

methodBody
    :   '{' block '}'
    ;

block returns [LocalScope scope]
    :   statement*
    ;

statement
    :   '{' block '}'                                       # BlockStat
    |   localVariableDeclaration ';'                        # LocalVarStat
    |   'if' parExpression statement ('else' statement)?    # IfStat
    |   'while' parExpression statement                     # WhileStat
    |   'return' expression? ';'                            # ReturnStat
    |   expression '=' expression ';'                       # AssignStat
    |   expression ';'                                      # CallStat
    |   'printf' '(' STRING ',' expressionList ')' ';'      # PrintStat
    |   'printf' '(' STRING ')' ';'                         # PrintStringStat
    ;

localVariableDeclaration
    :   jType ID
    ;

parExpression
    :   '(' expression ')'
    ;

expressionList
    :   expression (',' expression)*
    ;

expression returns [Type type]
    :   expression '.' ID                                   # FieldRef
    |   expression '.' ID '(' expressionList? ')'           # QMethodCall
    |   'new' ID '(' ')'                                    # CtorCall
    |   ID '(' expressionList? ')'                          # MethodCall
    |   '(' expression ')'                                  # Parens
    |   'this'                                              # ThisRef
    |   INT                                                 # LiteralRef
    |   FLOAT                                               # LiteralRef
    |   ID                                                  # IdRef
    |   'null'                                              # NullRef
    ;

INT :   [0-9]+ ;

FLOAT
    :   INT '.' INT   // 1.35, 1.35, 0.3, 4.5
    ;

fragment EXP :   [Ee] [+\-]? INT ;

ID : [a-zA-Z_] [a-zA-Z0-9_]* ;
STRING : '"' (~'"'|'\\"')* '"'  ;

COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;
LINE_COMMENT : '//' ~'\n'* '\n' -> channel(HIDDEN) ;
WS : [ \t\n\r]+ -> channel(HIDDEN) ;
