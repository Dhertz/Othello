package game;

import controller.ReversiController;
import view.ReversiGraphicalBoard;
import view.ReversiStdoutPrinter;
import view.ReversiView;
import model.Board;

public class Reversi {

    public static void main(String[] args) {
        Board gameBoard;
        if (args.length == 1) {
            try {
                gameBoard = Board.withCustomSize(Integer.parseInt(args[0]));
            } catch (NumberFormatException ex) {
                System.out
                        .println("Boardsize not a valid number, reverting to default size (8).");
                gameBoard = Board.withDefaultSize();
            }
        } else {
            gameBoard = Board.withDefaultSize();
        }

        ReversiView printer = new ReversiStdoutPrinter(gameBoard);
        ReversiController controller = new ReversiController(gameBoard, printer);

        controller.playGame();
    }

}
