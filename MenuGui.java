//Author WD

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;



public class MenuGui {

        // Scene to export
    private VBox menuOption;

        // Instructions for User
    private Label instructions;

        // Middle child holding the next 2 variables
    private HBox linedUp;
    private Button startButton;
    private ComboBox puzzleSelection;

    public MenuGui(){

            // Setting up the scene to be exported
        this.menuOption = new VBox();
        this.menuOption.setAlignment(Pos.CENTER);

            //
        this.instructions = new Label();

            // Setting up the Child to add to the Scene export
        this.linedUp = new HBox( 10);
        this.linedUp.setAlignment(Pos.CENTER);

            // Grandchildren
        this.startButton = new Button("Start Puzzle");
            // Sets up options
        this.puzzleSelection = new ComboBox<String>();

            //adding Grandchildren
        this.linedUp.getChildren().add(this.puzzleSelection);
        this.linedUp.getChildren().add(this.startButton);

            //adding middle children
        this.menuOption.getChildren().add(this.instructions);
        this.menuOption.getChildren().add(this.linedUp);
    }

    public Button getButton(){
        return this.startButton;
    }

    public VBox getMenu(){
        return this.menuOption;
    }

}
