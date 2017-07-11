package com.jason.studydagger2.mvpmodel.http.event;

/**
 * Created by jason_sunyf on 2017/7/11.
 * Email:yufeng.sun@21wendao.cn
 */

public class SearchEvent {

    public SearchEvent(String query, int type) {
        this.query = query;
        this.type = type;
    }

    private String query;

    private int type;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
