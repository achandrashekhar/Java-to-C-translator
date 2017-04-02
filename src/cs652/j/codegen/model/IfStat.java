package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class IfStat extends Stat {
    @ModelElement public Expr condition;
    @ModelElement public Stat stat;
}
