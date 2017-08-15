package td.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import td.game.model.Location;
import td.game.model.MapContents;
import td.game.model.MapObjects;
import td.game.model.VisualMapObjects;
import td.game.services.Log4jLogger;
import td.game.services.MapController;
import td.game.services.MapUtility;
import td.game.services.PathEndPointsCheckerService;
import td.game.services.PathValidityChecker;
import td.game.constants.Constants;

/**
 * This class is used to create/map 
 * @author Team3
 */
public class MapEditor extends JPanel implements ActionListener,MouseListener, MouseMotionListener 
{
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private int[] unionArray;
	private int[] unionSize;
	private Map<Location, Integer> unionMap;
	private static final Log4jLogger logger = new Log4jLogger();

	JRadioButton scenery;
	JRadioButton path;
	JRadioButton entry;
	JRadioButton exit;
	JRadioButton designmap;
	JRadioButton loadmap;
	JRadioButton savemap;
	Color gridDrawColor;
	MapContents contentOfCell;
	MapObjects grid;
	MapEditor mapEditor;
	ObjectCanvas canvas;
	JPanel mapLayer;
	JPanel mapButtons = new JPanel();
	MapController mapManager;
	
	/**
	 * Constructor
	 */
	@SuppressWarnings("unused")
	public MapEditor() 
	{
	}
	
	/**
	 * <b>main content of the map editor Initialization</b>
	 * @param width map width
	 * @param height map height
	 */
	public MapEditor(int width, int height) 
	{
		initialize(width, height);
		setLayout(new BorderLayout());
		scenery.setBackground(new Color(255, 255, 102));
		path.setBackground(new Color(200, 117, 51));
		entry.setBackground(new Color(38, 106, 46));
		exit.setBackground(new Color(255, 51, 51));
		loadmap.setBackground(new Color(51, 51, 255));
		savemap.setBackground(new Color(160, 160, 160));
		designmap.setBackground(new Color(255, 128, 0));
		
		mapButtons.setSize(10, 10);
		mapButtons.setLayout(new GridLayout(20,20));
		mapButtons.add(scenery);
		mapButtons.add(path);
		mapButtons.add(exit);
		mapButtons.add(entry);
		mapButtons.add(designmap);
		mapButtons.add(loadmap);
		mapButtons.add(savemap);
		
		mapManager=new MapController();

		scenery.addActionListener(this);
		path.addActionListener(this);
		entry.addActionListener(this);
		exit.addActionListener(this);
		loadmap.addActionListener(this);
		savemap.addActionListener(this);
		designmap.addActionListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		int setMapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int setMapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(setMapPixelWidth, setMapPixelHeight);
		mapLayer.setPreferredSize(new Dimension(setMapPixelWidth,
				setMapPixelHeight));
		mapLayer.add(canvas);

		add(new JPanel(), BorderLayout.NORTH);
		add(mapButtons, BorderLayout.WEST);
		add(mapLayer, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.SOUTH);
		setVisible(true);
	}

	/**
	 *This method is used to initialize  user interface tools when user needs to design a new map 
	 * @param width width of an initial map
	 * @param height height of an initial map
	 */
	private void initialize(int width, int height) 
	{
		this.width = width;
		this.height = height;

		scenery = new JRadioButton(Constants.SCENERY);
		path = new JRadioButton(Constants.PATH);
		entry = new JRadioButton(Constants.ENTRANCE);
		exit = new JRadioButton(Constants.EXIT);

		designmap = new JRadioButton("Design Map");
		loadmap = new JRadioButton("Load Map");
		savemap = new JRadioButton("Save Map");
		gridDrawColor = new Color(38, 106, 46);;
		contentOfCell = MapContents.PATH;
		grid = new VisualMapObjects(width, height, MapContents.SCENERY);
		canvas = new ObjectCanvas(grid);
		mapLayer = new JPanel();
		mapButtons = new JPanel();
	}

	/**
	 * TO find out the co-ordinate on the canvas
	 */
	public class CanvasCoordinate extends Point 
	{
		public CanvasCoordinate(int x, int y) 
		{
			super(x, y);
		}
	}

