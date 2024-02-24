import java.util.Arrays;

/**
 * @author Evelyn Totman.
 */


public class PuzzleTable {
    private GameCell[][] puzzlePiece;

    private boolean hasRowHeaders;
    private boolean hasColumnHeaders;

        //For any and all PuzzleTables
    public PuzzleTable(int rows, int columns, boolean bothHeaders){

        this.puzzlePiece = new GameCell[rows][columns];

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
            for(int x = 0; x < puzzlePiece[x].length; x++){
                for(int y = 0; y < puzzlePiece[y].length; y++){

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

    //Checks if the given object is equal to this obj by comparing puzzlePiece attributes
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || getClass() == obj.getClass()) return false;
        PuzzleTable temp = (PuzzleTable) obj;
        return Arrays.deepEquals(puzzlePiece, temp.puzzlePiece);
    }
}
