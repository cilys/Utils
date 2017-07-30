package com.cily.utils.web.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 13218 on 2017/7/26.
 */
public class ResDataBaseBean<T, O> implements Serializable {
    private List<T> items;
    private O extra;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public final static <I>ResDataBaseBean getResDataBase(List<I> i, Object extra){
        ResDataBaseBean b = new ResDataBaseBean();
        b.setItems(i);
        b.setExtra(extra);
        return b;
    }

    public O getExtra() {
        return extra;
    }

    public void setExtra(O extra) {
        this.extra = extra;
    }
}
