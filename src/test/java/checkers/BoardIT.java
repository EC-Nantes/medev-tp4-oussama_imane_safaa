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
 * @author user
 */
public class BoardIT {
    
    public BoardIT() {
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
     * Test of getPiece method, of class Board.
     */
    @Test
    public void testGetPiece() {
        System.out.println("getPiece");
        Position p = null;
        Board instance = new Board();
        Piece expResult = null;
        Piece result = instance.getPiece(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPiece method, of class Board.
     */
    @Test
    public void testSetPiece() {
        System.out.println("setPiece");
        Position p = null;
        Piece piece = null;
        Board instance = new Board();
        instance.setPiece(p, piece);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyMove method, of class Board.
     */
    @Test
    public void testApplyMove() {
        System.out.println("applyMove");
        Move m = null;
        Piece.Color player = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.applyMove(m, player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
