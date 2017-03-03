package fi.tp.bs2020.gui;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

/**
 * Äänistä vastaava luokka. Ääniefektit ja niiden soitto on tarkoitus toteuttaa viimeisenä.
 */
public class Aanet implements Runnable {

    long minPrime;
    Map<Integer, String> soittolista;
    boolean musiikkiSoi, aanetKeskeytyy;
    private Soittaja soittaja;
    
    public Aanet(Soittaja soittaja) {
//        soittolista = new HashMap<>();
//        this.luoSoittolista();
        this.minPrime = 143;
        this.soittaja = soittaja;
        this.musiikkiSoi = false;
        this.aanetKeskeytyy = false;
    }
    
    public void luoAluksi() {
        soittolista = new HashMap<>();
        this.luoSoittolista();
    }

    public void setMusiikkiSoi(boolean musiikkiSoi) {
        this.musiikkiSoi = musiikkiSoi;
    }

    public void setAanetKeskeytyy(boolean aanetKeskeytyy) {
        this.aanetKeskeytyy = aanetKeskeytyy;
    }

    @Override
    public void run() {
        if (soittaja.getSoitettava() != 1000) {
            this.play(soittaja.getSoitettava(), false);
        }
        if (soittaja.getSoitettava() == 1000 && !musiikkiSoi) {
            musiikkiSoi = true;
            while (musiikkiSoi) {
                this.play(1000, true);
            }
        }
    }
    
    private InputStream lataa(String tiedosto) {
        return getClass().getClassLoader().getResourceAsStream(tiedosto);
    }
    
    private void luoSoittolista() {
        soittolista.put(0, "aanet/svefif_01.wav");
        soittolista.put(1, "aanet/svefif_02.wav");
        soittolista.put(2, "aanet/svefif_03.wav");
        soittolista.put(3, "aanet/svefif_04.wav");
        soittolista.put(4, "aanet/svefif_05.wav");
        soittolista.put(5, "aanet/svefif_05_bonus.wav");
        soittolista.put(10, "aanet/come_1.wav");
        soittolista.put(11, "aanet/come_2.wav");
        soittolista.put(12, "aanet/come_3.wav");
        soittolista.put(13, "aanet/come_4.wav");
        soittolista.put(14, "aanet/come_5.wav");
        soittolista.put(20, "aanet/nato_01.wav");
        soittolista.put(21, "aanet/nato_02.wav");
        soittolista.put(22, "aanet/nato_03.wav");
        soittolista.put(23, "aanet/nato_04.wav");
        soittolista.put(24, "aanet/nato_05.wav");
        soittolista.put(30, "aanet/japan_01.wav");
        soittolista.put(31, "aanet/japan_02.wav");
        soittolista.put(32, "aanet/japan_03.wav");
        soittolista.put(33, "aanet/japan_04.wav");
        soittolista.put(34, "aanet/japan_05.wav");

        soittolista.put(50, "aanet/w01.wav");
        soittolista.put(51, "aanet/w02.wav");
        soittolista.put(52, "aanet/w03.wav");
        soittolista.put(53, "aanet/w04.wav");
        soittolista.put(54, "aanet/w05.wav");
        soittolista.put(55, "aanet/w06.wav");
        soittolista.put(56, "aanet/w07.wav");
        soittolista.put(57, "aanet/w08.wav");
        soittolista.put(58, "aanet/w09.wav");

        soittolista.put(60, "aanet/water_01.wav");
        soittolista.put(61, "aanet/water_02.wav");
        soittolista.put(62, "aanet/water_03.wav");
        soittolista.put(63, "aanet/water_04.wav");
        soittolista.put(64, "aanet/water_05.wav");
        soittolista.put(65, "aanet/water_06.wav");
        
        soittolista.put(70, "aanet/uboat_01.wav");
        soittolista.put(71, "aanet/uboat_02.wav");
        soittolista.put(72, "aanet/uboat_03.wav");
        soittolista.put(73, "aanet/uboat_04.wav");
        soittolista.put(74, "aanet/uboat_05.wav");

        soittolista.put(80, "aanet/ex_01.wav");
        soittolista.put(81, "aanet/ex_02.wav");
        soittolista.put(82, "aanet/ex_03.wav");
        soittolista.put(83, "aanet/ex_04.wav");
        soittolista.put(84, "aanet/ex_05.wav");
        soittolista.put(85, "aanet/ex_06.wav");
        soittolista.put(86, "aanet/ex_07.wav");
        soittolista.put(87, "aanet/ex_08.wav");
        soittolista.put(88, "aanet/ex_09.wav");
        soittolista.put(89, "aanet/ex_10.wav");

        soittolista.put(100, "aanet/menu_klik.wav");
        soittolista.put(101, "aanet/menu_select.wav");
        soittolista.put(102, "aanet/menu_eivoi.wav");

        soittolista.put(200, "aanet/end.wav");
        soittolista.put(201, "aanet/end_havio.wav");

        soittolista.put(1000, "aanet/musa.mp3");
    }

    public void play(int soitettava, boolean onkoMusiikki) {
        try (final AudioInputStream in = getAudioInputStream(lataa(soittolista.get(soitettava)))) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                     (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    line.open(outFormat);
                    line.start();
                    if (onkoMusiikki) {
                        musastream(getAudioInputStream(outFormat, in), line);
                    } else {
                        stream(getAudioInputStream(outFormat, in), line);
                    }
                    line.drain();
                    line.stop();
                }
            }

        } catch (UnsupportedAudioFileException 
               | LineUnavailableException 
               | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void musastream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            if (!musiikkiSoi) {
                return;
            }
            line.write(buffer, 0, n);
        }
    }    
    
    private void stream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            if (aanetKeskeytyy) {
                return;
            }
            line.write(buffer, 0, n);
        }
    }    
    
}
