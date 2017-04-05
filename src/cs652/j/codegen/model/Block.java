package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 4/1/17.
 */
public class Block extends Stat {
    @ModelElement public List<OutputModelObject> locals = new ArrayList<>();
    @ModelElement public List<OutputModelObject> instrs = new ArrayList<>();
}
