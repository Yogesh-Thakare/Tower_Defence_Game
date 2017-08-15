package td.game.model;

import java.util.Observable;

/**
 * This class is used for observer design patter for informing all towers about the critters'position
 * @author Team3
 */
public abstract class Defender extends Observable implements java.io.Serializable 
{
	private static final long serialVersionUID = -4980198702785542216L;
	protected Subject subject;
	protected Location alienPosition;
	protected Location waveStartPosition;
	
	/**
	 * This method is used to set the alien position
	 * @param value
	 */
	public void setAlienPosition(Location value)
	{
		alienPosition = value;
	}
	
	/**
	 * This method is used to fetch the alien position
	 * @return
	 */
	public Location getAlienPosition()
	{
		return alienPosition;
	}
	
	/**
	 * This method is used to update
	 * @param waveHeadPosition head position of the wave
	 */
	public void waveUpdate(Location waveHeadPosition) 
	{
		this.waveStartPosition = waveHeadPosition;
	}
	
	/**
	 * This method is used to set head position of the wave
	 */
	public void setWaveHeadPosition(Location value)
	{
		waveStartPosition = value;
	}
	
	/**
	 * This method is used to get the wave head position
	 * @return
	 */
	public Location getWaveHeadPosition()
	{
		return waveStartPosition;
	}
}
