package td.game.services;

import td.game.model.LifeManager;

/**
 * This class maintains player's life
 * @author Team3
 *
 */
public class PlayerLifeService 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * This method is used to manage player life and add life to player 
	 * @param life that will be set for player
	 */
	public void playerLifeManager(long life)
	{
		try 
		{
			LifeManager manager = LifeManager.getInstance();
			manager.addLife(life);
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to reset player life
	 */
	public void resetPlayerLife()
	{
		try 
		{
			LifeManager manager = LifeManager.getInstance();
			manager.resetCurrentLife();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
}
