package com.company;

/**
 * Содержит функции для работы с несколькими матрицами
 */
public class Operations
{
    /**
     * Добавляет число к матрице
     * @param matrix матрица, к которой будет добавлено число
     * @param value значение, которое будет добавлено
     * @return матрица с добавленным значением
     * @throws NullPointerException когда матрица равна 0
     */
    public static Matrix add(Matrix matrix, double value) throws NullPointerException {
        if (matrix == null) {
            throw new NullPointerException("Matrix can't be null");
        }
        var resultMatrix = new double[matrix.getRows()][matrix.getColumns()];
        for (int i = 0; i < matrix.getRows(); i++) {
            var tempRow = matrix.getRow(i + 1);
            for (int j = 0; j < matrix.getColumns(); j++) {
                resultMatrix[i][j] = tempRow[j] + value;
            }
        }

        return new Matrix(resultMatrix);
    }

    /**
     * Умножает матрицу на значение
     * @param matrix матрица, которую будут умножать
     * @param value значение, на которую нужно умножать
     * @return матрица, умноженная на значение
     */
    public static Matrix multiply(Matrix matrix, double value) {
        if (matrix == null) {
            throw new NullPointerException("Matrix can't be null");
        }

        var resultMatrix = new double[matrix.getRows()][matrix.getColumns()];
        for (int i = 0; i < matrix.getRows(); i++) {
            var matrixRow = matrix.getRow(i + 1);
            for (int j = 0; j < matrix.getColumns(); j++) {
                resultMatrix[i][j] = matrixRow[j] * value;
            }
        }

        return new Matrix(resultMatrix);
    }

    /**
     * Умножает 2 матрицы
     * @param firstMatrix первая матрица n*m
     * @param secondMatrix вторяя матрица m*k
     * @return умноженная матрица n*k
     * @throws ArithmeticException если столбцы первой матрицы не равны строкам второй матрицы
     */
    public static Matrix multiply(Matrix firstMatrix, Matrix secondMatrix) throws ArithmeticException {
        if (firstMatrix.getColumns() != secondMatrix.getRows()) {
            throw new ArithmeticException("Number of columns of the first matrix must be equal to the number of rows of the second array");
        }

        var resultMatrix = new double[firstMatrix.getRows()][secondMatrix.getColumns()];
        var n = firstMatrix.getRows();
        var k = secondMatrix.getColumns();
        var m = firstMatrix.getColumns();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < m; l++) {
                    resultMatrix[i][j] += firstMatrix.getRow(i + 1)[l] * secondMatrix.getRow(l + 1)[j];
                }
            }
        }

        return new Matrix(resultMatrix);
    }

    /**
     * Складывает две матрицы
     * @param firstMatrix первая матрица m*n
     * @param secondMatrix вторая матрица m*n
     * @return результирующая матрица m*n
     * @throws ArithmeticException если размеры двух матриц не равны
     * @throws NullPointerException если матрицы равны 0
     */
    public static Matrix add(Matrix firstMatrix, Matrix secondMatrix) throws ArithmeticException, NullPointerException {
        if (firstMatrix == null || secondMatrix == null) {
            throw new NullPointerException("Matrices can't be null");
        }
        if (firstMatrix.getColumns() != secondMatrix.getColumns() || firstMatrix.getRows() != secondMatrix.getRows()) {
            throw new ArithmeticException("Matrix with different dimensions can't be added");
        }

        var resultMatrix = new double[firstMatrix.getRows()][firstMatrix.getColumns()];
        for (int i = 0; i < firstMatrix.getRows(); i++) {
            var firstMatrixRow = firstMatrix.getRow(i + 1);
            var secondMatrixRow = secondMatrix.getRow(i + 1);
            for (int j = 0; j < firstMatrix.getColumns(); j++) {
                resultMatrix[i][j] = firstMatrixRow[j] + secondMatrixRow[j];
            }
        }

        return new Matrix(resultMatrix);
    }

    /**
     * Вычитает вторую матрицу из первой
     * @param firstMatrix первая матрица n*m
     * @param secondMatrix вторая марица n*m
     * @return результирующую матрицу n*m
     * @throws ArithmeticException если размеры двух матриц не равны
     */
    public static Matrix sub(Matrix firstMatrix, Matrix secondMatrix) throws ArithmeticException {
        if (firstMatrix.getColumns() != secondMatrix.getColumns() || firstMatrix.getRows() != secondMatrix.getRows()) {
            throw new ArithmeticException("Matrix with different dimensions can't be added");
        }

        return add(firstMatrix, multiply(secondMatrix, -1));
    }
}
