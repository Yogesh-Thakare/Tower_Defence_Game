package td.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import javax.swing.ImageIcon;
import td.game.constants.MapConstants;
import td.game.model.MapContents;
import td.game.model.Tower;
import td.game.services.TowerFactory;

/**
 * @author Team3
 * <b>This Cell class updates a cell with its image , meaning that if a cell is path , it will put</b>
 * <b> path image , if a cell is place a specific tower it will put that tower ...</b>
 */
@SuppressWarnings("serial")
public class CellView extends Rectangle 
{
	private ClassLoader classLoader = getClass().getClassLoader();
	private File file;
	private Image sceneryImg, pathImg, enteranceImg, exitImg;
	private Image modernTowerImg, ancientTowerImg, kingTowerImg;
	
	/**
	 * This method constructor to set up the images for the cell , depending on the number it has.
	 */
	public CellView()
	{
		setupImages();
	}
	
    /**
     * This method is called by the constructor to prepare the images
     */
	private void setupImages()
	{
		file = new File(classLoader.getResource(MapConstants.SCENERY_IMG).getFile());
		sceneryImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.PATH_IMG).getFile());
		pathImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.ENTRANCE_IMG).getFile());
		enteranceImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.EXIT_IMG).getFile());
		exitImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.HELENE_TOWER_IMG).getFile());
		modernTowerImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.CITIDELLE_TOWER_IMG).getFile());
		ancientTowerImg = new ImageIcon(file.getPath()).getImage();

		file = new File(classLoader.getResource(MapConstants.CHAMBY_TOWER_IMG).getFile());
		kingTowerImg = new ImageIcon(file.getPath()).getImage();
	}
	
	/**
	 * @param cellType  cell type is the type of cell scenery,path,entrance,exit,tower
	 * @param towers array of towers which are 3 types for now Modern,ancient,king
	 * @param x x location to draw
	 * @param y y location to draw
	 * @return Image depending on its type 
	 */
	private Image setup(MapContents cellType, Tower[][] towers, int x, int y) 
	{
		switch (cellType) 
		{
			case SCENERY:
				return sceneryImg;
			case PATH:
				return pathImg;
			case ENTRANCE:
				return enteranceImg;
			case EXIT:
				return exitImg;
			case TOWER:
			
			switch(towers[x][y].displayTowerImage())
			{
				case MapConstants.HELENE_TOWER_IMG:
					return modernTowerImg;
				case MapConstants.CITIDELLE_TOWER_IMG:
					return ancientTowerImg;
				case MapConstants.CHAMBY_TOWER_IMG:
					return kingTowerImg;
				default:
					return modernTowerImg;
			}
			default:
			return sceneryImg;
		}
	}	
	
	/** 
	 * @param g graphics to draw images
	 * @param cellType cell type can be path ,exit ,scenery ,tower,entry
	 * @param towers array of towers
	 * @param x  x coordinate of the cell
	 * @param y  y coordinate of the cell
	 * @param gridX grid x coordinate 
	 * @param gridY grid y coordinate
	 * @param isTower indicates if is a tower
	 */
	public void draw(Graphics g, MapContents cellType, Tower[][] towers, int x, int y, int gridX, int gridY, boolean isTower) 
	{
		Image image = setup(cellType, towers, gridX, gridY);
		
		//draw scenery
		file = new File(classLoader.getResource(MapConstants.SCENERY_IMG).getFile());
		Image sImage = new ImageIcon(file.getPath()).getImage();
		g.drawImage(sImage, x, y, MapConstants.UNIT_SIZE,
		MapConstants.UNIT_SIZE, null);
		
		if(isTower)
		{
			int len = ((new TowerFactory().getRange(towers[gridX][gridY]))*2 + 1)*MapConstants.UNIT_SIZE;
			int cornerX = (x) - (new TowerFactory().getRange(towers[gridX][gridY])*MapConstants.UNIT_SIZE);
			int cornerY = (y) - (new TowerFactory().getRange(towers[gridX][gridY])*MapConstants.UNIT_SIZE);
			g.setColor(Color.RED);
			g.drawRect(cornerX, cornerY, len, len);
			g.setColor(Color.BLACK);
		}
		g.drawImage(image, x, y, MapConstants.UNIT_SIZE,
		MapConstants.UNIT_SIZE, null);
	}
}
