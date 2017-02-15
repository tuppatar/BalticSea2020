package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Peliluokka vastaa pelin kokonaisuuden toiminnallisuudesta.
 */
public class Peli {
    
    private Random arpoja;
    private int[][] vastustajanMaasto, vastustajanPiirrettava, pelaajanMaasto, pelaajanPiirrettava;
    private boolean[][] vastustajanMaastoaNakyvissa;
    private MaastonLuoja ml;
    private int kursoriX, kursoriY, pelaajaOhittaaVuoroja, vastustajaOhittaaVuoroja;
    private Map<Integer, List<Integer>> vastustajanLaivojenKoordinaatit, omienLaivojenKoordinaatit;
    private TekoAly tekoaly;
    private Aanet aanet;

    public Peli(Random arpoja, Aanet aanet) {
        vastustajanMaasto = new int[20][20];
        vastustajanPiirrettava = new int[20][20];
        pelaajanMaasto = new int[20][20];
        pelaajanPiirrettava = new int[20][20];
        vastustajanMaastoaNakyvissa = new boolean[20][20];
        
        this.arpoja = arpoja;
        this.aanet = aanet;
        ml = new MaastonLuoja(arpoja);
        tekoaly = new TekoAly(arpoja);
        tekoaly.setAanet(aanet);
        
        vastustajanMaasto = ml.luoVastustajanMaasto();
        vastustajanPiirrettava = ml.getPiirrettava();
        vastustajanLaivojenKoordinaatit = ml.getLaivat(); //vastustajan laivat

        ml = new MaastonLuoja(arpoja);
        // pelaajanMaasto = ml.luoPelaajanMaasto(); // Laivojen omaa asettelua ei ole vielä toteutettu.
        pelaajanMaasto = ml.luoVastustajanMaasto(); // Laivojen omaa asettelua ei ole vielä toteutettu.
        pelaajanPiirrettava = ml.getPiirrettava();
        omienLaivojenKoordinaatit = ml.getLaivat(); // omat laivat
        
        tekoaly.setLaivanKoordinaatit(omienLaivojenKoordinaatit);
        for (int a = 0; a < 400; a++) {
            vastustajanMaastoaNakyvissa[a / 20][a % 20] = false;
        }
        kursoriX = 0;
        kursoriY = 0;
        pelaajaOhittaaVuoroja = 0;
        vastustajaOhittaaVuoroja = 0;
    }

    public void tauko(int msecs) {
        try {
            Thread.sleep(msecs);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }        
    }

    public int getPelaajaOhittaaVuoroja() {
        return pelaajaOhittaaVuoroja;
    }

    public void setPelaajaOhittaaVuoroja(int pelaajaOhittaaVuoroja) {
        this.pelaajaOhittaaVuoroja = pelaajaOhittaaVuoroja;
    }

    public int getVastustajaOhittaaVuoroja() {
        return vastustajaOhittaaVuoroja;
    }

    public void setVastustajaOhittaaVuoroja(int vastustaOhittaaVuoroja) {
        this.vastustajaOhittaaVuoroja = vastustaOhittaaVuoroja;
    }
    
    public int getKursoriX() {
        return kursoriX;
    }

    public void setKursoriX(int kx) {
        this.kursoriX = kx;
    }

    public int getKursoriY() {
        return kursoriY;
    }

    public void setKursoriY(int ky) {
        this.kursoriY = ky;
    }
    
    public boolean pelaaOmaVuoro() {
        if (vastustajanMaastoaNakyvissa[kursoriY][kursoriX]) {
            // TULOSTA INFORMAATIO "Olet jo ampunut tänne" tms.
            return false;
        }

        if (vastustajanMaasto[kursoriY][kursoriX] == 2) { // on laiva!
            vastustajanPiirrettava[kursoriY][kursoriX] += 10;
            josLaivaTuhottuKokonaanPiirraSeLaivaksi(kursoriY, kursoriX);
        } else { // muu kuin laiva!
            if (vastustajanMaasto[kursoriY][kursoriX] == 3) {
                this.setPelaajaOhittaaVuoroja(3);
            }
            vastustajanPiirrettava[kursoriY][kursoriX] += 100;
        }
        vastustajanMaasto[kursoriY][kursoriX] += 30;
        vastustajanMaastoaNakyvissa[kursoriY][kursoriX] = true;
        return true;
    }
    
