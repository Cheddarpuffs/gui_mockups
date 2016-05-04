package aeneas.models;

/**
 *
 * @author Joseph Martin
 * @author jbkuszmaul
 */
public class PuzzleBoard extends Board implements java.io.Serializable {
  PuzzleBoard() {
    super();
  }

  PuzzleBoard(Board board) {
    super(board);
  }

  @Override
  public Object clone() {
    PuzzleBoard newBoard = new PuzzleBoard();
    super.copy(this, newBoard);
    return newBoard;
  }
}
