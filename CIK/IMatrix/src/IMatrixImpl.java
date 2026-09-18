public class IMatrixImpl implements IMatrix{
    private final double[][] matrix;

    public IMatrixImpl(double[][] matrix) {
        this.matrix = matrix;
    }


    @Override
    public IMatrix times(IMatrix matrix) {
        return null;
    }

    @Override
    public IMatrix times(int scalar) {
        double[][] newMatrix = new double[getRows()][getColumns()];

        for(int row = 0; row < getRows(); row++){
            for(int column = 0; column < getColumns(); column++){
                newMatrix[row][column] = this.matrix[row][column] * scalar;
            }
        }

        return new IMatrixImpl(newMatrix);
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        if(!isSquare()){
            throw new NukeEverything("The world exploded :(");
        }

        double[][] newMatrix = new double[getRows()][getColumns()];

        for(int row = 0; row < getRows(); row++){
            for(int column = 0; column < getColumns(); column++){
                newMatrix[row][column] = this.matrix[row][column] + matrix.get(row, column);
            }
        }

        return new IMatrixImpl(newMatrix);
    }

    @Override
    public IMatrix transpose() {
        double[][] newMatrix = new double[getColumns()][getRows()];

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                newMatrix[column][row] = this.matrix[row][column];
            }
        }

        return new IMatrixImpl(newMatrix);
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