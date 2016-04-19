package mvc.views;

import mvc.cars.*;
import mvc.controller.*;
import mvc.logic.*;
import mvc.runner.*;

import javax.swing.*;

import mvc.cars.AdHocCar;
import mvc.cars.Car;
import mvc.cars.DoubleParked;
import mvc.cars.ParkingPassCar;
import mvc.cars.Reservation;
import mvc.controller.ParkingPassController;
import mvc.logic.Location;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Description of the class SimulatorView
 * This Class generates the graphic view of the simulator
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 0.1
 */
public class SimulatorView extends JFrame {
    // instance variables
    private GUI gui;
    private RevenueView revenueview;
    private ParkingPassController ppcontroller;
    public CustomerView customerView;
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;
    private JPanel statistics;
    public static JButton b6;
    private JMenuBar menuBar;
    private JMenu menu1, menu2, menu3;
    public static JMenuItem menuItem1;
    public static JMenuItem menuItem2;
    public static JMenuItem menuItem3;
    public static JMenuItem menuItem4;
    public static JMenuItem menuItem5;
	public static JMenuItem menuItem6;
	public static JMenuItem menuItem7;
	
    private static JLabel l1;
    private static JLabel l2;
    private Color color;
    public boolean running = true;
    Simulator simulator;
    public JTabbedPane tabbedPane;
    public boolean isRunning = false;

    /**
     * Constructor of the class SimulatorView
     * @param int numberOfFloors that the simulator needs to make
     * @param int numberOfRows that the simulator needs to make
     * @param int numberOfPlaces that the simulator needs to make
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces, Simulator simulator) {
        this.simulator = simulator;
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];

        carParkView = new CarParkView();
        carParkView.setLayout(null);

        tabbedPane = new JTabbedPane();

        //statistics.add(gui.frame3);
        //quit button
        b6 = new JButton("Quit");
        b6.setSize(90,25);
        b6.setLocation(350,380);
        b6.addActionListener(new Action(this));
        carParkView.add(b6);

        //label intro
        l1 = new JLabel("Car Park Monitoing Software, City Parking Groningen, developed by Team Johto™");
        l2 = new JLabel("Today, it's monday 08:00");
        l2.setSize(600,20);
        l2.setLocation(225,5);
        l1.setSize(600,20);
        l1.setLocation(225,20);
        carParkView.add(l1);
        carParkView.add(l2);

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu1 = new JMenu("Start");

        menuItem1 = new JMenuItem("Start");
        menuItem1.addActionListener(new Action(this));

        menuItem2 = new JMenuItem("Stop");
        menuItem2.addActionListener(new Action(this));

        //Buld menu 2
        menu2 = new JMenu("# Steps");

        menuItem3 = new JMenuItem("1 Step");
        menuItem3.addActionListener(new Action(this));

        menuItem4 = new JMenuItem("Multiple Steps");
        menuItem4.addActionListener(new Action(this));

        menuItem5 = new JMenuItem("# Steps");
        menuItem5.addActionListener(new Action(this));
        
        //Build menu 3
        menu3 = new JMenu("Help");
        
        menuItem6 = new JMenuItem("Instructions");
        menuItem6.addActionListener(new Action(this));
        
        menuItem7 = new JMenuItem("About Developers");
        menuItem7.addActionListener(new Action(this));;

        //add items to menu's
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu2.add(menuItem3);
        menu2.add(menuItem4);
        menu2.add(menuItem5);
        menu3.add(menuItem6);
        menu3.add(menuItem7);
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        // MENU BARRRR !!!!

        Container contentPane = getContentPane();
        //contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.add(carParkView, "Simulator");
        // packs the simulatorView frame and makes it visible
        pack();
        setResizable(false);
        setVisible(true);
        updateView();
    }
    
    /**
     * Method setjlabel2
     *
     * @param Needs a string to set the JLabel 2 to a new String
     */
    public void setjlabel2(String text)
    {
        l2.setText(text);
    }

