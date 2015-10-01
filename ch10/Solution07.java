package ch10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*
 * There are a total of 2^32 or 4 billion distinct integers possible.
 * We have 1 GB of memory which can store 8 billion bits.
 * 
 * Pseudocode:
 * 1. We can create a bit vector with 4 billion bits. The bit vector
 * can store an array of ints. Each int stores a sequence of 32 bits.
 * 2. Scan all numbers from the file and set the corresponding bits.
 * 3. Scan the bit vector from 0th index and return the first number
 * that is still 0.
 */

public class Solution07 {
	
	public void missingInt(String file) throws FileNotFoundException{
		long numberOfInts = ((long) Integer.MAX_VALUE) + 1;
		byte[] bitfield = new byte[(int) numberOfInts/8];
		Scanner in = new Scanner(new FileReader(file));
		while (in.hasNextInt()) {
			int n = in.nextInt ();
			bitfield [n / 8] |= 1 << (n % 8);
		}

		for (int i = 0; i < bitfield.length; i++) {
			for (int j = 0; j < 8; j++) {
				if ((bitfield[i] & (1 << j)) == 0) {
					System.out.println (i * 8 + j);
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {

	}

}
