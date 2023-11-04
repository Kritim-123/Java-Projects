/*
* Author: Kritim Bastola
*
* All the code for the Tile Puzzle game
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TilePuzzle implements SlidingTilePuzzle
{

    private int[][] board;
    private final int size;
    private int numMoves;
    private boolean hasQuit;


    /**
     * To initialize the TilePuzzle
     * @param size
     * @param useDefault
     */
    public TilePuzzle(int size, boolean useDefault){
        this.size = size;

        if(useDefault){
            makeDefaultConfig();

        }
        else {
            makeRandomConfig();
        }
    }

    /**
     * To make Default configuration.
     */
    public void makeDefaultConfig(){
        board = new int[size][size];

        int tileValue = 0;

        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                board[i][j] = tileValue;
                tileValue++;
            }
        }

    }

    /**
     * To make random Configuration.
     */
    public void makeRandomConfig(){
        int totalNumber = size*size;
        board = new int[size][size];

        List<Integer> tileNumbers = new ArrayList<>();

        for (int i=0; i<totalNumber; i++){
            tileNumbers.add(i);
        }

        Collections.shuffle(tileNumbers);

        int k = 0; // to keep track of the index of the tileNumber

        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                board[i][j] = (int) tileNumbers.get(k);
                k++;
            }
        }


    }


    @Override
    /**
     * @return true if-and-only-if the puzzle is in the
     * winning configuration
     */
    public boolean hasWon() {

        int[][] winningBoard = winningConfig();

        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if (winningBoard[i][j] != board[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Creates a board that represents user has won.
     * @return Winning Board
     */
    public int[][] winningConfig(){

        int[][] winningBoard = new int[size][size];
        int k = 1;
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if(i!= size && j!= size){
                    winningBoard[i][j] = k;
                    k++;
                }
            }
        }
        winningBoard[size-1][size-1] = 0;

        return winningBoard;
    }


    @Override
    /**
     * @return true if-and-only-if the player has quit
     * playing the game
     */
    public boolean hasQuit() {
        return hasQuit;
    }

    /**
     * Make the specified move in the game, if it possible
     * to perform that move given the current state of the game
     */
    @Override
    public void move(Move theNextMove) {
        int [] blankPosition = findBlankPosition(board);
        int rowOfBlank = blankPosition[0];
        int colOfBlank = blankPosition[1];
        int temp = board[rowOfBlank][colOfBlank];


        switch (theNextMove){
            case UP ->
            {
                if(rowOfBlank!= 0){
                    board[rowOfBlank][colOfBlank] = board[rowOfBlank-1][colOfBlank];
                    board[rowOfBlank-1][colOfBlank] = temp;
                    numMoves++;
                }
            }

            case DOWN ->
            {
                if(rowOfBlank!= size-1){
                    board[rowOfBlank][colOfBlank] = board[rowOfBlank+1][colOfBlank];
                    board[rowOfBlank+1][colOfBlank] = temp;
                    numMoves++;
                }
            }
            case LEFT ->
            {
                if(colOfBlank !=0){
                    board[rowOfBlank][colOfBlank] = board[rowOfBlank][colOfBlank-1];
                    board[rowOfBlank][colOfBlank-1] = temp;
                    numMoves++;
                }
            }
            case RIGHT ->
            {
                if(colOfBlank != size-1){
                    board[rowOfBlank][colOfBlank] = board[rowOfBlank][colOfBlank+1];
                    board[rowOfBlank][colOfBlank+1] = temp;
                    numMoves++;
                }
            }
            case QUIT -> {
                hasQuit = true;
            }

        }

    }

    /**
     * Gets the position of the blank space
     * Needed to validate if the next move is correct or not
     * @param board
     * @return
     */
    public int[] findBlankPosition(int[][] board)
    {
        int[] blankPosition = new int[2];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j] == 0){
                    blankPosition[0] = i;
                    blankPosition[1] = j;
                    return blankPosition;
                }
            }
        }

        return blankPosition;
    }

    /**
     * @return a List of all the moves that are legal
     * based on the current state of the game
     */
    @Override
    public List<Move> allCurrentLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        int[] blankPosition = findBlankPosition(board);
        int rowOfBlank = blankPosition[0];
        int colOfBlank = blankPosition[1];

        if (rowOfBlank != 0) {
            legalMoves.add(Move.UP);
        }
        if (rowOfBlank != size - 1) {
            legalMoves.add(Move.DOWN);
        }
        if (colOfBlank != 0) {
            legalMoves.add(Move.LEFT);
        }
        if (colOfBlank != size - 1) {
            legalMoves.add(Move.RIGHT);
        }

        if (!hasQuit) {
            legalMoves.add(Move.QUIT);
        }

        return legalMoves;
    }


    /**
     * @return the number of valid moves made in the game so far
     */
    @Override
    public int getNumMoves() {

        return numMoves;
    }

    /**
     * To print the 2D array
     * @return String representation of the array
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                result.append(board[row][column]).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
