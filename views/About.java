package mvc.views;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Description of the class About
 * This class contains information about the two frames "About Us!" and "Instructions".
 * 
 * @author ProjectGroup of ITV1C (Jesse Tijsma, Dennis Vrieling, Mark Dissel, Remand Knol)
 * @version 0.1
 */
public class About {
		//Instances for the frames
	   	private JFrame mainFrame;
	   	private JLabel headerLabel;
	   	private JButton button;
	   

	   /**
	    * Here is the code for the JFrame "Instructions", which displays the intstructions for the software
	    */
	   	public void Instructions(){
		      mainFrame = new JFrame("Instructions");
		      mainFrame.setSize(400,200);					//setup a frame for the items
		      mainFrame.setLayout(new GridLayout(3, 1));
		  
		      headerLabel = new JLabel("", JLabel.CENTER);	//setup a label for the text

		      mainFrame.add(headerLabel);
		      mainFrame.setVisible(true); 
		      
		      //Add text to header
		      headerLabel.setText("<html>Click on the start button, or on one of the steps buttons to run the program, after which several new tabs will open, all containing information concerning the simulation. </html>"); 

		      //Setting up a new button
		      button = new JButton("Close Window");
		      
		      button.addActionListener(new ActionListener() {	//Assign an action to the button
		      public void actionPerformed(ActionEvent e) {                        
	          
		      mainFrame.setVisible(false);
		      button.setVisible(true);
		      button.setEnabled(true);  						//make frame invisible after pressing "Close Window"
		         }
		      }); 

		      mainFrame.add(button);
		      mainFrame.setVisible(true);  
		   }
	   
	   	
	   	/**
	   	 * Here is the code for the (J)Frame "About Us!", which shows information about the company behind the software
	   	 */
	   		public void AboutUs(){
			      mainFrame = new JFrame("About Us!");
			      mainFrame.setSize(400,200);					//setup a frame for the items
			      mainFrame.setLayout(new GridLayout(3, 1));
			  
			      headerLabel = new JLabel("", JLabel.CENTER);	//setup a label for the text   

			      mainFrame.add(headerLabel);
			      mainFrame.setVisible(true); 					//add header to frame and make it visible
			      
			      //Add text to header
			      headerLabel.setText("<html>Team Johto is a private company that specializes in software Development, and small scale software engineering projects. You can contact us at de.vrieling@st.hanze.nl </html>");

			      //Stting up a new button
			      JButton button = new JButton("Close Window");
			    

			      button.addActionListener(new ActionListener() {	//assign an action to the button
			      public void actionPerformed(ActionEvent e) {                        
		            
			      mainFrame.setVisible(false);						//make frame invisible after use
			      button.setEnabled(true);  
			         }
			      }); 


			      mainFrame.add(button);							//Add button to the frame
			      mainFrame.setVisible(true);  
		   }
}
