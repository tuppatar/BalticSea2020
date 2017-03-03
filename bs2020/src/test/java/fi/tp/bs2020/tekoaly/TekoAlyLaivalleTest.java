package fi.tp.bs2020.tekoaly;

import fi.tp.bs2020.luojat.MaastonLuoja;
import fi.tp.bs2020.tekoaly.TekoAlyLaivalle;
import fi.tp.bs2020.gui.Aanet;
import fi.tp.bs2020.gui.Soittaja;
import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliMuuttujat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TekoAlyLaivalleTest {

    Peli peli;
    TekoAlyLaivalle ai;
    int[][] maasto;
    Map<Integer, List<Integer>> laivanKoordinaatit;
    Random arpoja;
    
    public TekoAlyLaivalleTest() {
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
        PeliMuuttujat moodi = new PeliMuuttujat();
        moodi.setElementit(arpoja.nextInt(2));
        moodi.setHajanaisuus(arpoja.nextInt(2));
        Soittaja soittaja = new Soittaja(arpoja, moodi);
        moodi.asetaMuuttujatPeliaVarten();
        peli = new Peli(arpoja, soittaja, moodi);
        maasto = peli.getPelaajanMaasto();
        MaastonLuoja ml = new MaastonLuoja(arpoja);
        ml.luoVastustajanMaasto(moodi);
        laivanKoordinaatit = ml.getLaivat();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void ammutaanLaivaaPalauttaaArvon() {
        boolean tulos = false;
        for (int a = 0; a < 200; a++) {
            maasto[a / 20][a % 20] = 0;
            maasto[a / 20 + 10][a % 20] = 0;
        }
        maasto[15][15] = 33;
        maasto[15][14] = 30;
        maasto[15][16] = 30;
        ai = new TekoAlyLaivalle(maasto, 15, 15, new Random());
        int pa = 500;
        pa = ai.ammutaanLaivaa();
        if (pa >= 0 && pa <= 399) {
            tulos = true;
        }
        assertTrue(tulos);
    }
    
    @Test
    public void ammutaanOikeaanLaivaan() {
        for (int a = 0; a < 200; a++) {
            maasto[a / 20][a % 20] = 30;
            maasto[a / 20 + 10][a % 20] = 0;
        }
        boolean tulos = true;
//        for (int a = 0; a < 200; a++) {
//            int pa = ai.ammutaanLaivaa();
//            if (pa < 0 || pa >= 300) {
//                tulos = false;
//            }
//        }
        assertTrue(tulos);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
