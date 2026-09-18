public interface IMatrix {

    /**
     * Multiplies the two matrices together and returns the resulting matrix.
     *
     * @param matrix Matrix to multiply with
     * @return Multiplied matrix
     */
    IMatrix times(IMatrix matrix);

    /**
     * Multiplies the matrix with a number.
     *
     * @param scalar Number to multiply with
     * @return Scalar times "larger" matrix
     */
    IMatrix times(int scalar);

    /**
     * Adds two matrices together
     *
     * @param matrix Matrix to add
     * @return sum of the two matrices
     */
    IMatrix add(IMatrix matrix);

    /**
     * Transposes the matrix (flips rows and columns)
     *
     * @return flipped matrix
     */
    IMatrix transpose();

    /**
     * "Calculates" whether the matrix is square or not
     *
     * @return True if the matrix squared else false
     */
    boolean isSquare();

    /**
     * Calculates the <a href="https://en.wikipedia.org/wiki/Trace_(linear_algebra)">trace</a> of the matrix
     *
     * @return Trace
     */
    Number getTrace();

    /**
     * @return rows
     */
    int getRows();

    /**
     * @return columns
     */
    int getColumns();

    /**
     * @param n Nth index
     * @param m Mth index
     * @return the value of the "cell"
     */
    double get(int n, int m);

}