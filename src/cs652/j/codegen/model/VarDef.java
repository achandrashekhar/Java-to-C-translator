package cs652.j.codegen.model;

/**
 * Created by ashi on 3/31/17.
 */
public class VarDef extends OutputModelObject {
    public final String name;
    public TypeSpec type;

    public VarDef(String name) {
        this.name = name;
    }
}
