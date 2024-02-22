/**
 * @author Evelyn Totman. Handles backend logic for the game board.
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class Puzzle {
    private ArrayList<PuzzleTable> gBoard;
    private ArrayList<PuzzleTable> answerKey;
    private LinkedList<ArrayList<PuzzleTable>> stateStack;

    public Puzzle(){

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

    }

    public void undo(){

    }

    public boolean isFinished(){

    }

    public void hint(){

    }
}
