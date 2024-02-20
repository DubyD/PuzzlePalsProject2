//Author WD

import javafx.scene.layout.VBox;

public class SceneSetter {

        //The background for the entire game, used to
    private VBox overMenu;
    private VBox menuOption;
    private VBox gameOption;

    public SceneSetter(){

            //Parent for the stage
        this.overMenu = new VBox();

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
        this.menuOption = MenuGui.getMenu();
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




}
