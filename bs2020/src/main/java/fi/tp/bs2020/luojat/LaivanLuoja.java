package fi.tp.bs2020.luojat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Luokka tekee laivat maastoon tiettyjen sääntöjen mukaisesti. Kaikkien laivapalojen tulee osua
 * merelle, ja eri laivat eivät voi sijaita vierekkäin, peräkkäin tai pää sivua vasten.
 */
public class LaivanLuoja {
    
    private Random arpoja;
    private PiirtotaulukonLuoja ptl;
    private int[][] maasto;
    
    /**
     * Konstruktori.
     * @param arpoja    random-olio.
     * @param maasto    Maasto, johon laiva luodaan.
     * @param ptl       Piirtotaulukonluoja, joka lisää laivan piirtotaulukkoon.
     */
    public LaivanLuoja(Random arpoja, int[][] maasto, PiirtotaulukonLuoja ptl) {
        this.arpoja = arpoja;
        this.maasto = maasto;
        this.ptl = ptl;
    }
    
    private boolean teeLaivaPystyyn(Map laivat, int pituus, int avain) {
        int xx = arpoja.nextInt(20);
        int yy = arpoja.nextInt(21 - pituus); // korjattu 20 -> 21
        boolean alatee = false;
        for (int cc = 0; cc < pituus; cc++) {
            if (maasto[yy + cc][xx] > 0) {
                alatee = true;
            }
            if (xx > 0) {
                if (maasto[yy + cc][xx - 1] == 2) {
                    alatee = true;
                }
            }
            if (xx < 19) {
                if (maasto[yy + cc][xx + 1] == 2) {
                    alatee = true;
                }
            }
        }
        if (yy > 0) {
            if (maasto[yy - 1][xx] == 2) {
                alatee = true;
            }
        }
        if ((yy + pituus) < 20) { // korjattu 19 -> 20
            if (maasto[yy + pituus][xx] == 2) {
                alatee = true;
            }
        }
        if (!alatee) {
            List koordinaatit = new ArrayList<>();
            for (int cc = 0; cc < pituus; cc++) {
                maasto[yy + cc][xx] = 2;
                koordinaatit.add(yy + cc);
                koordinaatit.add(xx);
                ptl.lisaaLaivaPystyyn(cc, pituus, yy, xx);
            }
            laivat.put(avain, koordinaatit);
            return true;
        }
        return false;
    }

    private boolean teeLaivaVaakaan(Map laivat, int pituus, int avain) {
        int xx = arpoja.nextInt(21 - pituus); // korjattu 20 -> 21
        int yy = arpoja.nextInt(20);
        boolean alatee = false;
        for (int cc = 0; cc < pituus; cc++) {
            if (maasto[yy][xx + cc] > 0) {
                alatee = true;
            }
            if (yy > 0) {
                if (maasto[yy - 1][xx + cc] == 2) {
                    alatee = true;
                }
            }
            if (yy < 19) {
                if (maasto[yy + 1][xx + cc] == 2) {
                    alatee = true;
                }
            }
        }
        if (xx > 0) {
            if (maasto[yy][xx - 1] == 2) {
                alatee = true;
            }
        }
        if ((xx + pituus) < 20) { // korjattu 19 -> 20
            if (maasto[yy][xx + pituus] == 2) {
                alatee = true;
            }
        }
        if (!alatee) {
            List koordinaatit = new ArrayList<Integer>();
            for (int cc = 0; cc < pituus; cc++) {
                maasto[yy][xx + cc] = 2;
                koordinaatit.add(yy);
                koordinaatit.add(xx + cc);
                ptl.lisaaLaivaVaakaan(cc, pituus, yy, xx);
            }
            laivat.put(avain, koordinaatit);
            return true;
        }
        return false;
    }
/**
 * Tekee laivan maastoon.
 * @param laivat    laivojen MAP
 * @param pituus    laivan pituus
 * @param avain     laivan MAP-avain 
 * @return boolean  onnistuuko laivan luonti kyseiseen maastoon, kokeillaan 10000 kertaa.
 */    
    public boolean teeLaiva(Map laivat, int pituus, int avain) {
        int kokeiluKertoja = 10000;
        while (kokeiluKertoja > 0) {
            int suunta = arpoja.nextInt(2);
            if (suunta == 0) {
                if (teeLaivaPystyyn(laivat, pituus, avain)) {
                    return true;
                }
            } else {
                if (teeLaivaVaakaan(laivat, pituus, avain)) {
                    return true;
                }
            }
            kokeiluKertoja--;
        }
        return false;
    }
    
}
