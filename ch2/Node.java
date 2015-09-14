package ch2;

public class Node {
	int n;
	Node next;
	int size;
	
	Node(int data) {
		this.n = data;
		this.next = null;
	}
	
	public int length(){
		return this.size;
	}
	
	public void add(int data) {
		Node n = new Node(data);
		if (this == null)
			return;
		Node current = this;
		while (current.next != null) { // stop after reaching the last node
			current = current.next;
		}
		current.next = n; 		// associate last node with the new node
		size++;
	}
	
	public void printLList() {
		if (this == null)
			return;
		Node current = this;
		while (current.next != null) {
			System.out.print(current.n + "->");
			current = current.next;
		}
		System.out.println(current.n); // print the last node whose next value
										// is null
	}
}
