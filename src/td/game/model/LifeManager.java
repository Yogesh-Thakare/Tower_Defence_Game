package td.game.model;

import java.io.Serializable;
import td.game.constants.GameConstants;

/**
 * This class is used to maintain the life details of the player
 * @author Team3
 */
@SuppressWarnings("serial")
public class LifeManager implements Serializable 
{
	private static LifeManager instance = null;
	public static int playerLife;
	private static int idManager;
	
	/**
	 * Private constructor to prevent more than one instance
	 */
	private LifeManager() 
	{
	}
	
	/**
	 * This method is used to get the instance of LifeManager, If exists
	 * @return
	 */
	public static LifeManager getInstance() 
	{
		if(instance == null) 
		{
			instance = new LifeManager();
			playerLife = GameConstants.DEFAULT_LIFE;
			idManager = 0;
		}
		return instance;
	}
	
	/**
	 * This method is used reset the player life
	 */
	public void resetCurrentLife() 
	{
		LifeManager.playerLife = 0;
	}
	
	/**
	 * This method is used to get the manager id
	 * @return
	 */
	public int getIdManager() 
	{
		return idManager;
	}
	
	/**
	 * This method is used to set the manager id value
	 * @param idManager
	 */
	@SuppressWarnings("static-access")
	public void setIdManager(int idManager) 
	{
		this.idManager = idManager;
	}
	
	/**
	 * @return
	 */
	@Override
	public boolean equals(Object arg0) 
	{
		return super.equals(arg0);
	}
	
	/**
	 * This method is used to fetch the players life
	 * @return
	 */
	public int getLife() 
	{
		return playerLife;
	}
	
	/**
	 * This method is used to set the players life
	 * @param life
	 */
	public  void setLife(int life) 
	{
		LifeManager.playerLife = life;
	}
	
	/**
	 * This method is used to add the players life
	 * @param life
	 */
	public void addLife(long life) 
	{
		LifeManager.playerLife += life;
	}
}