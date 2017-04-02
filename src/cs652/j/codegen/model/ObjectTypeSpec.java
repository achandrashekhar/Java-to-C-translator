package cs652.j.codegen.model;

/**
 * Created by ashi on 3/31/17.
 */
public class ObjectTypeSpec extends TypeSpec {

    public String type;
    public String id;

    public ObjectTypeSpec(String type, String id) {
        this.type = type;
        this.id = id;
    }
}
