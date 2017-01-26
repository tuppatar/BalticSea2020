/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.balticsea2020.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tuomas Pätäri
 */
public class MaastonLuoja {

    private Random arpoja;
    private int[][] maasto, piirrettava;
    private Map<Integer, List<Integer>> laivat;
    
    public MaastonLuoja(Random arpoja) {
        this.arpoja = arpoja;
        this.maasto = new int[20][20];
        this.piirrettava = new int[20][20];
        laivat = new HashMap<>();
    }

    public int[][] getPiirrettava() {
        return piirrettava;
    }

    public Map<Integer, List<Integer>> getLaivat() {
        return laivat;
    }
    
    public int[][] luoVastustajanMaasto() {

        teeVesi();
        teeMaa();
        
        lisaaPiirrettavaanVesiJaMaa();
        
        teeLaiva(5, 0); // tämä metodi lisää tiedot myös piirrettävään, koska helpompaa samassa yhteydessä.
        teeLaiva(4, 1);
        teeLaiva(3, 2);
        teeLaiva(3, 3);
        teeLaiva(2, 4);                
        
        return maasto;
    }

    public int[][] luoPelaajanMaasto() {

        teeVesi();
        teeMaa();
        
        //teeLaiva(maasto, 5, arpoja);
        //teeLaiva(maasto, 4, arpoja);
        //teeLaiva(maasto, 3, arpoja);
        //teeLaiva(maasto, 3, arpoja);
        //teeLaiva(maasto, 2, arpoja);                
        
        return maasto;
    }

