package td.game.model;

import td.game.constants.TowerCharactersticsConstants;
import td.game.services.CritterFireService;
import td.game.services.Log4jLogger;
import td.game.services.Observer;
import td.game.services.TowerFactory;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

/**
 * <b>This class is used for decorator design pattern for towers</b>
 * @author Team3
 */
@SuppressWarnings("serial")
public abstract class TowerFeatureDecorator extends Tower implements Observer 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private Critter targetCritter;
	public Map<Critter, Location> crittersLocation;
	public double nearestDistance;
	
	/**
	 * This method is used to return a map contain critters and their positions
	 * @return crittersLocation
	 */
	public Map<Critter, Location> getCrittersLocation() 
	{
		return crittersLocation;
	}
	
	/**
	 * This method is used to set critter locations in the map in our Map data structure
	 * @param crittersLocation a key value pair for keeping critter in a range for a tower
	 */
	public void setCrittersLocation(Map<Critter, Location> crittersLocation) 
	{
		crittersLocation = crittersLocation;
	}
	
	/**
	 * This method is used to remove dead critters from our wave
	 * @param critter critter
	 */
	public void removeDeadCritter(Critter critter) 
	{
		try 
		{
			crittersLocation.remove(critter);
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}

	/**
	 * <b>This method is used as a signature of getting description of the tower that is means it
	 * can return tower type and the all its features as a String</b> 
	 * @return description of the tower
	 */
	public abstract String getDescription();
	
	/**
	 * This method is used to implement observer pattern
	 * @param defenderInformer defender informer is added for deploy the observer pattern
	 */
	public void register(Subject defenderInformer) 
	{
		this.subject = defenderInformer;
		this.subject.registerObserver(this);
	}
	
	/**
	 * This method is used to return target critter which tower has to shoot it
	 * @return target
	 */
	public Critter getTarget() 
	{
		return targetCritter;
	}
	
	/**
	 * This method is used to get the defender
	 * @return TowerFeatureDecorator 
	 */
	public TowerFeatureDecorator getDefender() 
	{
		return this;
	}
	
	/**
	 * This method is used to set target for shooting
	 * @param target critter that is calculated as a target
	 */
	public void setTarget(Critter target) 
	{
		this.targetCritter = target;
	}
	
	/**
	 * This method is used to get the distance
	 * @param p first position
	 * @param q goal position
	 * @return the distance between two places
	 */
	public double getDistance(Location p, Location q) 
	{
		double result = 0;
		try 
		{
			result = Math.sqrt(Math.pow((p.getX() - q.getX()), 2)
					+ Math.pow((p.getY() - q.getY()), 2));
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return result;
	}

	/**
	 * This method is used to check if critter is in range
	 * @param source first position            
	 * @param destination goal position            
	 * @param range the range that is used as a scale           
	 * @return true, if position q is in the range
	 */
	public boolean checkIfCritterInRange(Location source, Location destination, int range) 
	{
		try 
		{
			return ((destination.getY() <= source.getY() + range && destination
					.getY() >= source.getY() - range) && (destination.getX() <= source
					.getX() + range && destination.getX() >= source.getX()
					- range));
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return false;
	}
	

	/**
	 * This method is used to implement for having observer design pattern
	 */
	public void alienUpdate(Location alienPosition, Critter critter,String shootingStrategy) 
	{
		try 
		{
			Map<Critter, Location> map = this.getCrittersLocation();
			Set<Entry<Critter, Location>> it =  map.entrySet();			
			CritterFireService service =  new CritterFireService();
			TowerFactory factory = new TowerFactory();
			int range = factory.getRange(this);	
			
			if(!checkIfCritterInRange(this.getTowerPosition(), 
					critter.getPath()[critter.getCurrentPosition()], range))
			{
				for (Entry<Critter, Location> entry : it) 
				{
					if(entry.getKey() == critter)
					{
						map.remove(entry.getKey());
						this.setCrittersLocation(map);
						break;
					}
				}
			}
			
			if(checkIfCritterInRange(this.getTowerPosition(), 
					critter.getPath()[critter.getCurrentPosition()], range) && critter.getLife() > 0)
			{
				map.put(critter, alienPosition);
				this.setCrittersLocation(map);
				Critter c = null;
				if(shootingStrategy == null)
				{
					this.setShootingStrategy( TowerCharactersticsConstants.NEARTOEND_STRATEGY);
					c = service.nearToStartCritter(this, critter.getPath());
			
				}
				else
				{
					switch (shootingStrategy) 
					{
						case TowerCharactersticsConstants.NEARTOEND_STRATEGY:
							c = service.nearToEndCritter(this, critter.getPath());
							break;
	
						case TowerCharactersticsConstants.NEARTOSTART_STRATEGY:
							c = service.nearToStartCritter(this, critter.getPath());
							break;
	
						case TowerCharactersticsConstants.STRONGEST_STRATEGY:
							c = service.strongestCritter(this);
							break;
	
						case TowerCharactersticsConstants.WEAKEST_STRATEGY:
							c = service.weakestCritter(this);
							break;
	
						case TowerCharactersticsConstants.NEARTOTOWER_STRATEGY:
							c = service.nearestCritter(this);
							break;
					}
				}
				if(c !=null)
				{
					setTarget(c);
					setChanged();
					notifyObservers();
				}
			}
			else
			{
				for (Entry<Critter, Location> entry : it) 
				{
					if(entry.getKey() == critter)
					{
						map.remove(entry);
						this.setCrittersLocation(map);
						break;
					}
				}
			}			
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}	
}
