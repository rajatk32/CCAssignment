package ch1;

/*
 * Time complexity: O(n)
 * No extra space used (In-place) 
 */

public class Solution03 {
	
	private String insertSpaces(char[] string, int trueLength){
		if(string==null || trueLength<0)	// error cases
			return null;
		int count = 0, newLength;
		for(int i=0;i<trueLength;i++){
			if(string[i] == ' ')
				count++;		// count the no. of spaces
		}
		newLength = trueLength + count * 2;		// calculate the required new length
												// we have chosen to multiply count by 2
												// because the spaces can be replaced by %
												// only 2 more chars '2' and '0' need to be
												// added for each space
		newLength -= 1;		// set the first fillable index from backwards
		
		for(int i=trueLength-1; i>=0; i--){		// start filling the array from backwards
			if(string[i]==' '){				// if space is found, add %20 to the char array
				string[newLength] = '0';	// backwards
				string[newLength-1] = '2';
				string[newLength-2] = '%';
				newLength -= 3;
			}
			else{									// otherwise just copy the original char
				string[newLength--] = string[i];	// as it is  
			}
		}
		return new String(string);			// convert char array to String and return
	}
	
	public static void main(String[] args) {
		Solution03 s3 = new Solution03();
		String st = "this is a test string        ";	// there are four spaces in this string
														// and eight spaces at the end so that enough
														// extra space is available for the algorithm
														// to work in-place
		System.out.println(s3.insertSpaces(st.toCharArray(), 21));
	}

}
