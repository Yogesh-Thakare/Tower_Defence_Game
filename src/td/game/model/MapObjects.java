package td.game.model;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import td.game.constants.MapConstants;

/**
 * This class handles all map objects
 * @author Team3
 */
@SuppressWarnings("serial")
public class MapObjects implements Serializable 
{
	private int width;
	private int height;
	private int unitSize = MapConstants.UNIT_SIZE;
	private MapContents[][] content;
	private String creationTime;
	private ArrayList<String> modificationTime = new ArrayList<String>();
	private ArrayList<PlayLog> playLog = new ArrayList<PlayLog>();
	private ArrayList<MapLog> mapLog = new ArrayList<MapLog>();

	/**
	 * This is a dummy constructor
	 */
	public MapObjects() 
	{
		this.width = 1;
		this.height = 1;
		this.content = new MapContents[width][height];
		initializeCellContents(MapContents.SCENERY);
	}

	/**
	 * <b>This method is used to create a Grid using width and height and initializes the content as Scenery</b>
	 */
	public MapObjects(int width, int height) 
	{
		this.width = width;
		this.height = height;
		this.content = new MapContents[width][height];
		initializeCellContents(MapContents.SCENERY);
	}

	/**
	 * <b>This method is used to create a Grid with width, height, and content type</b> 
	 * @param width  	width of grid
	 * @param height 	height of grid
	 * @param cellType 	type of grid
	 */
	public MapObjects(int width, int height, MapContents cellType) 
	{
		this.width = width;
		this.height = height;
		this.content = new MapContents[width][height];
		initializeCellContents(cellType);
	}

	/**
	 * <b>This method is used to construct a Grid by another grid.</b>
	 * @param grid    grid object
	 */
	public MapObjects(MapObjects grid) 
	{
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
		this.creationTime = grid.getCreationTime();
		this.modificationTime.addAll(grid.getModificationTime());
		this.playLog.addAll(grid.getPlayLog());
		this.mapLog.addAll(grid.getMapLog());
	}

