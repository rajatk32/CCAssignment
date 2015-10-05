package ch6;

import java.util.Random;

public class Solution07 {
	public static int[] runOneFamily() {
		Random random = new Random();
		int boys = 0;
		int girls = 0;
		while (girls == 0) { // until we have a girl
			if (random.nextBoolean()) { // true for a girl
				girls += 1;
			} else { 		// false for a boy
				boys += 1;
			}
		}
		int[] genders = {girls, boys};	// create an array for the no. of girls and boys in the family
		return genders;
	}
	
	public static double runNFamilies(int n) {
		int boys = 0;		// these are the no. of girls and boys for the entire population
		int girls = 0;
		for (int i = 0; i < n; i++) {		// run simulation for n number of families
			int[] genders = runOneFamily();
			girls += genders[0];
			boys += genders[1];
		}
		return girls / (double) (boys + girls);		// gender ratio: girls vs. entire 
	}
	
	public static void main(String[] args) {
		double ratio = runNFamilies(10000000);
		System.out.println(ratio);

	}

}