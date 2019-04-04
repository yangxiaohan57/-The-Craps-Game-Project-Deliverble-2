package crapsgame;

import java.util.ArrayList;

/**
 * Craps Game - Second Deliverable
 * This is the Controller for MVC framework.
 * @author Xiaohan Yang 03/26/19
 */
public class Controller {
    private final ModelPlayerList userListCtrl;
    private final ModelGameEngine engine;
    private int gameID = 0;

    public Controller(ModelPlayerList userList, ModelGameEngine engine) {
        this.userListCtrl = userList;
        this.engine = engine;
    }
    
    /**
     * Get player ArrayList
     * @return 
     */
    public ArrayList<ModelPlayer> getList() {
        return userListCtrl.getUsers();
    }
    
    public ArrayList<ModelGameRecords> getGameList(int id) {
        return userListCtrl.getUserGameRecord(id);
    }
    
    /**
     * Create player and add it to ArrayList
     * @param fName
     * @param lName 
     */
    public void createAndAddUser(String fName, String lName){
        ModelPlayer user = new ModelPlayer(fName, lName);
        user.setUserID(this.generateNewId());
        userListCtrl.addUser(user);
    }
    
    /**
     * Delete a player by id
     * @param id 
     */
    public void deleteUserById(int id){
        userListCtrl.deleteUser(id);
    }
    
    /**
     * Generate a new ID for a new player
     * @return 
     */
    private int generateNewId() {
        int size = userListCtrl.getUsers().size();
        
        if (size == 0) {
            return 1;
        } else {
            return userListCtrl.getUsers().get(size-1).getUserID()+1;
        }
    }
    
    public boolean findUser(int id) {
        return userListCtrl.findUser(id);
    }
    
    /**
     * Check if the ArrayList is empty or not
     * @return 
     */
    public boolean isEmpty() {
        return userListCtrl.isEmpty();
    }
    
    /**
     * Print all elements in ArrayList
     */
    public void printList() {
        userListCtrl.showAll();
    }
    
    public int getDie1() {
        return engine.getDie1();
    }
    
    public int getDie2() {
        return engine.getDie2();
    }
    
    public int getSum() {
        return engine.getSum();
    }
    
    public void rollDice() {
        engine.start();
    }
    
    public int getStatus() {
        return engine.getResult();
    }
    
    public int getPoint() {
        return engine.getPoint();
    }
    
    public void creatRecord(int id, int status, int point) {
        String result = "";
        if (status == 0) {
            result = "win";
        } else if (status == 1) {
            result = "lose";
        }
        ModelGameRecords gr = new ModelGameRecords(++gameID, result, point);
        userListCtrl.addRecord(id, gr);  
    }
}
