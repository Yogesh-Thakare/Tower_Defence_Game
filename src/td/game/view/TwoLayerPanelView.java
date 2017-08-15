package td.game.view;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JLayeredPane;

import td.game.model.MapGrid;
import td.game.model.MapObjects;
import td.game.model.Tower;
import td.game.services.GameStateController;
import td.game.services.MoneyController;

/**
 * This class is JLayeredPane that has two layers, one for map and one for towers and critters.
 * @author Team3
 */
public class TwoLayerPanelView extends JLayeredPane 
{
	private static final long serialVersionUID = 1L;
	public Thread mapT;
	private MapGrid grid;	
	private Point mapTopLeft;
	private Point mapButtomRight;	
	private GridLayerPanelView gridLayer;
	private GridItemsLayerPanelView otherItemsLayer;
	private GameInfoPanel gameInfoPanel;

	/**
	 * Information panel
	 * @return game information panel
	 */
	public GameInfoPanel getGameInfoPanel() 
	{
		return gameInfoPanel;
	}

	/**
	 * This method is used to set the game information panel
	 * @param gameInfoPanel - game information panel
	 */
	public void setGameInfoPanel(GameInfoPanel gameInfoPanel) 
	{
		this.gameInfoPanel = gameInfoPanel;
	}

	/**
	 * This method is used to represent some of the components of user interface
	 * @param dimension - map panel dimension
	 * @param gameInfoPanel - game information panel
	 * @param mainFrame - the main frame
	 */
	public TwoLayerPanelView(Dimension dimension, GameInfoPanel gameInfoPanel, MainFrame mainFrame) 
	{
		this.gameInfoPanel = gameInfoPanel;
		this.grid = new MapGrid(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));	
		gridLayer = new GridLayerPanelView(dimension);	
		otherItemsLayer = new GridItemsLayerPanelView(dimension, gameInfoPanel, mainFrame);
		add(gridLayer,new Integer(1));
		add(otherItemsLayer,new Integer(2));
	}
	
	/**
	 * This method is used to set a new value of a grid
	 * @param  grid new grid is being used in a game 
	 */
	public void setGrid(MapGrid grid) 
	{
		this.grid = grid;
		gridLayer.setGrid(grid);
		otherItemsLayer.setGrid(grid);
	}

	/**
	 * This method is used for getting grid layer
	 * @return current grid
	 */
	public MapObjects getGrid() 
	{
		return gridLayer.getGrid();
	}

	/**
	 * This method is used for getting map grid layer
	 * @return current map
	 */
	public MapGrid getGridMap() 
	{
		return otherItemsLayer.getGrid();
	}
	
	/**
	 * This method is used for getting other items panel
	 * @return other items panel
	 */
	public GridItemsLayerPanelView getOtherItemsPanel()
	{
		return otherItemsLayer;
	}
	
	/**
	 * This method is used for setting up towers
	 * @param towers new tower to be set
	 */
	public void setTowers(Tower[][] towers) 
	{
		otherItemsLayer.setTowers(towers);		
	}

	/**
	 * This method is used to get bank money
	 * @return bank amount
	 */
	public MoneyController getBank() 
	{
		return otherItemsLayer.getBank();
	}

	/**
	 * This method calculates the starting point of the map on the screen in pixels to draw the map right in the center of the screen.
	 * @param mapPanelDimension dimension of the map panel. 
	 * @return a point in pixel that represents the top-left corner of the map on the screen.
	 */
	private Point calculateMapStartingPoint(Dimension mapPanelDimension) 
	{
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) 
		{
			initX = (panelWidth - mapWidth) / 2;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) 
		{

			initY = (panelHeight - mapHeight) / 2;
		}

		return new Point(initX, initY);
	}
	
	/** This method calculates the bottom-right corner of the map on the screen in pixels to know the boundary of the map on the screen.
	 * @param mapPanelDimension dimension of the map panel.
	 * @return a point in pixel that represents the bottom-right corner of the map on the screen.
	 */
	private Point calculateMapBottomRight(Dimension mapPanelDimension)
	{
		int initX = 0;
		int initY = 0;

		int panelWidth = (int) mapPanelDimension.getWidth();
		int panelHeight = (int) mapPanelDimension.getHeight();
		int mapWidth = grid.getWidth() * grid.getUnitSize();
		int mapHeight = grid.getHeight() * grid.getUnitSize();

		if (panelWidth > grid.getWidth() * grid.getUnitSize()) 
		{
			initX = ((panelWidth - mapWidth) / 2)+mapWidth;

		}
		if (panelHeight > grid.getWidth() * grid.getUnitSize()) 
		{

			initY = ((panelHeight - mapHeight) / 2)+mapHeight;
		}

		return new Point(initX, initY);
	}
	
	/**
	 * Get map top left corner
	 * @return top left corner of a map
	 */
	public Point getMapTopLeft() 
	{
		return mapTopLeft;
	}
	
	/**
	 * sets top left corner of a map
	 * @param mapTopLeft sets top left corner of a map
	 */
	private void setMapTopLeft(Point mapTopLeft) 
	{
		this.mapTopLeft = mapTopLeft;
	}
	
	/**
	 * computes the size of a grid by given width and height 
	 */
	@Override
	public Dimension getPreferredSize() 
	{
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();
		return new Dimension(width, height);
	}
	
	/**
	 * bottom right corner of a map
	 * @return map bottom right corner of a map
	 */
	public Point getMapButtomRight() 
	{
		return mapButtomRight;
	}
	
	/**
	 * sets bottom right corner of a map
	 * @param mapButtomRight buttem right corner of a map
	 */
	private void setMapButtomRight(Point mapButtomRight) 
	{
		this.mapButtomRight = mapButtomRight;
	}

	/**
	 * When a map is loaded, this method is called to resize the panel and its layers.
	 * @param mapPanelDimension width and height of the map in cell numbers.
	 */
	public void resetSize(Dimension mapPanelDimension) 
	{
		Point mapTopLeft = calculateMapStartingPoint(mapPanelDimension);
		Point mapBottomRight = calculateMapBottomRight(mapPanelDimension);
		
		gridLayer.setMapTopLeft(mapTopLeft);
		gridLayer.setMapButtomRight(mapBottomRight);
		gridLayer.setDimension(mapPanelDimension);

		otherItemsLayer.setMapTopLeft(mapTopLeft);
		otherItemsLayer.setMapButtomRight(mapBottomRight);
		otherItemsLayer.setDimension(mapPanelDimension);
		otherItemsLayer.calcCritterStartingPoint();		
	}
	

	/**
	 * this method is used to set the game info
	 * @param game
	 */
	public void setGameInfo(GameStateController game) {
		otherItemsLayer.setGameInfo(game);
		
	}

	/**
	 * Get the wave number
	 * @return current wave number
	 */
	public int getWaveNumber() 
	{
		return otherItemsLayer.getWaveNumber();
	}
}
