package ch5;

/*
 * Time complexity: O(n)
 * Space complexity: O(1)
 */

public class Solution06 {
	
	public int bitsRequired(int a, int b){
		int x = a ^ b;		//do a XOR to leave only the bits that don't
							//match; returns 10001 (for below test data)
		int count = 0;
		
		//we are going to count the number of set bits by ANDing with
		//(1<<0) which checks if the bit at 0th position is set
		//simultaneously we also shift the XORed no. to right to count
		//all set bits; this terminates when all set bits are 
		//right-shifted resulting in the no. becoming 0
		
		while(x!=0){
			count += x & (1<<0);
			x >>= 1;
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		Solution06 br = new Solution06();
		int a=31;	// 11111 (in binary)
		int b=14;	//  1110 (in binary)
		
		System.out.println(br.bitsRequired(a, b));
		
	}
}