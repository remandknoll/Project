package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;
/**
 * Description of the class QueueView
 *This class generates information about the queues
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 3.0
 */
public class QueueView extends AbstractView
{
    //Creates 3 textareas that will be used to display the information.
    public JTextArea textArea16;
    public JTextArea textArea18;
    public JTextArea textArea20;
    /**
     * Constructor for objects of class QueueView
     * 
     * @param needs the controller to create an abstract view object
     */
    public QueueView(ParkingPassController controller)
    {
        //uses the controller to create an abstract view object
        super(controller);
        //Creates three default text areas
        textArea16 = new JTextArea("Amount of people in Entrance Queue: 0");
        textArea18 = new JTextArea("Amount of paying people: 0");
        textArea20 = new JTextArea("Amount of people in Exit queue: 0");
    }
    
    /**
     * Method updateView
     *
     */
    public void updateView()
    {
        //updates the text areas in the GUI, using the integer to string method while calling on the get methods, which the controller gets from the model.
        textArea16.setText("Amount of people in Entrance Queue: " + Integer.toString(controller.getAmountQueue(true)));
        textArea18.setText("Amount of paying people: " + Integer.toString(controller.getPaymentQueue()));
        textArea20.setText("Amount of people in Exit queue: " + Integer.toString(controller.getAmountQueue(false)));
    }
}
