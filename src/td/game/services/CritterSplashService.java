package td.game.services;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import td.game.constants.MapConstants;
import td.game.model.Critter;
import td.game.model.Location;
import td.game.model.Tower;
import td.game.model.Wave;

/**
 * This class is used to provide splash effect on shooting a critter
 * @author Team3
 *
 */
public class CritterSplashService 
{
	private HashMap<Critter, Integer> burningCritters = new HashMap<Critter, Integer>();
	private CellService mapCellService = new CellService();

	/**
	 * This method is used for critter splash
	 * @param defender tower
	 * @param target critter
	 * @param wave wave
	 * @param power power
	 * @param path location
	 */
	public void splashCritter(Tower defender, Critter target, Wave wave, int power, Location[] path) 
	{
		int gridX = path[target.getCurrentPosition()].getX();
		int gridY = path[target.getCurrentPosition()].getY();
		Location pixel = mapCellService.convertMapCellToPixel(new Location(gridX, gridY));
		
		int len = (4) * MapConstants.UNIT_SIZE;
		int cornerX = pixel.getX() - MapConstants.UNIT_SIZE - (MapConstants.UNIT_SIZE / 2);
		int cornerY = pixel.getY() - MapConstants.UNIT_SIZE - (MapConstants.UNIT_SIZE / 2);
		Rectangle areaOfEffect = new Rectangle(cornerX, cornerY, len, len);

		ArrayList<Critter> critters = new ArrayList<Critter>();
		critters.addAll(wave.getAliens());
		for (Critter c : wave.getAliens()) 
		{
			int gX = path[c.getCurrentPosition()].getX();
			int gY = path[c.getCurrentPosition()].getY();
			Location p = mapCellService.convertMapCellToPixel(new Location(gX, gY));
			int l = (1) * MapConstants.UNIT_SIZE;
			int cX = (p.getX());
			int cY = p.getY();
			Rectangle critterRect = new Rectangle(cX, cY, l, l);
			
			if (critterRect.intersects(areaOfEffect)) 
			{
				c.setResistance(c.getResistance()-1);
				
				if(c.getResistance()==0)
				{
					c.setLife(c.getLife() - power);
					c.setResistance(c.getWaveNumber());
				}			
			}
		}
	}	
}
