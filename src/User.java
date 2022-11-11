package src;

import java.util.ArrayList;
import java.util.List;

//Observers are users 
//use observer- when you recieve an update when another object changes 
//update the user every time 
public class User {
    private List <Observer> followers = new ArrayList<>();

    public void attach(Observer observer) {
        followers.add(observer);
    }
    // update the followers each time
    public void updateFollowers(String message) {
        for(Observer observer : this.followers) {
            observer.update(this, message);
        }
    }

}