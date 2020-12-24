package com.github.salpadding.jpa;

import java.util.*;

public abstract class AbstractRestriction implements Restriction {
    protected final List<Restriction> restrictions = new ArrayList<>();
    private final String delimiter;

    public AbstractRestriction(String delimiter, Restriction... rs) {
        this.delimiter = delimiter;
        this.restrictions.addAll(Arrays.asList(rs));
    }

    @Override
    public boolean isEmpty() {
        return restrictions.isEmpty();
    }

    public QuerySuffix build() {
        if (isEmpty())
            return QuerySuffix.EMPTY;
        Map<String, Object> params = new HashMap<>();
        String s = build(params);
        return new QuerySuffix(s, params);
    }

    String build(Map<String, Object> params) {
        List<String> subs = new ArrayList<>();
        for (int i = 0; i < restrictions.size(); i++) {
            Restriction r = restrictions.get(i);
            if (r.isEmpty())
                continue;
            if (r instanceof Column) {
                Column c = (Column) r;
                String[] fc = c.getCol().split("\\.");
                int j = 0;
                String s = String.format("%s_%d", fc[1], j);
                while (params.containsKey(s)) {
                    j++;
                    s = String.format("%s_%d", fc[1], j);
                }
                subs.add(String.format("(%s %s :%s)", c.getCol(), c.getOperator(), s));
                params.put(s, c.getVal());
            }
            if (r instanceof And) {
                And a = (And) r;
                subs.add("(" + a.build(params) + ")");
            }
            if (r instanceof Or) {
                Or o = (Or) r;
                subs.add("(" + o.build(params) + ")");
            }
        }
        return String.join(delimiter, subs);
    }
}
