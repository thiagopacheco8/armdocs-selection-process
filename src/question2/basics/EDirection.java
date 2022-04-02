package question2.basics;

public enum EDirection {
    N(0,1),
    E(1,0),
    S(0,-1),
    W(-1,0);

    private int deltaX;
    private int deltaY;

    EDirection(int deltaX, int deltaY){
        this.deltaX=deltaX;
        this.deltaY=deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
