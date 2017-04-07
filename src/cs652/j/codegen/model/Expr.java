package cs652.j.codegen.model;

public abstract class Expr extends OutputModelObject {
    @ModelElement public TypeSpec type;
    public Expr(TypeSpec type){
        this.type = type;
    }
}