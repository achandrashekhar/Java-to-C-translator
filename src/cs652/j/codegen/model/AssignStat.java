package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class AssignStat extends Stat {
    @ModelElement public Expr left;
    @ModelElement public Expr right;

}
