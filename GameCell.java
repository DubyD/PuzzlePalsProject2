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
        this.curVal = 0;
        this.error = false;
        if(header){
            this.stringVal = "Header";
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
        this.setStringVal();
    }

    public String getStringVal() {
        return stringVal;
    }

    public void setStringVal(){
        if(this.curVal == 0){
            this.stringVal = " ";
        }
        if(this.curVal == 1){
            this.stringVal = "X";
        }
        if(this.curVal == 2){
            this.stringVal = "O";
        }
    }

    public boolean isError() {
        return this.error;
    }

    public boolean isHeader() {
        return this.header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }

    public void setHeaderString(String header) {
        this.stringVal = header;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int clickCell(){
        if(this.curVal < 2){
            this.curVal++;
        }
        else {
            this.curVal = 0;
        }
        this.setStringVal();
        return curVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCell gameCell = (GameCell) o;
        return this.curVal == gameCell.curVal;
    }

    @Override
    public String toString(){
        return this.stringVal;
    }
}
