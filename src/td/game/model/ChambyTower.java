package td.game.model;

import td.game.constants.TowerCharactersticsConstants;
import td.game.constants.MapConstants;

/**
 * This class is used for ChambyTower characteristics
 * @author Team3
 */
@SuppressWarnings("serial")
public class ChambyTower extends Tower 
{
	/**
	 * This constructor is used to set the tower behavior
	 */
	public ChambyTower() 
	{
		setLineShootingBehaviour(new TowerLineShooting());
		this.description = "ChambyTower";
	}
	
	/**
	 * This method is called to display the tower image
	 * @return string image name
	 */
	@Override
	public String displayTowerImage() 
	{
		return MapConstants.CHAMBY_TOWER_IMG;
	}
	
	/** 
	 *This method is used to get the base cost of the tower
	 */
	@Override
	public long towerCost() 
	{
		return TowerCharactersticsConstants.CHAMBY_TOWER;
	}
}
