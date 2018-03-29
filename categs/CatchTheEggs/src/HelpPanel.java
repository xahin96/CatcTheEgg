import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

class HelpPanel extends JPanel
{
	Image helpbkg  = new ImageIcon("src/images/helpbkg.png").getImage();
	ImageIcon back = new ImageIcon("src/buttons/backBtn.png");
	
	JButton backBtn = new JButton("");

	HelpPanel()
	{
		backBtn.setIcon(back);
		setFocusable(true);
		add(backBtn);
		
		backBtn.addMouseListener(new MouseAdapter()
		  {
			public void mouseClicked(MouseEvent me)
			{
				CteGame.cl.show(CteGame.cards, "MenuPanel"); //show menuPanel when backBtn button is clicked
			}	
		  });
	}//end constructor
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(helpbkg, 0,0, null); // draw help backBtnground
		repaint();
	}//end paintComponent
}//end class
