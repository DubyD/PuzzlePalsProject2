import java.util.ArrayList;
import java.util.LinkedList;

public class Puzzle {
    private ArrayList<GameCell[][]> gBoard;
    private LinkedList<ArrayList<GameCell[][]>> stateStack;
    private ArrayList<GameCell[][]> answerKey;

    /**
     * initializes board with given size.
     * @param categories - number of categories
     * @param items - number of items in each category
     * @param answerKey - answerkey
     */
    public Puzzle(int categories, int items, ArrayList<GameCell[][]> answerKey){
        gBoard = new ArrayList<>();
        this.answerKey = answerKey;
        stateStack = new LinkedList<>();
        int sqrNum;
        if(categories == 3){
            sqrNum = 3;
        }
        else {
            sqrNum = 6;
        }
        for(int i = 0; i < sqrNum; i++){
            gBoard.add(new GameCell[items][items]);
            for(int j = 0; j < items; j++){
                for (int k = 0; k < items; k++){
                    gBoard.get(i)[j][k] = new GameCell();
                }
            }
        }
        stateStack.push(gBoard);
    }

    public void setAnswerKey(ArrayList<GameCell[][]> answerKey) {
        this.answerKey = answerKey;
    }

    public void clickCell(int square, int xval, int yval){
        int val = gBoard.get(square)[xval][yval].clickCell();
        if(val == 2){
            for(int i = 0; i < gBoard.get(square).length; i++){
                if(i != xval){
                    gBoard.get(square)[i][yval].setCurVal(1);
                }
                if (i != yval){
                    gBoard.get(square)[xval][i].setCurVal(1);
                }
            }
        }
        stateStack.push(gBoard);
    }

    public boolean clearErrors(){
        return true;
    }

    public ArrayList<GameCell[][]> getgBoard(){
        return gBoard;
    }

    public void undo(){

    }

    public void hint(){

    }

}
