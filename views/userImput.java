package mvc.views;

import mvc.cars.*;
import mvc.controller.*;
import mvc.logic.*;
import mvc.runner.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

 

/**
 * Description of the class userImput
 * This class will make it possible to call a frame, in which you can call an ammount of steps to take in the simulator.
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 0.1
 */
public class userImput {  
	//Instances for the frame(s)
   private SimulatorView simulatorView = null;
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   
   /**
    * Constructor for the class
    * Here it will execute the methods to make a frame, and to make it possible to get a textbox
    * @param simulatorView
    */
   public userImput(SimulatorView simulatorView){
      prepareGUI();    						//Execute method for the GUI
      showTextFieldDemo();					//Execute method for text box
      this.simulatorView = simulatorView;	//Make sure that we can update the simulatorView in our methods soon
   }

   
   /**
    * This method will make a GUI, with buttons and a descrption
    */
   private void prepareGUI(){
      //Setup a main JFrame
	  mainFrame = new JFrame("Steps to take");		
      mainFrame.setSize(400,150);
      mainFrame.setLayout(new GridLayout(3, 1));	
  
      //Create components
      headerLabel = new JLabel("", JLabel.CENTER);  
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,25);
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      //Add all the components to the main Frame
      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);					
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   /**
    * This method will makme the textbox in the mainFrame, and will call the method to update the SimulatorView
    */
   public void showTextFieldDemo(){
      //Set text to be displayed in the frame
      headerLabel.setText("How many steps would you like to take?"); 
      
      JLabel  namelabel= new JLabel("Steps: ", JLabel.RIGHT);
      final JTextField userText = new JTextField(6);

      //Setting up a button with an actionListener
      JButton button = new JButton("Go!");
      button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            String data = userText.getText();                      
                       
            if(containsOnlyNumbers(data) == false) {
                statusLabel.setText("Previous Imput: " + data);
                
                    // Make a new JFrame for errors
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame,
                    "Please only enter numbers!" + 
                    JOptionPane.ERROR_MESSAGE);  
            }
            
            else {
            	
            	//Execute ammount of "steps" in SimulatorView
                int steps = Integer.parseInt(data);

                Simulator sim = new Simulator(simulatorView);
            
                sim.runMultipleSteps(steps);
                mainFrame.setVisible(false);
                statusLabel.setText("Previous Imput: "+ data);  
                button.setEnabled(false);
                
            }
         }
      }); 

      
      //Add all the information and contents to the mainFrame / "controlPanel"
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(button);
      mainFrame.setVisible(true);  
      
      
   }
   
   /**
    * This is a method that will make sure that the "int builder" will only accept integer values. 
    * @param data, the imput from the user that has been saved in the method "showTextFieldDemo()"
    * @return False if it is has no values, or 
    * @return True if there are only integer values
    */
   public boolean containsOnlyNumbers(String data) {        
        //It can't contain only numbers if it's null or empty...
        if (data == null || data.length() == 0)
            return false;

        for (int i = 0; i < data.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(data.charAt(i)))
                return false;
        }

        //Return true if we only found integer values
        return true;
    }
}