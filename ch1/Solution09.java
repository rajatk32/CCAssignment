package ch1;

public class Solution09 {
	
	private boolean isSubstring(String s1, String s2){	// checks if s1 contains s2
		return s1.contains(s2);
	}
	
	/*
	 * We are first going to check whether both s1 and s2 are of the same size
	 * If so, we concatenate s1 with itself and check whether s2 is a substring
	 * of s1
	 * For e.g. consider s1 = erbottlewat	s2 = waterbottle
	 * 			s1 + s1 = erbottlewaterbottlewat
	 * 			we can clearly see that waterbottle is a substring of s1+s1
	 * Time complexity: O(1)
	 * Space complexity: O(n)
	 */
	
	private boolean isRotation(String s1, String s2) {
		int len = s1.length();
		/* check that s1 and s2 are equal length and not empty */
		if (len == s2.length() && len > 0) {
			/* concatenate s1 and s1 within new buffer */
			String s1s1 = s1 + s1;
			return isSubstring(s1s1, s2);
		}
		return false;
	}
	
	public static void main(String[] args) {
		Solution09 s9 = new Solution09();
		System.out.println(s9.isRotation("erbottlewat", "waterbottle"));
	}

}
