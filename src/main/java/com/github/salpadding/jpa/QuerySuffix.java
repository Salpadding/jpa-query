package com.github.salpadding.jpa;

import java.util.Collections;
import java.util.Map;

public class QuerySuffix {
    public static final QuerySuffix EMPTY = new QuerySuffix("", Collections.emptyMap());

    private String suffix;
    private Map<String, Object> params;

    QuerySuffix() {
    }

    QuerySuffix(String suffix, Map<String, Object> params) {
        this.suffix = suffix;
        this.params = params;
    }

    public boolean isEmpty() {
        return suffix == null || suffix.isEmpty();
    }

    public String getSuffix() {
        return this.suffix;
    }

    void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Map<String, Object> getParams() {
        return this.params;
    }
}
