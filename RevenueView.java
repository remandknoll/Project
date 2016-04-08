import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Write a description of class ParkingPassView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RevenueView extends AbstractView
{
    // instance variables - replace the example below with your own
    public JTextArea textArea5;
    public JTextArea textArea6;

    /**
     * Constructor for objects of class ParkingPassView
     */
    public RevenueView()
    {
        textArea5 = new JTextArea("\n");
        textArea6 = new JTextArea("Totale omzet: €0");
    }
}
