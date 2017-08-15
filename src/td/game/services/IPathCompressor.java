package td.game.services;

import td.game.model.Location;

/**
 * The Interface IWeightedUnionPathCompression
 * @author Team3
 */
public interface IPathCompressor 
{
	/**
	 * The constructor used to initialize.
	 * @param n value
	 * @param m value
	 */
	public void initialize(int n, int m);
	
	/**
	 * This method is used to check if path is Connected.
	 * @param p location
	 * @param q location
	 * @return true, if successful
	 */
	public boolean isConnected(Location p, Location q);
	
	/**
	 * The Root method.
	 * @param pKey the key
	 * @return int value
	 */
	public int root(int pKey);
	
	/**
	 * The Union method.
	 * @param p the p
	 * @param q the q
	 */
	public void union(Location p, Location q);
}