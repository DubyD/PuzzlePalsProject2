//Author: WD

import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;


public class GameCell {
//WD---vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    private String cellState;
    private Label pieceLabel;
    private boolean isHeader;
    public GameCell(){

            //Allows easy access to which state the cell is currently in while switching
            //through it's cycle of states
        this.cellState = "";
        this.pieceLabel = new Label();
        this.isHeader = false;

            //Creates a rotating state of values to play the game with.
        this.pieceLabel.setOnMouseClicked(event -> {
            if(this.cellState.equals("")){
                this.cellState = "X";
                this.pieceLabel.setText(this.cellState);
            }else if(this.cellState.equals("X")){
                this.cellState = "O";
                this.pieceLabel.setText(this.cellState);
            }else if(this.cellState.equals("O")){
                this.cellState = "";
                this.pieceLabel.setText(this.cellState);
            }
        });
    }

        //The second constructor is for headers of rows and columns, separating them
        //from the game pieces
    public GameCell(String header){

            //This will make it easier to identify clickable from
            // un-clickable cells from the backend
        this.isHeader = true;
        this.cellState = "";
        this.pieceLabel = new Label(header);
    }

        //Gives higher level classes access to what kind of object this is easier
    public boolean getCellType(){
        return this.isHeader;
    }
        //Allows the object that was created to be sent into a larger class without the need to set the constructor
    public Label getLabel(){
        return this.pieceLabel;
    }


//WD--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
