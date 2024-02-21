//Author WD

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;

public class Board {

        //The Scene to export
    private VBox gameBoard;

    //Returns to MainMenu
    private HBox endZone;
    private Button endButton;
    private Label endButtonInfo;

        //Separates the Puzzle and clues to be neatly next to each other
    private HBox mainFormatting;

        //The puzzle background space
    private VBox puzzleFormatting;

        //Space for the user to read the clues
    private TextArea clueArea;

        //compartmentalizes the Puzzle Sections to create unique shapes
        //based on the size of the puzzle
    private List<HBox> puzzleSectionRows;

        //Used to format which size puzzle the user would like to play
    private String puzzleSize;

    public Board(String puzzleSize){

            //Getting the parameters out of the way
        this.puzzleSize = puzzleSize;

            //Setting up the GameScene to be exported
        this.gameBoard = new VBox();
        this.gameBoard.setAlignment(Pos.CENTER);

            //eject Area
        this.endZone = new HBox();
        this.endZone.setAlignment(Pos.CENTER);
            //eject button and Label
        this.endButtonInfo = new Label("End Game or new Game")
        this.endButton = new Button("EmergencyExit");


            //Setting up the puzzle and clue separation
        this.mainFormatting = new HBox();
        this.mainFormatting.setAlignment(Pos.CENTER);

            //Setting up the Puzzle Space
        this.puzzleFormatting = new VBox();
        this.puzzleFormatting.setAlignment(Pos.CENTER);

            //Goes next to the puzzleFormatting
        this.clueArea = new TextArea();

        this.puzzleSectionRows = new ArrayList<HBox>();


            //Filling the first Row of the Scene
        this.endZone.getChildren().add(this.endButtonInfo);
        this.endZone.getChildren().add(this.endButton);

            //Filling the second Row of the Scene
        this.mainFormatting.getChildren().add(this.puzzleFormatting);
        this.mainFormatting.getChildren().add(this.clueArea);

            //First Row of the Scene
        this.gameBoard.getChildren().add(this.endZone);
            //Second Row of the Scene
        this.gameBoard.getChildren().add(this.mainFormatting);


    }

        //used to set up the Puzzle space
    private void setSectionRows(int x){

            //Sets up how many Section Rows the Board needs
        while(x > 0){
            HBox row = new HBox();
            this.puzzleSectionRows.getChildren().add(row);
            x = x - 1;
        }
    }

    public void setPuzzleSize(List<> puzzlePieces){

    }

        //Exports Button for Lambda function
    public Button getEndButton(){
        return this.endButton;
    }

        //Exports the GameScene
    public VBox getBoard(){
        return this.gameBoard;
    }

}
