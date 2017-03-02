package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import fi.tp.bs2020.gui.Soittaja;
import java.awt.Component;
import java.util.Random;

/**
 * Pelin päärunko.
 * 
 */
public class PeliRunko {
    
    private int peliTilanne = 0, omaviesti = 0, vastustajanviesti = 0, soitettava = 0;
    private Peli peli;
    private Aanet aanet;
    private Random arpoja;
    private PeliMuuttujat moodi;
    private Menu menu;
    private boolean musaOn, aanetOn;
    private Soittaja soittaja;
    
    public PeliRunko(Random arpoja) {
        peliTilanne = 0;
        this.arpoja = arpoja;
        this.moodi = new PeliMuuttujat();
        this.soittaja = new Soittaja(arpoja, moodi);
        this.menu = new Menu(moodi, soittaja);
        this.musaOn = false;
        this.aanetOn = false;
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
    
    public PeliMuuttujat getMoodi() {
        return moodi;
    }
    
    public Menu getMenu() {
        return menu;
    }
    
    public Peli uusiPeli() {
        moodi.asetaMuuttujatPeliaVarten();
        peli = new Peli(arpoja, soittaja, moodi);
        return peli;
    }

    public int getomaViesti() {
        return omaviesti;
    }

    public int getVastustajanviesti() {
        return vastustajanviesti;
    }

    private boolean pelaajanSiirto() {
        if (peli.getPelaajaOhittaaVuoroja() == 0) {
            if (!peli.pelaaOmaVuoro()) {
                omaviesti = 10;
                return false;
            } else {
                omaviesti = peli.getOmaviesti();
                return true;
            }
        } else {
            peli.setPelaajaOhittaaVuoroja(peli.getPelaajaOhittaaVuoroja() - 1);
            return true;
        }
    }
    
    private void vastustajanSiirto(boolean vastustajanVuoroPelataan) {
        if (vastustajanVuoroPelataan && peli.getVastustajaOhittaaVuoroja() == 0) {
            peli.pelaaVastustajanVuoro();
            vastustajanviesti = peli.getVastustajanviesti();
        } else if (vastustajanVuoroPelataan) {
            peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
        }
    }
    
    public void peliSiirto() {
        boolean vastustajanVuoroPelataan = true;
        if (!pelaajanSiirto()) {
            vastustajanVuoroPelataan = false;
        }
        vastustajanSiirto(vastustajanVuoroPelataan);
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
            soittaja.soitaLoppu(0);
            return 3; // tasapeli
        }
        if (f == 0) {
            soittaja.soitaLoppu(1);
            return 1; // vastustaja voitti
        }
        if (g == 0) {
            soittaja.soitaLoppu(0);
            return 2; // pelaaja voitti
        }
        return 0; // ei voittoa;
    }

    public Soittaja getSoittaja() {
        return soittaja;
    }
    
}
