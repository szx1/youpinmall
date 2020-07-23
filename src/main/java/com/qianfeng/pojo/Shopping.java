package com.qianfeng.pojo;

import java.io.Serializable;

public class Shopping implements Serializable {
    private Integer sid;

    private Integer bid;

    private Integer uid;

    private Integer num;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "sid=" + sid +
                ", bid=" + bid +
                ", uid=" + uid +
                ", num=" + num +
                '}';
    }
}