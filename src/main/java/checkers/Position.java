package checkers;


public class Position {
    public int row;
    public int col;

    public Position(int r, int c) {
        row = r;
        col = c;
    }

    public static Position midpoint(Position a, Position b) {
        return new Position((a.row + b.row) / 2, (a.col + b.col) / 2);
    }
}
