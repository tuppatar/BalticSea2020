package fi.tp.bs2020.logiikka;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import fi.tp.bs2020.gui.Aanet;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author Tuomas Pätäri
 */
public class PeliRunko {
    
    private int peliTilanne; // 0 = menu, 1 = peli, 2 = peliloppu, 3 = peliesc
    private Peli peli;
    private Aanet aanet;
    private Random arpoja;
    private PeliMuuttujat moodi;
    private Menu menu;
    private boolean musaOn, aanetOn;
    
    public PeliRunko(Random arpoja) {
        peliTilanne = 0;
        this.arpoja = arpoja;
        this.aanet = new Aanet();
        this.moodi = new PeliMuuttujat();
        this.menu = this.uusiMenu();
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

    public void peliSiirto(Component component) {
        if (peli.pelaaOmaVuoro()) {
            component.repaint();
            if (peli.getVastustajaOhittaaVuoroja() == 0) {
                peli.pelaaVastustajanVuoro();
            } else {
                peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
            }
        }
        while (peli.getPelaajaOhittaaVuoroja() > 0) {
            if (peli.getVastustajaOhittaaVuoroja() == 0) {
                peli.pelaaVastustajanVuoro();
            } else {
                peli.setVastustajaOhittaaVuoroja(peli.getVastustajaOhittaaVuoroja() - 1);
            }
            peli.setPelaajaOhittaaVuoroja(peli.getPelaajaOhittaaVuoroja() - 1);
        }
        component.repaint();
        int kumpi = peli.tarkistaVoitto(); // 1 = pelaaja voitti; 2 = kone voitti; 3 = tasapeli
        if (kumpi > 0) {
            // peliRunko.setTilanne(1); esim
            //peliloppuu
            System.out.println("peli loppuu");
        }
    }
    
    
}
