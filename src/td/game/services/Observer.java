package td.game.services;

import td.game.model.Critter;
import td.game.model.Location;

/**
 * The Interface Observer used for implementation of observer design pattern.
 * @author Team3
 */
public interface Observer 
{
	/**
	 *This method is used for wave update implementation
	 * @param position Position that represent the position of the wave's head
	 */
	public void waveUpdate(Location position);
	
	/**
	 * This method is used for alien update implementation
	 * @param position position of alien
	 * @param critter critter that move in the path
	 * @param shootingStrategy define the shooting strategy to kill the critters.
	 */
	public void alienUpdate(Location position, Critter critter,String shootingStrategy);
}
