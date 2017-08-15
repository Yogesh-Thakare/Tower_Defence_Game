package td.game.model;

import java.util.UUID;

/**
 *This abstract class is used for critters
 * @author Team3
 */
public abstract class Critter implements java.io.Serializable 
{

	private static final long serialVersionUID = -7151914372955282259L;
	public String Id= UUID.randomUUID().toString();
	private int life;
	private int currentCritterPosition;
	private Location[] critterPath;
	private Location initialPixel;
	transient CritterMovingBehaviour movingBehaviour;
	private int resistance;
	private int waveNumber;
	
	/**
	 * This abstract method is used for display
	 * @return string value
	 */
	public abstract String display();
	
	/**
	 * This abstract method is used to get tower health
	 * @return double value
	 */
	public abstract double returnTowerHealth();
	
	/**
	 * This method is used to return critters life
	 * @return life
	 */
	public int getLife() 
	{
		return life;
	}
	
	/**
	 * This method is used to set critters life 
	 * @param life life of player
	 */
	public void setLife(int life) 
	{
		this.life = life;
	}
	
	/**
	 * This method is used return critter current position
	 * @return currentPosition
	 */
	public int getCurrentPosition() 
	{
		return currentCritterPosition;
	}
	
	/**
	 * This method is used to set critter current position
	 * @param currentPosition current position 
	 */
	public void setCurrentPosition(int currentPosition) 
	{
		this.currentCritterPosition = currentPosition;
	}
	
	/**
	 * This method is used to set the moving behavior.
	 * @param movingBehaviour moving behavior
	 */
	public void setMovingBehaviour(CritterMovingBehaviour movingBehaviour) 
	{
		this.movingBehaviour = movingBehaviour;
	}
	
	/**
	 * This method is used to return movingBehaviour of a critters
	 * @return movingBehaviour
	 */
	public CritterMovingBehaviour getMovingBehaviour() 
	{
		return this.movingBehaviour;
	}
	
	/**
	 * This method is used to get initial Pixel of a critter
	 * @return position 
	 */
	public Location getInitialPixel() 
	{
		return initialPixel;
	}
	
	/**
	 * This method is used to set initial Pixel for a critter
	 * @param initialPixel location 
	 */
	public void setInitialPixel(Location initialPixel) 
	{
		this.initialPixel = initialPixel;
	}
	
	/**
	 * This method is used to perform moving behavior.
	 */
	public void performMovingBehaviour()
	{
		movingBehaviour.move();
	}
	
	protected String description;
	/**
	 * This method is used to get the description value.
	 * @return the description
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * This method is used to assign the value of description
	 * @param value
	 */
	public void setDescription(String value) 
	{
		description = value;
	}
	
	/**
	 * This method is used to return path as a position object contain x and y as actual position on the grid
	 * @return path
	 */
	public Location[] getPath() 
	{
		return critterPath;
	}
	
	/**
	 * This method is used to set a valid path for critters
	 * @param path correct path
	 */
	public void setPath(Location[] path) 
	{
		if(path != null)
			this.critterPath = path;
	}
	/**
	 * This method is used to fetch the resistance value
	 * @return resistance at each level
	 */
	public int getResistance() 
	{
		return resistance;
	}

	/**
	 * This method is used to set the resistance value
	 * @param resistance
	 */
	public void setResistance(int resistance) 
	{
		this.resistance = resistance;
	}
	
	/**
	 * This method is used to fetch the wave number
	 * @return wave to which critter belongs
	 */
	public int getWaveNumber() 
	{
		return waveNumber;
	}

	/**
	 * This method is used to set the wave number
	 * @param waveNumber
	 */
	public void setWaveNumber(int waveNumber) 
	{
		this.waveNumber = waveNumber;
	}
}
