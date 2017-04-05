package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 3/31/17.
 */
public class MethodDef extends OutputModelObject {
    @ModelElement public String funcName;
    @ModelElement public TypeSpec returnType;
    @ModelElement public List<VarDef> args = new ArrayList<>();
    @ModelElement public Block body;
}
