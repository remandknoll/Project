import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables - replace the example below with your own
    private ParkingPassView parkingview;
    private RevenueView revenueview;
    public JFrame frame3;
    public ParkingPassController passcontroller;
    /**
     * Constructor for objects of class GUI
     */
    public GUI(ParkingPassController controller)
    {
        passcontroller = controller;
        parkingview = passcontroller.getPass();
        revenueview = passcontroller.getRevenue();
        frame3 = new JFrame("AmountofregularCars");
        frame3.setLayout(new BoxLayout(frame3, BoxLayout.PAGE_AXIS));
        frame3.getContentPane().setLayout(new BoxLayout(frame3.getContentPane(), BoxLayout.PAGE_AXIS));
        Container contentPane3 = frame3.getContentPane();
        contentPane3.add(parkingview.textArea2);
        contentPane3.add(parkingview.textArea3);
        contentPane3.add(parkingview.textArea4);
        contentPane3.add(revenueview.textArea5);
        contentPane3.add(revenueview.textArea6);
        frame3.pack();
        frame3.setVisible(true);
    }
    
     // With this method it will show the GUI.
    public void openGUI()
    {
        
    }
}
