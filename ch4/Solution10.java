package ch4;

/*
 * We could be careful of the space we use here since one of the trees 
 * is huge. A quick idea that pops up is to convert both trees to their
 * inorder traversal string but this can cause us to hit memory limitations.
 * 
 * Suppose T1 > T2, a better approach here is to first find the root value
 * of T2 in T1. Only when we find it, do we compare each node of T2 with T1
 * 
 * Time complexity: O(n*m)
 * Space complexity: O(m)	where n > m
 */

public class Solution10 {
	
	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true; // The empty tree is a subtree of every tree
		}
		return subTree(t1, t2);	// else call subTree which is another recursive method
	}
	
	/* Checks if the binary tree rooted at r1 contains the binary tree 
	 * rooted at r2 as a subtree somewhere within it. */
	
	public static boolean subTree(TreeNode r1, TreeNode r2) {
		if (r1 == null) {	// big tree empty & subtree still not found
			return false; 
		} else if (r1.data == r2.data && matchTree(r1,r2)) {	// if the roots match, compare subtrees of t1 & t2
			return true;
		}
		return subTree(r1.left, r2) || subTree(r1.right, r2); 	// else go to the left and right nodes of t1 and repeat the process
	}

	/* Checks if the binary tree rooted at r1 contains the 
	 * binary tree rooted at r2 as a subtree starting at r1. */

	public static boolean matchTree(TreeNode r1, TreeNode r2) {
		if (r1 == null && r2 == null) {		// both trees fully traversed
			return true;					// which means all nodes matched
		} else if (r1 == null || r2 == null) {	// if one of trees is exhausted
			return false;
		} else if (r1.data != r2.data) { 	// if at any point nodes don't match
			return false;					// return false
		} else {							// else keep matching the left and right subtrees
			return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
		}
	}
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(5);
		TreeNode two1 = new TreeNode(2);
		TreeNode one1 = new TreeNode(1);
		TreeNode three1 = new TreeNode(3);
		TreeNode six1 = new TreeNode(6);
		
		t1.left = two1;
		two1.left = one1;
		two1.right = three1;
		t1.right = six1;
		
		TreeNode t2 = new TreeNode(2);
		TreeNode one2 = new TreeNode(1);
		TreeNode three2 = new TreeNode(3);
		
		t2.left = one2;
		t2.right = three2;
		
		System.out.println(containsTree(t1,t2));
	}

}
