package td.game.model;

import java.util.ArrayList;
import java.util.List;

import td.game.services.Log4jLogger;
/**
 * This class is for tower properties
 * @author Team3
 */
public abstract class Tower extends Defender  
{
	private static final Log4jLogger logger = new Log4jLogger();
	private static final long serialVersionUID = -1260736688387671401L;	
	public String Id;
	private transient TowerShootingBehaviour shootingBehaviour;
	private transient TowerLineShootingBehaviour lineShootingBehaviour;
	private TowerLevelEnum levelEnum;
	private Location towerLocation;
	private String shootingStrategy;
	private TowerTypeEnum towerType;
	protected String description;
	protected List<Tower> towers;
	public abstract String displayTowerImage();
	public abstract long towerCost();
		
	/**
	 * This method is used to create a list of towers in our map
	 * @return towers
	 */
	public List<Tower> towerInformation()
	{
		try 
		{
			if(towers != null)
				towers.add(this);
			else
			{
				towers = new ArrayList<Tower>();
				towers.add(this);
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return towers;
	}
	
	/**
	 * This method is used to return tower type
	 * @return towerType
	 */
	public TowerTypeEnum getTowerType() 
	{
		return towerType;
	}
	
	/**
	 * This method is used to set tower type
	 * @param towerType tower type is a enum that can describe the decorated object for a tower
	 */
	public void setTowerType(TowerTypeEnum towerType) 
	{
		this.towerType = towerType;
	}
	
	/**
	 * Return shooting strategy
	 * @return getShootingStrategy
	 */
	public String getShootingStrategy() 
	{
		return shootingStrategy;
	}
	
	/**
	 * This method is used to set shooting strategy
	 * @param shootingStrategy shooting strategy for killing the critters
	 */
	public void setShootingStrategy(String shootingStrategy) 
	{
		this.shootingStrategy = shootingStrategy;
	}
	
	/**
	 * This method is used to return tower position
	 * @return towerPosition
	 */
	public Location getTowerPosition() 
	{
		return towerLocation;
	}
	
	/**
	 * This method is used to set tower position
	 * @param towerPosition tower position on the map
	 */
	public void setTowerPosition(Location towerPosition) 
	{
		this.towerLocation = towerPosition;
	}
	
	/**
	 * This method is used to return tower level
	 * @return level
	 */
	public TowerLevelEnum getLevel() 
	{
		return levelEnum;
	}
	
	/**
	 * This method is used to set tower level
	 * @param level tower level that is a between one  to three
	 */
	public void setLevel(TowerLevelEnum level) 
	{
		this.levelEnum = level;
	}
	
	/**
	 * This method is used to get tower list
	 * @return towers
	 */
	public List<Tower> getTowers() 
	{
		return towers;
	}
	
	/**
	 * This method is used for tower description
	 * @param towers tower's details that contains the decorator object and decorated object
	 */
	public void setTowers(List<Tower> towers) 
	{
		this.towers = towers;
	}
	
	/**
	 * This method is used to return tower description
	 * @return description
	 */
	public String getDescription() 
	{
		return description;
	}
	
	/**
	 * This method is used to set shooting behavior
	 * @param shootingBehaviour shooting behaviour
	 */
	public void setShootingBehaviour(TowerShootingBehaviour shootingBehaviour) 
	{
		this.shootingBehaviour = shootingBehaviour;
	}

	/**
	 * This method is used to return line shooting behavior
	 * @return lineShootingBehaviour
	 */
	public TowerLineShootingBehaviour getLineShootingBehaviour() 
	{
		return lineShootingBehaviour;
	}
	
	/**
	 * This method is used to set line shooting behavior
	 * @param lineShootingBehaviour shooting shape will be a line
	 */
	public void setLineShootingBehaviour(TowerLineShootingBehaviour lineShootingBehaviour) 
	{
		this.lineShootingBehaviour = lineShootingBehaviour;
	}

	/**
	 * This method is used to perform shooting behavior
	 */
	public void performShootingBehaviour()
	{
		shootingBehaviour.shoot();
	}
}