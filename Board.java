//Author WD

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import java.util.List;
import java.util.ArrayList;

public class Board {

        //The Scene to export
    private VBox gameBoard;

    //Returns to MainMenu
    private GuiRow firstRow;

        //Separates the Puzzle and clues to be neatly next to each other
    private HBox mainFormatting;

        //The puzzle background space
    private VBox puzzleFormatting;

        //Space for the user to read the clues
    private TextArea clueArea;

        //compartmentalizes the Puzzle Sections to create unique shapes
        //based on the size of the puzzle
    private List<HBox> puzzleSectionRows;

        //Third row pieces, HBoxes required
    private HBox rowThree;

        //ThirdRow Leftside
    private GuiRow hintRow;

        //ThirdRow Rightside
    private GuiRow checkRow;

    private Label hintAction;

        //Saving puzzle size info to share with the rest of the program

    private Puzzle puzzle;


    public Board(String puzzleSize){


            //Saving the size of puzzle
        this.puzzle = PuzzleReader.readCSV(puzzleSize);
//-------------------------------------------------------------------------------------------
            //Setting up the GameScene to be exported
        this.gameBoard = new VBox();
        this.gameBoard.setAlignment(Pos.CENTER);
//-------------------------------------------------------------------------------------------
            //eject Area
        this.firstRow = new GuiRow();

            //eject button and Label
        this.firstRow.setLabel("End Game or new Game");
        this.firstRow.setButton("EmergencyExit");
//-------------------------------------------------------------------------------------------

            //Setting up the puzzle and clue separation
        this.mainFormatting = new HBox();
        this.mainFormatting.setAlignment(Pos.CENTER);

            //Setting up the Puzzle Space
        this.puzzleFormatting = new VBox();
        this.puzzleFormatting.setAlignment(Pos.CENTER);

            //Goes next to the puzzleFormatting
        this.clueArea = new TextArea();
        this.setClues();

            //Formats the Puzzle Space
        this.puzzleSectionRows = new ArrayList<HBox>();
        this.setPuzzleSize(puzzleSize);
//-------------------------------------------------------------------------------------------
            //Third row formatting
        this.rowThree = new HBox();
        this.rowThree.setAlignment(Pos.CENTER);

            //Leftside of third row
        this.checkRow = new GuiRow();
        this.checkRow.setLabel("Do you want to check your answers?");
        this.checkRow.setButton("Check Answers");

            //Rightside of third row
        this.hintRow = new GuiRow();
        this.hintRow.setLabel("Do you need a hint?");
        this.hintRow.setButton("'Hint'");

            //FourthRow
        this.hintAction = new Label();
        this.setHints();



            //Filling the second Row of the Scene
        this.mainFormatting.getChildren().add(this.puzzleFormatting);
        this.mainFormatting.getChildren().add(this.clueArea);

            //Filling the third Row of the Scene
        this.rowThree.getChildren().add(this.checkRow.getRow());
        this.rowThree.getChildren().add(this.hintRow.getRow());

            //First Row of the Scene
        this.gameBoard.getChildren().add(this.firstRow.getRow());
            //Second Row of the Scene
        this.gameBoard.getChildren().add(this.mainFormatting);
            //Third Row of the Scene
        this.gameBoard.getChildren().add(this.rowThree);

    }

    private void setClues(){
        ArrayList<String> adding = PuzzleReader.getClues();
        String reply = "";
        for(int i = 0; i < adding.size(); i++){
            reply = reply + adding.get(i) + "\n\n";
        }
        this.clueArea.setText(reply);
    }


    //Used for setting up the GUI
    private void setPuzzleSize(String size){

            //Sets up X to format HBoxs with number of Rows
        String[] pieces = size.split("");
        int x = Integer.parseInt(pieces[0]);
        x = x - 1;

            //Turning theoretical Data into GUI data
        ArrayList<PuzzleTable> formatting = this.puzzle.getgBoard();
        ArrayList<GridPane> guiPieces = new ArrayList<>();
            //The loop to make that happen
        for(int i = 0; i < formatting.size(); i++){
            guiPieces.add(PuzzleSection.setTable(formatting.get(i)));
        }

        for(int i = 0; i < x; i++){
            HBox row = new HBox();

            while(i < x){
                GridPane work = guiPieces.get(i);
                row.getChildren().add(work);
                guiPieces.remove(work);
                i++;
            }
            guiPieces.removeAll(row.getChildren());
            this.puzzleSectionRows.add(row);
            x = x - 1;
        }

    }



    private void setHints(){
        this.hintRow.getButton().setOnAction(event -> {
            hintAction.setText(this.puzzle.hint());
        });
    }

        //Exports Button for Lambda function
    public Button getEndButton(){
        return this.firstRow.getButton();
    }

        //Returns puzzle so if you finished you can return to a finish screen
    public Puzzle getPuzzle(){
        return this.puzzle;
    }

        //Used for connecting the answer check Button
    public Button getCheckButton(){
        return this.checkRow.getButton();
    }

        //Exports the GameScene
    public VBox getBoard(){
        return this.gameBoard;
    }

    @Override
    public String toString(){
        String reply = "This is a Gui for an initialized Version of the Game.";
        return reply;
    }

}
