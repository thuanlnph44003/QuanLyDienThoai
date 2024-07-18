/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class cammeramolde {
    int Id_Cmr;
    String CamMera;

    public cammeramolde() {
    }

    public cammeramolde(int Id_Cmr, String CamMera) {
        this.Id_Cmr = Id_Cmr;
        this.CamMera = CamMera;
    }

    public int getId_Cmr() {
        return Id_Cmr;
    }

    public void setId_Cmr(int Id_Cmr) {
        this.Id_Cmr = Id_Cmr;
    }

    public String getCamMera() {
        return CamMera;
    }

    public void setCamMera(String CamMera) {
        this.CamMera = CamMera;
    }
    
}
