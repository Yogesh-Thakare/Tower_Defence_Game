package td.game.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import td.game.model.Critter;

/**
 * This Class is the service class for the critter burning effect
 * @author Team3
 */
public class CritterBurnService 
{
	private HashMap<Critter, Integer> burningCritters = new HashMap<Critter, Integer>();

	/**
	 * This function to add the burning critters
	 * @param target The target critters
	 * @param repeatTimes based on tower power
	 */
	public void addBurningCritter(Critter target, int repeatTimes) 
	{
		burningCritters.put(target, repeatTimes);
	}
	
	/**
	 * This function is used to add burn to the critter
	 */
	public void burnCritter() 
	{
		Iterable<Entry<Critter, Integer>> its = burningCritters.entrySet();
		
		for (Entry<Critter, Integer> pairs : its) 
		{
			Critter critter = pairs.getKey();
			critter.setResistance(critter.getResistance()-1);
			
			if(critter.getResistance()==0)
			{
				critter.setLife(critter.getLife()-1);
				pairs.setValue(pairs.getValue()-1);
				critter.setResistance(critter.getWaveNumber());
			}
		}
		for(Iterator<Map.Entry<Critter, Integer>> it = burningCritters.entrySet().iterator(); it.hasNext(); ) 
		{
		    Map.Entry<Critter, Integer> entry = it.next();
		    if(entry.getValue().equals(0)) 
		    {
		       it.remove();
		    }
		}
	}
}
