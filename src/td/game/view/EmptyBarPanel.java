package td.game.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * @author Team3
 * This is an empty bar panel class which creates the panel for empty bar
 */
public class EmptyBarPanel extends JPanel 
{
	private static final long serialVersionUID = -343208754471468038L;

	/**
	* Create the panel, for the empty bar
	*/
	public EmptyBarPanel() 
	{
		Dimension dim = getPreferredSize();
		dim.width = 10;
		setPreferredSize(dim);
		setBackground(Color.BLACK);
	}

}

	
	
	

