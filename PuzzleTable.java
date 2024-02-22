//Author: WD
//WD---vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.List;

public class PuzzleTable {
    private GameCell[][] puzzlePiece;
    private TableView<GameCell> puzzleSection;
    private boolean hasRowHeaders;
    private boolean hasColumnHeaders;

    public PuzzleTable(int rows, int columns, boolean hasRowHeaders, boolean hasColumnHeaders){

        this.puzzlePiece = new GameCell[rows][columns];
        this.puzzleSection = new TableView<>();

            //For formatting the game to include A row or column of un-clickable Labels
        this.hasRowHeaders = hasRowHeaders;
        this.hasColumnHeaders = hasColumnHeaders;

    }

    public void setFirstPuzzle(List<String> columnHeader, List<String> rowHeader){

    }
    public GameCell[][] getSection(){
        return this.puzzlePiece;
    }

        //Used for Creating and updating the Puzzle
    public void updatePuzzle(GameCell[][] update){
        this.puzzlePiece = update;1
    }

        //Used for updating the Puzzle
    public boolean getRowHeader(){
        this.hasRowHeaders;
    }
        //Used for updating the Puzzle
    public boolean getColumnHeader(){
        this.hasColumnHeaders;
    }
//WD--^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}
