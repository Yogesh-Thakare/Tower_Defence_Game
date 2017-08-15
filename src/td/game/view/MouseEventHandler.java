package td.game.view;

import java.awt.event.*;
import java.awt.*;

/**
 * This class is for handling mouse events
 * @author Team3
 *<b> This class is the mouse listener click and motion</b>
 */
public class MouseEventHandler implements MouseListener, MouseMotionListener
{
	TwoLayerPanelView mapPanel;
	
	/**
	 * Constructor for setting map panel
	 * @param mapPanel assign the mapPanel to this mapPanel
	 */
	public MouseEventHandler(TwoLayerPanelView mapPanel)
	{
		this.mapPanel = mapPanel;
	}
	
	/**
	 * Mouse dragged event 
	 */
	public void mouseDragged(MouseEvent e) 
	{	
	}

	/**
	 * Mouse moved event
	 */
	public void mouseMoved(MouseEvent e) 
	{	
	}

	/**
	 * according to the click we set that tower to the click location
	 * @param event the xy coordinate of where the click occurred 
	 */
	public void mouseClicked(MouseEvent event) 
	{
		int mouseX = event.getX();
		int mouseY = event.getY();
		
		if((mouseX>=mapPanel.getOtherItemsPanel().getMapTopLeft().getX() && mouseX<=mapPanel.getOtherItemsPanel().getMapButtomRight().getX()) &&
				(mouseY>=mapPanel.getOtherItemsPanel().getMapTopLeft().getY() && mouseY<=mapPanel.getOtherItemsPanel().getMapButtomRight().getY()))
		{
			mapPanel.getOtherItemsPanel().setCellLocation(mouseX, mouseY);
			mapPanel.getOtherItemsPanel().towerOperation();
		}
	}

	/**
	 * mousePressed event
	 */
	public void mousePressed(MouseEvent e) 
	{	
		// TODO Auto-generated method stub
	}

	/**
	 * mouseReleased event
	 */
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub		
	}

	/**
	 * mouseEntered event
	 */
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub		
	}

	/**
	 * mouseExited event
	 */
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub		
	}
}