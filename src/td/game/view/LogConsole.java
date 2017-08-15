package td.game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import td.game.services.Log4jLogger;
import td.game.services.LogController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class is used to show dialog box for game log
 * @author Team3
 */
public class LogConsole extends JDialog 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private static LogController gameLogManager;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	
	/**
	 * The constructor is used to create the dialog
	 * @param gameLogManager is used for game log 
	 */
	public LogConsole(LogController gameLogManager) 
	{
		this.gameLogManager = gameLogManager;
		setTitle("Game Log");
		setBounds(200, 200, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		{
			final JComboBox comboBox = new JComboBox();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getLog(comboBox.getSelectedItem());
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(populateList()));
			contentPanel.add(comboBox);
		}
		{
			textArea = new JTextArea();
			textArea.setColumns(30);
			textArea.setRows(20);
			textArea.setWrapStyleWord(true);
			scrollPane = new JScrollPane(textArea);
			JScrollBar bar = new JScrollBar();
			scrollPane.add(bar);
			contentPanel.add(scrollPane);
			getContentPane().add(contentPanel, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		getGlobalGameLog();
	}
	
	/**
	 * This method is used to get the log
	 * @param selectedItem
	 */
	protected void getLog(Object selectedItem) 
	{
		try 
		{
			if (selectedItem instanceof String) 
			{
				String str = (String) selectedItem;
				if (str == "Global Log") 
				{
					getGlobalGameLog();
				} 
				else if (str == "Collective Tower Log") 
				{
					textArea.setText(gameLogManager.getCollectiveTowerLog());
				} 
				else if (str.substring(0, str.indexOf(":")).equalsIgnoreCase("Wave")) 
				{
					int waveNum = new Integer((str.substring(str.indexOf(":") + 1).trim())).intValue();
					textArea.setText(gameLogManager.getIndividualWaveLog(waveNum));
				} 
				else if (str.substring(0, str.indexOf(":")).equalsIgnoreCase("Tower")) 
				{
					String towerId = (str.substring(str.indexOf(":") + 1).trim());
					textArea.setText(gameLogManager.getIndividualTowerLog(towerId));
				}
			}
		} 
		catch (Exception ex) 
		{
			logger.writer("LogConsole.java", ex);
		}
	}
	
	/**
	 * This method is used to populate the wave and tower lists
	 * @return list
	 */
	public Object[] populateList() 
	{
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> waves = gameLogManager.getWaves();
		ArrayList<String> towers = gameLogManager.getTowers();
		list.add("Global Log");
		list.add("Collective Tower Log");
		list.addAll(waves);
		list.addAll(towers);
		return list.toArray();
	}
	
	/**
	 * This method is used to get the game log
	 */
	private void getGlobalGameLog() 
	{
		textArea.setText(gameLogManager.getGlobalLog());
	}
}
