package td.game.Junit.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import td.game.model.MapContents;
import td.game.model.MapObjects;
import td.game.services.MapController;

/**
 * This class is used to test Map controller
 * @author Team3
 *
 */
public class MapControllerTest 
{
	private static String fileName;
	private static MapObjects originalGrid;
	
	/**
	 * This method is used for initial set up
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		fileName = "test_file.fil";

		originalGrid = new MapObjects();
		originalGrid.setSize(10, 10);
		originalGrid.setCell(0, 5, MapContents.ENTRANCE);
		for(int i=1; i<9; i++)
		{
			originalGrid.setCell(i, 5, MapContents.PATH);
		}
		originalGrid.setCell(9, 5, MapContents.EXIT);
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
	}

	/**
	 * This method is used to test save and load of map
	 */
	@Test
	public void testMapSaveLoad() 
	{
		MapController mapManager = new MapController();
		mapManager.saveMapGridAsFile(originalGrid, fileName);
		MapObjects loadedGrid = new MapObjects();
		loadedGrid = mapManager.loadSavedMapFromFile(fileName);
		
		Assert.assertEquals(originalGrid.getWidth(), loadedGrid.getWidth());
		Assert.assertEquals(originalGrid.getHeight(), loadedGrid.getHeight());
		
		for(int i =0; i<originalGrid.getWidth();i++)
		{
			for(int j =0; j<originalGrid.getHeight();j++)
			{
				Assert.assertEquals(originalGrid.getCell(i, j), loadedGrid.getCell(i, j));
			}
		}
	}
}
