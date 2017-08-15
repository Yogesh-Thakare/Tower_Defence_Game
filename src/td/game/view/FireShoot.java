package td.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import td.game.services.Log4jLogger;
import td.game.model.Location;

/**
 * This class is used for shooting algorithm technique
 * @author Team3
 */
@SuppressWarnings("serial")
public class FireShoot extends JComponent 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * This method is for public constructor of the class
	 */
	public FireShoot()
	{
	}
	
	/**
	 * This method is to draw the bullet
	 * @param g Graphics object used for drawing
	 * @param source shooter
	 * @param target target point for shooting 
	 */
	public void drawBullet(Graphics g, Location source, Location target)
	{
		super.paintComponent(g);
		
		try
		{
			Image imageMo=null;
			g.setColor(Color.YELLOW);
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());	
			imageMo = ImageIO.read(new File("images/fire_effect.png"));
			g.drawImage(imageMo, target.getX(), target.getY(), 30, 30, null);
		}
		catch(Exception e)
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
}
