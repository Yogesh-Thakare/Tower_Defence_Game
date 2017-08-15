package td.game.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import td.game.model.Location;

/**
 * This class is used for the path compressor
 * @author Team3
 */
@Component
public class PathCompressor implements IUnion, IPathCompressor 
{
	private static final Log4jLogger logger = new Log4jLogger();
	private int[] unionArray;
	private int[] unionSize;
	private Map<Location, Integer> unionMap;	
	
	/**
	 * This constructor is used to initialize the empty one dimensional array 
	 * that has n * m size and the nodes are initialized by their index
	 * @param n the size of the array
	 * @param m the size of array
	 */
	@Override
	public void initialize(int n, int m)
	{
		try 
		{
			unionArray = new int[n * m];
			unionSize = new int[n * m];
			unionMap = new HashMap<>();
			int index = 0;
			for(int i = 0;i < n; i++)
			{
				for(int j = 0; j < m; j++)
				{
					unionArray[index] = index;
					unionSize[index] = 1;
					Location position = new Location(i, j);
					unionMap.put(position, index);
					index++;
				}
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to get the root of each graph
	 * @param pKey index of node p 
	 * @return int that represent the root node of each graph
	 */
	@Override
	public int root(int pKey)
	{
		try 
		{
			while (pKey != unionArray[pKey]) 
			{
				unionArray[pKey] = unionArray[unionArray[pKey]];
				pKey = unionArray[pKey];
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
		return pKey;
	}
	
	/**
	 * This method is used to make a union between two nodes of graph
	 * @param p node p from array
	 * @param q node q from array
	 */
	@Override
	public void union(Location p, Location q)
	{
		try 
		{
			int pKey = (Integer)unionMap.get(p);
			int qkey = unionMap.get(q);
			int i = root(pKey);
			int j = root(qkey);
			
			if( i == j) return;
			
			if(unionSize[i] < unionSize[j])
			{
				unionArray[i] = j;
				unionSize[j] += unionSize[i];
			}
			else
			{
				unionArray[j] = i;
				unionSize[i] += unionSize[j];
			}
		} 
		catch (Exception e) 
		{
			logger.writer(this.getClass().getName(), e);
		}
	}
	
	/**
	 * This method is used to check two nodes have any connection or not</b>
	 * @param p a position on map
	 * @param q a second position on the grid
	 * @return true, if the both of them has a connection  
	 */
	@Override
	public boolean isConnected(Location p, Location q)
	{
		int pKey = (Integer)unionMap.get(p);
		int qkey = unionMap.get(q);
		return root(pKey) == root(qkey);
	}
}