import java.awt.CardLayout;
import java.io.File;
import java.awt.CardLayout;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sun.audio.* ;

public class CteGame extends JFrame
{	
	static MenuPanel mp = new MenuPanel();
	static HelpPanel hp = new HelpPanel();
	static GamePanel gp = new GamePanel();
	
	static CardLayout cl = new CardLayout();
	static JPanel cards = new JPanel();  // to contain the panels as cards
	
	CteGame()
	{
		cards.setLayout(cl);
		cards.add(mp, "MenuPanel");
		cards.add(hp, "HelpPanel");
		cards.add(gp, "GamePanel");
		cl.show(cards, "MenuPanel");
		add(cards);
		
		mainSound() ; //main background sound
		
		setTitle("Catch The Eggs Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 730);
		setVisible(true);
		setResizable(false);
	}//end constructor
	
	void mainSound()
	{
		try
		{
			File sound = new File("sound//chick.wav");
		    AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
		    Clip clip = AudioSystem.getClip();
		    clip.open(ais);
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
		    clip.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void main(String [] args)
	{
		new CteGame();
		
	}//end main
}//end class