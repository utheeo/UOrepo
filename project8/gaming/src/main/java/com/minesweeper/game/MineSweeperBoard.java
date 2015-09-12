package com.minesweeper.game;

import java.util.Random;

/**
 * A MineSweeperBoard holds a representation of the contents of
 * the playing field for a Mine Sweeper game. The playing field
 * is represented using a 2 dimensional array of integer values.
 * The integer value stored in each cell of the array indicates
 * the icon which will appear in the coresponding cell of the
 * graphical user interface for the game. 
 *
 * @author Grant Braught
 * @author Tim Wahls
 * @author (YOUR NAME HERE)
 * @version (PUT DATE HERE)
 */
public class MineSweeperBoard {

    /**
     * A constant value representing a covered cell.
     * A covered cell is any cell which does not contains
     * a mine, has not been flagged and has not yet been
     * uncovered.
     */
    public static final int COVERED_CELL = -1;

    /**
     * A constant value representing a a cell that has
     * not been uncovered yet but contains a mine.
     */
    public static final int MINE = -2;

    /**
     * A constant value representing a cell which does not 
     * contain a mine but has had a flag placed on it.
     */
    public static final int FLAG = -3;

    /**
     * A constant value representing a cell which contains
     * a mine and has had a flag placed on it.
     */
    public static final int FLAGGED_MINE = -4;

    /**
     * A constant value representing a cell containing a mine that
     * has been uncovered.
     */
    public static final int UNCOVERED_MINE = -5;

    /**
     * A constant value representing the contents of an invalid cell.
     * This value is returned by the getCell method when an invalid
     * cell is specified.
     */
    public static final int INVALID_CELL = -10;

    /**
     * A constant value representing the easiest level of play.
     */
    public static final int BEGINNER_LEVEL = 1;
    
    /**
     * A constant value representing an intermediate level of play.
     */
    public static final int INTERMEDIATE_LEVEL = 2;
    
    /**
     * A constant value representing the hardest level of play. 
     */
    public static final int EXPERT_LEVEL = 3;

    /**
     * Construct a new fixed MineSweeperBoard for testing purposes.
     * The board should have 3 rows and 4 columns.  All cells should
     * contain COVERED_CELL, except that locations (0, 0) and (2, 1)
     * should contain MINE.
     */
    int Rows, Cols, Mines;
    int board[][];
    
    
    public MineSweeperBoard() {
    	Rows = 3; Cols = 4;
    	board = new int[Rows][Cols];
    	
    	for(int i = 0; i < Rows; i++)
    		for (int j = 0; j < Cols; j++)
    			board[i][j] = COVERED_CELL;
    	board[0][0] = MINE;
    	board[2][1] = MINE;
    	
    }

    /**
     * pre: level == BEGINNER_LEVEL || level == INTERMEDIATE_LEVEL ||
     *      level == EXPERT_LEVEL;
     * 
     * Construct a new MineSweeperBoard for play at the 
     * specified level.  The size of the board and the number
     * of mines are determined by the level of play. Valid levels
     * of play are indicated by the constants BEGINNER_LEVEL,
     * INTERMEDIATE_LEVEL and EXPERT_LEVEL.  The size of the board
     * and the number of cells which contain mines is as
     * follows:
     * <pre>
     * <U>
     * Level:              Board Size (RxC):   Mines:</U>        
     * BEGINNER_LEVEL      5x10                3
     * INTERMEDIATE_LEVEL  10x15               15
     * EXPERT_LEVEL        15x20               45
     * </pre>
     *
     * @param level the level of play.
     */
    public MineSweeperBoard(int level) {
    	if (level == BEGINNER_LEVEL){
    		Rows = 5;
    		Cols = 10;
    		Mines = 3;
    	}
    	else if(level == INTERMEDIATE_LEVEL){
    		Rows = 10;
    		Cols = 15;
    		Mines = 15;
    	}
    	else if(level == EXPERT_LEVEL){
    		Rows = 15;
    		Cols = 20;
    		Mines = 45;
    	}
    	else{ //If level other than 1, 2, or 3 is entered then no board will show indicating invalid
    		Rows = 0;
    		Cols = 0;
    	}
    	
    	board = new int[Rows][Cols];
    	
    	for(int i = 0; i < Rows; i++)
    		for (int j = 0; j < Cols; j++)
    			board[i][j] = COVERED_CELL;
    	
    	int mineCount = 0;
    	int x, y;
    	while(mineCount < Mines){
    		Random rnd = new Random();
    		x = rnd.nextInt(Rows);
    		y = rnd.nextInt(Cols);
    		if (board[x][y] == COVERED_CELL){
    			board[x][y] = MINE;
    			mineCount++;
    		}
    	}
    	
     }

    /**
     * Get the number of rows in this MineSweeperBoard.
     *
     * @return the number of rows in this MineSweeperBoard.
     */
    public int getRows() {
        return Rows;
    }

    /**
     * Get the number of columns in this MineSweeperBoard.
     *
     * @return the number of columns in this MineSweeperBoard.
     */
    public int getColumns() {
        return Cols;
    }

    /**
     * Get the number of mines in this MineSweeperBoard.
     *
     * @return the number of mines in this MineSweeperBoard.
     */
    public int getNumMines() {
        int mineCount =0;
        
        for (int i =0; i < Rows; i++)
        	for (int j =0; j < Cols; j++)
        		if(board[i][j] == MINE || board[i][j] == FLAGGED_MINE || board[i][j] == UNCOVERED_MINE)
        			mineCount++;
    	
    	return mineCount;
    }

