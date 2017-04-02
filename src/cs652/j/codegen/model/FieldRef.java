package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class FieldRef extends VarRef {
    @ModelElement public Expr entity;

    public FieldRef(String varField){
        super(varField);
    }

}
