/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stoja
 */
@XmlRootElement(name = "CityRest")
public class CityRest {
    
    private int IDCity;
    private String name;

    public int getIDCity() {
        return IDCity;
    }

    public void setIDCity(int IDCity) {
        this.IDCity = IDCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityRest(int IDCity, String name) {
        this.IDCity = IDCity;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityRest{" + "IDCity=" + IDCity + ", name=" + name + '}';
    }
            
}
