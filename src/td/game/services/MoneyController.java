package td.game.services;

import td.game.constants.GameConstants;

/**
 * This class is used for implementing singleton pattern for managing players' moneys balance
 * @author Team3
 */
public class MoneyController implements java.io.Serializable 
{ 
	private static final long serialVersionUID = -54897009666121452L;
	private static MoneyController instance = null;
	public static long balance;
	public static long currentBalance;
		
    /**
     * Private constructor to prevent multiple instances
     */
    private MoneyController() 
    {
	}
    
	/**
	 * This method is used to make a instance from this class if there is not any object of this class
	 * @return instance instance
	 */
	public static MoneyController getInstance() 
	{
		if(instance == null) 
		{
			instance = new MoneyController();
			balance = GameConstants.DEFAULT_BALANCE;
			currentBalance = 0;
		}
		return instance;
	}
	
	/**
	 * This method is used to set the balance
	 * @param value
	 */
	public void setBalanceMoney(long value) 
	{
		balance = value;
	}
	
	/**
	 * This method is used to get the total money that player can spend during game
	 * @return long
	 */	
	public long getBalanceMoney() 
	{
		return balance;
	}
	
	/**
	 * This method is used to get the current amount of moneys that player spent
	 * @return long
	 */
	public long getCurrentBalanceMoney() 
	{
		return currentBalance;
	}
	
	/**
	 * This method is used to add the extra money that player can add to the their amount of money
	 * @param balance money added to user's balance
	 */
	public void addBalanceMoney(long balance) 
	{
		MoneyController.balance += balance;
	}
	
	/**
	 * This method is used to make the total amount that have been used by player to zero
	 */
	public void resetCurrentBalanceMoney() 
	{
		MoneyController.currentBalance = 0;	
	}
	
	/**
	 * This method is used to get the new tower cost or other cost and added to the amount of
	 *  money that were spent
	 * @param currentBalance
	 */
	public void setCurrentBalanceMoney(long currentBalance) 
	{
		MoneyController.currentBalance += currentBalance;
	}
}