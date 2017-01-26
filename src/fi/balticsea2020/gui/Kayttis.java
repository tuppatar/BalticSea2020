package fi.balticsea2020.gui;

import fi.balticsea2020.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import kuva.Kuva;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jee
 */
public class Kayttis implements Runnable {

    private JFrame frame;
    private Palusta piirtoalusta;
    private ArrayList<Kuva> kuvat;
    private KuvanKasittelija kuk;
    private Peli peli;
    private Palusta piiro;
    
    public Kayttis() {
        kuk = new KuvanKasittelija();
        kuvat = kuk.lataaKuvat();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Palusta");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random arpoja = new Random();
        
        peli = new Peli(arpoja);
        luoKomponentit(frame.getContentPane());
        lisaaKuuntelijat();

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Palusta(peli, kuvat);
        container.add(piirtoalusta);
        this.piiro = piirtoalusta;        
    }

    private void lisaaKuuntelijat() {
        frame.addKeyListener(new NappaimistonKuuntelija(this.piiro, peli));
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