	/**
	 * <b>This method is used to initialize grid content to cellType</b>
	 */
	private void initializeCellContents(MapContents cellType) 
	{
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				setCell(x, y, cellType);
			}
		}
	}

	/**
	 * <b>This method can not be used directly. It allows drawing the grid through VisualGrid</b>
	 * @param g   draw graphics after iteration
	 */
	public void render(Graphics g) 
	{
	}

	/**
	 * @return height of map
	 */
	public int getHeight() 
	{
		return height;
	}

	/**
	 * This method is used to set the height
	 * @param value grid height
	 */
	public void setHeight(int value) 
	{
		height = value;
	}

	/**
	 * This method is used to get the width
	 * @return width of map
	 */
	public int getWidth() 
	{
		return width;
	}

	/**
	 * This method is used to set the width
	 * @param value grid width
	 */
	public void setWidth(int value) 
	{
		width = value;
	}

	/**
	 * This method is used to return contents of the grid
	 * @return type return content of the grid cell
	 */
	public MapContents[][] getContent() 
	{
		return content;
	}

	/**
	 * This method is used to set the content
	 * @param value grid content
	 */
	public void setContent(MapContents[][] value) 
	{
		content = value;
	}

	/**
	 * This method is used to get unit size
	 * @return size of the unit
	 */
	public int getUnitSize() 
	{
		return unitSize;
	}

	/**
	 * <b>This method is used to set the size of the grid and resets its content to scenery.</b>
	 * @param width  width of the content to set
	 * @param height height of the content to set
	 */
	public void setSize(int width, int height) 
	{
		this.width = width;
		this.height = height;
		content = new MapContents[width][height];
		initializeCellContents(MapContents.SCENERY);
	}

	/**
	 * <b>This method is used to set the content of a cell to type</b> 
	 * @param x   	location of cell
	 * @param y   	location of cell
	 * @param type 	type of cell
	 */
	public void setCell(int x, int y, MapContents type) 
	{
		if (x >= 0 && x < width && y >= 0 && y < height) 
		{
			content[x][y] = type;
		}
	}

	/**
	 * This method is used to get cell contents
	 * @param x   coordinate
	 * @param y   coordinate
	 * @return content type
	 */
	public MapContents getCell(int x, int y) 
	{
		if (x >= 0 && x < width && y >= 0 && y < height) 
		{
			return content[x][y];
		}
		return null;
	}

	/**
	 * This method is used to get entrance location
	 * @return position of the grid entry point  
	 */
	public Location getEntranceLocation() 
	{
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				if (content[x][y] == MapContents.ENTRANCE) 
				{
					return new Location(x, y);
				}
			}
		}
		return null;
	}

	/**
	 * This method is used to get exit location
	 * @return position of the grid exit point  
	 */
	public Location getExitLocation() 
	{
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y < height; y++) 
			{
				if (content[x][y] == MapContents.EXIT) 
				{
					return new Location(x, y);
				}
			}
		}
		return null;
	}
	
	/**
	 * This method is used to return the creation time
	 * @return grid creation time 
	 */
	public String getCreationTime() 
	{
		return creationTime;
	}

	/**
	 * This method is used to set the creation time
	 * @param creationTime grid creation time 
	 */
	public void setCreationTime(String creationTime) 
	{
		this.creationTime = creationTime;
	}

	/**
	 * This method is used to return the modified time
	 * @return grid modification time
	 */
	public ArrayList<String> getModificationTime() 
	{
		return modificationTime;
	}

	/**
	 * This method is used to set the modified time
	 * @param modificationTime grid modification time
	 */
	public void setModificationTime(ArrayList<String> modificationTime) 
	{
		this.modificationTime = modificationTime;
	}

	/**
	 * This method is used to add the modified time
	 * @param modificationTime modification time
	 */
	public void addModificationTime(String modificationTime) 
	{
		this.modificationTime.add(modificationTime);
	}

	/**
	 * This method is used to get the play log of grid.
	 * @return play log of the grid
	 */
	public ArrayList<PlayLog> getPlayLog() 
	{
		return playLog;
	}

	/**
	 * This method is used to set the play log of grid.
	 * @param playLog play log
	 */
	public void setPlayLog(ArrayList<PlayLog> playLog) 
	{
		this.playLog = playLog;
	}

	/**
	 * This method is used to add the play log of grid.
	 * @param time time of play
	 * @param score score of play
	 */
	public void addPlayLog(String time, long score) 
	{
		if (playLog == null) 
		{
			playLog = new ArrayList<PlayLog>();
		} 
		this.playLog.add(new PlayLog(time, score));
	}

	/**
	 * This method is used to returns the highest scores to display in log viewer.
	 * @param size number of highest scores to return
	 * @return the highest scores in string format
	 */
	public String getHighestScores(int size) 
	{
		String str="";
		if (size > playLog.size()) 
		{
			size = playLog.size();
		}
		ArrayList<PlayLog> scores = new ArrayList<PlayLog>();
		ArrayList<PlayLog> highScores = new ArrayList<PlayLog>();
		scores.addAll(playLog);
		PlayLog currentHighest = new PlayLog();
		
		if(!scores.isEmpty())
		{
			str = "        Game Played TimeStamps & Results"+"\n";
			for (PlayLog scoresEntry : scores) 
			{
				str += scoresEntry + "\n";
			}	
		}
		
		for (int c = 0; c < size; c++) 
		{
			int indx = 0;
			for (int i = 0; i < scores.size(); i++) 
			{
				if (scores.get(i).score > currentHighest.score) 
				{
					currentHighest = scores.get(i);
					indx = i;
				}
			}
			highScores.add(currentHighest);
			currentHighest = new PlayLog();
			scores.set(indx, new PlayLog());
		}
		
		if(!highScores.isEmpty())
		{
			str +="\n";
			str += "        Top 5 Scores with TimeStamps"+"\n";
			for (PlayLog highScoresEntry : highScores) 
			{
				str += highScoresEntry + "\n";
			}	
		}
		
		str +="\n";
		str += "        Map Creation TimeStamp"+"\n";
		str += "Time: "+this.creationTime+"\n";
		
		if(!modificationTime.isEmpty())
		{
			str +="\n";
			str += "        Map Modification TimeStamps"+"\n";
			for (String modtimeStamps : modificationTime) 
			{
				str += "Time: "+modtimeStamps + "\n";
			}	
		}
		
		return str;		
	}
	
	/**
	 * This method is used to get map log
	 * @return map log of the grid
	 */
	public ArrayList<MapLog> getMapLog() 
	{
		return mapLog;
	}
	
	/**
	 * This method is used to set map log
	 * @param mapLog map log
	 */
	public void setMapLog(ArrayList<MapLog> mapLog) 
	{
		this.mapLog = mapLog;
	}

	/**
	 * This method is used to add map log
	 * @param time time of map
	 */
	public void addMapLog(String time) 
	{
		if (mapLog == null) 
		{
			mapLog = new ArrayList<MapLog>();
		}
		this.mapLog.add(new MapLog(time));

	}
	
	/**
	 * This method is used to return the modified time of map to display in log viewer.
	 * 
	 * @param size number of editable times to return
	 * @return the editable times of map in string format
	 */
	public String getMapTime(int size) 
	{
		if (size > mapLog.size()) 
		{
			size = mapLog.size();
		}
		ArrayList<MapLog> times = new ArrayList<MapLog>();
		ArrayList<MapLog> highTime = new ArrayList<MapLog>();
		times.addAll(mapLog);
		MapLog currentTime = new MapLog();
		for (int c = 0; c < size; c++) 
		{
			int indx = 0;
			highTime.add(currentTime);
			currentTime = new MapLog();
			times.set(indx, new MapLog());
		}
		String str = "";
		for (MapLog entry : highTime) 
		{
			str += entry + "\n";
		}
		
		return str;
		
	}

	/**
	 * This is an internal class for PlayLog object
	 * @author Team 3
	 */
	class PlayLog implements Serializable 
	{
		String time;
		long score;

		/**
		 * Constructor used for initialization
		 */
		PlayLog() 
		{
			time = "";
			score = -1;
		}

		/**
		 * This is the constructor with two parameters 
		 * @param time time of play
		 * @param score score of play
		 */
		PlayLog(String time, long score) 
		{
			this.time = time;
			this.score = score;
		}

		/**
		 * This method is used to return the PlayLog as "Time: time - Score: score"
		 */
		public String toString() 
		{
			return "Time: " + time + " - Score: " + score;
		}
	}
	
	/**
	 * This is an internal class for MapLog object
	 * @author Team 3
	 *
	 */
	class MapLog implements Serializable 
	{
		String timeMap;

		/**
		 * Constructor used for initialization
		 */
		MapLog() 
		{
			timeMap = "";
		}

		/**
		 * This is the constructor with two parameters
		 * @param time time of play
		 * @param score score of play
		 */
		MapLog(String time) 
		{
			this.timeMap = time;
		}

		/**
		 * This is the method for getting time of editable map
		 * returns the PlayLog as "Time: time - Score: score"
		 */
		public String toString() 
		{
			return "Time: " + timeMap;
		}
	}
}