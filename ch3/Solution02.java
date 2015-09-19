package ch3;

/*
 * Time complexity: O(1)
 * Space complexity: O(1)
 * for all methods     
 */

public class Solution02 extends Stack{	//we need to extend Stack
										//as we will be overriding
										//push and pop methods
	
	Stack minStack;					//keep a separate stack to hold
	//the min values

	Solution02(){
		minStack = new Stack();		//initialize the minStack's top to null
	}

	public void push(Object n){
		if(n==null)
			return;

		//assuming we are comparing integer values here
		//we cast the Object class to Integer to compare
		Integer valueToInsert = (Integer) n;
		//if minStack has at least one element & value inserted is less
		//than or equal to the top element of minStack
		if(minStack.top!=null && ( (Integer)minStack.top.n >= valueToInsert) ){
			minStack.push(n);		// push it to minStack     
		}

		if(minStack.top==null){	//for the first element
			minStack.push(n);	//place the first element in both minStack
								//and original stack
			//Since minStack is an object of Stack class
			//the push method from Stack class is called
			//here and not push method from StackWithMin
		}

		super.push(n);		//either way value gets pushed to the original 
							//stack
	}

	public Object pop(){
		Object elementPopped = super.pop();		// pop from the original stack
		if(elementPopped != null && minStack.top!=null 	// if the element popped is the top
				&& elementPopped==minStack.top.n){		// of minStack also
			minStack.pop();							// pop it from minStack
		}
		return elementPopped;
	}

	public Object min(){
		if(minStack.isEmpty())
			return Integer.MAX_VALUE;		//return the maximum integer
											//value to signify that minStack
											//is empty
		else
			return minStack.peek();			//return top of minStack
	}

	public static void main(String[] args) {
		Solution02 s2 = new Solution02();
		s2.push(new Integer(4));
		s2.push(new Integer(2));
		s2.push(new Integer(2));
		s2.push(new Integer(3));
		s2.push(new Integer(1));
		s2.push(new Integer(2));
		System.out.println("Minimum: "+s2.min());
	}

}
