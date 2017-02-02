/**
 * Labyrinth Class
 * @author JordanGould jgould22@gmail.com
 * December 4th 2013
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Labyrinth {

	private Graph labGraph = null;
	private int width, length, wallBreakNumber;
	private Node entrance,exit;
	
	/**
	 * Constructor
	 * @param inputFile, .txt fileproperly formatted
	 * @throws LabyrinthException
	 */
	public Labyrinth(String inputFile) throws LabyrinthException{
		
		//Declare new file
		File inputfile = new File(inputFile);
		//declare queue to store the lines of the file
		Queue<String> q = new LinkedList<String>();
		
		 try {
			 	
			 //open buffered reader and start reading the file
		        FileReader reader = new FileReader(inputfile);
		        BufferedReader buffReader = new BufferedReader(reader);
		        int x = 0;
		        String s;
		        
		        //Loop throw lines storing them in the queue
		        while((s = buffReader.readLine()) != null){
		           
		        	q.add(s);
		        	
		        }
		        
		        //close the stream
		        buffReader.close();
		        
		    }
		    catch(IOException e){
		        
		    	throw new LabyrinthException("File IO Error");
		    	
		    }
		 
		 //Remove  the first line(scale factor) as this class does not need it
		 q.remove();
		 
		 try{
			 //Get width (second line in file)
			 this.width = Integer.parseInt(q.remove());
			 //Get Length (third line in file)
			 this.length = Integer.parseInt(q.remove());
			 //Get the number of walls the program is allowed to break (fourth  line in file)
			 this.wallBreakNumber = Integer.parseInt(q.remove());
			 
		 }catch(NoSuchElementException e){
			 
			 throw new LabyrinthException("Input File Incorrectly Formatted");
			 
		 }
		 
		 //Instantiate new graph of size length*width
		 labGraph = new Graph(this.length*this.width);
		
		//Read the chars from the input file into an array
		char[][] charArray = new char[(2*this.length)-1][(2*this.width)-1];
		 
		int i=0;
		
		//loop through each string in the queue storing each char in an array that will represent the lab
		while(!q.isEmpty()){
			 
			 String str = q.remove();
			 for (int j = 0, n = str.length(); j < n; j++) {
				 
				 char c = str.charAt(j);
				 charArray[i][j] = c;
				 
			 }
			 
			 i++;
			 
		}
		
		//Fill nodeNumbers array so it can be easily used to locate the nodes above and below an edge
		//during parsing
		int nodeCount = 0;
		int[][] nodeNumbers = new int[(2*this.length)-1][(2*this.width)-1];
		
		for(int r =0; r< (2*this.length)-1; r++) {
				
				for(int s =0; s< (2*this.width)-1; s++){
					
				
					nodeNumbers[r][s] = nodeCount;
					nodeCount++;
					s++;
					
				}
				
				r++;
				
		}
		
		//set node count = -1 since first node is 0
		nodeCount = -1;
		//loop through the lines parsing each char and inserting edges where appropriate
		for(int r = 0; r< (2*this.length)-1; r++) {
			
			for(int s = 0; s< (2*this.width)-1; s++) {
		
				try{
					 switch (charArray[r][s]) {
			            case 's':   nodeCount++;
			            			this.entrance = labGraph.getNode(nodeCount);
			            			break;
			            case 'e': 	nodeCount++; 		
			            			this.exit = labGraph.getNode(nodeCount);
			                     	break;
			            case 'o': 	nodeCount++;
			            			break;
			            case 'h': 	labGraph.insertEdge(labGraph.getNode(nodeNumbers[r][s-1]), labGraph.getNode(nodeNumbers[r][s+1]), "wall");
			                     	labGraph.getEdge(labGraph.getNode(nodeNumbers[r][s-1]), labGraph.getNode(nodeNumbers[r][s+1])).setLabel("UNEXPLORED");
			                     	labGraph.getEdge(labGraph.getNode(nodeNumbers[r][s+1]),labGraph.getNode(nodeNumbers[r][s-1])).setLabel("UNEXPLORED");
			            			break;
			            case 'v': 	labGraph.insertEdge(labGraph.getNode(nodeNumbers[r-1][s]), labGraph.getNode(nodeNumbers[r+1][s]), "wall");
			            			labGraph.getEdge(labGraph.getNode(nodeNumbers[r-1][s]), labGraph.getNode(nodeNumbers[r+1][s])).setLabel("UNEXPLORED");
			            			labGraph.getEdge( labGraph.getNode(nodeNumbers[r+1][s]),labGraph.getNode(nodeNumbers[r-1][s])).setLabel("UNEXPLORED");
	                 				break;
			            case '|': 	labGraph.insertEdge(labGraph.getNode(nodeNumbers[r-1][s]), labGraph.getNode(nodeNumbers[r+1][s]), "hall");
			            			labGraph.getEdge(labGraph.getNode(nodeNumbers[r-1][s]), labGraph.getNode(nodeNumbers[r+1][s])).setLabel("UNEXPLORED");
			            			labGraph.getEdge(labGraph.getNode(nodeNumbers[r+1][s]),labGraph.getNode(nodeNumbers[r-1][s])).setLabel("UNEXPLORED");
	     							break;        
			            case '-':   labGraph.insertEdge(labGraph.getNode(nodeNumbers[r][s-1]), labGraph.getNode(nodeNumbers[r][s+1]), "hall");
			            			labGraph.getEdge(labGraph.getNode(nodeNumbers[r][s-1]), labGraph.getNode(nodeNumbers[r][s+1])).setLabel("UNEXPLORED");
			            			labGraph.getEdge(labGraph.getNode(nodeNumbers[r][s+1]),labGraph.getNode(nodeNumbers[r][s-1]) ).setLabel("UNEXPLORED");
			            			break;
			            case ' ':   break;
					}
				}catch(GraphException e){
					
					throw new LabyrinthException("Cannot add Edge to Graph, Labrinth Creation Failed");
					
				}
				
				
			}
			
			
			
				
		
		
		}
		
		
		
		
	}
	
	
	/**
	 * getGraph
	 * @return the graph of this labyrinth
	 * @throws LabyrinthException
	 */
	public Graph getGraph() throws LabyrinthException {
		
		//If its not null return lab graph else throw exception
		if(labGraph != null)
			return labGraph;
		else
			throw new LabyrinthException("Graph Not yet created");
		
	}
	
	public Iterator<Node> solve(){
		
		//Declare new stack to store the path if there is one
			Stack<Node> stack= new Stack<Node>();		
		//return an iterator of the stack, stack iterator is null if no path found	
			return findPath(this.labGraph, this.entrance,this.exit, stack, this.wallBreakNumber);
		
	}
	
	/**
	 * findPath, private method to find the path recursively 
	 * @param Graph G, the graph of this labyrinth
	 * @param Node v, the starting node
	 * @param Node z, the exit node
	 * @param Stack s, the stack to store the path
	 * @param Int broken walls, teh number of walls that can be broken to reach exit
	 * @return
	 */
	private Iterator<Node> findPath(Graph G,Node v,Node z,Stack<Node> s, int brokenwalls){
		
		try{
		
		//set node to true, as it has been visited
		v.setMark(true);
		
		//push the node to the stack
		s.push(v);

		//If brokenwalls in < 0 this is not a valid path, pop the node
		if(brokenwalls>=0){
		
			//if v = z the exit has been found, return an iterator of the stack
			if(v == z){
				Iterator<Node> path = s.iterator();
				return path;
			}	
			
			
			//Declare an iterator of the incident edges of v
			Iterator<Edge> incidentEdges= G.incidentEdges(v);
			
			//loop through the incident edges
			while(incidentEdges.hasNext()){
					
				//get an edge from the iterator
				Edge checkEdge = incidentEdges.next();
					
					//if the edge is unexplored we havnt visited the node at the other end yet
					if(checkEdge.getLabel()=="UNEXPLORED"){
								
						//declare node that is opposite v
						Node w = checkEdge.secondEndpoint();
					
						//ensure the opposite node is in secondEndpoint, if not its in first endPoint
						if(w==v){
							
							w = checkEdge.firstEndpoint();
							
						}
						
						//if w has not been visited
						if(w.getMark()==false){
						
							//check if its a wall or hall
							if(checkEdge.getType()=="wall"){
								
								//if its a wall recursively call findpath with new w node and brokenwalls-1
								Iterator<Node> result = findPath(G,w,z,s,brokenwalls-1);
								
								//if the return is not null a path has been found,r return the result
								//else set the edge to unexplored and the node to false and try a different path
								if(result!=null)
									return result;
								else
									checkEdge.setLabel("UNEXPLORED");
							}else{
								//if its a hall call find path to check the next node
								Iterator<Node> result = findPath(G,w,z,s,brokenwalls);
								if(result!=null)
									return result;
								else
									checkEdge.setLabel("UNEXPLORED");
							}
							
								
						}			
					}	
				}
		}
		
		//path not found, set node visited to false and pop it from the stack
		v.setMark(false);
		s.pop();
			
		}catch(GraphException e){
			
			e.printStackTrace();
			
		}
		
		//path wasnt found return null;
		return null;	
		
	}
	

}
