package sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    // conversion of x, o and blank fields into numbers
    public static List<List<Integer>> conver(String boardFileName) throws FileNotFoundException {
        File boardFile = new File(boardFileName);
        Scanner scanner = new Scanner(boardFile);

        List<List<Integer>> myList2D = new ArrayList<>();

        while (scanner.hasNextLine()) {

            ArrayList<Integer> l = new ArrayList<>();
            l.add(0);
            l.add(0);
            l.add(0);

            String line = scanner.nextLine();
            int j = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'o') {
                    l.set(j, -1);
                    i++;
                    j++;
                } else if (line.charAt(i) == 'x') {
                    l.set(j, 1);
                    i++;
                    j++;
                } else if (line.charAt(i) == ';') {
                    j++;
                }
            }
            myList2D.add(l);
        }
        return myList2D;
    }
    // Sum of each row
    public static int sumRow(List<List<Integer>> matrix, int rowIndex) {
        int sum = 0;
        List<Integer> row = matrix.get(rowIndex);
        for (Integer elem : row) {
            sum += elem;
        }
        return sum;
    }

    //     Sum index column
    public static int sumColumn(List<List<Integer>> matrix, int colIndex) {
        int sum = 0;
        for (List<Integer> row : matrix) {
            sum += row.get(colIndex);
        }
        return sum;
    }

    // Sum of first diagonal
    public static int sumDiagonal1(List<List<Integer>> matrix) {
        int sum = 0;
        int colIndex = 0;
        for (List<Integer> row : matrix) {
            sum += row.get(colIndex);
            colIndex++;
        }
        return sum;
    }

    //Sum of second diagonal
    public static int sumDiagonal2(List<List<Integer>> matrix) {
        int sum = 0;
        int colIndex = 2;
        for (List<Integer> row : matrix) {
            sum += row.get(colIndex);
            colIndex--;
        }
        return sum;
    }

    public enum Result { NOT_FINISHED, NO_WINNER, X_WON, O_WON }

    public static Result checkBoard(String boardFileName) throws FileNotFoundException {

        List<List<Integer>> matrix = conver(boardFileName);
        int sumD1 = sumDiagonal1(matrix);
        int sumD2 = sumDiagonal2(matrix);
        if (sumD1 == 3 || sumD2 == 3) {
            return Result.X_WON;
        } else if (sumD1 == -3 || sumD2 == -3) {
            return Result.O_WON;
        }
        for (int n = 0; n < 2; n++) {
            if (sumRow(matrix, n) == 3 || sumColumn(matrix, n) == 3) {
                return Result.X_WON;
            } else if (sumRow(matrix, n) == -3 || sumColumn(matrix, n) == -3) {
                return Result.O_WON;
            }
        }
        for (List<Integer> row : matrix) {
            for (int elem : row) {
                if (elem == 0) {
                    return Result.NOT_FINISHED;
                }
            }
        }
        return Result.NO_WINNER;
    }
    public static void main(String[] args) throws FileNotFoundException {
        Result res = checkBoard("boards/tick0.csv");
        System.out.println(res);
    }
}
