package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Menu;
import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliRunko;
import java.awt.Component;
import javax.swing.JFrame;

/**
 *
 * @author Tuomas Pätäri
 */
public class NappainTapahtuma {
    
    private Peli peli;
    private Menu menu;
    private PeliRunko pelirunko;
    private JFrame frame;
    
    public void setPelirunko(PeliRunko pelirunko) {
        this.pelirunko = pelirunko;
        this.menu = pelirunko.getMenu();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    public void enter(Component component) {
        boolean tapahtunut = false;
        if (pelirunko.getPeliTilanne() == 0) {
            int tapahtuma = menu.menuEnter();
            if (tapahtuma == 1) {
                peli = pelirunko.uusiPeli();
                tapahtunut = true;
                pelirunko.setPeliTilanne(1);
            } else if (tapahtuma == 2) {
                pelirunko.getSoittaja().lopetaMusiikki();
                pelirunko.getSoittaja().lopetaAanet();
                pelirunko.getSoittaja().tauko(100);
                frame.dispose();
            }
        }
        if (!tapahtunut && pelirunko.getPeliTilanne() == 1) {
            pelirunko.peliSiirto();
            tapahtunut = true;
        }
    }
    
    public void escape(Component component) {
        boolean tapahtunut = false;
        if (pelirunko.getPeliTilanne() == 1) {
            pelirunko.setPeliTilanne(10);
            tapahtunut = true;
        }
        if (!tapahtunut && pelirunko.getPeliTilanne() >= 20) {
            pelirunko.setPeliTilanne(0);
            tapahtunut = true;
        }
    }
    
    public void nappainK(Component component) {
        if (pelirunko.getPeliTilanne() == 10) {
            pelirunko.setPeliTilanne(0);
        }
    }
    
    public void nappainE(Component component) {
        if (pelirunko.getPeliTilanne() == 10) {
            pelirunko.setPeliTilanne(1);
        }
    }
    
    public void peliVasemmalle() {
        if (pelirunko.getPeliTilanne() == 0) {
            menu.vahennaX();
        }
        if (pelirunko.getPeliTilanne() == 1) {
            if (peli.getKursoriX() > 0) {
                peli.setKursoriX(peli.getKursoriX() - 1);
            }
        }
    }
    
    public void peliOikealle() {
        if (pelirunko.getPeliTilanne() == 0) {
            menu.lisaaX();
        }
        if (pelirunko.getPeliTilanne() == 1) {
            if (peli.getKursoriX() < 19) {
                peli.setKursoriX(peli.getKursoriX() + 1);
            }
        }
    }
    
    public void peliYlos() {
        if (pelirunko.getPeliTilanne() == 0) {
            menu.vahennaY();
        }
        if (pelirunko.getPeliTilanne() == 1) {
            if (peli.getKursoriY() > 0) {
                peli.setKursoriY(peli.getKursoriY() - 1);
            }
        }
    }
    
    public void peliAlas() {
        if (pelirunko.getPeliTilanne() == 0) {
            menu.lisaaY();
        }
        if (pelirunko.getPeliTilanne() == 1) {
            if (peli.getKursoriY() < 19) {
                peli.setKursoriY(peli.getKursoriY() + 1);
            }
        }
    }
    
}
