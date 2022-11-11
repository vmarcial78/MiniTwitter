package src;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class UserViewUI {

    public static void openUserviewUI(UserLeaf user, Container rootGroup){
        
        
        UserViewUI userViewUI = new UserViewUI(user, rootGroup);
        VBox menuVBox = userViewUI.getMenuBox();
        Scene userScene = new Scene(menuVBox, 590, 450);

        
        Stage UIStage = new Stage();
        UIStage.setTitle(user.getID() + "'s Account");
        UIStage.setScene(userScene);
        UIStage.show();
    }


    
    private VBox menuVBox;
    
    private UserViewUI(UserLeaf user, Container rootGroup){
       

        TextField usernameText = new TextField();
        usernameText.setPromptText("Enter Username ID");
        
        Button followBUtton = new Button("Follow User");
       
        
        Alert popUp = new Alert(Alert.AlertType.INFORMATION);
        
        
        followBUtton.setOnAction((ActionEvent event)-> {
            String UsermustFollow = usernameText.getText();
            UserLeaf MustFollow = rootGroup.getUser(UsermustFollow);
            if(MustFollow == user){
                popUp.setContentText("Unable to follow yourself.");
                popUp.showAndWait();
               
            }
            
            else if(MustFollow == null){
                popUp.setContentText("Unable to find user");
                popUp.showAndWait();
            }
            
            //if user exist and not following add to list and follow 
            else{ 
                MustFollow.attach(user);
                user.addFollowingList(MustFollow);
            }
            usernameText.clear();
        });

        //Display following list 
        ListView followlist = new ListView(user.getFollowingList());
        followlist.setPrefHeight(55);
        
       
          TextArea TweetTextField =new TextArea();
          TweetTextField.setPromptText("What's happening?");
          TweetTextField.setPrefHeight(60);
          TweetTextField.setWrapText(true);
        
         
        Button postTweetButton = new Button("Post Tweet");
       
        
        postTweetButton.setOnAction((ActionEvent event)-> {
            String tweet = TweetTextField.getText();
            user.tweetMessage(tweet);
            TweetTextField.clear();
        });

        //ListView for newsFeed
        ListView newsFeedList = new ListView(user.getNewsFeedList());
        newsFeedList.setPrefHeight(100);
                
        Label newsFeed = new Label("News Feed");
        Label following = new Label(" You are Following");
        
       
        
        //Place buttons, Listview, label, textfield/area into proper layout
        HBox followUserOption = new HBox(10,usernameText, followBUtton);
        HBox tweetOption = new HBox(10, TweetTextField, postTweetButton);
        tweetOption.setAlignment(Pos.BOTTOM_CENTER);
        
        
        menuVBox = new VBox(10,  followUserOption, following, 
                followlist, tweetOption, newsFeed, newsFeedList);
        
    }
    public VBox getMenuBox(){
        return menuVBox;
    }
   
}