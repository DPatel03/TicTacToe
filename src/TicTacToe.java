import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X'; // X starts the game
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY_CELL;
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner() {

        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != EMPTY_CELL) {
                return true;
            }
        }
        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != EMPTY_CELL) {
                return true;
            }
        }

        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY_CELL) ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY_CELL)) {
            return true;
        }
        return false;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        while (!game.isBoardFull() && !game.isWinner()) {
            System.out.println("Current board:");
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + "'s turn.");
            System.out.print("Enter row (0-2): ");
            int row = -1;
            int col = -1;
            try {
                row = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                col = scanner.nextInt();
                if (game.makeMove(row, col)) {
                    if (game.isWinner()) {
                        System.out.println("Player " + game.currentPlayer + " wins!");
                    } else if (game.isBoardFull()) {
                        System.out.println("It's a draw!");
                    } else {
                        game.changePlayer();
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter integers.");
                scanner.nextLine(); // Clear the input buffer
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
