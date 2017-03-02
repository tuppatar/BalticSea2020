package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import fi.tp.bs2020.gui.Soittaja;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Peliluokka vastaa pelin kokonaisuuden toiminnallisuudesta. Tämän tulen varmaan vielä jakamaan kahteen erilaisesta
 * toiminnallisuudesta vastaamaan osaan.
 */
public class Peli {
    
    private Random arpoja;
    private int[][] vastustajanMaasto, vastustajanPiirrettava, pelaajanMaasto, pelaajanPiirrettava;
    private int[][] vastustajanMaastonSatunnaisuus;
    private boolean[][] vastustajanMaastoaNakyvissa;
    private MaastonLuoja ml;
    private int kursoriX = 0, kursoriY = 0, pelaajaOhittaaVuoroja = 0, vastustajaOhittaaVuoroja = 0, omaviesti = 0, vastustajanviesti = 0, soitettava = 0;
    private Map<Integer, List<Integer>> vastustajanLaivojenKoordinaatit, omienLaivojenKoordinaatit;
    private TekoAly tekoaly;
    private PeliMuuttujat moodi;
    private Soittaja soittaja;
    
    /**
     * Konstruktori.
     * @param arpoja    Random-olio.
     * @param aanet     Ääni-olio.
     */
    public Peli(Random arpoja, Soittaja soittaja, PeliMuuttujat moodi) {
        vastustajanMaasto = new int[20][20];
        vastustajanPiirrettava = new int[20][20];
        pelaajanMaasto = new int[20][20];
        pelaajanPiirrettava = new int[20][20];
        vastustajanMaastoaNakyvissa = new boolean[20][20];
        this.moodi = moodi;
        this.arpoja = arpoja;
        this.soittaja = soittaja;
        tekoaly = new TekoAly(arpoja);
        
        ml = new MaastonLuoja(arpoja);
        vastustajanMaasto = ml.luoVastustajanMaasto(moodi);
        vastustajanPiirrettava = ml.getPiirrettava();
        vastustajanMaastonSatunnaisuus = ml.getMaastonSatunnaisuus();
        vastustajanLaivojenKoordinaatit = ml.getLaivat(); //vastustajan laivat

        ml = new MaastonLuoja(arpoja);
        pelaajanMaasto = ml.luoVastustajanMaasto(moodi); // Laivojen omaa asettelua ei ole vielä toteutettu.
        pelaajanPiirrettava = ml.getPiirrettava();
        omienLaivojenKoordinaatit = ml.getLaivat(); // omat laivat
        
        tekoaly.setLaivanKoordinaatit(omienLaivojenKoordinaatit);
        for (int a = 0; a < 400; a++) {
            vastustajanMaastoaNakyvissa[a / 20][a % 20] = false;
        }
    }

    public PeliMuuttujat getMoodi() {
        return moodi;
    }

