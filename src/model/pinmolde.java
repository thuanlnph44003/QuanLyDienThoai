/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class pinmolde {
    int IdPin;
    String Pin;

    public pinmolde() {
    }

    public pinmolde(int IdPin, String Pin) {
        this.IdPin = IdPin;
        this.Pin = Pin;
    }

    public int getIdPin() {
        return IdPin;
    }

    public void setIdPin(int IdPin) {
        this.IdPin = IdPin;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String Pin) {
        this.Pin = Pin;
    }
    
}
