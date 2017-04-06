package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 3/31/17.
 */
public class MainMethod extends OutputModelObject {
    @ModelElement public List<OutputModelObject> statements = new ArrayList<OutputModelObject>();
public @ModelElement Block body;
    @ModelElement public List<VarDef> declarations = null;
}
