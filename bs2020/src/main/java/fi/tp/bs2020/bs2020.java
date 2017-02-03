/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.tp.bs2020;

import fi.tp.bs2020.gui.Kayttis;
import javax.swing.SwingUtilities;

/**
 *
 * @author Tuomas Pätäri
 */
public class bs2020 {
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Kayttis kayttoliittyma = new Kayttis();
        SwingUtilities.invokeLater(kayttoliittyma);
   
    }
    
}
