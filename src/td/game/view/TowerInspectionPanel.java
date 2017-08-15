package td.game.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import td.game.constants.TowerCharactersticsConstants;
import td.game.model.Location;
import td.game.model.Tower;
import td.game.model.TowerLevelEnum;
import td.game.services.MoneyController;
import td.game.services.TowerFactory;
import td.game.services.TowerSellPriceCalculator;

/**
 * <b>This class is an Observable class</b>
 * @author Team3
 */
public class TowerInspectionPanel extends Observable implements ActionListener 
{
	private String towertype;
	private MoneyController bank;
	private Tower tower;
	@SuppressWarnings("unused")
	private long availFunds;
	private String performedAction;
	private JLabel speedCount;
	private JLabel rangeCount;
	private JLabel powerCount;
	private JLabel valueCount;
	private JLabel sellPriceLable;
	private JLabel sellPriceCount;
	private JLabel levelLabel;
	private JButton upgradeBtn;
	private JButton sellBtn;
	private JPanel panel;
	private JDialog dialog;
	private JComboBox<String> strategyCombo;
	private String shootingStrategy;

	/**
	 * Create the panel.
	 * @param tower the tower that this panel needs to show its information
	 */
	public TowerInspectionPanel(final Tower tower) 
	{
		this.bank = MoneyController.getInstance();
		availFunds = this.bank.getBalanceMoney() - this.bank.getCurrentBalanceMoney();

		performedAction = "";
		dialog = new JDialog();
		dialog.addWindowListener(new WindowListener() 
		{
			@Override
			public void windowOpened(WindowEvent e) 
			{
			}

			@Override
			public void windowIconified(WindowEvent e) 
			{
			}

			@Override
			public void windowDeiconified(WindowEvent e) 
			{
			}

			@Override
			public void windowDeactivated(WindowEvent e) 
			{
			}

			@Override
			public void windowClosing(WindowEvent e) 
			{
				closeInspector();
			}

			@Override
			public void windowClosed(WindowEvent e) 
			{
			}

			@Override
			public void windowActivated(WindowEvent e) 
			{
			}
		});

		panel = new JPanel();
		this.tower = tower;
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.setTitle("Tower Inspection");

		List<Tower> towerList = tower.getTowers();
		TowerFactory f = new TowerFactory();
		this.towertype = f.getDecoratedName(towerList);
		this.bank = MoneyController.getInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gridBagLayout);
		panel.setBackground(Color.WHITE);

		JLabel speedLable = new JLabel("Fire Speed");
		speedLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		panel.add(speedLable, gbc_speedLable);

		speedCount = new JLabel("");
		GridBagConstraints gbc_speedCount = new GridBagConstraints();
		gbc_speedCount.insets = new Insets(0, 0, 5, 0);
		gbc_speedCount.gridx = 2;
		gbc_speedCount.gridy = 1;
		panel.add(speedCount, gbc_speedCount);

		JLabel powerLable = new JLabel("Fire Power");
		powerLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 2;
		panel.add(powerLable, gbc_powerLable);

		powerCount = new JLabel("");
		GridBagConstraints gbc_powerCount = new GridBagConstraints();
		gbc_powerCount.insets = new Insets(0, 0, 5, 0);
		gbc_powerCount.gridx = 2;
		gbc_powerCount.gridy = 2;
		panel.add(powerCount, gbc_powerCount);

		JLabel rangeLable = new JLabel("Fire Range");
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rangeLable = new GridBagConstraints();
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 3;
		panel.add(rangeLable, gbc_rangeLable);

		rangeCount = new JLabel("");
		GridBagConstraints gbc_rangeCount = new GridBagConstraints();
		gbc_rangeCount.insets = new Insets(0, 0, 5, 0);
		gbc_rangeCount.gridx = 2;
		gbc_rangeCount.gridy = 3;
		panel.add(rangeCount, gbc_rangeCount);

		JLabel valueLable = new JLabel("Value       ");
		valueLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_valueLable = new GridBagConstraints();
		gbc_valueLable.insets = new Insets(0, 0, 5, 5);
		gbc_valueLable.gridx = 1;
		gbc_valueLable.gridy = 4;
		panel.add(valueLable, gbc_valueLable);

		valueCount = new JLabel("");
		GridBagConstraints gbc_valueCount = new GridBagConstraints();
		gbc_valueCount.insets = new Insets(0, 0, 5, 0);
		gbc_valueCount.gridx = 2;
		gbc_valueCount.gridy = 4;
		panel.add(valueCount, gbc_valueCount);
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 5;

