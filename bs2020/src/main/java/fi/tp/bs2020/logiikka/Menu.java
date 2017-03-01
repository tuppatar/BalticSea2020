/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

import java.util.List;

/**
 *
 * @author Tuomas Pätäri
 */
public class Menu {
    
    private int x, y = 0;
    private final Integer[] valinnat = {2, 4, 4, 3, 3, 2, 2};
    private PeliMuuttujat moodi;

    public void setMoodi(PeliMuuttujat moodi) {
        this.moodi = moodi;
    }

    public Integer[] getValinnat() {
        return valinnat;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void korjaaX() {
        if (x >= valinnat[y]) {
            x = valinnat[y] - 1;
        }
    }
    
    public void vahennaY() {
        y--;
        if (y < 0) {
            y = valinnat.length - 1;
        }
        korjaaX();
    }

    public void lisaaY() {
        y++;
        if (y >= valinnat.length) {
            y = 0;
        }
        korjaaX();
    }

    public void vahennaX() {
        x--;
        if (x < 0) {
            x = valinnat[y] - 1;
        }
    }
    
    public void lisaaX() {
        x++;
        if (x >= valinnat[y]) {
            x = 0;
        }
    }
    
    public int menuEnter() { // 0 = menu jatkuu; 1 = uusi peli; 2 = exit game
        if (y == 0) {
            if (x == 0) {
                return 1;
            } else {
                return 2;
            }
        }
        if (y == 1) {
            if (moodi.getPelaaja() != x && moodi.getVastustaja() != x) {
                moodi.setPelaaja(x);
            }
        }
        if (y == 2) {
            if (moodi.getPelaaja() != x && moodi.getVastustaja() != x) {
                moodi.setVastustaja(x);
            }
        }
        if (y == 3) {
            moodi.setElementit(x);
        }
        if (y == 4) {
            moodi.setHajanaisuus(x);
        }
        return 0;
    }
    
}
