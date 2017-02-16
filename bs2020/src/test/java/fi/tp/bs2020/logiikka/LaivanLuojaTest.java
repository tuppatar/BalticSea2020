/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
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

/**
 *
 * @author ttpatari
 */
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
        Peli peli = new Peli(arpoja, new Aanet());
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
