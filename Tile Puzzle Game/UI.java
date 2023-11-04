import java.util.*;
import javax.swing.JOptionPane;

/**
 * Author: Kritim Bastola
 * GUI for the puzzle Game
 */

public class UI implements UserGameInterface
{


    /**
      query the user to enter the size of the game board
      Handles the number of rows and column section
      Promts user to enter the number of rows/column and checks it
      @return the number of rows (and columns) in the game grid
     */
    public int getBoardSize() {
        // All the variable needed in the method
        int numberOfTries = 0;
        int numberOfRows;
        String input;
        // Handles the number of rows and column section
        //Promts user to enter the number of rows/column and checks it
        while(true)
        {
            if(numberOfTries == TilePuzzleDriver.MAX_ATTEMPTS_INPUT_SIZE)
            {
                // TO show user a message once they have reached their max limit of tries.
                JOptionPane.showMessageDialog(null,
                        "You didn't correctly choose number of row and columns");
                System.exit(42);
            }

            try{
                //Asking user for input
                input = JOptionPane.showInputDialog("Enter the number of rows and columns you want");
                numberOfRows = Integer.parseInt(input);

                if(numberOfRows <TilePuzzleDriver.MIN_GRID_SIZE || numberOfRows > TilePuzzleDriver.MAX_GRID_SIZE) {
                    numberOfTries++;
                }
                else
                {
                    return numberOfRows;
                }
            }
            catch (NumberFormatException e){
                numberOfTries++;
            }


        }
    }


    /**
     * query the user for the desired initial configuration of the puzzle
     *Handles which type of config should we give at first
     * Checks if the given input is correct or not
     * @return true if the initial configuration should be the default configuration
     */
    @Override
    public boolean useDefaultConfig() {

        // Variables to be used in the method
        int numberOfAttempt = 0;
        boolean isDefault = false;


        while (true)
        {
            if(numberOfAttempt == TilePuzzleDriver.MAX_ATTEMPTS_SET_INIT_CONFIG)
            {
                JOptionPane.showMessageDialog(null, "You didn't enter proper configuration.");
                System.exit(42);
            }
            String config = JOptionPane.showInputDialog("Enter D for DEFAULT and R for RANDOM configuration.");

            config = config.toLowerCase();

            if(config.equals("d"))
            {
                isDefault = true;
                return isDefault;
            }

            else if(config.equals("r"))
            {
                return isDefault;
            }
            else {
                numberOfAttempt++;
            }

        }
    }

    @Override
    /**
     * query the user for the next move to make in the game
     * Displays the possible options user can choose
     * Handles what happens when user click close button
     * @param legalMoves is a list of moves that are legal for
     * the current game configuration
     * @return the next Move to make in the game
     */
    public Move getMove(List<Move> legalMoves) {
        String individualOption;
        Move theNextMove = null;

        String[] moveChoices = new String[legalMoves.size()];


        for(int i=0; i<legalMoves.size(); i++)
        {
            individualOption = legalMoves.get(i).toString();
            moveChoices[i] = individualOption;

        }

        try
        {
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "What move do you want?",
                    "Tile Puzzle Move",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    moveChoices,
                    moveChoices[moveChoices.length-1]
            );

            theNextMove = legalMoves.get(choice);

            JOptionPane.showMessageDialog(null,
                    "You chose:"+moveChoices[choice]);


        }
        catch (IndexOutOfBoundsException e)
        {
            theNextMove = Move.QUIT;
        }
        return theNextMove;
    }

    /**
     * display to the output the current configuration of the tiles
     *
     * @param theGame game to be displayed
     */
    @Override
    public void display(SlidingTilePuzzle theGame) {

        JOptionPane.showMessageDialog(null,
                theGame.toString(),
                "Current Puzzle State", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows Congratulations message once the game has been completed
     * @param numberMovesToWin the number of moves in the game to this point
     */
    @Override
    public void congratulations(int numberMovesToWin) {
        JOptionPane.showMessageDialog(null,
                "Congratulations, you solved the game in " + numberMovesToWin + " moves.");
        System.exit(42);
    }

    /**
     * Announces the end of the game, when the game is not correctly finished
     * but the user wants to end the game
     */
    @Override
    public void announceEndOfGame() {
        JOptionPane.showMessageDialog(null,
                "Game Over!");
        System.exit(42);
    }
}

