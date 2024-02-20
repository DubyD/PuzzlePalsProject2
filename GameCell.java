//Author: WD
//WD---vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
import javafx.scene.control.Label;


public class GameCell {

    private String cellState;
    private boolean isHeader;
    private int row;
    private int column;
    public GameCell(, int row, int column){

            //
        this.cellState = "";
        this.isHeader = false;

            //To identify which spaces need to be adjusted
        this.row = row;
        this.column = column;

            //Creates a rotating state of values to play the game with.
        /*this.pieceLabel.setOnMouseClicked(event -> {
            if(this.cellState.equals("")){
                this.cellState = "X";
                this.pieceLabel.setText(this.cellState);
            }else if(this.cellState.equals("X")){
                this.cellState = "O";
                this.pieceLabel.setText(this.cellState);
                GameCell[][] updating = this.puzzleSection.getSection();

                    //Updates the Board if 'O' is selected to 'X' out all non-Header Column and rows of the same value
                for(GameCell[] layer: updating) {
                    for (GameCell working : layer) {
                        if (working.getRow() == this.row && working.getColumn() == this.column) {
                            //nothing happens because that is this. GameCell

                        } else if (working.getRow() == this.row) {
                            //'X' in the Row
                            working.setLabel("X");
                        } else if (working.getColumn() == this.column) {
                            //'X' in the Column
                            working.setLabel("X");
                        }
                    }
                }
                    //Updates the Board to fill in 'X's into rows and columns
                this.puzzleSection.updatePuzzle(updating);


            }else if(this.cellState.equals("O")){
                this.cellState = "";
                this.pieceLabel.setText(this.cellState);
            }
        });*/
    }

        //The second constructor is for headers of rows and columns, separating them
        //from the game pieces
    public GameCell(String header){

            //This will make it easier to identify clickable from
            // un-clickable cells from the backend
        this.isHeader = true;
        this.cellState = header;
    }

        //Used to update PuzzleSection
    public int getRow(){
        return this.row;
    }
        //Used to update PuzzleSection
    public int getColumn(){
        return this.column;
    }

        //Gives access to what kind of object this is, making updates easier
    public boolean getCellType(){
        return this.isHeader;
    }
        //Allows the GameCell to be updated by the click events of other GameCells
    public void setLabel(String update){
            //checks the CellType so that Headers keep their values
        if(this.getCellType() == false) {
            this.pieceLabel.setText(update);
            this.cellState = update;
        }
    }

        //Used in Puzzle Table
    public void setHeader(String header){

            //Used to set Headers of a Puzzle
        if(this.getCellType() == true){
            this.pieceLabel.setText(header);
        }
    }

        //Added to make this a more complete class
    @Override
    public String toString(){
        String reply = "this is a tableview element, a basic piece of the Puzzle game.\n"+
                       "It's coordinates in the Table are: " + this.row +"x"+ this.column;
        return reply;
    }


//WD--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
