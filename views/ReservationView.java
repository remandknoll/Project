package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;

/**
 * Description of the class ReservationView
 *This class Generates information about the Reservations.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.3
 */
public class ReservationView extends AbstractView
{
    // creates a textarea
    JTextArea textArea22;
    /**
     * Constructor for objects of class DoubleParkedview
     * 
     * @param needs the controller to create an abstract view object
     */
    public ReservationView(ParkingPassController controller)
    {
       //uses the controller to create an abstract view object
       super(controller);
       //sets the default textarea
       textArea22 = new JTextArea("Amount of reservations: 0");
    }
    
    /**
     * Method updateView
     *
     */
    public void updateView()
    {
        // overrides the abstract view.
        textArea22.setText("Amount of reservations: " + Integer.toString(controller.getAmountReservation()));
    }
}
