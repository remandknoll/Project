package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;

/**
 * Description of the class CustomerView
 *This class Generates information about the Customers.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.1
 */
public class CustomerView extends AbstractView
{
    //Creates 4 textareas, which will be used later on.
    public JTextArea textArea9;
    public JTextArea textArea10;
    public JTextArea textArea11;
    public JTextArea textArea12;
    /**
     * Constructor for objects of class CustomerView
     * 
     * @param needs the controller to create an abstractview object.
     */
    public CustomerView(ParkingPassController controller)
    {
       //uses the controller to create an abstractview objects.
       super(controller);
       // Text areas used to display text.
       textArea10 = new JTextArea("Total amount of customers: 0");
       textArea11 = new JTextArea("\n");
       textArea12 = new JTextArea("Total amount of customers currently in garage: 0");
    }
    
    
   public void updateView()
    {  
       // This method updates the GUI with the most recent available information.
       textArea10.setText("Total amount of customers: " +  Integer.toString(controller.getAmountCustomers()));
       textArea12.setText("Total amount of customers currently in garage: " +  Integer.toString(controller.getAmountCustomersInGarage()));
    }
}
