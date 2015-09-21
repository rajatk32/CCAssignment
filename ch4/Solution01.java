package ch4;

import java.util.LinkedList;
import java.util.Stack;

/* 
 * We are going to do a simple DFT (depth first traversal of the graph
 * starting from the node given and mark the adjacent nodes when they are 
 * visited so that if there is no path from an adjacent node to the required
 * node, we don't repeat searching from there again.
 * Time complexity: O(V + E)
 * Space complexity: O(V)
 */

public class Solution01 {
	
	public boolean isRouteAvailable(GraphAdjList graph, GraphNode 
			from, GraphNode to){
		if(graph==null || from==null || to==null)	//if any param is
			return false;							//null, return false
		if(from==to)		//if from and to are same, return true
			return true;
		
		Stack<GraphNode> s = new Stack<GraphNode>();	//for DFT
		GraphNode temp;		//stores Stack pop
		LinkedList<Edge> adjacents = new LinkedList<Edge>();
		
		s.push(from);	//push from to Stack
		from.state = State.Visited;		//mark from's status as visited
		
		while(!s.isEmpty()){	//while Stack is not empty	
			temp = s.pop();		//pop from Stack
			if(temp == to)		//if popped element is same as to
				return true;	//return true
			else{											//else
				adjacents = graph.returnAdjacents(temp);	//find its
				for(Edge e:adjacents){						//adjacents
					GraphNode node = e.to;				//loop through
					if(node.state == State.UnVisited){	//them if they're
						if(node == to)				//Unvisited & push on
							return true;			//to stack if they're
						else{						//not equal to 'to'
							node.state = State.Visited;
							s.push(node);
						}
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		GraphAdjList gal = new GraphAdjList(1);
		for(int i=2;i<=8;i++)
			gal.addNode(i);
		
		gal.addEdge(gal.nodes.get(0), gal.nodes.get(1),10);	//connect 1 to 2
		gal.addEdge(gal.nodes.get(0), gal.nodes.get(2),20);	//connect 1 to 3
		gal.addEdge(gal.nodes.get(0), gal.nodes.get(3),30);	//connect 1 to 4
		gal.addEdge(gal.nodes.get(1), gal.nodes.get(4),40);	//connect 2 to 5
		gal.addEdge(gal.nodes.get(1), gal.nodes.get(5),50);	//connect 2 to 6
		gal.addEdge(gal.nodes.get(3), gal.nodes.get(6),60);	//connect 4 to 7
		gal.addEdge(gal.nodes.get(3), gal.nodes.get(7),65);	//connect 4 to 8
		gal.addEdge(gal.nodes.get(5), gal.nodes.get(4),70);	//connect 6 to 5
		gal.addEdge(gal.nodes.get(6), gal.nodes.get(0),75);	//connect 7 to 1
		gal.addEdge(gal.nodes.get(6), gal.nodes.get(2),80);	//connect 7 to 3
		gal.addEdge(gal.nodes.get(6), gal.nodes.get(5),90);	//connect 7 to 6
		gal.addEdge(gal.nodes.get(7), gal.nodes.get(5),95);	//connect 8 to 6
		
		gal.printGraph();
		
		Solution01 grbn = new Solution01();
		boolean result = grbn.isRouteAvailable(gal, gal.nodes.get(7),
				gal.nodes.get(5));		// checks whether node 8 has a path to node 6
		
		System.out.println(result);
	}

}
