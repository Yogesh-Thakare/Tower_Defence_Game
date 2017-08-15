package td.game.Junit.test;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import td.game.model.Location;
import td.game.model.MapContents;
import td.game.model.MapGrid;
import td.game.model.NormalCritter;
import td.game.services.PathService;

/**
 * This is the JUnit class to test critter characteristics
 * @author Team3
 *
 */
public class CritterFeaturesTest 
{
	private static MapGrid grid;
	private static Location[] path;

	/**
	 * This method is used for initial set up
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		grid = new MapGrid(10,10);
		grid.setSize(10, 10);
		grid.setCell(0, 5, MapContents.ENTRANCE);
		for(int i=1; i<9; i++)
		{
			grid.setCell(i, 5, MapContents.PATH);
		}
		grid.setCell(9, 5, MapContents.EXIT);
		
		path = new PathService().pathFinder(grid.getContent());
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
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		
	}

	/**
	 * This method is used to test critter resistance
	 */
	@Test
	public void testResistance() 
	{	
		NormalCritter critter = new NormalCritter(new Location(9,7),path);
		critter.setResistance(3);
		
		// assert part
		int actual = critter.getResistance();
		int expected = 3;
		assertEquals(actual, expected);
	}
	
	/**
	 * This method is used to test wave number
	 */
	@Test
	public void testWaveNumber() 
	{	
		NormalCritter critter = new NormalCritter(new Location(9,7),path);
		critter.setWaveNumber(1);
		
		// assert part
		int actual = critter.getWaveNumber();
		int expected = 1;
		assertEquals(actual, expected);
	}
}
