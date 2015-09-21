package ch4;

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution03 {
	
	/*
	 * Suppose we have a tree like:
	 * 				 1
	 * 			   /   \
	 * 			  2     3
	 * 			 / \   / \
	 * 			4   5 6   7
	 * 
	 * The output should be:
	 * List 1: 1
	 * List 2: 2->3
	 * List 3: 4->5->6->7
	 * 
	 * We are going to use the datatype ArrayList<LinkedList<TreeNode>>
	 * to hold the result.
	 */
	
	public ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root){
		if(root == null)	// if root is null, nothing to process
			return null;
		LinkedList<TreeNode> nodesTraversed = new LinkedList<TreeNode>();	// this holds all nodes currently processed
		LinkedList<TreeNode> currLevelNodes = new LinkedList<TreeNode>();	// this is used to take out nodes which belong to only the current level
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();		// used to hold the final result
		nodesTraversed.addLast(root);	// we begin with adding the root to nodes traversed first
		int currLevel = 1;				// keeps track of the no. of nodes found in the current level
		int nextLevel = 0;				// keeps track of the no. of nodes in the next level
		while(!nodesTraversed.isEmpty()){	// until there are no more nodes
			TreeNode temp = nodesTraversed.peekFirst();		// create a backup of the current node
			if(temp.left != null){			// check if it has any left node, add it to nodesTraversed
				nodesTraversed.addLast(temp.left);	// and increment the nextLevel counter
				nextLevel++;
			}
			if(temp.right != null){			// check if it has any right node, add it to nodesTraversed
				nodesTraversed.addLast(temp.right);	// and increment the nextLevel counter
				nextLevel++;
			}
			currLevelNodes.add(nodesTraversed.pollFirst());		// take out the oldest node from current level
			currLevel--;										// decrement the currLevel counter
			
			if(currLevel == 0){				// if there are no more nodes in the current level
				currLevel = nextLevel;		// make currLevel = nextLevel
				nextLevel = 0;				// reset nextLevel
				result.add(currLevelNodes);	// add the current level nodes to result
				currLevelNodes = new LinkedList<TreeNode>();	// create a new linked list to hold nodes for the next iteration
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		root.left = two;
		root.right = three;
		three.right = four;
		
		Solution03 s3 = new Solution03();
		for(LinkedList<TreeNode> ll: s3.createLevelLinkedList(root)){
			System.out.println(ll.toString());
		}
	}

}