    public int[][] getVastustajanMaastonSatunnaisuus() {
        return vastustajanMaastonSatunnaisuus;
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

    public int getOmaviesti() {
        return omaviesti;
    }

    public int getVastustajanviesti() {
        return vastustajanviesti;
    }
    
    /**
     * Pelataan pelaajan oma vuoro.
     * @return tosi, jos vuoro onnistui (eli kohtaan ei ole ammuttu aikaisemmin).
     */
    public boolean pelaaOmaVuoro() {
        omaviesti = 0;
        if (vastustajanMaastoaNakyvissa[kursoriY][kursoriX]) {
            return false;
        }
        int soittoon = vastustajanMaasto[kursoriY][kursoriX];
        if (vastustajanMaasto[kursoriY][kursoriX] == 2) { // on laiva!
            vastustajanPiirrettava[kursoriY][kursoriX] += 10;
            josLaivaTuhottuKokonaanPiirraSeLaivaksi(kursoriY, kursoriX);
        } else { // muu kuin laiva!
            if (vastustajanMaasto[kursoriY][kursoriX] == 3) {
                omaviesti = 7;
                this.setPelaajaOhittaaVuoroja(3);
            }
            vastustajanPiirrettava[kursoriY][kursoriX] += 100;
        }
        soittaja.soitaPelaaja(omaviesti, soittoon);
        vastustajanMaasto[kursoriY][kursoriX] += 30;
        vastustajanMaastoaNakyvissa[kursoriY][kursoriX] = true;
        return true;
    }
    
    /**
     * Pelataan vastustajan (eli tietokoneen) vuoro.
     */
    public void pelaaVastustajanVuoro() {
        int ampuu = tekoaly.siirto(pelaajanMaasto);
        int soittoon = tekoaly.getVanhaArvo();
        vastustajanviesti = tekoaly.getViesti();
        if (pelaajanMaasto[ampuu / 20][ampuu % 20] == 33) { // oli talo
            vastustajanviesti = 8;
            this.setVastustajaOhittaaVuoroja(3);
        }
        soittaja.soitaVastustaja(vastustajanviesti, soittoon);
        pelaajanPiirrettava[ampuu / 20][ampuu % 20] += 100;
    }
    
    private void josLaivaTuhottuKokonaanPiirraSeLaivaksi(int y, int x) {
        int mikaLaiva = palautaLaivaJohonOsuttiin(y, x); // palauttaa 200 jos ei osuttu, arvo 200 DEBUGGAUSTA varten, voidaan poistaa myöhemmin
        if (mikaLaiva < 200) {
            if (testaaOnkoLaivaKokonaanTuhottu(mikaLaiva, vastustajanLaivojenKoordinaatit, vastustajanPiirrettava)) {
                piirraTuhottuLaivaLaivaksi(mikaLaiva);
            } else {
                omaviesti = 1;
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
    
    private boolean testaaOnkoLaivaKokonaanTuhottu(int mikaLaiva, Map<Integer, List<Integer>> koordi, int[][] taulu) {
        boolean kaikkiinPaloihinOsuttu = true;
        for (int a = 0; a < (koordi.get(mikaLaiva).size() / 2); a++) {
            if (taulu[koordi.get(mikaLaiva).get(a * 2)][koordi.get(mikaLaiva).get(a * 2 + 1)] < 20) {
                kaikkiinPaloihinOsuttu = false;
            }
        }
        return kaikkiinPaloihinOsuttu;
    }
    
    private void piirraTuhottuLaivaLaivaksi(int mikaLaiva) {
        for (int a = 0; a < (vastustajanLaivojenKoordinaatit.get(mikaLaiva).size() / 2); a++) {
            vastustajanPiirrettava[vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2)][vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2 + 1)] += 90;
        }
        if (vastustajanLaivojenKoordinaatit.get(mikaLaiva).size() == 2) {
            omaviesti = 5;
        } else {
            omaviesti = 3;
        }
    }

    public Map<Integer, Integer> vastustajaaTuhottu() {
        Map<Integer, Integer> palautus = new HashMap<>();
        for (int hoo: vastustajanLaivojenKoordinaatit.keySet()) {
            if (testaaOnkoLaivaKokonaanTuhottu(hoo, vastustajanLaivojenKoordinaatit, vastustajanPiirrettava)) {
                palautus.put(hoo, 1);
            } else {
                palautus.put(hoo, 2);
            }
        }
        return palautus;
    }
    
    public Map<Integer, Integer> omaaTuhottu() {
        Map<Integer, Integer> palautus = new HashMap<>();
        for (int hoo: omienLaivojenKoordinaatit.keySet()) {
            if (testaaOnkoLaivaKokonaanTuhottu(hoo, omienLaivojenKoordinaatit, pelaajanPiirrettava)) {
                palautus.put(hoo, 1);
            } else {
                palautus.put(hoo, 2);
            }
        }
        return palautus;
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
    
    public void setAllVisible() {
        for (int a = 0; a < 400; a++) {
            vastustajanMaastoaNakyvissa[a / 20][a % 20] = true;
        }        
    }
    
}