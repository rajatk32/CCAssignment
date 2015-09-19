package ch3;

import java.util.ArrayList;
import java.util.Stack;

/*
 * Time complexity: O(1) amortized for push, pop and popAt operation since ArrayList is used
 * Time complexity: O(1) for isEmpty and peek operations
 * Space complexity: O(n) * 
 */

public class Solution03 extends Stack<Integer>{	// Stack needs to be extended as we will override
												// push, pop and peek methods to fit this problem description     
	private final int STACK_CAPACITY = 5;	// let each stack have a fixed size of 5 elements
	private ArrayList<Stack<Integer>> setOfStacks = new ArrayList<Stack<Integer>>();	// we can use an ArrayList to keep track of
																						// which stack comes after/before which one
	
	Solution03(){								// when the default constructor is invoked
		Stack<Integer> st = new Stack<Integer>();	// initialize an empty stack
		setOfStacks.add(st);						// and add it to set of stacks
	}
	
	public Integer push(Integer in){			// override the push method of Stack
		int currStack = setOfStacks.size() - 1;	// get the topmost/latest stack's index
		Stack<Integer> temp;					// points to the stack where elements are to be pushed
		int returnValue;
		if(setOfStacks.get(currStack).size() == STACK_CAPACITY){	// if the current stack is already full
			temp = new Stack<Integer>();		// create a new empty stack
			setOfStacks.add(temp);				// add it to set of stacks
		}
		else{									// otherwise point temp to the current stack
			temp = setOfStacks.get(currStack);
		}
		returnValue = temp.push(in);			// push to the appropriate stack
		return returnValue;						// return the value pushed
	}
	
	public Integer pop(){						// override pop method of Stack
		int currStack = setOfStacks.size() - 1;	// get the topmost/latest set index
		if(currStack < 0)						// if currStack is less than 0, it means that the set of stacks
			return null;						// has no stacks added in it (all values were popped)
		if(currStack == 0 && setOfStacks.get(currStack).isEmpty())	// if there is one stack only and it is empty
			return null;									// return null as it cannot be popped
		Stack<Integer> temp = setOfStacks.get(currStack);	// otherwise get the current stack's index
		int returnValue = temp.peek();				// backup its integer value at top
		if(temp.size() == 1){						// if current stack has only one element remaining
			setOfStacks.remove(currStack);			// we need to remove this stack from the set of stacks too
		}
		else{										// otherwise
			temp.pop();								// simply pop from it
		}
		return returnValue;
	}
	
	public Integer popAt(int stackNo){							// stackNo index starts from 0
		if(stackNo < 0 || stackNo > (setOfStacks.size() - 1) )	// if the stackNo is invalid
			return null;										// return null
		if(stackNo == 0 && setOfStacks.get(stackNo).isEmpty())	// if there is one stack only and it is empty
			return null;									// return null as it cannot be popped
		Stack<Integer> temp = setOfStacks.get(stackNo);	// otherwise get the pointer to stack at index stackNo
		int returnValue = temp.peek();				// backup its integer value at top
		if(temp.size() == 1){						// if current stack has only one element remaining
			setOfStacks.remove(stackNo);			// we need to remove this stack from the set of stacks too
		}
		else{										// otherwise
			temp.pop();								// simply pop from it
		}
		return returnValue;
	}
	
	public boolean isEmpty(){
		return setOfStacks.size() == 0 ? true : setOfStacks.get(0).isEmpty();		// if the set of stacks doesn't contain any stack, return true
																					// else check if the first stack is empty
	}
	
	public Integer peek(){							// override peek method of Stack
		int currStack = setOfStacks.size() - 1;		// get the topmost/latest stack's index
		if(currStack < 0)							// if there are no stacks in set of stacks
			return -1;								// return -1;
		return setOfStacks.get(currStack).peek();	// else return the topmost element in the current stack
	}
	
	public static void main(String[] args) {
		Solution03 s3 = new Solution03();
		System.out.println("Current total no. of stacks = "+ s3.setOfStacks.size());
		for(int i=0;i<s3.STACK_CAPACITY;i++)
			s3.push(i+1);
		System.out.println("Total no. of stacks after adding "+ s3.STACK_CAPACITY + " elements: " + s3.setOfStacks.size());
		s3.push(6);
		System.out.println("Total no. of stacks after adding "+ (s3.STACK_CAPACITY+1) + " elements: " + s3.setOfStacks.size());
		s3.popAt(1);
		System.out.println("Total no. of stacks after removing one element (using popAt): " + s3.setOfStacks.size());
		for(int i=0;i<s3.STACK_CAPACITY;i++)
			s3.pop();
		System.out.println("Total no. of stacks after removing all elements: " + s3.setOfStacks.size());
	}

}
