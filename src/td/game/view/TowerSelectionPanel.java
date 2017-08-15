package td.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import td.game.constants.TowerCharactersticsConstants;
import td.game.constants.MapConstants;
import td.game.model.Tower;
import td.game.model.TowerLevelEnum;
import td.game.services.Log4jLogger;
import td.game.services.TowerFactory;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;

/**
 * This class is for Creating the panel in which we would be selecting the Tower
 * @author Team3
 */
@SuppressWarnings("serial")
public class TowerSelectionPanel extends JPanel
{
	private boolean addTowerFlag;
	private JToggleButton modernTowerBtn;
	private JToggleButton ancientTowerBtn;
	private JToggleButton kingTowerBtn;
	private JPanel panel;
	private TowerInfoPanel towerInfoPanel;
	private static final Log4jLogger logger = new Log4jLogger();

	/**
	 * This method is used to create the panel.
	 * @param towerInfoPanel towerInfoPanel
	 */
	public TowerSelectionPanel(TowerInfoPanel towerInfoPanel) 
	{
		this.towerInfoPanel = towerInfoPanel;
		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		panel.setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file;
		file = new File(classLoader.getResource(MapConstants.HELENE_TOWER_IMG).getFile());
		BufferedImage buttonIcon;
		try 
		{
			buttonIcon = ImageIO.read(file);
			modernTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			modernTowerBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
				}
			});
			modernTowerBtn.setBorder(BorderFactory.createEmptyBorder());
		} 
		catch (IOException e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	
		GridBagConstraints gbc_modernTowerBtn = new GridBagConstraints();
		gbc_modernTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_modernTowerBtn.insets = new Insets(0, 0, 5, 0);
		gbc_modernTowerBtn.gridx = 0;
		gbc_modernTowerBtn.gridy = 0;
		add(modernTowerBtn, gbc_modernTowerBtn);
	
		file = new File(classLoader.getResource(MapConstants.CITIDELLE_TOWER_IMG).getFile());
		try 
		{
			buttonIcon = ImageIO.read(file);
			ancientTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			ancientTowerBtn.setBorder(BorderFactory.createEmptyBorder());
		} 
		catch (IOException e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	
		GridBagConstraints gbc_ancientTowerBtn = new GridBagConstraints();
		gbc_ancientTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_ancientTowerBtn.insets = new Insets(0, 0, 5, 0);
		gbc_ancientTowerBtn.gridx = 0;
		gbc_ancientTowerBtn.gridy = 1;
		add(ancientTowerBtn, gbc_ancientTowerBtn);
		
		file = new File(classLoader.getResource(MapConstants.CHAMBY_TOWER_IMG).getFile());
		try 
		{
			buttonIcon = ImageIO.read(file);
			kingTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			kingTowerBtn.setBorder(BorderFactory.createEmptyBorder());
			kingTowerBtn.setBackground(Color.GRAY);
		} 
		catch (IOException e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	
		GridBagConstraints gbc_kingTowerBtn = new GridBagConstraints();
		gbc_kingTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_kingTowerBtn.gridx = 0;
		gbc_kingTowerBtn.gridy = 2;
		add(kingTowerBtn, gbc_kingTowerBtn);
		
		modernTowerBtn.setBackground(Color.GRAY);
		ancientTowerBtn.setBackground(Color.GRAY);
		kingTowerBtn.setBackground(Color.GRAY);
	
		modernTowerBtn.addActionListener(new ActionListener()
		{
		       public void actionPerformed(ActionEvent e) 
		       {
		    	   Tower(TowerCharactersticsConstants.HELENE_TOWER_TYPE);
		}});
		       
		ancientTowerBtn.addActionListener(new ActionListener()
		{
		       public void actionPerformed(ActionEvent e) 
		       {
		    	   Tower(TowerCharactersticsConstants.CITIDELLE_TOWER_TYPE);
		}});
		
		kingTowerBtn.addActionListener(new ActionListener()
		{
		       public void actionPerformed(ActionEvent e) 
		       {
		    	   Tower(TowerCharactersticsConstants.CHAMBY_TOWER_TYPE);
		       }
		});
	}

	/**
	 * This method is used to create a tower of a given type
	 * @param towerType
	 */
	private void Tower(String towerType) 
	{
		TowerFactory towerFactory = new TowerFactory();
		Tower tower = towerFactory.getTower(towerType, TowerLevelEnum.ONE);
		towerInfoPanel.removeAll();
		towerInfoPanel.displayTowerDetail(tower);
		towerInfoPanel.repaint();
		towerInfoPanel.setBackground(Color.GRAY);
		addTowerFlag = true;
		SelectedTower.setInstance(towerType, tower, addTowerFlag);
	}
}