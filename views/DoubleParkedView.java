package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;

/**
 * Description of the class DoubleParkedView
 *This class generates information about the Cars that have parked wrongly..
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.3
 */
public class DoubleParkedView extends AbstractView
{
    //creates a new text area, used to display information.
    JTextArea textArea14;
    /**
     * Constructor for objects of class DoubleParkedview
     * .
     * @param need the Controller to create an abstract view
     */
    public DoubleParkedView(ParkingPassController controller)
    {
       //uses the controller to create an abstract view object
        super(controller);
       // fills the text area with information
       textArea14 = new JTextArea("Current amount of people who parked wrong: 0");
    }

    /**
     * Method updateView
     *
     */
    public void updateView()
    {
       //Overrides the View with the most up-to-date information.
        textArea14.setText("Current amount of people who parked wrong: " + Integer.toString(controller.getAmountDoubleParked()));
    }
}
