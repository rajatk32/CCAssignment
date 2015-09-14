package ch2;

public class Solution07 {
	/*
	 * A brute force solution could be to use two loops for each list
	 * but since it is not efficient O(n^2) we use a different approach.
	 * We could store the hashCode of a node object into a map and 
	 * find out easily if the hashCode appeared in the other list.
	 * 
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 * 
	 * We can still further optimize the space complexity by using another
	 * approach. Let's take an example of these two lists A and B:
	 * 
	 * A:       a1 -> a2
                        ->
                          c1 -> c2 -> c3
                        ->           
	   B: b1 -> b2 -> b3
	 * If we find the difference in the total number of nodes in each list
	 * which is 1 and skip those number of nodes from the beginning of 
	 * larger list and iterate both lists simultaneously, then at some node
	 * they will intersect at the same time.
	 * 
	 *  Time complexity: O(n)
	 *  Space complexity: O(1)
	 */
	
	private boolean intersect(Node l1, Node l2){
		if(l1==null || l2 == null)		// error case
			return false;
		
		Node curr1 = l1, curr2 = l2;	// backup l1 and l2 pointers
		int len1 = 0, len2 = 0;			// length of both lists
		
		while(curr1!=null){				// get length of list1
			len1++;
			curr1 = curr1.next;
		}
		
		while(curr2!=null){				// get length of list2
			len2++;
			curr2 = curr2.next;
		}
		
		curr1 = l1;						// reset curr1 and curr2 iteration pointers
		curr2 = l2;
		int diff = Math.abs(len1 - len2);	// get difference between lengths
		
		if(len1 > len2){				// if first list is bigger
			int i=0;
			while(i<diff){				// skip diff no. of nodes from beginning
				curr1 = curr1.next;
				i++;
			}
		}
		else if(len2 > len1){			// if second list is bigger
			int i=0;
			while(i<diff){				// skip diff no. of nodes from beginning
				curr2 = curr2.next;
				i++;
			}
		}
		
		while(curr1 != null && curr2!=null){	// until both lists are fully traversed
			if(curr1.hashCode() == curr2.hashCode())	// if the hash value of their nodes is equal
				return true;							// we have found an intersection
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		return false;							// otherwise return false if no intersection found
	}
	
	public static void main(String[] args) {
		Solution07 s7 = new Solution07();
		Node one = new Node(1);
		Node two = new Node(2);
		Node three = new Node(3);
		Node four = new Node(4);
		Node five = new Node(5);
		Node six = new Node(6);
		Node seven = new Node(7);
		Node eight = new Node(8);
		
		Node list1 = one;
		list1.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		
		Node list2 = six;
		list2.next = seven;
		seven.next = eight;
		eight.next = three;		// this is the intersection
		
		System.out.println(s7.intersect(list1, list2));
	}

}
