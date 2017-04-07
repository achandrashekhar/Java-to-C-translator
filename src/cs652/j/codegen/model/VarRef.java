package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class VarRef extends Expr {
    public String name;
    public VarRef(TypeSpec type, String name){
        super(type);
        this.name = name;}
}
