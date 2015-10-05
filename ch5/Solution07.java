package ch5;

/*
 position  : 76543210
 number    : 10001101
 output    : 01001110

 mask(even): 01010101 or 0x55(to keep even bits intact)
 mask(odd) : 10101010 or 0xaa(to keep odd bits intact)

 since even bits start at 0th position, we shift to left for even, and
 since odd bits end at 31st bit, we shift to right for odd

 Pseudocode:
 1. Create a mask such that only even bits remain, AND it with the
    original number and shift the bits left to move these bits to odd
    positions.
 2. Similarly, create a mask such that only odd bits remain, AND it  
    with the original number and shift the bits right to move these bits
    to even positions.
 3. OR the results from 1 & 2 to get the final output.

 Time complexity: O(1)
 Space complexity: O(1)
*/

public class Solution07 {
	
	public static int oddEvenBits(int n){
		int maskOddIntact = (0xaaaaaaaa & n)>>1;
		int maskEvenIntact = (0x55555555 & n)<<1;
		
		return maskOddIntact | maskEvenIntact;
	}
	
	public static void main(String[] args) {
		
		int n = Integer.parseInt("10001101", 2);
		System.out.println("Original No. "+n);
		System.out.println("No. in binary  "+Integer.toBinaryString(n));
		System.out.println("EvenOddSwapped "
				+ Integer.toBinaryString(oddEvenBits(n)));
		
	}

}