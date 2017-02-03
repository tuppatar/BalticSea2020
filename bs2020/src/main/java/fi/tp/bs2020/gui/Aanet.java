package fi.tp.bs2020.gui;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JFrame;


public class Aanet {

    private MediaPlayer mediaPlayer;
    private Media menuMusiikki;
    
    public Aanet() {
             JFrame frame = new JFrame("FX");
             final JFXPanel fxPanel = new JFXPanel();
             frame.add(fxPanel);
             frame.setVisible(true);
             //Scene scene = createScene();
             //fxPanel.setScene(scene);   
        menuMusiikki = new Media(getClass().getResource("Musa.mp3").toString());
        //menuMusiikki = new Media("Musa.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(menuMusiikki);
    }
    
    public void soitaMenumusiikki() {
        mediaPlayer.play();    
    }
    
    public void katkaiseMenumusiikki() {
        mediaPlayer.stop();
    }

}
