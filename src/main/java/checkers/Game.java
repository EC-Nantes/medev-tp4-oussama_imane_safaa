package checkers;

import java.util.Scanner;

public class Game {

    private Board board;
    private Piece.Color currentPlayer;

    public Game() {
        board = new Board();
        currentPlayer = Piece.Color.WHITE;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Jeu de Dames ===");

        while (true) {
            Utils.printBoard(board);
            System.out.println("Tour : " + currentPlayer);

            System.out.print("Coup (ex: 2,3 -> 3,4), save, load, quit : ");
            String input = sc.nextLine();

            switch (input) {
                case "quit":
                    return;
                case "save":
                    SaveManager.save(board, currentPlayer);
                    System.out.println("Partie sauvegardée.");
                    break;
                case "load":
                    SaveManager.LoadResult res = SaveManager.load();
                    board = res.board;
                    currentPlayer = res.player;
                    System.out.println("Partie chargée.");
                    break;
                default:
                    try {
                        Move move = Utils.parseMove(input);
                        if (board.applyMove(move, currentPlayer)) {
                            changePlayer();
                        } else {
                            System.out.println("Coup invalide !");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur de saisie");
                    }
            }
        }
    }

    private void changePlayer() {
        currentPlayer = (currentPlayer == Piece.Color.WHITE)
                ? Piece.Color.BLACK
                : Piece.Color.WHITE;
    }
}