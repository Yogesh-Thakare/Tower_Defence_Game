package td.game.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import td.game.constants.MapConstants;

/**
 * This class is used for map grid
 * @author Team3
 */
public class MapGrid extends MapObjects
{
	private static final long serialVersionUID = 1L;
	private Tower[][] towers;

	/**
	 * This method is used to set map grid height and width
	 * @param width Map width
	 * @param height Map height
	 */
	public MapGrid(int width, int height) 
	{
		super(width, height);
		towers = new Tower[width][height];
	}
	
	/**
	 * This method is used to get the map grid height and width
	 * @param grid Grid object
	 */
	public MapGrid(MapObjects grid) 
	{
		super(grid);
		towers = new Tower[grid.getWidth()][grid.getHeight()];
	}
	
	/**
	 * This method is used to get the towers
	 * @return 2D array of Tower
	 */
	public Tower[][] getTowers() 
	{
		return towers;
	}
	
	/**
	 * This method is used to update the towers that are on the map
	 * @param towers list of towers on the grid
	 */
	public void setTowers(Tower[][] towers) 
	{
		if (towers != null) 
		{
			this.towers = Arrays.copyOf(towers, towers.length);
		}
	}
	
	/**
	 * This method is used to draw the map on the screen
	 * @param g to paint the component
	 */
	public void draw(Graphics g) 
	{
		for (int x = 0; x < getWidth(); x++) 
		{
			for (int y = 0; y < getHeight(); y++) 
			{
				Color color = Color.green;
				switch (getCell(x, y)) 
				{
					case PATH:
						color = MapConstants.PATH_COLOR;
						break;
					case SCENERY:
						color = MapConstants.SCENERY_COLOR;
						break;
					case ENTRANCE:
						color = MapConstants.ENTRANCE_COLOR;
						break;
					case EXIT:
						color = MapConstants.EXIT_COLOR;
						break;
					case TOWER:
						break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(), getUnitSize());
			}
		}
	}
}
