package td.game.services;

import java.util.List;
import java.util.Stack;

/**
 * This is the service class used by the graph
 * @author Team3
 *
 */
public class GraphService 
{
	private final int vertice;
	private int edge;
	private Box<Integer>[] adj;

	/**
	 * Initializes an empty graph with V vertices and 0 edges.
	 * @param vertices number of vertices
	 */
	public GraphService(int vertices) 
	{
		if (vertices < 0) throw new IllegalArgumentException("The Number of vertices must not be negative");
		this.vertice = vertices;
		this.edge = 0;
		adj = (Box<Integer>[]) new Box[vertices];
		for (int v = 0; v < vertices; v++) 
		{
			adj[v] = new Box<Integer>();
		}
	}
	
	/**  
	 * This constructor initializes a graph from an input stream.
	 * @param graphInfo  the input stream
	 * @param nodes the graph nodes
	 */
	public GraphService(List<String> graphInfo, int nodes)
	{
		this(nodes);
		int edges = graphInfo.size();
		if (edges < 0) throw new IllegalArgumentException("Number of edges must not be negative");
		for (String str : graphInfo) 
		{
			String[] strs = str.split(" ");
			int v = Integer.parseInt(strs[0]);
			int w = Integer.parseInt(strs[1]);
			addEdgeGraph(v, w);
		}
	}
	
	/**
	 * This constructor initializes a new graph that is a deep copy of G
	 * @param graph the graph to copy
	 */
	public GraphService(GraphService graph) 
	{
		this(graph.v());
		this.edge = graph.e();
		for (int v = 0; v < graph.v(); v++) 
		{
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : graph.adj[v]) 
			{
				reverse.push(w);
			}
			
			for (int w : reverse) 
			{
				adj[v].addItems(w);
			}
		}
	}
	
	/**
	 * This method is used to get the number of edges in the graph.
	 * @return the number of edges in the graph
	 */
	public int e() 
	{
		return edge;
	}
	
	/**
	 *This method is used to get number of vertices in the graph.
	 * @return the number of vertices in the graph
	 */
	public int v() 
	{
		return vertice;
	}
	
	/**
	 * This method is used to Add the undirected edge v-w to the graph.
	 * @param v one vertex in the edge
	 * @param w the other vertex in the edge
	 */
	public void addEdgeGraph(int v, int w) 
	{
		validateVertex(v);
		validateVertex(w);
		edge++;
		adj[v].addItems(w);
		adj[w].addItems(v);
	}	
	
	/**
	 * This method is used to validate the vertex
	 * @param v
	 */
	private void validateVertex(int v) 
	{
		if (v < 0 || v >= vertice)
			throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertice-1));
	}
	
	/**
	 * This method is used to return the vertices adjacent to vertex v
	 * @return the vertices adjacent to vertex v as type Iterable
	 * @param v the vertex
	 */
	public Iterable<Integer> adj(int v) 
	{
		validateVertex(v);
		return adj[v];
	}
}