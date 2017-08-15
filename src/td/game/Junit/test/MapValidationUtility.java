package td.game.Junit.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import td.game.model.MapContents;

/**
 * This is a test class Map validation utility
 * @author Team 3
 */
public class MapValidationUtility 
{
	
	/**
	 * Read matrix map file
	 * @param file
	 * @param matrixHeight
	 * @param matrixWidth
	 * @return
	 */
	public int[][] matrixReadre(String file, int matrixHeight, int matrixWidth) 
	{
		int[][] matrix = new int[matrixHeight][matrixWidth];
		try (InputStream input = getClass().getResourceAsStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));) 
		{
			String line;
			int index = 0;
			String[] strs;
			
			while ((line = br.readLine()) != null) 
			{
				strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) 
				{
					matrix[index][i] = Integer.parseInt(strs[i]);
				}
				index++;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();		
		}		
		return matrix;
	}

	/**
	 * This test method is for checking map contents
	 * @param file
	 * @param matrixHeight
	 * @param matrixWidth
	 * @return
	 */
	public MapContents [][] matrixCellType(String file,
			int matrixHeight, int matrixWidth) 
	{
		MapContents[][] matrix = new MapContents[matrixHeight][matrixWidth];
		try 
		(
				InputStream input = getClass().getResourceAsStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(input));
		) 
		{
			String line;
			int index = 0;
			String[] strs;
			while ((line = br.readLine()) != null) 
			{
				strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) 
				{
					int cell = Integer.parseInt(strs[i]);
					switch (cell) 
					{
					case 0:
						matrix[index][i] = MapContents.SCENERY;
						break;
					case 1:
						matrix[index][i] = MapContents.PATH;
						break;

					case 2:
						matrix[index][i] = MapContents.ENTRANCE;
						break;

					case 3:
						matrix[index][i] = MapContents.EXIT;
						break;
					}
				}
				index++;
			}
		} 		
		catch (Exception e) 
		{
			e.printStackTrace();		
		}		
		return matrix;
	}
}