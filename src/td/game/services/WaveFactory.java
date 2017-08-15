package td.game.services;

import td.game.model.Wave;
import td.game.constants.GameConstants;
import td.game.model.NormalCritter;
import td.game.model.Location;

/**
 * This class is used for creating the critter wave
 * @author Team3
 */
public class WaveFactory 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * This method takes wave type name and it will create a type of wave
	 * @param waveType wave that contains a list of critter
	 * @param start start point
	 * @param path valid path
	 * @return Wave correct wave for walking 
	 */
	public Wave getCreateWave(String waveType,Location start, Location[] path, int waveNumber) 
	{
		Wave wave = new Wave();
		wave.setHeadPosition(start);
		try 
		{
			if (waveNumber==1) 
			{
				for (int i = 0; i < GameConstants.WAVE_SIZE; i++) 
				{
					NormalCritter critter = new NormalCritter(start, path);
					critter.Id= (new Integer(i)).toString();
					wave.critters.add(critter);
				}
				return wave;
			} 
			else
			{	
				for (int i = 0; i < GameConstants.WAVE_SIZE; i++) 
				{
					NormalCritter critter = new NormalCritter(start, path);
					critter.Id= (new Integer(i)).toString();
					critter.setLife(critter.getLife()*(waveNumber));
					critter.setResistance(waveNumber);
					critter.setWaveNumber(waveNumber);
					wave.critters.add(critter);
				}
				return wave;
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}
}
