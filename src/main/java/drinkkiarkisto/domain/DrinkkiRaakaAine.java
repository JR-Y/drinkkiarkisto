/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drinkkiarkisto.domain;

/**
 *
 * @author John-Robert
 */
public class DrinkkiRaakaAine {
    private Integer id;
    private final Drinkki drinkki;
    private final RaakaAine raakaAine;
    private Integer jarjestys;
    private String maara;
    private String ohje;

    public DrinkkiRaakaAine(Integer id, Drinkki drinkki, RaakaAine raakaAine, Integer jarjestys, String maara, String ohje) {
        this.id = id;
        this.drinkki = drinkki;
        this.raakaAine = raakaAine;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJarjestys() {
        return jarjestys;
    }

    public void setJarjestys(Integer jarjestys) {
        this.jarjestys = jarjestys;
    }

    public String getMaara() {
        return maara;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }   
    
}
