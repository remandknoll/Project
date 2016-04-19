package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;

/**
 * Description of the class RevenueView
 * This class generates the Information that is displayed in the JPanel once the simulator is running.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.0
 */
public class RevenueView extends AbstractView
{
    // instance variables - replace the example below with your own
    public JTextArea textArea5;
    public JTextArea textArea6;
    public JTextArea textArea7;
    public JTextArea textArea8;

    /**
     * Constructor for objects of class ParkingPassView.
     * @param Needs the controller to create the Abstractview object.
     */
    public RevenueView(ParkingPassController controller)
    {
        // Creates the AbstractView Object
        super(controller);
        // Creates two TextArea's for the statistics tab.
        textArea6 = new JTextArea("Total revenue: €0");
        textArea8 = new JTextArea("Total revenue still in garage: €0");
    }
    /**
     * Method updateView
     */
    public void updateView() {
        // totalMoney will be calculated from cents to euro's by dividing by 100.
        int totalMoney = controller.getTotalRevenue() / 100;
        // Updates the textarea in the GUI
        textArea6.setText("Total revenue: €" + Integer.toString(totalMoney));
        // revenueInGarage will be calculated by multiplying the RevenueInGarage by 5, because the GetRevenueInGarage Function works with 5 cents per minute, once divided by 100 you will get the revenue in euro's.
        int revenueInGarage = (controller.getRevenueInGarage() * 5) / 100;
        // Updates the textarea in the GUI
        textArea8.setText("Total revenue currently in garage: €" + Integer.toString(revenueInGarage));
    }
}
