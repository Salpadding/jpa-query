package com.github.salpadding.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Column implements Restriction {
    private final String col;
    private final String operator;
    private final Object val;

    public Column(String col, String operator, Object val) {
        this.col = col;
        this.operator = operator;
        this.val = val;
    }

    @Override
    public Column addChild(Restriction... rs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public QuerySuffix build() {
        String[] fc = getCol().split("\\.");
        Map<String, Object> m = new HashMap<>();
        m.put(fc[1], val);
        return new QuerySuffix(col + ' ' + operator + ' ' + ':' + fc[1], m);
    }

    String getCol() {
        return this.col;
    }

    String getOperator() {
        return this.operator;
    }

    Object getVal() {
        return this.val;
    }
}
