package ch5;

/*
     NEXT GREATER NUMBER
     -------------------
     1. Scan number from right to left bits.
     2. Find the position of the first 1 bit.
     3. From here keep moving left,till a 0 is found & turn this bit on.
     4. Turn off the 1 bit on right of above bit.
     5. Make this as small as possible to get just next larger number,
        Push all 1's after this bit to right end.
     
     E.g.
     (1) 
		6543210(position) 
		1011010(num) 
		1st zero position after getting 1 1's is 2, make it 1 and all
		1-1 1's at rightmost side 
		1011100 

	 (2) 
		6543210(position) 
		1011100(num) 
		1st zero position after getting 3 1's is 5, make it 1 and all
		3-1 1's at rightmost side 
		1100011 

	 (3) 
		6543210(position) 
		1100111(num) 
		1st zero position after getting 3 1's is 3, make it 1 and all
		3-1 1's at rightmost side 
		1101011 

     NEXT SMALLER NUMBER
     -------------------
     1. Scan number from right to left bits. (Just like above)
     2. Find the position of the first 0 bit.
     3. From here keep moving left,till a 1 is found & turn this bit off.
     4. Turn on the 0 bit on right of above bit.
     5. Make this as large as possible to get just previous smaller 
        number, Push all 1's after this bit to as much left as possible.
*/

/* we assume that we will be operating on positive numbers only */

/*
   Simple test data:
   Prev: 0110	=6
   Num : 1001	=9
   Next: 1010	=10

   Prev: 0111	=7
   Num : 1011	=11
   Next: 1101	=13

   Prev: 0001	=1
   Num : 0010	=2
   Next: 0100	=4
   
   Time complexity: O(n)
   Space complexity: O(1)
*/

public class Solution04 {
	
	public boolean isBitSet(int n, int pos){
		return (n & (1<<pos))>0;	//we need to check >0 and not ==1
									//bcoz suppose n=0101 then if we
									//check 2nd position, the expression
									//(n & (1<<pos)) returns 4, which is
									//the position of the bit
	}
	
	public int setBit(int n, int pos, boolean value){
		if(value==true){		//set 1
			return n | (1<<pos);
		}
		else{					//set 0
			return n & ~(1<<pos);
		}
	}
	
	public int getNext(int n){
		if(n<=0)
			return -1;
		
		int currIndex=0;
		int countOnes=0;		//used to shift bits to right later on
		
		//find first 1
		while(!isBitSet(n,currIndex)){		//isBitSet returns true when
			currIndex++;					//it finds a 1, we want to
											//continue the loop if a 0
											//is found for which isBitSet
											//returns false; this is why
											//we put a ! before isBitSet
		}
		
		//now move left from currIndex until a 0 is found
		//also increment countOne to keep track of no. of 1's
		
		while(isBitSet(n,currIndex)){
			currIndex++;
			countOnes++;
		}
		
		//set this 0 bit to 1
		
		n = setBit(n,currIndex,true);
		
		//set the bit on the right of this to 0
		//we've now eliminated a 1 so decrement countOnes as well
		
		currIndex--;
		n = setBit(n,currIndex,false);
		countOnes--;
		
		//Our currIndex is now at the 0 after which we have countOnes no.
		//of 1's together which have to be moved to the rightmost end
		//To do this, first run a loop in range 0..countOnes to set these
		//1's to 0 starting from currIndex; Next run another loop from 
		//range 0..countOnes to set these adjacent 1's beginning from the
		//0th position
		
		for(int i=0;i<countOnes;i++)
			n = setBit(n,--currIndex,false);
		for(int i=0;i<countOnes;i++)
			n = setBit(n,i,true);
		
		return n;
	}
	
	public int getPrev(int n){
		if (n <= 0) return -1;
		
		 int index = 0;
		 int countZeros = 0;
		
		 // Find first zero.
		 while (isBitSet(n, index)) index++;
		
		 // Find & turn off next 1.
		 while (!isBitSet(n, index)) {
		 index++;
		 countZeros++;
		 }
		 n = setBit(n, index, false);
		
		 // Turn on previous zero to this bit
		 index--;
		 n = setBit(n, index, true);
		 countZeros--;
		 
		 for(int i=0;i<countZeros;i++)
			n = setBit(n,--index,true);
		 for(int i=0;i<countZeros;i++)
			n = setBit(n,i,false);
		 
		 return n;
	}
	
	public static void main(String[] args) {
		
		Solution04 gpn = new Solution04();
		int n=Integer.parseInt("1011100", 2);	//1011100
		System.out.println("Original no. "+n);
		System.out.println("No. in binary: "+Integer.toBinaryString(n));
		System.out.println("\nNext no. "+gpn.getNext(n));
		System.out.println("Next no. in binary "+Integer.toBinaryString(gpn.getNext(n)));
		System.out.println("\nPrev no. "+gpn.getPrev(n));
		System.out.println("Prev no. in binary "+Integer.toBinaryString(gpn.getPrev(n)));
		
		/*
		   Output:
		   Original no. 92
		   No. in binary: 1011100

		   Next no. 99
		   Next no. in binary 1100011

		   Prev no. 90
		   Prev no. in binary 1011010

		 */
	}
}