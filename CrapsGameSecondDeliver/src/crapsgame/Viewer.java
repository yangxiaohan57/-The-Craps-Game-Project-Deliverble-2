package crapsgame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Craps Game - Second Deliverable
 * This is the Viewer for MVC framework.
 * @author Xiaohan Yang 03/26/2019
 */
public class Viewer {
    private int currentUser;
    private int storePoint;
    
    private GridPane grid;
    
    private final Controller ctrl;
    
    private final ObservableList<ModelPlayer> data;
    private ObservableList<ModelGameRecords> record = null;

    public Viewer(Controller ctrl) {
        this.ctrl = ctrl;
        data = FXCollections.observableList(ctrl.getList());
        currentUser = 0;
        storePoint = 0;
        createGrid();
    }
    
    public Parent asParent() {
        return grid;
    }
    
    /**
     * Main game user interface
     */
    private void createGrid() {
        grid = new GridPane();
        grid.setPadding(new Insets(40, 40, 35, 60));//Up, Down, right, left
        grid.setVgap(50);
        grid.setHgap(80);

        
        //labels and text fields
        Label die1 = new Label("Die1 value: ");
        Text d1Value = new Text();
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(die1, d1Value);
        
        Label die2 = new Label("Die2 value: ");
        Text d2Value = new Text();
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(die2, d2Value);
        
        Label point = new Label("Current point: ");
        Text p = new Text();
        HBox hb3 = new HBox();
        hb3.getChildren().addAll(point, p);
        
        Label sumTag = new Label("Sum: ");
        Text sum = new Text();
        HBox hb4 = new HBox();
        hb4.getChildren().addAll(sumTag, sum);
        
        Label gameStatus = new Label("Roll dice!");
        gameStatus.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
        
        hb1.setSpacing(20);
        hb2.setSpacing(20);
        hb3.setSpacing(20);
        hb4.setSpacing(20);
        GridPane.setConstraints(hb1, 0, 0);
        grid.getChildren().add(hb1);
        GridPane.setConstraints(hb2, 0, 1);
        grid.getChildren().add(hb2);
        GridPane.setConstraints(hb3, 0, 2);
        grid.getChildren().add(hb3);
        GridPane.setConstraints(hb4, 0, 3);
        grid.getChildren().add(hb4);
        
        //the roll button
        Button roll = new Button("Roll!");
        Button newUser = new Button("New User");
        newUser.setPrefWidth(100);
        Button viewUser = new Button("View User");
        viewUser.setPrefWidth(100);
        Button deleteUser = new Button("Delete User");
        deleteUser.setPrefWidth(100);
        Button record = new Button("View Record");
        record.setPrefWidth(100);
        
        TextField idSlot = new TextField();
        idSlot.setPromptText("Your ID.");
        Button ok = new Button("OK");
        idSlot.setPrefWidth(80);
        HBox hb5 = new HBox();
        hb5.getChildren().addAll(idSlot, ok);
        
        GridPane.setConstraints(roll, 0, 5);
        grid.getChildren().add(roll);
        
        GridPane.setConstraints(newUser, 2, 0);
        grid.getChildren().add(newUser);
        
        GridPane.setConstraints(deleteUser, 2, 1);
        grid.getChildren().add(deleteUser);
        
        GridPane.setConstraints(viewUser, 2, 2);
        grid.getChildren().add(viewUser);
        
        GridPane.setConstraints(record, 2, 3);
        grid.getChildren().add(record);
        
        GridPane.setConstraints(hb5, 2, 4);
        grid.getChildren().add(hb5);
        
        GridPane.setConstraints(gameStatus, 0, 4);
        GridPane.setColumnSpan(gameStatus, 2);
        grid.getChildren().add(gameStatus);
        
        roll.setOnAction((ActionEvent e) -> {
            
            if (currentUser != 0) {
                ctrl.rollDice();
                d1Value.setText(Integer.toString(ctrl.getDie1()));
                d2Value.setText(Integer.toString(ctrl.getDie2()));
                p.setText(Integer.toString(ctrl.getPoint()));
                sum.setText(Integer.toString(ctrl.getSum()));
                
                if (ctrl.getPoint() != 0) {
                    storePoint = ctrl.getPoint();
                }

                int result = ctrl.getStatus();
                
                switch(result) {
                    case 0:
                       gameStatus.setText("You won!");
                       gameStatus.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
                       ctrl.creatRecord(currentUser, result, storePoint);
                       idSlot.setEditable(true);
                       break;
                    case 1:
                        gameStatus.setText("You lose!");
                        gameStatus.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
                        ctrl.creatRecord(currentUser, result, storePoint);
                        idSlot.setEditable(true);
                        break;
                    case 2:
                        gameStatus.setText("Keeps rolling!");
                        gameStatus.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
                        break;
                }
                
            } else {
                gameStatus.setText("Enter your ID to start!");
                gameStatus.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            }
        });
        
        ok.setOnAction((ActionEvent e) -> {
            String sID = idSlot.getText();
            int ID = Integer.parseInt(sID);
            
            if (!ctrl.findUser(ID)){
                idSlot.setText("Invalid");
            } else {
                currentUser = ID;
                idSlot.setEditable(false);
                gameStatus.setText("Ready to roll!");
                gameStatus.setStyle("-fx-text-fill: blue; -fx-font-size: 16px;");
            }
        });
        
        newUser.setOnAction((ActionEvent e) -> {
            UserInputWindow();
        });
        
        deleteUser.setOnAction((ActionEvent e) -> {
            DeleteUserWindow();
        });
        
        viewUser.setOnAction((ActionEvent e) -> {
            UserListTable();
        });
        
        record.setOnAction((ActionEvent e) -> {
            RecordFinder();
        });
        
    }
    /**
     * Add new user
     */
    private void UserInputWindow() {
        Stage newStage = new Stage();
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(20);
        grid.setHgap(5);
        
        //labels and text fields
        Label fName = new Label("First Name: ");
        TextField fNameField = new TextField ();
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(fName, fNameField);
        hb1.setSpacing(10);
        GridPane.setConstraints(hb1, 0, 1);
        grid.getChildren().add(hb1);
        
        Label lName = new Label("Last Name: ");
        TextField lNameField = new TextField ();
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(lName, lNameField);
        hb2.setSpacing(10);
        GridPane.setConstraints(hb2, 0, 2);
        grid.getChildren().add(hb2);
        
        //the Submit and Clear button
        Button submit = new Button("Submit");
        submit.setPrefWidth(80);
        Button clear = new Button("Clear");
        clear.setPrefWidth(80);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(submit, clear);
        buttonBox.setSpacing(60);
        GridPane.setConstraints(buttonBox, 0, 3);
        grid.getChildren().add(buttonBox);
        
        Label label = new Label();
        label.setText("Please enter your name:");
        label.setFont(new Font("Arial", 16));
        GridPane.setConstraints(label, 0, 0);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);
        
