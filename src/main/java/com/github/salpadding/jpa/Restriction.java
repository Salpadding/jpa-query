package com.github.salpadding.jpa;

public interface Restriction {
    Restriction addChild(Restriction... rs);

    boolean isEmpty();

    QuerySuffix build();

    default And and(Restriction rs){
        return new And(this, rs);
    }

    default Or or(Restriction rs){
        return new Or(this, rs);
    }
}
