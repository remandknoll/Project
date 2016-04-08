
/**
 * Write a description of class PakringPassController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParkingPassController
{
    // instance variables - replace the example below with your own
    private RevenueView revenueview;
    private ParkingPassView parkingpassview;
    private ParkingpassModel parkingPass;


    /**
     * Constructor for objects of class PakringPassController
     */
    public ParkingPassController()
    {
        revenueview = new RevenueView();
        parkingpassview = new ParkingPassView();
        parkingPass = new ParkingpassModel();
    }

    public void updateView()
    {
        parkingpassview.textArea2.setText("Aantal auto's weggegaan met Parking Pass: " + Integer.toString(getAmountParkingPass()));
        parkingpassview.textArea4.setText("Aantal auto's weggegaan zonder Parking Pass: " + Integer.toString(getAmountNoParkingPass()));
        int totalMoney = getTotalRevenue() / 100;
        revenueview.textArea6.setText("Totale Omzet Prognose: €" + Integer.toString(totalMoney));
    }

    public void maxSum()
    {
        parkingPass.totalRevenue += 2000;
    }

    public void sumPrice()
    {
        parkingPass.totalRevenue += getMinutes() * 5;
    }

    public void modifyMinutes(int minutes)
    {
        parkingPass.amountMinutes = minutes;
    }

    public int getMinutes()
    {
        return parkingPass.amountMinutes;
    }

    public void incrementNoParkingPass()
    {
        parkingPass.noParkingPass++;
    }

    public void incrementParkingPass()
    {
        parkingPass.ParkingPass++;
    }

    public int getAmountParkingPass()
    {
        return parkingPass.ParkingPass;
    }

    public int getAmountNoParkingPass()
    {
        return parkingPass.noParkingPass;
    }

    public int getTotalRevenue()
    {
        return parkingPass.totalRevenue;
    }
    
    public RevenueView getRevenue()
    {
        return revenueview;
    }
    
    public ParkingPassView getPass()
    {
        return parkingpassview;
    }
}
