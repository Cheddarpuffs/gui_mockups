package aeneas.views;

import aeneas.models.Board;
import aeneas.models.Square;
import aeneas.models.Piece;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;


/**
 * View class to display a board
 * @author Logan Tutt
 * @author Joseph Martin
 */
public class BoardView extends GridPane {

  /** Specifies how many pixels the squares of a piece on the board will be */
  static final int SQUARE_SIZE = 40;

  public interface SquareClickListener {
    public void squareClicked(int row, int col);
  }

  SquareView[][] grid = new SquareView[Board.SIZE][Board.SIZE];
  Board board;
  SquareClickListener listener;

  /**
   * Initialized the board with grey squares
   *
   * @param board
   *          the board model object. Eventually this will model object will
   *          describe which squares are active
   */
  public BoardView(Board board) {
    listener = null;
    this.board = board;
    refresh();

    // This handle the drop of a piece on the board
    this.setOnDragDropped((DragEvent event) -> {
      Dragboard db = event.getDragboard();

      //use this to draw the piece on the board
      Piece pieceModel = (Piece) db.getContent(Piece.dataFormat);

      // this might change we we actually implement it,
      // such as if they drop it on a square that doesn't exist
      event.setDropCompleted(true);
      event.consume();

    });

    //this is absolutely nessecary
    this.setOnDragOver((DragEvent event) -> {
      event.acceptTransferModes(TransferMode.MOVE);
      event.consume();
    });
  }

  public void setSquareClickListener(SquareClickListener listener) {
    this.listener = listener;
  }

  /**
   * Refreshes the view to match the current state of the board
   */
  public void refresh(){
    Square[][] squares = board.assembleSquares();
    for (int i = 0; i < Board.SIZE; i++) {
      for (int j = 0; j < Board.SIZE; j++) {
        grid[j][i] = new SquareView(SQUARE_SIZE, squares[j][i]);
        final int row = j;
        final int col = i;
        grid[j][i].setOnMouseClicked((e) -> { listener.squareClicked(row, col); });
        this.add(grid[j][i], i, j);
      }
    }
  }
}
