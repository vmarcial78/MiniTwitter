package src;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//User is the leaf from the composite pattern
//subject and observer = observer 
//allow to track updates/update user newfeed
public class UserLeaf extends User implements Composite, Observer{
  
    private List<UserLeaf> following = new ArrayList<>();
    private ObservableList<UserLeaf> followingList = FXCollections.observableList(following);
    private String usernameID;
   private List<String> Tweets = new ArrayList<>();
   private List<String> newsFeed = new ArrayList<>(Arrays.asList());
   private ObservableList<String> newsFeedList = FXCollections.observableList(newsFeed);
  
    
   @Override
   public String getID() {
       return usernameID;
   }

   @Override
    public String toString() {
        return usernameID;
    }

   //Set ID of User and created User
    public UserLeaf(String newID) {
         this.usernameID = newID;
      
    }
    
    @Override
    public void accept(UserGroupV visitor) {
         visitor.visitUser(this);
    }
   
    @Override
    public void update(User subject, String tweet) {
        if (subject instanceof UserLeaf) {
            this.newsFeedList.add("-" + ((UserLeaf) subject).getID() + " : " + tweet);
          
        }
    }
    public ObservableList<UserLeaf> getFollowingList() {
            return followingList;
    }
    //if follow user, add to following list
    public void addFollowingList(UserLeaf user) {
        followingList.add(user);
    }

    public List<String> getMyTweets() {
        return Tweets;
    }


    public void tweetMessage (String tweet){
        Tweets.add(tweet);
        //add tweet to own newsfeed
        newsFeedList.add("-" + this.usernameID + " : " + tweet);
        updateFollowers(tweet);
    }


    public ObservableList<String> getNewsFeedList() {
        return newsFeedList;
    }
    
   
}