//Author: WD

import javafx.scene.control.Cell;


public class GameCell {
//WD---vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    private String cellState;
    private boolean isHeader;
    private Cell gridPiece;
    public GameCell(boolean header){

            //Allows easy access to which state the cell is currently in while switching
            //through it's cycle of states
        this.cellState = "";
            //Determines whether this is a label cell or a
        this.isHeader = header;

        if(header == false){

        }


    }
//WD--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
