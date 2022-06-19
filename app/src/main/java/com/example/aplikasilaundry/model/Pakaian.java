package com.example.aplikasilaundry.model;

public class Pakaian {

    private String id,berat,total;

    public Pakaian() {
    }

    public Pakaian(String berat, String total) {

        this.berat = berat;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
