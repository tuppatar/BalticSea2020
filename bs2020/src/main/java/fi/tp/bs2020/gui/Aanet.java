package fi.tp.bs2020.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFrame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class Aanet implements Runnable {

//    private MediaPlayer mediaPlayer;
//    private Media menuMusiikki;
    private String filename;
    long minPrime;
    List soittolista;
    int soita;
    boolean aaniLopussa;
    
    public Aanet() {
        this.minPrime = 143;
        this.filename = "erased.wav";
        this.soita = 0;
//             JFrame frame = new JFrame("FX");
//             final JFXPanel fxPanel = new JFXPanel();
//             frame.add(fxPanel);
//             frame.setVisible(true);
             //Scene scene = createScene();
             //fxPanel.setScene(scene);   
        //menuMusiikki = new Media(getClass().getResource("Musa.mp3").toString());
//        AudioInputStream audioIn = AudioSystem.getAudioInputStream("music.wav");
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioIn);
//        clip.start();        
        //menuMusiikki = new Media("Musa.mp3");
        //MediaPlayer mediaPlayer = new MediaPlayer(menuMusiikki);
    }
    
//    public void soitaMenumusiikki() {
//        mediaPlayer.play();    
//    }
//    
//    public void katkaiseMenumusiikki() {
//        mediaPlayer.stop();
//    }

    public void setSoita(int soita) {
        this.soita = soita;
    }

    public boolean isAaniLopussa() {
        return aaniLopussa;
    }

    @Override
    public void run() {
        //boolean ei = false;
        //while (!ei) {
            if (soita == 1) {
                this.play(filename);
            }
            if (soita == 2) {
                this.play("Musa.mp3");
            }
        //}
    }
    
    public void play(String filePath) {
        this.aaniLopussa = false;
        final File file = new File(filePath);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                     (SourceDataLine) AudioSystem.getLine(info)) {

                if (line != null) {
                    this.soita = 0;
                    line.open(outFormat);
                    line.start();
                    stream(getAudioInputStream(outFormat, in), line);
                    line.drain();
                    line.stop();
                    this.aaniLopussa = true;
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

    private void stream(AudioInputStream in, SourceDataLine line) 
        throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }    
    
}
