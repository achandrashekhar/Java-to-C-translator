// Generated from cs652/j/parser/J.g4 by ANTLR 4.6
package cs652.j.parser;

import cs652.j.semantics.*;
import org.antlr.symtab.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JParser}.
 */
public interface JListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(JParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(JParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(JParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(JParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(JParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(JParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(JParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(JParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(JParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(JParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(JParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(JParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#jType}.
	 * @param ctx the parse tree
	 */
	void enterJType(JParser.JTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#jType}.
	 * @param ctx the parse tree
	 */
	void exitJType(JParser.JTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(JParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(JParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(JParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(JParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(JParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(JParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(JParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(JParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(JParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(JParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LocalVarStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVarStat(JParser.LocalVarStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LocalVarStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVarStat(JParser.LocalVarStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(JParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(JParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(JParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(JParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(JParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(JParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(JParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(JParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(JParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(JParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStat(JParser.PrintStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStat(JParser.PrintStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStringStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStringStat(JParser.PrintStringStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStringStat}
	 * labeled alternative in {@link JParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStringStat(JParser.PrintStringStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclaration(JParser.LocalVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#localVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclaration(JParser.LocalVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(JParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(JParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(JParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(JParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CtorCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCtorCall(JParser.CtorCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CtorCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCtorCall(JParser.CtorCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldRef(JParser.FieldRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldRef(JParser.FieldRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ThisRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisRef(JParser.ThisRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ThisRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisRef(JParser.ThisRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParens(JParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParens(JParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LiteralRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralRef(JParser.LiteralRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LiteralRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralRef(JParser.LiteralRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdRef(JParser.IdRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdRef(JParser.IdRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MethodCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(JParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MethodCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(JParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNullRef(JParser.NullRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullRef}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNullRef(JParser.NullRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code QMethodCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterQMethodCall(JParser.QMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code QMethodCall}
	 * labeled alternative in {@link JParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitQMethodCall(JParser.QMethodCallContext ctx);
}