/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class chipmolde {
    int IdChip;
    String Chip;

    public chipmolde() {
    }

    public chipmolde(int IdChip, String Chip) {
        this.IdChip = IdChip;
        this.Chip = Chip;
    }

    public int getIdChip() {
        return IdChip;
    }

    public void setIdChip(int IdChip) {
        this.IdChip = IdChip;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String Chip) {
        this.Chip = Chip;
    }
    
}
