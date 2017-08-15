package td.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import td.game.constants.TowerCharactersticsConstants;
import td.game.services.Log4jLogger;
/**
 *  This class is used to implement decorator pattern for Towers
 * @author Team3
 */
@SuppressWarnings("serial")
public class FireSpeed extends TowerFeatureDecorator 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private Tower tower;
	
	/**
	 * This method is used get the tower
	 * @return tower
	 */
	public Tower getTower() 
	{
		return tower;
	}
	
	/**
	 * This method is used to get the tower information
	 */
	@Override
	public List<Tower> towerInformation() 
	{
		List<Tower> details = new ArrayList<Tower>();
		try 
		{
			details.addAll(tower.towerInformation());
			details.add(this);
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return details;
	}
	
	/**
	 * This method is used get tower and create a critter map positions
	 * @param tower tower that wanted to decorate
	 */
	public FireSpeed(Tower tower) 
	{
		this.tower = tower;
		crittersLocation = new HashMap<Critter, Location>();
	}
	
	/**
	 * This method is used to get decorated object description
	 * @return description contains all features and the tower type
	 */
	@Override
	public String getDescription() 
	{
		return this.tower.getDescription()+",fireSpeed";
	}
	
	/**
	 * This method is used to calculate the cost of decorated tower
	 */
	@Override
	public long towerCost() 
	{
		return TowerCharactersticsConstants.FIRE_SPEED + this.tower.towerCost();
	}
	
	/**
	 * 
	 * This method is used for the appearance of the tower
	 * @return Color button that is representative of a tower
	 */
	@Override
	public String displayTowerImage() 
	{
		return this.tower.displayTowerImage();
	}
}
