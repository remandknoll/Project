package mvc.views;

import mvc.cars.*;
import mvc.controller.*;
import mvc.logic.*;
import mvc.runner.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Font;

import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.title.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;
import org.jfree.util.*;

import mvc.controller.ParkingPassController;

import java.awt.event.*;

/**
 * Description of the class GUI
 * This class will generate the graph user interface.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 3.6
 */
public class GUI extends JPanel
{
    // instance variables - replace the example below with your own
    // Creates the Views to be displayed in the GUI
    private PieChart parkingview;
    private RevenueView revenueview;
    private CustomerView customerView;
    private DoubleParkedView dpview;
    private QueueView queue;
    // creates the controller that will be used to update information here
    private ParkingPassController passcontroller;
    private PieChart piechart;
    // creates the JPanels and TabbedPanes for the layout
    public JPanel statistics;
    public JPanel piecharttab;
    public Simulator simulator;
    public JTabbedPane tabbedpane2;
    public ReservationView reservation;
    // creates and integer for the turning PieChart
    int i;
    /**
     * Constructor for objects of class GUI
     * 
     * @param Needs the controller for updating the GUi
     * @param Needs the simulator for methods and information
     * 
     */
    public GUI(ParkingPassController controller, Simulator simulator)
    {
        // Sets the simulator using the Parameter
        this.simulator = simulator;
        i = 0;
        // Sets the views using methods from the controller
        queue = controller.getQueueView();
        dpview = controller.getDoubleParkedView();
        passcontroller = controller;
        revenueview = passcontroller.getRevenue();
        customerView = passcontroller.getCustomerView();
        piechart = passcontroller.getPieChart();
        reservation = passcontroller.getReservationView();

        // Create a 3D chart with the given title that appears above the chart
        JFreeChart chart=ChartFactory.createPieChart3D("Overview Garage",piechart.getChart());

        // Get a ui representation of the pie chart title so that you can customize
        // the font and padding
        TextTitle tt=new TextTitle("Overview Garage",new Font("Arial",Font.BOLD,14));

        // Space around the text and between its border
        // top, left, bottom, right
        tt.setPadding(5,5,5,5);
        chart.setTitle(tt);
        piechart.getChart().setValue("Parking Pass Holder",1);
        piechart.getChart().setValue("Regular Customer", 1);
        piechart.getChart().setValue("Wrong parked cars", 1);
        piechart.getChart().setValue("Amount of reservations", 1);
        // Get the PiePlot object so that you can customize the label font
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(new Font("Arial",Font.PLAIN,12));

        // Chart will be drawn on a panel
        ChartPanel panel=new ChartPanel(chart);
        // Set some preferred size
        panel.setPreferredSize(new Dimension(350,350));
        

        // Create a timer for animation
        // Executed every 150ms    
        Timer t=new Timer(10,new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        // Set the start angle, this increases everytime
                        // timer is executed
                        plot.setStartAngle(0);
                    }
                });

        // Start the timer (animation starts!)
        t.start();
        // creates two tabs in the layout
        statistics = new JPanel();
        piecharttab = new JPanel();
        // Gets the TabbedPane from the simulator
        tabbedpane2 = simulator.getTabbedPane();
        // Sets a new Layout
        statistics.setLayout(new BoxLayout(statistics, BoxLayout.PAGE_AXIS));
        //Adds all the textareas in the statistics tab
        statistics.add(revenueview.textArea6);
        statistics.add(revenueview.textArea8);
        statistics.add(customerView.textArea10);
        statistics.add(customerView.textArea12);
        statistics.add(dpview.textArea14);
        statistics.add(queue.textArea16);
        statistics.add(queue.textArea18);
        statistics.add(queue.textArea20);
        statistics.add(reservation.textArea22);
        //adds the piechart information in the piechart tab
        piecharttab.add(panel, "Chart 1");
        piecharttab.validate();
        tabbedpane2.add(statistics, "Statistics");
        tabbedpane2.add(piecharttab, "Pie Chart");
        //opens the piechart while starting the program
        tabbedpane2.setSelectedIndex(2);

        // sets the visibility of the piechart to true
        piecharttab.setVisible(true);
    }
}
