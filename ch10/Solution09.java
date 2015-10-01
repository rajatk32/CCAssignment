package ch10;

public class Solution09 {
	
	/*
	 * A naive algorithm will be to to do binary search on each and
	 * every row which will be O(mlogn) where there are m rows and
	 * it takes O(logn) time to search each one.
	 * 
	 * We can improve upon this by starting from the top-right element
	 * of the matrix and applying these conditions:
	 * 1. if the current element is equal to the element to be found
	 * 	  return true.
	 * 2. if the current element is larger than element to be found
	 * 	  decrement col.
	 * 3. else if the current element is smaller, increment row.
	 * 
	 * Time complexity: O(n)
	 * Space complexity: O(1)
	 */
	
	public static boolean findElement(int[][] matrix, int elem) {
		int row = 0;
		int col = matrix[0].length - 1; 
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] == elem) {
				return true;
			} else if (matrix[row][col] > elem) {
				col--;
			} else {
				row++; 
			}
		} 
		return false; 
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[10][5];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = 10 * i + j;
			}
		}
		
		System.out.println(findElement(matrix, 10));
	}

}
