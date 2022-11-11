package src;


//Visitor pattern 
public class TotalUser implements UserGroupV{
    
    private int userTotal =0;

      //return usertotal
      public int getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(int totalUser) {
        this.userTotal = totalUser;
    }
    
    @Override
    //everytime user is visited add one to total
    public void visitUser(UserLeaf user) {
        setUserTotal(getUserTotal() + 1);
    }

    @Override
    public void visitGroup(Container group) {
       
    }
  
   
}