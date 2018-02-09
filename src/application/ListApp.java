package application;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class ListApp extends Application {
	@Override
	public void start(Stage primaryStage) 
	throws Exception {
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getClassLoader().getResource("/view/List.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Song Library");
		primaryStage.setResizable(false);
		primaryStage.show(); 
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop(){
        LibModel model = LibModel.getTheModel();
        //
        model.storeToFile();
	}
}