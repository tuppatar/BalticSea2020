package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Peli;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Kayttis implements Runnable {

    private JFrame frame;
    private Palusta piirtoalusta;
    private Peli peli;
    private Palusta piiro;
    
    private Aanet aanet;
    
    public Kayttis() {
//        kuk = new KuvanKasittelija();
//        kuvat = kuk.lataaKuvat();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Palusta");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random arpoja = new Random();
        
        aanet = new Aanet();
        aanet.setSoita(2);
        new Thread(aanet).start();
        //aanet.start();
        peli = new Peli(arpoja, aanet);
        luoKomponentit(frame.getContentPane());
        lisaaKuuntelijat();

        //Test
        //aanet.play("erased.wav");
        //aanet.soitaMenumusiikki(); //AANET
        //aanet.play("Musa.mp3");
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Palusta(peli);
        container.add(piirtoalusta);
        this.piiro = piirtoalusta;
        // this.aanet = new Aanet(); AANET
    }

    private void lisaaKuuntelijat() {
        frame.addKeyListener(new NappaimistonKuuntelija(this.piiro, peli));
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
