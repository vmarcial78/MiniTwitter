package src;

public class updateuser implements UserGroupV {
    
    long UpdatedTime = 0;
    String UpdatedUser = "No one"; 
 
    @Override
    public void visitUser(UserLeaf user) {
       
        if (user.getLastUpdatedTime() > UpdatedTime){
            UpdatedTime = user.getLastUpdatedTime();
            UpdatedUser = user.getID();
        }
    }
 
    @Override
    public void visitGroup(Container group) {
 
    }
    
    public String getLastUpdateUser() {
        return UpdatedUser;
    }
   
}



