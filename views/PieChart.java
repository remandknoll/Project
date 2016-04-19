package mvc.views;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.title.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.*;
import org.jfree.util.*;

import mvc.controller.ParkingPassController;

import java.awt.event.*;
/**
 * Description of the class PieChart
 * This class generatees the PieChart for the Frame.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.0
 */
public class PieChart extends AbstractView {
    // A static variable represents pie chart angle
    static int i=0;
    // Data that will be used by the PieChart
    public DefaultPieDataset p;
    //Creates the panel for the PieChart
    public ChartPanel panel;
    public PieChart(ParkingPassController controller)
    {
        // Uses controller to create AbstractView object.
        super(controller);
        // Create a dataset
        p = new DefaultPieDataset();
    }
    
    /**
     * Method setVisiblePie
     */
    public void setVisiblePie()
    {
        setVisible(true);
    }
    
    /**
     * Method getChart
     *
     * @return Returns the DefaultPieDataSet which will be used in the GUI class.
     */
    public DefaultPieDataset getChart()
    {
        return p;
    }
    
    /**
     * Method updateView
     */
    public void updateView()
    {
        // Updates the values in the PieChart
        p.setValue("Parking Pass Holder",controller.getAmountParkingPass());
        p.setValue("Regular Customer", controller.getAmountNoParkingPass());
        p.setValue("Wrong parked cars", controller.getAmountDoubleParked());
        p.setValue("Amount of reservations", controller.getAmountReservation());
    }
}