    public void pelaaVastustajanVuoro() {
        int ampuu = tekoaly.siirto(pelaajanMaasto);
        //pelaajanMaasto[dy][dx] += 30;
        if (pelaajanMaasto[ampuu / 20][ampuu % 20] == 33) { // oli talo
            this.setVastustajaOhittaaVuoroja(3);
        }
        pelaajanPiirrettava[ampuu / 20][ampuu % 20] += 100;
    }
    
    private void josLaivaTuhottuKokonaanPiirraSeLaivaksi(int y, int x) {
        int mikaLaiva = palautaLaivaJohonOsuttiin(y, x); // palauttaa 200 jos ei osuttu, arvo 200 DEBUGGAUSTA varten, voidaan poistaa myöhemmin
        if (mikaLaiva < 200) {
            if (testaaOnkoLaivaKokonaanTuhottu(mikaLaiva)) {
                piirraTuhottuLaivaLaivaksi(mikaLaiva);
            }
        }
    }
    
    private int palautaLaivaJohonOsuttiin(int y, int x) { // palauttaa 200 jos ei osuttu
        int palautus = 200; // DEBUG
        for (int hoo: vastustajanLaivojenKoordinaatit.keySet()) {
            for (int a = 0; a < (vastustajanLaivojenKoordinaatit.get(hoo).size() / 2); a++) {
                if (vastustajanLaivojenKoordinaatit.get(hoo).get(a * 2) == y && vastustajanLaivojenKoordinaatit.get(hoo).get(a * 2 + 1) == x) {
                    palautus = hoo;
                }
            }
        }
        return palautus;
    }
    
    private boolean testaaOnkoLaivaKokonaanTuhottu(int mikaLaiva) {
        boolean kaikkiinPaloihinOsuttu = true;
        for (int a = 0; a < (vastustajanLaivojenKoordinaatit.get(mikaLaiva).size() / 2); a++) {
            if (vastustajanPiirrettava[vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2)][vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2 + 1)] < 20) {
                kaikkiinPaloihinOsuttu = false;
            }
        }
        return kaikkiinPaloihinOsuttu;
    }
    
    private void piirraTuhottuLaivaLaivaksi(int mikaLaiva) {
        for (int a = 0; a < (vastustajanLaivojenKoordinaatit.get(mikaLaiva).size() / 2); a++) {
            vastustajanPiirrettava[vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2)][vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2 + 1)] += 90;
        }
    }

    public int[][] getVastustajanMaasto() {
        return vastustajanMaasto;
    }

    public int[][] getVastustajanPiirrettava() {
        return vastustajanPiirrettava;
    }

    public int[][] getPelaajanMaasto() {
        return pelaajanMaasto;
    }

    public int[][] getPelaajanPiirrettava() {
        return pelaajanPiirrettava;
    }

    public boolean[][] getVisible() {
        return vastustajanMaastoaNakyvissa;
    }
    
    private int laskeEhjatLaivapalat(int[][] maasto) {
        int f = 0;
        for (int a = 0; a < 400; a++) {
            if (maasto[a / 20][a % 20] == 2) {
                f++;
            }
        }
        return f;
    }
    
    public int tarkistaVoitto() {
        int f = laskeEhjatLaivapalat(pelaajanMaasto);
        int g = laskeEhjatLaivapalat(vastustajanMaasto);
        
        if (f == 0 && g == 0) {
            return 3; // tasapeli
        }
        if (f == 0) {
            return 1; // vastustaja voitti
        }
        if (g == 0) {
            return 2; // pelaaja voitti
        }
        return 0; // ei voittoa;
    }
    
}
