package fi.tp.bs2020.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Kuvat tiedostoista MAP:piin lataava luokka.
 */
public class KuvanLataaja {

    private Map<Integer, BufferedImage> kuvat, menukuvat;

    public KuvanLataaja() {
        this.kuvat = new HashMap<>();
        this.menukuvat = new HashMap<>();
        this.luoMapit();
    }

    public Map<Integer, BufferedImage> getKuvat() {
        return kuvat;
    }
    
    public Map<Integer, BufferedImage> getMenukuvat() {
        return menukuvat;
    }
    
    private void luoMapit() {
        kuvat.put(8, this.lataa("kuvat/empty.png"));
        kuvat.put(0, this.lataa("kuvat/meri.png"));
        kuvat.put(100, this.lataa("kuvat/meri_osuma.png"));
        kuvat.put(9, this.lataa("kuvat/talo.png"));
        kuvat.put(109, this.lataa("kuvat/talo_osuma.png"));
        
        kuvat.put(10, this.lataa("kuvat/laiva_10.png"));
        kuvat.put(11, this.lataa("kuvat/laiva_11.png"));
        kuvat.put(12, this.lataa("kuvat/laiva_12.png"));
        kuvat.put(13, this.lataa("kuvat/laiva_13.png"));
        kuvat.put(14, this.lataa("kuvat/laiva_14.png"));
        kuvat.put(15, this.lataa("kuvat/laiva_15.png"));
        kuvat.put(16, this.lataa("kuvat/sukellusvene.png"));
        
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
        
        menukuvat.put(0, this.lataa("kuvat/menu1.png"));
        menukuvat.put(1, this.lataa("kuvat/menu2.png"));
        menukuvat.put(2, this.lataa("kuvat/menu3.png"));
        menukuvat.put(3, this.lataa("kuvat/menu4.png"));
        menukuvat.put(4, this.lataa("kuvat/menu5.png"));
        menukuvat.put(5, this.lataa("kuvat/menu6.png"));
        menukuvat.put(6, this.lataa("kuvat/menu7.png"));

        menukuvat.put(10, this.lataa("kuvat/menu_uusi_off.png"));
        menukuvat.put(11, this.lataa("kuvat/menu_flag_svefif_off.png"));
        menukuvat.put(12, this.lataa("kuvat/menu_flag_svefif_off.png"));
        menukuvat.put(13, this.lataa("kuvat/menu_vahan_off.png"));
        menukuvat.put(14, this.lataa("kuvat/menu_pieni_off.png"));
        menukuvat.put(15, this.lataa("kuvat/menu_paalla_off.png"));
        menukuvat.put(16, this.lataa("kuvat/menu_paalla_off.png"));

        menukuvat.put(20, this.lataa("kuvat/menu_pois_off.png"));
        menukuvat.put(21, this.lataa("kuvat/menu_flag_come_off.png"));
        menukuvat.put(22, this.lataa("kuvat/menu_flag_come_off.png"));
        menukuvat.put(23, this.lataa("kuvat/menu_keski_off.png"));
        menukuvat.put(24, this.lataa("kuvat/menu_keski_off.png"));
        menukuvat.put(25, this.lataa("kuvat/menu_pois_off.png"));
        menukuvat.put(26, this.lataa("kuvat/menu_pois_off.png"));

        menukuvat.put(31, this.lataa("kuvat/menu_flag_nato_off.png"));
        menukuvat.put(32, this.lataa("kuvat/menu_flag_nato_off.png"));
        menukuvat.put(33, this.lataa("kuvat/menu_paljon_off.png"));
        menukuvat.put(34, this.lataa("kuvat/menu_suuri_off.png"));

        menukuvat.put(41, this.lataa("kuvat/menu_flag_japan_off.png"));
        menukuvat.put(42, this.lataa("kuvat/menu_flag_japan_off.png"));

        menukuvat.put(10 + 100, this.lataa("kuvat/menu_uusi_on.png"));
        menukuvat.put(11 + 100, this.lataa("kuvat/menu_flag_svefif_on.png"));
        menukuvat.put(12 + 100, this.lataa("kuvat/menu_flag_svefif_on.png"));
        menukuvat.put(13 + 100, this.lataa("kuvat/menu_vahan_on.png"));
        menukuvat.put(14 + 100, this.lataa("kuvat/menu_pieni_on.png"));
        menukuvat.put(15 + 100, this.lataa("kuvat/menu_paalla_on.png"));
        menukuvat.put(16 + 100, this.lataa("kuvat/menu_paalla_on.png"));

        menukuvat.put(20 + 100, this.lataa("kuvat/menu_pois_on.png"));
        menukuvat.put(21 + 100, this.lataa("kuvat/menu_flag_come_on.png"));
        menukuvat.put(22 + 100, this.lataa("kuvat/menu_flag_come_on.png"));
        menukuvat.put(23 + 100, this.lataa("kuvat/menu_keski_on.png"));
        menukuvat.put(24 + 100, this.lataa("kuvat/menu_keski_on.png"));
        menukuvat.put(25 + 100, this.lataa("kuvat/menu_pois_on.png"));
        menukuvat.put(26 + 100, this.lataa("kuvat/menu_pois_on.png"));

        menukuvat.put(31 + 100, this.lataa("kuvat/menu_flag_nato_on.png"));
        menukuvat.put(32 + 100, this.lataa("kuvat/menu_flag_nato_on.png"));
        menukuvat.put(33 + 100, this.lataa("kuvat/menu_paljon_on.png"));
        menukuvat.put(34 + 100, this.lataa("kuvat/menu_suuri_on.png"));

        menukuvat.put(41 + 100, this.lataa("kuvat/menu_flag_japan_on.png"));
        menukuvat.put(42 + 100, this.lataa("kuvat/menu_flag_japan_on.png"));
    }
    
    private BufferedImage lataa(String tiedosto) {
        BufferedImage palautus = null;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(tiedosto);
            palautus = ImageIO.read(is);
        } catch (IOException ex) {
            // handle exception...
        }
        return palautus;
    }
    
}
