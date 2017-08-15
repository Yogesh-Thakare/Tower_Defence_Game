package td.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import td.game.constants.TowerCharactersticsConstants;
import td.game.constants.GameConstants;
import td.game.model.ChambyTower;
import td.game.model.CitidelleTower;
import td.game.model.Critter;
import td.game.model.LifeManager;
import td.game.model.Location;
import td.game.model.MapContents;
import td.game.model.MapGrid;
import td.game.model.MapObjects;
import td.game.model.CritterNormalMove;
import td.game.model.HeleneTower;
import td.game.model.Tower;
import td.game.model.TowerFeatureDecorator;
import td.game.model.TowerLevelEnum;
import td.game.model.Wave;
import td.game.services.CellService;
import td.game.services.CritterBurnService;
import td.game.services.CritterFreezeService;
import td.game.services.CritterSplashService;
import td.game.services.DefenderInformer;
import td.game.services.GameStateController;
import td.game.services.Log4jLogger;
import td.game.services.MapController;
import td.game.services.MoneyController;
import td.game.services.PathService;
import td.game.services.PositionService;
import td.game.services.TowerFactory;
import td.game.services.WaveFactory;
import td.game.services.LogController;

/**
 * This Class is a Jpanel that gets rendered as a layer in
 * LayeredMapPanel class. It is responsible for tasks related to Towers
 * and Critters on the ui side.
 * @author Team3 
 */
@SuppressWarnings("serial")
public class GridItemsLayerPanelView extends JPanel implements Observer,Runnable 
{
	private Tower[][] towers;
	private int x, y;
	private MoneyController bank;
	private long availFunds;
	private MapGrid grid;
	private TowerInspectionPanel inspection;
	public Thread critterT, mapT;
	private CellView cell;
	private Location[] path;
	private Wave wave;
	private int waveNumber=1;
	private Icon[] critterImage;
	private boolean waveStarted;
	private DefenderInformer informer;
	private Tower defender;
	private Critter target;
	private Map<Tower, Critter> defenderTargetPair;
	private GameInfoPanel gameInfoPanel;
	private List<Critter> currentWaveAlienList;
	private MainFrame mainFrame;
	private LifeManager life = LifeManager.getInstance();
	private int bulletCounter = 0;
	private int sleepLength = 10;
	private HashMap<Tower, Integer> shootingSchedule = new HashMap<Tower, Integer>();
	private CritterBurnService burnEffect = new CritterBurnService();
	private CritterSplashService splashEffect = new CritterSplashService();
	private CritterFreezeService freezeEffect = new CritterFreezeService();
	private CellService mapCellService = new CellService();
	private static final Log4jLogger logger = new Log4jLogger();
	LogConsole logViewer= new LogConsole(LogController.getInstance());

