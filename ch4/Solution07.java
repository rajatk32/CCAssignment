package ch4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/* Question: Build Order: You are given a list of projects and a list of dependencies (which 
 * is a list of pairs of projects, where the first project is dependent on the second project). 
 * All of project's dependencies must be built before the project is. Find a build order that
 * will allow the projects to be built. If there is no valid build order, return an error.
 * 
 * EXAMPLE:
 * Input: 
 * 		projects: a, b, c, d, e, f
 * 		dependencies: (d,a), (b,f), (d,b), (a,f), (c,d)
 * Output: f, b, a, d, e, c
 * 
 * This question is based on the topological sort algorithm of directed graphs.
 * 
 * The pseudocode for which is:
 * Reference: Slide 19 in http://courses.cs.washington.edu/courses/cse326/03wi/lectures/RaoLect20.pdf
 * 
 * 1. Store each vertex's in-degree (no. of incoming arcs) in an array
 * 2. Initialize a queue which contains all nodes whose in-degree is zero and mark them as visited
 *    so that they are not picked up again
 * 3. While there are vertices remaining in this queue:
 *    i. Dequeue and output a vertex
 *    ii. Reduce in-degree of all vertices adjacent to it by 1 in indegree array
 *    iii. Now, check if some new vertices have indegree zero in the indegree array, enqueue them 
 *    	   and mark them as visited
 * 
 * Note: Marking the nodes as visited is crucial otherwise the while loop runs indefinitely
 * 
 * At the end of topological sort, we will have an order where nodes with lesser in-degrees
 * will appear before those with higher in-degrees (dependencies). For this question, thus, we
 * need to reverse the order we get on applying topological sort algo i.e. nodes with higher
 * dependencies should appear first.
 * 
 * Time complexity analysis:
 * Initializing in-degree array: O(E)
 * Initializing queue with in-degree 0 vertices: O(V)
 * Dequeuing vertices: V vertices each one of them takes O(1) time to dequeue so total is O(V)
 * Reducing in-degree of adjacent vertices: O(E)
 * 
 * Total time complexity is O(|V| + |E|)
 * Space complexity is O(|V| + |E|)
 */

public class Solution07 {
	
	public void buildOrder(String projects, String dependencies){
		String[] proj = projects.split(", ");	// we will need to find the individual vertices
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();	// since our graph data structure takes only integers
																					// as data of vertices, we are using a hashmap to find out
																					// which char belongs to which integer
		char[] result = new char[proj.length];	// this will hold the final project build order
		int resultIdx = result.length - 1;		// the result array will be filled from back
		
		for(int i=0;i<proj.length;i++){			// this assigns each char a unique integer index
			charMap.put(proj[i].charAt(0), i);	// starting from 1
		}
		
		GraphAdjList gr = new GraphAdjList(0);	// add the vertices to the graph
		for(int i=1;i<proj.length;i++){
			gr.addNode(i);
		}
		
		String[] depy = dependencies.split(", ");	// dependencies stores the edge information b/w vertices
		// initialize an array with in-degree of all vertices
		int[] indegree = new int[proj.length];
		
		for(int i=0;i<depy.length;i++){				// we are going to create the edge connections b/w vertices
			char from = depy[i].charAt(1);			// gets the from and to vertices that need to be connected
			char to = depy[i].charAt(3);
			
			GraphNode fromNode = gr.nodes.get(charMap.get(from));	// since we need a GraphNode object for the vertices
			GraphNode toNode = gr.nodes.get(charMap.get(to));		// we fetch this information from the previously added
																	// nodes in graph
			gr.addEdge(fromNode, toNode, 0);		// here we make the edge finally
			indegree[charMap.get(to)] += 1;			// at the same time we also increment the in-degrees for the vertices
		}

		Queue<GraphNode> queue = new LinkedList<GraphNode>();		// this queue holds the vertices whose in-degrees are zero
		boolean isCyclic = true;					// there can be graphs that are cyclic; cyclic graphs are graphs which have no
													// vertices whose in-degrees are zero
		for(int i=0;i<indegree.length;i++){		// add all nodes whose in-degree is zero to the queue and set their state as Visited
			if(indegree[i] == 0){
				isCyclic = false;		// if there is at least one vertex whose in-degree is zero, set isCyclic to false
				queue.add(gr.nodes.get(i));
				gr.nodes.get(i).state = State.Visited;
			}
		}
		
		if(isCyclic == true){			// if the graph is cyclic the topological sorting can't be obtained
			throw new IllegalArgumentException("No build order can be obtained with given project dependencies!");
		}
		
		while(!queue.isEmpty()){		// until there are nodes left whose in-degree is zero
			GraphNode temp = queue.poll();	// dequeue one vertex from graph
			result[resultIdx--] = proj[temp.data].charAt(0);	// store this node's data in the final result array
			
			LinkedList<Edge> adjacents = gr.returnAdjacents(temp);	// get its adjacent nodes
			for(int i=0;i<adjacents.size();i++){		// decrement in-degree of all its adjacent nodes by 1
				int index = adjacents.get(i).to.data;
				indegree[index]-=1;
			}
			
			for(int i=0;i<indegree.length;i++){			// check the in-degree array again to see if there
				GraphNode curr = gr.nodes.get(i);		// are any new nodes whose in-degree became zero
				if(indegree[i] == 0 && curr.state!=State.Visited){
					queue.add(curr);					// enqueue them to queue
					curr.state = State.Visited;			// and mark them as Visited
				}
			}
		}
		System.out.println(Arrays.toString(result));
	}
	
	public static void main(String[] args) {
		String projects = "a, b, c, d, e, f";
		String dependencies = "(d,a), (b,f), (d,b), (a,f), (c,d)";	// we assume that the inputs are properly formatted
		
		Solution07 s7 = new Solution07();
		s7.buildOrder(projects, dependencies);
	}

}
