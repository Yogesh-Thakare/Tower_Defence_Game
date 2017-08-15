package td.game.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import td.game.model.Location;
import td.game.model.MapContents;
/**
 * This class is used for the path computations
 * @author Team3
 */
public class PathService 
{
	private static final Log4jLogger logger = new Log4jLogger();
	Map<Location, Integer> nodes;
	
	/**
	 * This method is used for the graph input
	 * @param matrix
	 * @return
	 */
	public List<String> graphInput(MapContents[][] matrix) 
	{
		try 
		{
			nodes = initialize(matrix);
			List<String> relations = new ArrayList<String>();

			Location p = null;
			for (int i = 0; i < matrix.length; i++) 
			{
				for (int j = 0; j < matrix[0].length; j++) 
				{
					if (isRight(matrix, i, j)) 
					{
						p = new Location(i, j);
						int node = nodes.get(p); // current cell node number

						p = new Location(i, j + 1);
						int rightNode = nodes.get(p); // right cell node number

						relations.add(node + " " + rightNode);
						; // should add to an array
					}
					
					if (isDown(matrix, i, j)) 
					{

						p = new Location(i, j);
						int node = nodes.get(p); // current cell node number

						p = new Location(i + 1, j);
						int bottomNode = nodes.get(p); // bottom cell node
						// number
						relations.add(node + " " + bottomNode);
					}
				}
			}
			return relations;
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}
	
	/**
	 * This method is used for the down direction in the map
	 * @param matrix grid
	 * @param i index of actual node
	 * @param j index of actual node
	 * @return true , if it has right connection
	 */
	public boolean isDown(MapContents[][] matrix, int i, int j) 
	{
		return (((matrix[i][j] == MapContents.PATH
				|| matrix[i][j] == MapContents.ENTRANCE || matrix[i][j] == MapContents.EXIT) && i + 1 < matrix.length) && ((matrix[i + 1][j] == MapContents.PATH
				|| matrix[i + 1][j] == MapContents.ENTRANCE || matrix[i + 1][j] == MapContents.EXIT) && i + 1 < matrix.length));
	}
	
	/**
	 * This method is used for the right direction in the map
	 * @param matrix matrix that is required for validating
	 * @param i  as int that shows the index of the node that we want to check
	 *            if there is any neighbor in right or not
	 * @param j as int that shows the index of the node that we want to check
	 *            if there is any neighbor in right or not
	 * @return boolean that shows there is a connected neighbor in right place
	 */
	public boolean isRight(MapContents[][] matrix, int i, int j) 
	{
		return (((matrix[i][j] == MapContents.PATH
				|| matrix[i][j] == MapContents.ENTRANCE || matrix[i][j] == MapContents.EXIT) && j + 1 < matrix[0].length) &&
				((matrix[i][j + 1] == MapContents.PATH
				|| matrix[i][j + 1] == MapContents.ENTRANCE || matrix[i][j + 1] == MapContents.EXIT) && j + 1 < matrix[0].length)
				);
	}
	
	/**
	 * This method is used to get the location
	 * @param grid
	 * @return Location[] path
	 */
	public Location[] pathFinder(MapContents[][] grid) 
	{
		Location[] path = null;
		try 
		{
			PathService p = new PathService();
			List<String> strs = p.graphInput(grid);
			Location s = findEnterance(grid);
			Location e = findExit(grid);
			int start = p.nodes.get(s);
			int end = p.nodes.get(e);
			path = PathCalculator.getPath(p.nodes, strs, start, end, grid.length * grid[0].length);
		} 
		catch (Exception e) 
		{
			//logger.writer(this.getClass().getName(), e);	
			System.out.println("Path service not yet initialised");
		}
		return path; 
	}
	
	/**
	 * This method can find the entrance point
	 * @param map grid info
	 * @return Position of the entrance from map
	 */
	public Location findEnterance(MapContents[][] map)
	{
		try 
		{
			for (int i = 0; i < map.length; i++) 
			{
				for (int j = 0; j < map[0].length; j++) 
				{
					if(map[i][j] == MapContents.ENTRANCE)
						return new Location(i, j);
				}
			}
			
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
			System.out.println("map not loaded");
		}
		return null;
	}
	
	/**
	 * This method is used for initialization
	 * @param matrix
	 * @return
	 */
	public Map<Location, Integer> initialize(MapContents[][] matrix) 
	{
		Map<Location, Integer> map = new HashMap<Location, Integer>();
		int key = 0;

		for (int i = 0; i < matrix.length; i++) 
		{
			for (int j = 0; j < matrix[0].length; j++) 
			{
				Location p = new Location(i, j);
				map.put(p, key++);
			}
		}
		return map;
	}
	
	/**
	 * This method can find the exit point
	 * @param map grid info
	 * @return Position of the entrance from exit 
	 */
	public Location findExit(MapContents[][] map)
	{
		try 
		{
			for (int i = 0; i < map.length; i++) 
			{
				for (int j = 0; j < map[0].length; j++) 
				{
					if(map[i][j] == MapContents.EXIT)
						return new Location(i, j);
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
			e.printStackTrace();
		}
		return null;
	}
}
