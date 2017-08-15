package td.game.services;

import td.game.constants.TowerCharactersticsConstants;
import td.game.model.Tower;

/**
 * This class is used for Tower sell functionality
 * @author Team3 
 */
public class TowerSellPriceCalculator 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 *This method is used to calculate the sell value base on the decorated tower
	 * @param tower the tower that player wants to sell
	 * @param <T> is type of tower
	 * @return sale value of a tower
	 */
	public <T extends Tower> long getSellTowerAmount(T tower)
	{
		long amount = 0;
		try 
		{
			amount = (tower.towerCost() * TowerCharactersticsConstants.SELL_PERCENTAGE)/100;
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return amount;
	}
}
