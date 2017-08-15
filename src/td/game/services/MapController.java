package td.game.services;

import td.game.model.Location;
import td.game.model.MapGrid;
import td.game.model.MapObjects;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * This class is used as a service during save and load from or into the file</b>
 * @author Team3
 */
public class MapController 
{
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * This is the MapController constructor
	 */
	public MapController() 
	{
	}
	
	/**
	 * This method is used to save the grid into file
	 * @param grid grid information
	 * @param fileName file path and name
	 */
	public void saveMapGridAsFile(MapObjects grid, String fileName) 
	{
		setLog(grid);
		
		try 
		{
			PathValidityChecker startEndChecker = new PathValidityChecker();
			
			if(!startEndChecker.hasEndPoint(grid.getContent()))
				throw new Exception("There isn't any exit point in the map!");
				
			if(!startEndChecker.hasStartPoint(grid.getContent()))
				throw new Exception("There isn't any enterance point in the map!");

			MapUtility mapUtility = new MapUtility();
			Location start = mapUtility.getMapEnterance(grid.getContent());
			Location end = mapUtility.getMapExit(grid.getContent());
			
			PathEndPointsCheckerService connectivityService = new PathEndPointsCheckerService();
			if(!connectivityService.isTherePath(start, end, grid.getContent()))
				throw new Exception("There isn't any path between start and end points in the map!");
			
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(grid);
			oos.close();
			
			JOptionPane.showMessageDialog(null, "Map saved Successfully!");
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to load the map from the drive
	 * @param fileName address of the file
	 * @return 
	 * @return Grid
	 */
	public MapObjects loadSavedMapFromFile(String fileName) 
	{
		MapObjects grid = null;
		try 
		{
			FileInputStream streamIn = new FileInputStream(fileName);
			ObjectInputStream objectinputstream = new ObjectInputStream(
					streamIn);
			Object obj = objectinputstream.readObject();
			if(obj instanceof MapObjects)
			{
				grid = (MapObjects) obj;
			}
			objectinputstream.close();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return grid;
	}
	
	/**
	 * This method is used to save play log
	 * @param grid
	 * @param fileName
	 */
	public void savePlayLog(MapObjects grid, String fileName) 
	{
		try 
		{
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(grid);
			oos.close();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to save maplog
	 * @param grid
	 * @param fileName
	 */
	public void saveMapLog(MapObjects grid, String fileName) 
	{
		try 
		{
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(grid);
			oos.close();
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to set play log
	 * @param grid
	 */
	private void setLog(MapObjects grid) 
	{
		Date date = new Date();
		if(grid.getCreationTime() != null && grid.getCreationTime().length() > 0)
		{
			if(grid.getModificationTime()==null)
			{
				grid.setModificationTime(new ArrayList<String>());
			}
			grid.addModificationTime(date.toString());
		}
		else 
		{
			grid.setCreationTime(date.toString());
		}
		
	}
	
	/**
	 * This method is used to set map log
	 * @param grid
	 */
	private void setMapLog(MapObjects grid) 
	{
		Date date = new Date();
		if(grid.getCreationTime() != null && grid.getCreationTime().length() > 0)
		{
			if(grid.getModificationTime()==null)
			{
				grid.setModificationTime(new ArrayList<String>());
			}
			grid.addModificationTime(date.toString());
		}
		else 
		{
			grid.setCreationTime(date.toString());
		}		
	}
}
