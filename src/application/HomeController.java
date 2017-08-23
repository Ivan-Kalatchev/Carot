package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HomeController{
Stage stage1 = new Stage();
	public void open() throws IOException{

		FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("LogIn.fxml"));
		Parent root = (Parent) fxmlLoader1.load();
        stage1.setTitle("LogIn");
        stage1.setScene(new Scene(root));
        stage1.show();
	}

	public void exit(){

		 Platform.exit();
	}

}