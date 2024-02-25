import java.util.Objects;

/**
 * @author Evelyn Totman.
 */
public class GameCell {
    private int curVal;
    private String stringVal;
    private boolean header;

    private String answerKey;
    private int answerVal;
    private int x;
    private int y;


    public GameCell(int x, int y){

            //initializes a GameCell
        this.header = false;
        this.curVal = 0;
        this.stringVal = "  ";
        this.x = x;
        this.y = y;
        this.answerKey = "";
        this.answerVal = 0;
    }

        //second constructor without param to complete class
    public GameCell(){
        this(0, 0);
    }

        //Sets the CurVal if a cell with the same row or column is considered the right answer
    public void setCurVal(int curVal) {
        this.curVal = curVal;
        this.setStringVal();
    }

        //Used to associate non-header GameCells with Specific Strings
    private void setStringVal(){
        if(this.curVal == 0){
            this.stringVal = "  ";
        }
        if(this.curVal == 1){
            this.stringVal = "X";
        }
        if(this.curVal == 2){
            this.stringVal = "O";
        }
    }

    //returns the current value of this cell
    public int getCurVal() {
        return curVal;
    }

    //States whether this is a header or not
    public boolean isHeader() {
        return this.header;
    }

        //Sets this GameCell as a Header if needed
    public void setHeader(boolean header) {
        this.header = header;
    }

        //Sets the String Value of a Header
    public void setHeaderString(String header) {
        this.stringVal = header;

    }

    //get the row of this cell
    public int getX(){
        return this.x;
    }

    //get the column of this cell
    public int getY(){
        return this.y;
    }

    public void clickCell(){
        if(this.curVal < 2){
            this.curVal++;
        }
        else {
            this.curVal = 0;
        }
        this.setStringVal();
    }

        //Checks to see if the user input the correct answer
    public boolean isError(){

            //Skips over Header Cells when checking answers
        if(this.header == true){
            return this.header;
        }
            //Returns whether this Cell is Correct or not
        if(this.answerKey.equals(this.stringVal)){
            return true;
        }else{
            return false;
        }
    }

        //Used to set the answer key to each section of Puzzle
    public void setAnswer(String correctAnswer){
        this.answerKey = correctAnswer;
        if(this.answerKey.equals("X")){
            this.answerVal = 1;
        }
        if(this.answerKey.equals("O")){
            this.answerVal = 0;
        }
    }

    public String getAnswerKey(){
        return this.answerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCell gameCell = (GameCell) o;
        return this.curVal == gameCell.curVal && Objects.equals(this.stringVal, gameCell.stringVal);
    }

    @Override
    public String toString() {
        return this.stringVal;
    }
}
