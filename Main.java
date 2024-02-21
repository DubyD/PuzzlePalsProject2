import javafx.application.*;
import javafx.stage.*;

public class Main extends Application{


    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        SceneSetter hud = new SceneSetter(primaryStage);
        hud.showMenu();

        primaryStage.setTitle("PuzzlePals");
        primaryStage.show();
    }
}