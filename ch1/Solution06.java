package ch1;

public class Solution06 {
	/*
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	
	private String compressString(String st){
		// if the string is null, empty or contains only 2 characters,
		// it can't be further compressed, so return it as it is:
		if(st==null || st.isEmpty() || st.length()<=2)
			return st;
		// we are going to keep track of the last read character and compare
		// it with the current one to check that the repetition is over
		char lastRead = st.charAt(0);		// initialize lastRead char as the first one
		int counter = 1;					// counter keeps track of the current char's repetition count
		StringBuffer compressed = new StringBuffer();	// use a StringBuffer so that every time we add a char to
														// the compressed string, a new String is not created
		for(int i=1;i<st.length();i++){
			char currChar = st.charAt(i);
			if(currChar == lastRead){		// if current char is equal to the last char read
				counter++;					// increase count of this char
			}
			else{							// otherwise add the compressed result to StringBuffer
				compressed.append(Character.toString(lastRead) + counter);
				lastRead = currChar;		// update the lastRead char
				counter = 1;				// reset counter
			}
		}
		compressed.append(Character.toString(lastRead) + counter);		// the last one still needs to be added
		// if compressed string's length is shorter return it, else return original string:
		return compressed.length() < st.length() ? compressed.toString() : st;
	}
	
	public static void main(String[] args) {
		Solution06 s6 = new Solution06();
		System.out.println(s6.compressString("abc"));
		System.out.println(s6.compressString("aabc"));
		System.out.println(s6.compressString("aabcccccaaa"));
	}

}
