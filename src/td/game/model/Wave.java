package td.game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>This class was implemented to keep all information about wave of
 *  critters, so we decided to keep only list of critters that are in 
 *  the wave and the position of the wave's head to inform all tower 
 *  the position of the wave by observer design pattern</p> 
 *  <b>this part it will be implemented for the build 2</b> 
 *  @author Team3
 */
public class Wave implements java.io.Serializable 
{
	private static final long serialVersionUID = -1889317271014210764L;
	public List<Critter> critters;
	public Location headPosition;
	
	/**
	 * This method is used to set aliens
	 * @param value
	 */
	public void setAliens(List<Critter> value)
	{
		critters = value;
	}
	
	/**
	 * This method is used to return first critter position in a wave
	 * 
	 * @return headPosition
	 */
	public Location getHeadPosition() 
	{
		return headPosition;
	}

	/**
	 * This method is used to set first critter position in a wave
	 * 
	 * @param headPosition set position of the wave. 
	 */
	public void setHeadPosition(Location headPosition) 
	{
		this.headPosition = headPosition;
	}
	
	/**
	 * This method is used to create alien as an list of critters
	 */
	public Wave() 
	{
		critters = new ArrayList<Critter>();
	}
	
	/**
	 * This method is used to get aliens
	 * @return critter list
	 */
	public List<Critter> getAliens()
	{
		return critters;
	}
}
