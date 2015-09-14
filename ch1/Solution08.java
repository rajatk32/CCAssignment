package ch1;

/*
 * Whenever we read a 0 in the matrix, we are going to keep
 * track of the row and column no. of that cell in two 1-D
 * arrays called row and column. We are then going to run another
 * loop to set zeros in columns and rows containing 0s by reading
 * these 1-D arrays.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(M+N)
 */

public class Solution08 {
	
	private void setZeros(int[][] matrix) {
		int[] row = new int[matrix.length];			// initialize row and column arrays
		int[] column = new int[matrix[0].length];
		// Store the row and column index with value 0
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length;j++) {
				if (matrix[i][j] == 0) {
					row[i] = 1;
					column[j] = 1;
				}
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if ((row[i] == 1 || column[j] == 1)) {	// if either the row or column contains a zero 
					matrix[i][j] = 0;					// set the cell to 0
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Solution08 s8 = new Solution08();
		int[][] array = new int[][]{{1,2,3},{4,5,6},{7,0,9}};
		s8.setZeros(array);
		
		for(int i=0;i<array.length;i++){		// print the matrix after setting zeros
			for(int j=0;j<array[0].length;j++){
				System.out.print(array[i][j]+"\t");
			}
			System.out.println();
		}
	}

}
