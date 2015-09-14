package ch1;

/*
 * We are going to consider the matrix as consisting of different layers
 * For e.g for the matrix:
 * 1   2   3   4
 * 5   6   7   8
 * 9  10  11  12
 * 13 14  15  16
 * 
 * We will begin with replacing the elements of the outermost layer which contains
 * 1,2,3,4,8,12,16,15,14,13,9,5 and then the inner layer which contains 6,7,11,10
 * We pick and replace the elements cyclically picking four at a time.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1) for storing backup of values in matrix
 */

public class Solution07 {
	
	public void rotate(int[][] a, int n){
		int layer=0, totalLayers=n/2;
		
		//run a loop for each layer beginning with the outer one
		for(int i=0;i<totalLayers;i++){
			int lastElement=n-1-i;
			for(int j=i;j<lastElement;j++){		//the current layer is i
				int temp=a[j][i]; 				//store the left-most element going downwards from top
				
				//rotate elements cyclically
				//bottom-->left rotation (first replace the element we stored above)
				a[j][i]=a[lastElement][j];		//left=bottom
				
				//above a[lastElement][j] is assigned and below the same is set
				//thus we assign and set elements cyclically so that only one variable
				//is sufficient for swapping, this is followed similarly in other steps
				
				//right-->bottom rotation
				a[lastElement][j]=a[lastElement-j+i][lastElement];		//bottom=right
				
				//top-->right rotation
				a[lastElement-j+i][lastElement]=a[i][lastElement-j+i];	//right=top	
				
				//left-->top rotation
				a[i][lastElement-j+i]=temp;								//top=left
			}
		}
		for(int i=0;i<n;i++){			// this is just to print the rotated matrix and is optional
			for(int j=0;j<n;j++){
				System.out.print(a[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Solution07 s7 = new Solution07();
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		s7.rotate(matrix, 4);
	}

}
