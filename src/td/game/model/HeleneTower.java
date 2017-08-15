package td.game.model;

import td.game.constants.TowerCharactersticsConstants;
import td.game.constants.MapConstants;

/**
 * This class is used for ChambyTower characteristics
 * @author Team3
 */
@SuppressWarnings("serial")
public class HeleneTower extends Tower 
{
	/**
	 * This constructor is used to set the tower behavior
	 */
	public HeleneTower() 
	{
		setLineShootingBehaviour(new TowerLineShooting());
		this.description = "HeleneTower";
	}
	
	/**
	 * This method is called to display the tower image
	 * @return string image name
	 */
	@Override
	public String displayTowerImage() 
	{
		return MapConstants.HELENE_TOWER_IMG;
	}
	
	/** 
	 *This method is used to get the base cost of the tower
	 */
	@Override
	public long towerCost() 
	{
		return TowerCharactersticsConstants.HELENE_TOWER;
	}
}
