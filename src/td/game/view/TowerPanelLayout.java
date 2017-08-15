package td.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 * This class creates panel for Tower, 
 * which includes layout for TowerSelection as well as TowerAttributes 
 * @author Team3
 */
public class TowerPanelLayout extends JPanel
{
	private static final long serialVersionUID = 1L;
	private TowerSelectionPanel towerSelectionPanel;
	private TowerInfoPanel towerInfoPanel;
	
	/**
	 * Create the panel for showing tower characteristics.
	 */
	public TowerPanelLayout() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setBackground(Color.GRAY);		
		towerInfoPanel = new TowerInfoPanel();
		towerSelectionPanel = new TowerSelectionPanel(towerInfoPanel);
		GridBagConstraints gbcTowerSelectionPanel = new GridBagConstraints();
		gbcTowerSelectionPanel.anchor = GridBagConstraints.WEST;
		gbcTowerSelectionPanel.insets = new Insets(0, 0, 0, 5);
		gbcTowerSelectionPanel.fill = GridBagConstraints.VERTICAL;
		gbcTowerSelectionPanel.gridx = 0;
		gbcTowerSelectionPanel.gridy = 0;
		add(towerSelectionPanel, gbcTowerSelectionPanel);		
		GridBagConstraints gbcTowerInfoPanel = new GridBagConstraints();
		gbcTowerInfoPanel.fill = GridBagConstraints.BOTH;
		gbcTowerInfoPanel.gridx = 1;
		gbcTowerInfoPanel.gridy = 0;
		add(towerInfoPanel, gbcTowerInfoPanel);
	}
}