		//set combo box
		strategyCombo = new JComboBox<>();
		DefaultComboBoxModel<String> strategyModel = new DefaultComboBoxModel<>();
		GridBagConstraints gbc_strategy = new GridBagConstraints();
		strategyModel.addElement(TowerCharactersticsConstants.NEARTOEND_STRATEGY);
		strategyModel.addElement(TowerCharactersticsConstants.NEARTOSTART_STRATEGY);
		strategyModel.addElement(TowerCharactersticsConstants.STRONGEST_STRATEGY);
		strategyModel.addElement(TowerCharactersticsConstants.WEAKEST_STRATEGY);
		strategyModel.addElement(TowerCharactersticsConstants.NEARTOTOWER_STRATEGY);
		strategyCombo.setModel(strategyModel);
		if(tower != null)
		{
			strategyCombo.setSelectedItem(tower.getShootingStrategy());
		}
		strategyCombo.addActionListener (new ActionListener () 
		{
			public void actionPerformed(ActionEvent e) 
			{
				shootingStrategy = (String)strategyCombo.getSelectedItem();
				if(tower != null)
				{
					tower.setShootingStrategy(shootingStrategy);
					sendUpdateSignal();
				}
			}
		});

		JLabel strategyLable = new JLabel("Strategy ");
		GridBagConstraints gbc_stratgyLable = new GridBagConstraints();
		gbc_stratgyLable.anchor = GridBagConstraints.WEST;
		gbc_stratgyLable.insets = new Insets(0, 0, 5, 5);
		gbc_stratgyLable.gridx = 1;
		gbc_stratgyLable.gridy = 5;
		panel.add(strategyLable, gbc_stratgyLable); 
		strategyCombo.setModel(strategyModel);
		gbc_strategy.insets = new Insets(0, 0, 5, 0);
		gbc_strategy.gridx = 2;
		gbc_strategy.gridy = 5;
		panel.add(strategyCombo, gbc_strategy);


		upgradeBtn = new JButton("Upgrade");
		upgradeBtn.setSize(30, 20);
		upgradeBtn.addActionListener(this);
		if (tower.getLevel().equals(TowerLevelEnum.THREE)) 
		{
			this.upgradeBtn.setEnabled(false);
		}

		JLabel label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 6;
		panel.add(label_1, gbc_label_1);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 7;

		levelLabel = new JLabel("");
		GridBagConstraints gbc_levelLabel = new GridBagConstraints();
		gbc_levelLabel.anchor = GridBagConstraints.WEST;
		gbc_levelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_levelLabel.gridx = 1;
		gbc_levelLabel.gridy = 7;
		panel.add(levelLabel, gbc_levelLabel);
		GridBagConstraints gbc_upgradeBtn = new GridBagConstraints();
		gbc_upgradeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_upgradeBtn.gridx = 2;
		gbc_upgradeBtn.gridy = 7;
		panel.add(upgradeBtn, gbc_upgradeBtn);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 8;
		panel.add(label, gbc_label);

		sellPriceLable = new JLabel("Sell Price ");
		sellPriceLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_sellPriceLable = new GridBagConstraints();
		gbc_sellPriceLable.insets = new Insets(0, 0, 0, 5);
		gbc_sellPriceLable.gridx = 1;
		gbc_sellPriceLable.gridy = 9;
		panel.add(sellPriceLable, gbc_sellPriceLable);

		sellPriceCount = new JLabel("");
		GridBagConstraints gbc_sellPriceCount = new GridBagConstraints();
		gbc_sellPriceCount.gridx = 2;
		gbc_sellPriceCount.gridy = 9;
		panel.add(sellPriceCount, gbc_sellPriceCount);
		List<Tower> towerDetails = tower.getTowers();
		TowerFactory factory = new TowerFactory();

