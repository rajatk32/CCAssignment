package ch1;

/* 
 * Assume X represents the one-edit character. There are three one-edit
 * distance operations that could be applied to S.
 * i. Modify operation – Modify a character to X in S.
 * S = "abcde"
 * T = "abXde"
 *
 * ii. Insert operation – X was inserted before a character in S.
 * S = "abcde"
 * T = "abcXde"
 *
 * iii. Append operation – X was appended at the end of S.
 * S = "abcde"
 * T = "abcdeX"
 *
 * We make a first pass over S and T simultaneously and stop at the first
 * non-matching character between S and T to check these conditions.
 */

/*
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution05 {
	public boolean isOneEditDistance(String s, String t) {
		int m = s.length(), n = t.length();
		if (m > n) return isOneEditDistance(t, s);
		if (n - m > 1) return false;
		int i = 0, shift = n - m;
		while (i < m && s.charAt(i) == t.charAt(i))
			i++; // loop until the smaller string is fully iterated and all
				// characters match
		if (i == m)
			return shift > 0; 	// if the full smaller string is iterated and all
								// characters matched, check if only 1 char was
								// appended
			if (shift == 0)
				i++; // if the above while loop got broken in between and shift is
					// 0, then increment i and keep iterating
			while (i < m && s.charAt(i) == t.charAt(i + shift))
				i++; // this while loop holds for both modify and insert operations
					// notice how we have added shift to index of T
			return i == m; 	// at last check that all elements have been iterated
							// through and return a corresponding boolean value
	}
	
	public static void main(String[] args) {
		Solution05 s5 = new Solution05();
		System.out.println(s5.isOneEditDistance("abcde", "abcxde"));
	}
	
}