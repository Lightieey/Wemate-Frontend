package com.srd.wemate.expense;

public class ExpenseData {
    private String id;
    private int money;
    private String purpose;
    private String date;
    private String memo;
    private String uid;

    public ExpenseData(String id, int money, String purpose, String date, String memo, String uid) {
        this.id = id;
        this.money = money;
        this.purpose = purpose;
        this.date = date;
        this.memo = memo;
        this.uid = uid;
    }

    public ExpenseData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String u_id) {
        this.uid = u_id;
    }


}
