package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SaveController{
	
	@FXML
	Button buttonClose;
	
	public void close(){
		((Stage) buttonClose.getScene().getWindow()).close();
		
	}
	
	
	
	
}