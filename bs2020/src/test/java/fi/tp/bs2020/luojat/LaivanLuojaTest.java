package fi.tp.bs2020.luojat;

import fi.tp.bs2020.luojat.LaivanLuoja;
import fi.tp.bs2020.luojat.PiirtotaulukonLuoja;
import fi.tp.bs2020.gui.Aanet;
import fi.tp.bs2020.gui.Soittaja;
import fi.tp.bs2020.logiikka.Peli;
import fi.tp.bs2020.logiikka.PeliMuuttujat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LaivanLuojaTest {
    
    LaivanLuoja ll;
    
    public LaivanLuojaTest() {
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
        Peli peli = new Peli(arpoja, soittaja, moodi);
        int[][] maasto = peli.getVastustajanMaasto();
        for (int loop = 0; loop < 400; loop++) {
            if (maasto[loop / 20][loop % 20] == 2) { // poistaa laivat
                maasto[loop / 20][loop % 20] = 0;
            }
        }
        int[][] temp = new int[20][20];
        PiirtotaulukonLuoja ptl = new PiirtotaulukonLuoja(arpoja, maasto, temp);
        ll = new LaivanLuoja(arpoja, peli.getVastustajanMaasto(), ptl);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void LuodaanKolmeLaivaa() {
        HashMap<Integer, ArrayList<Integer>> laivat = new HashMap<>();
        ll.teeLaiva(laivat, 5, 0);
        ll.teeLaiva(laivat, 4, 1);
        ll.teeLaiva(laivat, 3, 2);
        assertEquals(3, laivat.size());
        assertEquals(10, laivat.get(0).size());
        assertEquals(8, laivat.get(1).size());
        assertEquals(6, laivat.get(2).size());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
