package td.game.Junit.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import td.game.model.Location;
import td.game.model.MapContents;
import td.game.services.IPathEndPointsCheckerService;
import td.game.services.MapUtility;
import td.game.services.PathEndPointsCheckerService;
import td.game.services.PathService;
import td.game.services.PathValidityChecker;

/**
 * This class is used to test Map correctness
 * @author Team3
 *
 */
public class MapCorrectnessTest 
{
	private MapContents[][] matrix; 
	private int[][] intMatrix;
	private PathService service;
	private MapContents[][] cellTypeMatrix;
	private PathValidityChecker checker;
	MapValidationUtility utility;

	/**
	 * This method is used for initial set up
	 */
	@BeforeClass
	public static void setUpBeforeClass()
	{
	}

	/**
	 * This method is used to release resources after execution
	 */
	@AfterClass
	public static void tearDownAfterClass()
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
			utility = new MapValidationUtility();
			checker = new PathValidityChecker();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This method is used to test the path
	 */
	@Test
	public void testIsTherePath() 
	{
		try 
		{
			matrix = utility.matrixCellType("sample_map.txt", 4, 8);
			IPathEndPointsCheckerService service = new PathEndPointsCheckerService();
			
			assertTrue(service.isTherePath(new Location(0, 0), new Location(3, 7), matrix));
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * The method is used to test the enter location
	 */
	@Test
	public void testGetEnter() 
	{
		try 
		{
			intMatrix = utility.matrixReadre("sample_map.txt", 4, 8);
			cellTypeMatrix = utility.matrixCellType("sample_map.txt", 4, 8);
			MapUtility mapUtility = new MapUtility();
			Location enter =mapUtility.getMapEnterance(cellTypeMatrix);
			Location expected = new Location(0, 0);
			
			assertTrue(enter.equals(expected));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to test the exit location
	 */
	@Test
	public void testGetExit()
	{
		try 
		{
			intMatrix = utility.matrixReadre("sample_map.txt", 4, 8);
			cellTypeMatrix = utility.matrixCellType("sample_map.txt", 4, 8);
			MapUtility mapUtility = new MapUtility();
			Location exit =mapUtility.getMapExit(cellTypeMatrix);
			Location expected = new Location(3, 7);
			
			assertTrue(exit.equals(expected));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * This method is used to test the path finder
	 */
	@Test
	public void testPathFinder() 
	{
		try 
		{
			MapValidationUtility utility3 = new MapValidationUtility();
			intMatrix = utility3.matrixReadre("sample_map.txt", 4, 8);
			cellTypeMatrix = utility3.matrixCellType("sample_map.txt", 4, 8);
			service = new PathService();
			Location[] expected = {new Location(0,0), new Location(0,1),
					new Location(0,2), new Location(0,3),
					new Location(0,4), new Location(1,4),
					new Location(2,4), new Location(3,4),
					new Location(3,5), new Location(3,6),
					new Location(3,7)};
			Location[] path = service.pathFinder(cellTypeMatrix);
			
			assertArrayEquals(null, expected, path);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This method is used to check if location is overlapping
	 */
	@Test
	public void testHasOverLap() 
	{
		try 
		{
			Location first = new Location(0, 0);
			Location second = new Location(0, 0);
			
			assertTrue(checker.hasOverlap(first, second));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This method is used to test the edge
	 */
	@Test
	public void testIsInEdge()
	{
		try 
		{
			Location position = new Location(12, 13);
			int width = 12;
			int height = 13;
			
			assertTrue(checker.isOnEdge(width, height, position));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	} 
	
	/**
	 * This method is used to test the end of the matrix
	 */
	@Test 
	public void testHasEnd()
	{
		try 
		{
			int width = 8;
			int height = 4;
			
			MapContents[][] matrix = utility.matrixCellType("sample_map.txt", height, width);
			
			assertTrue(checker.hasEndPoint(matrix));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This method is used to test the start of the matrix
	 */
	@Test
	public void testHasStart()
	{
		try 
		{
			int width = 8;
			int height = 4;
			
			MapContents[][] matrix = utility.matrixCellType("sample_map.txt", height, width);
			
			assertTrue(checker.hasStartPoint(matrix));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
	}
	
	/**
	 * This method is used to test the correct size
	 */
	@Test
	public void testIsCorrectSize()
	{
		int height = 30;
		int width = 35;
		
		assert(checker.isCorrectSize(height, width));
	}
}
