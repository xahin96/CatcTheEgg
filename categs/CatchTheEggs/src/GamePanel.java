import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


class GamePanel extends JPanel
{ 
	Image gameBkgrnd     = new ImageIcon("src/images/gamebkg.png").getImage();
	Image basket      = new ImageIcon("src/images/basket.png").getImage();
	Image egg         = new ImageIcon("src/images/egg.png").getImage();
	Image cEgg    	  = new ImageIcon("src/images/eggCrack.png").getImage();
	Image gameOverbkg = new ImageIcon("src/images/gameOverbkg.png").getImage();
	Image tempBkgrnd; //temporary background
	
	JButton restart = new JButton("");
	JButton exit    = new JButton("");
	
	int x_basket,y_basket; //basket x and y  coordinates
	int x_egg,y_egg; //  egg x and y coordinate
	//int x_cEgg , y_cEgg ;
	
	int pointsCount = 0;
    int timeleft    = 59;
	int counter     = 0;
	int gameOver    = 1;
	
	Random rand = new Random(); // for randomizing xcoord of eggs
	JLabel time;
	JLabel points;
	JPanel res = new JPanel();
	JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);
	
	//int eggFall ;
	
	GamePanel()
	{
		setLayout(null);
		setFocusable(true);
		tempBkgrnd = gameBkgrnd;
		
		x_basket = 450; 
		y_basket = 600;
		x_egg = (int)rand.nextInt(900);
		y_egg = 0;
		
	    time = new JLabel("Time: 59");
		time.setBounds(20, 10, 50, 20);
	    
	    points = new JLabel("Points: 0");
		points.setBounds(100,10,100,20);
		
		add(time);
		add(points);
		
		this.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode() == ke.VK_LEFT & x_basket>15)
				{
					x_basket-=25;
					repaint(); // redraw at new position
				}
				if(ke.getKeyCode() == ke.VK_RIGHT & x_basket<900)
				{
					x_basket+=25; // redraw at new position
					repaint();
				}
			}//end keypressed
		});
		
		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent me) 
			{
				x_basket = me.getX();
				if (x_basket > 900)
				{
					x_basket = 900;
				}
			}	
			@Override
			public void mouseDragged(MouseEvent e)
			{
				// TODO Auto-generated method stub		
			}
		});//end mousemotion
	}//end constructor
	
	void updateTime()
	{
		counter++;
		if(counter == 250) //for time delay 
		{
		   timeleft--;  //dec time left after 59 counts
		   counter = 0; //reseting  counter
		}
		time.setText("Time:"+timeleft);
	}
	
	void detectCollision()
	{
		Rectangle basketRect = new Rectangle(x_basket,y_basket,55,100); //making a rectangle on the basket
		Rectangle eggRect    = new Rectangle(x_egg,y_egg,45,67); //making a rectangle on egg
		
		if(eggRect.intersects(basketRect))
		{
			catchSound();
			
			pointsCount+=5; // 5 points on each catch
			points.setText("Points:"+pointsCount); // set the count
			y_egg = 0; //  next egg
			x_egg = rand.nextInt(900); // again randomizing x axis of egg
		   
			//eggFall = 0 ;
		}
		/*else
		{
			eggFall = 1 ;
		}*/
	}//end collision detection
	
	void checkGameOver() //checking and increasing speed of egg fall
	{
		if(timeleft <=59 && timeleft >50)
		{				
		  gameOver = 1;
		}
	    if(timeleft <=49 && timeleft >=40)
		{
			gameOver = 2;
		}
	    if(timeleft <=39 && timeleft >=30)
		{
			gameOver = 3;
		}
	    if(timeleft <=29 && timeleft >=20)
		{
			gameOver = 4;
		}
	    if(timeleft <=19 && timeleft >=10)
		{
			gameOver = 5;
		}
	    if(timeleft <=9 && timeleft >=1)
		{
			gameOver = 6;
		}
        if(timeleft <=0) // game ends
		{
        	gameOver = 7;
        	
            tempBkgrnd = gameOverbkg;
            
			JLabel yourScore = new JLabel("Your SCORE :" + pointsCount);
            yourScore.setBounds(410, 240, 600, 300);
            yourScore.setFont(new Font("Arial", Font.BOLD, 30));
			yourScore.setForeground(Color.black);
			add(yourScore);
			
			ImageIcon restartButton = new ImageIcon("src/buttons/restart.png");
			restart.setBounds(400, 550, 252,82);
			restart.setIcon(restartButton);
			add(restart);
			
			restart.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent me)
				{
					remove(yourScore);
					reset();
					CteGame.cl.show(CteGame.cards, "MenuPanel");
			     	tempBkgrnd=gameBkgrnd;
				}
		    });
			
			ImageIcon exitButton = new ImageIcon("src/buttons/exit.png");
			exit.setBounds(400, 470, 252,82);
			exit.setIcon(exitButton);
			add(exit);
			
			exit.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent me)
				{
					System.exit(0);
				}
		    });
		 } //end of game end
		} //end gameOver
	
	public void reset() //reseting all values
	{
		pointsCount = 0;
	    timeleft = 59;
		counter  = 0;
		gameOver = 1;
		remove(restart);
		remove(exit);
	}
	
	void fallEgg_1() 
	{
		if(y_egg >=650)
		{
			crackSound();

			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+1;
	}
	
	void fallEgg_2()
	{
		if(y_egg >=650)
		{
			crackSound();
			
			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+2;
	}
	
	void fallEgg_3()
	{
		if(y_egg >=650)
		{
			crackSound();
			
			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+3;
	}
	
	void fallEgg_4()
	{
		if(y_egg >=650)
		{
			crackSound();
			
			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+4;
	}
	
	void fallEgg_5()
	{
		if(y_egg >=650)
		{
			crackSound();	
			
			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+5;
	}
	
	void fallEgg_6() 
	{
		if(y_egg >=650)
		{
			crackSound();
			
			y_egg = 0;
			x_egg = rand.nextInt(900);
		}
		else
			y_egg=y_egg+6;
	}
	
	void crackSound()
	{
		try
		{
			File sound = new File("sound//eggcrack.wav");
		    AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
		    Clip clip = AudioSystem.getClip();
		    clip.open(ais);
		    clip.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}//end crackSound
	
    void catchSound()
	{
		try
		{
			File sound = new File("sound//eggcatch.wav");
		    AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
		    Clip clip = AudioSystem.getClip();
		    clip.open(ais);
		    clip.start();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}//end catchSound


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(tempBkgrnd,0,0,null); //game background
		
		checkGameOver();
		
		if(gameOver == 1)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_1();
			detectCollision();
		
		    g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		   // g2d.drawImage(cEgg, x_egg, 645, null) ;	
		}
		
		else if(gameOver == 2)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_2();
			detectCollision();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
	    else if(gameOver == 3)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_3();
			detectCollision();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
		
	    else if(gameOver == 4)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_4();
			detectCollision();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
		
	    else if(gameOver == 5)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_5();
			detectCollision();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
		
	    else if(gameOver == 6)
		{
			setFocusable(true);
			grabFocus();
			updateTime();
			fallEgg_6();
			detectCollision();
		
			g2d.drawImage(egg, x_egg, y_egg,null); //drawing egg at new position
			g2d.drawImage(basket, x_basket, y_basket, null); //drawing basket
		}
		repaint();	
	}//end paintComponent
}//end class