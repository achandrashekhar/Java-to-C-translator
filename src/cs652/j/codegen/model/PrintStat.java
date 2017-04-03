package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 4/2/17.
 */
public class PrintStat extends Stat {
    public @ModelElement String name;
    public @ModelElement List<OutputModelObject> args = new ArrayList<>();
    //public @ModelElement String args;
    public PrintStat(String name){
        this.name = name;

    }
}
