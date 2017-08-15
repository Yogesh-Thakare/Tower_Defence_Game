package td.game.Junit.test;

import junit.framework.Assert;
import td.game.constants.TowerCharactersticsConstants;
import td.game.model.Critter;
import td.game.model.CritterNormalMove;
import td.game.model.Location;
import td.game.model.MapContents;
import td.game.model.MapGrid;
import td.game.model.NormalCritter;
import td.game.model.Tower;
import td.game.model.TowerLevelEnum;
import td.game.model.Wave;
import td.game.services.CritterBurnService;
import td.game.services.CritterSplashService;
import td.game.services.PathService;
import td.game.services.TowerFactory;
import td.game.services.WaveFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test class is to test all special effects
 * @author Team 3
 */
public class SpecialEffectsTest 
{
	private static MapGrid grid;

	/**
	 * Setup before class execution method
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
	 * This is a setup class
	 */
	@Before
	public void setUp() 
	{
	}

	/**
	 * This test method tests special effect of burning
	 */
	@Test
	public void testBurningSpecialEffect() 
	{
		PathService pathService = new PathService();
		NormalCritter critter = new NormalCritter(grid.getEntranceLocation(), pathService.pathFinder(grid.getContent()));
		int initLife = critter.getLife();
		
		CritterBurnService burningEffect = new CritterBurnService();
		burningEffect.addBurningCritter(critter, 1);
		burningEffect.burnCritter();
		
		Assert.assertEquals(critter.getLife(), initLife-1);
	}
	
	/**
	 * This test method tests special effect of Splash.
	 */
	@Test
	public void testSplashSpecialEffect()
	{
		TowerFactory factory = new TowerFactory();
		Critter[] critters = new Critter[3];
		WaveFactory waveFactory = new WaveFactory();
		Location[] path = new PathService().pathFinder(grid.getContent());
		Wave wave = waveFactory.getCreateWave("NormalCritter", new Location(9,7), path,1);
		
		critters[0] = wave.getAliens().get(0);
		critters[1] = wave.getAliens().get(1);
		critters[2] = wave.getAliens().get(2);
		
		int initLife = critters[0].getLife();
		Tower tower = factory.getTower(TowerCharactersticsConstants.HELENE_TOWER_TYPE,
				TowerLevelEnum.ONE);
		
		tower.setTowerPosition(new Location(5, 6));		
		critters[0].setCurrentPosition(3);
		critters[1].setCurrentPosition(4);
		critters[2].setCurrentPosition(9);
		
		CritterSplashService splashEffect = new CritterSplashService();
		splashEffect.splashCritter(tower, critters[1], wave, 1, path);
		
		Assert.assertEquals(critters[0].getLife(), initLife-1);
		Assert.assertEquals(critters[1].getLife(), initLife-1);
		Assert.assertEquals(critters[2].getLife(), initLife);
	}
	
	/**
	 * This test method is to test special effect of freeze.
	 */
	@Test
	public void testFreezeSpecialEffect()
	{
		WaveFactory waveFactory = new WaveFactory();
		Location[] path = new PathService().pathFinder(grid.getContent());
		Wave wave = waveFactory.getCreateWave("NormalCritter", new Location(9,7), path,1);		
		CritterNormalMove critterMove = ((CritterNormalMove) wave.getAliens().get(0).getMovingBehaviour());
		critterMove.setFreezeTime(100);
		critterMove.move();		
		Assert.assertEquals(critterMove.getFreezeTime(), 99);
	}
}
