package fi.tp.bs2020.logiikka;

import java.util.Random;

/**
 * Tätä tekoälyä käytetään, kun laivan pommitus on kesken ja näin ollen täytyy ampua laivan viereen.
 * Tekoäly ampuu järkevästi ja poissulkee mahdottomat vaihtoehdot.
 * @author Tuomas Pätäri
 */
public class TekoAlyLaivalle {
    
    private int[][] maasto;
    private int mihinY, mihinX;
    private Random arpoja;
    int dy = 0, dx = 0, suunta = 0; //ei määr; 1 = vaakaan; 2 = pystyyn
    boolean osumaHyvaksytty = false, eiVasemmalle = false, eiOikealle = false, eiYlos = false, eiAlas = false;
    
    public TekoAlyLaivalle(int[][] maasto, int mihinY, int mihinX, Random arpoja) {
        this.maasto = maasto;
        this.mihinY = mihinY;
        this.mihinX = mihinX;
        this.arpoja = arpoja;
    }
    
    private void tarkistaReunat() {
        if (mihinX == 0) {
            eiVasemmalle = true;
        } else if (mihinX == 19) {
            eiOikealle = true;
        }
        if (mihinY == 0) {
            eiYlos = true;
        } else if (mihinY == 19) {
            eiAlas = true;
        }
    }
    
    private void paatteleSuunta() {
        if (!eiVasemmalle) {
            if (maasto[mihinY][mihinX - 1] == 30 || maasto[mihinY][mihinX - 1] == 31) {
                eiVasemmalle = true;
            } else if (maasto[mihinY][mihinX - 1] >= 32) {
                eiYlos = true;
                eiAlas = true;
                suunta = 1; // vaakaan
            }
        }
        if (!eiOikealle) {
            if (maasto[mihinY][mihinX + 1] == 30 || maasto[mihinY][mihinX + 1] == 31) {
                eiOikealle = true;
            } else if (maasto[mihinY][mihinX + 1] >= 32) {
                eiYlos = true;
                eiAlas = true;
                suunta = 1; // vaakaan
            }
        }
        if (!eiAlas) {
            if (maasto[mihinY + 1][mihinX] == 30 || maasto[mihinY + 1][mihinX] == 31) {
                eiAlas = true;
            } else if (maasto[mihinY + 1][mihinX] >= 32) {
                eiOikealle = true;
                eiVasemmalle = true;
                suunta = 2; // pystyyn
            }
        }
        if (!eiYlos) {
            if (maasto[mihinY - 1][mihinX] == 30 || maasto[mihinY - 1][mihinX] == 31) {
                eiYlos = true;
            } else if (maasto[mihinY - 1][mihinX] >= 32) {
                eiOikealle = true;
                eiVasemmalle = true;
                suunta = 2; // pystyyn
            }
        }
    }
    
    private void ammuVasemmalle(int loop) {
        if (maasto[mihinY][mihinX - loop] < 20) {
            dy = mihinY;
            dx = mihinX - loop;
            osumaHyvaksytty = true;
        }
    }
    
    private void ammuOikealle(int loop) {
        if (maasto[mihinY][mihinX + loop] < 20) {
            dy = mihinY;
            dx = mihinX + loop;
            osumaHyvaksytty = true;
        }
    }
    
    private void ammuVaakaan() {
        int loop = 1;
        while (!osumaHyvaksytty) {
            if (mihinX - loop >= 0) {
                if (maasto[mihinY][mihinX - loop] == 30 || maasto[mihinY][mihinX - loop] == 31) {
                    eiVasemmalle = true;
                }
            }
            if (mihinX + loop <= 19) {
                if (maasto[mihinY][mihinX + loop] == 30 || maasto[mihinY][mihinX + loop] == 31) {
                    eiOikealle = true;
                }
            }
            int suuntab = arpoja.nextInt(2); // 0 = kokeillaan ensin vasemmalle, 1 = ensin oikealle
            if (suuntab == 0) {
                if (mihinX - loop >= 0 && !eiVasemmalle) {
                    ammuVasemmalle(loop);
                }
                if (mihinX + loop <= 19 && !eiOikealle) {
                    ammuOikealle(loop);
                }
            } else if (suuntab == 1) {
                if (mihinX + loop <= 19 && !eiOikealle) {
                    ammuOikealle(loop);
                }
                if (mihinX - loop >= 0 && !eiVasemmalle) {
                    ammuVasemmalle(loop);
                }
            }
            loop++;
        }
    }
    
    private void ammuAlas(int loop) {
        if (maasto[mihinY - loop][mihinX] < 20) {
            dy = mihinY - loop;
            dx = mihinX;
            osumaHyvaksytty = true;
        }
    }
    
    private void ammuYlos(int loop) {
        if (maasto[mihinY + loop][mihinX] < 20) {
            dy = mihinY + loop;
            dx = mihinX;
            osumaHyvaksytty = true;
        }
    }
    
    private void ammuPystyyn() {
        int loop = 1;
        while (!osumaHyvaksytty) {
            if (mihinY - loop >= 0) {
                if (maasto[mihinY - loop][mihinX] == 30 || maasto[mihinY - loop][mihinX] == 31) {
                    eiYlos = true;
                }
            }
            if (mihinY + loop <= 19) {
                if (maasto[mihinY + loop][mihinX] == 30 || maasto[mihinY + loop][mihinX] == 31) {
                    eiAlas = true;
                }
            }                    
            int suuntab = arpoja.nextInt(2); // 0 = kokeillaan ensin alas, 1 = ensin ylös
            if (suuntab == 0) {
                if (mihinY - loop >= 0 && !eiYlos) {
                    ammuAlas(loop);
                }
                if (mihinY + loop <= 19 && !eiAlas) {
                    ammuYlos(loop);
                }
            } else if (suuntab == 1) {
                if (mihinY + loop <= 19 && !eiAlas) {
                    ammuYlos(loop);
                }
                if (mihinY - loop >= 0 && !eiYlos) {
                    ammuAlas(loop);
                }
            }
            loop++;
        }
    }
    
    private void ammuSatunnaisesti() {
        dx = mihinX;
        dy = mihinY;
        int suuntab = arpoja.nextInt(4);
        if (suuntab == 0 && !eiVasemmalle) {
            dx--;
        } else if (suuntab == 1 && !eiOikealle) {
            dx++;
        } else if (suuntab == 2 && !eiAlas) {
            dy++;
        } else if (suuntab == 3 && !eiYlos) {
            dy--;
        }
        if (dy != mihinY || dx != mihinX) {
            osumaHyvaksytty = true;
        }
    }
/**
 * Aluksi tarkistetaan alueen reunat, sitten päätellään järkevä ampumissuunta jos mahdollista ja
 * lopuksi ammutaan.
 * @return Paikka johon ammutaan.
 */    
    public int ammutaanLaivaa() {
        tarkistaReunat();
        paatteleSuunta();
        if (suunta == 1) { //vaakaan
            ammuVaakaan();
        } else if (suunta == 2) { //pystyyn
            ammuPystyyn();
        }
        while (!osumaHyvaksytty) { // eli suunta == 0; eli suunta on määrittelemätön:
            ammuSatunnaisesti();
        }
        return (dy * 20 + dx);
    }

}