		// set textboxes with current feature informations
		Map<String, Integer> details = factory.getFeaturesCount(towerDetails);
		Iterator<String> keySetIterator = details.keySet().iterator();
		while (keySetIterator.hasNext()) 
		{
			String key = keySetIterator.next();
			switch (key) 
			{
				case "FirePower":
					powerCount.setText((details.get(key)).toString());
					break;
				case "FireRange":
					rangeCount.setText((details.get(key)).toString());
					break;
				case "FireSpeed":
					speedCount.setText((details.get(key)).toString());
					break;
			}
		}
		long value = (tower.towerCost());
		valueCount.setText(Long.toString(value));
		levelLabel.setText("Level " + tower.getLevel());
		sellBtn = new JButton("Sell");
		sellBtn.setSize(30, 20);
		sellBtn.addActionListener(this);
		GridBagConstraints gbc_sellBtn = new GridBagConstraints();
		gbc_sellBtn.gridx = 2;
		gbc_sellBtn.gridy = 9;
		panel.add(sellBtn, gbc_sellBtn);
		TowerSellPriceCalculator market = new TowerSellPriceCalculator();
		long sellPrice = market.getSellTowerAmount(tower);
		sellPriceCount.setText(new Long(sellPrice).toString());
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
		dialog.setSize(300, 303);
		dialog.setLocationRelativeTo(null);
	}

	/**
	 * <b>This method is based on user action, either upgrades a tower or sells it.</b>
	 * @param event ActionEvent passed based on user action.
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();
		switch (command) 
		{
			case "Upgrade":
				upgradeTower();
				break;
			case "Sell":
				sellTower();
				break;
		}
	}

	/**
	 * <b>This method is used by the Observer to know what has been updated.</b>
	 * 
	 * @return "Sell" or "Upgrade"
	 */
	public String getPerformedAction() 
	{
		return performedAction;
	}
	
	/**
	 * This method is used to sell and compute  a current balance
	 */
	private void sellTower() 
	{
		String str = sellPriceCount.getText();
		long temp = bank.getCurrentBalanceMoney();
		long sellPrice = new Long(str).longValue();
		temp -= sellPrice;
		bank.resetCurrentBalanceMoney();
		bank.setCurrentBalanceMoney(temp);
		performedAction = "Sell";
		closeInspector();
	}
	
	/**
	 * This method is for tower upgradation
	 */
	private void upgradeTower() 
	{
		if (!tower.getLevel().equals(TowerLevelEnum.THREE)) 
		{
			String str = this.speedCount.getText();
			int speedCount = Integer.parseInt(str) + 1;
			str = rangeCount.getText();
			int rangeCount = Integer.parseInt(str) + 1;
			str = powerCount.getText();
			int powerCount = Integer.parseInt(str) + 1;

			String tempId = tower.Id;
			Location p = tower.getTowerPosition();
			Tower newTower = upgradeTowerLevel(this.tower, speedCount,
					rangeCount, powerCount);
			if (newTower != null) 
			{
				newTower.Id = tempId;
				newTower.setTowerPosition(p);
				this.tower = newTower;
				this.speedCount.setText(new Integer(speedCount++).toString());
				this.rangeCount.setText(new Integer(rangeCount++).toString());
				this.powerCount.setText(new Integer(powerCount++).toString());
				this.valueCount.setText(Long.toString(newTower.towerCost()));
				TowerSellPriceCalculator market = new TowerSellPriceCalculator();
				this.sellPriceCount.setText(Double.toString(market
						.getSellTowerAmount(newTower)));
				this.levelLabel.setText("Level "
						+ newTower.getLevel().toString());
				this.sellBtn.setEnabled(false);
				performedAction = "Upgrade";
				sendUpdateSignal();
				if (newTower.getLevel().equals(TowerLevelEnum.THREE)) 
				{
					this.upgradeBtn.setEnabled(false);
				}
				this.tower = newTower;
			}
		}
	}

	/**
	 * @return The tower that has been acted upon.
	 */
	public Tower getTower() 
	{
		return this.tower;
	}
	
	/**
	 * This method is used to upgrade a level of towers
	 * @param tower that is need to be upgraded 
	 * @param speedCount new value for speed
	 * @param rangeCount new value for range
	 * @param powerCount new value for power
	 * @return
	 */
	private Tower upgradeTowerLevel(Tower tower, int speedCount,
			int rangeCount, int powerCount) 
	{
		TowerFactory factory = new TowerFactory();
		List<Tower> towerList = tower.getTowers();
		this.towertype = factory.getDecoratedName(towerList);
		long value = tower.towerCost();
		TowerLevelEnum level = tower.getLevel();
		Tower createdTower = null;
		createdTower = factory.updateLevel(tower, this.towertype, speedCount,rangeCount, powerCount);
			
		long delta = createdTower.towerCost() - value;
		if (delta < bank.getBalanceMoney() - bank.getCurrentBalanceMoney()) 
		{
			bank.setCurrentBalanceMoney(delta);
			switch (level) 
			{
			case ONE:
				createdTower.setLevel(TowerLevelEnum.TWO);
				break;
			case TWO:
				createdTower.setLevel(TowerLevelEnum.THREE);
				break;
			default:
				break;
			}
			return createdTower;
		} 
		else 
		{
			JOptionPane.showMessageDialog(new JFrame(), "you don't have enough money :(", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	/**
	 * <b>This method is used to close the inspection panel.</b>
	 */
	public void close() 
	{
		dialog.dispose();
	}
	
	/**
	 * <b>This method is used to close the inspector.</b>
	 */
	private void closeInspector() 
	{
		sendUpdateSignal();
		dialog.dispose();
	}
	
	/**
	 * <b>This method is used to send updated signal.</b>
	 */
	private void sendUpdateSignal() 
	{
		setChanged();
		notifyObservers();
	}
}
