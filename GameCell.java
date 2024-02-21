//Author: WD
//WD---vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv



public class GameCell {
    private int curVal;

    public GameCell(){
        curVal = 0;
    }

    public int getCurVal() {
        return curVal;
    }

    public void setCurVal(int curVal) {
        this.curVal = curVal;
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
}