    /**
     * This is the updateView method; this method clears and updates the view
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * This is the getNumberOfFloors method; this method gives the amount of floors within the car park
     * @return numberOfFloors of the car park
     */
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    /**
     * This is the getNumberOfRows method; this method gives the amount of rows within the car park
     * @return numberOfRows of the car park
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * This is the getNumberOFPlaces method; this method gives the amount of pleaces within the car park
     * @return numberOfPlaces of the car park
     */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    /**
     * This is the getCarAt method; this method gives the location of the car
     * @param  Location location of the car
     * @return cars location in floor, row and place
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    /**
     * This is the setCarAt method; this method sets the location of a specific car inputted by parameters
     * @param  Location location and Car car where the specific car needs to be placed
     * @return set succesfully or not (true or false)
     */
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            return true;
        }
        return false;
    }

    /**
     * This is the removeCarAt method; removes the car from a specific location entered by parameters
     * @param Location location where the car has to be removed from
     * @return car if succesfully removed from position else return null 
     */
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        return car;
    }

    /**
     * This is the getFirstFreeLocation method; this method gives the first free location available
     * @return location of the free place, if there isn't return null
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method checkNextOneFree
     * This method will check if the next spot of a location is empty
     *
     * @param Needs a location and it will check if the next spot is empty
     * @return Returns a location where the next spot is free, or it will return null if it finds nothing
     */
    public Location checkNextOneFree(Location location)
    {
        int place = location.getPlace();
        if(place < 28 && place > 0)
        {
            int place2 = place;
            place2 = place2 + 1;
            int row = location.getRow();
            int floor = location.getFloor();
            Location freelocation2 = new Location(floor,row,place2);
            if(freelocation2 != null && locationIsValid(freelocation2))
            {
                return freelocation2;
            }
        }
        return null;
    }

    /**
     * This is the getFirstLeavingCar method; this method gives the first leaving car
     * @return the first leaving Car object
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This is the tick method; this method runs a tick of the simulation for all cars
     */
    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * This is the locationIsValid method; this method checks if the location is valid
     * 
     * @param Location location to check if valid
     * @return if the location is valid or not (true or false)
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    private class CarParkView extends JPanel {

        private Dimension size;
        private Image carParkImage;
        private JPanel jpanel;

        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView() {
            size = new Dimension(800, 400);
        }

        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 400);
        }

        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }

            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }

        /**
         * Method updateView
         * This method will update the simulatorView frame and it will also paint the cars to the specified color.
         */
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        color = Color.white;
                        if(car instanceof AdHocCar)
                        {
                            color = Color.blue;
                        }
                        if(car instanceof ParkingPassCar)
                        {
                            color = Color.red;
                        }
                        if(car instanceof DoubleParked)
                        {
                            color = Color.green;
                        }
                        if(car instanceof Reservation)
                        {
                            color = Color.yellow;
                        }
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }

        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
        }
    }

    /**
     * Method setRunning
     *
     * @param Needs a boolean to check if the thread is running or not
     */
    public void setRunning(boolean running)
    {
        this.running = running;
    }

    /**
     * Method getRunning
     *
     * @return Returns a boolean where you can see if the thread is running or not
     */
    public boolean getRunning()
    {
        return running;
    }

    /**
     * Method getGui
     *
     * @return Returns the GUI that is used by the simulatorView
     */
    public GUI getGui()
    {
        return gui;
    }

    /**
     * Method getTabbedPane
     *
     * @return Returns the TabbedPane used by this simulatorView
     */
    public JTabbedPane getTabbedPane()
    {
        return tabbedPane;
    }

}

class Action implements ActionListener {
    // instance variables
    private SimulatorView simulatorView;
    GUI gui;
    JTabbedPane tabbedpane;
    
    
    /**
     * Action Constructor
     *
     * @param Needs this simulatorView to edit it
     */
    public Action(SimulatorView simulatorView) {
        this.simulatorView = simulatorView;
        gui = simulatorView.getGui();
        tabbedpane = simulatorView.getTabbedPane();
    }

    /**
     * Method actionPerformed
     *
     * @param Needs an ActionEvent to be used for the buttons and other layout components
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SimulatorView.menuItem1) {
            Simulator sim = new Simulator(simulatorView);
            sim.run();
            simulatorView.menuItem1.setEnabled(false);
            simulatorView.menuItem3.setEnabled(false);
            simulatorView.menuItem4.setEnabled(false);
            simulatorView.menuItem5.setEnabled(false);
        }
        if (e.getSource() == SimulatorView.menuItem3) {
            Simulator sim = new Simulator(simulatorView);

            sim.runOneStep();
            simulatorView.menuItem3.setEnabled(false);
        }
        if (e.getSource() == SimulatorView.menuItem4) {
            Simulator sim = new Simulator(simulatorView);

            sim.runMultipleSteps();
            simulatorView.menuItem4.setEnabled(false);
        }
        if (e.getSource() == SimulatorView.menuItem2) {
            simulatorView.setRunning(false);
            simulatorView.menuItem1.setEnabled(false);
        }
        if (e.getSource() == SimulatorView.menuItem5) {
            userImput imput = new userImput(simulatorView) ;

            simulatorView.menuItem5.setEnabled(false);
        }
        
        if (e.getSource() == SimulatorView.menuItem6) {
        	About about = new About();
        	about.Instructions();
        }
        
        if (e.getSource() == SimulatorView.menuItem7) {
        	About about = new About();
        	about.AboutUs();
        }
        
        if (e.getSource() == SimulatorView.b6) {
            System.exit(0);
        }
    }
}