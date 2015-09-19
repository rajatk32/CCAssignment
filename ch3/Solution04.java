package ch3;

import java.util.Stack;

/*
 * Let's say we have a queue:
 * 
 * rear->	4->3->2->1	<-front
 * 
 * When we dequeue, elements are taken out from front
 * and when we enqueue, elements are added at the rear
 * 
 * Suppose we want to replicate this behaviour with two stacks s1 and s2
 * which are initially both empty
 * 
 * 1. We first push all elements to s1, so s1 becomes
 * 		1->2->3->4	<-top
 * 2. Now if we want to dequeue, the element 1 should be taken out from front of queue
 * 3. We can do this by taking out the top element from s1 and pushing it to s2
 *    until s1 becomes empty; so s2 will then be:
 *    	4->3->2->1	<-top
 * 4. The element 1 can then be popped from s2 if we want to dequeue
 * 
 * So, in a way if we want to enqueue we use stack s1 and if we want to dequeue, we first
 * check if s2 is empty; if s2 is empty we push all elements from s1 to s2, otherwise we
 * simply remove the top element from s2.
 * In other words, we could say that s1's top pointer keeps track of the queue's rear
 * and s2's top pointer keeps track of the queue's front.
 * 
 * The basic logic for enqueue and dequeue is as follows:
 * 
 * Enqueue:
 * 1. Push everything to s1
 * 
 * Dequeue:
 * 1. If both stacks are empty, return error
 * 2. If s2 is empty push everything from s1 to s2
 * 3. pop s2 and return it
 * 
 * Time complexity: O(1) for enqueue, peekRear and size methods
 * Time complexity: O(n) for dequeue and peekFront methods
 * Space complexity: O(n)
 */

public class Solution04<T> {
	
	Stack<T> s1, s2;
	
	Solution04(){			// initialize the two stacks when default constructor is invoked
		s1 = new Stack<T>();
		s2 = new Stack<T>();
	}
	
	public void enqueue(T value){	// push the parameter value to s1
		s1.push(value);
	}
	
	public T dequeue(){
		if(s1.isEmpty() && s2.isEmpty())	// if both stacks are empty, there is nothing to dequeue
			return null;					// return null
		if(!s2.isEmpty())	return s2.pop();	// if s2 is not empty, pop its top element
		while(!s1.isEmpty())	s2.push(s1.pop());	// otherwise pop all elements from s1 and push to s2
		return s2.pop();					// pop the topmost element from s2
	}
	
	public T peek(){		// returns the element at front
		if(s1.isEmpty() && s2.isEmpty())	// this has similar logic to dequeue but we peek instead of popping
			return null;
		if(!s2.isEmpty())	return s2.peek();
		while(!s1.isEmpty())	s2.push(s1.pop());
		return s2.peek();
	}
	
	public int size(){	// returns the total number of elements in queue
		return s1.size() + s2.size();
	}
	
	public static void main(String[] args) {
		Solution04<Integer> s4 = new Solution04<Integer>();
		s4.enqueue(1);
		s4.enqueue(2);
		s4.enqueue(3);
		s4.enqueue(4);
		System.out.println("Peek = "+s4.peek());
		s4.dequeue();
		System.out.println("Peek = "+s4.peek());
	}

}
