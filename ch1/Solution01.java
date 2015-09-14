package ch1;

import java.util.HashMap;

public class Solution01 {
	
	/*
	 * allUniqueUnicode function checks that a string
	 * has all unique unicode characters, thus it handles a broad
	 * range of characters
	 * This function can also be used for ASCII characters
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	
	private boolean allUniqueUnicode(String ch){
		if(ch==null || ch.equals(""))		// assuming if the string is null
			return false;					// or empty we return false
		HashMap<Character,Boolean> map = new HashMap<Character, Boolean>(ch.length());	// create a hashmap which stores
		for(char c:ch.toCharArray()){													// the characters read from the string
			if(!map.containsKey(c))		// if the character is not already added in map, add it
				map.put(c, true);
			else if(map.containsKey(c))	// else immediately return false if a duplicate is found
				return false;
		}
		return true;				// if we have read the whole string and did not find any duplicates, return true
	}
	
	/*
	 * allUniqueLowerAlphabets function works assuming that the string
	 * passed as input contains only lowercase alphabets a-z
	 * From a-z we have only 26 possibilities, so we can use a 32-bit
	 * integer and toggle its bits at appropriate position to check
	 * whether we have encountered the same character in the string
	 * or not
	 * Note: this method does not use any additional data structures, 
	 * 		 only an integer data type is used
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 */
	
	private boolean allUniqueLowerAlphabets(String str){
		int check = 0;		// we choose 0 because in the starting
							// we want all bits to be 0
		for (int i = 0; i < str.length(); ++i) {	// read the chars in string
			int val = str.charAt(i) - 'a';	// get the offset of the char from 'a'
			// in the statement below, 1<<val sets the bit at val position
			// we need to AND it with check to see if the bit at that position is 
			// already set; if it is set we immediately return false as we found
			// a duplicate:
			if ((check & (1 << val)) > 0) return false;
			// otherwise if we set the bit in check variable as well using OR
			check = check | (1 << val);
		}
		return true;	// if we have read the whole string and did not find any duplicates, return true
	}
	
	public static void main(String[] args) {
		Solution01 s1 = new Solution01();
		System.out.println(s1.allUniqueUnicode("अआइईउ"));	// test for unicode chars
		System.out.println(s1.allUniqueLowerAlphabets("abcda"));	// test for all lowercase chars
	}

}
