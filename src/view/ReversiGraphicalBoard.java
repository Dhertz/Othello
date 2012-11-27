package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;
import model.Piece;
import model.PieceState;

public class ReversiGraphicalBoard implements ReversiView {

    JFrame window;
    JPanel boardPanel;
    JPanel columnHeaders;
    JPanel rowHeaders;
    Board board;
    JLabel[][] labels;
    JLabel[] rows;
    JLabel[] columns;

    public ReversiGraphicalBoard(Board board) {
        this.board = board;
        this.labels = new JLabel[board.getSize()][board.getSize()];
        this.rows = new JLabel[board.getSize()];
        this.columns = new JLabel[board.getSize()];

        window = new JFrame("Reversi!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);

        columnHeaders = new JPanel();
        columnHeaders.setLayout(new GridLayout(1, board.getSize()));
        setupColumns();
        columnHeaders.setBounds(10, 0, 400, 20);

        rowHeaders = new JPanel();
        rowHeaders.setLayout(new GridLayout(board.getSize(), 1));
        setupRows();

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getSize(), board.getSize()));
        boardPanel.setBounds(40, 20, 400, 400);
        setupBoard();

        window.getContentPane().add(columnHeaders);
        window.getContentPane().add(rowHeaders, BorderLayout.WEST);
        window.getContentPane().add(boardPanel);
        window.setVisible(true);
    }

    private void setupColumns() {
        for (char l = 'a'; l < 'a' + board.getSize(); l++) {
            columns[(int) l - 'a'] = new JLabel(String.valueOf(l));
            columnHeaders.add(columns[(int) l - 'a']);
        }
    }

    private void setupRows() {
        for (int i = 1; i <= board.getSize(); i++) {
            rows[i - 1] = new JLabel(String.valueOf(i));
            rowHeaders.add(rows[i - 1]);
        }
    }

    private void setupBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Piece p = board.getBoard()[i][j];
                String text = ".";
                if (p.getState() == PieceState.BLACK) {
                    text = "B";
                } else if (p.getState() == PieceState.WHITE) {
                    text = "W";
                }
                labels[i][j] = new JLabel(text);
                labels[i][j].setFont(new Font("sansserif", Font.BOLD, 15));
                boardPanel.add(labels[i][j]);
            }
        }
    }

    @Override
    public void showBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Piece p = board.getBoard()[i][j];
                String text = ".";
                if (p.getState() == PieceState.BLACK) {
                    text = "B";
                } else if (p.getState() == PieceState.WHITE) {
                    text = "W";
                }
                labels[i][j].setText(text);
            }
        }
        window.validate();
    }
}
