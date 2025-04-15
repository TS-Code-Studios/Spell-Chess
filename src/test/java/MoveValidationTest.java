import com.javadevs.ChessGameHandler;
import org.junit.Test;

public class MoveValidationTest {
    @Test
    public void coverTest() {
        ChessGameHandler testGame = new ChessGameHandler();
        System.out.println(testGame.isMovePossible("p", "d2", "d4"));
    }
}
