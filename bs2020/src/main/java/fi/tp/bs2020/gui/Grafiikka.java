package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Menu;
import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliMuuttujat;
import fi.tp.bs2020.logiikka.PeliRunko;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Grafiikasta vastaava luokka.
 */
public class Grafiikka extends JPanel {
    
    private Color sumentavaVari;
    private KuvanLataaja kl;
    private Map<Integer, BufferedImage> kuvat, menukuvat, viestikuvat;
    private PiirrettavanMuokkaaja piirtaja;
    private PeliRunko pelirunko;
    private final int offSet = 500;
    
    public Grafiikka(PeliRunko pelirunko) {
        super.setBackground(Color.BLACK);
        this.pelirunko = pelirunko;
        kl = new KuvanLataaja();
        this.kuvat = kl.getKuvat();
        this.menukuvat = kl.getMenukuvat();
        this.viestikuvat = kl.getViestikuvat();
        piirtaja = new PiirrettavanMuokkaaja();
    }

    private void piirraOma(Graphics graphics, Peli peli) {
        int[][] piir = peli.getPelaajanPiirrettava();
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20 + 180, (loop / 20) * 20 + 20, this);
        }
    }
    
    private void piirraVastustaja(Graphics graphics, Peli peli, int tilanne) {
        int[][] piir = piirtaja.piirrettava(peli.getVastustajanPiirrettava(), peli.getVisible(), peli.getVastustajanMaastonSatunnaisuus(), tilanne);
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20 + 620, (loop / 20) * 20 + 20, this);
        }
    }
    
    private void piirraKursori(Graphics graphics, Peli peli) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(peli.getKursoriX() * 20 + 620, peli.getKursoriY() * 20 + 20, 20, 20);
    }
    
    private void piirraPeliElementit(Graphics graphics, PeliMuuttujat moodi, Peli peli) {
        graphics.drawImage(menukuvat.get((moodi.getPelaaja() + 1) * 10 + 1 + 100), 20, 90, this);
        graphics.drawImage(menukuvat.get((moodi.getVastustaja() + 1) * 10 + 1 + 100), 1090, 90, this);
        piirraSivuLaivat(moodi.getLaivoja(), peli.vastustajaaTuhottu(), graphics, -20, 1160, 2, -2);
        piirraSivuLaivat(moodi.getLaivoja(), peli.omaaTuhottu(), graphics, 20, 20, 0, 0);
        graphics.drawImage(menukuvat.get(1000), 100, 590, this);
        graphics.drawImage(viestikuvat.get(1001), 20, 30, this);
        graphics.drawImage(viestikuvat.get(1002), 1090, 30, this);
    }
    
    private void piirraSivuLaivat(List<Integer> lai, Map<Integer, Integer> tuho, Graphics graphics, int os1, int os2, int k1, int k2) {
        for (int y = 0; y < lai.size(); y++) {
            int tuhoOffset = 0;
            if (tuho.get(y) == 1) {
                tuhoOffset = 100;
            }
            if (lai.get(y) == 1) {
                graphics.drawImage(kuvat.get(16 + tuhoOffset), 0 * os1 + os2, y * 30 + 180, this);
            } else {
                for (int x = 0; x < lai.get(y); x++) {
                    if (x == 0) {
                        graphics.drawImage(kuvat.get(13 + k1 + tuhoOffset), x * os1 + os2, y * 30 + 180, this);
                    } else if (x == (lai.get(y) - 1)) {
                        graphics.drawImage(kuvat.get(15 + k2 + tuhoOffset), x * os1 + os2, y * 30 + 180, this);
                    } else {
                        graphics.drawImage(kuvat.get(14 + tuhoOffset), x * os1 + os2, y * 30 + 180, this);
                    }
                }
            }
        }
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
        graphics.drawImage(menukuvat.get(1000), 100, 30, this);
    }
    
    private void piirraMenuKursori(Graphics graphics, Menu menu) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(menu.getX() * 120 + 335, menu.getY() * 75 + 105, 100, 70);
        graphics.drawRect(menu.getX() * 120 + 336, menu.getY() * 75 + 106, 98, 68);
        graphics.drawRect(menu.getX() * 120 + 337, menu.getY() * 75 + 107, 96, 66);
    }

    private void piirraMenuViestit(Graphics graphics, Menu menu, PeliMuuttujat moodi) {
        int x = 0;
        for (int y = 0; y < 4; y++) {
            if (menu.getY() == y + 1) {
                x = menu.getX();
            } else {
                if (y == 0) {
                    x = moodi.getPelaaja();
                }
                if (y == 1) {
                    x = moodi.getVastustaja();
                }
                if (y == 2) {
                    x = moodi.getElementit();
                }
                if (y == 3) {
                    x = moodi.getHajanaisuus();
                }
            }
            graphics.drawImage(menukuvat.get(y * 10 + x + 200), 800, y * 75 + 185, this);
        }
    }
    
    private void piirraPeliViestit(Graphics graphics, int oma, int vastustajan) {
        if (oma > 0) {
            graphics.drawImage(viestikuvat.get(oma), 580, 445, this); //
        }  
        if (vastustajan > 0) {
            graphics.drawImage(viestikuvat.get(vastustajan), 180, 445, this); //
        }  
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (pelirunko.getPeliTilanne() == 0) {
            piirraMenuElementit(graphics, pelirunko.getMoodi());
            piirraMenuKursori(graphics, pelirunko.getMenu());
            piirraMenuViestit(graphics, pelirunko.getMenu(), pelirunko.getMoodi());
        }
        if (pelirunko.getPeliTilanne() == 1) {
            piirraOma(graphics, pelirunko.getPeli());
            piirraVastustaja(graphics, pelirunko.getPeli(), 1);
            piirraKursori(graphics, pelirunko.getPeli());
            piirraPeliElementit(graphics, pelirunko.getMoodi(), pelirunko.getPeli());
            piirraPeliViestit(graphics, pelirunko.getOmaViesti(), pelirunko.getVastustajanviesti());
        }
        if (pelirunko.getPeliTilanne() == 10) { // escape
            piirraOma(graphics, pelirunko.getPeli());
            piirraVastustaja(graphics, pelirunko.getPeli(), 1);
            piirraPeliElementit(graphics, pelirunko.getMoodi(), pelirunko.getPeli());
            graphics.drawImage(viestikuvat.get(1000), 180, 445, this);
        }
        if (pelirunko.getPeliTilanne() >= 20) { // voitot
            piirraOma(graphics, pelirunko.getPeli());
            piirraVastustaja(graphics, pelirunko.getPeli(), pelirunko.getPeliTilanne());
            piirraPeliElementit(graphics, pelirunko.getMoodi(), pelirunko.getPeli());
            graphics.drawImage(viestikuvat.get(pelirunko.getPeliTilanne()), 180, 445, this);
        }
    }
}
