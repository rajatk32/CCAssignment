package ch4;

import java.util.HashSet;

public class Solution08 {

	/* We begin with the root and check its left and right subtrees to find
	both node1 and node2; simultaneously we also change the root node as
	we dig down deeper so that only the least common ancestor is chosen
	Time complexity: O(n) since we would be scanning all nodes once in
						  the worst case
	Space complexity: O(n)
  */
	
	public TreeNodeWithParent commonAncestor(TreeNodeWithParent root,
			TreeNodeWithParent node1, TreeNodeWithParent node2) {
		
		TreeNodeWithParent left=null, right=null;
		
		if(root==null)		//no root no common ancestor
			return null;
		
		if(root==node1 || root==node2)		//return node1 or node2
			return root;					//if they are found, to
											//set left & right below
		
		//change root & search node1 and node2 in left subtree till leaf
		//node
		//for example: left becomes 3 and root becomes 5 on reaching here
		left = commonAncestor(root.left, node1, node2);
		
		//change root & search node1 and node2 in right subtree till leaf
		//node
		//for example: root is 5 when code reaches here (see above)
		right = commonAncestor(root.right, node1, node2);
		
		if(left!=null && right!=null)	//if both left & right are set
			return root;				//node1 & node2 are on left/right
										//of root node so return root as
										//ancestor
		
		else if(left!=null)		//if left is set and right is still null
			return left;		//it means both node1 & node2 are in the
								//left subtree so return left as ancestor
		
		else if(right!=null)	//if left is still null and right is set
			return right;		//it means both node1 & node2 are in the
							 //right subtree so return right as ancestor
		
		return null;
	}	
	
	/* When parent is given, we can trace the path up to the root for each
	   * node and store it in a HashSet. Later we can intersect these
	   * HashSets to find the common nodes in their path and pick the first
	   * one as the common ancestor
	   * Time complexity = O(h)
	   * Space complexity = O(h)
	   * where h is height of the tree
	   */
	
	public TreeNodeWithParent commonAncestorWithParent(TreeNodeWithParent
			root, TreeNodeWithParent node1, TreeNodeWithParent node2) {
	HashSet<TreeNodeWithParent> set1 = new HashSet<TreeNodeWithParent>();
	HashSet<TreeNodeWithParent> set2 = new HashSet<TreeNodeWithParent>();
		
		while(node1!=null){		// store all paths till root node i.e.
			set1.add(node1);	// move upwards from node1
			node1=node1.parent;
		}
		while(node2!=null){		// store all paths till root node i.e.
			set2.add(node2);	// move upwards from node1
			node2=node2.parent;
		}
		
		set1.retainAll(set2);	//replace set1 with intersection of set1
								//& set2
		
		for(TreeNodeWithParent n:set1){
			return n;			//return first node from set if it exists
		}
		
		return null;			//else return null
	}
	
	public static void main(String[] args) {
		BinaryTreeParent bt = new BinaryTreeParent();
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
		
		Solution08 s8 = new Solution08();
		
		//common ancestor of 3 and 7
		System.out.println(s8.commonAncestor(bt.root, bt.root.left.left,
				bt.root.left.right.left.left.right).data);
		//Output: 5
		
	}

}
