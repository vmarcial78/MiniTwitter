package src;

//Visitor pattern 
public class TotalMessage implements UserGroupV{
    
    private int TotalTweet = 0;
    
   
    @Override
    public void visitGroup(Container group) {
                         }

    public int getTwetTotal() {
        return TotalTweet;
                }

     @Override
     public void visitUser(UserLeaf user) {
   
     //get amount of user tweets and add total message = message total
    setMessageTotal(getTwetTotal() + user.getMyTweets().size());
                }


    public void setMessageTotal(int totalMessage) {
        TotalTweet = totalMessage;
                 }


}