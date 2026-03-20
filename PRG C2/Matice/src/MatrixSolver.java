public class MatrixSolver {

    public int solve(int[][] matrix){
        return step(matrix);
    }

    private int step(int[][] matrix){
        int count = 0;
        if(matrix.length == 2){
            return matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1];
        }else{
            for(int j = 0; j < matrix.length; j++){
                int[][] smallerMatrix = subMatrix(matrix, j);
                count += (int) (matrix[0][j]*step(smallerMatrix)*Math.pow(-1, (j+1)));
            }
        }
        return count;
    }

    private int[][] subMatrix(int[][] matrix, int index){
        int[][] smallerMatrix = new int[matrix.length-1][matrix[0].length-1];
        for(int smallI = 1; smallI <= smallerMatrix.length; smallI++){
            for(int smallJ = 0; smallJ <= smallerMatrix[0].length; smallJ++){
                if(smallJ == index){
                    continue;
                }
                if (index < smallJ) {
                    smallerMatrix[smallI-1][smallJ-1] = matrix[smallI][smallJ];
                }else{
                    smallerMatrix[smallI-1][smallJ] = matrix[smallI][smallJ];
                }
            }
        }
        return smallerMatrix;
    }
}
