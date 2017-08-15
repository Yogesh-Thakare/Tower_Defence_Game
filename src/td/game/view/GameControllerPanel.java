package td.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.MatteBorder;

/**
 * This class is for creating layout Panel for game controlling
 * @author Team3
 */
@SuppressWarnings("serial")
public class GameControllerPanel extends JPanel
{
	private JToggleButton tglBtnNewToggleButton1;
	private String gameState;
	private TwoLayerPanelView mapPanel;
	int waveNumber;

	/**
	 * Create the panel ,for controlling the game status,
	 * the user can click certain buttons such as new game or critter info
	 * each one of this buttons will call specific object to reinitialize and show for
	 * the user
	 * @param mapPanel receive a panel to create new one
	 */
	public GameControllerPanel(TwoLayerPanelView mapPanel) 
	{
		this.gameState = "completed";
		this.mapPanel = mapPanel;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0,Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

		GridBagConstraints gbc_tglbtnNewToggleButton = new GridBagConstraints();
		gbc_tglbtnNewToggleButton.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton.gridx = 0;
		gbc_tglbtnNewToggleButton.gridy = 0;

		tglBtnNewToggleButton1 = new JToggleButton("New Wave");
		tglBtnNewToggleButton1.setPreferredSize(new Dimension(120, 70));
		GridBagConstraints gbc_tglbtnNewToggleButton_1 = new GridBagConstraints();
		gbc_tglbtnNewToggleButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnNewToggleButton_1.gridx = 1;
		gbc_tglbtnNewToggleButton_1.gridy = 0;
		add(tglBtnNewToggleButton1, gbc_tglbtnNewToggleButton_1);
		
		tglBtnNewToggleButton1.addMouseListener(new MouseAdapter() 
		{
			@Override
			/**
			 * @param arg0 catches event from mouse once clicked it will start the game
			 */
			public void mouseClicked(MouseEvent arg0) 
			{
				System.out.println("Start New Wave");
				
				performRequestedAction();
			}
		});
		
		TowerPanelLayout towerPanel = new TowerPanelLayout();
		GridBagConstraints gbc_towerPanel = new GridBagConstraints();
		gbc_towerPanel.fill = GridBagConstraints.BOTH;
		gbc_towerPanel.gridx = 2;
		gbc_towerPanel.gridy = 0;
		add(towerPanel, gbc_towerPanel);
	}
	
	/**
	 * The user can can pause a game and later resume it
	 * this gets the current state and acts accordingly
	 */
	protected void performRequestedAction() 
	{
		System.out.println(gameState);
		switch (gameState) 
		{	
			case "pause":
				mapPanel.getOtherItemsPanel().resumeGame();
				tglBtnNewToggleButton1.setText("Pause Game");
				this.gameState = "running";
				System.out.println(1);
				break;
			case "running":
				mapPanel.getOtherItemsPanel().pauseGame();
				tglBtnNewToggleButton1.setText("Resume Game");
				this.gameState = "pause";
				System.out.println(2);
				break;	
			case "completed":
			if (tglBtnNewToggleButton1.isEnabled())
			{  
				waveNumber= mapPanel.getWaveNumber();
				waveCount(waveNumber);
				mapPanel.getOtherItemsPanel().startWave();
				this.gameState = "running";
				System.out.println(3);
				tglBtnNewToggleButton1.setText("Pause Game");
				break;
			}
			default:
			break;
		}
	}
	
	/**
	 * Once a wave is completed , enable the user to start a new wave
	 * and reinitialize some instances
	 * @param waveNumber - the wave number
	 */
	public void waveCompleted(int waveNumber) 
	{
		this.gameState = "completed";
		tglBtnNewToggleButton1.setText("New Wave");		
		(mapPanel.getGameInfoPanel()).setWave(waveNumber);
		(mapPanel.getGameInfoPanel()).repaint();
	}
	
	/**
	 * This method is used to calculate the wave number and update it once the wave is started.
	 * @param waveNumber - the wave number
	 */
	public void waveCount(int waveNumber) 
	{
		(mapPanel.getGameInfoPanel()).setWave(waveNumber);
		 this.waveNumber = waveNumber;
	}	
}