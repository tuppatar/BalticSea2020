package fi.tp.bs2020.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;


public class KuvanLataaja {

    private Map<Integer, BufferedImage> kuvat;

    public KuvanLataaja() {
        this.kuvat = new HashMap<>();
        this.luoMappi();
    }

    public Map<Integer, BufferedImage> getKuvat() {
        return kuvat;
    }
    
    private void luoMappi() {
        kuvat.put(0, this.lataa("meri.png"));
        kuvat.put(1, this.lataa("maa.png")); //1
        kuvat.put(10, this.lataa("laiva_10.png")); //2
        kuvat.put(11, this.lataa("laiva_11.png")); //3
        kuvat.put(12, this.lataa("laiva_12.png")); //4
        kuvat.put(13, this.lataa("laiva_13.png")); //5
        kuvat.put(14, this.lataa("laiva_14.png")); //6
        kuvat.put(15, this.lataa("laiva_15.png")); //7
        kuvat.put(8, this.lataa("empty.png")); //8
        kuvat.put(30, this.lataa("meri_osuma.png")); //9
        kuvat.put(31, this.lataa("maa_osuma.png")); //10
        kuvat.put(40, this.lataa("laiva_10_osuma.png")); //11
        kuvat.put(41, this.lataa("laiva_11_osuma.png")); //12
        kuvat.put(42, this.lataa("laiva_12_osuma.png")); //13
        kuvat.put(43, this.lataa("laiva_13_osuma.png")); //14
        kuvat.put(44, this.lataa("laiva_14_osuma.png")); //15
        kuvat.put(45, this.lataa("laiva_15_osuma.png")); //16
        kuvat.put(20, this.lataa("hit.png")); //17
        kuvat.put(21, this.lataa("hit.png")); //17
        kuvat.put(22, this.lataa("hit.png")); //17
        kuvat.put(23, this.lataa("hit.png")); //17
        kuvat.put(24, this.lataa("hit.png")); //17
        kuvat.put(25, this.lataa("hit.png")); //17
    }
    
    private BufferedImage lataa(String tiedosto) {
        BufferedImage palautus = null;
        try {                
          palautus = ImageIO.read(new File(tiedosto));
       } catch (IOException ex) {
            // handle exception...
       }
        return palautus;
    }
    
}
