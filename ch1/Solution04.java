package ch1;

public class Solution04 {
	/*
	 * palindromePermutation function checks that at most there can
	 * be only 1 character that is not repeated. All other characters
	 * are repeated in even numbers. We are going to keep an int charMap
	 * in which we are going to toggle its value from 0-1 and 1-0 denoting
	 * that the character was encountered odd no. of time or even no. of time
	 * respectively. We also keep a variable count which is
	 * incremented/decremented in the same way. If count<=1, we can say
	 * that there is a palindrome permutation for this string.
	 * Assumption: the string belongs to ASCII character set and the palindrome
	 * 			   permutation evaluation is case-insensitive
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 * 
	 * The same idea can also be applied to Unicode charset by using a HashMap
	 * in which case space complexity will increase to O(n).
	 */
	
	private boolean palindromePermutation(String st){
		if(st==null || st.trim().equals(""))	// assuming for null and empty string
			return false;						// the function returns false
		st = st.toLowerCase();
		int[] charMap = new int[256];
		int count = 0;
		for(int i=0;i<st.length();i++){
			if(st.charAt(i) == ' ')				// we ignore space character
				continue;
			int index = (int) st.charAt(i);
			if(charMap[index] == 0){
				charMap[index] = 1;
				count++;
			}
			else if(charMap[index] == 1){
				charMap[index] = 0;
				count--;
			}
		}
		return count<=1;
	}
	
	public static void main(String[] args) {
		Solution04 s4 = new Solution04();
		System.out.println(s4.palindromePermutation("Tact Coa"));
	}

}
