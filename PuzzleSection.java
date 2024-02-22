import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.MouseEvent;

public class PuzzleSection {

    public static TableView setTable(PuzzleTable data){

            //Disecting the Backend GameCells
        GameCell[][] workingSpace = data.getSection();

            //iterating the Backend into a TableView object
        for(int x = 0; x < workingSpace.length; x++){
            for(int y = 0; y < workingSpace[x].length; y++){

                if(workingSpace[x][y].toString().equals("empty")){

                }else{

                }
            }
        }
    }
        //Used to add Click events to Table Elements
    public void addClickEvent(MouseEvent event){

    }

}