	/**
	 * <b>This method is used to convert point to a coordinate which is action able by the canvas.</b>
	 * @param location of the target on the grid (canvas)
	 * @return canvas coordinate
	 */
	public CanvasCoordinate toCanvasCoordinates(Point point) 
	{
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	/**
	 * <b>Sets the grid size either to design a new map or to load a map.</b>
	 * @param width map width
	 * @param height map height
	 */
	public void setGridSize(int width, int height) 
	{
		grid.setSize(width, height);
		canvas.setGrid(grid);
	}

	/**
	 * prepares for map design.
	 * @param width
	 * @param height
	 */
	public void design(int width, int height) 
	{
		try 
		{
			grid.setSize(width, height);
			canvas.setGrid(grid);
			mapLayer.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * <b>Prepares to draw path.</b>
	 * @param backgroundColor path color 
	 */
	public void drawPath(Color backgroundColor) 
	{
		try 
		{
			gridDrawColor = backgroundColor;
			contentOfCell = MapContents.PATH;
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent event) 
	{
		draw(event.getX(), event.getY());
	}

	/**
	 * This method is used to draw a map
	 * @param x
	 * @param y
	 */
	private void draw(int x, int y) 
	{
		int i = x / grid.getUnitSize();
		int j = y / grid.getUnitSize();
		boolean drawAllowed = true;
	
		if (contentOfCell == MapContents.ENTRANCE || contentOfCell == MapContents.EXIT) 
		{
			if (!isPresent(contentOfCell)) 
			{
				drawAllowed = false;
			}
		}
		
		if (drawAllowed) 
		{
			if ((i < grid.getWidth()) && (j < grid.getHeight())
					&& (grid.getCell(i, j) != contentOfCell)) 
			{
				grid.setCell(i, j, contentOfCell);
				canvas.repaint();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent event) 
	{
	}
	
	/** <b>Draws the selected content on the map.</b>
	 * @param event MouseEvent passed to the method. 
	 */
	public void mouseClicked(MouseEvent event) 
	{
		draw(event.getX(), event.getY());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent event) 
	{
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
	}

	
	/** 
	 * <b>Depending on which button user clicks on, a scenery, entrance, exit, or path will be selected to draw on map.</b> 
	 * @param event the ActionEvent passed to this method.
	 */
	public void actionPerformed(ActionEvent event) 
	{
		JRadioButton command = (JRadioButton) event.getSource();
		
        if (command == scenery) 
        { 
			scenery();
			path.setSelected(false);
			entry.setSelected(false);
			exit.setSelected(false);
			loadmap.setSelected(false);
			savemap.setSelected(false);
        } 
        else if (command == path) 
        {
			path();
			scenery.setSelected(false);
			entry.setSelected(false);
			exit.setSelected(false);
			loadmap.setSelected(false);
			savemap.setSelected(false);
			designmap.setSelected(false);
        } 
        else if (command == entry) 
        { 
			entrance();
			path.setSelected(false);
			scenery.setSelected(false);
			exit.setSelected(false);
			loadmap.setSelected(false);
			savemap.setSelected(false);
			designmap.setSelected(false);
        }
        else if (command == exit) 
        {
			exit();
			path.setSelected(false);
			scenery.setSelected(false);
			entry.setSelected(false);
			loadmap.setSelected(false);
			savemap.setSelected(false);
			designmap.setSelected(false);
        }
        else if (command == loadmap) 
        {
			MapFrame mapFrame = new MapFrame();
			mapFrame.loadOpenMap();			
			path.setSelected(false);
			entry.setSelected(false);
			scenery.setSelected(false);
			savemap.setSelected(false);
			designmap.setSelected(false);
			entry.setSelected(false);
			exit.setSelected(false);			
		}
        else if (command == savemap) 
        {
			saveMapAsFile();
			path.setSelected(false);
			entry.setSelected(false);
			scenery.setSelected(false);
			loadmap.setSelected(false);
			designmap.setSelected(false);
			exit.setSelected(false);
		}
        else if (command == designmap) 
        {
			MapFrame mapFrame = new MapFrame();
			mapFrame.designMap();
			path.setSelected(false);
			entry.setSelected(false);
			scenery.setSelected(false);
			loadmap.setSelected(false);
			exit.setSelected(false);
			savemap.setSelected(false);
		}      
	}
	
	/**
	 * To design a path
	 */
	private void path() 
	{
		try 
		{
			gridDrawColor = path.getBackground();
			contentOfCell = MapContents.PATH; // black for path
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * sets the Map size
	 * @param width
	 * @param height
	 */
	protected void mapSizeSet(int width, int height) 
	{
		try 
		{
			PathValidityChecker checker = new PathValidityChecker();
			if (!checker.isCorrectSize(height, width))
				throw new java.lang.Exception("Error size max size: ....., min size: ....");
			// end of validation
			mapLayer.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
			mapLayer.setBackground(new Color( 204, 255, 255));
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * gets the cellContent of for an exit point
	 */
	private void exit() 
	{
		try 
		{
			contentOfCell = MapContents.EXIT;
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * This method is used to get cell content for an entry point
	 */
	private void entrance() 
	{
		try 
		{
			contentOfCell = MapContents.ENTRANCE;
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * This method is used for checking for whether cell exists in a grid or not
	 * @param cellContent
	 * @return returns true if a given cell exists in a grid
	 */
	private boolean isPresent(MapContents cellContent) 
	{
		for (int x = 0; x < grid.getWidth(); x++) 
		{
			for (int y = 0; y < grid.getHeight(); y++) 
			{
				if (grid.getCell(x, y) == cellContent) 
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to draw the scenery on the map
	 */
	private void scenery() 
	{
		try 
		{
			gridDrawColor = scenery.getBackground();
			contentOfCell = MapContents.SCENERY;
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * This method is used to get the position of enter button from the map(canvas)
	 * @param map
	 * @return
	 */
	public Location getEnter(MapContents[][] map)
	{
		try 
		{
			for (int i = 0; i < map.length; i++) 
			{
				for (int j = 0; j < map[0].length; j++) 
				{
					if(map[i][j] == MapContents.ENTRANCE)
					return new Location(i, j);
				}
			}		
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return null;
	}
	
	/**
	 * This method is used to browse and open an existing map. This method is fired on click of Load Map
	 * from menu bar
	 */
	public void openExistingMap() 
	{
		JFileChooser browseMap = new JFileChooser();		
		try 
		{		
			if (JFileChooser.APPROVE_OPTION == browseMap.showOpenDialog(null)) 
			{
				grid = mapManager.loadSavedMapFromFile(browseMap.getSelectedFile().getAbsolutePath());
				canvas.setGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapLayer.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(), height * grid.getUnitSize());
			}

		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * This method is used to save a map as a File
	 */
	public void saveMapAsFile() 
	{
		try 
		{
			if (!isMapValid(grid.getContent())) 
			{
				throw new Exception("Map is not valid!!");
			} 
			else 
			{
				JFileChooser saveMap = new JFileChooser();
				if (saveMap.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					String mapName = saveMap.getSelectedFile().getAbsolutePath();
					mapManager.saveMapGridAsFile(grid, mapName);
				}
			}
		} 
		catch (java.lang.Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	
	/**
	 * This function validates the map
	 * @param matrix
	 * @return true if map is valid else false
	 */
	public boolean isMapValid(MapContents[][] matrix) 
	{
		PathValidityChecker startChecker = new PathValidityChecker();
		PathEndPointsCheckerService conn = new PathEndPointsCheckerService();
		MapUtility utility = new MapUtility();
		Location start = utility.getMapEnterance(matrix);
		Location end = utility.getMapExit(matrix);
		
		if (!startChecker.hasEndPoint(matrix)) 
		{
			return false;
		}
		if(!startChecker.hasStartPoint(matrix))
		{
			return false;
		}
		if (!conn.isTherePath(start, end, matrix)) 
		{
			return false;
		}
		if(startChecker.isNeighbor(start, end))
		{
			return false;
		}
		return true;
	}
}	