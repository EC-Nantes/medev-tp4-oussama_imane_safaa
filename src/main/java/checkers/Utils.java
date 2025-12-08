package checkers;

public class Joueur {

    private String nom;
    private Piece.Color couleur;
    private int score;

    public Joueur(String nom, Piece.Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.score = 0;
    }

    public String getNom() {
        return nom;
    }

    public Piece.Color getCouleur() {
        return couleur;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }

    @Override
    public String toString() {
        return nom + " (" + couleur + ", score=" + score + ")";
    }
}