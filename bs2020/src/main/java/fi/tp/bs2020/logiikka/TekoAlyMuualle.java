package fi.tp.bs2020.logiikka;

import java.util.Random;

/**
 * Tekoäly, jota käytetään kun laivanpommitus ei ole kesken. Välttelee saaria.
 */
public class TekoAlyMuualle {
    
    private int[][] maasto;
    private Random arpoja;
    
    /**
     * Konstruktori.
     * @param maasto    Maasto, johon ammutaan.
     * @param arpoja    Random-olio.
     */
    public TekoAlyMuualle(int[][] maasto, Random arpoja) {
        this.maasto = maasto;
        this.arpoja = arpoja;
    }
    /**
     * Laskee viereisien palojen "saaristoisuuspisteet", jos ovat jo näkyvillä.
     * Muutettu nyt myös lisäämällä mitaMitataan, jotta tekoäly ei ammu jo ammuttujen laivojen viereen.
     * @param pisteita pisteitä.
     * @param dy y.
     * @param dx x.
     * @param maasto maasto.
     * @param mitaMitataan 31 = ammuttua maata, 32 = ammuttua laivaa, 33 = ammuttua taloa.
     * @return pisteet.
     */
    private int laskeViereiset(int pisteita, int dy, int dx, int[][] maasto, int mitaMitataan) {
        int yhteensa = 0;
        if (dy > 0) {
            if (maasto[dy - 1][dx] == mitaMitataan) {
                yhteensa += pisteita;
            }
        }
        if (dy < 19) {
            if (maasto[dy + 1][dx] == mitaMitataan) {
                yhteensa += pisteita;
            }
        }
        if (dx > 0) {
            if (maasto[dy][dx - 1] == mitaMitataan) {
                yhteensa += pisteita;
            }
        }
        if (dx < 19) {
            if (maasto[dy][dx + 1] == mitaMitataan) {
                yhteensa += pisteita;
            }
        }
        return yhteensa;
    }
    /**
     * Laskee kulmittaisten palojen "saaristoisuuspisteet", jos ovat jo näkyvillä.
     * @param pisteita pisteitä.
     * @param dy y.
     * @param dx x.
     * @param maasto maasto.
     * @return pisteet.
     */
    private int laskeKulmittaiset(int pisteita, int dy, int dx, int[][] maasto) {
        int yhteensa = 0;
        if (dy > 0 && dx > 0) {
            if (maasto[dy - 1][dx - 1] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dy < 19 && dx < 19) {
            if (maasto[dy + 1][dx + 1] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx > 0 && dy < 19) {
            if (maasto[dy + 1][dx - 1] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx < 19 && dy > 0) {
            if (maasto[dy - 1][dx + 1] == 31) {
                yhteensa += pisteita;
            }
        }
        return yhteensa;
    }
    /**
     * Laskee viereisiä viereisten palojen "saaristoisuuspisteet", jos ovat jo näkyvillä.
     * @param pisteita pisteitä.
     * @param dy y.
     * @param dx x.
     * @param maasto maasto.
     * @return pisteet.
     */
    private int laskeViereistenViereiset(int pisteita, int dy, int dx, int[][] maasto) {
        int yhteensa = 0;
        if (dy > 1) {
            if (maasto[dy - 2][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dy < 18) {
            if (maasto[dy + 2][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx > 1) {
            if (maasto[dy][dx - 2] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx < 18) {
            if (maasto[dy][dx + 2] == 31) {
                yhteensa += pisteita;
            }
        }
        return yhteensa;
    }
/**
 * "dynaaminen" tekoäly, jossa todennäköisyys vaihtelee, sille ammutaanko miten lähelle näkyviä saaripaloja.
 * @param dy y.
 * @param dx x.
 * @param maasto maasto.
 * @return onko saarta ammuttavan kohteen lähellä tekoälyn mielestä.
 */ 
    private boolean katsoOnkoSaartaVieressa(int dy, int dx, int[][] maasto) {
        int pisteita = laskeViereiset(10, dy, dx, maasto, 31); //max 40
        pisteita += laskeViereiset(20, dy, dx, maasto, 33); //
        pisteita += laskeViereiset(20, dy, dx, maasto, 32); //
        pisteita += laskeKulmittaiset(7, dy, dx, maasto); // max 28
        pisteita += laskeViereistenViereiset(4, dy, dx, maasto); // max 16
        int dynaaminenTekoAly = arpoja.nextInt(15) + 5;
        if (pisteita >= dynaaminenTekoAly) {
            return true;
        }
        return false;
    }
/**
 * Kokeillaan 100 kertaa paikkaa, jossa näkyvää saaristoa ei ole "liian lähellä".
 * @return paluuarvo ammuttavalle kohteelle.
 */
    public int ammutaanMuualle() {
        boolean osumaHyvaksytty = false;
        int dy = 0;
        int dx = 0;
        int kokeillaan = 3000;
        while (!osumaHyvaksytty) {
            dx = arpoja.nextInt(20);
            dy = arpoja.nextInt(20);
            if (!katsoOnkoSaartaVieressa(dy, dx, maasto) || kokeillaan <= 0) {
                osumaHyvaksytty = true;
            }
            kokeillaan--;

            if (maasto[dy][dx] >= 30) {            
                osumaHyvaksytty = false;
            }
        }
        return dy * 20 + dx;
    }
}
