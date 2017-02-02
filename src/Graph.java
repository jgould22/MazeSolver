/**
 * Graph
 * @author Jordan Gould jgould22@uwo.ca
 *
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {

	//Decalre class variables
	private int graphSize;
	private Node[] nodeArray;
	private Edge[][] edgeMatrix;
	
	/**
	 * Constructor
	 * @param int n, the size of the graph
	 */
	public Graph(int n){
		
		this.graphSize = n;
		
		//Creat the graph with the numebr of nodes and store them in an array
		nodeArray = new Node[n];
		for(int i=0;i<n;i++){
			
			nodeArray[i] = new Node(i);
			
		}
		
		//Instantiate edge matrix
		edgeMatrix = new Edge[n][n];
		
	}
	
	
	/**
	 * Insert Edge Methods
	 * @param Node u, the first node conneted to the edge 
	 * @param Node v, the second node conneted to the edge 
	 * @param edgeType,wall or hall
	 * @throws GraphException
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException{
		
		//Declare the names of the two end node
		int uName = u.getName(), vName = v.getName();
		
		//Check to make sure the node names are within the node array,
		//if they are outside the bounds of the array they dont exist
		
		//Check is node u exists, if not throw graphexception
		if(u.getName()>nodeArray.length-1 || u.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
		
		//Check is v exists if not throw graph exception
		if(v.getName()>nodeArray.length-1 || v.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
		
		if(edgeMatrix[uName][vName] != null)
			throw new GraphException("Error: Node Does Not Exist!");
		
		try{
			
			//Add the Edge to teh edge matrix for both possiable locations
			edgeMatrix[uName][vName] = new Edge(u, v, edgeType);
			
			edgeMatrix[vName][uName] = new Edge(u, v, edgeType);
			
		}catch(Exception e){
			
			throw new GraphException("Error: Node Does Not Exist!");
			
		}
		
	}
	
	/**
	 * GetNode
	 * @param int name, the number of the node
	 * @return Node the node with the name
	 */
	public Node getNode(int name){
		
		return nodeArray[name];
		
		
	}
	
	/**
	 * incidentEdges
	 * @param Node u, the node whose incident edges we need
	 * @return Iterator of the edges that are incident to the supplied node
	 * @throws GraphException
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException{
		
		//Check is node u exists, if not throw graphexception
		if(u.getName()>nodeArray.length-1 || u.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
		
		//Get the name
		int uName = u.getName();
		
		//Declare list to store the edges
		ArrayList<Edge> list = new ArrayList<Edge>();
		
		//loop through the row of the edgematrix adding the edges if they are not null
		for(int i = 0;i<graphSize;i++){
			
			if(edgeMatrix[uName][i] != null){
				
				list.add(edgeMatrix[uName][i]);
				
			}
			
			
		}
		
		//if the list is empty there are no incident edges, return null
		if(list.isEmpty()){
			
			return null;
			
		}else{
		
			//return an iterator of the edges
			return list.iterator();
			
		}
		
		
	}
	
	/**
	 * getEdge method
	 * @param Node u
	 * @param Node v
	 * @return Edge, the edge between node u and v
	 * @throws GraphException
	 */
	public Edge getEdge(Node u, Node v)throws GraphException{
		
		//Check is node u exists, if not throw graphexception
		if(u.getName()>nodeArray.length-1 || u.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
				
		//Check is v exists if not throw graph exception
		if(v.getName()>nodeArray.length-1 || v.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
		
		//Check if the edge is null, if it is it doesnt exist
		if(edgeMatrix[u.getName()][v.getName()] == null)
			throw new GraphException("Error: Edge Does Not Exist!");
		else
			//return edge between nodes v and u
			return edgeMatrix[u.getName()][v.getName()];
		
	}
	
	/**
	 * areAdjacent
	 * @param u
	 * @param v
	 * @return boolean, true if u and v are ajacent, false otherwise
	 * @throws GraphException
	 */
	public boolean areAdjacent(Node u, Node v)throws GraphException{
		
		//Check is node u exists, if not throw graphexception
		if(u.getName()>nodeArray.length-1 || u.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
				
		//Check is v exists if not throw graph exception
		if(v.getName()>nodeArray.length-1 || v.getName()<0)
			throw new GraphException("Error: Node Does Not Exist!");
		
		//Check both possiable locations for an edge, if no edge return false
		if((edgeMatrix[u.getName()][v.getName()] == null) && (edgeMatrix[v.getName()][u.getName()] == null))
			return false;
		else
			return true;
		
	}
	
	
	
	
	
}
