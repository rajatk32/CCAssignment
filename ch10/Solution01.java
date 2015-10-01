package ch10;

import java.util.Arrays;

public class Solution01 {
	
	/*
	 * Assuming length of a1 is > a2, we are going to insert elements in a1
	 * from the back because if we do it from front, we will have to shift the
	 * elements on the right which will make our solution O(n^2).
	 * 
	 * Time complexity: O(n)	where n is the length of shorter array
	 * Space complexity: O(1)
	 */
	
	public static void merge(int[] a1, int[] a2, int indexLastA, int indexLastB) {
		int mergeAtIndex = a1.length - 1;	// insert from the end
	
		while (indexLastB >= 0) {			// until we fully traverse the shorter array
			if (indexLastA >= 0 && a1[indexLastA] > a2[indexLastB]) {	// whichever array's
				a1[mergeAtIndex] = a1[indexLastA]; 				// element is bigger, insert
				indexLastA--; 									// that element to the end
			} else {
				a1[mergeAtIndex] = a2[indexLastB]; 
				indexLastB--;
			}
			mergeAtIndex--; 				// decrement index to insert at
		}
	}
	
	public static void main(String[] args) {
		int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
		int[] b = {1, 4, 7, 6, 7, 7};
		merge(a, b, 7, 5);
		System.out.println(Arrays.toString(a));
	}

}
