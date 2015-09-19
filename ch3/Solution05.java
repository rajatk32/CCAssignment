package ch3;

import java.util.Stack;

/*
 * Say the given stack is s1 which has elements in random order
 * and we are allowed to use another stack s2 which is empty in
 * the beginning. Since the output stack has to have the smallest
 * element on top, the largest element should be at the bottom.
 * From s1, we can keep pushing elements to s2 until s1 becomes 
 * empty. At the same time we also need to make sure that if 
 * s2.top < s1.top, s1.top should be pushed below s2.top. Since 
 * this can happen for multiple values in s2 in succession, we 
 * need to have a loop for s2 to check this condition.
 * 
 * The way this is done is by popping s1.top in a temp variable,
 * popping all values < s1.top from s2 and pushing them to s1. Now
 * push temp to s2 and repeat this process until s1 becomes empty.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 */

public class Solution05 {
	
	public Stack<Integer> sortStack(Stack<Integer> s1){
		Stack<Integer> s2 = new Stack<Integer>();	// create one other stack
		
		while(!s1.isEmpty()){			// until all elements from original stack s1 are processed
			int temp = s1.pop();		// backup top of s1
			while(!s2.isEmpty() && s2.peek() < temp){	// until s2 contains more elements and s1.top > s2.top
				s1.push(s2.pop());						// pop element from s2 and move it to s1
			}
			s2.push(temp);				// at the end push s1's top which was retrieved before onto s2 and repeat the process     
		}
		
		return s2;
	}
	
	public static void main(String[] args) {
		Solution05 s5 = new Solution05();
		Stack<Integer> st = new Stack<Integer>();
		st.push(3);
		st.push(4);
		st.push(6);
		st.push(2);
		st.push(1);
		st.push(5);
		System.out.println(s5.sortStack(st) + "  <-top");
	}

}
