package td.game.Junit.test;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import td.game.model.LifeManager;

/**
 * This class is used to check life
 * @author Team3
 *
 */
public class LifeCheckerTest 
{
	
	private LifeManager lifeController;
	
	/**
	 * This method is used for initial set up
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	/**
	 * This method is used for releasing resources after execution
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	/**
	 * This method is used for setup
	 */
	@Before
	public void setUp()
	{
		try 
		{
			lifeController= LifeManager.getInstance();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to test balance
	 */
	@Test
	public void testResetBalance()
	{
		int expectedLife=30;
		int actualLife = lifeController.getLife();
		lifeController.addLife(10);
		
		assertEquals(actualLife+10, expectedLife);
	}
}
