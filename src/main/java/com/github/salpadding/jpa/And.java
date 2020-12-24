package com.github.salpadding.jpa;

import java.util.Arrays;

public class And extends AbstractRestriction {
    public And(Restriction... rs) {
        super(" and ", rs);
    }

    @Override
    public And addChild(Restriction... rs) {
        restrictions.addAll(Arrays.asList(rs));
        return this;
    }
}
