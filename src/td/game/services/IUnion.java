package td.game.services;

import td.game.model.Location;

/**
 * The Interface IUnion.
 * @author Team3
 */
public interface IUnion 
{
	
	/**
	 * The method Union.
	 * @param p value
	 * @param q value
	 */
	public void union(Location p, Location q);
	
	/**
	 * The method Initialize a matrix that can transform the grid.
	 * @param n value
	 * @param m value
	 */
	public void initialize(int n, int m);
	
	/**
	 * The method is used for checking validation part in design map
	 *
	 * @param p value
	 * @param q value
	 * @return true, if successful
	 */
	public boolean isConnected(Location p, Location q);
}
