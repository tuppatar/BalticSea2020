package fi.tp.bs2020.logiikka;

import java.util.Random;


/*
 * Tämä luokka lisää luotavat elementit piirtotaulukkoon. Piirtotaulukko on erotettu maastotaulukosta,
 * koska eri toiminnallisuuksia on vain muutama, mutta samalla toiminnallisuudella voi olla lukemattomia
 * erilaisia ulkoasuja.
 */
public class PiirtotaulukonLuoja {
    
    private Random arpoja;
    private int[][] maasto, piirrettava;

    public PiirtotaulukonLuoja(Random arpoja, int[][] maasto, int[][] piirrettava) {
        this.arpoja = arpoja;
        this.maasto = maasto;
        this.piirrettava = piirrettava;
    }
/**
 * Valitsee maapalan viereisten palojen mukaisesti ja arpoo lisäksi eri vaihtoehdoista.
 * Metodi on aika pitkä mutta en näe näin selkeän metodin pilkkomista järkeväksi?
 * @param y     y-koordinaatti.
 * @param x     x-koordinaatti.
 * @return      palan MAP-indeksi.
 */
    private int valitseOikeaMaapala(int y, int x) {
        boolean vasemmallaVetta = false;
        boolean alhaallaVetta = false;
        boolean ylhaallaVetta = false;
        boolean oikeallaVetta = false;
        
        if (y > 0) {
            if (maasto[y - 1][x] == 0) {
                ylhaallaVetta = true;
            }
        }
        if (y < 19) {
            if (maasto[y + 1][x] == 0) {
                alhaallaVetta = true;
            }
        }
        if (x > 0) {
            if (maasto[y][x - 1] == 0) {
                vasemmallaVetta = true;
            }
        }
        if (x < 19) {
            if (maasto[y][x + 1] == 0) {
                oikeallaVetta = true;
            }
        }
        if (ylhaallaVetta && alhaallaVetta && vasemmallaVetta && oikeallaVetta) {
            return 90;
        } else if (ylhaallaVetta && alhaallaVetta && vasemmallaVetta) { // maa oikealla
            return 82;
        } else if (ylhaallaVetta && alhaallaVetta && oikeallaVetta) { // maa vasemmalla
            return 86;
        } else if (ylhaallaVetta && oikeallaVetta && vasemmallaVetta) { // maa alhaalla
            return 84;
        } else if (oikeallaVetta && alhaallaVetta && vasemmallaVetta) { // maa ylhaalla
            return 80;
        } else if (oikeallaVetta && vasemmallaVetta) {
            return 92;
        } else if (oikeallaVetta && alhaallaVetta) {
            return 72;
        } else if (oikeallaVetta && ylhaallaVetta) {
            return 70;
        } else if (vasemmallaVetta && ylhaallaVetta) {
            return 76;
        } else if (vasemmallaVetta && alhaallaVetta) {
            return 74;
        } else if (alhaallaVetta && ylhaallaVetta) {
            return 94;
        } else if (alhaallaVetta) {
            return 64;
        } else if (ylhaallaVetta) {
            return 60;
        } else if (vasemmallaVetta) {
            return 66;
        } else if (oikeallaVetta) {
            return 62;
        }
        return 50 + arpoja.nextInt(4);
    }
/**
 * Lisää piirtotaulukkoon veden ja maan.
 */
    public void lisaaVesiJaMaa() {
        for (int loop = 0; loop < 400; loop++) {
            if (maasto[loop / 20][loop % 20] == 0) {
                piirrettava[loop / 20][loop % 20] = 0;
            } else {
                piirrettava[loop / 20][loop % 20] = valitseOikeaMaapala(loop / 20, loop % 20);
            }        
        }
    }
/**
 * Lisää piirtotaulukkoon talon.
 */
    public void lisaaTalo(int yy, int xx) {
        piirrettava[yy][xx] = 9;
    }
 /**
  * Lisää vaakasuuntaisen laivan tai suunnattoman sukellusveneen.
  * @param cc       rakennettava laivan kohta.
  * @param pituus   kokonaispituus.
  * @param yy       y-koordinaatti.
  * @param xx       x-koordinaatti.
  */   
    public void lisaaLaivaVaakaan(int cc, int pituus, int yy, int xx) {
        if (pituus == 1) {
            piirrettava[yy][xx] = 16; //sukellusvene
        } else {
            if (cc == 0) {
                piirrettava[yy][xx + cc] = 13; // VAAKAPÄÄTYPALA 13
            } else if (cc == (pituus - 1)) {
                piirrettava[yy][xx + cc] = 15; // VAAKAPÄÄTYPALA 15
            } else {
                piirrettava[yy][xx + cc] = 14; // VAAKAKESKIPALA 14
            }
        }
    }
 /**
  * Lisää pystysuuntaisen laivan tai suunnattoman sukellusveneen.
  * @param cc       rakennettava laivan kohta.
  * @param pituus   kokonaispituus.
  * @param yy       y-koordinaatti.
  * @param xx       x-koordinaatti.
  */   
    public void lisaaLaivaPystyyn(int cc, int pituus, int yy, int xx) {
        if (pituus == 1) {
            piirrettava[yy][xx] = 16; //sukellusvene
        } else {
            if (cc == 0) {
                piirrettava[yy + cc][xx] = 10; // PYSTYPÄÄTYPALA 10
            } else if (cc == (pituus - 1)) {
                piirrettava[yy + cc][xx] = 12; // PYSTYPÄÄTYPALA 12
            } else {
                piirrettava[yy + cc][xx] = 11; // PYSTYKESKIPALA 11
            }
        }
    }
    
}
