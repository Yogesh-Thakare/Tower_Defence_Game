package td.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import td.game.model.Location;
import td.game.services.Log4jLogger;

/**
 * This class is used for shooting algorithm
 * @author Team3 
 *
 */
public class SplashShoot extends JComponent 
{
	private static final Log4jLogger logger = new Log4jLogger();
	
	/**
	 * public constructor of the class
	 */
	public SplashShoot()
	{
	}
	
	/**
	 * This method is to draw the bullet for splash effect
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
			g.setColor(Color.BLUE);
			g.drawLine(source.getX(), source.getY(), target.getX(), target.getY());
			imageMo = ImageIO.read(new File("images/splash_effect.png"));
			//g.drawImage(imageMo, target.getX(), target.getY(), 30, 30, null);
			g.drawImage(imageMo, target.getX()-60, target.getY()-4, 100, 30, null);
		}
		catch(Exception e)
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
}
