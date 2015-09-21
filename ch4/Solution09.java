package ch4;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Time complexity: O(N^2)
 * Space complexity: O(N)
 */

public class Solution09 {
	
	public static void createLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		
		if (first.size() == 0 || second.size() == 0) {	// if one of the lists is empty
			LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();	// copy prefix
			result.addAll(first);
			result.addAll(second);
			results.add(result);		// add it to final list
			return;
		}
		
		int headFirst = first.removeFirst();	// take out the head of first list
		prefix.addLast(headFirst);				// add it in prefix list
		createLists(first, second, results, prefix);	// do this recursively
		prefix.removeLast();					// remove the last from prefix
		first.addFirst(headFirst);
		
		/* Do the same thing with second list*/
		int headSecond = second.removeFirst();
		prefix.addLast(headSecond);
		createLists(first, second, results, prefix);
		prefix.removeLast();	
		second.addFirst(headSecond);
	}
	
	public static ArrayList<LinkedList<Integer>> sequences(TreeNode node) {
		ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		
		if (node == null) {		// if node is null, create an empty list and return it
			result.add(new LinkedList<Integer>());
			return result;
		} 
		
		LinkedList<Integer> prefix = new LinkedList<Integer>();
		prefix.add(node.data);		// combinations are created based on this prefix
		
		/* Recursively traverse both left and right subtrees */
		ArrayList<LinkedList<Integer>> leftSeq = sequences(node.left);
		ArrayList<LinkedList<Integer>> rightSeq = sequences(node.right);
		
		/* Find the combinations from left and right lists */
		for (LinkedList<Integer> left : leftSeq) {
			for (LinkedList<Integer> right : rightSeq) {
				ArrayList<LinkedList<Integer>> lists = new ArrayList<LinkedList<Integer>>();
				createLists(left, right, lists, prefix);
				result.addAll(lists);
			}
		}
		return result;
	}

	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.insert(2);
		bt.insert(1);
		bt.insert(3);
		
		ArrayList<LinkedList<Integer>> allSeq = sequences(bt.root);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
	}

}
