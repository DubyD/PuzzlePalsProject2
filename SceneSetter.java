//Author WD
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class SceneSetter {

        //The background for the entire game, used to
    private VBox overMenu;
    private VBox menuOption;
    private VBox gameOption;
    private Button startButton;
    private ComboBox puzzleSelection;

    public SceneSetter(){

            //used to set Lambda fuctions to switch scenes
        this.startButton = new Button();


            //Parent for the stage
        this.overMenu = new VBox();
        this.overMenu.setAlignment(Pos.CENTER);

            //Screens to switch between
        this.gameOption = new VBox();
        this.menuOption = new VBox();
    }

    public VBox getMenu(){

            //Catches the first iteration of supplying the Menu Screen
        try{
            this.overMenu.getChildren().remove(this.gameOption);
        }catch(NullPointerException e){

        }
            //creating custom settings of VBox
        MenuGui menuSetter = new MenuGui();
        Button setLambda = menuSetter.getButton();
        setLambda.setOnAction(event->{

        });

        this.menuOption = menuSetter.getMenu();

        this.overMenu.getChildren().add(this.menuOption);
        return this.overMenu;
    }

    public VBox getGame(){
            //Removes the Selection Menu
        this.overMenu.getChildren().remove(this.menuOption);

            //Returns a Game Screen
        this.gameOption = board.getGame();
        this.overMenu.getChildren().add(this.gameOption);
        return this.overMenu;
    }
    public void showMenu(){
        primaryStage.setScene(this.getMenu());
    }

    public void showGame(){
        primaryStage.setScene(this.getGame());
    }



}
