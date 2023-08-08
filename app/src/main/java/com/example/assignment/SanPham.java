package com.example.assignment;

import androidx.annotation.NonNull;

public class SanPham {
    public int ma;
    public String ten;
    public int gia, sl;

    public SanPham(int ma, String ten, int gia, int sl) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.sl = sl;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    @NonNull
    @Override
    public String toString() {
        return ten + "\n" + "Giá: " + gia + " VND - Số lượng: còn " + sl;
    }
}
