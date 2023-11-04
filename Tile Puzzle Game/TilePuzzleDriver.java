
public class TilePuzzleDriver {
	
	public static final int MIN_GRID_SIZE = 3;
	public static final int MAX_GRID_SIZE = 8;	
	
	public static final int MAX_ATTEMPTS_INPUT_SIZE = 3;
	public static final int MAX_ATTEMPTS_SET_INIT_CONFIG = 4;

	public static void main(String[] args) {

		UserGameInterface io = new UI();    // object that interacts with the User/Player
		
		int gameBoardSize = io.getBoardSize();  // obtain the size of the game board
		
		boolean useDefault = io.useDefaultConfig();  // ask user for initial configuration of the tiles
		
		SlidingTilePuzzle theGame = new TilePuzzle( gameBoardSize, useDefault );
		
		io.display(theGame);     // display the initial state of the game
		
		// continue to play the game until the user Quits, or has won the game
		while ( ! theGame.hasWon() && ! theGame.hasQuit() ) 
		{
			// obtain the next game move
			Move nextMove = io.getMove( theGame.allCurrentLegalMoves() );  
			theGame.move(nextMove);        // make the next move in the game
			io.display(theGame);           // display the state of the game after this move
		}
		
		if ( theGame.hasWon() )
			io.congratulations( theGame.getNumMoves() );  // report number of moves to win game
		else 
			io.announceEndOfGame();
	}
}
