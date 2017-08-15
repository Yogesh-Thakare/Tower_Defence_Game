package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import td.game.model.ChambyTower;
import td.game.model.CitidelleTower;
import td.game.model.FirePower;
import td.game.model.FireRange;
import td.game.model.FireSpeed;
import td.game.model.HeleneTower;
import td.game.model.Tower;
import td.game.model.TowerLevelEnum;
import td.game.services.TowerFactory;

/**
 * This is a test class to test all tower features
 * @author Team 3
 */
public class TowerFeaturesTest 
{
	private String helene;
	private String citidelle;
	private String chamby;
	TowerFactory towerFactory;

	/**
	 * Setup before class execution method
	 */
	@BeforeClass
	public static void setUpBeforeClass()
	{
	}

	/**
	 * Tear down after class execution method
	 */
	@AfterClass
	public static void tearDownAfterClass()
	{
	}

	/**
	 * This is a setup method
	 */
	@Before
	public void setUp()
	{
		try 
		{
			helene = HeleneTower.class.getSimpleName();
			citidelle = CitidelleTower.class.getSimpleName();
			chamby = ChambyTower.class.getSimpleName();
			towerFactory = new TowerFactory();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}

	/**
	 * This is a test method for get tower functionality
	 */
	@Test
	public void testGetTower() 
	{
		try 
		{
			Tower heleneObj = towerFactory.getTower("HeleneTower");
			Tower citidelleObj = towerFactory.getTower("CitidelleTower");
			Tower chambyObj = towerFactory.getTower("ChambyTower");
			String heleneExpected = heleneObj.getClass().getSimpleName();
			String citidelleExpected = citidelleObj.getClass().getSimpleName(); 
			String chambyExpected = chambyObj.getClass().getSimpleName();
			assertEquals(heleneExpected, helene);
			assertEquals(citidelleExpected, citidelle);
			assertEquals(chambyExpected, chamby);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method for decorated tower functionality
	 */
	@Test 
	public void testGetDecoratedTower()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			Tower tower = towerFactory.getDecoratedTower(expDecorated.towerInformation());			
			assertEquals(expDecorated.towerCost(), tower.towerCost());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method for get tower functionality on basis of 3 levels
	 */
	@Test
	public void testGetTowerByLevelThree()
	{
		try 
		{
			Tower tower = towerFactory.getTower("HeleneTower", TowerLevelEnum.THREE);	
			Tower expected = towerFactory.getTower("HeleneTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			assertEquals(expDecorated.towerCost(), tower.towerCost());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for get tower functionality on basis of 2 levels
	 */
	@Test
	public void testGetTowerByLevelTwo()
	{
		try 
		{
			Tower tower = towerFactory.getTower("HeleneTower", TowerLevelEnum.TWO);			
			Tower expected = towerFactory.getTower("HeleneTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FirePower(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			assertEquals(expDecorated.towerCost(), tower.towerCost());

		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method for get tower functionality on basis of 1 level
	 */
	@Test
	public void testGetTowerByLevelOne()
	{
		try 
		{
			Tower tower = towerFactory.getTower("HeleneTower", TowerLevelEnum.ONE);
			Tower expected = towerFactory.getTower("HeleneTower");
			Tower expDecorated = new FirePower(expected);
			expDecorated = new FireRange(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			assertEquals(expDecorated.towerCost(), tower.towerCost());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for get range
	 */
	@Test
	public void testGetRange()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 1;			
			assertEquals((Double)expecteRange, (Double)range);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for 2 level get range
	 */
	@Test
	public void testGet2levelRange()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 2;			
			assertEquals((Double)expecteRange, (Double)range);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for 3 level get range
	 */
	@Test
	public void testGet3levelRange()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireRange(expected);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FireRange(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double range = towerFactory.getRange(expDecorated);
			double expecteRange = 3;			
			assertEquals((Double)expecteRange, (Double)range);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This is a test method for 2 level speed
	 */
	@Test
	public void testGet2LevelSpeed()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 2;			
			assertEquals((Double)expectedSpeed, (Double)speed);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for get speed
	 */
	@Test
	public void testGetSpeed()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 1;			
			assertEquals((Double)expectedSpeed, (Double)speed);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for get 3 level speed
	 */
	@Test
	public void testGet3LevelSpeed()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FireSpeed(expected);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FireSpeed(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double speed = towerFactory.getSpeed(expDecorated);
			double expectedSpeed = 3;			
			assertEquals((Double)expectedSpeed, (Double)speed);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method for get power
	 */
	@Test
	public void testGetPower()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 1;			
			assertEquals((Double)expectePower, (Double)power);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method for get 2 level power
	 */
	@Test
	public void testGet2LevelPower()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 2;			
			assertEquals((Double)expectePower, (Double)power);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();			
		}
	}
	
	/**
	 * This is a test method for get 3 level power
	 */
	@Test
	public void testGet3LevelPower()
	{
		try 
		{
			Tower expected = towerFactory.getTower("HeleneTower");
			List<Tower> lst = expected.getTowers();
			Tower expDecorated = new FirePower(expected);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated = new FirePower(expDecorated);
			lst.add(expDecorated);
			expDecorated.setTowers(lst);
			double power = towerFactory.getPower(expDecorated);
			double expectePower = 3;			
			assertEquals((Double)expectePower, (Double)power);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
}
