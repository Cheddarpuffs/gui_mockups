package aeneas.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import aeneas.models.Bullpen;
import aeneas.models.Level;
import aeneas.models.LightningLevel;
import aeneas.models.PuzzleLevel;
import aeneas.models.ReleaseLevel;

public class TestSetTimeMove {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testLightning() {
    int oldTime = 0, newTime = 100;
    LightningLevel test = new LightningLevel(null, oldTime);
    IMove move = new SetTimeMove(test, newTime);
    assertEquals(oldTime, test.getAllowedTime());
    assertTrue(move.isValid());
    assertTrue(move.execute());
    assertEquals(newTime, test.getAllowedTime());
    assertTrue(move.undo());
    assertEquals(oldTime, test.getAllowedTime());
  }

  @Test
  public void testNullFails() {
    LightningLevel nullLevel = null;
    IMove move = new SetTimeMove(nullLevel, 100);
    assertFalse(move.isValid());
    assertFalse(move.execute());
    assertFalse(move.undo());
  }

  @Test
  public void testNegativeTime() {
    PuzzleLevel test = new PuzzleLevel(new Bullpen());
    IMove move = new SetMovesMove(test, -100);
    assertFalse(move.isValid());
    assertFalse(move.execute());
    assertFalse(move.undo());
  }
}
