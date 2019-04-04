package crapsgame;

/**
 * Craps Game - Second Deliverable
 * This is one of the Models for MVC framework.
 * This class is for game records.
 * @author Xiaohan Yang 03/26/19
 */
public class ModelGameRecords {
    private int gameID;
    private String result;
    private int point;

    public ModelGameRecords(int gameID, String result, int point) {
        this.gameID = gameID;
        this.result = result;
        this.point = point;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String isResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "GameRecords{" + "gameID=" + gameID + ", result=" + result + ", point=" + point + '}';
    }
    
    
}
