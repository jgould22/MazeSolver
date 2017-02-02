/**
 * This class represents an edge of the graph
 * @author Jordan Gould jgould22@uwo.ca
 *
 */
public class Edge {
	
	private Node firstEndpoint, secondEndpoint;
	private String type, label;
	
	
	/**
	 *Constructor
	 * @param u First node
	 * @param v second node
	 * @param type 
	 */
	public Edge(Node u, Node v, String type){
		
		this.firstEndpoint = u;
		this.secondEndpoint = v;
		this.type = type;
		this.label = "";
		
	}
	
	/**
	 * FirstEnpoint
	 * @return the first endpoint of this node
	 */
	public Node firstEndpoint(){
		
		return this.firstEndpoint;
		
	}
	
	/**
	 * Second Endpoint
	 * @return the second endpoint of this node
	 */
	public Node secondEndpoint(){
		
		return this.secondEndpoint;
		
	}
	
	/**
	 * setLabel
	 * @param String Label 
	 */
	public void setLabel(String label){
		
		this.label = label;
		
	}
	
	/**
	 * getLabel
	 * @param String Label 
	 */
	public String getLabel(){
		
		return this.label;
		
	}
	
	/**
	 * getTypel
	 * @param String Type 
	 */
	public String getType(){
		
		return this.type;
		
	}
	
	
}
