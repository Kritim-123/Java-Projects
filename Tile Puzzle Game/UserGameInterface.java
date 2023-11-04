

import java.util.List;

public interface UserGameInterface {
	
	/**
	 * query the user to enter the size of the game board
	 * 
	 * @return the number of rows (and columns) in the game grid
	 */
	int getBoardSize();
	
	/** 
	 * query the user for the desired initial configuartion of the puzzle
	 * 
	 * @return true if the initial configuration should be the default configuration
	 */
	boolean useDefaultConfig();
	
	/**
	 * query the user for the next move to make in the game
	 * @param legalMoves is a lest of moves that are legal for
         * the current game configuration
	 * @return the next Move to make in the game
	 */
	Move getMove( List<Move> legalMoves );
	
	/**
	 * display to the output the current configuration of the tiles
	 * 
	 * @param theGame game to be displayed
	 */
	void display( SlidingTilePuzzle theGame );
	
	/**
	 * display to the output a "congratulations" message to the user,
	 * which includes the number of moves made to win the game
	 * 
	 * @param numberMovesToWin the number of moves in the game to this point
	 */
	void congratulations( int numberMovesToWin );
	
	/**
	 * display to the output a message indicating that the game 
	 * has terminated
	 */
	void announceEndOfGame();
}
