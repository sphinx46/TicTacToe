import java.util.Scanner;

public class TicTacToe {
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[3][3];
        scanner = new Scanner(System.in);
        initializeBoard();
        currentPlayer = PLAYER_X;
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    private boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != EMPTY) {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    private void playGame() {
        System.out.println("Добро пожаловать в Крестики-нолики!");
        System.out.println("Вводите координаты хода в формате 'строка столбец' (например, '1 2')");

        while (true) {
            printBoard();
            System.out.println("Игрок " + currentPlayer + ", ваш ход:");

            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (!makeMove(row, col)) {
                System.out.println("Недопустимый ход! Попробуйте снова.");
                continue;
            }

            if (checkWin()) {
                printBoard();
                System.out.println("Игрок " + currentPlayer + " победил!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("Ничья!");
                break;
            }

            switchPlayer();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}