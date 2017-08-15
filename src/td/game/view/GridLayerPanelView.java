package td.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;
import td.game.model.MapGrid;
import td.game.model.Tower;

/**
 * This class is a JPanel that contains the map. This map drawn only once (when it is loaded from file). This panel is one of the layers of the LayeredMapPanel. 
 * @author Team3
 */
@SuppressWarnings("serial")
public class GridLayerPanelView extends JPanel 
{
	private Point mapTopLeft;
	private Point mapButtomRight;
	private MapGrid grid;
	private CellView cell;
	
	/**
	 * This constructor is used to set the dimensions
	 * @param dimension height and width of the panel.
	 */
	public GridLayerPanelView(Dimension dimension) 
	{
		this.grid = new MapGrid(1, 1);
		setMapTopLeft(new Point(0, 0));
		setMapButtomRight(new Point(0, 0));
		setOpaque(false);
		setDimension(dimension);
	}

	/**
	 * This method is used to set the grid
	 * @param grid new value of a grid
	 */
	public void setGrid(MapGrid grid) 
	{
		cell = new CellView();
		this.grid = grid;
	}

	/**
	 * This method draws the map on the screen when the repaint method is called. 
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();

		setMapTopLeft(new Point(initX, initY));
		setMapButtomRight(new Point(initX + grid.getWidth()
				* grid.getUnitSize(), initY + grid.getHeight()
				* grid.getUnitSize()));

		if (grid.getWidth() > 1) 
		{				
			for (int x = 0; x < grid.getWidth(); x++) 
			{
				for (int y = 0; y < grid.getHeight(); y++) 
				{
					int xCoordinate = grid.getUnitSize() * x + initX;
					int yCoordinate = grid.getUnitSize() * y + initY;
					if (grid.getTowers() == null) 
					{
						grid.setTowers(new Tower[1][1]);
					}
					cell.draw(g, grid.getCell(x, y), grid.getTowers(),
					xCoordinate, yCoordinate, x, y, false);
				}
			}
		}
	}

	/**
	 * This method is to get the preferred size
	 */
	@Override
	public Dimension getPreferredSize() 
	{
		int width = grid.getWidth() * grid.getUnitSize();
		int height = grid.getHeight() * grid.getUnitSize();
		return new Dimension(width, height);
	}

	/**
	 * This method is to get the top left corner of the map
	 * @return 
	 */
	protected Point getMapTopLeft() 
	{
		return mapTopLeft;
	}
	
	/**
	 * This method is to set the top left corner of the map
	 * @param mapTopLeft
	 */
	protected void setMapTopLeft(Point mapTopLeft) 
	{
		this.mapTopLeft = mapTopLeft;
	}
	
	/**
	 * This method is to get the map bottom right corner
	 * @return
	 */
	protected Point getMapButtomRight() 
	{
		return mapButtomRight;
	}

	/**
	 * This method is for setting up map bottom right corner
	 * @param mapButtomRight
	 */
	protected void setMapButtomRight(Point mapButtomRight) 
	{
		this.mapButtomRight = mapButtomRight;
	}

	/**
	 * This method is for getting the grid
	 * @return grid object
	 */
	public MapGrid getGrid() 
	{
		return grid;
	}
	
	/**
	 * This method is used to set up the dimension
	 * @param mapPanelDimension
	 */
	protected void setDimension(Dimension mapPanelDimension) 
	{
		setSize(mapPanelDimension);	
	}
}
