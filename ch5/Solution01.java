package ch5;

/*
 * Suppose n = 11000011000 and m = 10101
 * If we have to insert m in n from bit i=2 to j=6,
 * where bit positions begin from 0
 * the expected output will be: 11001010100
 * 
 * We need to first clear any set bits in n from 
 * i=2 to j=6. For this follow the below steps:
 * 
 * 1. (1 << j) = 1000000
 * 2. (1 << j) - 1 = 111111
 * 3. Let max = ~0 which is all 32 bits set to 1
 * 4. Subtract expression in Step 2 from max:
 *    max - ((1 << j) - 1) = 11111111111111111111111111000000
 * 5. Do the same for i, i.e. (1 << i) = 100
 * 6. (1 << i) - 1 = 11
 * 7. OR the two results obtained:
 *    11111111111111111111111111000000 | 11 = 11111111111111111111111111000011
 * 8. AND the expression in Step 7 with n to clear bits from i=2 to j=6 in n
 *    n & mask = 11000000000
 * 
 * Now, shift m by i bits to left to get:
 * m<<i = 1010100
 * 
 * AND m<<i with n to get the result: 11001010100
 * 
 * Time complexity: O(1)
 * Space complexity: O(1)
 */

public class Solution01 {

	public static void main(String[] args) {
		int i=2,j=6;
		 int n=Integer.parseInt("11000011000", 2);
		 int m=Integer.parseInt("10101", 2);
		 int max = ~0; /* All 1’s */
		// 1’s through position j, then 0’s
		 int left = max - ((1 << j) - 1);
		
		 // 1’s after position i
		 int right = ((1 << i) - 1);
		
		 // 1’s, with 0s between i and j
		 int mask = left | right;
		
		 // Clear i through j, then put m in there
		 System.out.println(Integer.toBinaryString((n & mask) | (m << i)));

	}

}
