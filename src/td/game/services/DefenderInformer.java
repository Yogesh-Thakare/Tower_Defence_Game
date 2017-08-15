package td.game.services;

import java.util.ArrayList;
import java.util.List;
import td.game.model.Critter;
import td.game.model.Location;
import td.game.model.Subject;
/**
 * This class is used to implement observer design pattern which informs all 
 * towers about the wave's head position
 * @author Team3
 */
public class DefenderInformer implements Subject, ITowerInformer, java.io.Serializable 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private static final long serialVersionUID = -5999028712196865753L;
	//represents wave head position
	private Location waveHeadPosition; 
	private Critter critter;
	private Location alienPosition;
	private String shootingStrategy;
	//List of observer that contains all observer for implementing observer design pattern
	private List<Observer> observers;
	
	/**
	 * This constructor is used to instantiate a new defender informer.
	 */
	public DefenderInformer()
	{
		observers = new ArrayList<Observer>();
		alienPosition = new Location(0,0);
	}	
	
	/**
	 * <b>
	 * This method registers all observers in observer's list
	 * </b>
	 * @param o Observer object
	 */
	@Override
	public void registerObserver(Observer o) 
	{
		try 
		{
			if( o !=null)
				observers.add(o);			
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * <b>
	 * This method removes a observer from observer's list
	 * </b>
	 * @param o Observer object
	 */
	@Override
	public void removeObserver(Observer o) 
	{
		try 
		{
			if (o !=null)
				observers.remove(o);
		} catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * <b>This method force observers to update and inform towers about the position of aliens.</b>
	 */
	@Override
	public void notifyObservers() 
	{
		try 
		{
			for (Observer ob : observers) 
			{
				ob.waveUpdate(waveHeadPosition);
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method force observers to update and inform towers about the position of aliens
	 */
	@Override
	public void notifyDefenders() 
	{
		try 
		{
			for (Observer ob : observers) 
			{
				ob.alienUpdate(alienPosition, critter, shootingStrategy);
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to set the wave's position
	 * @param x integer 
	 * @param y integer
	 */
	@Override
	public void setAlienPosition(int x, int y, Critter critter, String strategy)
	{
		try 
		{
			this.alienPosition.setX(x);
			this.alienPosition.setY(y);
			this.critter = critter;
			this.shootingStrategy = strategy;
			alienPositionChange();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method sets the wave'poisition
	 * @param x as integer 
	 * @param y as integer
	 */
	@Override
	public void setWavePosition(int x, int y)
	{
		try 
		{
			this.waveHeadPosition.setX(x);
			this.waveHeadPosition.setY(y);
			wavePositionChange();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to notify the observers if the x and y of the head changes the setPosition 
	 */
	@Override
	public void alienPositionChange()
	{
		try 
		{
			notifyDefenders();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	* This method is used to notify the observers if the x and y of the head changes the setPosition 
	*/
	@Override
	public void wavePositionChange()
	{
		try 
		{
			notifyObservers();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
     *This method is used to return the critter position
	 * @return Position position of critter
	 */
	@Override
	public Location getAlienPosition() 
	{
		return alienPosition;
	}
	
	/**
	 *This method is used to return the current position of the wave's head
	 * @return Position position of wave
	 */
	@Override
	public Location getWavePosition() 
	{
		return waveHeadPosition;
	}
}
