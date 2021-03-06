package td.game.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import td.game.model.LifeManager;
import td.game.model.MapGrid;

/**
 * <b> This class is used to save and load our game, and keep game state for loading the game and continuing the game </b>
 * <b> we save the game status as a serialized object </b>
 * @author Team3
 */
public class GameStateController implements Serializable 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private MapGrid map;
	private int wave;
	private long balance;
	private long currentBalance;
	private int life;
	private String mapLocation;
	
	/**
	 * <b>game state manager's constructor </b>
	 * @param map game grid map
	 * @param wave the wave that was finished
	 * @param mapLocation the location that our map was saved in.
	 */
	public GameStateController(MapGrid map, int wave, String mapLocation) 
	{
		this.map = map;
		this.wave = wave;
		this.mapLocation = mapLocation;
		this.balance = MoneyController.getInstance().getBalanceMoney();
		this.currentBalance = MoneyController.getInstance().getCurrentBalanceMoney();
		this.life = LifeManager.getInstance().getLife();
	}
	
	/**
	 * 
	 * @param fileName file name and information about the file that we want to save our game on it
	 * @param game game manager object that contains all status of our games
	 */
	public static void save(String fileName, GameStateController game) 
	{

		FileOutputStream fout;
		try 
		{
			fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(game);
		} 
		catch (Exception e) 
		{
			logger.writer("GameStateController", e);
		}
	}
	
	/**
	 * 
	 * @param fileName that contains all information about our game status and our game status was saved on it
	 * @return game status and information about the game for continuing the game
	 */
	public static GameStateController load(String fileName) 
	{
		GameStateController game = null;
		try 
		{
			FileInputStream streamIn = new FileInputStream(fileName);
			ObjectInputStream objectinputstream = new ObjectInputStream(
					streamIn);
			Object obj = objectinputstream.readObject();
			if (obj instanceof GameStateController) 
			{
				game = (GameStateController) obj;
			}
			objectinputstream.close();
		} 
		catch (Exception e) 
		{
			logger.writer("GameStateController.java", e);
		}
		return game;
	}
	
	/**
	 * 
	 * @return the game map for continuing the game
	 */
	public MapGrid getMap() 
	{
		return map;
	}
	
	/**
	 * 
	 * @return game wave number that was finished
	 */
	public int getWaveNum() 
	{
		return wave;
	}
	
	/**
	 * 
	 * @return the amount of money that user had for buying the towers 
	 */
	public long getBalance() 
	{
		return balance;
	}
	
	/**
	 * 
	 * @return the amount of money that user have charged for buying towers
	 */
	public long getCurrentBalance() 
	{
		return currentBalance;
	}
	/**
	 * 
	 * @return total life that user has saved until now
	 */
	public int getLife() 
	{
		return life;
	}
	
	/**
	 * 
	 * @return map file location
	 */
	public String getMapLocation() 
	{
		return mapLocation;
	}
}
