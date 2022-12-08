package src;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.scene.control.Dialog;



public class AdminControlPanel{
    //singleton pattern here in admin control panel 
    private static AdminControlPanel control;

    public static AdminControlPanel getInstance(){
        if(control == null){
              control = new AdminControlPanel();
                }
        
            return control;
    }
    private HBox menuHBox;
    
    private AdminControlPanel(){

         //Create  button to add new user
         Button addUserButton = new Button("Add User");
         
        //Create textfield
         TextField UserIDText = new TextField();
         UserIDText.setPromptText("Enter Username ID");

         TextField GroupIDText = new TextField();
         GroupIDText.setPromptText("Enter Group name ID");

        Button addGroupButton = new Button("Add Group ");
         
         Button userViewButton = new Button("Open User View ");
        
        Button usertotalButton = new Button("Total Users ");
       
        Button grouptotalButton = new Button("Total Groups");

        Button TotalTweetButton = new Button("Total Tweets");
        

        Button positiveButton = new Button("Show Positive Percentage");

        Button ValidateUserButton = new Button( "Validate User ");

        Button LastUpdatedUser  =new Button("Last Updated User's ID");

       
        

         
         //Create popup
        // Dialog<String> dialog = new Dialog<String>();
         Alert PopUp = new Alert(Alert.AlertType.INFORMATION);

         final Image rootPic = new Image("C:/Users/vmarc/Downloads/MiniTwiterjf/minitwitter/src",20, 20, false, false);
         Container rootGroup = new Container("Root");
         

         //Add Treeview for usres and group and folder pic 
        TreeItem<Composite> root = new TreeItem<> (rootGroup, new ImageView(rootPic));
        root.setExpanded(true);
        TreeView<Composite> treeView = new TreeView<>(root);



          //action event for add user button 
        addUserButton.setOnAction((ActionEvent event) -> {
            TreeItem<Composite> selectedUser = treeView.getSelectionModel().getSelectedItem();
            String newUserInput= UserIDText.getText();
            //Can't add same user to different groups
            if(rootGroup.containsUser(newUserInput)){
                PopUp.setContentText("This user is already in a group ");
                PopUp.showAndWait();
            }
            else{
                //set value of when User ID was created
                UserLeaf temp = new UserLeaf(newUserInput);
              
                ((Container) selectedUser.getValue()).addGroupUsers(temp);
                selectedUser.getChildren().add(new TreeItem<>(temp));
            }
            UserIDText.clear();
        });

       
        //create action event for add group button 
        addGroupButton.setOnAction((ActionEvent event) -> {
            String newGroup = GroupIDText.getText();
            Container temp = new Container(newGroup);
            TreeItem<Composite> selectedGroup = treeView.getSelectionModel().getSelectedItem();
           
            if(rootGroup.containsGroup(newGroup)){
                PopUp.setContentText("This group already exists ");
                PopUp.showAndWait();
            }
            else{
               
                ((Container) selectedGroup.getValue()).addGroupUsers(temp);
                selectedGroup.getChildren().add(new TreeItem<>(temp, new ImageView(rootPic)));
            }
            GroupIDText.clear();
        });
       
       

        userViewButton.setOnAction((ActionEvent event) -> {
            TreeItem<Composite> selectedUser = treeView.getSelectionModel().getSelectedItem();
            if (selectedUser.getValue() instanceof UserLeaf){
                UserLeaf userViewUser = (UserLeaf) selectedUser.getValue();
                UserViewUI.openUserviewUI(userViewUser, rootGroup);
            }
        });
       
        
       
        usertotalButton.setOnAction((ActionEvent event) -> {
            TotalUser userTotals = new TotalUser();
            rootGroup.accept(userTotals);
            PopUp.setContentText("There are " + userTotals.getUserTotal()+" users total");
            PopUp.showAndWait();
        });
        
        
        //Create action event for group total button display group total with popup 
        grouptotalButton.setOnAction((ActionEvent event) -> {
            TotalGroup groupTotals = new TotalGroup();
            rootGroup.accept(groupTotals);
            PopUp.setContentText("There are " + groupTotals.getGroupTotal() + " groups total");
            PopUp.showAndWait();
            
        });
        
     
        TotalTweetButton.setOnAction((ActionEvent event) -> {
            TotalMessage tweetTotals=new TotalMessage();
            rootGroup.accept(tweetTotals);
            PopUp.setContentText("There are " + tweetTotals.getTwetTotal()+ " tweets total");
            PopUp.showAndWait();
        });

        
        positiveButton.setOnAction((ActionEvent event)-> {
            PositiveUser positiveUser = new PositiveUser();
            rootGroup.accept(positiveUser);
            PopUp.setContentText(String.format("There are %.2f percent of "
            + "positive messages total" , positiveUser.getPositivePercentage()));
            
            PopUp.showAndWait();
        });


        //Validate user actions 
        ValidateUserButton.setOnAction((ActionEvent event) -> {
            String newGroup= GroupIDText.getText();
           
            String newUser= UserIDText.getText();
           
           
            if(rootGroup.containsGroup(newGroup) || rootGroup.containsUser(newUser)){
                PopUp.setContentText("Invalid, ID already exists!");
                PopUp.showAndWait();
            }
            //doesn't allow spaces in IDs, invalid
            else if(newGroup.contains(" ") || newUser.contains(" ")){
                PopUp.setContentText("Invalid, ID can not contain spaces!");
                PopUp.showAndWait();
            }
           
            else if(!rootGroup.containsGroup(newGroup) || !rootGroup.containsUser(newUser)){
                PopUp.setContentText("Valid, ID has not been used!");
                PopUp.showAndWait();
            }
        });
       
        //LAST UPdated user action 

        LastUpdatedUser.setOnAction((ActionEvent event) -> {

            updateuser updateuser =new updateuser();
            rootGroup.accept(updateuser);
            PopUp.setContentText("User's last updated: "  + updateuser.getLastUpdateUser()); 
            PopUp.showAndWait();
        });



        //Put buttons and text buttons in Hbox and v box to display in scene 
    
        HBox userHbox = new HBox(15, UserIDText, addUserButton);
        HBox groupHbox = new HBox(15, GroupIDText, addGroupButton);
        HBox GroupTotalHBox = new HBox(15, usertotalButton, grouptotalButton);
        HBox TweetsHBox = new HBox(15, TotalTweetButton, positiveButton);
        VBox treeViewVBox = new VBox(treeView);
        VBox UserButtonsVbox=new VBox(15, ValidateUserButton,LastUpdatedUser, userViewButton);
        UserButtonsVbox.setAlignment(Pos.CENTER);
        VBox topButtons = new VBox(15, userHbox, groupHbox, UserButtonsVbox, GroupTotalHBox, TweetsHBox);
        VBox bottomButtons=new VBox(15,GroupTotalHBox, TweetsHBox);
        VBox allButtons = new VBox(15, topButtons, bottomButtons);
        allButtons.setSpacing(170);
        menuHBox = new HBox(15, treeViewVBox, allButtons);
        menuHBox.setPadding(new Insets(15));

    }
    //Display stuff 
    public HBox getAdminControlPanel() {
            return menuHBox;
    }
}