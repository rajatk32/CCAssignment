package ch4;

public class Solution04 {
	/*
	 * We can solve this question using a top-down approach 
	 * in which we will calculate the depth of the left and
	 * right subtrees for each node. This causes unnecessary
	 * re-calculations and has a time complexity of O(n^2).
	 * 
	 * We can do better by using a bottom up approach using DFS
	 * in which if we know that the left or right subtree is 
	 * unbalanced we directly terminate any further processing.
	 * 
	 * Time complexity: O(n)
	 * Space complexity: O(H)		H -> height of tree     
	 * 
	 */
	
	public boolean isBalanced(TreeNode root) {
		return maxDepth(root) != -1;
	}
	private int maxDepth(TreeNode root) {
		if (root == null) return 0;		// base case
		int L = maxDepth(root.left); // this reaches the leftmost node in the tree
									// first
		if (L == -1) return -1;
		// if at any time the left subtree is found as
		// unbalanced return -1 right there to avoid
		// unnecessary calculations
		// do the same for right subtree:
		int R = maxDepth(root.right);
		if (R == -1) return -1;
		if (Math.abs(L - R) <= 1) return (Math.max(L, R) + 1);	// the if condition checks that the left and right subtrees
		else return -1;											// are balanced for each node; it returns the max depth if true and -1 otherwise
	}
	
	public static void main(String[] args) {
		Solution04 s4 = new Solution04();
		TreeNode root = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		root.left = two;
		root.right = three;
		three.right = four;
		
		System.out.println(s4.isBalanced(root));
	}

}
