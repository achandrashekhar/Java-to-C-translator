package cs652.j.codegen.model;

/**
 * Created by ashi on 4/1/17.
 */
public class FieldRef extends Expr {
  public String fieldName;
  @ModelElement public Expr object;

}
