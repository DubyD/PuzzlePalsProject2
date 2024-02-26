//Author WD


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.ietf.jgss.GSSManager;

import java.util.List;

public class PuzzleSection {

    public static GridPane setTable(PuzzleTable data, Button clearError){

            //Disecting the Backend GameCells to place in Tableview
        GameCell[][] workingSpace = data.getSection();
            //importing the Backend into a GridPane
        GridPane gridPane = new GridPane();
            //Setting up the allignment format
        gridPane.setHgap(20);
        gridPane.setVgap(20);


        int columns = workingSpace[0].length;
        Label[][] connectingCells = new Label[workingSpace.length][columns];

            //iterating the 2D array onto a Grid
        for (int x = 0; x < workingSpace.length; x++) {
            for (int y = 0; y < columns; y++) {
                GameCell cell = workingSpace[x][y];

                    //Adding a simple string property so that the Grid can Update automatically
                    //this will be easier than adding specialized methods
                Label label = new Label();
                label.setText(cell.toString());
                connectingCells[x][y] = label;


                    //If the Grid space is not a Header it will add a click event
                if(cell.isHeader() == false) {
                        //This will add a little flare and space between pieces
                    label.setStyle("-fx-border-width: 2; -fx-border-color: black;");

                    label.setOnMouseClicked(event -> {
                        int i = cell.getX();
                        int j = cell.getY();
                        data.clickCell(i,j);
                        changeGrid(event, data, connectingCells);
                    });

                    clearError.setOnMouseClicked(event -> {
                        data.clearErrors();
                        changeGrid(event, data, connectingCells);
                    });
                }else{
                        //This will add a little flare and space between pieces
                    label.setStyle("-fx-border-width: 2; -fx-border-color: Red;");
                }

                    //Adding Clear Button because I don't know how else to Access these Labels


                    // Add label to the grid pane
                    //and H/V alignment
                gridPane.add(label, x, y);



            }
        }

            // Add constraints for uniformity
        for (int i = 0; i < workingSpace.length; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(20);
            rowConstraints.setMaxHeight(20);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        for(int i = 0; i < columns; i++){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(60);
            columnConstraints.setMaxWidth(60);
            gridPane.getColumnConstraints().add(columnConstraints);

        }

        return gridPane;
    }

    public static void changeGrid(MouseEvent event, PuzzleTable grid, Label[][] guiGrid) {

        for(int i = 0; i < guiGrid.length; i++){
            for(int j = 0; j < guiGrid[0].length; j++){
                guiGrid[i][j].setText(grid.getSection()[i][j].toString());
            }
        }

    }

}
