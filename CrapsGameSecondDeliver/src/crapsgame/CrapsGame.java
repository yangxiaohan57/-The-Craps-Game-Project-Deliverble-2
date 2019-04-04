package crapsgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Craps Game - Second Deliverable
 * Implemented player interactions and basic GUI
 * @author Xiaohan Yang 03/26/2019
 */

/**
 * Test Instructions:
 * 1. Build and run the program.
 * 2. To start the game, you need to create a new user by hitting "New User" button.
 * 3. Click in "View User" to view all users, and the user you just created should appear.
 * 4. You will be able to know your user ID in "View User" interface.
 * 5. Enter you user ID in the entry box at the bottom right. Then click "OK".
 * 6. Start rolling by hit "Roll" button. The text will tell you the game status.
 * 7. If you want to view your game record, click into "View Records", and enter your user ID.
 * 8. You should be able to view all the games you played and your status in the record list.
 * 9. If you want to play this game as a different player, just create a new user and start over.
 * 
 */

public class CrapsGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ModelPlayerList playerList = new ModelPlayerList();
        ModelGameEngine engine = new ModelGameEngine();
        Controller ctrl = new Controller(playerList, engine);
        Viewer ui = new Viewer(ctrl);
        
        Scene scene = new Scene(ui.asParent(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Launch the game GUI
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}