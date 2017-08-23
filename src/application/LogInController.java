package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LogInController{
	@FXML
	public TextField userName;

	@FXML
	public PasswordField password;

	@FXML
	Button buttonClose;


	Stage stage = new Stage();
	
	public Integer cashierId;

	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://horton.elephantsql.com:5432/zjvzuxmo";
    private static final String USERNAME = "zjvzuxmo";
    private static final String PASSWORD = "QGv_uDa0V1ubqXD_08_BPgNjcLZx13E2";
    private static final String MAX_POOL = "10";
//250 MAXPOOLS FOR MAX//
    public Connection connection;
	public Statement stmt = null;
	public String sql;
	public String sql1;

	private Properties properties;

	    // create properties
	private Properties getProperties() {
		if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
		return properties;
    }
	
	public void check() throws IOException{
		try {
			Class.forName(DATABASE_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, getProperties());
			stmt = connection.createStatement();
			sql = "SELECT id, code FROM cashiers WHERE code = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, this.userName.getText());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				cashierId = rs.getInt("id");
//				try{
//
//					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
//					Parent root1 = (Parent) fxmlLoader.load();
//					stage.setTitle("Carrot+");
//					stage.setScene(new Scene(root1));
//					stage.show();
//					((Stage) buttonClose.getScene().getWindow()).close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//					Alert alert = new Alert(AlertType.ERROR);
//					alert.setTitle("Error");
//					alert.setHeaderText("Error");
//					alert.setContentText("Ooops, cannot open 'carrot+'!Please call 0884354475!");
//					alert.showAndWait();
//				}
				//preparedStatement.close();
				stmt.close();
				connection.close();
				((Stage) buttonClose.getScene().getWindow()).close();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Error");
					alert.setContentText("Ooops, there is no such profile in the database!");
					alert.showAndWait();
				};
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.printf("No internet connection!");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("Ooops, there is no internet connection!");
			alert.showAndWait();
	    }
    }
	
	public Integer getcashier(){
		return cashierId;
	}
	
	public void exit(){
		this.cashierId = -1;
		((Stage) buttonClose.getScene().getWindow()).close();
	}
	
	
}