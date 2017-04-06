package cs652.j.codegen.model;


/**
 * Created by ashi on 3/31/17.
 */
public class VarDef extends OutputModelObject {
    public String id;
    public @ModelElement TypeSpec type;

    public VarDef(String name, TypeSpec type) {
        this.id = name;
        this.type = type;
    }
}
