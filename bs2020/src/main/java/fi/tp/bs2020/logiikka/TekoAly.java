/**
 * Kalibrointiarvo todennäköisyyden laskemiseen.
 */
package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class TekoAly {
    
    private Random arpoja;
    private boolean kohdeKesken; // DEBUG
    private Map<Integer, List<Integer>> laivanKoordinaatit;
    private Map<Integer, Integer> laivaTuhottu;
    private Aanet aanet;
    
    public TekoAly(Random arpoja) {
        this.arpoja = arpoja;
    }

    public void setAanet(Aanet aanet) {
        this.aanet = aanet;
    }

    public void setLaivanKoordinaatit(Map<Integer, List<Integer>> laivanKoordinaatit) {
        this.laivanKoordinaatit = laivanKoordinaatit;
        laivaTuhottu = new HashMap<>();
        for (Integer laiva: laivanKoordinaatit.keySet()) {
            laivaTuhottu.put(laiva, 0);
        }
    }

    private boolean onkoKesken() {
        boolean onkoKesken = false;
        for (Integer laiva: laivanKoordinaatit.keySet()) {
            if (laivaTuhottu.get(laiva) == 1) {
                onkoKesken = true;
            }
        }
        return onkoKesken;
    }
    
    private int mikaKesken() {
        int keskenerainen = 0;
        for (Integer laiva: laivanKoordinaatit.keySet()) {
            if (laivaTuhottu.get(laiva) == 1) {
                keskenerainen = laiva;
            }
        }
        return keskenerainen;
    }
    
    private int mihinOsuttu(int laiva, int[][] maasto) {
        int palautus = 0;
        for (int a = 0; a < (laivanKoordinaatit.get(laiva).size() / 2); a++) {
            if (maasto[laivanKoordinaatit.get(laiva).get(a * 2)][laivanKoordinaatit.get(laiva).get(a * 2 + 1)] >= 20) {
                return ((laivanKoordinaatit.get(laiva).get(a * 2) * 20) + laivanKoordinaatit.get(laiva).get(a * 2 + 1));
            }
        }
        return palautus;
    }
    
    private void paivitaOsuttu(int dy, int dx, int[][] maasto) {
        if (maasto[dy][dx] >= 2) {
            for (int laiva: laivanKoordinaatit.keySet()) {
                for (int a = 0; a < (laivanKoordinaatit.get(laiva).size() / 2); a++) {
                    if (laivanKoordinaatit.get(laiva).get(a * 2) == dy && laivanKoordinaatit.get(laiva).get(a * 2 + 1) == dx) {
                        laivaTuhottu.replace(laiva, 1);
                    }
                }
            }
        }
    }
    
    private void paivitaTuhottu(int laiva, int[][] maasto) {
        boolean kaikki = true;
        for (int a = 0; a < (laivanKoordinaatit.get(laiva).size() / 2); a++) {
            if (maasto[laivanKoordinaatit.get(laiva).get(a * 2)][laivanKoordinaatit.get(laiva).get(a * 2 + 1)] < 20) {
                kaikki = false;
            }
        }
        if (kaikki) {
            laivaTuhottu.replace(laiva, 2);
            //aanet.play("erased.wav"); //////// äänitesti
            //aanet.run();
        }
    }
    
    private int pommitetaanLaivaa(int[][] maasto) {
        int mikaLaivaKesken = mikaKesken();
        int yksiKohtaMihinOsuttu = mihinOsuttu(mikaLaivaKesken, maasto);

        TekoAlyLaivalle tal = new TekoAlyLaivalle(maasto, (yksiKohtaMihinOsuttu / 20), (yksiKohtaMihinOsuttu % 20), arpoja);
        int pal = tal.ammutaanLaivaa();

        maasto[pal / 20][pal % 20] += 30; // huono tapa
        this.paivitaTuhottu(mikaLaivaKesken, maasto);
        return pal;
    }
    
    private int pommitetaanMuualle(int[][] maasto) {
        TekoAlyMuualle tam = new TekoAlyMuualle(maasto, arpoja);
        int pal = tam.ammutaanMuualle();
        
        paivitaOsuttu(pal / 20, pal % 20, maasto);
        maasto[pal / 20][pal % 20] += 30; // huono tapa                
        return pal;
    }
    
    public int siirto(int[][] maasto) {
        int paluuarvo = 0;

        aanet.setSoita(1);
        //new Thread(aanet).start(); /// TEST

        boolean laivaKesken = onkoKesken();
        if (laivaKesken) {
            paluuarvo = pommitetaanLaivaa(maasto);
        } else {
            paluuarvo = pommitetaanMuualle(maasto);
        }
        
        return paluuarvo;
    }
    
}
    