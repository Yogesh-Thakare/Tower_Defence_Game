package td.game.model;

import td.game.services.Observer;

/**
 * This class is for Subject class for observer design pattern
 * @author Team3
 */
public interface Subject 
{
	/**
	 * This method is used to register observer
	 * @param o
	 */
	public void registerObserver(Observer o);
	
	/**
	 * This method is used to remove observer
	 * @param o
	 */
	public void removeObserver(Observer o);
	
	/**
	 * This method is used to notify observer
	 */
	public void notifyObservers();
	
	/**
	 * This method is used to notify defenders
	 */
	public void notifyDefenders();
}
