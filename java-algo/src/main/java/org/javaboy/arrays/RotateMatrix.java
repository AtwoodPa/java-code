package org.javaboy.arrays;

/**
 * 矩阵旋转
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不能使用额外的内存，你必须在原地旋转图像并直接更新输入的数组，以便在调用返回后的方法时，输入数组是旋转的图像。
 * @author supanpan
 * @date 2024/08/04
 */
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
//        // 首先，进行矩阵的转置
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[j][i];
//                matrix[j][i] = temp;
//            }
//        }
//
//        // 然后，反转每一行
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n / 2; j++) {
//                int temp = matrix[i][j];
//                matrix[i][j] = matrix[i][n - j - 1];
//                matrix[i][n - j - 1] = temp;
//            }
//        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotateMatrix rotateMatrix = new RotateMatrix();
        rotateMatrix.rotate(matrix);
//        rotateMatrix.rotate(matrix);
//        rotateMatrix.rotate(matrix);
//        rotateMatrix.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
