import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Write a description of class RevenueView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParkingPassView extends AbstractView
{
    // instance variables - replace the example below with your own
    public JTextArea textArea2;
    public JTextArea textArea3;
    public JTextArea textArea4;

    /**
     * Constructor for objects of class RevenueView
     */
    public ParkingPassView()
    {
        textArea2 = new JTextArea("Aantal auto's weggegaan met Parking Pass: " + "0");
        textArea3 = new JTextArea("\n");
        textArea4 = new JTextArea("Aantal auto's weggegaan zonder Parking Pass: 0");
    }
}
