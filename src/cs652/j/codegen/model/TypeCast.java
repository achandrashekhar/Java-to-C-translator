package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class TypeCast extends Expr {
    @ModelElement public TypeSpec type;
    @ModelElement public Expr expr;
}
