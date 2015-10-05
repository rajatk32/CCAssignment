package ch5;

/*
 * We are going to check the least significant bit of the number given
 * in each iteration and then bit shift the number to right by one.
 * In other words, we are going to check the bits of the number beginning
 * from the back.
 * At the same time we are going to keep track of whether we have found
 * a zero or not. There will be two variables left1 and right1 to count
 * how many 1s are there to the left and right of the last zero found.
 * As soon as we find another zero in the number, we calculate the 
 * length of the longest sequence of 1s we could make for the last zero
 * found and replace maxLength if it less than the current length. Now 
 * the left1 of the previous zero will be the right1 of the next zero
 * so we swap right1 with left1 and reset left1 to 0.
 * 
 * For e.g. Consider 11011101111
 * 
 * 1. Beginning with the LSB, since the bit is 1, we increment right1=1
 * 2. We keep incrementing right1 till the four 1's from the right are found
 *    so right1=4
 * 3. Now we have found a 0 so we set flag zeroFound=true; this means we found
 *    our first zero.
 * 4. Next we process the next three 1's to increment left1=3
 * 5. We again find a 0; At this point, we need to calculate the longest length
 * 	  we found and reset the left1 and right1 counters. So the current longest 
 *    length will be 3+4+1 = 8 ; After this the left1 of previous zero will be
 *    right1 of the next zero we found, so right1 = left1 and since we don't 
 *    know yet how many 1's are there to the left of the next zero we found, 
 *    we reset left1=0.
 * 6. The algorithm is continued in this way until the number itself becomes zero
 *    i.e. we processed all 1 bits contained in the number.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution03 {

	public static void main(String[] args) {
		int n = 1775;
		int left1=0, right1=0, currLength=0, maxLength=0, x;
		boolean zeroFound = false;
		while(n != 0){
			x = n & 1;
			if(x==1 && zeroFound == false){
				right1++;
			}
			else if(x==0 && zeroFound == false){
				zeroFound = true;
			}
			else if(x==1 && zeroFound == true){
				left1++;
			}
			else if(x==0 && zeroFound == true){
				currLength = left1 + right1 + 1;
				if(currLength > maxLength)
					maxLength = currLength;
				right1 = left1;
				left1 = 0;
			}
			n = n >> 1;
		}
		System.out.println(maxLength);
	}

}
