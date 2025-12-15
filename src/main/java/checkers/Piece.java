package checkers;

public class Piece {

    public enum Color { WHITE, BLACK }
    public enum Type { PAWN, KING }

    private Color color;
    private Type type;

    public Piece(Color c) {
        color = c;
        type = Type.PAWN;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public void promote() {
        type = Type.KING;
    }
}
