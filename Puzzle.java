/**
 * @author Evelyn Totman. Handles backend logic for the game board.
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class Puzzle {
    private ArrayList<PuzzleTable> gBoard;
    private ArrayList<String[][]> answerKey;
    private LinkedList<ArrayList<PuzzleTable>> stateStack;
    private String puzzleSize;
    private int categories;
    private ArrayList<ArrayList<String>> topHeaders;
    private ArrayList<ArrayList<String>> leftHeaders;

    public Puzzle(int categories, int dim){

        this.gBoard = new ArrayList<>();

        this.answerKey = PuzzleReader.getAnswerKey();

        this.puzzleSize = categories + "x" + dim;
            //Used for Hint
        this.categories = categories;
        this.topHeaders = PuzzleReader.getTopData();
        this.leftHeaders = PuzzleReader.getLeftData();

        int header = dim + 1;
        if(categories == 3){
                //top left section
            gBoard.add(new PuzzleTable(header, header, true));
                //top right section
            gBoard.add(new PuzzleTable(header, dim, false));
                //bottom left section
            gBoard.add(new PuzzleTable(dim, header,false));
        }
        else{
                //top left section
            gBoard.add(new PuzzleTable(header, header, true));
                //top middle section
            gBoard.add(new PuzzleTable(header, dim, false));
                //top right section
            gBoard.add(new PuzzleTable(header, dim, false));
                //middle left section
            gBoard.add(new PuzzleTable(dim, header,false));
                //middle middle section
            gBoard.add(new PuzzleTable(dim, dim, false));
                //bottom left section
            gBoard.add(new PuzzleTable(dim, header,false));
        }
        this.stateStack = new LinkedList<>();
        stateStack.push(gBoard);

            //Private methods to set the rest of the Data in this Puzzle Obj.
        this.setAnswerKey();
        this.setHeaders(this.topHeaders, "Top");
        this.setHeaders(this.leftHeaders, "Left");
    }
    private void setHeaders(ArrayList<ArrayList<String>> headers, String xSide){
        for(int i = 0; i < gBoard.size(); i++){

            if(xSide.equals("Top")){
                if(gBoard.get(i).getColumnHeader() == true){
                    ArrayList<String> working = headers.get(i);
                    gBoard.get(i).addTopHeader(working);

                }
            }else{
                if(gBoard.get(i).getRowHeader() == true){
                    ArrayList<String> working = headers.get(i);
                    gBoard.get(i).addLeftHeader(working);

                }
            }
        }
    }

        //used in the constructor
    private void setAnswerKey() {


            //Separates each answer section to it's respected section of the Puzzle
        for(int number = 0; number > this.answerKey.size(); number++){

                //Separating each Section into each PuzzleTable
            String[][] answers = this.answerKey.get(number);
            PuzzleTable workingArea = this.gBoard.get(number);
            workingArea.connectingAnswers(answers);
        }
    }


    public ArrayList<PuzzleTable> getgBoard() {
        return gBoard;
    }

    /*  Not sure if needed. Set each gamecell to have it's answer value
    public ArrayList<PuzzleTable> getAnswerKey() {
        return answerKey;
    }

        Added the click event to each PuzzleTable as not to mess with the other PuzzleTable Values
    public void clickCell(int piece, int row, int column){
        gBoard.get(piece).clickCell(row, column);
        stateStack.push(gBoard);
    }

     */


/*
    public boolean clearErrors(){
        boolean noErrors = true;
        for(int i = 0; i < gBoard.size(); i++){
            if(!gBoard.get(i).equals(answerKey.get(i))){
                for(int j = 0; j < gBoard.get(i).getSection().length; j++){
                    for (int k = 0; k < gBoard.get(i).getSection().length; k++){
                        if(!gBoard.get(i).getCell(j, k).equals(answerKey.get(i).getCell(j, k)) && gBoard.get(i).getCell(j, k).getCurVal() != 0){
                            noErrors = false;
                            gBoard.get(i).getCell(j, k).setCurVal(0);
                        }
                    }
                }
            }
        }
        if(!noErrors){
            stateStack.push(gBoard);
        }
        return noErrors;
    }

    public void undo(){
        if(!stateStack.isEmpty()){
            gBoard = stateStack.pop();
        }
    }

 */

    //checks if the puzzle is finished
    public boolean isFinished(){
        boolean gameWon = true;
        for(int i = 0; i < gBoard.size(); i++){
            gameWon = gameWon && gBoard.get(i).isCorrect();
        }
        return gameWon;
    }

        //Returns a string of where a change occurred
    public String hint(){
        String reply = "";
        for(int i =0; i < gBoard.size(); i++){

                //Panel where change occurred
            String errorPanel = "";
            if(i == 1) {
                errorPanel = " Top Left";
            }
                //Changing Section names for different puzzle sizes
            if(this.categories == 3){
                if(i == 2){
                    errorPanel = " Top Right";
                }
                if(i == 3){
                    errorPanel = " Bottom left";
                }
            }else{
                if(i == 2){
                    errorPanel = " Top Middle";
                }
                if(i == 3){
                    errorPanel = " Top Right";
                }
                if(i == 4){
                    errorPanel = " Middle Left";
                }
                if(i == 5){
                    errorPanel = " Middle Middle";
                }
                if(i == 6){
                    errorPanel = " Bottom Left";
                }
            }
            reply = gBoard.get(i) + errorPanel + " Section";
        }
        return reply;
    }
}
