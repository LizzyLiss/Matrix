package com.company;

import java.util.Arrays;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
	System.out.println();
    double[][] arr={{1,2,3},{4,5,6},{7,8,9}};
        Matrix matrix1 = new Matrix(arr);
        Matrix matrix2 = new Matrix(arr);
        matrix1=matrix1.inverse();
        System.out.println(Arrays.deepToString(matrix1.getarr()));

        System.out.println(Arrays.deepToString(matrix1.getarr()));
    }
}
