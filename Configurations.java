/**
 * @author Eliandro Pizzonia
 * This class implements all the methods needed by algorithm computerPlay
 */

public class Configurations{
	
	// 2d board array
    private char[][] board;
    
    // size of the board
    private int boardSize;
    
    // length of the sequence needed to win the game
    private int lengthToWin;
    
    // maximum level of the game tree that will be explored by the program
    private int maxLevels;

    
    /**
     * @param boardSize
     * @param lengthToWin
     * @param maxLevels
     * constructor to set instance variables
     */
    public Configurations (int boardSize, int lengthToWin, int maxLevels){
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;

       // initializing board with empty spaces
        this.board = new char[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                board[i][j] = ' ';
            }
        }
    }
    
    
    /**
     * @return an empty HashDictionary
     * 
     * creates and returns an empty hash dictionary 
     */
    public HashDictionary createDictionary(){
        return new HashDictionary(8009);
    }
    
    
    /**
     * @param hashTable
     * @return associated score if string is in hash table otherwise returns -1
     * 
     * stores the characters of the board in a String
     * then it checks whether the String representing the board is in the hash table
     */
    public int repeatedConfiguration(HashDictionary hashTable){
    
        String boardString = "";
        
        // creates string representation of board
        for(int i = 0; i < boardSize; i++){
                for(int j = 0; j < boardSize; j++){
                    boardString += board[i][j];
                }
        }
        
        // check if string representation of board is in hash table
        if(hashTable.get(boardString) == -1){
            return -1;
        }
        else{
            return hashTable.get(boardString);
        }
    } 
    
    
    /**
     * @param hashDictionary
     * @param score
     * 
     * represents the content of the board as a String
     * then it inserts this String and score in the hashDictionary
     */
    public void addConfiguration(HashDictionary hashDictionary, int score){

        String boardString = "";
        
        // creates string representation of board
        for(int i = 0; i < boardSize; i++){
                for(int j = 0; j < boardSize; j++){
                    boardString += board[i][j];
                }
        }

        // creates new Data object and adds string and score in hash table
        Data config = new Data(boardString, score);
        hashDictionary.put(config);
    }

    
    /**
     * @param row
     * @param col
     * @param symbol
     *`
     * Stores a symbol in the board[row][col]
     */
    public void savePlay(int row, int col, char symbol){
        this.board[row][col] = symbol;
    }

    
    /**
     * @param row
     * @param col
     * @return True if board[row][col] is ’ ’
     * otherwise it returns false
     */
    public boolean squareIsEmpty (int row, int col){

        char boardVal = board[row][col];

        if (boardVal == ' '){
            return true;
        }
        else{
            return false;
        }
    }

    
    /**
     * private helper method
     * @param symbol
     * @return true if if there is a horizontal continuous sequence of length
     * at least k formed by tiles of the kind symbol on the board
     * 
     */
    private boolean horizontalWin(char symbol){

    	// loops through each row in board
        for(int i = 0; i < boardSize; i++){
            int moveSequenceCount = 0;
            for(int j = 0; j < boardSize; j++){
            	
            	// if position in board matches symbol, count is updated
                if (board[i][j] == symbol) {
                    moveSequenceCount += 1;
                }
                // resets count if symbol not consecutive anymore before count equals length to win
                else{
                    moveSequenceCount = 0;
                }

                if (moveSequenceCount == lengthToWin) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    /**
     * private helper method
     * @param symbol
     * @return true if if there is a vertical continuous sequence of length
     * at least k formed by tiles of the kind symbol on the board
     * 
     */
    private boolean verticalWin(char symbol){
        
    	//loops through each column of board
        for(int j = 0; j < boardSize; j++){
            int moveSequenceCount = 0;
            for(int i = 0; i < boardSize; i++){
               
            	// if position in board matches symbol, count is updated
                if (board[i][j] == symbol) {
                    moveSequenceCount += 1;
                }
                // resets count if symbol not consecutive anymore before count equals length to win
                else{
                    moveSequenceCount = 0;
                }

                if (moveSequenceCount == lengthToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    
    /**
     * private helper method
     * @param symbol
     * @return true if if there is a diagonal continuous sequence of length
     * at least k formed by tiles of the kind symbol on the board
     */
    private boolean diagonalWin(char symbol){
        int moveSequenceCount = 0;
        
        // checks if diagonal of board from top left corner to bottom right corner has a consecutive diagonal
        // of length lengthToWin and of type symbol
        for(int i = 0; i < boardSize; i++){
            if(board[i][i] == symbol){
                moveSequenceCount += 1;
            }
            else{
                moveSequenceCount = 0;
            }

            if (moveSequenceCount == lengthToWin) {
                return true;
            }
        }
        
        moveSequenceCount = 0;
        
        // checks if diagonal of board from top right corner to bottom left corner has a consecutive diagonal
        // of length lengthToWin and of type symbol
        for(int i = boardSize - 1; i >= 0; i--){
            if(board[i][boardSize - 1 - i] == symbol){
                moveSequenceCount += 1;
            }
            else{
                moveSequenceCount = 0;
            }

            if (moveSequenceCount == lengthToWin) {
                return true;
            }
        }

        return false;
    }

    
    /**
     *@param symbol
     *@return true if any of the private helper methods return true indicating a win has taken place
     * 
     * calls horizontal, vertical, diagonal win private methods
     */
    public boolean wins (char symbol){
        return horizontalWin(symbol) || verticalWin(symbol) || diagonalWin(symbol);
    }

    
    /**
     * @return true if board has no empty positions left and no player has won the game
     */
    public boolean isDraw(){

    	// if still empty position on board, false is returned to indicate no draw yet
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                if(squareIsEmpty(i, j)){
                    return false;
                }
            }
        }
        
        // if there is a win, return false
        if (wins('X') || wins('O')) {
            return false;
        }
        return true;
    }

    
    /**
     * @return integer value based on the outcome of situation
     */
    public int evalBoard(){
        
    	// if the computer has won
    	if (wins('O')) {
            return 3;
        }
    	
    	// if the human player has won
        else if (wins('X')) {
            return 0;
        }
    	
    	//  if the game is a draw
        else if (isDraw()) {
            return 2;
        }
    	
    	// if the game is still undecided
        else {
            return 1;
        }
    }
}