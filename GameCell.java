/**
 * @author Evelyn Totman.
 */
public class GameCell {
    private int curVal;
    private String stringVal;
    private boolean header;
    private boolean error;

    public GameCell(boolean header){
        this.header = header;
        curVal = 0;
        error = false;
        if(header){
            stringVal = "Header";
        }
    }

    public GameCell(){
        this(false);
    }

    public int getCurVal() {
        return curVal;
    }

    public void setCurVal(int curVal) {
        this.curVal = curVal;
        setStringVal();
    }

    public String getStringVal() {
        return stringVal;
    }

    public void setStringVal(){
        if(curVal == 0){
            stringVal = " ";
        }
        if(curVal == 1){
            stringVal = "X";
        }
        if(curVal == 2){
            stringVal = "O";
        }
    }

    public boolean isError() {
        return error;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
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
        setStringVal();
        return curVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCell gameCell = (GameCell) o;
        return curVal == gameCell.curVal;
    }

    @Override
    public String toString(){
        return stringVal;
    }
}
