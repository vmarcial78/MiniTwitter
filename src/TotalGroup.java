package src;


//Visitor pattern 
public class TotalGroup implements UserGroupV{
  

    public void visitUser(UserLeaf user){
       
    }

    public void setTotalGroup(int totalUser) {
        this.groupTotal = totalUser;
    }

    
    private int groupTotal = 0;
    
    public int getGroupTotal() {
        return groupTotal;
    }


    @Override
    //Add 1 to counter everytime a group is visit
    public void visitGroup(Container group) {
        setTotalGroup(getGroupTotal() + 1);
    }

    
}