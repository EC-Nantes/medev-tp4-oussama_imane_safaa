package checkers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    public void testParseMove() {
        Move m = Utils.parseMove("2,3 -> 4,5");

        assertEquals(2, m.from.row);
        assertEquals(3, m.from.col);
        assertEquals(4, m.to.row);
        assertEquals(5, m.to.col);
    }
}