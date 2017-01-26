/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.balticsea2020.gui;

import java.util.ArrayList;
import java.util.List;
import kuva.Fotari;
import kuva.Kuva;

/**
 *
 * @author jee
 */
public class KuvanKasittelija {

    private ArrayList<Kuva> kk;
    private List<String> tiedostot;

    public KuvanKasittelija() {
        this.kk = new ArrayList<>();
        tiedostot = new ArrayList<>();
        tiedostot.add("meri.png"); //0
        tiedostot.add("maa.png"); //1
        tiedostot.add("laiva_10.png"); //2
        tiedostot.add("laiva_11.png"); //3
        tiedostot.add("laiva_12.png"); //4
        tiedostot.add("laiva_13.png"); //5
        tiedostot.add("laiva_14.png"); //6
        tiedostot.add("laiva_15.png"); //7
        tiedostot.add("empty.png"); //8
        tiedostot.add("meri_osuma.png"); //9
        tiedostot.add("maa_osuma.png"); //10
        tiedostot.add("laiva_10_osuma.png"); //11
        tiedostot.add("laiva_11_osuma.png"); //12
        tiedostot.add("laiva_12_osuma.png"); //13
        tiedostot.add("laiva_13_osuma.png"); //14
        tiedostot.add("laiva_14_osuma.png"); //15
        tiedostot.add("laiva_15_osuma.png"); //16
        tiedostot.add("hit.png"); //17
    }
    
    public ArrayList<Kuva> lataaKuvat() {
        for (String aa : tiedostot) {
            Kuva kuva = Fotari.lataa(aa);
            this.kk.add(kuva);
        }
        return this.kk;
    }

    
}
