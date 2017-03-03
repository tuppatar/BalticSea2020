package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Soittaja;
import java.util.List;

/**
 * Alkuvalikko-luokka. Vastaa valikossa liikkumisesta ja valintojen tekemisestä.
 * Asettaa tehdyt valinnat PeliMuuttujat-luokkaan.
 */
public class Menu {
    
    private int x, y = 0;
    private final Integer[] valinnat = {2, 4, 4, 3, 3, 2, 2};
    private PeliMuuttujat moodi;
    private Soittaja soittaja;

    /**
     * Konstruktori.
     * @param moodi     Pelissä olevat muuttujat.
     * @param soittaja  Pelin äänien soittaja.
     */
    public Menu(PeliMuuttujat moodi, Soittaja soittaja) {
        this.moodi = moodi;
        this.soittaja = soittaja;
    }

    public Integer[] getValinnat() {
        return valinnat;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void korjaaX() {
        if (x >= valinnat[y]) {
            x = valinnat[y] - 1;
        }
    }
    
    /**
     * Menussa liikutaan ylös.
     */
    public void vahennaY() {
        soittaja.soitaAani(100);
        y--;
        if (y < 0) {
            y = valinnat.length - 1;
        }
        korjaaX();
    }

    /**
     * Menussa liikutaan Alas.
     */
    public void lisaaY() {
        soittaja.soitaAani(100);
        y++;
        if (y >= valinnat.length) {
            y = 0;
        }
        korjaaX();
    }

    /**
     * Menussa liikutaan vasemmalle.
     */
    public void vahennaX() {
        soittaja.soitaAani(100);
        x--;
        if (x < 0) {
            x = valinnat[y] - 1;
        }
    }
    
    /**
     * Menussa liikutaan Oikealle.
     */
    public void lisaaX() {
        soittaja.soitaAani(100);
        x++;
        if (x >= valinnat[y]) {
            x = 0;
        }
    }
    
    private void menuTapahtuma1() {
        if (moodi.getPelaaja() != x && moodi.getVastustaja() != x) {
            moodi.setPelaaja(x);
            soittaja.soitaAani(101);
        } else if (moodi.getPelaaja() == x) {
            soittaja.soitaAani(101);
        } else {
            soittaja.soitaAani(102);
        }
    }

    private void menuTapahtuma2() {
        if (moodi.getPelaaja() != x && moodi.getVastustaja() != x) {
            moodi.setVastustaja(x);
            soittaja.soitaAani(101);
        } else if (moodi.getVastustaja() == x) {
            soittaja.soitaAani(101);
        } else {
            soittaja.soitaAani(102);
        }
    }
    
    private void menuTapahtuma3() {
        soittaja.soitaAani(101);
        moodi.setElementit(x);
    }
    
    private void menuTapahtuma4() {
        soittaja.soitaAani(101);
        moodi.setHajanaisuus(x);
    }
    
    private void menuTapahtuma5() {
        if (x == 0 && !moodi.isMusaOn()) {
            soittaja.soitaAani(101);
            moodi.setMusaOn(true);
            soittaja.aloitaMusiikki();
        }
        if (x == 1 && moodi.isMusaOn()) {
            soittaja.soitaAani(101);
            moodi.setMusaOn(false);
            soittaja.lopetaMusiikki();
        }
    }
    
    private void menuTapahtuma6() {
        if (x == 0 && !moodi.isAanetOn()) {
            soittaja.soitaAani(101);
            moodi.setAanetOn(true);
        }
        if (x == 1 && moodi.isAanetOn()) {
            soittaja.soitaAani(101);
            moodi.setAanetOn(false);
        }
    }
    
    /** Eri valikkotapahtumat, kun NappainTapahtuma-luokalta tulee tieto Enter-nappulan painamisesta valikossa.
     * @return int: 0 = menu jatkuu, 1 = aloitetaan uusi peli, 2 = poistutaan koko pelistä.
     */
    public int menuEnter() {
        if (y == 0) {
            if (x == 0) {
                soittaja.soitaAani(101);
                return 1;
            } else {
                return 2;
            }
        } else if (y == 1) {
            menuTapahtuma1();
        } else if (y == 2) {
            menuTapahtuma2();
        } else if (y == 3) {
            menuTapahtuma3();
        } else if (y == 4) {
            menuTapahtuma4();
        } else if (y == 5) {
            menuTapahtuma5();
        } else if (y == 6) {
            menuTapahtuma6();
        }
        return 0;
    }
    
}
