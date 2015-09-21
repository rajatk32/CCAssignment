package ch4;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
	int data;
	TreeNode left, right;
	TreeNode(int n){
		left = null;
		right = null;
		data = n;
	}
	
	public String toString(){
		return Integer.toString(data);
	}
}

public class BinaryTree {
	TreeNode root;
	
	BinaryTree(){
		root = null;
	}
	
	//-------------------------------------------------------------//
	
	public void insert(int data){		//this function is used to call
										//an inner recursive function
										//which returns the root of the
										//modified tree after insertion
		
		this.root = insert(this.root, data);	//the root is reset here
	}
	
	private TreeNode insert(TreeNode root, int data){
		if(root == null){		//if BT is empty (base case)
			root = new TreeNode(data);
		}
		else{
			if(data <= root.data)
				root.left = insert(root.left, data);	//we need to set
													//the left & right
													//pointers here bcoz
													//if we only call the
													//function, the func.
													//returns a TreeNode
													//object which we have
													//to set to alter the tree
			else if(data > root.data)
				root.right = insert(root.right, data);
		}
		return root;
	}
	
	//-------------------------------------------------------------//
	
	public boolean lookup(int data){	//this function is created to
										//call the recursive function
										//lookup so that we don't have
										//pass the root of the tree
										//every time
		return lookup(this.root, data);
		
	}
	
	//Tip: In a recursive function all variables which are changed in
	//recursive calls must be passed as parameters in the function
	
	private boolean lookup(TreeNode root, int data){	
										//returns true if data exists
										//in the tree
		if(root==null)
			return false;
		if(root.data == data)			//base case (data is found at
			return true;				//root only)
		else if(data <= root.data)
			return lookup(root.left, data);
		else if(data > root.data)
			return lookup(root.right, data);
		
		//otherwise
		return false;
	}
	
	//-------------------------------------------------------------//
	
	public int getDepth(){
		return getDepth(this.root);
	}
	
	private int getDepth(TreeNode root){
		if(root==null)		//base or minimal case (tree has no depth)
			return 0;
		
		//increment depth by 1 and go deeper to find the max depth
		//as depths for left and right sub-trees might be different
		
		else return 1 + Math.max(getDepth(root.left), getDepth(root.right));
	}
	
	//-------------------------------------------------------------//
	
	public TreeNode delete(int data){
		return delete(this.root, data);
	}
	
	private TreeNode delete(TreeNode root, int data){
		if(root==null)		//null pointer handling
			return null;
		
		if(data < root.data)						//traverse till we
			root.left = delete(root.left, data);	//reach the root
		else if (data > root.data)					//where root.data
			root.right = delete(root.right, data);	//is = data, and 
													//also set the 
													//appropriate left
													//or right subtree
													//with that returned
		
		// Notice that we are not checking for equality in the above
		// conditions because when data is equal to root.data, we
		// want to execute the below statements bcoz it means we
		// have found a match of the node to delete:
		
		//Case 1 : if root has no children set it to null
		
		if(root.left==null && root.right==null && root.data==data)
			root = null;
		
		//Case 2 : if either left/right is null set root's left/right
		//subtree as the root accordingly
		
		else if(root.left!=null && root.right==null && root.data==data)
			root = root.left;
		else if(root.left==null && root.right!=null && root.data==data)
			root = root.right;
		
		//Case 3 : if root has both children
		
		else if(root.left!=null && root.right!=null && root.data==data){
			TreeNode previous = null, current = root.left;
			while(current.right!=null){		//find the largest element
				previous = current;			//in the left subtree
				current = current.right;
			}
			//We keep a track of previous bcoz the largest element which
			//is rightmost element of the left subtree may have a left
			//pointer pointing to an element which is smaller than it.
			//To remove this largest element we point the right pointer
			//of previous node to largest element's left
			
			root.data = current.data;	//move largest element to root
										//to delete root
			
			if(previous==null)				//if previous was not set
				root.left = current.left;	//it means largest element
											//was at root.left only (in
											//other words, while loop
											//didn't run) and that it
											//had no right pointer
											//so we can safely set its
											//left as root's left
			
			else							//if largest element was found
				previous.right = current.left;	//at deeper levels
		}
		return root;
	}
	
	//-------------------------------------------------------------//
	
	//Breadth First Traversal Pseudocode:
	
	/*
	  levelorder(root)
	  q = empty queue
	  q.enqueue(root)
	  while not q.empty do
	    node := q.dequeue()
	    visit(node)
	    if node.left != null then
	      q.enqueue(node.left)
	    if node.right != null then
	      q.enqueue(node.right)
	 */
	
	public void printBFTraversal() {		//because we can solve this
		TreeNode root = this.root;			//problem with iteration 
		int currentLevel = 1;				//(instead of recursion), we
		int nextLevel = 0;					//do not need a wrapper
											//function that pulls out the
											//top-level node pointer
		
		// currentLevel keeps track of the total no. of nodes in the current
		// level; nextLevel keeps track of the total no. of nodes in the next
		// level  
		
		Queue<TreeNode> q=new LinkedList<TreeNode>();
		if(root==null)
			return;
		else
			q.add(root);
		
		while(!q.isEmpty()){		//till all nodes have been processed
			
			if(q.peek().left != null){
				q.add(q.peek().left);
				nextLevel++;
			}
			if(q.peek().right != null){
				q.add(q.peek().right);
				nextLevel++;
			}
			
			System.out.print(q.remove().data+" ");
			currentLevel--;
			
			if(currentLevel==0){	// i.e. all nodes in te currentLevel
									// are processed?
				currentLevel = nextLevel;
				nextLevel=0;
				System.out.println();
			}
		}
	}
	
	//-------------------------------------------------------------//
	
	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree();
		bt.insert(50);
		bt.insert(17);
		bt.insert(76);
		bt.insert(23);
		bt.insert(19);
		bt.insert(9);
		bt.insert(14);
		bt.insert(12);
		bt.insert(54);
		bt.insert(72);
		bt.insert(67);
		System.out.println(bt.lookup(2));
		System.out.println(bt.getDepth()+"\n");
		bt.printBFTraversal();
		
		/*output will be:
		
		false
		5

		50 
		17 76 
		9 23 54 
		14 19 72 
		12 67 

		
		*/ 
	}

}