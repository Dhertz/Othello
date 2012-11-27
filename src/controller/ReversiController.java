package controller;

import java.util.Scanner;

import model.Board;
import model.PieceState;
import view.ReversiView;

public class ReversiController {

    private Player player1;
    private Player player2;
    private Board board;
    private ReversiView output;
    private Scanner reader;
    private Player currentPlayer;

    public ReversiController(Board board, ReversiView printer) {
        this.board = board;
        this.output = printer;
        reader = new Scanner(System.in);
        printWelcomeMessage();
        setupPlayers();
    }

    private void printWelcomeMessage() {
        System.out.println("\\ \\        / / | |");
        System.out.println(" \\ \\  /\\  / ___| | ___ ___  _ __ ___   ___");
        System.out.println("  \\ \\/  \\/ / _ | |/ __/ _ \\| '_ ` _ \\ / _ \\");
        System.out.println("   \\  /\\  |  __| | (_| (_) | | | | | |  __/ ");
        System.out.println("    \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
        System.out.println("            | |");
        System.out.println("            | |_ ___");
        System.out.println("            | __/ _ \\");
        System.out.println("            | || (_) |");
        System.out.println("  ____   _  _\\__\\___/  _ _");
        System.out.println(" / __ \\| | | |        | | |");
        System.out.println("| |  | | |_| |__   ___| | | ___");
        System.out.println("| |  | | __| '_ \\ / _ | | |/ _ \\");
        System.out.println("| |__| | |_| | | |  __| | | (_) |");
        System.out.println(" \\____/ \\__|_| |_|\\___|_|_|\\___/");
        System.out
                .println("Please enter moves in the format letter <space> number, eg a 1");
    }

    private void setupPlayers() {
        System.out.println("Player 1, what is your name?");
        player1 = new Player(PieceState.BLACK, reader.nextLine());

        System.out.println("Player 2, what is your name?");
        player2 = new Player(PieceState.WHITE, reader.nextLine());

        currentPlayer = player1;
    }

    public void readMove() {
        if (!board.hasValidMoves(currentPlayer.getColour())) {
            System.out.println(currentPlayer.getName() + " has no valid move!");
            changePlayer();
        }
        System.out
                .println(currentPlayer.getName() + ", please enter your move");
        String input = reader.nextLine();
        String[] tokenised = input.split(" ");

        try {

            char x = tokenised[0].charAt(0);
            int xi = x - 96;
            int y = Integer.parseInt(tokenised[1]);

            if (board.isValidMove(xi, y, currentPlayer.getColour())) {
                board.setPiece(xi, y, currentPlayer.getColour());
                board.capturePieces(xi, y, currentPlayer.getColour());
                changePlayer();
            } else {
                System.out.println("Invalid move.");
                readMove();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            handleException();
        } catch (StringIndexOutOfBoundsException e) {
            handleException();
        } catch (NumberFormatException e) {
            handleException();
        }

    }

    private void handleException() {
        System.out
                .println("Invalid move. Please enter letter <space> row, eg a 1");
        readMove();
    }

    private void changePlayer() {
        currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
    }

    public void playGame() {
        while (isPlayable()) {
            output.showBoard();
            readMove();
        }
        output.showBoard();
        System.out.println("Game over! Winner is: " + getWinnerName() + ".");
    }

    private boolean isPlayable() {
        return board.hasValidMoves(player1.getColour())
                || board.hasValidMoves(player2.getColour());
    }

    private String getWinnerName() {
        if (board.getPieceCount(player1.getColour()) > board
                .getPieceCount(player2.getColour())) {
            return player1.getName();
        } else {
            return player2.getName();
        }
    }

}
