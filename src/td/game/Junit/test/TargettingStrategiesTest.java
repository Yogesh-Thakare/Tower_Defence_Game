package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import td.game.model.Critter;
import td.game.model.Location;
import td.game.model.NormalCritter;
import td.game.model.TowerFeatureDecorator;
import td.game.model.TowerLevelEnum;
import td.game.services.CritterFireService;
import td.game.services.TowerFactory;
import td.game.model.Tower;


/**
 * This is a test class for testing all strategies
 * @author Team 3
 */
public class TargettingStrategiesTest 
{
	private TowerFeatureDecorator tower;
	private TowerFactory factory;
	private Location[] path;
	private CritterFireService shootingService;

	/**
	 * Setup before class execution method
	 */
	@BeforeClass
	public static void setUpBeforeClass()
	{
	}

	/**
	 * Tear down method after class execution
	 */
	@AfterClass
	public static void tearDownAfterClass()
	{
	}

	/**
	 * This is a setup class
	 */
	@Before
	public void setUp()
	{
		factory = new TowerFactory();
		Location[] p = {new Location(0,0), new Location(0,1),
				new Location(0,2), new Location(0,3),
				new Location(0,4), new Location(1,4),
				new Location(2,4), new Location(3,4),
				new Location(3,5), new Location(3,6),
				new Location(3,7)};
		path = p;
		
		this.tower = (TowerFeatureDecorator) factory.getTower("HeleneTower", TowerLevelEnum.ONE);
		((Tower)this.tower).setTowerPosition(new Location(1, 3));
		Critter critter1 = new NormalCritter(new Location(0,0), path);
		Critter critter2 = new NormalCritter(new Location(0,0), path);
		Critter critter3 = new NormalCritter(new Location(0,0), path);
		critter1.setCurrentPosition(1);
		critter1.setLife(200);
		critter2.setCurrentPosition(3);
		critter2.setLife(250);
		critter3.setCurrentPosition(9);
		critter3.setLife(300);
		Map<Critter, Location> crittersLocation = new HashMap<Critter, Location>();
		crittersLocation.put(critter1, new Location(0,1));
		crittersLocation.put(critter2, new Location(0,3));
		crittersLocation.put(critter3, new Location(3,6));
		tower.setCrittersLocation(crittersLocation);
		this.shootingService = new CritterFireService();
	}

	/**
	 * This is a test method to test near to start strategy
	 */
	@Test
	public void testNearToStartCritter() 
	{
		try 
		{
			Critter expected = shootingService.nearToStartCritter(this.tower, this.path);
			int place = expected.getCurrentPosition();
			Location actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Location(0,1)));
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method to test near to end strategy
	 */
	@Test
	public void testNearToEndCritter() 
	{
		try 
		{
			Critter expected = shootingService.nearToEndCritter(this.tower, this.path);
			int place = expected.getCurrentPosition();
			Location actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Location(3,6)));
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method to test strongest strategy
	 */
	@Test
	public void testStrongestCritter() 
	{
		try 
		{
			Critter expected = shootingService.strongestCritter(this.tower);
			int actualLife = expected.getLife();
			assertEquals(actualLife, 300);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method to test weakest strategy
	 */
	@Test
	public void testWeakestCritter() 
	{
		try 
		{
			Critter expected = shootingService.weakestCritter(this.tower);
			int actualLife = expected.getLife();
			assertEquals(actualLife, 200);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method to test nearest strategy
	 */
	@Test
	public void testNearestCritter() 
	{
		try 
		{
			Critter expected = shootingService.nearestCritter(this.tower);
			int place = expected.getCurrentPosition();
			Location actualPosition = this.path[place];
			assertTrue(actualPosition.equals(new Location(0,3)));			
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
}
