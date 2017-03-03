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
 * @author ttpatari
 */
public class PeliRunkoTest {
    
    Random arpoja;
    PeliRunko pelirunko;
    
    public PeliRunkoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        arpoja = new Random();
        pelirunko = new PeliRunko(arpoja);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tilanneAsettuuJaLukeutuu() {
        pelirunko.setPeliTilanne(2);
        assertEquals(2, pelirunko.getPeliTilanne());
    }
    
    @Test
    public void peliEtenee() {
        pelirunko.uusiPeli();
        assertEquals(0, pelirunko.getPeliTilanne());
        PeliMuuttujat moodi = pelirunko.getMoodi();
        assertEquals(0, moodi.getPelaaja());
        assertEquals(1, moodi.getVastustaja());
        assertEquals(2, moodi.getElementit());
        assertEquals(0, moodi.getHajanaisuus());
        pelirunko.peliSiirto();
        assertEquals(0, pelirunko.getPeliTilanne());
        assertEquals(0, pelirunko.tarkistaVoitto());
        int paluuviesti = pelirunko.getOmaViesti();
        boolean totuus = false;
        if (paluuviesti >= 0 && paluuviesti <= 10) {
            totuus = true;
        }
        assertTrue(totuus);
        paluuviesti = pelirunko.getVastustajanviesti();
        totuus = false;
        if (paluuviesti >= 0 && paluuviesti <= 10) {
            totuus = true;
        }
        assertTrue(totuus);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
