/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.balticsea2020.gui;

import fi.balticsea2020.logiikka.Peli;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author jee
 */
public class NappaimistonKuuntelija implements KeyListener {

    private Component component;
    private Peli peli;

    public NappaimistonKuuntelija(Component component, Peli peli) {
        this.component = component;
        this.peli = peli;
        //this.kuvio = kuvio;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (peli.getKursoriX() > 0) {
                peli.setKursoriX(peli.getKursoriX() - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (peli.getKursoriX() < 19) {
                peli.setKursoriX(peli.getKursoriX() + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (peli.getKursoriY() > 0) {
                peli.setKursoriY(peli.getKursoriY() - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (peli.getKursoriY() < 19) {
                peli.setKursoriY(peli.getKursoriY() + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (peli.pelaaOmaVuoro()) {
                component.repaint();        
                peli.pelaaVastustajanVuoro();
                //component.repaint();        
                //peli.tauko(500);
            }
        }

        component.repaint();
        
        int kumpi = peli.tarkistaVoitto(); // 1 = pelaaja voitti; 2 = kone voitti; 3 = tasapeli
        if (kumpi > 0) {
            //peliloppuu
            System.out.println("peli loppuu");
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
