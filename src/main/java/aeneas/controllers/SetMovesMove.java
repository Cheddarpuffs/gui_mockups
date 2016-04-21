package aeneas.controllers;
import aeneas.models.Level;
import aeneas.models.Level.LevelWithMoves;
import aeneas.models.Level.LevelType;
import aeneas.models.PuzzleLevel;
import aeneas.models.ReleaseLevel;

/**
 * Move action to set the allotted moves for a puzzle level
 * For use in the builder.
 *
 * @author Logan
 * @author jbkuszmaul
 */
public class SetMovesMove implements IMove {

  LevelWithMoves level;

  int oldMoves;
  int newMoves;

  /**
   * Constructor.
   *
   * @param level the level that is being edited
   * @param moves the amount of moves to set the level too. Positive.
   */
  public SetMovesMove(LevelWithMoves level, int moves) {
    this.level = level;
    this.newMoves = moves;
  }

  @Override
  public boolean execute() {
    if (!isValid()) return false;
    oldMoves = level.getAllowedMoves();
    level.setAllowedMoves(newMoves);
    return true;
  }

  @Override
  public boolean undo() {
    if (!isValid()) return false;
    level.setAllowedMoves(oldMoves);
    return true;
  }

  @Override
  public boolean isValid() {
    return level != null && newMoves >= 0;
  }

}
