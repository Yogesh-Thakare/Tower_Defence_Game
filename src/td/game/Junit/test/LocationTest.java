package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import td.game.model.Location;
import td.game.services.PositionService;

/**
 * This class is used to test Location
 * @author Team3
 *
 */
public class LocationTest 
{
	private Location p;
	private Location q;
	private PositionService positionService;

	/**
	 * This method is used for initial set up
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	/**
	 * This method is used to release resources after execution
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * This method is used for set up
	 */
	@Before
	public void setUp()
	{
		try 
		{
			p = new Location(4, 5);
			q = new Location(8, 8);
			positionService = new PositionService();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to test distance
	 */
	@Test
	public void testGetDistance() 
	{
		try 
		{
			double distance = positionService.getDistance(p, q);
			double expected = 5;
			assertEquals(distance, expected, 0.001);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to test the range
	 */
	@Test
	public void testIsInRange()
	{
		try 
		{
			assertTrue(positionService.isInRange(p, q, 6));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
