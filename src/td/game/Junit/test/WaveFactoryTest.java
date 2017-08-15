package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import td.game.constants.GameConstants;
import td.game.model.Critter;
import td.game.model.Location;
import td.game.model.NormalCritter;
import td.game.model.Wave;
import td.game.services.WaveFactory;

/**
 * This is a test class for wave factory
 * @author Team 3
 *
 */
public class WaveFactoryTest 
{
	WaveFactory testWave;

	/**
	 * Setup before method
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * Tear down after method
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * Setup method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		testWave = new WaveFactory();
	}

	/**
	 * This is a test method to test first wave
	 */
	@Test
	public void testFirstWave() 
	{		
		int expectedResistance=1;
		int expectedWaveNumber=1;
		Wave wave = testWave.getCreateWave("NormalCritter", new Location(0, 0), null,1);
		assertEquals(10, wave.critters.size());		
		for (Critter critter : wave.critters) 
		{
			assertTrue(critter instanceof NormalCritter);
		}		
		
		assertEquals(expectedResistance, wave.critters.get(0).getResistance());
		assertEquals(expectedWaveNumber, wave.critters.get(0).getWaveNumber());
	}
	
	/**
	 * This is a test method to test second wave
	 */
	@Test
	public void testSecondWave() 
	{	
		int expectedResistance=2;
		int expectedWaveNumber=2;
		Wave wave = testWave.getCreateWave("NormalCritter", new Location(0, 0), null,2);
		assertEquals(10, wave.critters.size());		
		for (Critter critter : wave.critters) 
		{
			assertTrue(critter instanceof NormalCritter);
		}
		
		assertEquals(expectedResistance, wave.critters.get(0).getResistance());
		assertEquals(expectedWaveNumber, wave.critters.get(0).getWaveNumber());
	}

	/**
	 * This is a test method to test wave factory
	 */
	@Test
	public void testWaveFactory() 
	{
		Location[] path = {
				new Location(0, 0),new Location(0, 1),
				new Location(0, 2),new Location(0, 3),
				new Location(0, 4),new Location(0, 5),
				new Location(1, 9),new Location(1, 8),
				new Location(2, 16),new Location(2, 17),
				new Location(2, 18)
		};
		WaveFactory factory = new WaveFactory();
		Wave wave = factory.getCreateWave("NormalCritter", new Location(0, 0), path,1);
		for (Critter c : wave.critters) 
		{
			assertTrue(c instanceof NormalCritter);
		}
		
		assertEquals(wave.critters.size(),  GameConstants.WAVE_SIZE);
	}
}
