public class Connections {
    private Infobutton startPoint;
    private Infobutton endPoint;
    private int startXPosition;
    private int startYPosition;
    private int endYPosition;
    private int endXPosition;

    public Connections(int startXPosition, int startYPosition, int endXPosition, int endYPosition, Infobutton startPoint,Infobutton endPoint) {
        this.startXPosition = startXPosition;
        this.startYPosition = startYPosition;
        this.endXPosition = endXPosition;
        this.endYPosition = endYPosition;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Infobutton getStartPoint() {
        return startPoint;
    }

    public Infobutton getEndPoint() {
        return endPoint;
    }

    public int getStartXPosition() {
        return startXPosition;
    }

    public void setStartXPosition(int startXPosition) {
        this.startXPosition = startXPosition;
    }

    public int getStartYPosition() {
        return startYPosition;
    }

    public void setStartYPosition(int startYPosition) {
        this.startYPosition = startYPosition;
    }

    public int getEndYPosition() {
        return endYPosition;
    }

    public void setEndYPosition(int endYPosition) {
        this.endYPosition = endYPosition;
    }

    public int getEndXPosition() {
        return endXPosition;
    }

    public void setEndXPosition(int endXPosition) {
        this.endXPosition = endXPosition;
    }
}
