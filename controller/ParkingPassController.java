package mvc.controller;

import mvc.cars.*;
import mvc.logic.*;
import mvc.runner.*;
import mvc.views.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * Description of the class ParkingPassController
 *This class Generates updates the model and the views.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 4.5
 */
public class ParkingPassController extends JFrame
{
    // creates an array, in which all the views can be stored
    private ArrayList<AbstractView> views = new ArrayList<AbstractView>();
    // creates a model in order to access the information
    private ParkingpassModel parkingPass;

    /**
     * Constructor for objects of class PakringPassController
     * 
     * 
     */
    public ParkingPassController()
    {
        //all views are added to the array.
        new RevenueView(this);
        new PieChart(this);
        new CustomerView(this);
        new DoubleParkedView(this);
        new QueueView(this);
        new ReservationView(this);
        //Creates the model
        parkingPass = new ParkingpassModel();
    }

    /**
     * Method updateView
     *
     */
    public void updateView()
    {   
        //Updates all the views
        for(AbstractView view : views) {
            view.updateView();
        }
    }

    /**
     * Method addView
     *
     * @param uses the parameter view in order to insert it into an array. 
     */
    public void addView(AbstractView view) {
        //Inserts into the array
        views.add(view);
    }

    /**
     * Method getViews
     *
     * @return returns all the views fom the array
     */
    public ArrayList<AbstractView> getViews() {
        return views;
    }

    /**
     * Method maxSum
     *
     */
    public void maxSum()
    {
        //this method adds 20 euros to the total revenue and 400 minutes to the garage.
        
        parkingPass.totalRevenue += 2000;
        parkingPass.amountMinutesInGarage += 400;
    }

    /**
     * Method deleteSum
     *
     * @param boolean max, uses parameters to determine whether you use the maximum cost or the amount of time the cars has been parked in realtime.
     */
    public void deleteSum(boolean max)
    {
        //checks if the maxsum is true
        if(max == true)
        {
            parkingPass.amountMinutesInGarage -= 400;
        }
        //uses the amount of minutes the car has been parked.
        else
        {
            parkingPass.amountMinutesInGarage -= getMinutes();
        }
    }

    /**
     * Method sumPrice
     *
     */
    public void sumPrice()
    {   
        //Accecces the controller in order to update the total Revenue, by minutes  *5
        parkingPass.totalRevenue += getMinutes() * 5;
    }

    /**
     * Method modifyMinutes
     *
     * @param minutes
     */
    public void modifyMinutes(int minutes)
    {
        // sets the amount of minutes using the controller and the minutes from the parameter
        parkingPass.amountMinutes = minutes;
        //Increments the total amount of minutes
        parkingPass.amountMinutesInGarage += minutes;
    }

    /**
     * Method getMinutes
     *
     * @return Returns the amount of minutes from the model.
     */
    public int getMinutes()
    {
        return parkingPass.amountMinutes;
    }

    /**
     * Method getMinutesInGarage
     *
     * @return return the amount of minutes that a car has been staying in the garage.
     */
    public int getMinutesInGarage()
    {
        return parkingPass.amountMinutesInGarage;
    }

    /**
     * Method incrementNoParkingPass
     *
     */
    public void incrementNoParkingPass()
    {
       //increments cars with no Parking Passes
       parkingPass.noParkingPass++;
    }
    
    /**
     * Method decreaseNoParkingPass
     *
     */
    public void decreaseNoParkingPass()
    {
        //Decreases amount of cars without parking passes
        parkingPass.noParkingPass--;
    }

    /**
     * Method incrementParkingPass
     *
     */
    public void incrementParkingPass()
    {
        //increments amount of cars with Parking Passes
        parkingPass.ParkingPass++;
    }
    /**
     * Method decreaseParkingPass
     *
     */
    public void decreaseParkingPass()
    {
        //decreases the amount of cars with parking passes
        parkingPass.ParkingPass--;
    }

    /**
     * Method getAmountParkingPass
     *
     * @return returns the amount of cars with a Parkingpass
     */
    public int getAmountParkingPass()
    {
        return parkingPass.ParkingPass;
    }

    /**
     * Method getAmountNoParkingPass
     *
     * @return Returns the amount of cars that don't use a ParkingPass
     */
    public int getAmountNoParkingPass()
    {
        return parkingPass.noParkingPass;
    }
    /**
     * Method incrementReservation
     *
     */
    public void incrementReservation()
    {
        //increments the amount of Reservations
        parkingPass.Reservation++;
    }
    /**
     * Method decreaseReservation
     *
     */
    public void decreaseReservation()
    {
        //decreases the amount of Reservations
        parkingPass.Reservation--;
    }
    /**
     * Method getAmountReservation
     *
     * @return Returns the Amount of Reservations
     */
    public int getAmountReservation()
    {
        return parkingPass.Reservation;
    }
    /**
     * Method getTotalRevenue
     *
     * @return Returns the total revenue
     */
    public int getTotalRevenue()
    {
        return parkingPass.totalRevenue;
    }

