package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Menu;
import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliMuuttujat;
import fi.tp.bs2020.logiikka.PeliRunko;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Grafiikasta vastaava luokka.
 */
public class Grafiikka extends JPanel {
    
    private Color sumentavaVari;
    //private Peli peli;
    private KuvanLataaja kl;
    private Map<Integer, BufferedImage> kuvat, menukuvat;
    private PiirrettavanMuokkaaja piirtaja;
    private PeliRunko pelirunko;
    private final int offSet = 500;
    
    /**
     * Konstruktori.
     * @param pel Peli, joka on meneillään.
     */
    public Grafiikka(PeliRunko pelirunko) {
        super.setBackground(Color.BLACK);
        this.pelirunko = pelirunko;
        kl = new KuvanLataaja();
        this.kuvat = kl.getKuvat();
        this.menukuvat = kl.getMenukuvat();
        piirtaja = new PiirrettavanMuokkaaja();
    }

    private void piirraOma(Graphics graphics, Peli peli) {
        int[][] piir = peli.getPelaajanPiirrettava();
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20, (loop / 20) * 20, this);
        }
    }
    
    private void piirraVastustaja(Graphics graphics, Peli peli) {
        int[][] piir = piirtaja.piirrettava(peli.getVastustajanPiirrettava(), peli.getVisible(), peli.getVastustajanMaastonSatunnaisuus());
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20 + 500, (loop / 20) * 20, this);
        }
    }
    
    private void piirraKursori(Graphics graphics, Peli peli) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(peli.getKursoriX() * 20 + 500, peli.getKursoriY() * 20, 20, 20);
    }
    
    private void piirraMenuElementit(Graphics graphics, PeliMuuttujat moodi) {
        for (int a = 0; a < 7; a++) {
            graphics.drawImage(menukuvat.get(a), 40, a * 75 + 110, this);
        }
        for (int a = 0; a < 4; a++) {
            if (moodi.getPelaaja() == a) { // pelaaja
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 1 + 100), 340 + a * 120, 1 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 1), 340 + a * 120, 1 * 75 + 110, this);
            }
            if (moodi.getVastustaja() == a) { // vastustaja
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 2 + 100), 340 + a * 120, 2 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 2), 340 + a * 120, 2 * 75 + 110, this);
            }
        }
        for (int a = 0; a < 3; a++) {
            if (moodi.getElementit() == a) { // elementit
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 3 + 100), 340 + a * 120, 3 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 3), 340 + a * 120, 3 * 75 + 110, this);
            }
            if (moodi.getHajanaisuus() == a) { // rikkonaisuus
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 4 + 100), 340 + a * 120, 4 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 4), 340 + a * 120, 4 * 75 + 110, this);
            }
        }
        for (int a = 0; a < 2; a++) {
            graphics.drawImage(menukuvat.get((a + 1) * 10 + 0 + 100), 340 + a * 120, 0 * 75 + 110, this);
            int b = 0;
            if (moodi.isMusaOn()) {
                b = 1;
            }
            if (a != b) { //musa
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 5 + 100), 340 + a * 120, 5 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 5), 340 + a * 120, 5 * 75 + 110, this);
            }
            b = 0;
            if (moodi.isAanetOn()) {
                b = 1;
            }
            if (a != b) { //aanet
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 6 + 100), 340 + a * 120, 6 * 75 + 110, this);
            } else {
                graphics.drawImage(menukuvat.get((a + 1) * 10 + 6), 340 + a * 120, 6 * 75 + 110, this);
            }
        }
    }
    
    private void piirraMenuKursori(Graphics graphics, Menu menu) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(menu.getX() * 120 + 335, menu.getY() * 75 + 105, 100, 70);
        graphics.drawRect(menu.getX() * 120 + 336, menu.getY() * 75 + 106, 98, 68);
        graphics.drawRect(menu.getX() * 120 + 337, menu.getY() * 75 + 107, 96, 66);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (pelirunko.getPeliTilanne() == 0) {
            piirraMenuElementit(graphics, pelirunko.getMoodi());
            piirraMenuKursori(graphics, pelirunko.getMenu());
        }
        
        if (pelirunko.getPeliTilanne() == 1) {
            piirraOma(graphics, pelirunko.getPeli());
            piirraVastustaja(graphics, pelirunko.getPeli());
            piirraKursori(graphics, pelirunko.getPeli());
        }
        //graphics.setColor(vari);
    }
}
