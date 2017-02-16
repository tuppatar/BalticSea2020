package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Peli;
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
    private Peli peli;
    private KuvanLataaja kl;
    private Map<Integer, BufferedImage> kuvat;
    private PiirrettavanMuokkaaja piirtaja;
    
    /**
     * Konstruktori.
     * @param pel Peli, joka on meneillään.
     */
    public Grafiikka(Peli pel) {
        sumentavaVari = new Color(127, 127, 127);
        super.setBackground(Color.BLACK);
        peli = pel;
        kl = new KuvanLataaja();
        this.kuvat = kl.getKuvat();
        piirtaja = new PiirrettavanMuokkaaja();
    }

    private void piirraOma(Graphics graphics) {
        int[][] piir = peli.getPelaajanPiirrettava();
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20, (loop / 20) * 20, this);
        }
    }
    
    private void piirraVastustaja(Graphics graphics) {
        int[][] piir = piirtaja.piirrettava(peli.getVastustajanPiirrettava(), peli.getVisible(), peli.getVastustajanMaastonSatunnaisuus());
        for (int loop = 0; loop < 400; loop++) {
            graphics.drawImage(kuvat.get(piir[loop / 20][loop % 20]), (loop % 20) * 20 + 500, (loop / 20) * 20, this);
        }
    }
    
    private void piirraKursori(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(peli.getKursoriX() * 20 + 500, peli.getKursoriY() * 20, 20, 20);
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        piirraOma(graphics);
        piirraVastustaja(graphics);
        piirraKursori(graphics);
        //graphics.setColor(vari);
    }
}
