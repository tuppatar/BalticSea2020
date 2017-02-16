package fi.tp.bs2020.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Kuvat tiedostoista MAP:piin lataava luokka.
 */
public class KuvanLataaja {

    private Map<Integer, BufferedImage> kuvat;

    /**
     * Kuvanlataajan konstruktori.
     */
    public KuvanLataaja() {
        this.kuvat = new HashMap<>();
        this.luoMappi();
    }

    public Map<Integer, BufferedImage> getKuvat() {
        return kuvat;
    }
    
    private void luoMappi() {
        kuvat.put(8, this.lataa("kuvat/empty.png"));
        kuvat.put(0, this.lataa("kuvat/meri.png"));
        kuvat.put(100, this.lataa("kuvat/meri_osuma.png"));
        kuvat.put(9, this.lataa("kuvat/talo.png"));
        kuvat.put(109, this.lataa("kuvat/talo_osuma.png"));
        //kuvat.put(1, this.lataa("kuvat/maa.png")); //1
        kuvat.put(10, this.lataa("kuvat/laiva_10.png"));
        kuvat.put(11, this.lataa("kuvat/laiva_11.png"));
        kuvat.put(12, this.lataa("kuvat/laiva_12.png"));
        kuvat.put(13, this.lataa("kuvat/laiva_13.png"));
        kuvat.put(14, this.lataa("kuvat/laiva_14.png"));
        kuvat.put(15, this.lataa("kuvat/laiva_15.png"));
        kuvat.put(16, this.lataa("kuvat/sukellusvene.png"));
        //kuvat.put(31, this.lataa("kuvat/maa_osuma.png")); //10
        kuvat.put(110, this.lataa("kuvat/laiva_10_osuma.png")); //11
        kuvat.put(111, this.lataa("kuvat/laiva_11_osuma.png")); //12
        kuvat.put(112, this.lataa("kuvat/laiva_12_osuma.png")); //13
        kuvat.put(113, this.lataa("kuvat/laiva_13_osuma.png")); //14
        kuvat.put(114, this.lataa("kuvat/laiva_14_osuma.png")); //15
        kuvat.put(115, this.lataa("kuvat/laiva_15_osuma.png")); //16
        kuvat.put(116, this.lataa("kuvat/sukellusvene_osuma.png")); //16
        
        kuvat.put(20, this.lataa("kuvat/hit.png")); //17
        kuvat.put(21, this.lataa("kuvat/hit.png")); //17
        kuvat.put(22, this.lataa("kuvat/hit.png")); //17
        kuvat.put(23, this.lataa("kuvat/hit.png")); //17
        kuvat.put(24, this.lataa("kuvat/hit.png")); //17
        kuvat.put(25, this.lataa("kuvat/hit.png")); //17
        
        kuvat.put(50, this.lataa("kuvat/maaperus_1.png"));
        kuvat.put(51, this.lataa("kuvat/maaperus_2.png"));
        kuvat.put(52, this.lataa("kuvat/maaperus_3.png"));
        kuvat.put(53, this.lataa("kuvat/maaperus_4.png"));
        kuvat.put(60, this.lataa("kuvat/maa_1_meriylhaalla.png"));
        kuvat.put(62, this.lataa("kuvat/maa_1_merioikealla.png"));
        kuvat.put(64, this.lataa("kuvat/maa_1_merialhaalla.png"));
        kuvat.put(66, this.lataa("kuvat/maa_1_merivasemmalla.png"));
        kuvat.put(70, this.lataa("kuvat/maa_1_meriylhaalla_ja_oikealla.png"));
        kuvat.put(72, this.lataa("kuvat/maa_1_merialhaalla_ja_oikealla.png"));
        kuvat.put(74, this.lataa("kuvat/maa_1_merialhaalla_ja_vasemmalla.png"));
        kuvat.put(76, this.lataa("kuvat/maa_1_meriylhaalla_ja_vasemmalla.png"));
        kuvat.put(80, this.lataa("kuvat/maa_1_maaylhaalla.png"));
        kuvat.put(82, this.lataa("kuvat/maa_1_maaoikealla.png"));
        kuvat.put(84, this.lataa("kuvat/maa_1_maaalhaalla.png"));
        kuvat.put(86, this.lataa("kuvat/maa_1_maavasemmalla.png"));
        kuvat.put(90, this.lataa("kuvat/maa_1_saari.png"));
        kuvat.put(92, this.lataa("kuvat/maa_1_merioikealla_ja_vasemmalla.png"));
        kuvat.put(94, this.lataa("kuvat/maa_1_merialhaalla_ja_ylhaalla.png"));
        
        kuvat.put(150, this.lataa("kuvat/maaperus_1_osuma.png"));
        kuvat.put(151, this.lataa("kuvat/maaperus_2_osuma.png"));
        kuvat.put(152, this.lataa("kuvat/maaperus_3_osuma.png"));
        kuvat.put(153, this.lataa("kuvat/maaperus_4_osuma.png"));
        kuvat.put(160, this.lataa("kuvat/maa_1_meriylhaalla_osuma.png"));
        kuvat.put(162, this.lataa("kuvat/maa_1_merioikealla_osuma.png"));
        kuvat.put(164, this.lataa("kuvat/maa_1_merialhaalla_osuma.png"));
        kuvat.put(166, this.lataa("kuvat/maa_1_merivasemmalla_osuma.png"));
        kuvat.put(170, this.lataa("kuvat/maa_1_meriylhaalla_ja_oikealla_osuma.png"));
        kuvat.put(172, this.lataa("kuvat/maa_1_merialhaalla_ja_oikealla_osuma.png"));
        kuvat.put(174, this.lataa("kuvat/maa_1_merialhaalla_ja_vasemmalla_osuma.png"));
        kuvat.put(176, this.lataa("kuvat/maa_1_meriylhaalla_ja_vasemmalla_osuma.png"));
        kuvat.put(180, this.lataa("kuvat/maa_1_maaylhaalla_osuma.png"));
        kuvat.put(182, this.lataa("kuvat/maa_1_maaoikealla_osuma.png"));
        kuvat.put(184, this.lataa("kuvat/maa_1_maaalhaalla_osuma.png"));
        kuvat.put(186, this.lataa("kuvat/maa_1_maavasemmalla_osuma.png"));
        kuvat.put(190, this.lataa("kuvat/maa_1_saari_osuma.png"));
        kuvat.put(192, this.lataa("kuvat/maa_1_merioikealla_ja_vasemmalla_osuma.png"));
        kuvat.put(194, this.lataa("kuvat/maa_1_merialhaalla_ja_ylhaalla_osuma.png"));

        kuvat.put(60 + 1, this.lataa("kuvat/maa_2_meriylhaalla.png"));
        kuvat.put(62 + 1, this.lataa("kuvat/maa_2_merioikealla.png"));
        kuvat.put(64 + 1, this.lataa("kuvat/maa_2_merialhaalla.png"));
        kuvat.put(66 + 1, this.lataa("kuvat/maa_2_merivasemmalla.png"));
        kuvat.put(70 + 1, this.lataa("kuvat/maa_2_meriylhaalla_ja_oikealla.png"));
        kuvat.put(72 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_oikealla.png"));
        kuvat.put(74 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_vasemmalla.png"));
        kuvat.put(76 + 1, this.lataa("kuvat/maa_2_meriylhaalla_ja_vasemmalla.png"));
        kuvat.put(80 + 1, this.lataa("kuvat/maa_2_maaylhaalla.png"));
        kuvat.put(82 + 1, this.lataa("kuvat/maa_2_maaoikealla.png"));
        kuvat.put(84 + 1, this.lataa("kuvat/maa_2_maaalhaalla.png"));
        kuvat.put(86 + 1, this.lataa("kuvat/maa_2_maavasemmalla.png"));
        kuvat.put(90 + 1, this.lataa("kuvat/maa_2_saari.png"));
        kuvat.put(92 + 1, this.lataa("kuvat/maa_2_merioikealla_ja_vasemmalla.png"));
        kuvat.put(94 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_ylhaalla.png"));
        
        kuvat.put(160 + 1, this.lataa("kuvat/maa_2_meriylhaalla_osuma.png"));
        kuvat.put(162 + 1, this.lataa("kuvat/maa_2_merioikealla_osuma.png"));
        kuvat.put(164 + 1, this.lataa("kuvat/maa_2_merialhaalla_osuma.png"));
        kuvat.put(166 + 1, this.lataa("kuvat/maa_2_merivasemmalla_osuma.png"));
        kuvat.put(170 + 1, this.lataa("kuvat/maa_2_meriylhaalla_ja_oikealla_osuma.png"));
        kuvat.put(172 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_oikealla_osuma.png"));
        kuvat.put(174 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_vasemmalla_osuma.png"));
        kuvat.put(176 + 1, this.lataa("kuvat/maa_2_meriylhaalla_ja_vasemmalla_osuma.png"));
        kuvat.put(180 + 1, this.lataa("kuvat/maa_2_maaylhaalla_osuma.png"));
        kuvat.put(182 + 1, this.lataa("kuvat/maa_2_maaoikealla_osuma.png"));
        kuvat.put(184 + 1, this.lataa("kuvat/maa_2_maaalhaalla_osuma.png"));
        kuvat.put(186 + 1, this.lataa("kuvat/maa_2_maavasemmalla_osuma.png"));
        kuvat.put(190 + 1, this.lataa("kuvat/maa_2_saari_osuma.png"));
        kuvat.put(192 + 1, this.lataa("kuvat/maa_2_merioikealla_ja_vasemmalla_osuma.png"));
        kuvat.put(194 + 1, this.lataa("kuvat/maa_2_merialhaalla_ja_ylhaalla_osuma.png"));
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
