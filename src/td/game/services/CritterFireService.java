package td.game.services;

import java.util.Map;
import java.util.Map.Entry;
import td.game.model.TowerFeatureDecorator;
import td.game.model.Critter;
import td.game.model.Location;
import td.game.model.Tower;

/**
 * Shoot critter with selected targeting strategy
 * @author Team3
 */
public class CritterFireService 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * Calculate nearest critter to specific tower
	 * @param tower tower that we need for calculate which is the nearest
	 * @return nearestCritter nearest critter to the tower
	 */
	public Critter nearestCritter(Tower tower)
	{
		Critter nearest = null;
		Location nearestPosition =null;
		double nearestDistance = Double.MAX_VALUE;
		try 
		{
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Location> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Location>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Location> entry : it) 
			{
				Location[] path = entry.getKey().getPath();
				Location tPosition = path[entry.getKey().getCurrentPosition()];
				double distance = positionService.getDistance(tPosition, tower.getTowerPosition());
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && distance <= nearestDistance)
				{
					nearestDistance= distance;
					nearest = entry.getKey();
					nearestPosition = entry.getValue();
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return nearest;
	}
	
	/**
	 * Calculate weakest Critter based on critter life
	 * @param tower a tower
	 * @return weakestCritter weakest in the range
	 */
	public Critter weakestCritter(Tower tower)
	{
		Critter weakest = null;
		Location weakestPosition =null;
		int lowestLife = Integer.MAX_VALUE;
		try {
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Location> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Location>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Location> entry : it) 
			{
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getLife() < lowestLife)
				{
					lowestLife= ((Critter)entry.getKey()).getLife();
					weakest = entry.getKey();
					weakestPosition = entry.getValue();
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return weakest;
	}
	
	/**
	 * Calculate strongest Critter Critter based on critter life
	 * @param tower tower
	 * @return strongestCritter return the strongest
	 */
	public Critter strongestCritter(Tower tower)
	{
		Critter strongest = null;
		Location strongestPosition =null;
		int highestLife = Integer.MIN_VALUE;
		try 
		{
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Location> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Location>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Location> entry : it) 
			{
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getLife() > highestLife)
				{
					highestLife= ((Critter)entry.getKey()).getLife();
					strongest = entry.getKey();
					strongestPosition = entry.getValue();
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return strongest;
	}
	
	/**
	 * Calculate nearest critter to exit gate
	 * @param tower tower
	 * @param path path in a map
	 * @return nearToEndCritter nearest critter that is in the range of the tower
	 */
	public Critter nearToEndCritter(Tower tower,Location[] path)
	{
		Critter nearToEnd = null;
		Location nearToEndPosition =null;
		int nearestDistance = path.length - 1;
		try 
		{
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Location> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Location>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Location> entry : it) 
			{
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && ((path.length -1) - entry.getKey().getCurrentPosition())<= nearestDistance)
				{
					nearestDistance= (path.length -1) - entry.getKey().getCurrentPosition();
					nearToEnd = entry.getKey();
					nearToEndPosition = entry.getValue();
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return nearToEnd;
	}
	
	/**
	 * Calculate nearest critter to entrance gate
	 * @param tower tower
	 * @param path valid path
	 * @return nearToStartCritter nearest to start
	 */
	public Critter nearToStartCritter(Tower tower,Location[] path)
	{
		Critter nearToStart = null;
		Location nearToStartPosition =null;
		int nearestDistance = path.length - 1;
		try 
		{
			PositionService positionService = new PositionService();
			TowerFactory towerFactory = new TowerFactory();
			int range = towerFactory.getRange(tower);
			Map<Critter, Location> map = ((TowerFeatureDecorator)tower).getCrittersLocation();
			Iterable<Entry<Critter, Location>> it = (((TowerFeatureDecorator)tower).getCrittersLocation()).entrySet();
			for (Entry<Critter, Location> entry : it) 
			{
				if(positionService.isInRange(tower.getTowerPosition(), entry.getValue(), range) && entry.getKey().getCurrentPosition() <= nearestDistance)
				{
					nearestDistance= (path.length -1) - entry.getKey().getCurrentPosition();
					nearToStart = entry.getKey();
					nearToStartPosition = entry.getValue();
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return nearToStart;
	}
}
