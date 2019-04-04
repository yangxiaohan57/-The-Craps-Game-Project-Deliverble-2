package crapsgame;

import java.util.ArrayList;

/**
 * Craps Game - Second Deliverable
 * This is one of the Models for MVC framework.
 * This class is a player class.
 * @author Xiaohan Yang
 */
public class ModelPlayer {
    private String Fname;
    private String Lname;
    private int userID;
    private double winRate;
    private ArrayList<ModelGameRecords> records;

    public ModelPlayer(String Fname, String Lname) {
        this.Fname = Fname;
        this.Lname = Lname;
        userID = 0;
        winRate = 0;
        records = new ArrayList();
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public ArrayList<ModelGameRecords> getRecords() {
        return records;
    }
    
    public void addRecord(ModelGameRecords gr){
        records.add(gr);
    }

    @Override
    public String toString() {
        return "Player{" + "Fname=" + Fname + ", Lname=" + Lname + ", userID=" + userID + ", winRate=" + winRate + '}';
    }

    
}