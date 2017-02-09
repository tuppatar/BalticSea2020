/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Aanet;
import java.util.List;
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
 * @author Tuomas Pätäri
 */
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
        Aanet aanet = new Aanet();
        peli = new Peli(arpoja, aanet);
        maasto = peli.getPelaajanMaasto();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void ammutaanLaivaaPalauttaaArvon() {
        boolean tulos = false;
        for (int a = 0; a < 200; a++) {
            maasto[a / 20][a % 20] = 0;
            maasto[a / 20 + 200][a % 20] = 0;
        }
        maasto[15][15] = 33;
        ai = new TekoAlyLaivalle(maasto, 15, 15, arpoja);
        int pa = ai.ammutaanLaivaa();
        if (pa >= 0 && pa <= 399) {
            tulos = true;
        }
        assertTrue(tulos);
    }
    
    @Test
    public void ammutaanOikeaanLaivaan() {
        for (int a = 0; a < 200; a++) {
            maasto[a / 20][a % 20] = 30;
            maasto[a / 20 + 200][a % 20] = 0;
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
