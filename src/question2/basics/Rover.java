package question2.basics;

public class Rover {

    private int xPosition;
    private int yPosition;
    private EDirection direction;

    public Rover() {
        this.xPosition = 0;
        this.yPosition = 0;
        this.direction = EDirection.N;
    }

    public Rover(int xPosition, int yPosition, EDirection direction) {
        this.direction = direction;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void move()
    {
        this.xPosition += this.direction.getDeltaX();
        this.yPosition += this.direction.getDeltaY();
    }

    public void turnLeft()
    {
        switch(this.direction){
            case N:
                this.direction=EDirection.W;
                break;
            case W:
                this.direction=EDirection.S;
                break;
            case S:
                this.direction=EDirection.E;
                break;
            case E:
                this.direction=EDirection.N;
                break;
        }
    }

    public void turnRight()
    {
        switch(this.direction){
            case N:
                this.direction=EDirection.E;
                break;
            case E:
                this.direction=EDirection.S;
                break;
            case S:
                this.direction=EDirection.W;
                break;
            case W:
                this.direction=EDirection.N;
                break;
        }
    }

    @Override
    public String toString() {
        return this.xPosition + " " + this.yPosition + " " +  this.direction.toString();
    }
}
