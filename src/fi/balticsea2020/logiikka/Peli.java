/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.balticsea2020.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author jee
 */
public class Peli {
    
    private Random arpoja;
    private int[][] vastustajanMaasto, vastustajanPiirrettava, pelaajanMaasto, pelaajanPiirrettava;
    private boolean[][] vastustajanMaastoaNakyvissa;
    private MaastonLuoja ml;
    private int kursoriX,kursoriY;
    private Map<Integer, List<Integer>> vastustajanLaivojenKoordinaatit;

    public Peli(Random arpoja) {
        vastustajanMaasto = new int[20][20];
        vastustajanPiirrettava = new int[20][20];
        pelaajanMaasto = new int[20][20];
        pelaajanPiirrettava = new int[20][20];
        vastustajanMaastoaNakyvissa = new boolean[20][20];
        
        this.arpoja = arpoja;
        ml = new MaastonLuoja(arpoja);
        
        vastustajanMaasto = ml.luoVastustajanMaasto();
        vastustajanPiirrettava = ml.getPiirrettava();

        vastustajanLaivojenKoordinaatit = ml.getLaivat(); //vastustajan laivat
        System.out.println(vastustajanLaivojenKoordinaatit); // DEBUG

        ml = new MaastonLuoja(arpoja);
        // pelaajanMaasto = ml.luoPelaajanMaasto(); // Laivojen omaa asettelua ei ole vielä toteutettu.
        pelaajanMaasto = ml.luoVastustajanMaasto(); // Laivojen omaa asettelua ei ole vielä toteutettu.
        pelaajanPiirrettava = ml.getPiirrettava();
        for (int a = 0; a < 400; a++) {
            vastustajanMaastoaNakyvissa[a / 20][a % 20] = false;
        }
        kursoriX = 0;
        kursoriY = 0;
        
        this.debugPiirra(pelaajanMaasto); // DEBUG
        System.out.println(); // DEBUG
        this.debugPiirra(vastustajanMaasto); // DEBUG
    }

    public void tauko(int msecs) {
        try {
            Thread.sleep(msecs);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }        
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
            vastustajanPiirrettava[kursoriY][kursoriX] += 30;
        }
        vastustajanMaasto[kursoriY][kursoriX] += 30;
        vastustajanMaastoaNakyvissa[kursoriY][kursoriX] = true;
        return true;
    }
    
    public void pelaaVastustajanVuoro() {
        boolean jatketaan = true;
        int dx = arpoja.nextInt(20);
        int dy = arpoja.nextInt(20);
        
        while (jatketaan) {
            dx = arpoja.nextInt(20);
            dy = arpoja.nextInt(20);
            if (pelaajanMaasto[dy][dx] < 30) {
                jatketaan = false;
            }
        }
        
        pelaajanMaasto[dy][dx] += 30;
        pelaajanPiirrettava[dy][dx] += 30;
    }
    
    private void josLaivaTuhottuKokonaanPiirraSeLaivaksi(int y, int x) {
        int mikaLaiva = palautaLaivaJohonOsuttiin(y, x); // palauttaa 200 jos ei osuttu, arvo 200 DEBUGGAUSTA varten, voidaan poistaa myöhemmin
        System.out.println(mikaLaiva); // DEBUG

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
            vastustajanPiirrettava[vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2)][vastustajanLaivojenKoordinaatit.get(mikaLaiva).get(a * 2 + 1)] += 20;
        }
    }
    
    private void debugPiirra(int[][] debuttava) {
        ml.debugDraw(debuttava);
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