package ch10;

/*
 * We could use an array and keep it in sorted order as we insert
 * but the insertion will not be efficient since we will have to
 * shift elements.
 * Instead we can use a binary tree to store the integers in which
 * insertion will take O(logn) time. We will keep track of the 
 * size of the left subtree in a counter so that we don't have to
 * count it every time we need to get rank of a number in the
 * right half of the tree.
 * 
 * Time complexity: O(logn)
 * Space complexity: O(1)
 */

public class Solution10 {
	
	private static RankNode root = null;

	public static void track(int number) {
		if (root == null) {
			root = new RankNode(number);
		} else {
			root.insert(number);
		}
	}

	public static int getRankOfNumber(int number) {
		return root.getRank(number);
	}
	
	public static void main(String[] args) {
		int size = 10;
		for(int i=1;i<=10;i++){
			track(i);
		}
		
		System.out.println(getRankOfNumber(4));
	}

}
