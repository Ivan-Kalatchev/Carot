package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddController{
	Stage stage = new Stage();
	
	managerController mc = new managerController();
	
	@FXML
	public TextField priceP;


	@FXML
	public TextField nameP;
	
	@FXML
	public TextField barcodeP;
	

	public Connection connection;
	public Statement stmt = null;
	public String sql;
	public String sql1;
	public BigDecimal priceNow = new BigDecimal(0).setScale(2);
	public BigDecimal sum = new BigDecimal(0).setScale(2);




	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://horton.elephantsql.com:5432/zjvzuxmo";
    private static final String USERNAME = "zjvzuxmo";
    private static final String PASSWORD = "QGv_uDa0V1ubqXD_08_BPgNjcLZx13E2";
    private static final String MAX_POOL = "10";
    
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

	

	public void addP(){
		try{
	            Class.forName(DATABASE_DRIVER);
	            connection = DriverManager.getConnection(DATABASE_URL, getProperties());
	            stmt = connection.createStatement();
	            sql = "INSERT INTO products (name, price, barcode) VALUES (?, ?, ?)";
	            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            BigDecimal X1 = new BigDecimal(priceP.getText().replaceAll(",", "."));
	            preparedStatement.setString(1, nameP.getText());
	            preparedStatement.setBigDecimal(2, X1);
	            preparedStatement.setString(3, barcodeP.getText());
	            preparedStatement.execute();
	            Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Information Dialog");
	    		alert.setHeaderText("Information");
	    		alert.setContentText("Продуктат беше успешно добавен!");
	    		
	    		alert.showAndWait();
	    		this.barcodeP.setText("");
	    		this.priceP.setText("");
	    		this.nameP.setText("");
	    		//mc.items.clear();
	    		//mc.load_table();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
}