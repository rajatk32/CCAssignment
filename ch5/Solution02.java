package ch5;

/*
 * We can obtain the binary number for the number on the left
 * part of decimal point in the standard way by dividing with
 * 2 until the number becomes zero.
 * Finding the binary representation of the number on the right
 * of the decimal point is a bit different. For that, we have to
 * multiply it with 2 and extract whatever digit we get on the 
 * left of the decimal after multiplying.
 * For e.g. if we have to convert 0.625 to binary:
 * 1. Multiply 0.625 with 2 to get 1.25
 *    Take 1 from the left which is our first digit of the binary 
 *    representation of 0.625 and continue multiplication with 0.25
 * 2. 0.25 x 2 = 0.50
 *    The next number in the binary representation is 0
 * 3. Multiply 0.50 x 2 = 1.00
 *    The next number in the binary representation is 1. So the 
 *    binary representation of 0.625 is 0.101
 *    
 * Since the number on the right of the decimal is 0 in Step 3,
 * we stop here.
 * 
 * There can also be numbers that don't ever yield a 0 on the right
 * of the decimal on using this method. We should put a check if 
 * the binary representation beyond 32 digits and
 * stop there returning an Error.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution02 {
	
	public static String printBinary(String n){
		//separate whole number from fraction
		
		int intPart = Integer.parseInt(n.substring(0, n.indexOf('.')));
		double decPart = Double.parseDouble(n.substring(n.indexOf('.'), n
				.length()));		//returns 0.72
		
		String int_String="", dec_String="";	//these will be our
												//output strings
		
		//get binary representation of int part
		
		while(intPart>0){	//loop terminates after intPart becomes 1
							//and is divided by 2 resulting in 0 value
			
			if(intPart%2==0)				//if intPart is even
				int_String="0"+int_String;	//we can't use
											//int_String+="0" bcoz we
											//want to prepend not append
			
			else							//if intPart is odd
				int_String="1"+int_String;
			
			intPart /= 2;
		}
		
		//get binary representation of decimal part
		
		while(decPart>0){		//till there are some non-zero digits
								//in decPart
			if(dec_String.length() > 32)	//if no. can't be
				return "ERROR";				//represented accurately
			
			double calc = decPart * 2;
			dec_String+=(int)calc;		//here we append unlike above
			if(calc >= 1)				//if 1 is there before decimal
				decPart = calc-1;		//subtract 1 to obtain decPart
			else
				decPart = calc;			//else set decPart as it is
		}
		
		return int_String+"."+dec_String;	//combine both strings
	}
	
	public static void main(String[] args) {
		
		System.out.println(printBinary("3.625"));
		//Output: 11.101
	}

}