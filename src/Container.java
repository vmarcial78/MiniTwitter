package src;

import java.util.ArrayList;
import java.util.List;

//Groups -cdomposite patterm 

public class Container implements Composite{
    
    private List<Composite> groups = new ArrayList<>();
    private String groupID;

   
    public Container(String newID) {
       this.groupID = newID;
    
    }


    @Override
    public void accept(UserGroupV visitor) {
        visitor.visitGroup(this);
        for(Composite members : groups) {
            if (members instanceof UserLeaf) {
                members.accept(visitor);
            } else if (members instanceof Container) {
                members.accept(visitor);
            }
        }
    }


    
    @Override
    public String getID() {
        return this.groupID;
    }
    public List<Composite> getGroupUsers(){
        return groups;
    }

    @Override
    public String toString() {
        return groupID;
    }
   
   
    
    public void addGroupUsers(Composite newGroup){
        this.groups.add(newGroup);
    }
   
    public Boolean containsUser(String UserID){
        for (Composite members : groups) {
            if (members instanceof UserLeaf) {
                if (members.getID().equals(UserID)) {
                    return true;
                }
            }
            else if (members instanceof Container) {
                if (((Container) members).containsUser(UserID)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //return user 
    public UserLeaf getUser(String userID){
        for (Composite members : groups) {
            if (members instanceof UserLeaf) {
                if (members.getID().equals(userID)){
                    return (UserLeaf) members;
                }
            }
            else if (members instanceof Container) {
                // iterate through all groups in group and check if the 
                //user id exist there
                if (((Container) members).containsUser(userID)) {
                    return ((Container) members).getUser(userID);
                }
            }
        }
        return null;
    } 

//Check if group already exist
public Boolean containsGroup(String memberID){
    for (Composite members : groups) {
        if (members instanceof UserLeaf) {
            continue;
        }
        
        else if (members instanceof Container) {
            if (members.getID().equals(memberID)){
                return true;
            }
            //check ID of groups within groups
            else {
                if(((Container) members).containsGroup(memberID)){
                    return true;
                }
            }
        }
    }
    return false;
}





}