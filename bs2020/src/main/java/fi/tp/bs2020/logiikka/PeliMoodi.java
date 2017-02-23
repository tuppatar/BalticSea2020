package fi.tp.bs2020.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelissä olevat maastoon liittyvät muuttujat. Nämä ovat omassa luokassaan, jotta pelissä näitä
 * voi menussa helposti vaihtaa.
 */
public class PeliMoodi {
    
    private int maata, taloja, hajanaisuusArvo;
    private List<Integer> laivoja;
    
    /**
     * Konstruktori. Sen käyttämä asetaMuuttujat asettaa maastonluomisen muuttujat.
     * @param moodi on pelimoodi.
     * @param hajanaisuus = saariston hajanaisuus.
     */
    public PeliMoodi(int moodi, int hajanaisuus) {
        this.laivoja = new ArrayList<>();
        this.asetaMuuttujat(moodi, hajanaisuus);
    }
    
    /**
     * Tämä on publiccina vain pelinteon takia, voidaan ajatella privatemetodiksi.
     * @param moodi on pelimoodi.
     * @param hajanaisuus = saariston hajanaisuus.
     */
    public void asetaMuuttujat(int moodi, int hajanaisuus) {
        this.hajanaisuusArvo = 15;
        if (moodi == 0) {
            maata = 80;
            taloja = 3;
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
        if (moodi == 1) {
            maata = 140;
            taloja = 6;
            laivoja.add(6);
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
        if (moodi == 2) {
            maata = 180;
            taloja = 10;
            laivoja.add(6);
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
        if (hajanaisuus == 2) {
            this.hajanaisuusArvo = 10;
        }
        if (hajanaisuus == 1) {
            this.hajanaisuusArvo = 40;
        }
        if (hajanaisuus == 0) {
            this.hajanaisuusArvo = 70;
        }
    }

    public int getMaata() {
        return maata;
    }

    public int getTaloja() {
        return taloja;
    }

    public List<Integer> getLaivoja() {
        return laivoja;
    }

    public int getHajanaisuusArvo() {
        return hajanaisuusArvo;
    }

    public void setHajanaisuusArvo(int hajanaisuusArvo) { // turha metodi?
        this.hajanaisuusArvo = hajanaisuusArvo;
    }
    
}
