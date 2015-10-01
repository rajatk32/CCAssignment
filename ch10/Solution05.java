package ch10;

/*
 * This is similar to binary search, the modification to be done is
 * that since we need to find a mid that actually contains a value, 
 * if the string at mid is empty, we move mid to the nearest non-empty
 * value.
 * 
 * Time complexity: O(n) (if all strings are empty in array)
 * Space complexity: O(1)
 */

public class Solution05 {
	
	public static int findString(String val, String[] strs, int low, int high)
	{
		if(low > high)
			return -1;
		int mid = (low+high)/2;
		if(strs[mid].isEmpty())		// if mid is empty
		{							// create two pointers left and right; we're not using low and high because
									// we want to keep their values intact for later use
			int left = mid - 1;		// set left to the index just before mid
			int right = mid + 1;	// set right to the index just after mid
			while(true)				// keep running this loop, we break when we find the first non-empty string on either left or right
			{
				if(left < low && right > high)		// if bounds are exceeded
					return -1;
				else if(left >= low && !strs[left].isEmpty())	// if left is still valid and is not empty
				{
					mid = left;									// set mid to left and break loop
					break;
				}
				else if(right <= high && !strs[right].isEmpty())	// else if right is still valid and is not empty
				{
					mid = right;								// set mid to right and break loop
					break;
				}
				left--;			// otherwise keep decrementing left and incrementing right
				right++;
			}
		}
		if(val.compareTo(strs[mid]) < 0)		// if val is less than mid
			return findString(val, strs, low, mid-1);	// find val in left half
		else if(val.compareTo(strs[mid]) > 0)	// else if val is greater than mid
			return findString(val, strs, mid+1, high);	// find val in right half
		else									// else they both are equal
			return mid;							// we have found the index of val
	}
	
	public static void main(String[] args) {
		String[] strings = {"at","","","","ball","","","car","","","dad","",""};
		System.out.println(findString("ball", strings,0,strings.length-1));		
	}

}
