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
        this.gameBoard.setAlignment(Pos.TOP_CENTER);
//-------------------------------------------------------------------------------------------
            //eject Area
        this.firstRow = new GuiRow();

            //eject button and Label
        this.firstRow.setLabel("End Game or new Game");
        this.firstRow.setButton("EmergencyExit");
//-------------------------------------------------------------------------------------------
            //Goes Next to the puzzleFormatting
        this.clueArea = new TextArea();
            //Clears clues if you choose a different puzzle
            //Sets the clues
        this.setClues();
        this.clueArea.setEditable(false);


            //Setting up the Puzzle Space
        this.puzzleFormatting = new VBox();
        this.puzzleFormatting.setAlignment(Pos.TOP_LEFT);
        this.puzzleFormatting.setSpacing(30);


            //Formats the Puzzle Space
        this.puzzleSectionRows = new ArrayList<HBox>();
        this.setPuzzleSize(puzzleSize);

            //Formats the Clues next to the puzzle
        this.mainFormatting = new HBox();
        this.mainFormatting.getChildren().add(this.puzzleFormatting);
        this.mainFormatting.getChildren().add(this.clueArea);
//-------------------------------------------------------------------------------------------
            //Third row formatting
        this.rowThree = new HBox();
        this.rowThree.setAlignment(Pos.CENTER);

            //Leftside of Third row
        this.checkRow = new GuiRow();
        this.checkRow.setLabel("Do you want to check your answers?");
        this.checkRow.setButton("Check Answers");

            //Rightside of Third row
        this.hintRow = new GuiRow();
        this.hintRow.setLabel("Do you need a hint?");
        this.hintRow.setButton("'Hint'");

        //Filling the Third Row of the Scene
        this.rowThree.getChildren().add(this.checkRow.getRow());
        this.rowThree.getChildren().add(this.hintRow.getRow());

//-------------------------------------------------------------------------------------------
            //FourthRow
        this.hintAction = new Label();
        this.setHints();

//-------------------------------------------------------------------------------------------
            //First Row of the Scene
        this.gameBoard.getChildren().add(this.firstRow.getRow());
            //Second Row of the Scene
        this.gameBoard.getChildren().add(this.mainFormatting);
            //Third Row of the Scene
        this.gameBoard.getChildren().add(this.rowThree);
            //Fourth Row of the Scene
        this.gameBoard.getChildren().add(this.hintAction);

    }

    private void setClues(){
        this.clueArea.setText("");
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

            //Adding the Rows to Line up the GridPanes Next to each other
        for(int i = 0; i < x; i++){
            HBox row = new HBox();
            row.setAlignment(Pos.TOP_LEFT);
            row.setSpacing(20);

            //Adding particular GridPanes to HBox's
            int j = 0;
            int k = x - i;
            while(j < k){

                    //Takes the First GridPane and adds it to the current Row
                GridPane work = guiPieces.get(0);
                row.getChildren().add(work);

                    //Removing GridPanes to not 'Double Book'
                guiPieces.remove(work);
                j++;
            }

            this.puzzleSectionRows.add(row);
        }
            //Adding the HBox's to the Puzzle Space itself
        for(int i = 0; i < this.puzzleSectionRows.size(); i++){
            this.puzzleFormatting.getChildren().add(this.puzzleSectionRows.get(i));
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