	/**
	 * Constructor to set the layer panel view
	 * @param dimension
	 *            height and width of the panel
	 * @param gameInfoPanel
	 *            a reference to the instance of the game info panel that
	 *            appears on top of the game ui (life, bank, wave info)
	 * @param mainFrame
	 *            a reference to the instance of the main frame that contains ui
	 *            components.
	 */
	public GridItemsLayerPanelView(Dimension dimension,GameInfoPanel gameInfoPanel, MainFrame mainFrame) 
	{
		
		this.mainFrame = mainFrame;
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new MapGrid(1, 1);
		this.bank = MoneyController.getInstance();
		availFunds = this.bank.getBalanceMoney() - this.bank.getCurrentBalanceMoney();
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());
		mapT = new Thread(this);
		mapT.start();
		setOpaque(false);
		setDimension(dimension);
		critterImage = new Icon[GameConstants.WAVE_SIZE];
		informer = new DefenderInformer();
		defenderTargetPair = new HashMap<Tower, Critter>();
		gameInfoPanel.setWave(waveNumber);
	}

	/**
	 * This method re-initializes the grid when a map is loaded/ 
	 * @param grid
	 *            the map that is loaded from a file
	 */
	public void setGrid(MapGrid grid) 
	{
		cell = new CellView();
		this.grid = grid;
		towers = new Tower[grid.getWidth()][grid.getHeight()];
	}

	/**
	 * This Calculates the critters' starting point on the map in pixels. 
	 * @return initial position of the critters.
	 */
	protected Location calcCritterStartingPoint() 
	{
		
		PathService pathService = new PathService();
		this.path = pathService.pathFinder(grid.getContent());

		return mapCellService.getPixelOfCell(grid.getEntranceLocation());
	}

	/**
	 * This method draws everything that needs to be drawn at this iteration. 
	 * @param g Graphics component
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int initX = (int) mapCellService.getMapTopLeft().getX();
		int initY = (int) mapCellService.getMapTopLeft().getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
		* grid.getUnitSize(), initY + grid.getHeight()
		* grid.getUnitSize()));

		if (grid.getTowers() == null) 
		{
			grid.setTowers(new Tower[1][1]);
		}
		for (int x = 0; x < grid.getWidth(); x++) 
		{
			for (int y = 0; y < grid.getHeight(); y++) 
			{
				int xCoordinate = grid.getUnitSize() * x + initX;
				int yCoordinate = grid.getUnitSize() * y + initY;
				if (grid.getCell(x, y) == MapContents.TOWER) 
				{
					cell.draw(g, grid.getCell(x, y), grid.getTowers(),
					xCoordinate, yCoordinate, x, y, true);
				}
			}
		}

		if (waveStarted) 
		{
			for (int i = 0; i < currentWaveAlienList.size(); i++) 
			{
				Location pos = ((CritterNormalMove) (currentWaveAlienList.get(i)
				.getMovingBehaviour())).getPixelPosition();
				new CritterView().draw(g, critterImage[i], pos.getX(),
				pos.getY(), currentWaveAlienList.get(i).getLife());
			}

			Iterable<Entry<Tower, Critter>> its = defenderTargetPair.entrySet();
			for (Entry<Tower, Critter> pairs : its) 
			{
				Tower t2 = pairs.getKey();
				Critter c = pairs.getValue();
				if (isWithinRateOfFire(t2)) 
				{
					PositionService positionService = new PositionService();
					TowerFactory factory = new TowerFactory();
					int range = factory.getRange(t2);
					if (positionService.isInRange(t2.getTowerPosition(),
						c.getPath()[c.getCurrentPosition()], range)) 
					if (t2.getTowers().get(0).getClass().getSimpleName().equals(CitidelleTower.class.getSimpleName())) 
					{
						new IceShoot().drawBullet(g, mapCellService.convertMapCellToPixel(t2.getTowerPosition()),
						mapCellService.convertMapCellToPixel(path[c.getCurrentPosition()]));
					}
					else if (t2.getTowers().get(0).getClass().getSimpleName().equals(HeleneTower.class.getSimpleName())) 
					{
						new SplashShoot().drawBullet(g, mapCellService.convertMapCellToPixel(t2.getTowerPosition()),
						mapCellService.convertMapCellToPixel(path[c.getCurrentPosition()]));
					}
					else if (t2.getTowers().get(0).getClass().getSimpleName().equals(ChambyTower.class.getSimpleName())) 
					{
						new FireShoot().drawBullet(g, mapCellService.convertMapCellToPixel(t2.getTowerPosition()),
						mapCellService.convertMapCellToPixel(path[c.getCurrentPosition()]));
					}
				}
			}
		}
	}

	/* 
	 * This method is to get the preferred size
	 * @see javax.swing.JComponent#getPreferredSize()
	 */
	@Override
	public Dimension getPreferredSize() 
	{
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();
		return new Dimension(width, height);
	}

	/**
	 * This method is to display the tower upgrade panel.
	 */
	private void towerUpgradePanels() 
	{
		if (towers[x][y] != null) 
		{
			if (inspection != null) 
			{
				inspection.close();
				inspection = null;
			}
			inspection = new TowerInspectionPanel(towers[x][y]);
			inspection.addObserver(this);
		}
	}

	/**
	 * This method adds a tower to the map and also displays in the UI. Before
	 * adding the tower it makes sure the user has enough money. 
	 * @param x 
	 *            x coordinate of the cell that towers is going to be placed.
	 * @param y
	 *            y coordinate of the cell that towers is going to be placed.
	 */
	private void addTower(int x, int y) 
	{
		if (grid.getCell(x, y) == MapContents.SCENERY) 
		{
			TowerFactory factory = new TowerFactory();
			Tower tower;
			String towerType = SelectedTower.getTowerType();
			
			switch (towerType) 
			{
			case TowerCharactersticsConstants.HELENE_TOWER_TYPE:
				tower = factory.getTower(TowerCharactersticsConstants.HELENE_TOWER_TYPE, TowerLevelEnum.ONE);
				break;
			case TowerCharactersticsConstants.CITIDELLE_TOWER_TYPE:
				tower = factory.getTower(TowerCharactersticsConstants.CITIDELLE_TOWER_TYPE, TowerLevelEnum.ONE);
				break;
			case TowerCharactersticsConstants.CHAMBY_TOWER_TYPE:
				tower = factory.getTower(TowerCharactersticsConstants.CHAMBY_TOWER_TYPE, TowerLevelEnum.ONE);
				break;
			default:
				tower = factory.getTower(TowerCharactersticsConstants.HELENE_TOWER_TYPE, TowerLevelEnum.ONE);
			}

			tower.setShootingStrategy(TowerCharactersticsConstants.NEARTOEND_STRATEGY);
			if (tower.towerCost() < bank.getBalanceMoney() - bank.getCurrentBalanceMoney()) 
			{
				bank.setCurrentBalanceMoney(tower.towerCost());
				availFunds = this.bank.getBalanceMoney() - this.bank.getCurrentBalanceMoney();
				gameInfoPanel.setBank((int) availFunds);
				towers[x][y] = tower;
				tower.setTowerPosition(new Location(x, y));
				grid.setCell(x, y, MapContents.TOWER);
				grid.setTowers(towers);
				LogController.getInstance().addTowerLog(waveNumber, tower,
						"Placement");
				informer.registerObserver((TowerFeatureDecorator) tower);
				((TowerFeatureDecorator) tower).addObserver(this);
				shootingSchedule.put(tower, 0);
				repaint();
			} 
			else 
			{
				JOptionPane.showMessageDialog(new JFrame(),
				"you don't have enough money !!!", "Alert",
				JOptionPane.WARNING_MESSAGE);
			}
			SelectedTower.setInstance(SelectedTower.getTowerType(),
			SelectedTower.getTower(), false);
		}
	}

	/**
	 * This method does the operation on tower
	 */
	public void towerOperation() 
	{
		boolean addTowerFlag = SelectedTower.getAddTowerFlag();
		if (addTowerFlag) 
		{
			addTower(x, y);
		} 
		else 
		{
			towerUpgradePanels();
		}
	}

	/**
	 * When a mouse click occurs, this method converts the pixel location of the
	 * pointer to a cell location.
	 * @param mouseX
	 *            x coordinate of mouse pointer
	 * @param mouseY
	 *            y coordinate of mouse pointer
	 */
	public void setCellLocation(int mouseX, int mouseY) 
	{
		int i = (mouseX - (int) mapCellService.getMapTopLeft().getX() - 75 / 2) / grid.getUnitSize();
		int j = (mouseY - (int) mapCellService.getMapTopLeft().getY() - 120) / grid.getUnitSize();
		this.x = i;
		this.y = j;
	}

	/**
	 * This method returns the tower
	 */
	public Tower[][] getTowers() 
	{
		return towers;
	}

	/**
	 * This method returns the Grid
	 */
	public MapGrid getGrid() 
	{
		return grid;
	}

	/**
	 * This method sets the tower
	 */
	public void setTowers(Tower[][] towers) 
	{
		this.towers = Arrays.copyOf(towers, towers.length);
	}

	/**
	 * return single instance
	 */
	public MoneyController getBank() 
	{
		return bank;
	}

	/**
	 * <b>This method updates the tower stats and the bank balance and removes
	 * the a tower from the map. It also performs the required actions after a
	 * target has been identified for a tower.</b>
	 * @param arg1 is object is of type tower perform operation
	 * @param subject observer object
	 */
	@Override
	public void update(Observable subject, Object arg1) 
	{
		if (subject instanceof TowerInspectionPanel) 
		{
			notifiedByInspectionPanel();
		}
		if (subject instanceof TowerFeatureDecorator) 
		{
			shootingOps((TowerFeatureDecorator) subject);
		}
	}

	/**
	 * This method is used to calculating shooting range of critter
	 * @param subject
	 */
	private void shootingOps(TowerFeatureDecorator subject) 
	{
		target = subject.getTarget();
		defender = subject.getDefender();

		Tower t2 = defender;
		Critter c = target;

		PositionService positionService = new PositionService();
		TowerFactory factory = new TowerFactory();
		int range = factory.getRange(t2);
		if (positionService.isInRange(t2.getTowerPosition(),
		c.getPath()[c.getCurrentPosition()], range)) 
		{
			shoot(defender, target);
			removeDeadCritters();
		}
	}

	/**
	 * This method calls further functions for action to be performed on selection of "Upgrade" or
	 * "Sell" option on tower 
	 */
	private void notifiedByInspectionPanel() 
	{
		switch (inspection.getPerformedAction()) 
		{
		case "Upgrade":
			upgradeTower();
			break;
		case "Sell":
			clearTower(x, y);
			break;
		default:
			break;
		}
	}

	/**
	 * This method does the tasks that happen when a critter is shot by a tower,
	 * like decreasing life. 
	 * @param defender 	tower (shooter)
	 * @param target   	critter (to be shot)
	 */
	private void shoot(Tower defender, Critter target) 
	{
		defenderTargetPair.put(defender, target);
		if (shootingSchedule.get(defender) >= 100) 
		{
			shootingSchedule.put(defender, 0);
		} 
		else 
		{
			shootingSchedule.put(defender, shootingSchedule.get(defender) + 1);
		}

		if (isWithinRateOfFire(defender)) 
		{
			TowerFactory factory = new TowerFactory();
			String defenderType = factory
					.getDecoratedName(defender.getTowers());
			switch (defenderType) 
			{
				case TowerCharactersticsConstants.CHAMBY_TOWER_TYPE:
					shootAndBurn(defender, target);
					break;
				case TowerCharactersticsConstants.HELENE_TOWER_TYPE:
					splashEffect.splashCritter(defender, target, wave, getTowerPropertyBasedOnLevel(defender), path);
					break;
				case TowerCharactersticsConstants.CITIDELLE_TOWER_TYPE:
					int range = factory.getRange(defender);
					shootAndFreeze(target,range);
					break;
			}
			try 
			{
			} 
			catch (Exception e2) 
			{				
				logger.writer("shooting :" + defender.Id + " --> " + target.Id
						+ "(" + target.getCurrentPosition() + ")", e2);
			}
		}
	}
	
	/*
	 * This method is for shooting and freezing critters.F
	 * @param target
	 * @param range
	 */
	private void shootAndFreeze(Critter target,int range) 
	{	
		freezeEffect.freezeCritter(target, range, 10);		
	}
	
	/**
	 * This method is for shooting and burning critter
	 * @param defender
	 * @param target
	 */
	private void shootAndBurn(Tower defender, Critter target) 
	{
		int power = getTowerPropertyBasedOnLevel(defender);
		target.setLife(target.getLife() - power);
		burnEffect.addBurningCritter(target, getTowerPropertyBasedOnLevel(defender)* sleepLength);
		burnEffect.burnCritter();
	}
	
	/**
	 * Getting tower properties based on its level
	 * @param defender
	 * @return
	 */
	private int getTowerPropertyBasedOnLevel(Tower defender) 
	{
		switch (defender.getLevel()) 
		{
			case ONE:
				return 1;
			case TWO:
				return 2;
			case THREE:
				return 3;
		}
		return 1;
	}

	/**
	 * To calculate if the critter is in range to fire
	 * @param defender
	 * @return
	 */
	private boolean isWithinRateOfFire(Tower defender) 
	{
		int rateOfFire = getTowerPropertyBasedOnLevel(defender);
		if (shootingSchedule.get(defender) <= rateOfFire * sleepLength * 3) 
		{
			return true;
		}
		return false;
	}

	/**
	 * This method is called when the user has finished upgrading a tower.
	 */
	private void upgradeTower() 
	{
		towers[x][y] = inspection.getTower();
		availFunds = this.bank.getBalanceMoney() - this.bank.getCurrentBalanceMoney();
		gameInfoPanel.setBank((int) availFunds);
		LogController.getInstance().addTowerLog(waveNumber, towers[x][y],
				"Upgrade");
		repaint();
	}

	/**
	 * When a user sells a tower, this method get's called to remove it from the
	 * map. 
	 * @param x           x location of the tower to be removed
	 * @param y           y location of the tower to be removed.
	 */
	private void clearTower(int x, int y) 
	{
		if ((x < grid.getWidth()) && (y < grid.getHeight())&& (grid.getCell(x, y) == MapContents.TOWER)) 
		{
			availFunds = this.bank.getBalanceMoney() - this.bank.getCurrentBalanceMoney();
			gameInfoPanel.setBank((int) availFunds);
			LogController.getInstance().addTowerLog(waveNumber, towers[x][y],
					"Sell");
			informer.removeObserver((TowerFeatureDecorator) towers[x][y]);
			defenderTargetPair.remove(towers[x][y]);
			shootingSchedule.remove(towers[x][y]);
			towers[x][y] = null;
			grid.setTowers(towers);
			grid.setCell(x, y, MapContents.SCENERY);
			repaint();
		}
	}

	/**
	 * This method runs as long as the thread is running to update the screen
	 * and all properties of this class.
	 */
	public void run() 
	{
		while (true) 
		{
			System.out.print("");
			if (waveStarted) 
			{
				updateBulletCounter();
				for (int j = 0; j < currentWaveAlienList.size(); j++) 
				{
					Critter critter = currentWaveAlienList.get(j);
					if (critter.getCurrentPosition() != ((CritterNormalMove) critter.getMovingBehaviour()).getPath().length - 1) 
					{
						critter.performMovingBehaviour();
						int i = ((CritterNormalMove) critter.getMovingBehaviour())
								.getCurrentPosition();
						Location p = path[i];
						critter.setCurrentPosition(i);
						if (towers[x][y] != null) 
						{
							informer.setAlienPosition(p.getX(), p.getY(),
									critter, towers[x][y].getShootingStrategy());
						}
					} 
					else 
					{
						System.out.println("At exit point.");
						updatePlayerLife(1);
						currentWaveAlienList.remove(j);
						if (isWaveComplete()) 
						{
							waveCompleted();
						}

					}
				}
				repaint();
				try 
				{
					Thread.sleep(sleepLength);
				} 
				catch (InterruptedException e) 
				{
					logger.writer(this.getClass().getName(), e);
				}
			}
		}
	}

	/**
	 * This method is used to update bullet counter
	 */
	private void updateBulletCounter() 
	{
		if (this.bulletCounter >= sleepLength * 100) 
		{
			this.bulletCounter = 0;
		} 
		else 
		{
			this.bulletCounter++;
		}
	}

	/**
	 * This method checks the wave to see if there are any critters left in it. 
	 * @return true if there all critters are killed.
	 */
	private boolean isWaveComplete() 
	{
		if (currentWaveAlienList.size() <= 0) 
		{
			return true;
		}
		return false;
	}

	/**
	 * This method is called every time a critter is shot. it removes dead
	 * critters from the map.
	 */
	private void removeDeadCritters() 
	{
		for (int i = 0; i < currentWaveAlienList.size(); i++) 
		{
			Critter c = currentWaveAlienList.get(i);
			if (c.getLife() <= 0) 
			{
				bank.addBalanceMoney(((long) c.returnTowerHealth()));
				availFunds = (long) (this.bank.getBalanceMoney() + c.returnTowerHealth());
				gameInfoPanel.setBank((int) availFunds);
				for (Tower[] t1 : towers) 
				{
					for (Tower t : t1) 
					{
						if (t != null) 
						{
							Map<Critter, Location> map = ((TowerFeatureDecorator) t)
									.getCrittersLocation();
							map.remove(c);
							((TowerFeatureDecorator) t)
									.setCrittersLocation(map);
							System.out.println(c.Id);
						}
					}
				}
				currentWaveAlienList.remove(i);
				if (isWaveComplete()) 
				{
					waveCompleted();
				}
			}
		}
	}

	/**
	 * This method prints the message on console and increment wave count
	 */
	private void waveCompleted() 
	{
		if(waveNumber==5)
		{
			System.out.println("Game Over");
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}
		
		System.out.println("Wave is completed!");
		LogController.getInstance().addWaveLog(waveNumber, "Wave completed.");
		mainFrame.getGameControllerPanel().waveCompleted(++waveNumber);
		LogController.getInstance().addWaveLog(waveNumber, "New wave started");
		waveStarted = false;
		mainFrame.enableSaveLoadMenu();
	}
	
	/**
	 * This method is for saving the score
	 */
	private void saveScore() 
	{
		grid.addPlayLog(new Date().toString(), this.bank.getBalanceMoney()
				- this.bank.getCurrentBalanceMoney());		
		MapGrid vg = new MapGrid((MapObjects) grid);
		removeTowers(vg);
		(new MapController()).savePlayLog(vg, mainFrame.getMapFilePath());
	}
	
	
	/**
	 * This method is for saving the score
	 */
	void saveMapLog() 
	{
		grid.addMapLog(new Date().toString());		
		MapGrid vg = new MapGrid((MapObjects) grid);
		removeTowers(vg);
		(new MapController()).saveMapLog(vg, mainFrame.getMapFilePath());
	}
	
	/**
	 * This method is used to remove towers.
	 * @param vg
	 */
	private void removeTowers(MapGrid vg) 
	{
		for (int i = 0; i < vg.getWidth(); i++) 
		{
			for (int j = 0; j < vg.getHeight(); j++) 
			{
				if (vg.getCell(i, j) == MapContents.TOWER) 
				{
					vg.setCell(i, j, MapContents.SCENERY);
				}
			}
		}
	}

	/**
	 * when a critter reaches the end of the path, it decreases the player's
	 * life. 
	 * @param escapedCritters    the number of critters that have reached the exit point.
	 */
	private void updatePlayerLife(int escapedCritters) 
	{
		life.setLife(life.getLife() - escapedCritters);
		System.out.println("life: " + life.getLife());
		
		gameInfoPanel.setLife(life.getLife());
		if (life.getLife() <= 0) 
		{
			gameOver();
		}
	}

	/**
	 * Action to be performed when Game is over
	 */
	private void gameOver() 
	{
		saveScore();
		System.out.println("Game Over");
		mainFrame.displayScoreBoard(grid);
		JOptionPane.showMessageDialog(null, "Game Over");
		System.exit(0);
	}

	/**
	 * To pause the game
	 */
	@SuppressWarnings("deprecation")
	public void pauseGame() 
	{
		mapT.suspend();
	}

	/**
	 * Get map location
	 * @return
	 */
	protected Point getMapTopLeft() 
	{
		return mapCellService.getMapTopLeft();
	}

	/**
	 * Set map location
	 * @param mapTopLeft
	 */
	protected void setMapTopLeft(Point mapTopLeft) 
	{
		mapCellService.setTopLeftMap(mapTopLeft);
	}

	/**
	 * Get map location of bottom right
	 * @return
	 */
	protected Point getMapButtomRight() 
	{
		return mapCellService.getButtomRightMap();
	}

	/**
	 * Set map location of bottom right
	 * @param mapButtomRight
	 */
	protected void setMapButtomRight(Point mapButtomRight) 
	{
		mapCellService.setButtomRightMap(mapButtomRight);
	}

	/**
	 * Set the dimensions of the game map panel
	 * @param mapPanelDimension
	 */
	protected void setDimension(Dimension mapPanelDimension) 
	{
		setSize(mapPanelDimension);
	}

	/**
	 * To perform action
	 */
	public void performScene() 
	{
	}

	/**
	 * This method is called when the user clicks on the "new wave" button.
	 */
	public void startWave() 
	{	
		mainFrame.disableSaveLoadMenu();
		WaveFactory waveFactory = new WaveFactory();
		Location[] path = new PathService().pathFinder(grid.getContent());
		wave = waveFactory.getCreateWave("NormalCritter",
				calcCritterStartingPoint(), path,waveNumber);
		ClassLoader classLoader = getClass().getClassLoader();
		File file;

		currentWaveAlienList = new ArrayList<Critter>();
		for (int i = 0; i < wave.critters.size(); i++)
		{
			currentWaveAlienList.add(wave.critters.get(i));
		}

		for (int i = 0; i < currentWaveAlienList.size(); i++) 
		{
			((CritterNormalMove) (currentWaveAlienList.get(i).getMovingBehaviour())).setFreezeTime(calculateWaveSpeed(i,waveNumber));

			file = new File(classLoader.getResource(currentWaveAlienList.get(i).display()).getFile());
			critterImage[i] = new ImageIcon(file.getPath());
		}
		LogController.getInstance().addWaveLog(waveNumber,
				"Critters entered the map.");
		waveStarted = true;
	}
	
	/**
	 * Calculates the critter speed on the fly
	 * @param critterPos
	 * @param waveNumber
	 * @return
	 */
	public int calculateWaveSpeed(int critterPos, int waveNumber)
	{
		return critterPos * (250- 50*(waveNumber-1));
	}
	
	/**
	 * This method is used to resume the game
	 */
	@SuppressWarnings("deprecation")
	public void resumeGame() 
	{
		mapT.resume();
	}
	
	/**
	 * This method is used to set the game info
	 * @param game
	 */
	public void setGameInfo(GameStateController game) 
	{
		this.waveNumber = game.getWaveNum();
		setTowers(grid.getTowers());
		this.bank.setBalanceMoney(game.getBalance());
		this.bank.setCurrentBalanceMoney(game.getCurrentBalance());
		this.availFunds = this.bank.getBalanceMoney()+ 5;
		life.setLife(game.getLife());
		gameInfoPanel.setLife(life.getLife());
		gameInfoPanel.setBank(availFunds);
		gameInfoPanel.getWave();
		gameInfoPanel.setWave(waveNumber);
		for (Tower[] t : towers) 
		{
			for (Tower tower : t) 
			{
				if (tower != null) 
				{
					informer.registerObserver((TowerFeatureDecorator) tower);
					((TowerFeatureDecorator) tower).addObserver(this);
					shootingSchedule.put(tower, 0);
				}
			}
		}
	}
	
	/**
	 * This method is to get the wave number
	 * @return
	 */
	public int getWaveNumber() 
	{
		return this.waveNumber;
	}
}