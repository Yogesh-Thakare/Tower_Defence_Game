package td.game.model;

/**
 * This class is used for class content
 * @author Team3
 */
public enum MapContents implements java.io.Serializable  
{
	SCENERY(0), PATH(1), ENTRANCE(2), EXIT(3), TOWER(11);
	private int value;
	
	/**
	 * This method is used to get the value
	 * @return int value
	 */
	public int getValue() 
	{
		return this.value;
	}	
	
	/**
	 * This method is used to set the value
	 * @param value
	 */
	public void setValue(int value) 
	{
		this.value = value;
	}
	
	/**
	 * This method is used to set the map content value
	 * @param value int value
	 */
	private MapContents(int value)
	{
		this.value = value;
	}
}
