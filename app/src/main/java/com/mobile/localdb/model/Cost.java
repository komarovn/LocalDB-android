package com.mobile.localdb.model;

public class Cost {
    private String name;
    private boolean isAsset;
    private Double amount;
    private int version;

    public Cost(String name, boolean isAsset, Double amount, int version) {
        this.name = name;
        this.isAsset = isAsset;
        this.amount = amount;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAsset() {
        return isAsset;
    }

    public void setAsset(boolean asset) {
        isAsset = asset;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
