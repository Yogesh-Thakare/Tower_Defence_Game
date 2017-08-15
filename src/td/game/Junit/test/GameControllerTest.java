package td.game.Junit.test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import td.game.model.ChambyTower;
import td.game.model.CitidelleTower;
import td.game.model.HeleneTower;
import td.game.model.MapContents;
import td.game.model.MapGrid;
import td.game.model.Tower;
import td.game.services.GameStateController;
import org.junit.Test;

/**
 * This class is used for Game controller test
 * @author Team3
 *
 */
public class GameControllerTest 
{
	private static GameStateController loadedGame;
	private static GameStateController stateManager;
	
	/**
	 * This method is used for initial set up
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		MapGrid map = new MapGrid(10, 10);
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				map.setCell(i, j, (i + j) % 2 == 0 ? MapContents.PATH : MapContents.SCENERY);
			}
		}
		
		Tower [][] towers = new Tower[10][10];
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if((i + j) % 2 != 0)
				{
					if((i + j) % 3 == 0)
					{
						towers[i][j] = new CitidelleTower();
					}
					else if((i + j) % 5 == 0)
					{
						towers[i][j] = new HeleneTower();
					}
					else
					{
						towers[i][j] = new ChambyTower();
					}
				}
			}	
		}
		
		map.setTowers(towers);
		int wave = 2;
		String fileName = "sample_map.txt";
		stateManager = new GameStateController(map, wave, "");
		loadedGame = GameStateController.load(fileName);
		stateManager = new GameStateController(map, wave, "");
		GameStateController.save(fileName, stateManager);
		loadedGame = GameStateController.load(fileName);
	}

	/**
	 * This method is used to test loaded game
	 */
	@Test
	public void testLoadedGame() 
	{
		Tower [][] towers = new Tower[10][10];
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if((i + j) % 2 != 0)
				{
					if((i + j) % 3 == 0)
					{
						towers[i][j] = new CitidelleTower();
					}
					else if((i + j) % 5 == 0)
					{
						towers[i][j] = new HeleneTower();
					}
					else
					{
						towers[i][j] = new ChambyTower();
					}
				}
			}	
		}
		
		assertEquals(loadedGame.getBalance(), stateManager.getBalance());
		assertEquals(loadedGame.getCurrentBalance(), stateManager.getCurrentBalance());
		assertEquals(loadedGame.getLife(), stateManager.getLife());
		assertEquals(loadedGame.getMapLocation(), stateManager.getMapLocation());
		assertEquals(loadedGame.getWaveNum(), stateManager.getWaveNum());
		assertEquals(loadedGame.getMap().getHeight(), stateManager.getMap().getHeight());
		assertEquals(loadedGame.getMap().getWidth(), stateManager.getMap().getWidth());
		assertEquals(loadedGame.getMap().getUnitSize(), stateManager.getMap().getUnitSize());
		
		Tower[][] loadedTowers = loadedGame.getMap().getTowers();
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if((i + j) % 2 == 0)
				{
					assertEquals(loadedTowers[i][j], towers[i][j]); // both of the containers has to be null				
				}
				else
				{
					assertEquals(loadedTowers[i][j].getTowerType(), towers[i][j].getTowerType());
					assertEquals(loadedTowers[i][j].getLevel(), towers[i][j].getLevel());
					assertEquals(loadedTowers[i][j].getShootingStrategy(), towers[i][j].getShootingStrategy());
					assertEquals(loadedTowers[i][j].getTowerPosition(), towers[i][j].getTowerPosition());
				}
			}
		}
	}
	
	/**
	 * This method is used to test loaded Map
	 */
	@Test
	public void testLoadedMap() 
	{
		int width = loadedGame.getMap().getWidth();
		int height = loadedGame.getMap().getHeight();
		
		for(int x = 0; x < width; x++)		// checks all the scenery and path cells
		{ 
			for(int y = 0; y < height; y++)
			{
				assertEquals(loadedGame.getMap().getCell(x, y), stateManager.getMap().getCell(x, y));
			}
		}		
	}
}
