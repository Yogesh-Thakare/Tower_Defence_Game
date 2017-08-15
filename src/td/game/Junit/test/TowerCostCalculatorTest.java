package td.game.Junit.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import td.game.constants.TowerCharactersticsConstants;
import td.game.model.CitidelleTower;
import td.game.model.FirePower;
import td.game.model.FireRange;
import td.game.model.FireSpeed;
import td.game.model.HeleneTower;
import td.game.model.Tower;
import td.game.services.TowerSellPriceCalculator;

/**
 * This is a test class to test costs related to towers
 * @author Team 3
 */
public class TowerCostCalculatorTest 
{
	private CitidelleTower citidelle;
	private HeleneTower helene;

	/**
	 * Setup before class execution method
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * Tear down method after class execution
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * setup method
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		citidelle = new CitidelleTower();
		helene = new HeleneTower();
	}

	/**
	 * This is a test method to test fire range
	 */
	@Test
	public void testFireRangeCost() 
	{
		try 
		{
			long specialHelene = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_RANGE;
			long specialCitidelle = TowerCharactersticsConstants.CITIDELLE_TOWER
					+ TowerCharactersticsConstants.FIRE_RANGE;
			Tower mWithRange = new FireRange(helene);
			Tower aWithRange = new FireRange(citidelle);
			
			assertEquals(specialHelene, mWithRange.towerCost());
			assertEquals(specialCitidelle, aWithRange.towerCost());
		} catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}

	/**
	 * This is a test method to test fire speed
	 */
	@Test
	public void testFireSpeedCost() 
	{
		try 
		{
			long specialModern = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_SPEED;
			long specialAncient = TowerCharactersticsConstants.CITIDELLE_TOWER
					+ TowerCharactersticsConstants.FIRE_SPEED;
			Tower mWithSpeed = new FireSpeed(helene);
			Tower aWithSpeed = new FireSpeed(citidelle);
			
			assertEquals(specialModern, mWithSpeed.towerCost());
			assertEquals(specialAncient, aWithSpeed.towerCost());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}

	/**
	 * This is a test method to test fire power
	 */
	@Test
	public void testFirePowerCost() 
	{
		try 
		{
			long specialHelene = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER;
			long specialCitidelle = TowerCharactersticsConstants.CITIDELLE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER;
			Tower mWithPower = new FirePower(helene);
			Tower aWithPower = new FirePower(citidelle);
			
			assertEquals(specialHelene, mWithPower.towerCost());
			assertEquals(specialCitidelle, aWithPower.towerCost());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method to test tower type
	 */
	@Test
	public void testTowerTypeCost() 
	{
		try 
		{
			assertEquals(TowerCharactersticsConstants.CITIDELLE_TOWER, citidelle.towerCost());
			assertEquals(TowerCharactersticsConstants.HELENE_TOWER, helene.towerCost());
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method for sell
	 */
	@Test
	public void testSell()
	{
		try 
		{
			double specialModern = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED;
			
			// crate modern with three features
			Tower modernCombo = new FirePower(helene);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			TowerSellPriceCalculator market = new TowerSellPriceCalculator();
			
			long sellResult = market.getSellTowerAmount(modernCombo);
			long expected = (long) ((specialModern * TowerCharactersticsConstants.SELL_PERCENTAGE)/100);			
			assertEquals(expected, sellResult, 0.001);			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();	
		}
	}
	
	/**
	 * This is a test method for objects
	 */
	@Test
	public void testObjectDetails() 
	{
		try 
		{
			List<String> modernExpected = Arrays.asList("HeleneTower",
					"FirePower", "FireRange", "FireSpeed");
			List<String> ancientExpected = Arrays.asList("CitidelleTower",
					"FirePower", "FireRange", "FireSpeed");

			// crate modern with three features
			Tower modernCombo = new FirePower(helene);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			List<String> modernDetails = new ArrayList<String>();
			for (Tower detail : modernCombo.towerInformation()) 
			{
				modernDetails.add(detail.getClass().getSimpleName());
			}

			// crate ancient with three features
			Tower ancientCombo = new FirePower(citidelle);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			List<String> ancientDetails = new ArrayList<String>();
			for (Tower detail : ancientCombo.towerInformation()) 
			{
				ancientDetails.add(detail.getClass().getSimpleName());
			}
			// assert part
			assertEquals(modernExpected, modernDetails);
			assertEquals(ancientExpected, ancientDetails);
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	/**
	 * This is a test method for combo
	 */
	@Test
	public void testThreeTimesComboFeature() 
	{
		try 
		{
			long specialModern = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED;
			long specialAncient = TowerCharactersticsConstants.CITIDELLE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED;
			// crate modern with three features
			Tower modernCombo = new FirePower(helene);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);
			modernCombo = new FirePower(modernCombo);
			modernCombo = new FirePower(modernCombo);

			// crate ancient with three features
			Tower ancientCombo = new FirePower(citidelle);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);
			ancientCombo = new FirePower(ancientCombo);
			ancientCombo = new FirePower(ancientCombo);

			// assert part
			assertEquals(specialModern, modernCombo.towerCost());
			assertEquals(specialAncient, ancientCombo.towerCost());
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}

	}
	
	/**
	 * This is a test method for combo feature
	 */
	@Test
	public void testComboFeature() 
	{
		try 
		{
			long specialModern = TowerCharactersticsConstants.HELENE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED;
			long specialAncient = TowerCharactersticsConstants.CITIDELLE_TOWER
					+ TowerCharactersticsConstants.FIRE_POWER
					+ TowerCharactersticsConstants.FIRE_RANGE
					+ TowerCharactersticsConstants.FIRE_SPEED;
			// crate modern with three features
			Tower modernCombo = new FirePower(helene);
			modernCombo = new FireRange(modernCombo);
			modernCombo = new FireSpeed(modernCombo);

			// crate ancient with three features
			Tower ancientCombo = new FirePower(citidelle);
			ancientCombo = new FireRange(ancientCombo);
			ancientCombo = new FireSpeed(ancientCombo);

			// assert part
			assertEquals(specialModern, modernCombo.towerCost());
			assertEquals(specialAncient, ancientCombo.towerCost());
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
}
