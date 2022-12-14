package com.example.myapplication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DoAn implements Serializable {
    private int id;
    private int type;
    private String imageDoAn;
    private String TenDoAn;
    private String DiaChi;
    private String TinhTrang;
    private String Gia;
    private boolean YeuThich;
    private boolean GioHang;

    public DoAn() {
    }

    public DoAn(int id, int type, String imageDoAn, String tenDoAn, String diaChi, String tinhTrang, String gia, boolean yeuThich, boolean gioHang) {
        this.id = id;
        this.type = type;
        this.imageDoAn = imageDoAn;
        TenDoAn = tenDoAn;
        DiaChi = diaChi;
        TinhTrang = tinhTrang;
        Gia = gia;
        YeuThich = yeuThich;
        GioHang = gioHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImageDoAn() {
        return imageDoAn;
    }

    public void setImageDoAn(String imageDoAn) {
        this.imageDoAn = imageDoAn;
    }

    public String getTenDoAn() {
        return TenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        TenDoAn = tenDoAn;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public boolean isYeuThich() {
        return YeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        YeuThich = yeuThich;
    }

    public boolean isGioHang() {
        return GioHang;
    }

    public void setGioHang(boolean gioHang) {
        GioHang = gioHang;
    }

    public Map<String, Object> mapYeuThich(){
        HashMap<String, Object> yeuThichUp = new HashMap<>();
        yeuThichUp.put("YeuThich", YeuThich);
        yeuThichUp.put("GioHang", GioHang);
        return yeuThichUp;
    }


}
