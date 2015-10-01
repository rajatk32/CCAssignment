package ch10;

import java.util.Arrays;

/*
 * We can first sort the array and then swap each alternating
 * element in the array.
 * Time complexity: O(nlogn)
 * Space complexity: O(1)
 */

public class Solution11 {
	
	public static void sortValleyPeak(int[] array) {
		Arrays.sort(array);
		for (int i = 1; i < array.length; i += 2) {
			swap(array, i - 1, i);
		}
	}

	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void main(String[] args) {
		int[] array = {48, 40, 31, 62, 28, 21, 64, 40, 23, 17};
		sortValleyPeak(array);
		System.out.println(Arrays.toString(array));
	}

}
