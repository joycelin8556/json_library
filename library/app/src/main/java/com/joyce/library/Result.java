package com.joyce.library;

import java.util.List;

/**
 * Created by Joyce on 2016/12/15.
 */
public class Result {
    String limit;
    int offset;
    int count;
    String sort;
    List<Station> results;
    public List<Station> getResults() {
        return results;
    }

    public String getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }
    public String getSort() {
        return sort;
    }
}
