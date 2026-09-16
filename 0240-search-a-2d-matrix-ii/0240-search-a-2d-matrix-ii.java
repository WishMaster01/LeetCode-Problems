class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rows = 0;
        int cols = n - 1;

        while(rows < m && cols >= 0) {
            if(matrix[rows][cols] == target) {
                return true;
            }
            else if(matrix[rows][cols] < target) {
                rows += 1;
            }
            else {
                cols -= 1;
            }
        }

        return false;
    }
}