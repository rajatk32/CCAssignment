package ch3;

class GenericNode {
	Object n;
	GenericNode next;

	GenericNode(Object data) {
		this.n = data;
		this.next = null;
	}
}

public class Stack {
	
	GenericNode top;

	public Stack() {
		top = null;
	}

	public void push(Object n) {
		GenericNode node = new GenericNode(n);
		if (top == null) {
			top = node;
			return;
		}
		node.next = top;
		top = node;
	}

	public Object pop() {
		if (top == null)
			return null;
		Object item = top.n;
		top = top.next;
		return item;
	}

	public Object peek() {
		if (top == null)
			return null;

		return top.n;
	}

	public boolean isEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	public void printStack() {
		GenericNode current = this.top;
		if (current == null)
			return;

		while (current.next != null) {
			System.out.print(current.n + "->");
			current = current.next;
		}
		System.out.println(current.n);
	}
	
}
