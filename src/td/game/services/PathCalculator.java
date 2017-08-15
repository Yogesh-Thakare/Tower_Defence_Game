package td.game.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import td.game.model.Location;
import java.util.Stack;
/**
 * This class is used for the calculation of the graph path
 * @author Team3
 */
public class PathCalculator 
{
	private static final Log4jLogger logger = new Log4jLogger();
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex
 
    /**
     * This constructor Computes a path between s and every other vertex in graph G
     * @param g the graph
     * @param s the source vertex
     */
    public PathCalculator(GraphService g, int s) 
    {
        this.s = s;
        edgeTo = new int[g.v()];
        marked = new boolean[g.v()];
        depthFirstSearch(g, s);
    }
    
    /**
     * This method is used to check if there is a path between the source vertex s and vertex v
     * @param v the vertex
     * @return true if there is a path, false otherwise
     */
    public boolean hasPathTo(int v) 
    {
        return marked[v];
    }
    
    /**
     * This method returns a path between the source vertex s and vertex v, or
     * null if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a path between the source vertex
     * s and vertex v, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) 
    {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
    
    /**
     * This method is used for DepthFirstPaths data type.
     * @param nodes all node of graph
     * @param graphInfo graph info that contains the relations
     * @param start start point
     * @param end end point
     * @param nodesNum nodes number in the graph
     * @return Position[] it will return the current path 
     */
    public static Location[] getPath(Map<Location,Integer> nodes, List<String> graphInfo, int start, int end, int nodesNum) 
    {
        GraphService g = new GraphService(graphInfo, nodesNum);
        PathCalculator dfs = new PathCalculator(g, start);
        Map<Location,Integer> map = nodes;
        LinkedList<Location> route = new LinkedList<>();
        
        if (dfs.hasPathTo(end)) 
        {
        	for (int x : dfs.pathTo(end)) 
            {
        		Iterator<Entry<Location, Integer>> iteratotr = map.entrySet().iterator();
                while (iteratotr.hasNext()) 
                {
                	Entry<Location,Integer> obj = (Entry<Location,Integer>) iteratotr.next();
                	if(x == obj.getValue())
            		{
                		route.add(obj.getKey());
            			break;
            		}
            	}
             }
         }
        
        Location[]  array = new Location[route.size()];
        int i = route.size();
      
        for (Location r : route) 
        {
        	array[i - 1] = r;
        	i --;
		}
        return array;
    }
    
    /**
     * This method is used for depth first search from v
     * @param g
     * @param v
     */
    private void depthFirstSearch(GraphService g, int v) 
    {
    	try 
    	{
    		marked[v] = true;
    	    for (int w : g.adj(v)) 
    	    {
    	    	if (!marked[w]) 
    	        {
    	    		edgeTo[w] = v;
    	            depthFirstSearch(g, w);
    	        }
    	    }
		} 
    	catch (Exception e) 
    	{
    		logger.writer(this.getClass().getName(), e);		
		}
    }
}