package td.game.services;

import td.game.model.Critter;
import td.game.model.CritterNormalMove;

/**
 * This Class is the service class for the critter burning effect
 * @author Team3
 *
 */
public class CritterFreezeService 
{
	/**
	 * This function is used to freeze critter for time specified
	 */
	public void freezeCritter(Critter target,int range,int freezeTime) 
	{
		((CritterNormalMove) target.getMovingBehaviour())
		.setFreezeTime(freezeTime);
		target.setResistance(target.getResistance()-range);
		
		if(target.getResistance()==0)
		{
			target.setLife(target.getLife() - range);
			target.setResistance(target.getWaveNumber());
		}
	}
}
