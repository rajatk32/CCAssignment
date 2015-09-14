package ch1;

import java.util.Arrays;

public class Solution02 {
	
	/*
	 * isPermutationUsingSort checks whether two strings are permutations
	 * of each other or not by first sorting both the strings and then
	 * verifying whether they both are equal
	 * For e.g. if we have abc and cab as the two strings, if we sort them
	 * 			both will become abc and then we can just check their equality
	 * Time complexity: O(nlogn)
	 * Space complexity: O(logn) because quicksort is used
	 */
	
	private boolean isPermutationUsingSort(String str1, String str2){
		if(str1==null || str2==null || str1.length()!=str2.length())	// error cases
			return false;
		char[] s1 = str1.toCharArray();	// we need to convert the strings to char arrays as using
		char[] s2 = str2.toCharArray();	// Arrays.sort requires arrays to be passed as parameters
		Arrays.sort(s1);				//Use quick sort to sort the char arrays
		Arrays.sort(s2);
		String str11=new String(s1);		//We need to convert the char array to string
		String str22=new String(s2);		//as using the variable s1 or s2 returns the starting address
		return str11.equals(str22);		//of the char array
	}
	
	/*
	 * isPermutationUsingCharCount function does better in terms of time
	 * & space complexity by keeping a track of the character counts in the two
	 * strings and checking whether all characters appear the same no. of
	 * times in the string
	 * Assumption: The two strings contain chars of ASCII set
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 */
	
	private boolean isPermutationUsingCharCount(String str1, String str2){
		if(str1==null || str2==null || str1.length()!=str2.length())	// error cases
			return false;
		int[] charMap = new int[256];	// used to keep track of char count
		char[] s1 = str1.toCharArray();	// convert str1 to charArray
		for(char c:s1){					// read first string's chars
			charMap[(int)c]+=1;			// increment char count for each char
		}
		for(int i=0;i<str2.length();i++){	// for the second string we decrement
			int ch = (int) str2.charAt(i);	// char count for each char
			charMap[ch]-=1;					// since ideally the char counts should
			if(charMap[ch] < 0)				// all be 0, we check that if it becomes
				return false;				// less than 0 at any point then the strings
		}									// are not permutations of each other
		return true;
	}
	
	public static void main(String[] args) {
		Solution02 s2 = new Solution02();
		System.out.println(s2.isPermutationUsingSort("abcd", "dbac"));
		System.out.println(s2.isPermutationUsingCharCount("abcde", "dbcae"));
	}

}
