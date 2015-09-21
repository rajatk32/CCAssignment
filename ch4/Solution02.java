package ch4;

public class Solution02 {
	
	/*
	 * A Tree with minimal height will be a balanced tree.
	 * Algorithm:
		1. Insert into the tree the middle element of the array.
		2. Insert (into the left subtree) the left subarray elements
		3. Insert (into the right subtree) the right subarray elements
		4. Apply recursion to repeat this
	 */
	
	public TreeNode createBST(int[] array){
		return addToTree(array, 0, array.length - 1);
	}
	
	private TreeNode addToTree(int[] array, int start, int end){
		if(start > end)		// do this only till start is greater than end
			return null;
		
		int mid = (start+end)/2;	// find the middle index
		TreeNode root = new TreeNode(array[mid]);	// make the middle element as root
		root.left = addToTree(array, start, mid-1);	// repeat the process for left sub-half
		root.right = addToTree(array, mid+1, end);	// repeat the process for right sub-half
		return root;
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7};
		Solution02 s2 = new Solution02();
		TreeNode root = s2.createBST(array);
		BinaryTree bt = new BinaryTree();
		bt.root = root;
		bt.printBFTraversal();
	}

}
