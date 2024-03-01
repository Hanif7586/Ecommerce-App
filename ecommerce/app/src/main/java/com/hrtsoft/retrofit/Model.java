package com.hrtsoft.retrofit;

import java.util.List;

public class Model {


    private String total;
    private String skip;
    private String limit;
private List<products>products;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSkip() {
        return skip;
    }

    public void setSkip(String skip) {
        this.skip = skip;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<com.hrtsoft.retrofit.products> getProducts() {
        return products;
    }

    public void setProducts(List<com.hrtsoft.retrofit.products> products) {
        this.products = products;
    }
}
