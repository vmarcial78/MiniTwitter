package src;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



//visit message to see if it contains positive words 
//Visitor patterm 
public class PositiveUser implements UserGroupV{
    
    private double positiveCount = 0;
     private double totalTweets = 0;
    private double positiveMath = 0;

    private List<String> positiveWords= new ArrayList<>
        (Arrays.asList("amazing","good","W", "lit", "awesome","beautiful", "great", "excellent","busting", "nice",
        "happy","faboulous","best"));


                @Override
                public void visitGroup(Container group) {
                    
                }
                       

                public double getPositivePercentage() {
                    if (totalTweets ==0) {
                        return positiveMath;
                    }
                    //calculate 
                    setPositivePercentage((positiveCount/totalTweets)*100.0);
                     return (positiveMath);
                }
            
                public void setPositivePercentage(double positivePercentage) {
                        this.positiveMath = positivePercentage;
                }



    @Override
    public void visitUser(UserLeaf user) {
        for (String message : user.getMyTweets()){
                totalTweets +=1;
            //iterative through each word to locate a positive word
            for (String positive : positiveWords){
                if (message.toLowerCase().contains(positive.toLowerCase())){
                    positiveCount +=1;
                    break;
                }
            }
        }
    }

  
   
}