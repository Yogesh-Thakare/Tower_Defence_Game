package td.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import td.game.constants.TowerCharactersticsConstants;
import td.game.services.Log4jLogger;
/**
 * This class is used to implement decorator pattern for Towers
 * @author Team3
 */
@SuppressWarnings("serial")
public class FireRange extends TowerFeatureDecorator 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private Tower tower;
	
	/**
	 * This method is used to get the tower
	 * @return
	 */
	public Tower getTower() 
	{
		return tower;
	}
	
	/**
	 * This method is used to get the tower information as a list
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
	 * This method is used to get tower and create a critter map positions
	 * @param tower tower that we want to decorate
	 */
	public FireRange(Tower tower) 
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
		return this.tower.getDescription() + ",FireRange";
	}
	
	/**
	 * This method is used to show the appearance of the tower
	 * @return Color color of the button that is representative of a tower
	 */
	@Override
	public String displayTowerImage() 
	{
		return this.tower.displayTowerImage();
	}
	
	/**
	 *This method is used to get the tower cost
	 */
	@Override
	public long towerCost() 
	{
		return TowerCharactersticsConstants.FIRE_RANGE + this.tower.towerCost();
	}
}
