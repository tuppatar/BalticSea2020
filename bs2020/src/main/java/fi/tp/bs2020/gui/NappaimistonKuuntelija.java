package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliRunko;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class NappaimistonKuuntelija implements KeyListener {

    private Component component;
    private PeliRunko pelirunko;
    private NappainTapahtuma nt;
    private boolean released;
    
    /**
     * Näppäimistön painalluksiin reagoiva luokka. Sisältää toistaiseksi toiminnallisuutta joka on tarkoitus siirtää muualle.
     * @param component Komponentti
     * @param peli Käynnissä oleva Peli.
     */
    public NappaimistonKuuntelija(Component component, PeliRunko pelirunko, NappainTapahtuma nt) {
        this.component = component;
        this.pelirunko = pelirunko;
        this.nt = nt;
        nt.setPelirunko(pelirunko);
        this.released = true;
        //this.kuvio = kuvio;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            nt.peliVasemmalle();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            nt.peliOikealle();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            nt.peliYlos();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            nt.peliAlas();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            nt.enter(component);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            nt.escape(component);
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            nt.nappainE(component);
        } else if (e.getKeyCode() == KeyEvent.VK_K) {
            nt.nappainK(component);
        }
        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.released = true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
