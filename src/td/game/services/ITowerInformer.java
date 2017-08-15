package td.game.services;

import td.game.model.Critter;
import td.game.model.Location;

/**
 * The interfaces ITowerInformer that DefenderInformer has to implement
 * @author Team3
 */
public interface ITowerInformer 
{
	
	/**
	 * This method is used to register one of tower or defender from observer's list of defenders
	 * @param o
	 */
	public void registerObserver(Observer o);
	
	/**
	 * 
	 *This method is used for removing one of tower or defender from observer's list of defenders
	 * @param o the object the you want to remove from the observer's list
	 */
	public void removeObserver(Observer o);
	
	/**
	 * This method is used to notify defenders about the latest position of wave 
	 */
	public void notifyObservers();
	
	/**
	 * This method is used to set the wave's head position
	 * @param x as integer 
	 * @param y as integer
	 */
	public void setWavePosition(int x, int y);
	
	/**
	 * This method is used to set the alien's position
	 * @param x as integer 
	 * @param y as integer
	 * @param critter critter object reference
	 * @param strategy strategy for shooting
	 */
	public void setAlienPosition(int x, int y, Critter critter, String strategy);
	
	/**
	 * This method is used for the position changes
	 */
	public void wavePositionChange();
	
	/**
	 * This method is used for the position changes
	 */
	public void alienPositionChange();
	
	/**
	 * This method is used for getting position of the wave's head
	 * @return Position 
	 */
	public Location getWavePosition();
	
	/**
	 * This method is used for getting position of the wave's head
	 * @return Position 
	 */
	public Location getAlienPosition();
}