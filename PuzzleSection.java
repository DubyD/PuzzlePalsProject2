import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.event.MouseEvent;

public class PuzzleSection {

    private TableView<TableRowData> section;
    private int numberOfColumns;

    public PublicSection(){

        this.section = new TableView<>();
        this.numberOfColumns = 0;

    }

    public void setRowHeader(String header){
        String[] pieces = header.split(",");
        TableColumn<TableRowData, String> columnHeader = new TableColumn<>("Header");
        for(String next: pieces){
            columnHeader.setCellFactory(cellData -> cellData.getValue().getFirstColumn());
        }
        this.numberOfColumns = this.numberOfColumns + 1;
    }
    public void setColumn(String header){
        TableColumn<Table, String> nextColumn = TableColumn<>(header);

        this.section.getColumns().add(nextColumn);
    }

        //Used to add Click events to Table Elements
    public void addClickEvent(MouseEvent event){

    }

}
