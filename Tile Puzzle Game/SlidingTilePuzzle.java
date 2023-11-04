

import java.util.List;

public interface SlidingTilePuzzle {
	
	/**
	 * @return true if-and-only-if the puzzle is in the
	 * winning configuration
	 */
	boolean hasWon();
	
	/**
	 * @return true if-and-only-if the player has quit
	 * playing the game
	 */
	boolean hasQuit();
	
	/**
	 * Make the specified move in the game, if it possible
	 * to perform that move given the current state of the game
	 */
	void move( Move theNextMove );
	
	/** 
	 * @return a List of all the moves that are legal
	 * based on the current state of the game
	 */
	public List<Move> allCurrentLegalMoves();
	
	/**
	 * @return the number of valid moves made in the game so far
	 */
	int getNumMoves();
}
