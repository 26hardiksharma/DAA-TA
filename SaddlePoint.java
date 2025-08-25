public class SaddlePoint {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        boolean found = false;
        for (int i = 0; i < matrix.length; i++) {
            int minCol = 0;
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] < matrix[i][minCol]) {
                    minCol = j;
                }
            }
            int sP= matrix[i][minCol];
            boolean isSaddle = true;
            for (int k = 0; k < matrix.length; k++) {
                if (matrix[k][minCol] > sP) {
                    isSaddle = false;
                    break;
                }
            }
            if (isSaddle) {
                System.out.println("Saddle point: (" + i + ", " + minCol + "): " + sP);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No saddle point found.");
        }
    }
}
