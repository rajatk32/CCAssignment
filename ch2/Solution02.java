package ch2;

public class Solution02 {
	
	/*
	 * we are going to use two pointers, left and right
	 * right pointer is advanced to n-1 position to the right from head
	 * and we then keep advancing left and right pointers by one position
	 * till we reach the last node; now the node at left pointer is the answer 
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 */
	
	private void nthToLastLL(Node head, int n){
		if(head==null || n<1)
			return;
		
		Node left=head, right=head;
		
		//increment right pointer by n-1 positions
		for(int i=1;i<n;i++){
			if(right==null)			//not found since list size < n
				return;
			right=right.next;
		}
		try{
			while(right.next!=null){	//reach the last node and stop there
				left=left.next;
				right=right.next;
			}
		} catch(Exception e){
			return;
		}
		System.out.println(n + "th element to the last of LL: "+ left.n);
	}
	
	public static void main(String[] args) {
		Solution02 s2 = new Solution02();
		Node head=new Node(1);
		head.add(2);
		head.add(3);
		head.add(4);
		head.add(5);
		System.out.print("Original list is: ");
		head.printLList();
		s2.nthToLastLL(head, 2);
	}

}
