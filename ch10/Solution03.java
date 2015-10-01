package ch10;

/*
 * Since the array was sorted before rotating, intuitively we can
 * guess that we can apply binary search but this comes with a little
 * twist. Consider:
 * Array 1: {10, 15, 20, 0 , 5}
 * Array 2: {50, 5, 20, 30, 40}
 * 
 * Note that both arrays have a midpoint of 20 but 5 appear on the right
 * of Array1 and on the left of Array2. We see that one half of the array
 * is always ordered in increasing order. We can check which half it is
 * by comparing the left, mid and right pointers.
 * 
 * In Array 1, if we're searching for 5, we can compare the left=10, 
 * mid=20 and mid=20, right=5; since left<mid and mid!<right we can 
 * identify that left half is sorted but since the left half will 
 * not have 5, we move to the right half.
 * In Array 2, if we're searching for 5, we compare left=50, mid=20 and
 * mid=20, right=40; since left!<mid and mid<right we identify that right
 * half is sorted but since right half will not contain 5 we move to the
 * left half.
 * We can do this recursively until mid is the element we are searching.
 * 
 * There's another catch, what if the array contains a lot of duplicates.
 * Consider, {2,2,2,3,4,2}
 * Here all left, mid and right are equal. To solve situations like this:
 * 1. First find if left == mid
 * 	  1.1   If so, find if mid == right
 * 		  1.1.1   If no, search only the right half
 * 		  1.1.2   If yes, then there's no choice but we have to search both
 * 				  halves
 * 
 * Time complexity: O(n) in the worst case when there are many duplicates
 * 					O(nlogn) otherwise
 * Space complexity: O(1)
 */

public class Solution03 {
	
	public static int searchRotated(int[] a, int left, int right, int x){
		int mid = (left + right)/2;
		
		if(x==a[mid])			// if the element to be found is at mid
			return mid;			// return its position
		
		if(left>right)			// if left exceeds right
			return -1;			// return -1
		
		if(a[left] < a[mid]){				// if left half is sorted
			if(x>=a[left] && x<=a[mid])		// check if x is contained in left half
				return searchRotated(a, left, mid-1,x);
			else							// otherwise search in right half
				return searchRotated(a, mid+1, right,x);
		}
		else if(a[mid] < a[right]){			// if right half is sorted
			if(x>=a[mid] && x<=a[right])	// check if x is contained in right half
				return searchRotated(a,mid+1,right,x);
			else							// otherwise search left half
				return searchRotated(a,left,mid-1,x);
		}
		else if(a[left] == a[mid]){			// if there are duplicates in the left half
			if(a[mid] != a[right])			// if mid and right are different
				return searchRotated(a,mid+1,right,x);	// we can search in the right half
			else{							// otherwise there is no choice and we have to search both halves
				int result = searchRotated(a,left,mid-1,x);
				if(result == -1)
					result = searchRotated(a,mid+1,right,x);
				return result;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array = {2,2,2,3,4,2};
		
		System.out.println(searchRotated(array,0,array.length-1,4));

	}

}
