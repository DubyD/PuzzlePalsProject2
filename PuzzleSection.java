import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class PuzzleSection {

    public static GridPane setTable(PuzzleTable data){

            //Disecting the Backend GameCells to place in Tableview
        GameCell[][] workingSpace = data.getSection();
            //importing the Backend into a GridPane
        GridPane gridPane = new GridPane();
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
                    label.setStyle("-fx-border-width: 1; -fx-border-color: black;");

                    label.setOnMouseClicked(event ->
                        this.addClickEvent(event, cell, data,connectingCells);
                    );
                }else{
                        //This will add a little flare and space between pieces
                    label.setStyle("-fx-border-width: 2; -fx-border-color: Red;");
                }

                // Add label to the grid pane
                gridPane.add(label, x, y);
            }
        }

        // Add constraints for uniformity
        for (int i = 0; i < workingSpace.length; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(100);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }

    private static void addClickEvent(MouseEvent event, GameCell cell, PuzzleTable grid, Label[][] guiGrid) {

        int x = cell.getX();
        int y = cell.getY();
        grid.clickCell(x,y);
        for(int i = 0; i < guiGrid.length; i++){
            for(int j = 0; j < guiGrid[0].length; j++){
                guiGrid[i][j].setText(grid[i][j].toString());
            }
        }
        
    }

}
