/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hate Halon
 */
public class PeliTest {
    
    Peli peli;
    
    public PeliTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Random arpoja = new Random();
        peli = new Peli(arpoja);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void pelaa3OmaaVuoroaJaLaskeOsumiaVastustajalla3() {
        peli.pelaaOmaVuoro();
        peli.setKursoriX(10);
        peli.setKursoriY(15);
        peli.pelaaOmaVuoro();
        peli.setKursoriX(5);
        peli.setKursoriY(12);
        peli.pelaaOmaVuoro();
        int[][] palautus = peli.getVastustajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] >= 20) {
                a++;
            }
        }
        assertEquals(3, a);
    }
    
    @Test
    public void pelaa3VastustajanVuoroaJaLaskeOsumiaItsella3() {
        peli.pelaaVastustajanVuoro();
        peli.pelaaVastustajanVuoro();
        peli.pelaaVastustajanVuoro();
        int[][] palautus = peli.getPelaajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] >= 20) {
                a++;
            }
        }
        assertEquals(3, a);
    }
    
    @Test
    public void pelaa380VastustajanVuoroaJaLaskeOsumiaItsella380() {
        for (int c = 0; c < 380; c++) {
            peli.pelaaVastustajanVuoro();
        }
        int[][] palautus = peli.getPelaajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] >= 20) {
                a++;
            }
        }
        assertEquals(380, a);
    }
    
    @Test
    public void pelaa380VastustajanVuoroaJaLaskeOsumattomiaItsella20() {
        for (int c = 0; c < 380; c++) {
            peli.pelaaVastustajanVuoro();
        }
        int[][] palautus = peli.getPelaajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] < 20) {
                a++;
            }
        }
        assertEquals(20, a);
    }

    @Test
    public void pelaa100OnnistunuttaOmaaVuoroaJaLaskeVastustajalla300PimeaaRuutua() {
        int a = 0;
        while (a < 100) {
            peli.setKursoriX(a / 20);
            peli.setKursoriY(a % 20);
            if (peli.pelaaOmaVuoro()) {
                a++;
            }
        }
        boolean[][] palautus = peli.getVisible();
        a = 0;
        for (int b = 0; b < 400; b++) {
            if (!palautus[b / 20][b % 20]) {
                a++;
            }
        }
        assertEquals(300, a);
    }
    
    @Test
    public void omaPeliEiPelauduJoTuhotussaRuudussa() {
        peli.setKursoriX(10);
        peli.setKursoriY(15);
        peli.pelaaOmaVuoro();
        assertFalse(peli.pelaaOmaVuoro());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
