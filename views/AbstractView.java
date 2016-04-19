package mvc.views;
import javax.swing.*;

import mvc.controller.ParkingPassController;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**
 * Description of the class AbstractView.
 * This class adds Views to an Array.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 2.0
 */
public abstract class AbstractView extends JFrame
{
    // creates a new ParkingPassController to add the views.
    public ParkingPassController controller;

    /**
     * Constructor for objects of class AbstractView
     *
     *@param needs the controller in order to create an abstract view object. 
     */
    public AbstractView(ParkingPassController controller)
    {
        //Gets controller and uses it for a parameter to create a view.
        this.controller = controller;
        controller.addView(this);
    }

    /**
     * Method updateView
     *
     */
    public void updateView() {
           // Will be overridden by views. Has to be empty!
    }
}
