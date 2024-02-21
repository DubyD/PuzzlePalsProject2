//Author WD
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class SceneSetter {

        //Creates a pointer to the Main classes stage
    private Stage primaryStage;

        //The Scenes to choose from
    private Scene menuScene;
    //private Scene gameScene;

    public SceneSetter(Stage primaryStage){

            //A pointer to the primaryStage
        this.primaryStage = primaryStage;
            //Sets the Scenes and Buttons
        this.menuScene =
        this.setScenes();
    }

    private void setScenes(){

            // creating custom settings of menuScene components
            // creating a pointer to one of the VBox's children (Button)
            // for desired results when clicked
        MenuGui menuSetter = new MenuGui();
        menuSetter.getButton().setOnAction(event ->{
            showMenu();
        });
        this.menuScene = new Scene(menuSetter.getMenu(), 300, 200);

            // creating custom settings of gameMenu components
            // creating a pointer to one of the VBox's children (Button)
            // for desired results when clicked
        Board gameBoard = new Board();
        gameBoard.getButton().setOnAction(event->{
            showGame();
        });
        this.gameScene = new Scene(gameBoard.getBoard(), 600, 600);
    }


        //The switchboard between game screens
        //
    public void showMenu(){
        primaryStage.setScene(this.menuScene);
    }

    public void showGame(){
        //primaryStage.setScene(this.gameScene);
    }



}
