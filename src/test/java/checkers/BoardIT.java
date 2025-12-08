package checkers;

public class BoardIT {

    public static void main(String[] args) {
        Board board = new Board();
        testInitialSetup(board);
        testSimpleMove(board);
        testCaptureMove(board);
        testPromotion(board);

        System.out.println("Tous les tests ont été exécutés.");
    }

    private static void testInitialSetup(Board board) {
        int blackCount = 0;
        int whiteCount = 0;

        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                Piece p = board.getPiece(new Position(r, c));
                if (p != null) {
                    if (p.getColor() == Piece.Color.BLACK) blackCount++;
                    if (p.getColor() == Piece.Color.WHITE) whiteCount++;
                }
            }
        }

        assert blackCount == 20 : "Nombre de pièces noires incorrect";
        assert whiteCount == 20 : "Nombre de pièces blanches incorrect";

        System.out.println("Initial setup test passed.");
    }

    private static void testSimpleMove(Board board) {
        Position from = new Position(6, 1);
        Position to = new Position(5, 0);

        boolean result = board.applyMove(new Move(from, to), Piece.Color.WHITE);
        assert result : "Mouvement simple échoué";
        assert board.getPiece(to) != null : "Pièce non déplacée";
        assert board.getPiece(from) == null : "Ancienne position non vide";

        System.out.println("Simple move test passed.");
    }

    private static void testCaptureMove(Board board) {
        // Préparer une situation de capture
        board.setPiece(new Position(4, 1), new Piece(Piece.Color.BLACK));
        Position from = new Position(5, 0);
        Position to = new Position(3, 2);

        boolean result = board.applyMove(new Move(from, to), Piece.Color.WHITE);
        assert result : "Capture échouée";
        assert board.getPiece(to) != null : "Pièce non déplacée après capture";
        assert board.getPiece(new Position(4, 1)) == null : "Pièce capturée toujours présente";

        System.out.println("Capture move test passed.");
    }

    private static void testPromotion(Board board) {
        // Déplacer une pièce blanche jusqu'à la première ligne
        Position from = new Position(1, 2);
        board.setPiece(from, new Piece(Piece.Color.WHITE));
        Position to = new Position(0, 3);

        boolean result = board.applyMove(new Move(from, to), Piece.Color.WHITE);
        assert result : "Promotion échouée";
        assert board.getPiece(to).getType() == Piece.Type.KING : "Pièce non promue";

        System.out.println("Promotion test passed.");
    }
}
