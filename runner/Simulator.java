package mvc.runner;

import mvc.cars.*;
import mvc.controller.*;
import mvc.logic.*;
import mvc.views.*;

import javax.swing.*;

import mvc.cars.AdHocCar;
import mvc.cars.Car;
import mvc.cars.CarQueue;
import mvc.cars.DoubleParked;
import mvc.cars.ParkingPassCar;
import mvc.cars.Reservation;
import mvc.controller.ParkingPassController;
import mvc.logic.Location;
import mvc.views.GUI;
import mvc.views.SimulatorView;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import static java.lang.Thread.currentThread;

import java.util.concurrent.TimeUnit;
import java.util.*;

/**
 * Description of the class Simulator
 * This class contains information about the values that are used in the simulation and contains information about the workings of the simulator itself
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 3.2
 */
public class Simulator {
    // Instantiates all the variables.
    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;
    
    //instantiates the variables that will be used to display and calculate the time.
    private int day = 0; 
    private int hour = 8;
    private int minute = 0;
    
    private int tickPause = 100; //waits between ticks so that the human eye and mind can keep up.

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour
    int LateNightArrivals = 10; // average number of arriving cars during the day and night

    int enterSpeed = 5; // number of cars that can enter per minute
    int paymentSpeed = 1; // number of cars that can pay per minute
    int exitSpeed = 4; // number of cars that can leave per minute
    int parkingPassProbability = 6; // chance that a car uses a Parking Pass
    int chanceDoubleParked = 25; // chance that a car will park badly and take up two parking spaces.
    int chanceReservation = 20;  // chance that a carr will reserve a spot.

