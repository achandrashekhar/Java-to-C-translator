package cs652.j.codegen.model;


/**
 * Created by ashi on 3/31/17.
 */
public class VarDef extends OutputModelObject {
    public final String id;
    public String type;

    public VarDef(String name, String type) {
        this.id = name;
        this.type = type;
    }
}
