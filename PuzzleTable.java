/**
 * @author Evelyn Totman.
 */


public class PuzzleTable {
    private GameCell[][] puzzlePiece;

    private boolean hasRowHeaders;
    private boolean hasColumnHeaders;

    public PuzzleTable(int rows, int columns, boolean hasRowHeaders, boolean hasColumnHeaders){

        this.puzzlePiece = new GameCell[rows][columns];


            //For formatting the game to include A row or column of un-clickable Labels
        this.hasRowHeaders = hasRowHeaders;
        this.hasColumnHeaders = hasColumnHeaders;
        //initializes the game cells
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                puzzlePiece[i][j] = new GameCell();
                if(hasColumnHeaders && i == 0){
                    puzzlePiece[i][j].setHeader(true);
                }
                if(hasRowHeaders && j == 0){
                    puzzlePiece[i][j].setHeader(true);
                }

            }
        }
    }

    public PuzzleTable(int dim){
        this(dim, dim, false, false);
    }


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
//WD--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    public GameCell getCell(int x, int y){
        return puzzlePiece[x][y];
    }

    public void clickCell(int row, int column){
        puzzlePiece[row][column].clickCell();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
