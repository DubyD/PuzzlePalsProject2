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

    private int sizeLength;
    private int sizeWidth;

        //For any and all PuzzleTables
    public PuzzleTable(int rows, int columns, boolean bothHeaders){

        this.puzzlePiece = new GameCell[rows][columns];

        this.bothHeaders = bothHeaders;

            //Used for formatting the Header Cells
        if(rows < columns){

            this.hasRowHeaders = true;
            this.hasColumnHeaders = false;

                //For connecting answers
            this.sizeWidth = rows - 1;
            this.sizeLength = columns;

        }else if(rows > columns){

            this.hasRowHeaders = false;
            this.hasColumnHeaders = true;

                //For connecting answers
            this.sizeWidth = rows;
            this.sizeLength = columns - 1;

        } else if(bothHeaders){

            this.hasRowHeaders = true;
            this.hasColumnHeaders = true;

                //For connecting answers
            this.sizeWidth = rows - 1;
            this.sizeLength = columns - 1;

        }else{

            this.hasRowHeaders = false;
            this.hasColumnHeaders = false;

                //For connecting answers
            this.sizeWidth = rows;
            this.sizeLength = columns;

        }


            //initializes the game cells
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){

                    //added I, J so the Cell can return where it is in space
                puzzlePiece[i][j] = new GameCell(i, j);

                    //Setting Header booleans will add Header strings later
                if(this.hasRowHeaders){
                    puzzlePiece[i][0].setHeader(true);
                }
                    //Setting Header booleans, will add Header Strings later
                if(this.hasColumnHeaders){
                    puzzlePiece[0][j].setHeader(true);
                }
                    //Sets the blank Space so we don't have to add it to .csv
                if(bothHeaders){
                    puzzlePiece[0][0].setHeaderString("Header Row");
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

        //Used for updating the Puzzle
    public boolean getRowHeader(){
        return hasRowHeaders;
    }
        //Used for updating the Puzzle
    public boolean getColumnHeader(){
        return hasColumnHeaders;
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
                for(int y = 0; y < puzzlePiece.length; y++){

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

    public void connectingAnswers(String[][] answers){

        int x = 0;

            //skips the first row
        if(this.hasColumnHeaders == true){
            x = 1;
        }
            //Using the filler Variables to connect the answer string to the GameCellArray
        while(x < this.sizeLength){
            int y;
                //Skips the first Column
            if(this.hasRowHeaders == true){
                y = 1;
            }else{
                y = 0;
            }

            while(y < this.sizeWidth) {
                this.puzzlePiece[x][y].setAnswer(answers[x][y]);
                y++;
            }
            x++;
        }
    }
        //Comparing the user input with answerKey
    public boolean isCorrect(){

        boolean reply = true;
            //Iterates through the puzzle to find if the entire puzzle is correct or not
        for(int x = 0; x < this.sizeLength; x++){
            for(int y = 0; y < this.sizeWidth; y++){
                reply = reply && puzzlePiece[x][y].isError();

                    //Ends the loop if it is already false
                if(reply == false){
                    return reply;
                }
            }
        }
        return reply;
    }

    public String getHint(){

        String reply = "";
        for(int x = 0; x < this.sizeLength; x++) {
            for (int y = 0; y < this.sizeWidth; y++) {
                if(puzzlePiece[x][y].isError() == false){
                    reply = puzzlePiece[x][y].getX() + "x" + puzzlePiece[x][y].getY();
                    reply = reply + " is supposed to be " + puzzlePiece[x][y].getAnswerKey();
                    return reply;
                }
            }
        }
        return reply;
    }

        //Adding Row Headers
    public void addLeftHeader(ArrayList<String> header){
            //iterates through the Rows to Add the first row
        for(int i = 0; i < header.size(); i++){
            if(this.bothHeaders == true){

                //Skips the Very first section
            }else{
                puzzlePiece[i][0].setHeaderString(header.get(i));
            }
        }
    }
        //Adding Top headers
    public void addTopHeader(ArrayList<String> header){
            //iterates through the Columns to Add the first Header
        for(int i = 0; i < header.size(); i++){
            if(this.bothHeaders == true){

                //Skips the Very first section
            }else {
                puzzlePiece[0][i].setHeaderString(header.get(i));
            }
        }
    }

    //Checks if the given object is equal to this obj by comparing puzzlePiece attributes
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() == obj.getClass()) return false;
        PuzzleTable temp = (PuzzleTable) obj;
        return Arrays.deepEquals(puzzlePiece, temp.puzzlePiece);
    }
}
