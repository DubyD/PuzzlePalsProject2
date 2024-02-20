import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class Main extends Application{


    public static void main(String [] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        GameMenu hud = new GameMenu();
        Scene guiComponents = new Scene(hud.getMenu(), 400, 400);

        primaryStage.setTitle("Rock Game");
        primaryStage.setScene(guiComponents);
        primaryStage.show();
    }
}