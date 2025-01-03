package com.example.demo.Entity;

import java.time.LocalDate;
public class Sales {
    private int id, billID, custID, prodID;
    private String sreilNum, billNum;
  private double piecePrice, discount, totPrice;
    private LocalDate date;
    public Sales(double piecePrice, double discount, double totPrice, LocalDate date, int prodID, String sreilNum, int billID, String billNum) {
        this.prodID = prodID;
        this.sreilNum = sreilNum;
        this.billNum = billNum;
        this.piecePrice = piecePrice;
        this.billID =billID;
        this.discount = discount;
        this.totPrice = totPrice;
        this.date = date;

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public String getSreilNum() {
        return sreilNum;
    }

    public void setSreilNum(String sreilNum) {
        this.sreilNum = sreilNum;
    }

    public String getBillNum() {
        return billNum;
    }

    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }

    public double getPiecePrice() {
        return piecePrice;
    }

    public void setPiecePrice(Float piecePrice) {
        this.piecePrice = piecePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(Float totPrice) {
        this.totPrice = totPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
