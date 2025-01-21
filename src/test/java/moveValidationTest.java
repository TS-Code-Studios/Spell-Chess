import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.javadevs.ChessGameHandler;

public class moveValidationTest {
    ChessGameHandler testGame = new ChessGameHandler();

    @Test
    public void testPawns() {
        assertEquals(true, testGame.isMovePossible("p", "e2", "e4", "w"));
        assertEquals(true, testGame.isMovePossible("p", "f1", "f2", "w"));
        assertEquals(false, testGame.isMovePossible("p", "a1", "a3", "w"));
    }

    @Test
    public void testKnights() {
        assertEquals(true, testGame.isMovePossible("n", "b1", "c3", "w"));
        assertEquals(true, testGame.isMovePossible("n", "b1", "a3", "w"));
        assertEquals(true, testGame.isMovePossible("n", "g1", "f3", "w"));
        assertEquals(false, testGame.isMovePossible("n", "b1", "b3", "w"));
    }
}