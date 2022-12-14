package com.example.myapplication;

public class DangKyMoi {
    private String strTenDK;
    private String strTenHT;
    private String strEmailDK;
    private String strSDTDK;
    private String strMKDK;

    public DangKyMoi(String strTenDK, String strTenHT, String strEmailDK, String strSDTDK, String strMKDK) {
        this.strTenDK = strTenDK;
        this.strTenHT = strTenHT;
        this.strEmailDK = strEmailDK;
        this.strSDTDK = strSDTDK;
        this.strMKDK = strMKDK;
    }

    public DangKyMoi(){

    }

    public String getStrTenDK() {
        return strTenDK;
    }

    public void setStrTenDK(String strTenDK) {
        this.strTenDK = strTenDK;
    }

    public String getStrTenHT() {
        return strTenHT;
    }

    public void setStrTenHT(String strTenHT) {
        this.strTenHT = strTenHT;
    }

    public String getStrEmailDK() {
        return strEmailDK;
    }

    public void setStrEmailDK(String strEmailDK) {
        this.strEmailDK = strEmailDK;
    }

    public String getStrSDTDK() {
        return strSDTDK;
    }

    public void setStrSDTDK(String strSDTDK) {
        this.strSDTDK = strSDTDK;
    }

    public String getStrMKDK() {
        return strMKDK;
    }

    public void setStrMKDK(String strMKDK) {
        this.strMKDK = strMKDK;
    }
}
