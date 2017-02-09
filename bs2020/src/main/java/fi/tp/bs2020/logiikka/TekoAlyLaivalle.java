package fi.tp.bs2020.logiikka;

import java.util.Random;


public class TekoAlyLaivalle {
    
    private int[][] maasto;
    private int mihinY, mihinX;
    private Random arpoja;
    
    public TekoAlyLaivalle(int[][] maasto, int mihinY, int mihinX, Random arpoja) {
        this.maasto = maasto;
        this.mihinY = mihinY;
        this.mihinX = mihinX;
        this.arpoja = arpoja;
    }
    
    public int ammutaanLaivaa() {
        boolean osumaHyvaksytty = false;
        int suunta = 0; //ei määr; 1 = vaakaan; 2 = pystyyn
        int dy = 0;
        int dx = 0;
        boolean eiVasemmalle = false;
        boolean eiOikealle = false;
        boolean eiYlos = false;
        boolean eiAlas = false;

        if (mihinX == 0) {
            eiVasemmalle = true;
        }
        if (mihinX == 19) {
            eiOikealle = true;
        }
        if (mihinY == 0) {
            eiYlos = true;
        }
        if (mihinY == 19) {
            eiAlas = true;
        }

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

        if (suunta == 1) { //vaakaan
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
                int suuntab = arpoja.nextInt(2); // 0 = vasen
                if (suuntab == 0) {
                    //ensin vasemmalle
                    if (mihinX - loop >= 0 && !eiVasemmalle) {
                        if (maasto[mihinY][mihinX - loop] < 20) {
                            dy = mihinY;
                            dx = mihinX - loop;
                            osumaHyvaksytty = true;
                        }
                    }
                    if (mihinX + loop <= 19 && !eiOikealle) {
                        if (maasto[mihinY][mihinX + loop] < 20) {
                            dy = mihinY;
                            dx = mihinX + loop;
                            osumaHyvaksytty = true;
                        }
                    }
                }
                if (suuntab == 1) {
                    //ensin oikealle
                    if (mihinX + loop <= 19 && !eiOikealle) {
                        if (maasto[mihinY][mihinX + loop] < 20) {
                            dy = mihinY;
                            dx = mihinX + loop;
                            osumaHyvaksytty = true;
                        }
                    }
                    if (mihinX - loop >= 0 && !eiVasemmalle) {
                        if (maasto[mihinY][mihinX - loop] < 20) {
                            dy = mihinY;
                            dx = mihinX - loop;
                            osumaHyvaksytty = true;
                        }
                    }
                }
                loop++;
            }
        }

        if (suunta == 2) { //pystyyn
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
                int suuntab = arpoja.nextInt(2);
                if (suuntab == 0) {
                    //ensin alas
                    if (mihinY - loop >= 0 && !eiYlos) {
                        if (maasto[mihinY - loop][mihinX] < 20) {
                            dy = mihinY - loop;
                            dx = mihinX;
                            osumaHyvaksytty = true;
                        }
                    }
                    if (mihinY + loop <= 19 && !eiAlas) {
                        if (maasto[mihinY + loop][mihinX] < 20) {
                            dy = mihinY + loop;
                            dx = mihinX;
                            osumaHyvaksytty = true;
                        }
                    }
                }
                if (suuntab == 1) {
                    //ensin ylös
                    if (mihinY + loop <= 19 && !eiAlas) {
                        if (maasto[mihinY + loop][mihinX] < 20) {
                            dy = mihinY + loop;
                            dx = mihinX;
                            osumaHyvaksytty = true;
                        }
                    }
                    if (mihinY - loop >= 0 && !eiYlos) {
                        if (maasto[mihinY - loop][mihinX] < 20) {
                            dy = mihinY - loop;
                            dx = mihinX;
                            osumaHyvaksytty = true;
                        }
                    }
                }
                loop++;
            }
        }

        while (!osumaHyvaksytty) { // eli suunta == 0; eli suunta on määrittelemätön:
            dx = mihinX;
            dy = mihinY;
            int suuntab = arpoja.nextInt(4);
            if (suuntab == 0 && !eiVasemmalle) {
                dx--;
                osumaHyvaksytty = true;
            }
            if (suuntab == 1 && !eiOikealle) {
                dx++;
                osumaHyvaksytty = true;
            }
            if (suuntab == 2 && !eiAlas) {
                dy++;
                osumaHyvaksytty = true;
            }
            if (suuntab == 3 && !eiYlos) {
                dy--;
                osumaHyvaksytty = true;
            }
        }
        return (dy * 20 + dx);
    }

}