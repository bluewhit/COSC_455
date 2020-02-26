import java.util.*;

public class Application { 
	static int numV, numE;
	static LinkedList<Integer> graph[];
	static ArrayList<Edge> edges; //for testing
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
		
		int rNum, rNum2, maxEdges, minEdges;
		maxEdges = ((numV-1) * numV) /2;
		minEdges = numV;
		numE = rand.nextInt(maxEdges - minEdges) + minEdges; //create random number of edges 
		System.out.println(numE);
		for(int i=0; i<numE; i++) {
			
			do {//the while check if the edge already exists. Sometimes edges get duplicated it seems.
				rNum=rand.nextInt(numV);
				rNum2=rand.nextInt(numV);				
				
				while(rNum==rNum2) {
					rNum=rand.nextInt(numV);
				}//end while. Make sure we arent connecting the node to itself
			} while(checkEdge(rNum, rNum2));//while loop checks if edge exists
			
			edges.add(new Edge(rNum, rNum2)); //add our edge to edges so we can loop through edges in check edge
			addEdge(rNum, rNum2); //actually add the edge to the graph
			
		}//end populate graph
		print();
		System.out.println("TRAVERSE");
		DFS(0);
	}//end main
	
	public static boolean checkEdge(int num1, int num2) { //return true if edge exists 
		//Doesnt work with smaller vertices i think
		for(Edge e: edges) {
			if((e.v1==num1 && e.v2== num2) || (e.v1==num2 && e.v2== num1)) {
				System.out.println("WHY?");
				return true;
			}//end if else chain
		}//end for
		return false;
	}//end checkEdge
	
	public static void addEdge(int v1, int v2) { //add edge to the graph
		graph[v1].add(v2); //at vertex1 add an edge to vertex 2
		graph[v2].add(v1); //since its unweighted, also add an edge from vertex 2, to vertex 1
	}//end add edge
	
	public static void print() { //print our graph
		for(int v=0; v<numV; v++) {
			System.out.println("Vertex " + v); //get our column
			for(Integer n: graph[v]) {
				System.out.print(" -> " + n); //each N is a node connected to the vertex V
			}//end inner for
			System.out.println();
		}//end for
	}//end print
	
	
	
	
	
	
	 // A function used by DFS 
    static void DFSUtil(int v,boolean visit[]) 
    { 
        // Mark the current node as visited and print it 
        visit[v] = true; 
        System.out.print(v+" "); 
  
        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> i = graph[v].listIterator(); 
        while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visit[n]) 
                DFSUtil(n, visit); 
        } 
    } 
  
     // The function to do DFS traversal. It uses recursive DFSUtil() 
    public static void DFS(int v) 
    { 
        // Mark all the vertices as not visited(set as 
        // false by default in java) 
        boolean visited[] = new boolean[numV]; 
  
        // Call the recursive helper function to print DFS traversal 
        DFSUtil(v, visited); 
    } 
	
	
	
	
	
	
	
	
	
	
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
	}//toString to print edges
}//end class edge
