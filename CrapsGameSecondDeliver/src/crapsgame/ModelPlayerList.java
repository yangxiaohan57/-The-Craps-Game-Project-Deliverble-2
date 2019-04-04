package crapsgame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Craps Game - Second Deliverable
 * This is one of the Models for MVC framework.
 * This class stores players and records in ArrayList.
 * @author Xiaohan Yang
 */
public class ModelPlayerList {
    private ArrayList<ModelPlayer> userList;

    public ModelPlayerList() {
        userList = new ArrayList();
    }
    
    /**
     * Get ArrayList
     * @return 
     */
    public ArrayList<ModelPlayer> getUsers() {
        return userList;
    }
    
    public ArrayList<ModelGameRecords> getUserGameRecord(int id) {
        Iterator<ModelPlayer> itr = userList.iterator();
        while(itr.hasNext()) {
            ModelPlayer temp = (ModelPlayer)itr.next();
            if (temp.getUserID() == id){
                return temp.getRecords();
            }
        }
        return null;
    }
    
    /**
     * Add player to ArrayList
     * @param newUser 
     */
    public void addUser(ModelPlayer newUser) {
        userList.add(newUser);
    }
    
    /**
     * Delete player by ID
     * @param id 
     */
    public void deleteUser(int id) {
        Iterator<ModelPlayer> itr = userList.iterator();
        while(itr.hasNext()) {
            ModelPlayer temp = (ModelPlayer)itr.next();
            if (temp.getUserID() == id){
                itr.remove();
            }
        }
    }
    
    public boolean findUser(int id) {
        Iterator<ModelPlayer> itr = userList.iterator();
        while(itr.hasNext()) {
            ModelPlayer temp = (ModelPlayer)itr.next();
            if (temp.getUserID() == id){
                return true;
            }
        }
        return false;
    }
    
    public void addRecord(int id, ModelGameRecords gr) {
        Iterator<ModelPlayer> itr = userList.iterator();
        while(itr.hasNext()) {
            ModelPlayer temp = (ModelPlayer)itr.next();
            if (temp.getUserID() == id){
                temp.addRecord(gr);
            }
        }
    }
    
    public boolean isEmpty() {
        return userList.isEmpty();
    }
    
    public void showAll() {
        if (userList.size() != 0) {
            for (ModelPlayer user : userList) {
                System.out.println(user);
            }
        } else {
            System.out.println("Empty List.");
        }
    }
    
}
