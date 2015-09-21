package ch4;

/*consider this tree:

				   ;:,;                             
				  : 15 :                            
				  :::.:  
				/	      \
		 ,,::                :::,                   
		,: 5 ;              : 16 :                  
		 ;:;:                ;,;.                   
	  /         \                    \                 
 ;:,:;             :::                  :::,          
,, 3 ;            , 12 :               ; 20 :         
 ;:;;              ;::;                 ;,;.          
		        /       \             /       \ 
			 :::,       :::,       ,::,       ,::,    
			: 10 :     : 13 :     : 18 :     ; 23 :   
			 :;::       ::;'       ,::;        ;:;
		  /
	,:::,                                         
	: 6 :                                         
	::::'                                         
		  \                                         
			 ,::;,                                     
			 : 7 :                                    
			 '::;'             

Inorder traversal: 3 5 6 7 10 12 13 15 16 18 20 23

Pseudocode:
There are two cases here:

1. The node has a right subtree.
Find the leftmost node in the right subtree. If there is no left node
return the right node in the right subtree.

2. The node does not have a right subtree.
Two cases arise here as well:
(a) if the current node is a left node of its parent, return its 
parent
(b) if the current node is a right node of its parent, check if its
parent is a left node of its grandparent and return grandparent,
else iterate upwards in this fashion

*/

//creating the classes required to include a parent element with each node

class TreeNodeWithParent{
	TreeNodeWithParent left, right, parent;
	int data;
	TreeNodeWithParent(int data){
		left=null;
		right=null;
		parent=null;
		this.data = data;
	}
}

class BinaryTreeParent{
	TreeNodeWithParent root;
	BinaryTreeParent(){
		root = null;
	}
	public void insert(int data){
		this.root = insert(this.root, data, null);
	}
	
	private TreeNodeWithParent insert(TreeNodeWithParent root, int data,
			TreeNodeWithParent parent) {
		if(root == null){
			TreeNodeWithParent newnode = new TreeNodeWithParent(data);
			if(parent!=null){
				newnode.parent = parent;
			}
			root = newnode;
		}
		else{
			if(data <= root.data)
				root.left = insert(root.left, data, root);
			else if(data > root.data)
				root.right = insert(root.right, data, root);
		}
		return root;
	}
}
//end of helper classes required to include parent node


public class Solution06 {
	
	public TreeNodeWithParent inOrderSucc(TreeNodeWithParent node){
		if(node==null)
			return null;
		
		if(node.right!=null){			//if node has a right sub-tree
			node = node.right;			//find the left-most node
			while(node.left != null){	//in the right subtree
				node=node.left;
			}
			return node;
		}
		
		if(node.right == null){			//if no right sub-tree exists
			if(node.parent.left == node)	//if node is a left node
				return node.parent;
			else{						//else if node is a right node
				TreeNodeWithParent grandparent = node.parent.parent;
				TreeNodeWithParent current = node.parent;
				while(grandparent.left != current){		// unless we find that the parent is a left node of grandparent
					current = grandparent;				// replace current with grandparent
					grandparent = current.parent;		// and grandparent with its parent to move up the tree and continue iteration
					if(grandparent == null)				// if we have traversed up fully but couldn't find such a node
						break;							// break loop
				}
				return grandparent;
			}
		}
		
		return null;
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
		
		Solution06 s6 = new Solution06();
		System.out.println(s6.inOrderSucc(bt.root.right).data);
		//successor of 16
		//Output: 18
	}

}
