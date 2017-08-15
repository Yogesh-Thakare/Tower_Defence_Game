package td.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import td.game.constants.Constants;
import td.game.model.MapGrid;
import td.game.model.MapObjects;
import td.game.model.Tower;
import td.game.services.LogController;
import td.game.services.GameStateController;
import td.game.services.Log4jLogger;
import td.game.services.MapController;

/**
 * This class loads the main window
 *  @author Team3
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener
{
	TwoLayerPanelView mapPanel;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mapMenu;
	private JMenuItem openMenuItem;
	private JMenuItem loadGameMenutItem;
	private JMenuItem saveGameMenuItem;
	private GameInfoPanel gameInfoPanel;
	private EmptyBarPanel emptyBarPanel;
	private GameControllerPanel gameControllerPanel;
	private String mapFilePath = "";
	private ScoreCard scoreBoardDialog;
	private JMenuItem mntmLogViewer;
	private JMenuItem mapLogViewer;
	private LogConsole logViewerDialog;
	private ScoreCard mapLogDialog;
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * This method is used to create the main launch frame.
	 */
	public MainFrame() 
	{		
		setUpMenuBar();
		setTitle(Constants.GAME_TITLE);
		setBounds(100, 100, 520, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		gameInfoPanel = new GameInfoPanel();
		gameInfoPanel.setBackground(Color.GRAY);
		contentPane.add(gameInfoPanel, BorderLayout.NORTH);

		emptyBarPanel = new EmptyBarPanel();
		contentPane.add(emptyBarPanel, BorderLayout.WEST);

		EmptyBarPanel emptyBarPanel_1 = new EmptyBarPanel();
		contentPane.add(emptyBarPanel_1, BorderLayout.EAST);

		mapPanel = new TwoLayerPanelView(getMapPanelDimention(), gameInfoPanel, this);
		contentPane.add(mapPanel, BorderLayout.CENTER);
		contentPane.setBackground(Color.GRAY);
		gameInfoPanel.setBackground(Color.GRAY);

		gameControllerPanel = new GameControllerPanel(mapPanel);
		gameControllerPanel.setBackground(Color.GRAY);
		contentPane.add(gameControllerPanel, BorderLayout.SOUTH);

		addMouseListener(new MouseEventHandler(mapPanel));
		addMouseMotionListener(new MouseEventHandler(mapPanel));

		setSize(1000, 800);
		setVisible(true);

		this.addComponentListener(new ComponentListener() 
		{
			public void componentResized(ComponentEvent e) 
			{
				System.out.println("resized");
				mapPanel.resetSize(getMapPanelDimention());
			}
			@Override
			public void componentHidden(ComponentEvent arg0) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void componentMoved(ComponentEvent arg0) 
			{
				// TODO Auto-generated method stub
			}
			@Override
			public void componentShown(ComponentEvent arg0) 
			{
				// TODO Auto-generated method stub
			}
		});
	}
	
	/**
	 * This method is used to set up menu bar
	 */
	private void setUpMenuBar() 
	{
		menuBar = new JMenuBar();
		mapMenu = new JMenu("Actions");
		openMenuItem = new JMenuItem(Constants.LOAD_MAP);
		openMenuItem.addActionListener(this);
		
		mapMenu.add(openMenuItem);
		menuBar.add(mapMenu);
		loadGameMenutItem = new JMenuItem(Constants.LOAD_GAME);
		loadGameMenutItem.addActionListener(this);
		mapMenu.add(loadGameMenutItem);

		saveGameMenuItem = new JMenuItem(Constants.SAVE_GAME);
		saveGameMenuItem.addActionListener(this);
		mapMenu.add(saveGameMenuItem);
		
		mntmLogViewer = new JMenuItem("Game Log");
		mntmLogViewer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					if (logViewerDialog != null) 
					{
						logViewerDialog.dispose();
					}
					logViewerDialog = new LogConsole(LogController.getInstance());
					logViewerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					logViewerDialog.setSize(500, 500);
					logViewerDialog.setVisible(true);
				} 
				catch (Exception e) 
				{
					logger.writer(this.getClass().getName(), e);
				}
			}
		});
		

		mapMenu.add(mntmLogViewer);		
		setJMenuBar(menuBar);
	}
	
	/**
	 * This method is used to fetch the value of the game controller panel
	 * @return gameControllerPanel object of type GameControllerPanel
	 */
	public GameControllerPanel getGameControllerPanel() 
	{
		return gameControllerPanel;
	}
	
	/**
	 * This method is used to set the value of the game controller panel
	 * @param gameControllerPanel object of type GameControllerPanel
	 */
	public void setGameControllerPanel(GameControllerPanel gameControllerPanel) 
	{
		this.gameControllerPanel = gameControllerPanel;
	}
	
	/**
	 * This method is used to get the height and width of the map panel
	 * @return computes the actual dimension of a map panel
	 */
	public Dimension getMapPanelDimention() 
	{
		int width = gameInfoPanel.getWidth() - (emptyBarPanel.getWidth() * 2);
		int height = emptyBarPanel.getHeight();
		return new Dimension(width, height);
	}
	
	/**
	 * This event specifies which type of action is to be performed for a map
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String menuItem = event.getActionCommand();
		switch (menuItem) 
		{
			case Constants.LOAD_MAP:
				openExistingMap();
				break;
				
			case Constants.LOAD_GAME:
				openLoadGame();
				break;
				
			case Constants.SAVE_GAME:
				saveGame();
				break;
		}
	}
	
	/**
	 * This method loads map from a file
	 */
	protected void openExistingMap() 
	{	
		JFileChooser openFile = new JFileChooser();
		try
		{	
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) 
			{
				MapController mapManager = new MapController();
				mapFilePath = openFile.getSelectedFile().getAbsolutePath();
				MapGrid grid = new MapGrid((MapObjects) mapManager.loadSavedMapFromFile(mapFilePath));
				displayScoreBoard(grid);
				mapPanel.setGrid(grid);
				resetGameState();
				mapPanel.repaint();
				LogController.getInstance().addWaveLog(1, "New wave started");
			}
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * This method displays some user interface components 
	 * @param grid the grid object
	 */
	public void displayScoreBoard(final MapObjects grid) 
	{
		mapLogViewer = new JMenuItem("Map Log");
		mapLogViewer.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					if (mapLogDialog != null) 
					{
						mapLogDialog.dispose();
					}
					mapLogDialog = new ScoreCard(grid);
					mapLogDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					mapLogDialog.setSize(500, 500);
					mapLogDialog.setVisible(true);
				} 
				catch (Exception e) 
				{
					logger.writer(this.getClass().getName(), e);
				}
			}
		});
		
		mapMenu.add(mapLogViewer);
	}
	
	/**
	 * This method is used to reset the game state
	 */
	private void resetGameState() 
	{
		mapPanel.setTowers(new Tower[(mapPanel.getGrid()).getWidth()][(mapPanel.getGrid()).getHeight()]);
		mapPanel.getBank().resetCurrentBalanceMoney();
		mapPanel.resetSize(getMapPanelDimention());
	}
	
	
	/**
	 * This method is used to save the game into a file
	 */
	protected void saveGame() 
	{
		JFileChooser saveFile = new JFileChooser();
		if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			String fileName = saveFile.getSelectedFile().getAbsolutePath();
			GameStateController game = new GameStateController(mapPanel.getGridMap(),mapPanel.getWaveNumber(), mapFilePath);
			GameStateController.save(fileName, game);
		}
	}
	
	/**
	 * This method is used to disable the LoadGame and SaveGame menu
	 */
	public void disableSaveLoadMenu() 
	{
		loadGameMenutItem.setEnabled(false);
		saveGameMenuItem.setEnabled(false);
	}
	
	/**
	 * This method is used to enable the LoadGame and SaveGame menu
	 */
	public void enableSaveLoadMenu() 
	{
		loadGameMenutItem.setEnabled(true);
		saveGameMenuItem.setEnabled(true);
	}
	
	/**
	 * This method is used to load the game from a file
	 */
	protected void openLoadGame() 
	{
		JFileChooser openFile = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) 
		{
			String fileName = openFile.getSelectedFile().getAbsolutePath();
			GameStateController game = GameStateController.load(fileName);
			this.mapFilePath = game.getMapLocation();
			mapPanel.setGrid(game.getMap());
			mapPanel.setGameInfo(game);
			mapPanel.resetSize(getMapPanelDimention());
			mapPanel.repaint();
		}
	}
	
	/**
	 * This method is used to return valid path for a map
	 * @return valid path of a map
	 */
	public String getMapFilePath() 
	{
		return mapFilePath;
	}
}
