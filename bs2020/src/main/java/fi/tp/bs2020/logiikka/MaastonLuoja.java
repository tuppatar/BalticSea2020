package fi.tp.bs2020.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Luokka luo maaston siten, että saarista tulee järkevän näköisiä kokonaisuuksia.
 */
public class MaastonLuoja {

    private Random arpoja;
    private int[][] maasto, piirrettava, maastonSatunnaisuus;
    private Map<Integer, List<Integer>> laivat;
    private PiirtotaulukonLuoja ptl;
    
    /**
     * Konstruktori.
     * @param arpoja    Random-olio. 
     */
    public MaastonLuoja(Random arpoja) {
        this.arpoja = arpoja;
        this.maasto = new int[20][20];
        this.piirrettava = new int[20][20];
        laivat = new HashMap<>();
        ptl = new PiirtotaulukonLuoja(arpoja, maasto, piirrettava);
        this.maastonSatunnaisuus = new int[20][20];
    }

    public int[][] getPiirrettava() {
        return piirrettava;
    }

    public int[][] getMaastonSatunnaisuus() {
        return maastonSatunnaisuus;
    }

    public Map<Integer, List<Integer>> getLaivat() {
        return laivat;
    }
/**
 * Tässä luodaan vastustajan maasto, toistaiseksi vielä pelaajankin.
 * @return vastustajan maasto.
 */    
    public int[][] luoVastustajanMaasto() {
        boolean uudelleen = true;
        //ptl
        while (uudelleen) {
            teeVesi();
            teeMaa(80);
            ptl.lisaaVesiJaMaa();
            maastonSatunnaisuus = ptl.getMaastonSatunnaisuus();
            if (teeTalot(3)) {
                uudelleen = false;
            }
        }
        LaivanLuoja ll = new LaivanLuoja(arpoja, maasto, ptl);
        ll.teeLaiva(laivat, 5, 0);
        ll.teeLaiva(laivat, 4, 1);
        ll.teeLaiva(laivat, 3, 2);
        ll.teeLaiva(laivat, 3, 3);
        ll.teeLaiva(laivat, 2, 4);
        ll.teeLaiva(laivat, 1, 5);
        return maasto;
    }
/**
 * EI KÄYTÖSSÄ.
 * @return maasto.
 */
    public int[][] luoPelaajanMaasto() {
        return maasto;
    }
/**
 * Alustetaan maasto (vedeksi).
 */
    private void teeVesi() {
        for (int a = 0; a < 400; a++) {
            maasto[a / 20][a % 20] = 0;
        }
    }
/**
 * Luodaan maastoon 80 maapalaa järkeviksi saariksi.
 */
    private void teeMaa(int maapaloja) {
        int a = 0;
        while (a < maapaloja) {
            int x = 0, y = 0;
            int tod = arpoja.nextInt(15);
            if (tod == 0 || a == 0) {
                x = arpoja.nextInt(20);
                y = arpoja.nextInt(20);
            } else {
                int tod2 = arpoja.nextInt(a);
                int e = 0;
                for (int loop = 0; loop < 400; loop++) {
                    if (maasto[loop / 20][loop % 20] == 1 && e == tod2) {
                        x = loop % 20;
                        y = loop / 20;
                    }
                    if (maasto[loop / 20][loop % 20] == 1) {
                        e++;
                    }
                }
                int tod3 = arpoja.nextInt(4);
                if (tod3 == 0 && x < 19) {
                    x++;
                } else if (tod3 == 1 && x > 0) {
                    x--;
                } else if (tod3 == 2 && y < 19) {
                    y++;
                } else if (tod3 == 3 && y > 0) {
                    y--;
                }
            }
            if (maasto[y][x] != 1) {
                maasto[y][x] = 1;
                a++;
            }
        }
    }
/**
 * PRIVATE. Tarkistetaan että talopaikka on sisämaassa.
 * @return True jos talopaikka oli sisämaassa.
 */    
    private boolean tarkistaTalopaikanSopivuus(int y, int x) {
        if (maasto[y][x] != 1) {
            return false;
        }
        if (y > 0) {
            if (maasto[y - 1][x] != 1) {
                return false;
            }
        }
        if (y < 19) {
            if (maasto[y + 1][x] != 1) {
                return false;
            }
        }
        if (x > 0) {
            if (maasto[y][x - 1] != 1) {
                return false;
            }
        }
        if (x < 19) {
            if (maasto[y][x + 1] != 1) {
                return false;
            }
        }
        return true;
    }
    /**
     * Tekee talot saarille. Ei sellaisiin paikkoihin, joissa on meripala vieressä.
     * @param taloja talojen määrä.
     * @return saatiinko talot sijoitettua maastoon.
     */
    private boolean teeTalot(int taloja) {
        List<Integer> sopivat = new ArrayList<>();
        List<Integer> paikat = new ArrayList<>();
        for (int loop = 0; loop < 400; loop++) {
            if (tarkistaTalopaikanSopivuus(loop / 20, loop % 20)) {
                sopivat.add(loop);
            }
        }
        if (sopivat.size() < taloja) {
            return false;
        }
        while (paikat.size() < taloja) {
            int arvottu = sopivat.get(arpoja.nextInt(sopivat.size()));
            if (!paikat.contains(arvottu)) {
                paikat.add(arvottu);
                maasto[arvottu / 20][arvottu % 20] = 3;
                ptl.lisaaTalo(arvottu / 20, arvottu % 20);
            }
        }
        return true;
    }

}
