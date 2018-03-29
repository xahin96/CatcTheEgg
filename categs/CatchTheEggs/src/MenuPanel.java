import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class MenuPanel extends JPanel
{
	JButton play = new JButton("");
	JButton help = new JButton("");
	JButton exit = new JButton("");
	
	Image menuBkgrnd = new ImageIcon("src/images/menubkg.png").getImage();  //menu background
	
	/* Setting icons on buttons */
	ImageIcon playbtn = new ImageIcon("src/buttons/play.png"); 
	ImageIcon helpbtn = new ImageIcon("src/buttons/help.png");
	ImageIcon exitbtn = new ImageIcon("src/buttons/exit.png");

	JPanel center = new JPanel(); //adding another jpanel in a panel for boxLayout
	
	MenuPanel()
	{
		center.setLayout(new BoxLayout(center,BoxLayout.X_AXIS)); //setting box layout 
		add(center);
		
		play.setIcon(playbtn); 
		help.setIcon(helpbtn);
		exit.setIcon(exitbtn);
		
		center.add(play);
		center.add(help);
		center.add(exit);

		play.addMouseListener(new Click());
		help.addMouseListener(new Click());
		exit.addMouseListener(new Click());
		
	}//end constructor
	
	class Click extends MouseAdapter
	{ //internal friendly class
	
		public void mouseClicked(MouseEvent me)
        {
			if(me.getSource()== play)
			{
				CteGame.cl.show(CteGame.cards, "GamePanel"); //show gamePanel when play is clicked
			}
			if(me.getSource()== help)
			{
				CteGame.cl.show(CteGame.cards, "HelpPanel"); //show helpPanel when help is clicked
			}	
			if(me.getSource()== exit)
			{
				System.exit(0);  //exit application when exit is clicked
			}
		}//end mouseClick
	}//end mouseAdapter
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); //calling the super class functions
		Graphics2D g2d = (Graphics2D)g; //casting to graphcis2D
		setFocusable(true);	 //setting the focus on the panel
	
		g2d.drawImage(menuBkgrnd, 0,0, null); //draw menu image
		repaint();
	}//end paintComponent
}//end GamePanel