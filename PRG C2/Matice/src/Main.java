public class Main {
    public static void main(String[] args) {
        MatrixSolver solver = new MatrixSolver();
        int[][] matrix = new int[][]{
                {1, 0, 2, -1},
                {3, 1, 0, 2},
                {0, -1, 1, 1},
                {2, 0, 3, 1}
        };

        System.out.println(solver.solve(matrix));
    }
}