    public int totalRevenue; //total revenue
    private int totalMinutes; //total minutes
    private ParkingPassController ppcontroller; //instantiates controller object
    private GUI gui; // instantiates a GUI
    public boolean isRunning = false; // instantiates a boolean
    String StringDatum; 
    String dag;
    String newHour;
    String newMinute;
    /**
     * Constructor of the class: Simulator; creates the simulation
     */
    public Simulator() {
        entranceCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30, this);

    }

    /**
     * Extra constructor
     */
    public Simulator(SimulatorView simulatorView) {
        entranceCarQueue = new CarQueue();
        this.simulatorView = simulatorView;
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        totalRevenue = 0;
        totalMinutes = 0;
        ppcontroller = new ParkingPassController();
        gui = new GUI(ppcontroller, this);
    }

    /**
     * Method run
     *
     */
    public void run() {
        // creates a thread 
        Thread t = new Thread(new Runnable() {
                    //calls on run method
                    public void run() {
                        // for loop that decides how long the thread will run and updates the view accordingly.
                        for (int i = 0; i < 10000; i++) {
                            // sets date in Jlabel 12
                            simulatorView.setjlabel2(StringDatum);
                            tick();
                            ppcontroller.updateView();
                            //breaks if the Simulator View running = false.
                            if(simulatorView.running == false)
                            {
                                break;
                            }

                        }
                    }
         
       });
       //runs thread
        t.start();
    }

    /**
     * Method runOneStep, runs the simulater one step 
     *
     */
    public void runOneStep() {
        for (int i = 0; i < 1; i++) {
            tick();
            isRunning = true;
            ppcontroller.updateView();
        }
    }

    /**
     * This is the run method; runs the simulation for a duration
     */
    public void runMultipleSteps() {
        Thread t1 = new Thread(new Runnable() {
                    public void run() {           
                        for (int i = 0; i < 100; i++) {
                            tick();
                            isRunning = true;

                            if(simulatorView.running == false)
                            {
                                break;
                            }
                            ppcontroller.updateView();
                        }
                    }
                });
        t1.start();
    }

    /**
     * Method runMultipleSteps
     *
     * @param uses the parameter stappen in order to determine for how many ticks the simulation has to run.
     */
    public void runMultipleSteps(int stappen) {
        Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        for (int i = 0; i < stappen; i++) {
                            tick();

                            if(simulatorView.running == false)
                            {
                                break;
                            }
                            ppcontroller.updateView();
                        }
                    }
                });
        t2.start();
    }

    /**
     * This is the tick method; calculates the times in minutes, hours and days
     */
    public void tick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }
        //uses a switch statement in order to keep track of the day
        switch (day) {
            case 0:  dag = "monday";
            break;
            case 1:  dag = "tuesday";
            break;
            case 2:  dag = "wednesday";
            break;
            case 3:  dag = "thursday";
            break;
            case 4:  dag = "friday";
            break;
            case 5:  dag = "saturday";
            break;
            case 6:  dag = "sunday";
            break;
            default: dag = "Error";
            break;
        }
        //uses a switch statement in order to keep track of the current hour
        switch (hour) {
            case 0:  newHour = "00";
            break;
            case 1:  newHour = "01";
            break;
            case 2:  newHour = "02";
            break;
            case 3:  newHour = "03";
            break;
            case 4:  newHour = "04";
            break;
            case 5:  newHour = "05";
            break;
            case 6:  newHour = "06";
            break;
            case 7:  newHour = "07";
            break;
            case 8:  newHour = "08";
            break;
            case 9:  newHour = "09";
            break;
            default: newHour = "" + hour;
            break;

        }
        // uses a switch statement in order to keep track of the minutes
        switch (minute) {
            case 0:  newMinute = "00";
            break;
            case 1:  newMinute = "01";
            break;
            case 2:  newMinute = "02";
            break;
            case 3:  newMinute = "03";
            break;
            case 4:  newMinute = "04";
            break;
            case 5:  newMinute = "05";
            break;
            case 6:  newMinute = "06";
            break;
            case 7:  newMinute = "07";
            break;
            case 8:  newMinute = "08";
            break;
            case 9:  newMinute = "09";
            break;
            default: newMinute = "" + minute;
            break;

        }
        //creates a string that contains the day, and time, in hours and minutes
        StringDatum = "It is today " + dag + " and the time is " + newHour + ":" + newMinute;
        //instantiates a new random
        Random random = new Random();
        
        //instantiates an int averageNumberOfCarsPerhour
        int averageNumberOfCarsPerHour = 0;
        //if statement used to decrease the arriving cares during the night
        if(hour > 21 || hour < 7){

            averageNumberOfCarsPerHour = 10;
        } else {
            averageNumberOfCarsPerHour = day < 5 
            ? weekDayArrivals
            : weekendArrivals; 
        }
        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);
        int reservationChance = random.nextInt(chanceReservation);
        
        //increases the chance that a car will reserve a place on saturday evening.
        if(day == 5 && hour > 18)
        {
            reservationChance = random.nextInt(4);
        }
        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            int parkingPassChance = random.nextInt(parkingPassProbability);
           
            if(parkingPassChance == 0)
            {
                // 
                ppcontroller.incrementAmountCustomers(true);
                Car car = new ParkingPassCar();
                entranceCarQueue.addCar(car);
                ppcontroller.incrementParkingPass();
            }
            else if(reservationChance == 0)
            {
                Car car = new Reservation();
                entranceCarQueue.addCar(car);
                ppcontroller.incrementAmountCustomers(true);
                ppcontroller.incrementReservation();
            }
            else {
                ppcontroller.incrementAmountCustomers(true);
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
                ppcontroller.incrementNoParkingPass();
            }

        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();

            if (freeLocation != null) {
                int doubleParked = random.nextInt(chanceDoubleParked);
                simulatorView.setCarAt(freeLocation,car);
                Car car2 = new DoubleParked();
                if(doubleParked == 0){
                    Location freeLocation2 = simulatorView.checkNextOneFree(freeLocation);
                    if(freeLocation2 != null){
                        ppcontroller.incrementDoubleParked(true);
                        simulatorView.setCarAt(freeLocation2,car2);
                    }

                }

                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                if(car instanceof AdHocCar || car instanceof Reservation)
                {
                    // A car should never pay more than 20 euros. 400 minutes is 20 euro's.
                    if(stayMinutes >= 400)
                    {
                        ppcontroller.maxSum();
                    }
                    else
                    {
                        ppcontroller.modifyMinutes(stayMinutes);
                        ppcontroller.sumPrice();
                    }
                }
                
                if(car instanceof Reservation)
                {
                    
                    if(day == 6 && hour > 18)
                    {
                        car.setMinutesLeft(360);
                    }
                    else{
                        car.setMinutesLeft(1440);
                    }
                }
                else{
                    car.setMinutesLeft(stayMinutes);
                }
                if(car2 != null){
                    car2.setMinutesLeft(stayMinutes);
                }
            }
        }
        
        //the controller modifys the entrance queue.
        ppcontroller.modifyEntranceQueue(entranceCarQueue.returnQueueLength());
        // Perform car park tick.
        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            // determines which car it handles and modifies statistics if necessary
            if(car instanceof ParkingPassCar)
            {
                exitCarQueue.addCar(car);
                simulatorView.removeCarAt(car.getLocation());
                ppcontroller.decreaseParkingPass();
                
            }
            

            else if(car instanceof DoubleParked) {
            	ppcontroller.incrementDoubleParked(false);
            	simulatorView.removeCarAt(car.getLocation());
            } else if(car instanceof Reservation) {
                exitCarQueue.addCar(car);
                simulatorView.removeCarAt(car.getLocation());
                ppcontroller.decreaseReservation();
            }  else {
                paymentCarQueue.addCar(car);
                ppcontroller.decreaseNoParkingPass();
            }

            car.setIsPaying(true);
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            
            simulatorView.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }
        // modifies the Payment queue
        ppcontroller.modifyPaymentQueue(paymentCarQueue.returnQueueLength());

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            ppcontroller.incrementAmountCustomers(false);
            if(ppcontroller.getMinutes() >= 400)
            {
                ppcontroller.deleteSum(true);
            }
            else
            {
                ppcontroller.deleteSum(false);
            }
            // Bye!
        }
        // updates the Exit Queue
        ppcontroller.modifyExitQueue(exitCarQueue.returnQueueLength());
        // Update the car park view.
        simulatorView.updateView();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method getTabbedPane
     *
     * @return Returns the TabbedPane
     */
    public JTabbedPane getTabbedPane()
    {
        JTabbedPane tabbedpane1 = simulatorView.tabbedPane;
        return tabbedpane1;

    }

}
