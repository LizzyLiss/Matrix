package tests;
import com.company.*;
import  org.junit.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class MatrixTests {
    @Test
    /**
     * Утверждает, возникает ли исключение ArrayStoreException, когда нулевой массив передается конструктору матрицы
     */
    public void failsToCreateOnNullReferenceArray() {
        // arrange
        double[][] matrixArray = null;

        // act
        // assert
        assertThrows(ArrayStoreException.class, () -> new Matrix(matrixArray));
    }

    @Test
    /**
     * Утверждает, что при передаче конструктору матрицы отрицательных размеров возникает исключение NegativeArraySizeException
     */
    public void failsToCreateOnInvalidSizes() {
        // arrange
        int rows = -1;
        int columns = 5;

        // act
        // assert
        assertThrows(NegativeArraySizeException.class, () -> new Matrix(rows, columns));
    }

    @Test
    public void getsValidRow() {
        // arrange
        double[][] matrixArray = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrixArray[i][j] = i + j / 10;
            }
        }
        var expectedRow = matrixArray[0];

        // act
        var matrix = new Matrix(matrixArray);
        // assert
        assertTrue(Arrays.equals(expectedRow, matrix.getRow(1)));
    }

    @Test
    public void throwsArrayIndexOutOfBoundsExceptionOnBadRowIndex() {
        // arrange
        double[][] matrixArray = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrixArray[i][j] = i + j / 10;
            }
        }

        // act
        var matrix = new Matrix(matrixArray);
        // assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.getRow(-1));
    }

    @Test
    public void getsValidColumn() {
        // arrange
        double[][] matrixArray = new double[2][2];
        var expectedColumn = new double[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrixArray[i][j] = i + j / 10;
            }
            expectedColumn[i] = matrixArray[i][0];
        }

        // act
        var matrix = new Matrix(matrixArray);

        // assert
        assertTrue(Arrays.equals(expectedColumn, matrix.getColumn(1)));
    }

    @Test
    public void throwsArrayIndexOutOfBoundsExceptionOnBadColumnIndex() {
        // arrange
        double[][] matrixArray = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrixArray[i][j] = i + j / 10;
            }
        }

        // act
        var matrix = new Matrix(matrixArray);
        // assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> matrix.getColumn(-1));
    }

    @Test
    public void returnsActualNumberOfColumns() {
        int rows = 1;
        int columns = 1;
        var matrix = new Matrix(rows, columns);

        assertEquals(columns, matrix.getColumns());
    }

    @Test
    public  void returnsActualNumberOfRows() {
        int rows = 1;
        int columns = 1;
        var matrix = new Matrix(rows, columns);

        assertEquals(columns, matrix.getRows());
    }

    @Test
    public  void returnsTrueIfActuallySquare() {
        int rows = 1;
        int columns = 1;
        var matrix = new Matrix(rows, columns);

        assertTrue(matrix.isSquare());
    }

    @Test
    public  void returnsFalseIfNotSquare() {
        int rows = 1;
        int columns = 2;
        var matrix = new Matrix(rows, columns);

        assertFalse(matrix.isSquare());
    }

    @Test
    public void returnsValidDeterminantOf3x3Matrix() {
        double[][] matrixArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var matrix = new Matrix(matrixArray);

        double expectedDeterminant = 0;
        double resultDeterminant = matrix.determinant();

        assertEquals(expectedDeterminant, resultDeterminant);
    }

    @Test
    public  void returnsValidDeterminantOf2x2Matrix() {
        double[][] matrixArray = {{1, 2}, {3, 4}};
        var matrix = new Matrix(matrixArray);

        double expectedDeterminant = -2;
        double resultDeterminant = matrix.determinant();

        assertEquals(expectedDeterminant, resultDeterminant);
    }

    @Test
    public  void throwsArithmeticExceptionWhenDeterminantIsZero() {
        double[][] matrixArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        var matrix = new Matrix(matrixArray);

        assertThrows(ArithmeticException.class, () -> matrix.inverse());
    }
}
