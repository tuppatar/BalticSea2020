package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import java.awt.Component;
import java.util.Random;

/**
 *
 * @author Tuomas Pätäri
 */
public class PeliRunko {
    
    private int peliTilanne = 0, omaviesti = 0, vastustajanviesti = 0; // 0 = menu, 1 = peli, 2 = peliloppu, 3 = peliesc
    private Peli peli;
    private Aanet aanet;
    private Random arpoja;
    private PeliMuuttujat moodi;
    private Menu menu;
    private boolean musaOn, aanetOn, taukoTila;
    
    public PeliRunko(Random arpoja) {
        peliTilanne = 0;
        this.arpoja = arpoja;
        this.aanet = new Aanet();
        this.moodi = new PeliMuuttujat();
        this.menu = this.uusiMenu();
        this.musaOn = false;
        this.aanetOn = false;
        this.taukoTila = false;
    }
    
    /**
     * EI KÄYTÖSSÄ.
     * @param msecs tauko millisekunteina.
     */
    public void tauko(int msecs) {
        this.taukoTila = true;
        try {
            Thread.sleep(msecs);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.taukoTila = false;
    }

    public boolean isTaukoTila() {
        return taukoTila;
    }
    
    public Peli getPeli() {
        return peli;
    }

    public int getPeliTilanne() {
        return peliTilanne;
    }

    public void setPeliTilanne(int peliTilanne) {
        this.peliTilanne = peliTilanne;
    }
    
    public void aloitus() {
        
    }

    public PeliMuuttujat getMoodi() {
        return moodi;
    }
    
    public Menu uusiMenu() {
        Menu menuZ = new Menu();
        menuZ.setMoodi(moodi);
        return menuZ;
    }

    public Menu getMenu() {
        return menu;
    }
    
    public Peli uusiPeli() {
        moodi.asetaMuuttujatPeliaVarten();
        peli = new Peli(arpoja, aanet, moodi);
        return peli;
    }

    public int getomaViesti() {
        return omaviesti;
    }

    public int getVastustajanviesti() {
        return vastustajanviesti;
    }

    public void peliSiirto(Component component) {
        //omaviesti = 0;
        //vastustajanviesti = 0;
        boolean vastustajanVuoroPelataan = true;
        if (peli.getPelaajaOhittaaVuoroja() == 0) {
            if (!peli.pelaaOmaVuoro()) {
                omaviesti = 10;
                vastustajanVuoroPelataan = false;
            } else {
                omaviesti = peli.getOmaviesti();
            }
        } else {
            peli.setPelaajaOhittaaVuoroja(peli.getPelaajaOhittaaVuoroja() - 1);
        }
        if (vastustajanVuoroPelataan && peli.getVastustajaOhittaaVuoroja() == 0) {
            peli.pelaaVastustajanVuoro();
            vastustajanviesti = peli.getVastustajanviesti();
        } else if (vastustajanVuoroPelataan) {
            peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
        }
//        if (peli.pelaaOmaVuoro()) {
//            component.repaint();
//            if (peli.getVastustajaOhittaaVuoroja() == 0) {
//                peli.pelaaVastustajanVuoro();
//            } else {
//                peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
//            }
//        } else {
//            omaviesti = 10;
//        }
//        while (peli.getPelaajaOhittaaVuoroja() > 0) {
//            if (peli.getVastustajaOhittaaVuoroja() == 0) {
//                peli.pelaaVastustajanVuoro();
////                this.tauko(500);
////                component.repaint();
//            } else {
//                peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
//            }
//            peli.setPelaajaOhittaaVuoroja(peli.getPelaajaOhittaaVuoroja() - 1);
//        }
        component.repaint();
        int kumpi = this.tarkistaVoitto(); // 1 = vastustaja voitti; 2 = pelaaja voitti; 3 = tasapeli
        if (kumpi > 0) {
            peli.setAllVisible();
            setPeliTilanne(20 + kumpi);
        }
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
    
    /**
     * Tarkistetaan kierroksen JÄLKEEN, onko jompi kumpi pelaaja voittanut.
     * @return 0, jos tasapeli, 1, jos vastustaja voitti, 2, jos pelaaja voitti, ja 0, jos ei voittoa.
     */
    public int tarkistaVoitto() {
        int f = laskeEhjatLaivapalat(peli.getPelaajanMaasto());
        int g = laskeEhjatLaivapalat(peli.getVastustajanMaasto());
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
