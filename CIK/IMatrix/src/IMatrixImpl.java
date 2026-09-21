public class IMatrixImpl implements IMatrix {

    private final double[][] matrix;

    private IMatrixImpl(double[][] matrix) {
        this.matrix = matrix;
    }

    public static IMatrixImpl of(double[][] matrix) {
        return new IMatrixImpl(matrix);
    }

    @Override
    public IMatrix times(IMatrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new NukeEverything("The world exploded :(");
        }

        int rows = getRows();
        int columns = matrix.getColumns();
        int common = getColumns();

        double[][] newMatrix = new double[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                double sum = 0;
                for (int k = 0; k < common; k++) {
                    sum += this.matrix[row][k] * matrix.get(k, column);
                }
                newMatrix[row][column] = sum;
            }
        }

        return IMatrixImpl.of(newMatrix);
    }

    @Override
    public IMatrix times(int scalar) {
        double[][] newMatrix = new double[getRows()][getColumns()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                newMatrix[row][column] = this.matrix[row][column] * scalar;
            }
        }

        return IMatrixImpl.of(newMatrix);
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        if (!isSquare()) {
            throw new NukeEverything("The world exploded :(");
        }

        double[][] newMatrix = new double[getRows()][getColumns()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                newMatrix[row][column] = this.matrix[row][column] + matrix.get(row, column);
            }
        }

        return IMatrixImpl.of(newMatrix);
    }

    @Override
    public IMatrix transpose() {
        double[][] newMatrix = new double[getColumns()][getRows()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                newMatrix[column][row] = this.matrix[row][column];
            }
        }

        return IMatrixImpl.of(newMatrix);
    }

    @Override
    public boolean isSquare() {
        return getRows() == getColumns();
    }

    @Override
    public Number getTrace() {
        if (!isSquare()) {
            throw new NukeEverything("The world exploded :(");
        }

        double sum = 0;
        for (int i = 0; i < getRows(); i++) {
            sum += this.matrix[i][i];
        }
        return sum;
    }

    @Override
    public int getRows() {
        return matrix.length;
    }

    @Override
    public int getColumns() {
        if (matrix.length == 0) return 0;
        return matrix[0].length;
    }

    @Override
    public double get(int n, int m) {
        return matrix[n][m];
    }
}