package src;

import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.application.Application;



public class Driver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //set stage and scene in order to be able to view panel in java fx 
    //must set stage first then scene inside stage 
    @Override
    public void start(Stage mainStage) {
        //Admin contol will be the main thing that should be run 
     
        AdminControlPanel adminControlPanel = AdminControlPanel.getInstance();
        HBox adminControlHbox = adminControlPanel.getAdminControlPanel();
        
        Scene scene = new Scene(adminControlHbox, 580, 500);
        //put the scene in the stage to show 
        mainStage.setScene(scene);

        mainStage.setTitle("Mini Twitter");
        
        mainStage.show();
    }        

    
    
}