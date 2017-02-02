
public class Node {
	
	
	//Attributes of Node
	private int name;
	private boolean mark;
	
	
	/**
	 * @constructor
	 * @param int name, the name of the node
	 */
	public Node(int name){
		
		this.name=name;
		this.mark = false;
		
	}
	
	public void setMark(boolean mark){
		
		this.mark=mark;
		
	}
	
	public boolean getMark(){
		
		return this.mark;
		
	}
	
	int getName(){
		
		return this.name;
		
	}
	
}