    private void teeVesi() {
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {
                maasto[a][b] = 0;
            }
        }
    }

    private void teeMaa() {
        int a = 0;
        while (a < 80) { // maapalojen lukumäärä
            int x = 0, y = 0;
            if (a == 0) {
                x = arpoja.nextInt(20);
                y = arpoja.nextInt(20);
            } else {
                int tod = arpoja.nextInt(15);
                if (tod == 0) {
                    x = arpoja.nextInt(20);
                    y = arpoja.nextInt(20);
                } else {
                    int tod2 = arpoja.nextInt(a);
                    int e = 0;
                    for (int d = 0; d < 20; d++) {
                        for (int b = 0; b < 20; b++) {
                            if (maasto[b][d] == 1) {
                                if (e == tod2) {
                                    x = b;
                                    y = d;
                                }
                                e++;
                            }
                        }
                    }
                    int tod3 = arpoja.nextInt(4);
                    if (tod3 == 0) {
                        x++;
                    } else if (tod3 == 1) {
                        x--;
                    } else if (tod3 == 2) {
                        y++;
                    } else if (tod3 == 3) {
                        y--;
                    }
                }
            }
            
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            if (x > 19) {
                x = 19;
            }
            if (y > 19) {
                y = 19;
            }
            
            if (maasto[x][y] != 1) {
                maasto[x][y] = 1;
                a++;
            }
            
        }
    }
    
    private void lisaaPiirrettavaanVesiJaMaa() {
        for (int a = 0; a < 20; a++) {
            for (int b = 0; b < 20; b++) {
                if (maasto[a][b] == 0) {
                    piirrettava[a][b] = 0;
                } else {
                    piirrettava[a][b] = 1;
                }
            }
        }
    }
    
    private void teeLaiva(int pituus, int avain) {
            
        boolean onnistus = false;
        while (!onnistus) {
            int suunta = arpoja.nextInt(2);
            if (suunta == 0) { //PYSTYYN
                int xx = arpoja.nextInt(20);
                int yy = arpoja.nextInt(20 - pituus);
                boolean alatee = false;
                for (int cc = 0; cc < pituus; cc++) {
                    if ((maasto[yy+cc][xx] == 1) || (maasto[yy+cc][xx] == 2)) {                  
                        alatee = true;
                    }
                    if (xx > 0) {
                        if (maasto[yy+cc][xx-1] == 2) {
                            alatee = true;
                        }
                    }
                    if (xx < 19) {
                        if (maasto[yy+cc][xx+1] == 2) {
                            alatee = true;
                        }
                    }
                }

                if (yy > 0) {
                    if (maasto[yy - 1][xx] == 2) {
                        alatee = true;
                    }
                }
                if ((yy + pituus) < 19) {
                    if (maasto[yy + pituus][xx] == 2) {
                        alatee = true;
                    }
                }

                if (!alatee) {
                    List koordinaatit = new ArrayList<>();
                    for (int cc = 0; cc < pituus; cc++) {
                        maasto[yy+cc][xx] = 2;
                        koordinaatit.add(yy+cc);
                        koordinaatit.add(xx);
                        if (cc == 0) {
                            piirrettava[yy+cc][xx] = 10; // PYSTYPÄÄTYPALA 10
                        } else if (cc == (pituus - 1)) {
                            piirrettava[yy+cc][xx] = 12; // PYSTYPÄÄTYPALA 12
                        } else {
                            piirrettava[yy+cc][xx] = 11; // PYSTYKESKIPALA 11
                        }
                    }
                    laivat.put(avain, koordinaatit);
                    onnistus = true;
                }
                    
                
            } else { // VAAKAAN
                int xx = arpoja.nextInt(20 - pituus);
                int yy = arpoja.nextInt(20);
                boolean alatee = false;
                for (int cc = 0; cc < pituus; cc++) {
                    if ((maasto[yy][xx+cc] == 1) || (maasto[yy][xx+cc] == 2)) {
                        alatee = true;
                    }
                    if (yy > 0) {
                        if (maasto[yy-1][xx+cc] == 2) {
                            alatee = true;
                        }
                    }
                    if (yy < 19) {
                        if (maasto[yy+1][xx+cc] == 2) {
                            alatee = true;
                        }
                    }
                }

                if (xx > 0) {
                    if (maasto[yy][xx - 1] == 2) {
                        alatee = true;
                    }
                }
                if ((xx + pituus) < 19) {
                    if (maasto[yy][xx + pituus] == 2) {
                        alatee = true;
                    }
                }

                if (!alatee) {
                    List koordinaatit = new ArrayList<Integer>();
                    for (int cc = 0; cc < pituus; cc++) {
                        maasto[yy][xx+cc] = 2;
                        koordinaatit.add(yy);
                        koordinaatit.add(xx+cc);
                        if (cc == 0) {
                            piirrettava[yy][xx+cc] = 13; // VAAKAPÄÄTYPALA 13
                        } else if (cc == (pituus - 1)) {
                            piirrettava[yy][xx+cc] = 15; // VAAKAPÄÄTYPALA 15
                        } else {
                            piirrettava[yy][xx+cc] = 14; // VAAKAKESKIPALA 14
                        }
                    }
                    laivat.put(avain, koordinaatit);
                    onnistus = true;
                }
            }
        }
            
    }

    public void debugDraw(int[][] maastod) {
        for (int aa = 0; aa < 20; aa++) {
            for (int b = 0; b < 20; b++) {
                int de = maastod[aa][b];
//                    System.out.print(de + " ");
                if (de == 0) {
                    System.out.print(".");
                } else if (de == 1) {
                    System.out.print("#");
                } else if (de == 2) {
                    System.out.print("O");
                } else if (de == 10) {
                    System.out.print("^");
                } else if (de == 11) {
                    System.out.print("|");
                } else if (de == 12) {
                    System.out.print("V");
                } else if (de == 13) {
                    System.out.print("<");
                } else if (de == 14) {
                    System.out.print("-");
                } else if (de == 15) {
                    System.out.print(">");
                }

                if (de == 30) {
                    System.out.print(".");
                } else if (de == 31) {
                    System.out.print("#");
                } else if (de == 32) {
                    System.out.print("O");
                } else if (de == 40) {
                    System.out.print("^");
                } else if (de == 41) {
                    System.out.print("|");
                } else if (de == 42) {
                    System.out.print("V");
                } else if (de == 43) {
                    System.out.print("<");
                } else if (de == 44) {
                    System.out.print("-");
                } else if (de == 45) {
                    System.out.print(">");
                }

            }
            System.out.println();
        }
    }
    
}
