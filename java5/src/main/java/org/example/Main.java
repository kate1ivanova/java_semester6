package org.example;


import org.example.entities.Injector;
import org.example.entities.SomeBean;

public class Main {
    public static void main(String[] args) {
        /*SomeBean sb = (new Injector()).inject(new SomeBean());
        sb.foo();*/

        /*SomeBean sb1 = (new Injector("res/some.properties")).inject(new SomeBean());
        sb1.foo();*/
        SomeBean sb2 = (new Injector("res/someOther.properties")).inject(new SomeBean());
        sb2.foo();
    }
}