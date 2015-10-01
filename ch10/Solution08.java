package ch10;

import java.util.BitSet;

/*
 * There are at most 32000 numbers or ints. 4KB of memory is
 * 4 * 1000 * 8 = 32000 bits, so we can store the occurence
 * of each number into a bit vector and see if there is a 
 * duplicate.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution08 {
	
	public static void findDuplicate(int[] array){
		BitSet bs = new BitSet(32000);
		for(int i=0;i<array.length;i++){
			int bitIndex = array[i] - 1;	// numbers start from 1 but bit index starts at 0
			if(bs.get(bitIndex) == true)
				System.out.println(array[i]);
			bs.set(bitIndex);
		}
	}
	
	public static void main(String[] args) {
		int[] array = new int[32000];
		int duplicate = 22100;
		
		for(int i=1;i<=32000;i++){
			array[i-1] = i;
		}
		
		array[45] = duplicate;	// set the duplicate at an arbitrary position
		
		findDuplicate(array);
	}

}
