package td.game.services;

import td.game.model.Location;
import td.game.model.MapContents;

/**
 * The interface IPathEndPointsCheckerService
 * @author Team3
 */
public interface IPathEndPointsCheckerService 
{
	/**
     *This method is used to validate if there is any path in our grid or not
	 * @param start start position
	 * @param end end position
	 * @param matrix grid content
	 * @return true, if there is a valid path.
	 */
	public boolean isTherePath(Location start, Location end, MapContents[][] matrix);
}