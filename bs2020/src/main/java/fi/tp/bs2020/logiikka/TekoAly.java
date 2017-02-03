package fi.tp.bs2020.logiikka;

import java.util.Random;


public class TekoAly {
    
    private Random arpoja;
    private boolean kohdeKesken; // DEBUG
    
    public TekoAly(Random arpoja) {
        this.arpoja = arpoja;
    }
    
    public int siirto(int[][] vastustajanMaasto) {

        boolean osumaHyvaksytty = false;
        // katsotaanko onko keskeneräistä laivaa
        
        // ei ole:
        int dx = arpoja.nextInt(20);
        int dy = arpoja.nextInt(20);
        
        int kokeillaan = 100;
        while (!osumaHyvaksytty || kokeillaan > 0) {
            dx = arpoja.nextInt(20);
            dy = arpoja.nextInt(20);
            if (!katsoOnkoSaartaVieressa(vastustajanMaasto)) {
                osumaHyvaksytty = true;
            }
            kokeillaan--;
        }
        return dy * 200 + dx;
    }
    
    private boolean katsoOnkoSaartaVieressa(int[][] vastustajanMaasto) {
        
        return false;
    }
}
