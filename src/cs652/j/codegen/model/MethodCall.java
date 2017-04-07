package cs652.j.codegen.model;

import java.util.List;

/**
 * Created by ashi on 4/1/17.
 */
public class MethodCall extends Expr {
    @ModelElement public Expr receiver;
    @ModelElement public FuncPtrType fptrType;
    @ModelElement public List<Expr> args;
    @ModelElement public String className;
//    public MethodCall(String name){
//        this.className = name;
//    }
}
