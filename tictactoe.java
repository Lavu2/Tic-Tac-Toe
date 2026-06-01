import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
            {'1','2','3'},
            {'4','5','6'},
            {'7','8','9'}
    };

    static char currentPlayer = 'X';

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int moves = 0;

        while (true) {

            printBoard();

            System.out.println("Player " + currentPlayer + ", enter position (1-9):");
            int pos = sc.nextInt();

            if (!placeMark(pos)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            moves++;

            if (checkWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard();
                System.out.println("Game Draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + board[i][0] + " | " + board[i][1] + " | " + board[i][2]);
            if (i < 2)
                System.out.println("---+---+---");
        }
        System.out.println();
    }

    static boolean placeMark(int pos) {

        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;

        if (pos < 1 || pos > 9)
            return false;

        if (board[row][col] == 'X' || board[row][col] == 'O')
            return false;

        board[row][col] = currentPlayer;
        return true;
    }

    static boolean checkWinner() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer)
                return true;

            if (board[0][i] == currentPlayer &&
                board[1][i] == currentPlayer &&
                board[2][i] == currentPlayer)
                return true;
        }

        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer)
            return true;

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer)
            return true;

        return false;
    }
}
