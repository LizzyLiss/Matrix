package com.company;

import java.util.Arrays;
import java.util.Objects;

/**
 * Оболочка двумерного массива для матрицы
 */
public class Matrix
{
    /**
     * Массив
     */
    private final double[][] array;
    /**
     * Количество столбцов матрицы(!=0)
     */
    private final int columns;
    /**
     * Количество строк матрицы(!=0)
     */
    private final int rows;

    /**
     * Содает нулевую матрицу
     * @param columns количество столбцов
     * @param rows количество строк
     * @throws NegativeArraySizeException если размеры матрицы отрицательны
     */
    public Matrix(int columns, int rows) throws NegativeArraySizeException
    {
        if (columns <= 0 || rows <= 0)
        {
            throw new NegativeArraySizeException("Размеры матрицы должны быть положительными числами");
        }

        this.columns = columns;
        this.rows = rows;
        array = new double[rows][columns];
    }

    /**
     * Создает матрицу из двумерного массива
     * @param array исходный двумерный массив
     * @throws ArrayStoreException если двумерный массив равен нулю
     */
    public Matrix(double[][] array) throws ArrayStoreException
    {
        if (array == null)
        {
            throw new ArrayStoreException("Matrix can't be null ");
        }

        this.array = array;
        this.rows = array.length;
        this.columns = array[0].length;
    }

    /**
     * Возвращает строку матрицы
     * @param rowIndex положительный индекс, имеющейся строки в массиве
     * @return массив чисел
     * @throws ArrayIndexOutOfBoundsException когда в матрице нет данного индекса
     */
    public double[] getRow(int rowIndex) throws ArrayIndexOutOfBoundsException
    {
        if (rowIndex <= 0 || rowIndex > rows)
        {
            throw new ArrayIndexOutOfBoundsException("There is no such index in the array");
        }

        return array[rowIndex - 1];
    }
public double[][] getarr(){
        return array;
}
    /**
     * Возвращает столбец матрицы
     * @param columnIndex положительный индекс, имеющегося столбца в массиве
     * @return массив чисел
     * @throws ArrayIndexOutOfBoundsException когда в матрице нет данного индекса
     */
    public double[] getColumn(int columnIndex) throws ArrayIndexOutOfBoundsException {
        if (columnIndex <= 0 || columnIndex > columns)
        {
            throw new ArrayIndexOutOfBoundsException("There is no such index in the array");
        }

        var resultColumn = new double[rows];
        for (int i = 0; i < rows; i++) {
            resultColumn[i] = array[i][columnIndex - 1];
        }
        return resultColumn;
    }

    /**
     * @return количество столбцов в матрице
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @return количество строк в матрице
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return если матрица квадратная
     */
    public boolean isSquare() {
        return columns == rows;
    }

    /**
     * Транспонирование матрицы
     * @return транспонированную матрицу
     */
    public Matrix transpose()
    {
        Matrix result = new Matrix(columns, rows);

        for (int row = 0; row < rows; ++row)
        {
            for (int col = 0; col < columns; ++col)
            {
                result.array[col][row] = array[row][col];
            }
        }

        return result;
    }

    /**
     * Вычитание матрицы из матрицы
     * @param exclude_row индекс строки для исключения(>0)
     * @param exclude_col идекс столбца для исключения(>0)
     * @return новую матрицу с исключенными строками и столбцами
     */
    public Matrix subMatrix(int exclude_row, int exclude_col)
    {
        Matrix result = new Matrix(rows - 1, columns - 1);

        for (int row = 0, p = 0; row < rows; ++row)
        {
            if (row != exclude_row - 1)
            {
                for (int col = 0, q = 0; col < columns; ++col)
                {
                    if (col != exclude_col - 1)
                    {
                        result.array[p][q] = array[row][col];
                        ++q;
                    }
                }
                ++p;
            }
        }
        return result;
    }

    /**
     * Вычисляет определить матрицы
     * @return определитель
     */
    public double determinant()
    {
        if (columns == 1) {
            return array[0][0];
        }
        else if (columns == 2) {
            return (array[0][0] * array[1][1] - array[0][1] * array[1][0]);
        }
        else {
            double result = 0.0;

            for (int col = 0; col < columns; ++col) {
                Matrix sub = subMatrix(1, col + 1);

                result += (Math.pow(-1, 1 + col + 1) *
                        array[0][col] * sub.determinant());
            }

            return result;
        }
    }


    /**
     * @return инвентированную матрицу
     * @throws ArithmeticException если матрица квадратная или равна 0
     */
    public Matrix inverse() throws ArithmeticException
    {
        double determinant = determinant();

        if (rows != columns || determinant == 0.0)
        {
            throw new ArithmeticException("This matrix cannot be inverted");
        }
        else
        {
            Matrix result = new Matrix(rows, columns);

            for (int row = 0; row < rows; ++row)
            {
                for (int col = 0; col < columns; ++col)
                {
                    Matrix sub = subMatrix(row + 1, col + 1);
                    result.array[col][row] = (1.0 / determinant * Math.pow(-1, row + col) * sub.determinant());
                }
            }

            return result;
        }
    }
}