package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 4/1/17.
 */
public class FuncPtrType extends OutputModelObject {
    @ModelElement public TypeSpec returnType;
    @ModelElement public List<TypeSpec> argTypes = new ArrayList<>();
}
