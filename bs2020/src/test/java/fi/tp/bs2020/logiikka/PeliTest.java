package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import fi.tp.bs2020.gui.Soittaja;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PeliTest {
    
    Peli peli;
    PeliRunko pr;
    
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
        pr = new PeliRunko(arpoja);
        Soittaja soittaja = pr.getSoittaja();
        PeliMuuttujat moodi = pr.getMoodi();
        moodi.setElementit(arpoja.nextInt(2));
        moodi.setHajanaisuus(arpoja.nextInt(2));
        peli = pr.uusiPeli();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void peliRunkoPalauttaaOikeanPelin() {
        assertEquals(peli, pr.getPeli());
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
    
    @Test
    public void tarkistaVoittoPalauttaaNollan() {
        assertEquals(0, pr.tarkistaVoitto());
    }

    @Test
    public void tarkistaVoittoPalauttaaHavion() {
        for (int c = 0; c < 400; c++) {
            peli.pelaaVastustajanVuoro();
        }
        assertEquals(1, pr.tarkistaVoitto());
    }
    
    @Test
    public void tarkistaVoittoPalauttaaVoiton() {
        int a = 0;
        while (a < 400) {
            peli.setKursoriX(a / 20);
            peli.setKursoriY(a % 20);
            if (peli.pelaaOmaVuoro()) {
                a++;
            }
        }
        assertEquals(2, pr.tarkistaVoitto());
    }
    
    @Test
    public void tarkistaVoittoPalauttaaTasapelin() {
        for (int c = 0; c < 400; c++) {
            peli.pelaaVastustajanVuoro();
        }
        int a = 0;
        while (a < 400) {
            peli.setKursoriX(a / 20);
            peli.setKursoriY(a % 20);
            if (peli.pelaaOmaVuoro()) {
                a++;
            }
        }
        assertEquals(3, pr.tarkistaVoitto());
    }
    
    @Test
    public void kaikkiMuuttuuNakyvaksi() {
        boolean[][] vertaus;
        vertaus = new boolean[20][20];
        for (int a = 0; a < 400; a++) {
            vertaus[a / 20][a % 20] = false;
        }
        assertArrayEquals(vertaus, peli.getVisible());
        for (int a = 0; a < 400; a++) {
            vertaus[a / 20][a % 20] = true;
        }
        peli.setAllVisible();
        assertArrayEquals(vertaus, peli.getVisible());
    }
    
    @Test
    public void kursoritPalautuvat() {
        assertEquals(0, peli.getKursoriX());
        assertEquals(0, peli.getKursoriY());
        peli.setKursoriX(2);
        peli.setKursoriY(2);
        assertEquals(2, peli.getKursoriX());
        assertEquals(2, peli.getKursoriY());
    }
    
    @Test
    public void tarkistaTuhoListat() {
        Map Oma = peli.omaaTuhottu();
        Map Vast = peli.vastustajaaTuhottu();
        for (int a = 0; a < Oma.size(); a++) {
            assertEquals(2, Oma.get(a));
            assertEquals(2, Vast.get(a));
        }
        for (int c = 0; c < 400; c++) {
            peli.pelaaVastustajanVuoro();
            peli.setKursoriX(c % 20);
            peli.setKursoriY(c / 20);
            peli.pelaaOmaVuoro();
        }
        Oma = peli.omaaTuhottu();
        Vast = peli.vastustajaaTuhottu();
        for (int a = 0; a < Oma.size(); a++) {
            assertEquals(1, Oma.get(a));
            assertEquals(1, Vast.get(a));
        }
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
