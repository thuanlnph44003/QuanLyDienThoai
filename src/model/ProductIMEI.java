/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ACER
 */
public class ProductIMEI {
    private String maChiTietSP;
    private List<String> imeiList;

    public ProductIMEI(String maChiTietSP, List<String> imeiList) {
        this.maChiTietSP = maChiTietSP;
        this.imeiList = imeiList;
    }

    public String getMaChiTietSP() {
        return maChiTietSP;
    }

    public List<String> getImeiList() {
        return imeiList;
    }
}
