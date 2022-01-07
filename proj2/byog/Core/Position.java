package byog.Core;

public class Position {
    public int x;
    public int y;

    public Position(int xx,int yy) {
        this.x = xx;
        this.y = yy;
    }

    public Position PosOffsetVal(int rightOffset, int upOffset){
        Position a = new Position(this.x, this.y);
        a.x = a.x + rightOffset;
        a.y = a.y + upOffset;
        return a;
    }


}
