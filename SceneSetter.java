//Author WD

import javafx.scene.*;
import javafx.stage.*;
public class SceneSetter {

        //Creates a pointer to the Main classes stage
    private Stage primaryStage;

        //The Scenes to choose from
    private Scene menuScene;
    private Scene gameScene;

    public SceneSetter(Stage primaryStage){

            //A pointer to the primaryStage
        this.primaryStage = primaryStage;

            //Sets the Scenes and Buttons
        this.setScenes();
    }

        //Sets up the MenuScene to be able to Activate the Game
    private void setScenes(){

            // creating custom settings of menuScene components
            // creating a pointer to one of the VBox's children (Button)
            // for desired results when clicked
        MenuGui menuSetter = new MenuGui();

            //Lambda function for menu StartButton
        menuSetter.getStartButton().setOnAction(event ->{
                //Catches if a user hasn't selected a puzzle size
            if(menuSetter.getSelectedItem() != null){
                showGame(menuSetter.getSelectedItem());
            }
        });
        this.menuScene = new Scene(menuSetter.getMenu(), 400, 200);


    }


        //The switchboard between game screens
        //Creates a new iteration of GameScene every time the StartButton is pressed
    private void showGame(String puzzleSize){

            // creating a New custom settings of gameMenu components
            // creating a pointer to one of the VBox's children (Button)
            // for desired results when clicked
        Board gameBoard = new Board(puzzleSize);
        gameBoard.getEndButton().setOnAction(event->{
            showMenu();
        });
        this.gameScene = new Scene(gameBoard.getBoard(), 600, 600);
        primaryStage.setScene(this.gameScene);
    }

    public void showMenu(){

        primaryStage.setScene(this.menuScene);

    }

    @Override
    public String toString(){
        String reply = "This is a SwitchBoard for tranversing between a game and a game menu";
        return reply;
    }

}
