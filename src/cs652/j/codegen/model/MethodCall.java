package cs652.j.codegen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 4/1/17.
 */
public class MethodCall extends Expr {

    public String methodName;
    @ModelElement public Expr receiver;
    @ModelElement public FuncPtrType fptrType;
    @ModelElement public List<Expr> args = new ArrayList<>();
    @ModelElement public TypeSpec receiverType;
    public String className;

    public MethodCall(TypeSpec type, String methodName, String className){
        super(type);
        this.methodName = methodName;
        this.className = className;
    }
}
