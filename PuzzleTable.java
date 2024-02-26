import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Evelyn Totman.
 */


public class PuzzleTable {
    private GameCell[][] puzzlePiece;

    private boolean hasRowHeaders;
    private boolean hasColumnHeaders;
    private boolean bothHeaders;

    //For any and all PuzzleTables
    public PuzzleTable(int rows, int columns, boolean bothHeaders){

        this.puzzlePiece = new GameCell[rows][columns];

        this.bothHeaders = bothHeaders;

        //Used for formatting the Header Cells
        if(rows < columns){

            this.hasRowHeaders = true;
            this.hasColumnHeaders = false;

        }else if(rows > columns){

            this.hasRowHeaders = false;
            this.hasColumnHeaders = true;

        } else if(bothHeaders){

            this.hasRowHeaders = true;
            this.hasColumnHeaders = true;

        }else{

            this.hasRowHeaders = false;
            this.hasColumnHeaders = false;
        }


        //initializes the game cells
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                //added I, J so the Cell can return where it is in space
                this.puzzlePiece[i][j] = new GameCell(i, j);

                //Setting Header booleans will add Header strings later
                if(this.hasRowHeaders){
                    this.puzzlePiece[i][0].setHeader(true);
                }
                //Setting Header booleans, will add Header Strings later
                if(this.hasColumnHeaders){
                    this.puzzlePiece[0][j].setHeader(true);
                }
                //Sets the blank Space so we don't have to add it to .csv
                if(bothHeaders){
                    this.puzzlePiece[0][0].setHeaderString("Header");
                }
            }
        }
    }

    //To complete the Object adding a non param constructor
    public PuzzleTable(){
        this.puzzlePiece = null;
        this.hasColumnHeaders = false;
        this.hasRowHeaders = false;
    }

    //Used to return the entire Section to be iterated over a GridPane
    public GameCell[][] getSection(){
        return this.puzzlePiece;
    }

    //Used for Creating and updating the Puzzle
    public void updatePuzzle(GameCell[][] update){
        this.puzzlePiece = update;
    }

    private void setCells(boolean topHeads, boolean leftHeads){
        if()
    }

    //used to return the main Game Component and set Headers
    public GameCell getCell(int x, int y){
        return puzzlePiece[x][y];
    }

    //Used for Non-header cells when clicked
    public void clickCell(int row, int column){

        puzzlePiece[row][column].clickCell();

        //if you selected this as your answer, Changes cells in the same row and column to "X"
        if(puzzlePiece[row][column].toString().equals("O")){

            //iterating through the puzzle
            for(int x = 0; x < puzzlePiece.length; x++){
                for(int y = 0; y < puzzlePiece[0].length; y++){

                    if(x == row){
                        if(y == column){
                            //Skips if it is this cell
                        }else{
                            //changes cells in the row to "X"
                            puzzlePiece[x][y].setCurVal(1);
                        }
                    }else if(y == column){
                        //changes cells in the column to "X"
                        puzzlePiece[x][y].setCurVal(1);
                    }
                }
            }
        }
    }

    //------------------------------------------------------------------------------------------------
    //Comparing the user input with answerKey
    //Either to finish the puzzle or get a hint
    public boolean isCorrect(){

        boolean reply = true;
        //Iterates through the puzzle to find if the entire puzzle is correct or not
        for(GameCell[] cells : puzzlePiece){
            for(GameCell cell : cells) {

                reply = cell.isError();
                if(reply == false){
                    return reply;
                }
            }
        }
        return reply;
    }

        //If isCorrect returned false this function kicks in if Hint is clicked
    public String getHint(){

        String reply = "";
        for(GameCell[] cells : puzzlePiece) {
            for (GameCell cell : cells) {
                if(cell.isError() == false) {
                    //ends the Loop to save on time
                    reply = cell.getX() + "x" + cell.getY();
                    reply = reply + " is supposed to be " + cell.getAnswerKey();
                    return reply;
                }
            }
        }
        //Puzzle will never interact with this reply.
        return reply;
    }
//------------------------------------------------------------------------------------------------
    //Header Functions used to format the GameCell[][],
    //Set clicks appropriately, Label each row, make certain cells unEditable


    //Used for updating the Puzzle
    public boolean getRowHeader(){
        return this.hasRowHeaders;
    }
    //Used for updating the Puzzle
    public boolean getColumnHeader(){
        return this.hasColumnHeaders;
    }


    //Adding Row Headers
    public void addLeftHeader(ArrayList<String> header){
        //iterates through the Rows to Add the first row
        //x is needed to make sure the headers are formatted to the first PuzzleTable
        int x = 0;
        for(int i = 0; i < this.puzzlePiece.length; i++){
            if(this.bothHeaders == true && i == 0){

                //Skips the Very first section
            }else{
                this.puzzlePiece[i][0].setHeaderString(header.get(x));
                x++;
            }
        }
    }
    //Adding Top headers
    public void addTopHeader(ArrayList<String> header){
        //iterates through the Columns to Add the first Header
        //x is needed to make sure the headers are formatted to the first PuzzleTable
        int x = 0;
        for(int i = 0; i < this.puzzlePiece[0].length; i++){
            if(this.bothHeaders == true && i == 0){

                //Skips the Very first section
            }else {

                this.puzzlePiece[0][i].setHeaderString(header.get(x));
                x++;
            }
        }
    }


    //------------------------------------------------------------------------------------------------
    //Checks if the given object is equal to this obj by comparing puzzlePiece attributes
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() == obj.getClass()) return false;
        PuzzleTable temp = (PuzzleTable) obj;
        return Arrays.deepEquals(puzzlePiece, temp.puzzlePiece);
    }
}