package td.game.services;

import java.io.BufferedReader;
import td.game.model.Location;
import td.game.model.MapContents;
/**
 * This class acts as the connectivity service to check if there is any path between start and end 
 * @author Team3
 */
public class PathEndPointsCheckerService implements IPathEndPointsCheckerService 
{
	private static final Log4jLogger logger = new Log4jLogger();
	@SuppressWarnings("unused")
	private static BufferedReader br;
	
	/**
	 * This method is used to Check for validating if there is any path between start and end 
	 * and transform the grid that was built as GridCellType
	 * into an graph and after that can make a union between each nodes of graph that has a relation to their neighbors.
	 * @param start as Position that represent the entrance point
	 * @param end as Position that represent the exit point of aliens
	 * @param matrix as GridCellContentType
	 * @return boolean it is true if there is a path
	 */
	public boolean isTherePath(Location start, Location end, MapContents[][] matrix) 
	{
		try 
		{
			PathCompressor compression = new PathCompressor();
			compression.initialize(matrix.length, matrix[0].length);
			for (int i = 0; i < matrix.length; i++) 
			{
				for (int j = 0; j < matrix[0].length; j++) 
				{
					if (isRight(matrix, i, j)) 
					{
						if (isPath(matrix, i, j + 1))
						compression.union(new Location(i, j), new Location(i, j + 1));
					}
					if (isDown(matrix, i, j)) 
					{
						if (isPath(matrix, i + 1, j))
						compression.union(new Location(i, j), new Location(i + 1, j));
					}
				}
			}
			return (compression.isConnected(start, end));

		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
			return false;
		}
	}
	
	/**
	 * This method is used to check the node in right direction
	 * @param matrix the matrix that we want for validating
	 * @param i as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @param j as int that shows the index of the node that we want to check if there is any neighbor in right or not
	 * @return boolean that shows there is a connected neighbor in right place
	 */
	public boolean isRight(MapContents[][] matrix, int i, int j) 
	{
		return ((matrix[i][j] == MapContents.PATH
				|| matrix[i][j] == MapContents.ENTRANCE 
				|| matrix[i][j] == MapContents.EXIT)
				&& j + 1 < matrix[0].length);
	}
	
	/**
	 * This method is used to check the node in down direction
	 * @param matrix that is the grid 
	 * @param i index of actual node
	 * @param j index of actual node
	 * @return true , if it has right connection
	 */
	public boolean isDown(MapContents[][] matrix, int i, int j) 
	{
		return ((matrix[i][j] == MapContents.PATH
				|| matrix[i][j] == MapContents.ENTRANCE 
				|| matrix[i][j] == MapContents.EXIT)
				&& i + 1 < matrix.length);
	}
	
	/**
	 * This method is used to check this node is part of path or not
	 * @param matrix grid info
	 * @param i actual node index
	 * @param j actual node index
	 * @return true, if it has Down connection  
	 */
	public boolean isPath(MapContents[][] matrix, int i, int j) 
	{
		return (matrix[i][j] == MapContents.PATH
				|| matrix[i][j] == MapContents.ENTRANCE
				|| matrix[i][j] == MapContents.EXIT);
	}
}
