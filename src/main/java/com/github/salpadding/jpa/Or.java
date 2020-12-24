package com.github.salpadding.jpa;

import java.util.Arrays;

public class Or extends AbstractRestriction {
    public Or(Restriction... rs) {
        super(" or ", rs);
    }

    @Override
    public Or addChild(Restriction... rs) {
        restrictions.addAll(Arrays.asList(rs));
        return this;
    }
}
