package com.github.salpadding.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JpaTests {

    @Test
    public void test0(){
        And a = new And(new Column("m.value", "=", 128), new Column("m.value", "=", 199));
        And a1 = a.and(new Column("m.value2", "<", 190)).and(new And());
        QuerySuffix sf = a1.build();
        System.out.println(sf.getSuffix());
        sf.getParams().forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    @Test
    public void test1(){
        And a = new And(new Column("m.value", "=", 128), new Column("m.value", "=", 199));
        Or o = a.or(new Column("m.value2", "<", 190));
        QuerySuffix sf = o.build();
        System.out.println(sf.getSuffix());
        sf.getParams().forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }
}
