package checkers;

import java.io.*;

public class SaveManager {

    public static class LoadResult {
        public Board board;
        public Piece.Color player;

        public LoadResult(Board b, Piece.Color p) {
            board = b;
            player = p;
        }
    }

    public static void save(Board b, Piece.Color player) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(b);
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LoadResult load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("save.bin"))) {
            Board b = (Board) ois.readObject();
            Piece.Color p = (Piece.Color) ois.readObject();
            return new LoadResult(b, p);
        } catch (Exception e) {
            return new LoadResult(new Board(), Piece.Color.WHITE);
        }
    }
}
