package ch4;

//we're creating a Directed Weighted Graph here

import java.util.ArrayList;
import java.util.LinkedList;

enum State {
    UnVisited, Visited, Processed;
}

class GraphNode {

    public int data;
    public State state;

    public GraphNode(int d) {
        this.data = d;
        this.state = State.UnVisited;
    }
   
}

class Edge implements Comparable<Edge>{
	GraphNode from;
	GraphNode to;
	int weight;
	
	Edge(GraphNode from, GraphNode to, int weight){
		this.from=from;
		this.to=to;
		this.weight=weight;
	}
	
	//this is used for PriorityQueue in Dijkstra's algorithm
	public int compareTo(Edge obj1){
		return this.weight - obj1.weight;	//this - anotherObject is ASC
										   //anotherObject - this is DESC
	}
	
}

public class GraphAdjList {
	GraphNode root;
	ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
	ArrayList<LinkedList<Edge>> adjList = new ArrayList<LinkedList<Edge>>();
	
	GraphAdjList(int data){
		this.root = new GraphNode(data);
		nodes.add(root);
		adjList.add(new LinkedList<Edge>());
	}
	
	public void addNode(int data){
		GraphNode node = new GraphNode(data);
		nodes.add(node);
		adjList.add(new LinkedList<Edge>());
	}
	
	public void addEdge(GraphNode from, GraphNode to, int weight){
		int idxFrom = nodes.indexOf(from);
		int idxTo = nodes.indexOf(to);
		if(idxFrom == -1 || idxTo == -1)
			throw new IllegalArgumentException("Node not found");
		Edge edge = new Edge(from, to, weight);
		adjList.get(idxFrom).add(edge);
	}
	
	public void removeNode(GraphNode node){
		int idxNode = nodes.indexOf(node);
		if(idxNode == -1)
			throw new IllegalArgumentException("Node not found");
		nodes.remove(idxNode);
		adjList.remove(idxNode);
	}
	
	public void removeEdge(GraphNode fromNode, GraphNode toNode){
		int idxFromNode = nodes.indexOf(fromNode);
		if(idxFromNode == -1)
			throw new IllegalArgumentException("Node not found");
		
		LinkedList<Edge> edges = returnAdjacents(fromNode);
		Edge edgeToBeRemoved=null;
		
		for(Edge e:edges){
			if(e.to.equals(toNode))
				edgeToBeRemoved = e;
		}
		if(edgeToBeRemoved == null)
			throw new NullPointerException("Edge not found for the given node");
		else
			adjList.get(idxFromNode).remove(edgeToBeRemoved);
	}
	
	public LinkedList<Edge> returnAdjacents(GraphNode node){
		if(node==null)
			return null;
		int idxNode = nodes.indexOf(node);
		if(idxNode == -1)
			throw new IllegalArgumentException("Node not found");
		
		return adjList.get(idxNode);
	}
	
	public void printGraph(){
		for(GraphNode node:nodes){
			System.out.print(node.data+"-->");
			LinkedList<Edge> llist = returnAdjacents(node);
			for(Edge edge:llist){
				System.out.print(edge.to.data + "(" + edge.weight + "),");
			}
			System.out.println();
		}
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
	}

}
