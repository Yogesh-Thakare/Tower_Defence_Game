package td.game.services;

import td.game.model.Location;
import td.game.model.MapContents;

/**
 * The Interface IStartEndChecker.
 * @author Team3
 */
public interface IPathValidityChecker 
{
	/**
	 * This function is used to Check for overlap.
	 * @param first the first node
	 * @param second the second node
	 * @return true, if successful
	 */
	public boolean hasOverlap(Location first, Location second);
	
	/**
	 * This function is used to check if is in edge.
	 * @param width the width of grid
	 * @param height the height of grid 
	 * @param position the position of the node the we want to check
	 * @return true, if is in edge
	 */
	public boolean isOnEdge(int width, int height, Location position);
	
	/**
	 * This function is used for validation on design map
	 * @param matrix grid info
	 * @return true, if has start node
	 */
	public boolean hasStartPoint(MapContents[][] matrix);
	
	/**
	 * This function is used for validation on design map
	 * @param matrix grind info
	 * @return true, if has end
	 */
	public boolean hasEndPoint(MapContents[][] matrix);
	
	/**
	 * This function is used to check for size validation in grid design 
	 * @param height height of the grid
	 * @param width with of the grid
	 * @return true if correct
	 */
	public boolean isCorrectSize(int height, int width);
}