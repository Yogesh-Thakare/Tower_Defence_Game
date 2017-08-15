package td.game.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import td.game.model.Tower;

/**
 * <b>This class is used to implement log manager for the game , and this service has
 * the service that are used for log manager</b>
 * @author Team3
 */
public class LogController 
{
	private ArrayList<GameLog> gameLog;
	private static LogController instance = null;

	/**
	 * This constructor is used for initialization
	 */
	private LogController() 
	{
		gameLog = new ArrayList<GameLog>();
	}

	/**
	 * This method is used for Log Controller
	 * @return instance LogController instance
	 */
	public static LogController getInstance() 
	{
		if (instance == null) 
		{
			instance = new LogController();
		}
		return instance;
	}
	
	/**
	 * <b>This method can add a log and save a description for our tower during the game</b>
	 * @param wave it can show game level. by this number we know this log belong to witch wave
	 * @param tower the tower that we want to crate a log for it
	 * @param description the log message for a tower
	 */
	public void addTowerLog(int wave, Tower tower, String description) 
	{
		instance.gameLog.add(new GameLog(wave, LogType.TOWER, new TowerLog(tower, description)));
	}
	
	/**
	 * This method is used to add wave log
	 * @param wave it can show game level. by this number we know this log belong to witch wave
	 * @param description description the log message for a wave or a game level
	 */
	public void addWaveLog(int wave, String description) 
	{
		instance.gameLog.add(new GameLog(wave, LogType.WAVE, new WaveLog(description)));
	}
	
	/**
	 * This method is used to get waves
	 * @return the all logs that have relation to the wave
	 */
	public ArrayList<String> getWaves() 
	{
		ArrayList<String> waves = new ArrayList<>();

		int currentWave=0;
		for (GameLog gL : gameLog) 
		{
			if (gL.wave != currentWave) 
			{
				currentWave = gL.wave;
				waves.add("Wave: " + currentWave);
			}
		}
		return waves;
	}
	
	/**
	 * This method is used to get towers
	 * @return the all logs that have relation to the all tower that are used in a wave
	 */
	public ArrayList<String> getTowers() 
	{
		ArrayList<String> towers = new ArrayList<>();
		HashMap<String, String> hM = new HashMap<String, String>();

		for (GameLog gL : gameLog) 
		{
			if (gL.type == LogType.TOWER) 
			{
				hM.put(((TowerLog) gL.logElement).id, "Tower: "
						+ ((TowerLog) gL.logElement).id);
			}
		}

		Iterator it = hM.entrySet().iterator();
		
		while (it.hasNext()) 
		{
			Map.Entry pairs = (Map.Entry) it.next();
			towers.add(pairs.getValue().toString());
		}
		return towers;
	}
	
	/**
	 * This method is used get global log
	 * @return all logs for the whole game 
	 */
	public String getGlobalLog() 
	{
		String str = "";
		for (GameLog logEntry : gameLog) 
		{
			switch (logEntry.type) 
			{
			case TOWER:
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
				break;
			case WAVE:
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (WaveLog) logEntry.logElement + "\n";
				break;
			}
		}
		return str;
	}
	
	/**
	 * 
	 * @return tower logs
	 */
	public String getCollectiveTowerLog() 
	{
		String str = "";
		for (GameLog logEntry : gameLog) 
		{
			if (logEntry.type == LogType.TOWER) 
			{
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
			}
		}
		return str;
	}
	
	/**
	 * 
	 * @param towerId the tower identification for getting individual logs for specific tower
	 * @return logs that have relation to specific tower
	 */
	public String getIndividualTowerLog(String towerId) 
	{
		String str = "";
		for (GameLog logEntry : gameLog) 
		{
			if (logEntry.type == LogType.TOWER	&& ((TowerLog) logEntry.logElement).id.equalsIgnoreCase(towerId)) 
			{
				str += logEntry.time + " - wave:" + logEntry.wave + " - "
						+ (TowerLog) logEntry.logElement + "\n";
			}
		}
		return str;
	}
	
	/**
	 * 
	 * @param waveNum wave number shows the the game level
	 * @return all logs that have relation to specific wave
	 */
	public String getIndividualWaveLog(int waveNum) 
	{
		String str = "";
		for (GameLog logEntry : gameLog) 
		{
			if (logEntry.wave == waveNum) 
			{
				switch (logEntry.type) 
				{
				case TOWER:
					str += logEntry.time + " - wave:" + logEntry.wave + " - "
							+ (TowerLog) logEntry.logElement + "\n";
					break;
				case WAVE:
					str += logEntry.time + " - wave:" + logEntry.wave + " - "
							+ (WaveLog) logEntry.logElement + "\n";
					break;
				}
			}
		}
		return str;
	}
	
	/**
	 * <b>This inner class that uses as a object  for using in log manager</b>
	 * @author Team3
	 */
	class GameLog 
	{
		String time;
		int wave;
		LogType type;
		Object logElement;

		/**
		 * This constructor is used for initialization
		 * @param wave integer
		 * @param type LogType
		 * @param logElement object logElement
		 */
		GameLog(int wave, LogType type, Object logElement) 
		{
			this.time = new Date().toString();
			this.wave = wave;
			this.type = type;
			this.logElement = logElement;
		}
	}
	
	/**
	 * <b>log type is defined as a enum for using in log manager. log type contains two type tower and wave</b>
	 * @author Team3
	 */
	enum LogType 
	{
		TOWER, WAVE;
	}
	
	/**
	 * <b>This class is a inner class tower log object definition is defined to use in log manager</b>
	 * @author Team3
	 */
	class TowerLog 
	{
		String id;
		String towerType;
		String description;

		/**
		 * This constructor is used for initialization
		 * @param tower
		 * @param description
		 */
		TowerLog(Tower tower, String description) 
		{
			this.id = tower.Id;
			TowerFactory factory = new TowerFactory();
			String name = factory.getDecoratedName(tower.towerInformation());
			this.towerType = name;
			this.description = description;
		}
		
		/**
		 * This method is used to return string
		 * @return tower log complete informations
		 */
		public String toString() 
		{
			return "id: " + id + " type: " + towerType + " desc: "	+ description;
		}
	}
	
	/**
	 * <b> This wave log class definition is used for log manager</b>
	 * @author Team3
	 */
	class WaveLog 
	{
		String description;

		/**
		 * This constructor is used to initialize
		 * @param description
		 */
		WaveLog(String description) 
		{
			this.description = description;
		}
		
		/**
		 * This method is used to return string
		 * @return wave log information 
		 */
		public String toString() 
		{
			return description;
		}
	}
}
