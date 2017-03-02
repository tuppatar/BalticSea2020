package fi.tp.bs2020.gui;

import fi.tp.bs2020.logiikka.PeliMuuttujat;
import java.util.Random;

/**
 *
 */
public class Soittaja {

    private Aanet aanet;
    private int soitettava;
    private Random arpoja;
    private PeliMuuttujat moodi;
    private Thread musa;
    
    public Soittaja(Random arpoja, PeliMuuttujat moodi) {
        this.aanet = new Aanet(this);
        aanet.luoAluksi();
        this.arpoja = arpoja;
        this.moodi = moodi;
    }

    public void tauko(int msecs) {
        try {
            Thread.sleep(msecs);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void soitaAani(int ssoitettava) {
        if (moodi.isAanetOn()) {
            tauko(20);
            this.soitettava = ssoitettava;
            tauko(20);
            new Thread(aanet).start();
        }
    }
    
    public void aloitaMusiikki() {
        tauko(20);
        this.soitettava = 1000;
        tauko(20);
        musa = new Thread(aanet);
        musa.start();
    }
    
    public void lopetaMusiikki() {
        //musa.stop();
        aanet.setMusiikkiSoi(false);
    }

    public void lopetaAanet() {
        aanet.setAanetKeskeytyy(true);
    }
    
    public int getSoitettava() {
        return soitettava;
    }
    
    public void setSoitettava(int soitettava) {
        this.soitettava = soitettava;
    }
    
    public void soitaPelaaja(int viesti, int eiosumaa) {
        if (viesti == 1) { // osuma
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(80 + arpoja.nextInt(10));
            this.soitaAani(moodi.getPelaaja() * 10 + 2 + arpoja.nextInt(2));
        } else if (viesti == 3) { //upotus
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(70 + arpoja.nextInt(5));
            this.soitaAani(moodi.getPelaaja() * 10 + 1);
        } else if (viesti == 5) { //uboat
            int bonus = 0;
            if (moodi.getPelaaja() == 0) {
                bonus = arpoja.nextInt(2);
            }
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(70 + arpoja.nextInt(5));
            this.soitaAani(moodi.getPelaaja() * 10 + 4 + bonus);
        } else if (viesti == 7) { //talo
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(80 + arpoja.nextInt(10));
            this.soitaAani(moodi.getVastustaja() * 10);
        } else if (eiosumaa == 0) {
            this.soitaAani(60 + arpoja.nextInt(6));
        } else if (eiosumaa == 1) {
            this.soitaAani(80 + arpoja.nextInt(10));
        }
    }
    
    public void soitaVastustaja(int viesti, int eiosumaa) {
        if (viesti == 2) { // osuma
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(80 + arpoja.nextInt(10));
            this.soitaAani(moodi.getVastustaja() * 10 + 2 + arpoja.nextInt(2));
        } else if (viesti == 4) { //upotus
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(70 + arpoja.nextInt(5));
            this.soitaAani(moodi.getVastustaja() * 10 + 1);
        } else if (viesti == 6) { //uboat
            int bonus = 0;
            if (moodi.getVastustaja() == 0) {
                bonus = arpoja.nextInt(2);
            }
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(70 + arpoja.nextInt(5));
            this.soitaAani(moodi.getVastustaja() * 10 + 4 + bonus);
        } else if (viesti == 8) { //talo
            this.soitaAani(50 + arpoja.nextInt(9));
            this.soitaAani(80 + arpoja.nextInt(10));
            this.soitaAani(moodi.getPelaaja() * 10);
        } else if (eiosumaa == 0) {
            this.soitaAani(60 + arpoja.nextInt(6));
        } else if (eiosumaa == 1) {
            this.soitaAani(80 + arpoja.nextInt(10));
        }
    }
    
    public void soitaLoppu(int musa) {
        this.soitaAani(musa + 200);
    }
    
}
