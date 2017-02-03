package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.Peli;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import javax.swing.JPanel;


public class Palusta extends JPanel {
    
    private Color vari;
    private Peli peli;
    private KuvanLataaja kl;
    private Map<Integer, BufferedImage> kuvat;
    
    public Palusta(Peli pel) {
        vari = new Color(140,0,0);
        super.setBackground(Color.WHITE);
        peli = pel;
        kl = new KuvanLataaja();
        this.kuvat = kl.getKuvat();
    }

    private void piirraOma(Graphics graphics) {
        int[][] piir = peli.getPelaajanPiirrettava();
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {
                graphics.drawImage(kuvat.get(piir[b][a]), a*20, b*20, this);
            }
        }
    }

    private void piirraVastustaja(Graphics graphics) {
        int[][] piir = peli.getVastustajanPiirrettava();
        boolean[][] jaa = peli.getVisible();
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {
                if (jaa[b][a]) {
                    graphics.drawImage(kuvat.get(piir[b][a]), a*20+500, b*20, this);
                } else {
                    graphics.drawImage(kuvat.get(8), a*20+500, b*20, this);
                }
            }
        }
    }
    
    private void piirraKursori(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(peli.getKursoriX()*20+500,peli.getKursoriY()*20,20,20);
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
