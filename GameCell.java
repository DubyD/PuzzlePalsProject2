/**
 * @author Evelyn Totman.
 */
public class GameCell {
    private int curVal;
    private boolean error;

    public GameCell(){
        curVal = 0;
        error = false;
    }

    public int getCurVal() {
        return curVal;
    }

    public void setCurVal(int curVal) {
        this.curVal = curVal;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int clickCell(){
        if(curVal < 2){
            curVal++;
        }
        else {
            curVal = 0;
        }
        return curVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCell gameCell = (GameCell) o;
        return curVal == gameCell.curVal;
    }

}
