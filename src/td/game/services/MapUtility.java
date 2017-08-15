package td.game.services;

import td.game.model.Location;
import td.game.model.MapContents;

/**
 * This class is used for working with a map
 * @author Team3
 */
public class MapUtility 
{	
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * This method is used to find the exit point
	 * @param map grid information
	 * @return Position of the entrance from exit 
	 */
	public Location getMapExit(MapContents[][] map)
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
		}
		return null;
	}
	
	/**
	 * This method is used to find the entrance point
	 * @param map grid info
	 * @return Position of the entrance from map
	 */
	public Location getMapEnterance(MapContents[][] map)
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
		}
		return null;
	}
}
