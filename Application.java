import java.util.*;

public class Application { 
	
	static int numV, numE;
	static LinkedList<Integer> graph[];
	
	
	static ArrayList<Edge> edges;
	static Scanner input;	
	static ArrayList edgesTest;
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		input = new Scanner(System.in);
		System.out.print("Enter the number of Vertices: ");
		numV= input.nextInt();
		
		while(numV>50) {//check to make sure there's not too many vertices
			System.out.println("Too many Vertices, dont break me. \nTry again: ");
			numV=input.nextInt();
		}//end check 
		
		graph = new LinkedList[numV]; //generate linkedList 
		
		
		for(int v=0; v < numV; v++) {
			graph[v] = new LinkedList<>();
		}//end for
		
		edges = new ArrayList<Edge>();
		
		int rNum, rNum2;
		
		int maxEdges = ((numV-1) * numV)/2;
		int minEdges = numV-1; 
		
		numE = rand.nextInt(maxEdges-minEdges)+ minEdges; //random number from  
		
		for(int i=0; i<numE; i++) {
			
			do {//the while check if the edge already exists. Sometimes edges get duplicated it seems.
				
				rNum=rand.nextInt(numV);
				rNum2=rand.nextInt(numV);				
				
				counter++; 
				
				while(rNum==rNum2) {
					rNum=rand.nextInt(numV);
				}//end if. Make sure we arent connecting the node to itself
			} while(checkEdge(rNum, rNum2)==true);//end while 
			
			edges.add(new Edge(rNum, rNum2));
			addEdge(rNum, rNum2);
			
		}//end populate graph
		print();
	}//end main
	
	
	public void traverseGraph() {
		
		int[] tempArray = countEdges(); 
		
		int minNum = 0; 
		int minVertex = 0; 
		for(int i = 0; i <= tempArray.length; i++) {
			
			if(minNum < tempArray[i]) {
				minNum = tempArray[i]; 
				minVertex = i; 
			}//end if 
		}//end for 
		
		if(minNum < 2) {
			System.out.println("Not a hamiltonian graph! The degree of vertex" + minVertex + " is less than 2!");
		}//end if 
		
		//put min Vertex 
	}
	
	
	/*
	 * Count Edges 
	 */
	public int[] countEdges() {
		
		//array to hold numbers 
		int[] totalEdges = new int[numV]; 
		
		//counts the number of edges for each vertex 
		for(int v=0; v<numV; v++) {
			
			int countEdges = 0;
			for(Integer n: graph[v]) {
				countEdges++; 
			}//end inner for
			
			totalEdges[v] = countEdges; 
		}//end for
			
		return totalEdges; 
	}
	
	/*
	 * Check Edges
	 * params: int num1, int num2 
	 * returns a boolean 
	 */
	public static boolean checkEdge(int num1, int num2) {
		
		for(Edge e: edges) {
			if((e.v1 == num1 && e.v2==num2) || (e.v1 == num2 && e.v2 == num1)) {
				System.out.println(" ");
				System.out.println("Im looping!");
				System.out.println("num1 " + num1 + " num2 " + num2);
				return true; 
			}//end if 
		}//end for
		
		return false; 
	}//end checkEdge
	
	static int counter = 0; 
	public static void addEdge(int v1, int v2) {
		
		counter++; 
		graph[v1].add(v2);
		graph[v2].add(v1);
	}//end add edge
	
	//Print 
	public static void print() {
		for(int v=0; v<numV; v++) {
			System.out.println("Vertex " + v);
			for(Integer n: graph[v]) {
				System.out.print(" -> " + n);
			}//end inner for
			System.out.println();
		}//end for
		
	}//end print
	
}//end class



class Edge{
	int v1, v2;
	public Edge(int v1, int v2) {
		this.v1=v1;
		this.v2=v2;
	}//end constructor
	public int getV1() {
		return v1;
	}//end get v1
	public int getV2() {
		return v2;
	}//end get v2
	
	public String toString() {
		return v1 + " is connected to " + v2;
	}
}//end class edge
