package td.game.launch;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import td.game.view.MainFrame;
import td.game.view.MapFrame;

/**
 * This class is used to launch the home page or the start page of the Tower Defense Game. The user will be 
 * navigated to the MainFrame class on click of start game button.
 * @author Team3
 */
public class GameMainApp 
{
	JFrame homePage = new JFrame("Tower Defense Game - Team 3");
	
	/**
	 * This is the default constructor of GameMainApp class.
	 */
	private GameMainApp() 
	{		
	}
	
	/**
	 * This is the main method of the class.
	 * @param args accepts arguments.
	 */
	public static void main(String[] args) 
	{	
		GameMainApp objGame= new GameMainApp();	
		objGame.startGame();	
	}
	
	/**
	 * This function is used to launch the start page of the game. On click of start game button the screen is navigated
	 * to the MainFrame. On click of exit button the game will close.
	 */
	public void startGame()
	{
		homePage.setVisible(true);
		homePage.setSize(1300, 700);   //(width, height)
		homePage.setDefaultCloseOperation(homePage.EXIT_ON_CLOSE);
		
		JLabel title = new JLabel("Tower Defense Game- Team 3");
		title.setFont(new Font("Arial",Font.BOLD,50));
		
		JButton cancel = new JButton("Exit");
		JButton loadGame = new JButton("Play Game");
		JButton designMap = new JButton("Design Map");
		JButton loadMap = new JButton("Load Map");
		JButton help = new JButton("How To Play");
		
		homePage.setLayout(null);
		
		title.setBounds(300, 100,7000, 50);
		homePage.add(title);
		
		loadGame.setBounds(500,190,250,45); //x-coordinate,y-coordinate,width,height
		homePage.add(loadGame);
		
		designMap.setBounds(500, 250,250,45); //x-coordinate,y-coordinate,width,height
		homePage.add(designMap);
		
		loadMap.setBounds(500, 310,250,45); //x-coordinate,y-coordinate,width,height
		homePage.add(loadMap);	
			
		help.setBounds(500, 370,250,45); //x-coordinate,y-coordinate,width,height
		homePage.add(help);
		
		cancel.setBounds(500, 430,250,45); //x-coordinate,y-coordinate,width,height
		homePage.add(cancel);		
		
		title.setBackground(Color.BLUE);
		title.setForeground(Color.WHITE);
		
		cancel.setBackground(Color.white);
		cancel.setForeground(Color.black);
		cancel.setFont(new Font("Arial",Font.BOLD,24));
		
		help.setBackground(Color.white);
		help.setForeground(Color.black);
		help.setFont(new Font("Arial",Font.BOLD,24));
		
		loadGame.setBackground(Color.white);
		loadGame.setForeground(Color.black);
		loadGame.setFont(new Font("Arial",Font.BOLD,24));
		
		designMap.setBackground(Color.white);
		designMap.setForeground(Color.black);
		designMap.setFont(new Font("Arial",Font.BOLD,24));
		
		loadMap.setBackground(Color.white);
		loadMap.setForeground(Color.black);
		loadMap.setFont(new Font("Arial",Font.BOLD,24));
		
		homePage.getContentPane().setBackground(new Color(34,139,34));	
		
		loadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{		
				launchGame();			
			}		
		});
		
		help.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{		
				gamePlayInstructions();
			}		
		});
		
		designMap.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{		
				designMap();
				
			}		
		});
		
		loadMap.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{		
				loadMap();
				
			}		
		});
		
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				homePage.dispose();
			}		
		});		
	}
	
	/**
	 * This Function is fired on click of how to play from help menu bar. This will display the
	 * instructions to the user on game play
	 */
	public void gamePlayInstructions() 
	{
		JFrame howToPlay = new JFrame("How to Play Tower Defence");
		howToPlay.setVisible(true);
		howToPlay.setSize(400, 400); 
		howToPlay.getContentPane().setBackground(new Color(34,139,34));	
		
		JLabel instructions = new JLabel("<html>The game consists of a map in the form "
				+ "of a grid <br> where a path needs to be created by the user. This path <br>"
				+ "will consist of a start point and an exit point.  Critters<br> will move "
				+ "from the start point to the end point. The <br>user needs to place towers "
				+ "on the map outside the path which will <br>fire at the critters as they "
				+ "move from start to end point. <br>The user needs to destroy all the critters "
				+ "using the tower <br>before they reach the exit point and earn coins.</html>");
		instructions.setFont(new Font("Arial",Font.PLAIN,14));
		
		howToPlay.add(instructions);
	}
	
	/**
	 * This is function is called on click of design map button and it navigates the user to design map page
	 */
	public void designMap()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MapFrame mapFrame = new MapFrame();
					mapFrame.designMap();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * This is function is called on click of load map button and it navigates the user to load map page
	 */	
	public void loadMap()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MapFrame mapFrame = new MapFrame();
					mapFrame.loadOpenMap();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * This is function is called on click of Start game button and it navigates the user to MainFrame
	 */
	public void launchGame()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainFrame mainFrame = new MainFrame();
					mainFrame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
