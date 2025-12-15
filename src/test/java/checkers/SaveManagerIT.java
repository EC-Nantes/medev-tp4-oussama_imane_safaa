/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package checkers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author dodi
 */
public class SaveManagerIT {
    
    public SaveManagerIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test Sauvegarde + chargement d’un plateau initial
     */
    
    @Test
    public void testSaveAndLoadInitialBoard() {
        Board b = new Board();
        Piece.Color current = Piece.Color.WHITE;

        // Save
        SaveManager.save(b, current);

        // Load
        SaveManager.LoadResult res = SaveManager.load();

        assertNotNull(res);
        assertNotNull(res.board);
        assertEquals(current, res.player);

        Board loaded = res.board;

        // Compare board contents
        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                Piece p1 = b.getPiece(new Position(r,c));
                Piece p2 = loaded.getPiece(new Position(r,c));

                if (p1 == null) {
                    assertNull(p2, "Expected null at ("+r+","+c+")");
                } else {
                    assertNotNull(p2, "Expected piece at ("+r+","+c+")");
                    assertEquals(p1.getColor(), p2.getColor());
                    assertEquals(p1.getType(), p2.getType());
                }
            }
        }
    }


    /**
     * Test Sauvegarde après modification du plateau
     */
    
   
    @Test
    public void testSaveAfterMove() {
        Board b = new Board();

        // On part d’un plateau VIDE pour contrôler exactement la position
        for (int r = 0; r < Board.SIZE; r++) {
            for (int c = 0; c < Board.SIZE; c++) {
                b.setPiece(new Position(r, c), null);
            }
        }

        // On place un seul pion noir en (2,1)
        Position from = new Position(2, 1);
        Position to   = new Position(3, 2);
        Piece blackPawn = new Piece(Piece.Color.BLACK);
        b.setPiece(from, blackPawn);
        b.setPiece(to, null); // destination vide

        // Ce coup DOIT être valide
        Move m = new Move(from, to);
        boolean ok = b.applyMove(m, Piece.Color.BLACK);
        assertTrue(ok, "Le coup devrait être valide");

        // On sauvegarde l'état modifié
        SaveManager.save(b, Piece.Color.BLACK);

        // On recharge
        SaveManager.LoadResult res = SaveManager.load();
        Board loaded = res.board;

        // Vérifications : pion déplacé correctement
        assertNull(loaded.getPiece(from), "La case de départ doit être vide après le coup");
        Piece p2 = loaded.getPiece(to);
        assertNotNull(p2, "La case d'arrivée doit contenir une pièce après le coup");
        assertEquals(Piece.Color.BLACK, p2.getColor());
        assertEquals(Piece.Type.PAWN, p2.getType());
    }

    /**
     * Vérifier que la couleur du joueur est bien sauvegardée
     */
    @Test
    public void testPlayerColorPersistence() {
        Board b = new Board();

        SaveManager.save(b, Piece.Color.BLACK);

        SaveManager.LoadResult res = SaveManager.load();
        assertNotNull(res);

        assertEquals(Piece.Color.BLACK, res.player);
    }
    @Test
    public void checkInitialPiece() {
        Board b = new Board();
        Piece p = b.getPiece(new Position(2,1));
        assertNotNull(p);
        assertEquals(Piece.Color.BLACK, p.getColor());
}
}
