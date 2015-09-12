
package com.minesweeper.game;


import junit.framework.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import com.minesweeper.game.MineSweeperBoard;


public class TestMineSweeper extends TestCase {

	
	public void testgetRows() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		int rows = myBoard.getRows();
		assertEquals( rows, 3);
	}
	
	
	public void testgetColumns() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		int cols = myBoard.getColumns();
		assertEquals( cols, 4);
	}
	
	
	public void testgetNumMines() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		int mines = myBoard.getNumMines();
		assertEquals( mines, 2);
	}
	
	
	public void testgetCell() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		int cell1 = myBoard.getCell(0,0);
		assertEquals( cell1, -2);
		int cell2 = myBoard.getCell(2,1);
		assertEquals( cell2, -2);
		int cell3 = myBoard.getCell(0,1);
		assertEquals( cell3, -1);
		int cell4 = myBoard.getCell(1,0);
		assertEquals( cell4, -1);
		int cell5 = myBoard.getCell(0,3);
		assertEquals( cell5, -1);
		int cell6 = myBoard.getCell(-1,0);
		assertEquals( cell6, -10);
		int cell7 = myBoard.getCell(0,-2);
		assertEquals( cell7, -10);
		int cell8 = myBoard.getCell(4,3);
		assertEquals( cell8, -10);
		int cell9 = myBoard.getCell(0,5);
		assertEquals( cell9, -10);

	}
	
	
	public void testlevel1Board() {
		MineSweeperBoard myBoard = new MineSweeperBoard(1);
		int rows = myBoard.getRows();
		assertEquals( rows, 5);
		int cols = myBoard.getColumns();
		assertEquals( cols, 10);
		int mines = myBoard.getNumMines();
		assertEquals( mines, 3);		
	}
	
	
	public void testlevel2Board() {
		MineSweeperBoard myBoard = new MineSweeperBoard(2);
		int rows = myBoard.getRows();
		assertEquals( rows, 10);
		int cols = myBoard.getColumns();
		assertEquals( cols, 15);		
		int mines = myBoard.getNumMines();
		assertEquals( mines, 15);		
	}

	
	public void testlevel3Board() {
		MineSweeperBoard myBoard = new MineSweeperBoard(3);
		int rows = myBoard.getRows();
		assertEquals( rows, 15);
		int cols = myBoard.getColumns();
		assertEquals( cols, 20);		
		int mines = myBoard.getNumMines();
		assertEquals( mines, 45);		
	}
	
	public void testnumAdjMines() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		int adjMines = myBoard.numAdjMines(0,3);
		assertEquals( adjMines, 0);
		adjMines = myBoard.numAdjMines(1,0);
		assertEquals( adjMines, 2);
		adjMines = myBoard.numAdjMines(2,0);
		assertEquals( adjMines, 1);		
	}
	

	public void testuncoverCell() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		myBoard.uncoverCell(0,3);
		assertEquals(myBoard.getCell(0,3), 0);
		myBoard.uncoverCell(0,0);
		assertEquals(myBoard.getCell(0,0), -5);
	}

	
	public void testflagCell() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		myBoard.flagCell(0,3);
		assertEquals(myBoard.getCell(0,3), -3);
		myBoard.flagCell(0,0);
		assertEquals(myBoard.getCell(0,0), -4);
		myBoard.flagCell(0,3);
		assertEquals(myBoard.getCell(0,3), -1);
		myBoard.flagCell(0,0);
		assertEquals(myBoard.getCell(0,0), -2);
	}
	
	
	public void testrevealBoard() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		myBoard.revealBoard();
		assertEquals(myBoard.getCell(0,0), -5);
		assertEquals(myBoard.getCell(2,1), -5);
		assertEquals(myBoard.getCell(0,1), 1);
		assertEquals(myBoard.getCell(0,2), 0);
		assertEquals(myBoard.getCell(0,3), 0);
		assertEquals(myBoard.getCell(1,0), 2);
		assertEquals(myBoard.getCell(1,1), 2);
		assertEquals(myBoard.getCell(1,2), 1);
		assertEquals(myBoard.getCell(1,3), 0);
		assertEquals(myBoard.getCell(2,0), 1);
		assertEquals(myBoard.getCell(2,2), 1);
		assertEquals(myBoard.getCell(2,3), 0);		
	}

	
	public void testgameLost1() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		assertEquals(myBoard.gameLost(), false);
		myBoard.uncoverCell(0,0);
		assertEquals(myBoard.gameLost(), true);
	}
	
	
	public void testgameLost2() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		assertEquals(myBoard.gameLost(), false);
		myBoard.uncoverCell(2,1);
		assertEquals(myBoard.gameLost(), true);
	}

	
	public void testgameWon() {
		MineSweeperBoard myBoard = new MineSweeperBoard();
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(0,3);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(1,2);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(2,2);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(1,1);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(0,1);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(1,0);
		assertEquals(myBoard.gameWon(), false);
		myBoard.uncoverCell(2,0);
		assertEquals(myBoard.gameWon(), false);
		myBoard.flagCell(0,0);
		assertEquals(myBoard.gameWon(), false);
		myBoard.flagCell(2,1);
		assertEquals(myBoard.gameWon(), true);
		
		
	}
}
