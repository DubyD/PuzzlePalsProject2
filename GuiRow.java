//Author WD

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class GuiRow {
        //There are a lot of instances of these row formats so to tidy up the code I made a new class

    private HBox row;
    private Label instruction;
    private Button action;

    public GuiRow(){

            //Basic Box format
        this.row = new HBox();
        this.row.setAlignment(Pos.CENTER);

            //Basic Children
        this.instruction = new Label();
        this.action = new Button();

        this.row.getChildren().add(this.instruction);
        this.row.getChildren().add(this.action);
    }

        //Customize Label
    public void setLabel(String words){
        this.instruction.setText(words);
    }

        //Customize Button Text
    public void setButton(String words){
        this.action.setText(words);
    }

        //For custom Actions
    public Button getButton(){
        return this.action;
    }

    public HBox getRow(){
        return this.row;
    }

}
