package td.game.view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import td.game.model.MapObjects;

/**
 * This class is used for grid drawing in map editor and game panel
 * and also to visually reflect user actions on the grid.
 * @author Team3
 *
 */
@SuppressWarnings("serial")
public class ObjectCanvas extends Canvas 
{
	MapObjects grid = null;
	Dimension dimension = new Dimension();
	Graphics imageGraphic = null;

	/**
	 * class's public constructor
	 * @param newGrid new value for newGrid member variable
	 */
	public ObjectCanvas(MapObjects newGrid) 
	{
		grid = newGrid;
	}

	/**
	 * <b>Update</b>
	 * @param grid object of Grid
	 */
	public void setGrid(MapObjects grid) 
	{
		this.grid = grid;
	}

	/**
	 * draws the grid
	 * @param graphics Specified Graphics context
	 */
	@Override
	public void paint(Graphics graphics) 
	{
		grid.render(graphics);
	}
}
