package checkers;

public class Board {

    public static final int SIZE = 10;
    private Piece[][] grid = new Piece[SIZE][SIZE];

    public Board() {
        initPieces();
    }

    public Piece getPiece(Position p) {
        return grid[p.row][p.col];
    }

    public void setPiece(Position p, Piece piece) {
        grid[p.row][p.col] = piece;
    }

    private void initPieces() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = null;
            }
        }

        // Noirs en haut
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < SIZE; c++) {
                if ((r + c) % 2 == 1)
                    grid[r][c] = new Piece(Piece.Color.BLACK);
            }
        }
        // Blancs en bas
        for (int r = 6; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if ((r + c) % 2 == 1)
                    grid[r][c] = new Piece(Piece.Color.WHITE);
            }
        }
    }

    public boolean applyMove(Move m, Piece.Color player) {
        Piece p = getPiece(m.from);

        if (p == null || p.getColor() != player)
            return false;
        if (getPiece(m.to) != null)
            return false;

        int dr = m.to.row - m.from.row;
        int dc = Math.abs(m.to.col - m.from.col);

        // mouvement simple
        if (Math.abs(dr) == 1 && dc == 1)
            return simpleMove(m, p);

        // capture
        if (Math.abs(dr) == 2 && dc == 2)
            return captureMove(m, p, player);

        return false;
    }

    private boolean simpleMove(Move m, Piece p) {
        setPiece(m.to, p);
        setPiece(m.from, null);
        promoteIfNeeded(m.to, p);
        return true;
    }

    private boolean captureMove(Move m, Piece p, Piece.Color player) {
        Position mid = Position.midpoint(m.from, m.to);
        Piece target = getPiece(mid);

        if (target == null || target.getColor() == player)
            return false;

        // capture
        setPiece(mid, null);
        setPiece(m.to, p);
        setPiece(m.from, null);
        promoteIfNeeded(m.to, p);
        return true;
    }

    private void promoteIfNeeded(Position pos, Piece p) {
        if (p.getType() == Piece.Type.KING) return;

        if (p.getColor() == Piece.Color.WHITE && pos.row == 0)
            p.promote();
        if (p.getColor() == Piece.Color.BLACK && pos.row == SIZE - 1)
            p.promote();
    }
}
