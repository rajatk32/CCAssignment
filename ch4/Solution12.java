package ch4;

import java.util.ArrayList;

/*
 * Pseudocode:
 * 1. Keep a temporary variable which is initialized to sum and a 
 *    temporary buffer to store the paths
 * 2. Traverse the tree normally and add current node's value to the
 *    temporary buffer
 * 3. At each pass check that if we subtract the node values of all nodes
 *    added in the buffer (going from bottom to top) till now, can
 *    we get tmp=0. If so, print only these nodes. If not, keep 
 *    searching in the buffer till its beginning.
 * 4. Make two copies of this buffer to pass the paths traversed till
 *    now to the left and right recursion functions.
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 */

public class Solution12 {
	
	void findSum(TreeNode root, int sum, ArrayList<Integer> buffer, 
			int level) {	//variable level is required so that we know
							//how many nodes have been added in the 
							//buffer till now. This is utilized in the
							//print function
		
		if (root == null)
			return;
		int tmp = sum;	//sum's orginal value is never changed and tmp is
						//always initialized to the original sum value
		
		buffer.add(root.data);		//add the node value along the path
		
		//since we have the path in the buffer and there are currently
		//'level' no. of nodes (bcoz at each level we add one node),
		//start from the end of the buffer and move up to the starting
		//to check if the path becomes equal to the sum at any point;
		//if it does print the path only up to that point (not beyond
		//that)
		
		for (int i = level; i > -1; i--) {
			tmp -= buffer.get(i);
			if (tmp == 0)
				print(buffer, i, level);
		}
		
		//c1 and c2 copy the paths traversed till now up to root (current
		//node) and pass these paths to the left and right subtrees bcoz
		//both will render a different path after processing & we don't
		//want to alter the original path buffer as that will lead to 
		//wrong output
		
		ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
		ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
		
		//sum's value shouldn't be changed bcoz if findSum for left(below)
		//changes the value of sum, the changed value will be passed to
		//the findSum for right thus, changing the original sum that is
		//required and resulting in incorrect output
		
		findSum(root.left, sum, c1, level + 1);
		findSum(root.right, sum, c2, level + 1);
	}
	
	void print(ArrayList<Integer> buffer, int from, int to) {
		for (int i = from; i <= to; i++) {
			System.out.print(buffer.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.insert(15);
		bt.insert(5);
		bt.insert(3);
		bt.insert(12);
		bt.insert(10);
		bt.insert(13);
		bt.insert(6);
		bt.insert(7);
		bt.insert(16);
		bt.insert(20);
		bt.insert(18);
		bt.insert(23);
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		int sum=23;
		
		Solution12 so12 = new Solution12();
		so12.findSum(bt.root,sum, path, 0);
		
		/* Output:
		   15 5 3 
		   10 6 7 
		   23 
		*/
	}

}
