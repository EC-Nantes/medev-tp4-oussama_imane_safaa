package checkers;

public class Utils {

    public static Move parseMove(String s) {
        String[] parts = s.split("->");
        String[] a = parts[0].trim().split(",");
        String[] b = parts[1].trim().split(",");

        Position from = new Position(
                Integer.parseInt(a[0]),
                Integer.parseInt(a[1])
        );
        Position to = new Position(
                Integer.parseInt(b[0]),
                Integer.parseInt(b[1])
        );

        return new Move(from, to);
    }

    public static void printBoard(Board b) {
        System.out.println("   0 1 2 3 4 5 6 7 8 9");

        for (int r = 0; r < Board.SIZE; r++) {
            System.out.print(r + "  ");

            for (int c = 0; c < Board.SIZE; c++) {
                Piece p = b.getPiece(new Position(r, c));
                if (p == null) {
                    System.out.print(". ");
                } else if (p.getColor() == Piece.Color.WHITE) {
                    System.out.print("w ");
                } else {
                    System.out.print("b ");
                }
            }
            System.out.println();
        }
    }
}