import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class PuzzleSection {

    public static GridPane setTable(PuzzleTable data, int row){

            //Disecting the Backend GameCells to place in Tableview
        GameCell[][] workingSpace = data.getSection();
            //importing the Backend into a GridPane
        GridPane gridPane = new GridPane();

            //iterating the 2D array onto a Grid
        for (int x = 0; x < workingSpace[x].length; x++) {
            for (int y = 0; y < workingSpace.length; y++) {
                GameCell cell = workingSpace[x][y];

                    //Adding a simple string property so that the Grid can Update automatically
                StringProperty cellText = new SimpleStringProperty(cell.toString());
                Label label = new Label();
                label.textProperty().bind(cellText);

                // Add styles, event handlers, or other customization as needed
                label.setStyle("-fx-border-width: 1; -fx-border-color: black;");
                if(cell.isHeader() == false) {
                    label.setOnMouseClicked(event -> addClickEvent(event, cell, workingSpace));
                }

                // Add label to the grid pane
                gridPane.add(label, x, y);
            }
        }

        // Add constraints for uniformity
        for (int i = 0; i < workingSpace.length; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(50);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        return gridPane;
    }

    private static void addClickEvent(MouseEvent event, GameCell cell, GameCell[][] grid) {


    }

}
