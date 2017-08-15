package td.game.services;

import java.awt.Point;
import td.game.constants.MapConstants;
import td.game.model.Location;

/**
 * This Class is used to convert map cell to pixels
 * @author Team3
 */
public class CellService 
{
	private Point mapTopLeft = new Point();
	private Point mapButtomRight = new Point();
	
	/**
	 * This method converts a map cell to a pixel position on the screen
	 * @param cell position on the grid
	 * @return pixel position on the screen
	 */
	public Location convertMapCellToPixel(Location cell) 
	{
		Location pixel = getPixelOfCell(cell);
		if (pixel != null) 
		{
			pixel.setX(pixel.getX() + MapConstants.UNIT_SIZE / 2);
			pixel.setY(pixel.getY() + MapConstants.UNIT_SIZE / 2);
			return pixel;
		}
		return null;
	}
	
	/**
	 * This method is used to find the pixel of the given cell
	 * @param cell position on the grid
	 * @return pixel position on the screen
	 */
	public Location getPixelOfCell(Location cell) 
	{
		int initX = (int) mapTopLeft.getX();
		int initY = (int) mapTopLeft.getY();
		Location pixel;
		
		if (cell != null) 
		{
			pixel = new Location(
					(int) (initX + (cell.getX() * MapConstants.UNIT_SIZE)),
					(int) (initY + (cell.getY() * MapConstants.UNIT_SIZE)));

			return pixel;
		}
		return null;
	}
	
	/**
	 * This method is used to set the top left map position
	 * @param mapTopLeft top left point
	 */
	public void setTopLeftMap(Point mapTopLeft) 
	{
		this.mapTopLeft = mapTopLeft;
	}
	
	/**
	 * This method is used to set the value of the bottom right point
	 * @param mapButtomRight
	 */
	public void setButtomRightMap(Point mapButtomRight) 
	{
		this.mapButtomRight = mapButtomRight;
	}	
	
	/**
	 * This method is used to get the bottom right point
	 * @return mapButtomRight bottom right point
	 */
	public Point getButtomRightMap() 
	{
		return mapButtomRight;
	}
	
	/**
	 * This method is used to get the top left position of the map
	 * @return mapTopLeft top left point
	 */
	public Point getMapTopLeft() 
	{
		return mapTopLeft;
	}	
}
