package mvc.logic;
/**
 * Description of the class ModelView
 * This Class stores all the data that will be used in this program
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 1.8
 */
public class ParkingpassModel{
    // Creates all the variables to store information, it will get information from the controller
    public int ParkingPass;
    public int noParkingPass;
    public int totalRevenue;
    public int amountMinutes;
    public int amountMinutesInGarage;
    public int amountCustomers;
    public int amountCustomersInGarage;
    public int amountDoubleParked;
    public int amountEntranceQueue;
    public int amountExitQueue;
    public int amountPaymentQueue;
    public int Reservation;
    /**
     * ParkingpassModel Constructor
     *
     */
    public ParkingpassModel()
    {
        // sets all the variables to zero
        ParkingPass = 0;
        noParkingPass = 0;
        totalRevenue = 0;
        amountMinutes = 0;
        amountMinutesInGarage = 0;
        amountCustomers = 0;
        amountCustomersInGarage = 0;
        amountDoubleParked = 0;
        amountEntranceQueue = 0;
        amountExitQueue = 0;
        amountPaymentQueue = 0;
        Reservation = 0;
    }
}