package application;

import com.sun.prism.impl.ps.CachingShapeRep;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	Boolean toExit;
	
	@Override
	public void start(Stage primaryStage) {		
		System.out.println("start");
		toExit = false;
		Integer cashierId = null;
	
		while (!toExit){
			Integer casherID = doLogin();
			if (casherID != null ) {
				if (casherID == -1) {
					toExit = true;
				} else {
					//TODO: Open main scene
					try {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			    		Parent root1 = (Parent) fxmlLoader.load();
			    		primaryStage.setTitle("Carrot++");
			            primaryStage.setScene(new Scene(root1));
						//primaryStage.show();
						Image im = null;
						//try {
						im = new Image("/resources/store.png", false);
						///} catch (Exception e) {
						///.out.printf("haha");
						//}
						primaryStage.getIcons().add(im);
						primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private Integer doLogin() {
		System.out.println("Entering doLogin");
		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UTILITY);
		try {
			toExit = true;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		primaryStage.setTitle("Log in");
            primaryStage.setScene(new Scene(root1));
            LogInController contr = fxmlLoader.getController();
            //Image im = null;
			//try {
			//im = new Image("/resources/Log_in.png", false);
			///} catch (Exception e) {
			///.out.printf("haha");
			//}
			//primaryStage.getIcons().add(im);
            primaryStage.showAndWait();
			System.out.println("Leaving doLogin, Cashier Id = " + contr.cashierId.toString());
			return contr.cashierId;
			//contr.cashierId
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Leaving doLogin, no Cashier");
			return null;
		}
		
	}
	
	private void doMainWindow() {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
