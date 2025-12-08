package checkers;

import java.util.Scanner;

public class Game {

    private Board board;
    private Joueur joueurBlanc;
    private Joueur joueurNoir;
    private Joueur currentPlayer;

    public Game() {
        board = new Board();
        joueurActuel = joueurBlanc;
        joueurBlanc = new Joueur("Blanc", Piece.Color.WHITE);
        joueurNoir = new Joueur("Noir", Piece.Color.BLACK);

    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Jeu de Dames ===");

        while (true) {
            Utils.printBoard(board);
            System.out.println("Tour : " + joueurActuel);

            System.out.print("Coup (ex: 2,3 -> 3,4), save, load, quit : ");
            String input = sc.nextLine();

            switch (input) {
                case "quit":
                    return;
                case "save":
                    SaveManager.save(board, joueurActuel);
                    System.out.println("Partie sauvegardée.");
                    break;
                case "load":
                    SaveManager.LoadResult res = SaveManager.load();
                    board = res.board;
                    joueurActuel = res.player;
                    System.out.println("Partie chargée.");
                    break;
                default:
                    try {
                        Move move = Utils.parseMove(input);
                        if (board.applyMove(move, joueurActuel.getCouleur())) {
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
            joueurActuel = (joueurActuel == joueurBlanc) ? joueurNoir : joueurBlanc;

    }
}