/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.balticsea2020.gui;

import fi.balticsea2020.logiikka.Peli;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import kuva.Kuva;

/**
 *
 * @author jee
 */
public class Palusta extends JPanel {
    
    private Color vari;
    private Peli peli;
    private ArrayList<Kuva> kuvat;
    
    public Palusta(Peli pel, ArrayList<Kuva> kuvat) {
        vari = new Color(140,0,0);
        super.setBackground(Color.WHITE);
        peli = pel;
        this.kuvat = kuvat;
    }

    private void piirraOma(Graphics graphics) {
        int[][] piir = peli.getPelaajanPiirrettava();
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {

                Kuva koo;
                if (piir[b][a] == 0) {
                    koo = kuvat.get(0);
                } else if (piir[b][a] == 1) {
                    koo = kuvat.get(1);                    
                } else if (piir[b][a] == 10) {
                    koo = kuvat.get(2);                    
                } else if (piir[b][a] == 11) {
                    koo = kuvat.get(3);                    
                } else if (piir[b][a] == 12) {
                    koo = kuvat.get(4);                    
                } else if (piir[b][a] == 13) {
                    koo = kuvat.get(5);                    
                } else if (piir[b][a] == 14) {
                    koo = kuvat.get(6);                    
                } else if (piir[b][a] == 15) {
                    koo = kuvat.get(7);                    
                } else if (piir[b][a] == 30) { // maa osuma
                    koo = kuvat.get(9);
                } else if (piir[b][a] == 31) { // meri osuma
                    koo = kuvat.get(10);                    
                } else if (piir[b][a] == 40) {
                    koo = kuvat.get(11);                    
                } else if (piir[b][a] == 41) {
                    koo = kuvat.get(12);                    
                } else if (piir[b][a] == 42) {
                    koo = kuvat.get(13);                    
                } else if (piir[b][a] == 43) {
                    koo = kuvat.get(14);                    
                } else if (piir[b][a] == 44) {
                    koo = kuvat.get(15);                    
                } else if (piir[b][a] == 45) {
                    koo = kuvat.get(16);                    
                } else {
                    koo = kuvat.get(17); // turha mutta saa virheilmoituksen pois
                }
                
                for (int c = 0; c < 20; c++) {
                    for (int d = 0; d < 20; d++) {
                        vari = new Color(koo.punainen(d, c),koo.vihrea(d, c),koo.sininen(d, c));
                        graphics.setColor(vari);
                        graphics.fillRect(a*20+d,b*20+c,1,1);
                    }
                }
                
            }
        }
       
    }

    private void piirraVastustaja(Graphics graphics) {
        int[][] piir = peli.getVastustajanPiirrettava();
        boolean[][] jaa = peli.getVisible();
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {

                Kuva koo;
                if (jaa[b][a]) {
                    if (piir[b][a] == 0) {
                        koo = kuvat.get(0);
                    } else if (piir[b][a] == 1) {
                        koo = kuvat.get(1);                    
                    } else if (piir[b][a] == 10) {
                        koo = kuvat.get(2);                    
                    } else if (piir[b][a] == 11) {
                        koo = kuvat.get(3);                    
                    } else if (piir[b][a] == 12) {
                        koo = kuvat.get(4);                    
                    } else if (piir[b][a] == 13) {
                        koo = kuvat.get(5);                    
                    } else if (piir[b][a] == 14) {
                        koo = kuvat.get(6);                    
                    } else if (piir[b][a] == 15) {
                        koo = kuvat.get(7);                    
                    } else if (piir[b][a] == 30) { // maa osuma
                        koo = kuvat.get(9);
                    } else if (piir[b][a] == 31) { // meri osuma
                        koo = kuvat.get(10);                    
                    } else if (piir[b][a] == 40) {
                        koo = kuvat.get(11);                    
                    } else if (piir[b][a] == 41) {
                        koo = kuvat.get(12);                    
                    } else if (piir[b][a] == 42) {
                        koo = kuvat.get(13);                    
                    } else if (piir[b][a] == 43) {
                        koo = kuvat.get(14);                    
                    } else if (piir[b][a] == 44) {
                        koo = kuvat.get(15);                    
                    } else if (piir[b][a] == 45) {
                        koo = kuvat.get(16);                    
                    } else {
                        koo = kuvat.get(17); // turha mutta saa virheilmoituksen pois
                    }
                } else {
                    koo = kuvat.get(8);
                }
                    
                for (int c = 0; c < 20; c++) {
                    for (int d = 0; d < 20; d++) {
                        vari = new Color(koo.punainen(d, c),koo.vihrea(d, c),koo.sininen(d, c));
                        graphics.setColor(vari);
                        graphics.fillRect(a*20+d+500,b*20+c,1,1);
                    }
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
