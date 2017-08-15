package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import td.game.services.MoneyController;

/**
 * This is a test class to check all money parameters.
 * @author Team 3
 */
public class MoneyCheckerTest 
{
	private MoneyController moneyController;

	/**
	 * Before class method
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * After class method
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * This test method is used for initialization
	 */
	@Before
	public void setUp()
	{
		try 
		{
			moneyController = MoneyController.getInstance();		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This is a test method to check current balance
	 */
	@Test
	public void testAddCurrentBalance()
	{
		moneyController.resetCurrentBalanceMoney();
		moneyController.resetCurrentBalanceMoney();
		long actualBalance = moneyController.getCurrentBalanceMoney();
		long expected = moneyController.getCurrentBalanceMoney();		
		assertEquals(expected, actualBalance);
	}
	
	/**
	 * This is a test method to add the balance.
	 */
	@Test
	public void testAddBalance()
	{
		long actualBalance = moneyController.getBalanceMoney();
		long expected = moneyController.getBalanceMoney();		
		assertEquals(expected, actualBalance);
	}
	
}
