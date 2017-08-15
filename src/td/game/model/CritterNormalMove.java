package td.game.model;

import td.game.constants.MapConstants;

/**
 * <b>this is used for strategy design pattern this class is defined to
 * implement the one of the moving behavior of a alien that is a kind of regular
 * move </b> 
 * @author Team3
 */
public class CritterNormalMove implements CritterMovingBehaviour, java.io.Serializable 
{
	private static final long serialVersionUID = -7111897498315966069L;

	private Location[] path;
	private int xUnit;
	private int yUnit;
	private Location PixelPosition;
	private int currentCritterPosition;
	private int freezeTime;
	
	/**
	 * This method is used to return pixel position
	 * @return PixelPosition location
	 */
	public Location getPixelPosition() 
	{
		return PixelPosition;
	}
	
	/**
	 * This method is used to return current position in map
	 * @return currentPosition
	 */
	public int getCurrentPosition() 
	{
		return currentCritterPosition;
	}
	
	/**
	 * This method is used to return path in array of positions 
	 * @return path
	 */
	public Location[] getPath() 
	{
		return path;
	}
	
	/**
	 * This method is used to set freeze time for tower
	 * @param freezeTime time for freezing a critter
	 */
	public void setFreezeTime(int freezeTime) 
	{
		this.freezeTime = freezeTime;
	}
	
	/**
	 * This method is used to set the path value
	 * @param path
	 */
	private void setPath(Location[] path) 
	{
		this.path = path;
	}
	
	/**
	 * This method is used to set the pixel position
	 * @param path
	 */
	private void setPixelPosition(Location pixelPosition) 
	{
		PixelPosition = pixelPosition;
	}
	
	/**
	 * This method is used to fetch the freeze time
	 * @param path
	 */
	public int getFreezeTime() 
	{
		return freezeTime;
	}
	
	/**
	 * This method is used to set PixelPosition and path for regular move
	 * @param path valid path
	 * @param initialPixel initial path pixel
	 */
	public CritterNormalMove(Location[] path, Location initialPixel) 
	{
		this.setPixelPosition(initialPixel);
		this.setPath(path);
	}
	
	/**
	 *This method is used to move the critter
	 */
	@Override
	public void move() 
	{
		if (freezeTime <= 0) 
		{
			int xPixel = PixelPosition.getX();
			int yPixel = PixelPosition.getY();
			int currPathCell = currentCritterPosition;
			if (currPathCell != path.length - 1) 
			{
				Location currCell = path[currPathCell];
				Location nextCell = path[currPathCell + 1];

				if (currCell.getY() == nextCell.getY()) 
				{
					if (currCell.getX() + 1 == nextCell.getX()) 
					{
						xPixel += 1;
						xUnit += 1;
						if (xUnit >= MapConstants.UNIT_SIZE - 1) 
						{
							xUnit = 0;
							currPathCell++;
						}

					} 
					else if (currCell.getX() - 1 == nextCell.getX()) 
					{
						xPixel -= 1;
						xUnit += 1;
						if (xUnit >= MapConstants.UNIT_SIZE - 1) 
						{
							xUnit = 0;
							currPathCell++;
						}
					}

				}
				if (currCell.getX() == nextCell.getX()) 
				{
					if (currCell.getY() + 1 == nextCell.getY()) 
					{
						yPixel += 1;
						yUnit += 1;

					} 
					else if (currCell.getY() - 1 == nextCell.getY()) 
					{
						yPixel -= 1;
						yUnit += 1;
					}
					if (yUnit >= MapConstants.UNIT_SIZE - 1) 
					{
						yUnit = 0;
						currPathCell++;
					}
				}
			}
			this.PixelPosition = new Location(xPixel, yPixel);
			currentCritterPosition = currPathCell;
		} 
		else 
		{
			if (freezeTime > 0) 
			{
				freezeTime--;
			}
		}
	}
}
