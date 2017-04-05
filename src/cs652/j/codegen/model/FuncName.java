package cs652.j.codegen.model;

import cs652.j.semantics.JMethod;

/**
 * Created by ashi on 4/1/17.
 */
public class FuncName extends OutputModelObject {
    public JMethod method;

    public String name;
    public FuncName(JMethod method) {
        this.method = method;
    }

    public String getClassName(){
        return method.getEnclosingScope().toString();
    }

    public String getMethodName(){
        return method.getName();
    }

}
