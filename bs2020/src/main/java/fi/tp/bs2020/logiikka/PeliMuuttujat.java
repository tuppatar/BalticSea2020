package fi.tp.bs2020.logiikka;

import java.util.ArrayList;
import java.util.List;

/**
 * Pelissä olevat maastoon liittyvät muuttujat. Nämä ovat omassa luokassaan, jotta pelissä näitä
 * voi menussa helposti vaihtaa.
 */
public class PeliMuuttujat {
    
    private int pelaaja, vastustaja, maata, taloja, hajanaisuusArvo;
    private int elementit, hajanaisuus; // nämä ovat 0-2
    private boolean musaOn, aanetOn;
    private List<Integer> laivoja;
    
    /**
     * Konstruktori. Sen käyttämä asetaMuuttujat asettaa maastonluomisen muuttujat.
     * @param moodi on pelimoodi.
     * @param hajanaisuus = saariston hajanaisuus.
     */
    public PeliMuuttujat() {
        this.pelaaja = 0; //svefif
        this.vastustaja = 1; // new comecon
        this.elementit = 2;
        this.hajanaisuus = 0;
        this.musaOn = false;
        this.aanetOn = false;
        //this.asetaMuuttujat();
    }

    public int getPelaaja() {
        return pelaaja;
    }

    public void setPelaaja(int pelaaja) {
        this.pelaaja = pelaaja;
    }

    public int getVastustaja() {
        return vastustaja;
    }

    public void setVastustaja(int vastustaja) {
        this.vastustaja = vastustaja;
    }
    
    public void asetaMuuttujatPeliaVarten() {
        this.asetaMuuttujat();
        this.asetaHajanaisuus();
    }
    
    private void asetaMuuttujat() {
        this.laivoja = new ArrayList<>();
        if (elementit == 0) {
            maata = 80;
            taloja = 3;
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
        if (elementit == 1) {
            maata = 140;
            taloja = 9;
            laivoja.add(6);
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
        if (elementit == 2) {
            maata = 200;
            taloja = 15;
            laivoja.add(6);
            laivoja.add(5);
            laivoja.add(4);
            laivoja.add(4);
            laivoja.add(3);
            laivoja.add(3);
            laivoja.add(2);
            laivoja.add(1);
        }
    }
    
    private void asetaHajanaisuus() {
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

    public int getElementit() {
        return elementit;
    }

    public void setElementit(int elementit) {
        this.elementit = elementit;
    }

    public int getHajanaisuus() {
        return hajanaisuus;
    }

    public void setHajanaisuus(int hajanaisuus) {
        this.hajanaisuus = hajanaisuus;
    }

    public boolean isMusaOn() {
        return musaOn;
    }

    public void setMusaOn(boolean musaOn) {
        this.musaOn = musaOn;
    }

    public boolean isAanetOn() {
        return aanetOn;
    }

    public void setAanetOn(boolean aanetOn) {
        this.aanetOn = aanetOn;
    }

}