    /**
     * Method getRevenue
     *
     * @return Returns the RevenueView to be used in the GUI
     */
    public RevenueView getRevenue()
    {
        // 0 means the place in the array
        return (RevenueView) views.get(0);
    }

    /**
     * Method getRevenueInGarage
     *
     * @return Returns the total revenue in the garage by using the total amount of minutes
     */
    public int getRevenueInGarage()
    {
        return parkingPass.amountMinutesInGarage;
    }

    /**
     * Method getPieChart
     *
     * @return Returns the PieChart view to be used in the GUI
     */
    public PieChart getPieChart()
    {
        // 1 means the place in the array
        return (PieChart) views.get(1);
    }

    /**
     * Method getCustomerView
     *
     * @return Returns the Customerview to be used in the GUI
     */
    public CustomerView getCustomerView()
    {
        // 2 means the place in the array
        return (CustomerView) views.get(2);
    }

    /**
     * Method getAmountCustomers
     *
     * @return Returns the total amount of customers
     */
    public int getAmountCustomers()
    {
        return parkingPass.amountCustomers;
    }

    /**
     * Method getAmountCustomersInGarage
     *
     * @return Returns the current amount of customers in the garage
     */
    public int getAmountCustomersInGarage()
    {
        return parkingPass.amountCustomersInGarage;
    }

    /**
     * Method incrementAmountCustomers
     *
     * @param boolean, if it's true it will increment, if it's false it will decrease
     */
    public void incrementAmountCustomers(boolean increment)
    {
        if(increment == true)
        {
            // this will increase the total amount of customers and the current amount of customers in the garage
            parkingPass.amountCustomersInGarage++;
            parkingPass.amountCustomers++;
        }

        else
        {
            // this will decrease the current amount of customers in the garage
            parkingPass.amountCustomersInGarage--;
        }
    }
    
    /**
     * Method incrementDoubleParked
     *
     * @param Boolean, this will be used to determine whether the amount of badly parked cars has to be incremented or decreased.
     */
    public void incrementDoubleParked(boolean increment)
    {
        if(increment == true)
        {
            // Increments the amount of badly parked cars
            parkingPass.amountDoubleParked++;
        }
        
        else{
            // Decreased the amount of badly parked cars
            parkingPass.amountDoubleParked--;   
        }
    }
    
    /**
     * Method getAmountDoubleParked
     *
     * @return Returns the amount of badly parked cars
     */
    public int getAmountDoubleParked()
    {
        return parkingPass.amountDoubleParked;
    }
    
    /**
     * Method getDoubleParkedView
     *
     * @return Returns the Double Parked View to be used in the GUI.
     */
    public DoubleParkedView getDoubleParkedView()
    {
        // 3 means the place in the array
        return (DoubleParkedView) views.get(3);
    }
    
    /**
     * Method modifyEntranceQueue
     *
     * @param Amount of cars in the Entrance Queue
     */
    public void modifyEntranceQueue(int amount)
    {
        // This will update the amount of cars in the Entrance Queue
        parkingPass.amountEntranceQueue = amount;
    }

    /**
     * Method modifyExitQueue
     *
     * @param Needs the amount of cars in the Exit Queue
     */
    public void modifyExitQueue(int amount)
    {
        // This will change the amount of Exit Queue cars in the model
        parkingPass.amountExitQueue = amount;
    }

    /**
     * Method getAmountQueue
     *
     * @param Boolean whether the amount of Entrance queue or Exit queue has to be returned
     * @return Returns the amount of Entrance or Exit queue cars
     */
    public int getAmountQueue(boolean queue)
    {
        if(queue == true)
        {
            return parkingPass.amountEntranceQueue;
        }

        else
        {
            return parkingPass.amountExitQueue;
        }
    }
    
    /**
     * Method getQueueView
     *
     * @return Returns the QueueView that will be used in the GUI
     */
    public QueueView getQueueView()
    {
        // 4 means the place in the array
        return (QueueView) views.get(4);
    }
       
    /**
     * Method modifyPaymentQueue
     *
     * @param an amount of cars in the Payment Queue
     */
    public void modifyPaymentQueue(int amount)
    {
        // This will change the amount of cars in the payment queue
        parkingPass.amountPaymentQueue = amount;
    }

    /**
     * Method getPaymentQueue
     *
     * @return Returns the amount of cars in the Payment Queue
     */
    public int getPaymentQueue()
    {
        return parkingPass.amountPaymentQueue;
    }
    
        /**
         * Method getReservationView
         *
         * @return Returns the ReservationView that will be used in the GUI
         */
        public ReservationView getReservationView()
    {
        // 5 means the place in the array
        return (ReservationView) views.get(5);
    }
}
