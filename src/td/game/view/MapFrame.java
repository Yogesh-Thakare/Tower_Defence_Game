package td.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import td.game.constants.Constants;
import td.game.services.PathValidityChecker;
import td.game.view.MapFrame;

/**
 * This class loads frame for designing map
 * @author Team3
 */
@SuppressWarnings("serial")
public class MapFrame extends JFrame implements ActionListener , ChangeListener
{
	private MapEditor mapFrame;
	private int width;
	private int height;
	private GridItemsLayerPanelView mapView;
	
	JDialog mapSizeDialog;
	JTextField widthTextfield;
	JSlider widthSlider;
	JTextField heightTextfield;
	JSlider heightSlider;

	/**
	 * Inner class for exception
	 * @author Team3
	 *
	 */
	static class WidthCheck extends Exception 
	{		
	};
	
	/**
	 * Inner class for exception
	 * @author Team3
	 *
	 */
	static class HeightCheck extends Exception 
	{ 
	};
	
	/**
	 * Inner class for exception
	 * @author Team3
	 *
	 */
	static class CorrectCheck extends Exception 
	{ 		
	};
	
	/**
	 * Constructs Editor's main frame. 
	 */
	public MapFrame() 
	{
		setup();
		setVisible(true);
	}

	/**
	 * Method for setting up parameters
	 */
	private void setup() 
	{
		setTitle("Tower Defence - Map Editor");
		reloadMap();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(34,139,34));		
		setLayout(new BorderLayout());
		mapFrame = new MapEditor(8,8);
		mapFrame.setBackground(Color.blue);
	}

	/**
	 * this method is used when there is a need to change the size of a game frame 
	 */
	private void reloadMap() 
	{
		setSize(700, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}

	/** 
	 * Action to be performed for each selection
	 */
	public void actionPerformed(ActionEvent event) 
	{
		String menuItem = event.getActionCommand();
		switch (menuItem) 
		{
			case Constants.LAUNCH:
				setMapSize();
				break;
			case Constants.CANCEL:
				closeMapSizeDialog();
				break;
		}
	}
	
	/**
	 * This method is fired on click of save map in menu bar
	 */
	public void saveMap() 
	{
		mapFrame.saveMapAsFile();
		mapView.saveMapLog();
	}

	/**
	 * This method is called if user edits the map after loading
	 */
	public void editMap() 
	{
			add(mapFrame, BorderLayout.CENTER);
			refresh();
	}
	
	/**
	 * This is a refesh function which will be call in EditMap method
	 */
	private void refresh() 
	{
		setSize(700, 500);
		setExtendedState(JFrame.MAXIMIZED_BOTH);		
	}
	
	/**
	 * This method is used to load existing map into the game
	 */
	public void loadOpenMap() 
	{	
		mapFrame.openExistingMap();
		editMap();
	}
	
	/**
	 * To close the dialogue map when the map size is set.
	 */
	private void closeMapSizeDialog() 
	{
		mapSizeDialog.dispose();	
	}

	/**
	 * this method gets call when user needs to change the size of the map
	 */
	private void setMapSize() 
	{
		try
		{
			width = (new Integer(widthTextfield.getText())).intValue();
			height = (new Integer(heightTextfield.getText())).intValue();
			PathValidityChecker checker = new PathValidityChecker();
				if(!checker.isCorrectSize(height,width))
				throw new CorrectCheck();	
				if(!checker.isCorrectHeight(height))
				throw new HeightCheck();
				if(!checker.isCorrectWidth(width))
				throw new WidthCheck();
				startMapDesign();
				closeMapSizeDialog();
		}
		catch (HeightCheck e) 
		{
			JOptionPane.showMessageDialog(new JFrame(), "Error!! Please select correct size of height between 5 to 8", "Alert",
				       JOptionPane.ERROR_MESSAGE);
		}
		catch (WidthCheck e) 
		{
			JOptionPane.showMessageDialog(new JFrame(), "Error!! Please select correct size of Width between 5 to 20", "Alert",
				       JOptionPane.ERROR_MESSAGE);
		}
		catch (CorrectCheck e) 
		{
			JOptionPane.showMessageDialog(new JFrame(), "Error!! Please select correct size for Width and Height", "Alert",
				       JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method is used to design a new map
	 */
	private void startMapDesign() 
	{
		mapFrame.setGridSize(width, height);
		mapFrame.mapSizeSet(width, height);
		continueMapDesign();
	}

	/**
	 * This method is used to change already designed map
	 */
	private void continueMapDesign() 
	{
		add(mapFrame, BorderLayout.CENTER);
		reloadMap();	
	}

	/**
	 * This method is used to get the map size from user
	 */
	public void designMap() 
	{
		mapSizeDialog = new JDialog(this, true); // parent, isModal
		mapSizeDialog.setTitle(Constants.MAP_SIZE);
		mapSizeDialog.setSize(400, 250);
		mapSizeDialog.setLocationRelativeTo(this);
		JButton okButton = new JButton(Constants.LAUNCH);
		JButton cancelButton = new JButton(Constants.CANCEL);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);	
		widthSlider = new JSlider(JSlider.HORIZONTAL,0, 29, 5);
		widthSlider.setPaintTicks(true);
		widthSlider.setPaintLabels(true);
		widthSlider.setMajorTickSpacing(100);
		widthSlider.setMinorTickSpacing(10);
		widthSlider.addChangeListener(this);
		widthSlider.addChangeListener(this);
		widthSlider.setBorder(BorderFactory.createTitledBorder("Width (Select size between 5 to 30)"));		
		heightSlider = new JSlider(JSlider.HORIZONTAL,0, 15, 5);
		heightSlider.setPaintTicks(true);
		heightSlider.setPaintLabels(true);
		heightSlider.setMajorTickSpacing(100);
		heightSlider.setMinorTickSpacing(10);
		heightSlider.addChangeListener(this);
		heightSlider.addChangeListener(this);
		heightSlider.setBorder(BorderFactory.createTitledBorder("Height (Select size between 5 to 15)"));	      
		widthTextfield = new JTextField(10); 
		widthTextfield.setText("" + ((int)Math.round(widthSlider.getValue())));
		widthTextfield.addActionListener(this);	      
		heightTextfield = new JTextField(10); 
		heightTextfield.setText("" + ((int)Math.round(heightSlider.getValue())));
		heightTextfield.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setSize(100, 200);

		// This section is to layout the form
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createSequentialGroup().addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(widthSlider)
										.addComponent(heightSlider)
										.addComponent(cancelButton)))
				.addGroup(
						layout.createSequentialGroup().addGroup(
								layout.createParallelGroup(
										GroupLayout.Alignment.LEADING)
										.addComponent(widthTextfield)
										.addComponent(heightTextfield)
										.addComponent(okButton))));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(widthSlider)
								.addComponent(widthTextfield))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(heightSlider)
								.addComponent(heightTextfield))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(cancelButton)
								.addComponent(okButton)));
		panel.setLayout(layout);
		panel.setBackground(Color.cyan);
		// Form layout done!
		mapSizeDialog.add(panel);
		mapSizeDialog.setVisible(true);
	}
	
	/* 
	 * This methos is used to set the change event of slider
	 * (non-Javadoc)
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent arg0) 
	{
		widthTextfield.setText("" + ((int)Math.round(widthSlider.getValue() )));
		heightTextfield.setText("" + ((int)Math.round(heightSlider.getValue() )));
	}
}