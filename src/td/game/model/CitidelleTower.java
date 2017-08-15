package td.game.model;

import td.game.constants.TowerCharactersticsConstants;
import td.game.constants.MapConstants;

/**
 * This class is used for CitidelleTower characteristics
 * @author Team3
 * 
 */
@SuppressWarnings("serial")
public class CitidelleTower extends Tower 
{
	/**
	 *This constructor is used to set the tower behavior
	 */
	public CitidelleTower() 
	{
		setLineShootingBehaviour(new TowerLineShooting());
		this.description = "CitidelleTower";
	}
	
	/**
	 * 
	 This method is called to display the tower image
	 * @return string image name
	 */
	@Override
	public String displayTowerImage() 
	{
		return MapConstants.CITIDELLE_TOWER_IMG;
	}
	
	/**
	 * 
	 *This method is used to get the base cost of the tower
	 */
	@Override
	public long towerCost() 
	{
		return TowerCharactersticsConstants.CITIDELLE_TOWER;
	}
}


