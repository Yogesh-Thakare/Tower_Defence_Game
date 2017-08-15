package td.game.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import td.game.model.MapObjects;

/**
 * This class is used to display the score card of highest scores
 * @author team3
 */
public class ScoreCard extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea textArea;
	private MapObjects grid;
	private JScrollPane scrollPane;

	/**
	 * This method is used to create the dialog
	 * @param grid current grid used
	 */
	public ScoreCard(MapObjects grid) 
	{
		this.grid = grid;
		setTitle("Map Log & Score Board");
		setBounds(200, 200, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			textArea = new JTextArea(getHighScores());
			textArea.setColumns(30);
			textArea.setRows(20);
			textArea.setWrapStyleWord(true);
			
			scrollPane = new JScrollPane(textArea);
			JScrollBar bar = new JScrollBar();
			scrollPane.add(bar);
			contentPanel.add(scrollPane);
		
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
	}
	
	
	/**
	 * This method is used to get top 5 scores for a particular map
	 * @return
	 */
	private String getHighScores() 
	{
		return grid.getHighestScores(5);
	}
}
