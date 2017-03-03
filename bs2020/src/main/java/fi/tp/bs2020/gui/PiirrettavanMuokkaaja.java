package fi.tp.bs2020.gui;

/**
 * Luokka muuttaa piirrettävän taulukon "lennossa" sellaiseen ulkoasuun, jossa viereiset näkymättömät
 * ruudut eivät paljasta, mitä viereisessä ruudussa on.
 */
public class PiirrettavanMuokkaaja {

    private int valitseOikeaMaapala(int y, int x, int[][] alkuTaulukko, boolean[][] visible, int satunnaisuus, int tilanne) {
        if (tilanne >= 20) {
            return alkuTaulukko[y][x];
        }
        boolean vasemmallaVetta = false;
        boolean alhaallaVetta = false;
        boolean ylhaallaVetta = false;
        boolean oikeallaVetta = false;
        boolean alkuperainen = true;
        if (y > 0) {
            if (alkuTaulukko[y - 1][x] < 150 && alkuTaulukko[y - 1][x] != 109 && visible[y - 1][x]) {
                ylhaallaVetta = true;
            }
            if (!visible[y - 1][x]) {
                alkuperainen = false;
            }
        }
        if (y < 19) {
            if (alkuTaulukko[y + 1][x] < 150 && alkuTaulukko[y + 1][x] != 109 && visible[y + 1][x]) {
                alhaallaVetta = true;
            }
            if (!visible[y + 1][x]) {
                alkuperainen = false;
            }
        }
        if (x > 0) {
            if (alkuTaulukko[y][x - 1] < 150 && alkuTaulukko[y][x - 1] != 109 && visible[y][x - 1]) {
                vasemmallaVetta = true;
            }
            if (!visible[y][x - 1]) {
                alkuperainen = false;
            }
        }
        if (x < 19) {
            if (alkuTaulukko[y][x + 1] < 150 && alkuTaulukko[y][x + 1] != 109 && visible[y][x + 1]) {
                oikeallaVetta = true;
            }
            if (!visible[y][x + 1]) {
                alkuperainen = false;
            }
        }
        if (ylhaallaVetta && alhaallaVetta && vasemmallaVetta && oikeallaVetta) {
            return 190 + satunnaisuus;
        } else if (ylhaallaVetta && alhaallaVetta && vasemmallaVetta) { // maa oikealla
            return 182 + satunnaisuus;
        } else if (ylhaallaVetta && alhaallaVetta && oikeallaVetta) { // maa vasemmalla
            return 186 + satunnaisuus;
        } else if (ylhaallaVetta && oikeallaVetta && vasemmallaVetta) { // maa alhaalla
            return 184 + satunnaisuus;
        } else if (oikeallaVetta && alhaallaVetta && vasemmallaVetta) { // maa ylhaalla
            return 180 + satunnaisuus;
        } else if (oikeallaVetta && vasemmallaVetta) {
            return 192 + satunnaisuus;
        } else if (oikeallaVetta && alhaallaVetta) {
            return 172 + satunnaisuus;
        } else if (oikeallaVetta && ylhaallaVetta) {
            return 170 + satunnaisuus;
        } else if (vasemmallaVetta && ylhaallaVetta) {
            return 176 + satunnaisuus;
        } else if (vasemmallaVetta && alhaallaVetta) {
            return 174 + satunnaisuus;
        } else if (alhaallaVetta && ylhaallaVetta) {
            return 194 + satunnaisuus;
        } else if (alhaallaVetta) {
            return 164 + satunnaisuus;
        } else if (ylhaallaVetta) {
            return 160 + satunnaisuus;
        } else if (vasemmallaVetta) {
            return 166 + satunnaisuus;
        } else if (oikeallaVetta) {
            return 162 + satunnaisuus;
        }
        if (alkuperainen) {
            return alkuTaulukko[y][x];
        } else {
            return 150;
        }
    }
    
    /**
     * Muokkaa piirtotaulukosta näytettävän version sen mukaan, paljon maastoa on näkyvillä. Tällä estetään se, että
     * pelaaja voisi näkyvästä maapalasta päätellä, onko viereisessä ruudussa vettä vai maata.
     * @param piirrettava   maaston piirtotaulukko.
     * @param visible       maaston näkyvyystaulukko.
     * @param satunnaisuus  maaston satunnaisuustaulukko.
     * @param tilanne       tilanne on erilainen, kun pelin päättyessä kaikki muuttuu näkyväksi. silloin taulukko palautetaan sellaisenaan.
     * @return              näytettävän piirtotaulukon.
     */
    public int[][] piirrettava(int[][] piirrettava, boolean[][] visible, int[][] satunnaisuus, int tilanne) {
        int[][] palautus = new int[20][20];
        for (int loop = 0; loop < 400; loop++) {
            if (visible[loop / 20][loop % 20]) {
                if (piirrettava[loop / 20][loop % 20] >= 150) {
                    palautus[loop / 20][loop % 20] = this.valitseOikeaMaapala(loop / 20, loop % 20, piirrettava, visible, satunnaisuus[loop / 20][loop % 20], tilanne);
                } else {
                    palautus[loop / 20][loop % 20] = piirrettava[loop / 20][loop % 20];
                }
            } else {
                palautus[loop / 20][loop % 20] = 8;
            }
        }
        return palautus;
    }
}
