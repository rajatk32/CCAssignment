package ch10;

import java.util.Arrays;

/*
 * We can start at index 1 and keep doubling it up until we reach an 
 * element which is greater than the one we have to find as array is
 * in increasing order. We can then apply binary search from index/2
 * to index. index/2 is chosen because if we have doubled up then it
 * means that even when we were at index/2 the element was still less
 * than the value to be found.
 * 
 * Time complexity: O(logn)
 * Space complexity: O(1)
 */

class Listy {
	int[] array;
	
	public Listy(int[] arr) {
		array = arr.clone();
	}
	
	public int elementAt(int index) {
		if (index >= array.length) {
			return -1;
		}
		return array[index];
	}
}

public class Solution04 {
	
	public static int searchListy(Listy list, int value){
		int index = 1;
		while(list.elementAt(index) != -1 && list.elementAt(index) < value){
			index *= 2;
		}
		return Arrays.binarySearch(list.array, index/2, index, value);
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
		Listy list = new Listy(array);
		
		System.out.println(searchListy(list,1));
	}

}
