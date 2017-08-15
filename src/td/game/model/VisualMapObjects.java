package td.game.model;

import java.awt.Color;
import java.awt.Graphics;
import td.game.constants.Constants;

@SuppressWarnings("serial")
/**
 * This class is used for initialization and setting up the grid
 * @author Team3
 */
public class VisualMapObjects extends MapObjects 
{
	/**
	 * This constructor is used for initialization of grid 
	 * @param grid use for initialization of grid
	 */
	public VisualMapObjects(MapObjects grid) 
	{
		super(grid);	
	}
		
	/**
	 * This constructor is used for map objects visualization
	 * @param width  grid width
	 * @param height grid height
	 */
	public VisualMapObjects(int width, int height) 
	{
		super(width, height);
	}
		
	/**
	 * This constructor is used for map objects visualization
	 * @param width  grid width
	 * @param height grid height
	 * @param cellType type of cell path scenery entry point 
	 */
	public VisualMapObjects(int width, int height, MapContents cellType) 
	{
		super(width, height, cellType);
	}
		
	/**
	 * This method is used to draw graphics
	 * @param g  to draw graphics
	 */
	@SuppressWarnings("incomplete-switch")
	public void render(Graphics g) 
	{
		for (int x = 0; x < getWidth(); x++) 
		{
			for (int y = 0; y < getHeight(); y++) 
			{
				Color color = Color.green;
				switch (getCell(x, y)) 
				{
					case PATH:
					color = Constants.PATH_COLOR;
					break;
					case SCENERY:
					color = Constants.SCENERY_COLOR;
					break;
					case ENTRANCE:
					color = Constants.ENTRANCE_COLOR;
					break;
					case EXIT:
					color = Constants.EXIT_COLOR;
					break;
				}
				g.setColor(color);
				g.fillRect(x * getUnitSize(), y * getUnitSize(), getUnitSize(),getUnitSize());	
			}
		}	
	}
}
