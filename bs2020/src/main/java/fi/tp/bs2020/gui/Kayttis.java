package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliRunko;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Kayttis implements Runnable {

    private JFrame frame;
    private Grafiikka piirtoalusta;
    private Peli peli;
    private Grafiikka grafik;
    private NappainTapahtuma nt;
    private PeliRunko pelirunko;
    
    private Aanet aanet;
    
    public Kayttis() {
//        kuk = new KuvanKasittelija();
//        kuvat = kuk.lataaKuvat();
    }
    
    @Override
    public void run() {
        frame = new JFrame("Palusta");
        frame.setPreferredSize(new Dimension(1220, 700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Random arpoja = new Random();
        
        pelirunko = new PeliRunko(arpoja);
        nt = new NappainTapahtuma();
        nt.setFrame(frame);
        luoKomponentit(frame.getContentPane());
        lisaaKuuntelijat();

        //new Thread(aanet).start();
        //aanet.start();
        //aanet.play("erased.wav");
        //aanet.soitaMenumusiikki(); //AANET
        //aanet.play("Musa.mp3");
        
        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        piirtoalusta = new Grafiikka(pelirunko);
        container.add(piirtoalusta);
        this.grafik = piirtoalusta;
    }

    private void lisaaKuuntelijat() {
        frame.addKeyListener(new NappaimistonKuuntelija(this.grafik, pelirunko, nt));
    }

    public JFrame getFrame() {
        return frame;
    }
    
}
