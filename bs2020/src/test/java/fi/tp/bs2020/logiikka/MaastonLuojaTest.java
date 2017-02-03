/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020.logiikka;

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
 * @author Hate Halon
 */
public class MaastonLuojaTest {
    
    MaastonLuoja ml;
    
    public MaastonLuojaTest() {
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
        ml = new MaastonLuoja(arpoja);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void LuodussaMaastossaMaapalojaOn80() {
        int[][] palautus = ml.luoVastustajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] == 1) {
                a++;
            }
        }
        assertEquals(80, a);
    }
    
    @Test
    public void LuodussaPiirtotaulukossaMaapalojaOn80() {
        int[][] palautus = ml.luoVastustajanMaasto();
        int[][] palautus2 = ml.getPiirrettava();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus2[b / 20][b % 20] == 1) {
                a++;
            }
        }
        assertEquals(80, a);
    }
    
    @Test
    public void LuodussaMaastossaLaivapalojaOn17() {
        int[][] palautus = ml.luoVastustajanMaasto();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus[b / 20][b % 20] == 2) {
                a++;
            }
        }
        assertEquals(17, a);
    }

    @Test
    public void LuodussaPiirtotaulukossaLaivapalojaOn17() {
        int[][] palautus = ml.luoVastustajanMaasto();
        int[][] palautus2 = ml.getPiirrettava();
        int a = 0;
        for (int b = 0; b < 400; b++) {
            if (palautus2[b / 20][b % 20] >= 10) {
                a++;
            }
        }
        assertEquals(17, a);
    }

    @Test
    public void LaivaPalatSijoittuvatSaantojenMukaisesti() { // eli ei vierekkäin tai peräkkäin, jolloin vierekkäisiä paloja on yhteensä 12.
        int[][] palautus = ml.luoVastustajanMaasto();
        int summa = 0;
        for (int y = 0; y < 20; y++) { // testaus toiseen suuntaan
            int edellinen = 0;
            for (int x = 0; x < 20; x++) {
                if (palautus[y][x] == 2) {
                    if (edellinen == 1) {
                        summa++;
                    }
                    edellinen = 1;
                } else {
                    edellinen = 0;
                }
            }
        }
        for (int x = 0; x < 20; x++) { // ja sitten toiseen
            int edellinen = 0;
            for (int y = 0; y < 20; y++) {
                if (palautus[y][x] == 2) {
                    if (edellinen == 1) {
                        summa++;
                    }
                    edellinen = 1;
                } else {
                    edellinen = 0;
                }
            }
        }
        assertEquals(12, summa);
    }

    @Test
    public void laivaKoordinaattejaPalautuu34() {
        int summa = 0;
        int[][] palautus = ml.luoVastustajanMaasto();
        Map<Integer, List<Integer>> palautusXY = ml.getLaivat();
        for (int hoo: palautusXY.keySet()) {
            summa += palautusXY.get(hoo).size();
        }
        assertEquals(34, summa);
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
