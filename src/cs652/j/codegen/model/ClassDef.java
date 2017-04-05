package cs652.j.codegen.model;

import cs652.j.semantics.JClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashi on 3/31/17.
 */
public class ClassDef extends OutputModelObject {
    public JClass jclazz;
    public String name;
    @ModelElement public List<VarDef> fields=new ArrayList<>();
    @ModelElement public List<MethodDef> methods = new ArrayList<>();
    @ModelElement public List<VTable> vtable = new ArrayList<>();


//    public ClassDef(JClass jclazz)
//    {
//        this.jclazz = jclazz;
//    }

    public ClassDef(String name){
        this.name = name;
    }

//    public String getName(){
//        return jclazz.getName();
//    }

}
