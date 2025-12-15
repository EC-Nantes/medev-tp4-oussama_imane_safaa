package checkers;

import java.io.*;

public class SaveManager {

    private static final String FILE = "save.txt";

    // --------------------------
    //  Structure de retour load()
    // --------------------------
    public static class LoadResult {
        public Board board;
        public Piece.Color player;

        public LoadResult(Board board, Piece.Color player) {
            this.board = board;
            this.player = player;
        }
    }

    // --------------------------
    //        SAVE
    // --------------------------
    public static void save(Board board, Piece.Color player) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {

            // Sauvegarde du plateau
            for (int r = 0; r < Board.SIZE; r++) {
                for (int c = 0; c < Board.SIZE; c++) {

                    Piece p = board.getPiece(new Position(r, c));

                    if (p == null) {
                        pw.print(". ");   // case vide
                    } else {
                        char col = (p.getColor() == Piece.Color.WHITE) ? 'W' : 'B';
                        char type = (p.getType() == Piece.Type.KING) ? 'K' : 'P';
                        pw.print("" + col + type + " ");
                    }
                }
                pw.println();
            }

            // Sauvegarde du joueur
            pw.println("PLAYER=" + player);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --------------------------
    //        LOAD
    // --------------------------
    public static LoadResult load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {

            Board board = new Board();

            // On efface le plateau avant de charger
            for (int r = 0; r < Board.SIZE; r++)
                for (int c = 0; c < Board.SIZE; c++)
                    board.setPiece(new Position(r, c), null);

            // Lecture ligne par ligne
            for (int r = 0; r < Board.SIZE; r++) {
                String[] tokens = br.readLine().split(" ");

                for (int c = 0; c < Board.SIZE; c++) {

                    String t = tokens[c];

                    if (t.equals(".")) {
                        continue;  // case vide
                    }

                    // Recréation de la pièce
                    Piece.Color col = (t.charAt(0) == 'W') ? Piece.Color.WHITE : Piece.Color.BLACK;
                    Piece piece = new Piece(col);

                    if (t.charAt(1) == 'K') {
                        piece.promote();
                    }

                    board.setPiece(new Position(r, c), piece);
                }
            }

            // Dernière ligne : joueur
            String line = br.readLine();
            Piece.Color player = Piece.Color.valueOf(line.split("=")[1]);

            return new LoadResult(board, player);

        } catch (Exception e) {
            e.printStackTrace();
            return new LoadResult(new Board(), Piece.Color.WHITE);
        }
    }
}