    /**
     * Get the contents of the specified cell on this MineSweeperBoard.
     * The value returned from this method must be one of the defined
     * constants (COVERED_CELL, MINE, FLAG, FLAGGED_MINE, UNCOVERED_MINE)
     * or a non-negative integer representing the number of mines adjacent
     * to the cell.
     *
     * @param row the row containing the cell.
     * @param col the column containing the cell.
     * @return the value contained in the cell specified by row and col,
     *         or INVALID_CELL if the specified cell does not exist.
     */
    public int getCell(int row, int col) {
    	if(row >= Rows || col >= Cols || row < 0 || col < 0)
    		return INVALID_CELL;
    	else 
    		return board[row][col];
    	
    }

    /**
     * Count the number of mines that appear in cells that 
     * are adjacent to the specified cell. 
     * 
     * @param row the row of the cell.
     * @param col the column of the cell.
     * @return the number of mines adjacent to the specified cell.
     *         if the specified cell is invalid (not on the board),
     *         return 0;
     */
    public int numAdjMines(int row, int col) {
    	if(getCell(row, col) == INVALID_CELL)	
    		return 0;
    	else{
    		int mineCount = 0;
    		for(int i = row-1; i<= row+1; i++)
    			for(int j = col-1; j <= col+1; j++){
    				if(i == row && j == col)
    					;
    				else if(getCell(i,j) == MINE || getCell(i,j) == FLAGGED_MINE || getCell(i,j) == UNCOVERED_MINE)
            			mineCount++;
        	
    			}
    		return mineCount;    				
    	}
    }

    /**
     * Uncover the specified cell. If the cell already contains a flag it should
     * not be uncovered. If there is not a mine under the
     * specified cell then the value in that cell is changed to the
     * number of mines that appear in adjacent cells. If there is a mine 
     * under the specified cell the game is over and the player has lost. 
     * If the specified cell is already uncovered or is invalid, no change 
     * is made to the board.
     *
     * @param row the row of the cell to be uncovered.
     * @param col the column of the cell to be uncovered. 
     */
    public void uncoverCell(int row, int col) {
    	if (getCell(row, col) == INVALID_CELL)
    		;
    	else if (getCell(row,col) == COVERED_CELL)
    		board[row][col] = numAdjMines(row, col);
    	else if (getCell(row,col) == MINE)
    		board[row][col] = UNCOVERED_MINE;
    	if(getCell(row,col) == 0)
    		for(int i = row-1; i <= row+1; i++)
    			for(int j = col-1; j <= col+1; j++)
    				if(i == row && j == col)
    					;
    				else
    					if(getCell(i,j) == COVERED_CELL)
    						uncoverCell(i,j);
    			
    }

    /**
     * Place or remove a flag from the specified cell. If the cell currently
     * covered then place a flag on the cell.  If the cell currently contains
     * a flag, remove that flag but do not uncover the cell. If the cell has
     * already been uncovered or is invalid, no change is made to the board.
     *
     * @param row the row of the cell to be flagged/unflagged.
     * @param col the column of the cell to be flagged/unflagged.
     */
    public void flagCell(int row, int col) {
    	if(getCell(row,col) == INVALID_CELL)
    		;
    	else if(getCell(row,col) == COVERED_CELL)
    		board[row][col] = FLAG;
    	else if(getCell(row,col) == FLAG)
    		board[row][col] = COVERED_CELL;
    	else if(getCell(row,col) == MINE)
    		board[row][col] = FLAGGED_MINE;
    	else if(getCell(row,col) == FLAGGED_MINE)
    		board[row][col] = MINE;
    }

    /**
     * Uncover all of the cells on the board.
     */
    public void revealBoard() {
    	for(int i = 0; i < Rows; i++)
    		for(int j = 0; j < Cols; j++){
    			if (getCell(i,j) == FLAG || getCell(i,j) == FLAGGED_MINE)
    				flagCell(i,j);
    			uncoverCell(i,j);
    		}
    }

    /**
     * Determine if the player has lost the current game. The game
     * is lost if the player has uncovered a mine.
     *
     * @return true if the current game has been lost and false
     *         otherwise.
     */
    public boolean gameLost() {
    	for(int i = 0; i < Rows; i++)
    		for(int j = 0; j < Cols; j++)
    			if(getCell(i,j) == UNCOVERED_MINE)
    				return true;
    			
        return false;
    }
    
    /**
     * Determine if the player has won the current game.
     * The game is won when three conditions are met:
     * <OL>
     * <LI>Flags have been placed on all of the mines.
     * <LI>No flags have been placed incorrectly.
     * <LI>All non-flagged cells have been uncovered.
     * </OL>
     *
     * @return true if the current game has been won and
     *         false otherwise.
     */
    public boolean gameWon() {
    	int correctFlagCount = 0;
    	
    	for(int i = 0; i < Rows; i++)
    		for(int j = 0; j < Cols; j++){
    			if(getCell(i,j) == COVERED_CELL || getCell(i,j) == FLAG || getCell(i,j) == MINE)
    				return false;
    			if(getCell(i,j) == FLAGGED_MINE)
    				correctFlagCount++;
    		}
    		return true;
    	
    }

}

