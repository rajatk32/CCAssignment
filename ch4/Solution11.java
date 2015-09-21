package ch4;

import java.util.ArrayList;
import java.util.Random;

/*
 * Time complexity: O(n)
 * Space complexity: O(n)
 */

class NodeTree{
	int data;
	NodeTree left, right;
	NodeTree(int n){
		left = null;
		right = null;
		data = n;
	}
	
	public String toString(){
		return Integer.toString(data);
	}
}

class TreeBinary {
	NodeTree root;
	int size;
	
	TreeBinary(){
		root = null;
		size = 0;
	}
	
	//-------------------------------------------------------------//
	
	public void insert(int data){		//this function is used to call
										//an inner recursive function
										//which returns the root of the
										//modified tree after insertion
		
		this.root = insert(this.root, data);	//the root is reset here
		this.size++;
	}
	
	private NodeTree insert(NodeTree root, int data){
		if(root == null){		//if BT is empty (base case)
			root = new NodeTree(data);
		}
		else{
			if(data <= root.data)
				root.left = insert(root.left, data);//we need to set
													//the left & right
													//pointers here bcoz
													//if we only call the
													//function, the func.
													//returns a NodeTree
													//object which we have
													//to set to alter the tree
			else if(data > root.data)
				root.right = insert(root.right, data);
		}
		return root;
	}
	
	//-------------------------------------------------------------//
	
	public boolean find(int data){		//this function is created to
										//call the recursive function
										//lookup so that we don't have
										//pass the root of the tree
										//every time
		return find(this.root, data);
		
	}
	
	private boolean find(NodeTree root, int data){	
										//returns true if data exists
										//in the tree
		if(root==null)
			return false;
		if(root.data == data)			//base case (data is found at
			return true;				//root only)
		else if(data <= root.data)
			return find(root.left, data);
		else if(data > root.data)
			return find(root.right, data);
		
		//otherwise
		return false;
	}
	
	//-------------------------------------------------------------//
	
	public NodeTree delete(int data){
		return delete(this.root, data);
	}
	
	private NodeTree delete(NodeTree root, int data){
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
			NodeTree previous = null, current = root.left;
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
		this.size--;
		return root;
	}
	
	//-------------------------------------------------------------//
	
	public NodeTree getRandomNode(){
		Random random = new Random();
		int rand = random.nextInt(this.size);	// generate random no. between 0 and size
		ArrayList<NodeTree> list = new ArrayList<NodeTree>();	// create an array list which will hold all
																// nodes of the tree
		getRandomNode(this.root, list);			// recursive function to go through all nodes in DFS order
		return list.get(rand);					// return the node at rand index in list
	}
	
	private void getRandomNode(NodeTree root, ArrayList<NodeTree> list) {
		if(root == null)	// if root is null
			return;			// terminate here
		getRandomNode(root.left, list);		// recursively go through left subtree
		getRandomNode(root.right, list);	// recursively go through right subtree
		list.add(root);		// add root to list
	}
	
}

public class Solution11 {

	public static void main(String[] args) {
		
		TreeBinary bt = new TreeBinary();
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
		
		bt.delete(19);
		System.out.println(bt.find(19));
		System.out.println(bt.getRandomNode());
	}

}
