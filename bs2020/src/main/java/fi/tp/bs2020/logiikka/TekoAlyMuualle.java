package fi.tp.bs2020.logiikka;

import java.util.Random;


public class TekoAlyMuualle {
    
    private int[][] maasto;
    private Random arpoja;
    
    public TekoAlyMuualle(int[][] maasto, Random arpoja) {
        this.maasto = maasto;
        this.arpoja = arpoja;
    }
    
    private int laskeViereiset(int pisteita, int dy, int dx, int[][] maasto) {
        int yhteensa = 0;
        if (dy > 0) {
            if (maasto[dy - 1][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dy < 19) {
            if (maasto[dy + 1][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx > 0) {
            if (maasto[dy][dx - 1] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx < 19) {
            if (maasto[dy][dx + 1] == 31) {
                yhteensa += pisteita;
            }
        }
        return yhteensa;
    }
    
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
    
    private int laskeViereistenViereiset(int pisteita, int dy, int dx, int[][] maasto) {
        int yhteensa = 0;
        if (dy > 0) {
            if (maasto[dy - 1][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dy < 19) {
            if (maasto[dy + 1][dx] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx > 0) {
            if (maasto[dy][dx - 1] == 31) {
                yhteensa += pisteita;
            }
        }
        if (dx < 19) {
            if (maasto[dy][dx + 1] == 31) {
                yhteensa += pisteita;
            }
        }
        return yhteensa;
    }
 
    private boolean katsoOnkoSaartaVieressa(int dy, int dx, int[][] maasto) {
        int pisteita = laskeViereiset(10, dy, dx, maasto); //max 40
        pisteita += laskeKulmittaiset(7, dy, dx, maasto); // max 28
        pisteita += laskeViereistenViereiset(4, dy, dx, maasto); // max 16
        int dynaaminenTekoAly = arpoja.nextInt(15) + 5;
        if (pisteita >= dynaaminenTekoAly) {
            return true;
        }
        return false;
    }

    public int ammutaanMuualle() {
        boolean osumaHyvaksytty = false;
        int dy = 0;
        int dx = 0;
        int kokeillaan = 100;
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
