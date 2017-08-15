package td.game.services;

import td.game.model.Location;
import td.game.model.MapContents;
/**
 * This class is used as a service to check some validation in map based on the position of start and end 
 * @author Team3
 */
public class PathValidityChecker implements IPathValidityChecker 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * This method is used to check if the node that was selected is located on the age of grid or not
	 * @param width place of the edge
	 * @param height place of the edge
	 * @param position position that we need for validation 
	 * @return true, if node is on edge
	 */
	@Override
	public boolean isOnEdge(int width, int height, Location position)
	{
			if(condition(width, height, position))
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	
	/**
	 * This method is used to check any two positions that were selected have overlap with others our not
	 * @param first first node that was chosen
	 * @param second second node that was chosen
	 * @return boolean
	 */
	@Override
	public boolean hasOverlap(Location first, Location second)
	{
		return first.equals(second);
	}
	
	/**
	 * This method is used to check if there is any start point on grid or not
	 * @param matrix grid info
	 * @return true, if it has a start point
	 */
	@Override
	public boolean hasStartPoint(MapContents[][] matrix)
	{
		return nodeChecker(matrix, MapContents.ENTRANCE);
	}
	
	/**
	 * This method is used to check if there is any start point on grid or not
	 * </b>
	 * @param matrix grid info
	 * @return true, if it has an end point
	 */
	@Override
	public boolean hasEndPoint(MapContents[][] matrix) 
	{
		return nodeChecker(matrix, MapContents.EXIT);
	}
	
	/**
	 * This method is used to find any neighboring point if any
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean isNeighbor(Location start, Location end) 
	{
		if (start.getX() + 1 == end.getX()) 
		{
			return true;
		}
		if (start.getX() - 1 == end.getX()) 
		{
			return true;
		}
		if (start.getY() + 1 == end.getY()) 
		{
			return true;
		}
		if (start.getY() + 1 == end.getY()) 
		{
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used to extract re factoring for condition
	 * @param width
	 * @param height
	 * @param position
	 * @return true , if the condition is correct
	 */
	private boolean condition(int width, int height, Location position)
	{
		return ((position.getX() == 0) || (position.getX() ==width))
				|| ((position.getY() == 0) || (position.getY() == height));
	}
	
	/**
	 * This method is used for grid size validation
	 * @param height height of the grid
	 * @param width width of grid
	 * @return true , if the correct size was chosen
	 */
	@Override
	public boolean isCorrectSize(int height, int width) 
	{
		return ((height >= 5 && height <= 15) || (width >= 5 && width <= 29));
	}
	
	/**
	 * This method is used to extract re factoring
	 * @param matrix grid info
	 * @param expected expected matrix
	 * @return true , if both of them are the same
	 */
	public boolean nodeChecker(MapContents[][] matrix, MapContents expected)
	{
		boolean flag = false;
		try 
		{
			for (MapContents[] rows : matrix) 
			{
				for (MapContents type : rows) 
				{
					if(type == expected)
						flag = true;
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return flag;
	}
	
	/**
	 *This method is used for validation for grid size, maximum 12*20
	 * @param height height of the grid
	 * @param width width of grid
	 * @return true , if the correct size was chosen
	 */
	public boolean isCorrectHeight(int height) 
	{
		return ((height >= 5 && height <= 15));
	}
	
	/**
	 * This method is used for validation for grid size, maximum 12*20
	 * @param width
	 * @return
	 */
	public boolean isCorrectWidth(int width) 
	{
		return (width >= 5 && width <= 29);
	}
}
