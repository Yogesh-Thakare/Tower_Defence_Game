package td.game.model;

/**
 *This class is used to define the location
 * @author Team3
 */
public class Location implements java.io.Serializable 
{
	private static final long serialVersionUID = -1521288002292900413L;
	private int x;
	private int y;
	
	/**
	 * This Constructor is used for initiating object of x , y
	 * @param x width of the actual position on the grid
	 * @param y height of the actual position on the grid
	 */
	public Location(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This method is used to return the x position of an element in the map
	 * @return x 
	 */
	public int getX() 
	{
		return x;
	}
	
	/**
	 * This method is used as setter for x value of an element in the map
	 * @param x width of the actual position on the grid
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	
	/**
	 * This method is used get y 
	 * @return y int value
	 */
	public int getY() 
	{
		return y;
	}
	
	/**
	 * This method is used o set y 
	 * @param y int value
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	
	/**
	 * This method is used get hash map
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	/**
	 * This method is used to compare the positions required to override the equals method
	 * @return true, if it is done successfully
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/**
	 * @return String 
	 */
	@Override
	public String toString() 
	{
		return "["+ this.x +","+this.y+"]"; 
	}
}
