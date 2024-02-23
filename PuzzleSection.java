import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.MouseEvent;

public class PuzzleSection {

    public static TableView setTable(PuzzleTable data, int row){

            //Disecting the Backend GameCells to place in Tableview
        GameCell[][] workingSpace = data.getSection();
        TableView<GameCell> reply = new TableView<>();



//-------------------------------------------------------------------------- WorkingSpace-------------
            //For consistent Row Length
        int format = 0;

            //This section of code is for iterating the Backend into a TableView object
            //The initial loop is for iterating through the Columns
        for(int y = 0; y < workingSpace[format].length; y++){

                //Creates the New Column and strings (spaghetti) associated with that Column
            TableColumn<GameCell, String> stringTableColumn = new TableColumn<>();
            ObservableList<String> spaghetti = FXCollections.observableArrayList();

                //iterating through the rows
            for(int x = 0; x < workingSpace.length; x++){

                    //Skips adding a Lambda function for Header Cells
                if(workingSpace[x][y].isHeader() == true){

                        //Sets the top Headers to the Header of the column
                    if(row == 0 && x == 0){

                            //Sets the Header Column
                        if(workingSpace[x][y].toString().equals("empty")){
                            stringTableColumn.setText("Headers");
                        }

                        stringTableColumn.setText(workingSpace[x][y].toString());
                    }

                    spaghetti.add(workingSpace[x][y].toString());

                }else{


                }//End of Setting Row Data for Columns
            }//End of Rows in Column

            stringTableColumn.setItems(spaghetti)
            reply.getColumns().add(stringTableColumn);
        }
    }
        //Used to add Click events to Table Elements
    public void addClickEvent(MouseEvent event){

    }

}
