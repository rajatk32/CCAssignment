package ch5;

/*
 * If AND of two numbers is 0, it means that they don't have any bits in common.
 * 
 * Let's take simple examples to find out what it means for n AND n-1 == 0
 * 
 * n   = 8 : 1000
 * n-1 = 7 : 0111 
 * 
 * n   = 7 : 0111
 * n-1 = 6 : 0110
 * 
 * n   = 6 : 0110
 * n-1 = 5 : 0101
 * 
 * n   = 5 : 0101
 * n-1 = 4 : 0100
 * 
 * n   = 4 : 0100
 * n-1 = 3 : 0011
 * 
 * n   = 3 : 0011
 * n-1 = 2 : 0010
 * 
 * n   = 2 : 0010
 * n-1 = 1 : 0001
 * 
 * From the above examples, we can clearly see that n & (n-1) == 0 
 * is valid for n = 2, 4, 8,.. we can infer from this that n & (n-1)
 * == 0 checks if n is a power of 2 (or 0)
 */

public class Solution05 {

	public static void main(String[] args) {

		
	}

}
