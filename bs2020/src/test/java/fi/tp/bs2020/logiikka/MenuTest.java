/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

import fi.tp.bs2020.gui.Soittaja;
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
public class MenuTest {
    
    Menu menu;
    PeliMuuttujat moodi;
    Soittaja soittaja;
    Random arpoja;
    
    public MenuTest() {
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
        this.moodi = new PeliMuuttujat();
        moodi.setElementit(arpoja.nextInt(2));
        moodi.setHajanaisuus(arpoja.nextInt(2));
        moodi.asetaMuuttujatPeliaVarten();
        this.soittaja = new Soittaja(arpoja, moodi);
        this.menu = new Menu(moodi, soittaja);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void liikutaJaKatso() {
        menu.lisaaX();
        menu.lisaaY();
        menu.vahennaX();
        menu.vahennaY();
        assertEquals(0, menu.getX());
        assertEquals(0, menu.getY());
        menu.vahennaY();
        menu.vahennaX();
        menu.vahennaX();
        assertEquals(0, menu.getX());
        assertEquals(6, menu.getY());
        menu.vahennaY();
        menu.vahennaY();
        menu.vahennaY();
        menu.vahennaY();
        menu.lisaaX();
        menu.lisaaX();
        menu.lisaaX();
        menu.lisaaX();
        assertEquals(0, menu.getX());
        assertEquals(2, menu.getY());
        menu.lisaaY();
        menu.lisaaY();
        menu.lisaaY();
        menu.lisaaY();
        menu.lisaaY();
        assertEquals(0, menu.getX());
        assertEquals(0, menu.getY());
    }
    
    @Test
    public void valinnatPalautuu() {
        final Integer[] valinnat = {2, 4, 4, 3, 3, 2, 2};
        assertArrayEquals(valinnat, menu.getValinnat());
    }
    
    @Test
    public void enterPalauttaaOikein() {
        assertEquals(1, menu.menuEnter());
        menu.lisaaX();
        assertEquals(2, menu.menuEnter());
        menu.lisaaY();
        assertEquals(0, menu.menuEnter());
    }
    
    @Test
    public void enterMuuttaaArvoja() {
        menu.lisaaY();
        menu.menuEnter();
        assertEquals(0, moodi.getPelaaja());
        menu.lisaaX();
        menu.lisaaX();
        menu.menuEnter();
        assertEquals(2, moodi.getPelaaja());
        menu.lisaaY();
        menu.menuEnter();
        assertEquals(1, moodi.getVastustaja());
        menu.lisaaX();
        menu.menuEnter();
        assertEquals(3, moodi.getVastustaja());
        menu.lisaaY();
        menu.menuEnter();
        assertEquals(2, moodi.getElementit());
        menu.vahennaX();
        menu.menuEnter();
        assertEquals(1, moodi.getElementit());
        menu.lisaaY();
        menu.menuEnter();
        assertEquals(1, moodi.getHajanaisuus());
        menu.menuEnter();
        assertEquals(1, moodi.getHajanaisuus());
        menu.lisaaY();
        menu.menuEnter();
        assertFalse(moodi.isMusaOn());
        menu.lisaaY();
        menu.menuEnter();
        assertFalse(moodi.isAanetOn());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
