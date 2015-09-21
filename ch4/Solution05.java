package ch4;

import java.util.LinkedList;

public class Solution05 {
	/*
	 * A brute force solution will be to check that a node’s left subtree contains nodes which are lesser or
	 * equal in value and the right subtree contains nodes which are greater in value and this has to be
	 * repeated for each node making the algorithm O(n^2).
	 * 
	 * We can avoid examining all nodes of both subtrees in each pass by passing down the low and
	 * high limits from the parent to its children. This will take O(n) time and O(n) space.
	 * This solution has the limitation that we can only check it for a specific data type, int in
	 * this case which has a definite range. If one of the nodes falls outside, int's range, the code
	 * will not work as expected.
	 */
	
	private boolean isValidBST(TreeNode node, int minValue, int maxValue){
		if(node == null) // if node is null it means that we have
			return true; // successfully traversed till leaf node
		// without any violation, so return true
		// check for violations i.e. values outside of the
		// minValue..maxValue range (inclusive):
		if(node.data<minValue || node.data>maxValue)
			return false;
		return isValidBST(node.left,minValue,node.data) && 	  // both left & right
				isValidBST(node.right,node.data+1,maxValue); // subtrees should
															// return true
	}
	
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	/*
	 * Another solution is to do an in-order traversal of the binary tree, and verify that its inorder
	 * elements follow a strict monotonic increasing order. We are going to store the inorder traversal of the tree
	 * in a list and then check whether it is monotonically increasing. If it is, then it is a valid BST else it's not.
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 * 
	 */
	
	LinkedList<Integer> inorderList;	// this holds the tree in-order
	
	Solution05(){
		inorderList = new LinkedList<Integer>();
	}
	
	public void inorder(TreeNode root){		// this method populates the in-order list
		if(root == null)
			return;
		inorder(root.left);
		inorderList.add(root.data);
		inorder(root.right);
	}
	
	public boolean isValidBSTinOrder(TreeNode root) {
		inorder(root);			// create the in-order list first
		boolean flag = true;	// return value
		for(int i=1;i<inorderList.size();i++){		// iterate through the inorder list
			if(inorderList.get(i) <= inorderList.get(i-1))	// if a subsequent element is found to be less than the previous one
				flag = false;								// set flag to false
		}
		return flag;			// return result
	}

	
	public static void main(String[] args) {
		Solution05 s5 = new Solution05();
		TreeNode root = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		root.left = two;
		root.right = three;
		three.right = four;
		
		System.out.println(s5.isValidBST(root));
	}

}
