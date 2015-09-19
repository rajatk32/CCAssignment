package ch3;

public class Solution01 {
	
	/*
	 * Assuming all of the stacks are of fixed size, we can allocate
	 * an initial array buffer of thrice the size of stacks and provide
	 * push, pop, peek and isEmpty methods for all three stacks independently
	 * Thus, the three stacks can hold elements at the following indices:
	 * Stack 1: [0, n/3)
	 * Stack 2: [n/3, 2n/3)
	 * Stack 3: [2n/3, n)
	 * where [ means inclusive and ) means exclusive.
	 * 
	 * Time complexity: O(1)
	 * Space complexity: O(1)
	 * for all methods     
	 * 
	 * Although not given here, if the question expected the stacks to be of
	 * variable length, we could expand the array buffer by keeping links
	 * between the array buffers created, which is similar to keeping the
	 * next pointer in linked lists.
	 */
	
	int stackSize = 300;		// say each stack can hold 300 elements
	int[] buffer = new int[stackSize * 3];	// we initialize an array buffer of
											// thrice the stack size	
	int[] stackPointer = { -1, -1, -1 };   // initially initialized to -1,
										// changed to topmost index subsequently
										// in the program; this tracks the top element
										// index in all 3 stacks
	
	void push(int stackNum, int value) { // stackNums start from 0
		if(stackPointer[stackNum] == stackSize)	// assuming when no more elements can be pushed
			return;								// to the stack (when it's full) we return
		int index = stackNum * stackSize + stackPointer[stackNum] + 1;
		// +1 is required because we start stackPointers from -1
		
		buffer[index] = value;
		stackPointer[stackNum]++;   // reflect the changed top position in
									// stackPointer too
	}

	int pop(int stackNum) {
		if(stackPointer[stackNum] == -1)	// assuming when the stack is empty
			return -1;						// we return -1.
		int index = stackNum * stackSize + stackPointer[stackNum]; // get the top position of the stack
		int value = buffer[index];
		buffer[index] = 0;
		stackPointer[stackNum]--;   // decrement one position from the stack
		return value;
	}

	int peek(int stackNum) {
		int index = stackNum * stackSize + stackPointer[stackNum]; // get the top position of the stack
		return buffer[index];
	}

	boolean isEmpty(int stackNum) {
		return stackPointer[stackNum] == -1;
	}
	
	public static void main(String[] args) {
		
		Solution01 s1 = new Solution01();
		for(int i=0;i<301;i++)
			s1.push(0, 1);
		s1.push(0, 1);
		System.out.println(s1.peek(0));
		System.out.println(s1.isEmpty(0));
		s1.pop(0);
	}

}
