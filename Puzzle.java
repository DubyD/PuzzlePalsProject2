/**
 * @author Evelyn Totman. Handles backend logic for the game board.
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class Puzzle {
    private ArrayList<PuzzleTable> gBoard;
    private ArrayList<PuzzleTable> answerKey;
    private LinkedList<ArrayList<PuzzleTable>> stateStack;

    public Puzzle(int categories, int dim){
        gBoard = new ArrayList<>();
        answerKey = new ArrayList<>();
        if(categories == 3){
            gBoard.add(new PuzzleTable(dim + 1, dim + 1, true, true));
            gBoard.add(new PuzzleTable(dim + 1, dim, false, true));
            gBoard.add(new PuzzleTable(dim, dim + 1, true, false));
        }
        else{
            gBoard.add(new PuzzleTable(dim + 1, dim + 1, true, true));
            gBoard.add(new PuzzleTable(dim + 1, dim, false, true));
            gBoard.add(new PuzzleTable(dim + 1, dim, false, true));
            gBoard.add(new PuzzleTable(dim));
            gBoard.add(new PuzzleTable(dim, dim + 1, true, false));
            gBoard.add(new PuzzleTable(dim, dim + 1, true, false));
        }

        stateStack = new LinkedList<>();
        stateStack.push(gBoard);
    }

    public void setAnswerKey(ArrayList<PuzzleTable> answerKey) {
        this.answerKey = answerKey;
    }

    public ArrayList<PuzzleTable> getgBoard() {
        return gBoard;
    }

    public ArrayList<PuzzleTable> getAnswerKey() {
        return answerKey;
    }

    public void clickCell(int piece, int row, int column){
        gBoard.get(piece).clickCell(row, column);
    }

    public boolean clearErrors(){
        boolean noErrors = true;
        for(int i = 0; i < gBoard.size(); i++){
            if(!gBoard.get(i).equals(answerKey.get(i))){
                noErrors = false;
                for(int j = 0; j < gBoard.get(i).getSection().length; j++){
                    for (int k = 0; k < gBoard.get(i).getSection().length; k++){
                        if(!gBoard.get(i).getCell(j, k).equals(answerKey.get(i).getCell(j, k))){
                            gBoard.get(i).getCell(j, k).setError(true);
                            gBoard.get(i).getCell(j, k).setCurVal(0);
                        }
                    }
                }
            }
        }
        stateStack.push(gBoard);
        return noErrors;
    }

    public void undo(){
        if(!stateStack.isEmpty()){
            gBoard = stateStack.pop();
        }
    }

    public boolean isFinished(){
        boolean gameWon = true;
        for(int i = 0; i < gBoard.size(); i++){
            gameWon = gBoard.get(i).equals(answerKey.get(i));
        }
        return gameWon;
    }

    public void hint(){

    }
}
