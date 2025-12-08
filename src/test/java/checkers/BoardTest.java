package checkers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testSimpleMove() {
        Board b = new Board();

        Move m = new Move(new Position(6,1), new Position(5,2));
        boolean ok = b.applyMove(m, Piece.Color.WHITE);

        assertTrue(ok);
        assertNull(b.getPiece(new Position(6,1)));
        assertNotNull(b.getPiece(new Position(5,2)));
    }

    @Test
    public void testCapture() {
        Board b = new Board();

        // placement manuel
        b.setPiece(new Position(5,2), new Piece(Piece.Color.BLACK));

        Move m = new Move(new Position(6,1), new Position(4,3));
        boolean ok = b.applyMove(m, Piece.Color.WHITE);

        assertTrue(ok);
        assertNull(b.getPiece(new Position(5,2)));
    }
    
    
}