        submit.setOnAction((ActionEvent e) -> {
            String first = fNameField.getText();
            String last = lNameField.getText();
            if (first != null || fNameField.getText().trim().equals("") || fNameField.getText().isEmpty()) {
                ctrl.createAndAddUser(first, last);
                ctrl.printList();
            } else {
                label.setText("First name cannot be empty.");
            }
        });
        
        clear.setOnAction((ActionEvent e) -> {
            fNameField.setText("");
            lNameField.setText("");
        });
        
        Scene stageScene = new Scene(grid, 330, 230);
        newStage.setScene(stageScene);
        newStage.showAndWait();
    }
    /**
     * Delete a player by id
     */
    private void DeleteUserWindow() {
        Stage stage = new Stage();
        stage.setTitle("Delete User");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(20);
        grid.setHgap(5);
        
        Label prompt = new Label("Enter the user id to delete:");
        TextField idInput = new TextField();
        Button delete = new Button("Delete");
        delete.setPrefWidth(80);
        Button clear = new Button("Clear");
        clear.setPrefWidth(80);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(delete, clear);
        buttonBox.setSpacing(50);
        
        GridPane.setConstraints(prompt, 0, 0);
        grid.getChildren().add(prompt);
        GridPane.setConstraints(idInput, 0, 1);
        grid.getChildren().add(idInput);
        GridPane.setConstraints(buttonBox, 0, 2);
        grid.getChildren().add(buttonBox);
        
        delete.setOnAction((ActionEvent e) -> {
            if (!ctrl.isEmpty()) {
                ctrl.deleteUserById(Integer.valueOf(idInput.getText()));
                ctrl.printList();
            } else {
                idInput.setText("Empty List!");
            }
            
        });
        
        clear.setOnAction((ActionEvent e) -> {
            idInput.setText("");
        });
        
        Scene stageScene = new Scene(grid, 230, 180);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
    /**
     * Generate player list
     */
    private void UserListTable() {
        Stage stage = new Stage();
        stage.setTitle("Player List");
        
        TableView table = new TableView();
        
        final Label label = new Label("Player List");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn c1 = new TableColumn("User ID");
        c1.setCellValueFactory(new PropertyValueFactory<ModelPlayer, Integer>("userID"));
        TableColumn c2 = new TableColumn("First Name");
        c2.setCellValueFactory(new PropertyValueFactory<ModelPlayer, String>("Fname"));
        TableColumn c3 = new TableColumn("Last Name");
        c3.setCellValueFactory(new PropertyValueFactory<ModelPlayer, String>("Lname"));
        
        table.setItems(data);
        table.getColumns().addAll(c1, c2, c3);
        
        final HBox hbox = new HBox();
        hbox.setSpacing(30);
        
        
        Button refresh = new Button("Refresh");
        refresh.setOnAction((ActionEvent e) -> {
            table.refresh();
        });
        
        hbox.getChildren().addAll(label, refresh);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 0, 0, 10));
        grid.setVgap(20);
        GridPane.setConstraints(hbox, 0, 0);
        grid.getChildren().add(hbox);
        GridPane.setConstraints(table, 0, 1);
        grid.getChildren().add(table);
        
        Scene stageScene = new Scene(grid, 280, 500);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
    /**
     * Find game record by player id
     */
    private void RecordFinder() {
        Stage stage = new Stage();
        stage.setTitle("Record Finder");
        GridPane grid1 = new GridPane();
        grid1.setPadding(new Insets(30, 30, 30, 30));
        grid1.setVgap(20);
        grid1.setHgap(5);
        
        Label prompt = new Label("View record by id:");
        TextField idInput = new TextField();
        Button find = new Button("Search");
        find.setPrefWidth(80);
        Button clear = new Button("Clear");
        clear.setPrefWidth(80);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(find, clear);
        buttonBox.setSpacing(50);
        
        GridPane.setConstraints(prompt, 0, 0);
        grid1.getChildren().add(prompt);
        GridPane.setConstraints(idInput, 0, 1);
        grid1.getChildren().add(idInput);
        GridPane.setConstraints(buttonBox, 0, 2);
        grid1.getChildren().add(buttonBox);
        
        find.setOnAction((ActionEvent e) -> {
            if (!ctrl.isEmpty()) {
                int recordId = Integer.parseInt(idInput.getText());
                RecordTable(recordId);
            } else {
                idInput.setText("Empty List!");
            }
            
        });
        
        clear.setOnAction((ActionEvent e) -> {
            idInput.setText("");
        });
        
        Scene stageScene = new Scene(grid1, 260, 180);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
    
    /**
     * Generate A Record Table (not finished yet because game engine has not been implemented yet)
     */
    private void RecordTable(int id) {
        Stage stage = new Stage();
        stage.setTitle("Game Record");
        
        TableView table = new TableView();
        
        final Label label = new Label("Game Record");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn c1 = new TableColumn("Game ID");
        c1.setCellValueFactory(new PropertyValueFactory<ModelPlayer, Integer>("gameID"));
        TableColumn c2 = new TableColumn("Result");
        c2.setCellValueFactory(new PropertyValueFactory<ModelPlayer, String>("result"));
        TableColumn c3 = new TableColumn("Point");
        c3.setCellValueFactory(new PropertyValueFactory<ModelPlayer, String>("point"));
        
        
        table.setItems(record);
        table.getColumns().addAll(c1, c2, c3);
        
        final HBox hbox = new HBox();
        hbox.setSpacing(30);
        
        
        Button refresh = new Button("Refresh");
        refresh.setOnAction((ActionEvent e) -> {
            if (currentUser!= 0){
                record = FXCollections.observableList(ctrl.getGameList(id));
            }
            table.setItems(record);
            table.refresh();
        });
        
        hbox.getChildren().addAll(label, refresh);
        
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(10, 0, 0, 10));
        grid2.setVgap(20);
        GridPane.setConstraints(hbox, 0, 0);
        grid2.getChildren().add(hbox);
        GridPane.setConstraints(table, 0, 1);
        grid2.getChildren().add(table);
        
        Scene stageScene = new Scene(grid2, 280, 500);
        stage.setScene(stageScene);
        stage.showAndWait();
    }
}

