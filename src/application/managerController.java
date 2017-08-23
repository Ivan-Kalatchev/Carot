package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.awt.Button;
import java.awt.Window;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class managerController{
	
	public void initialize(){
		load_table();
		//openPadd1();
	}


	@FXML
	public TextField priceP;


	@FXML
	public TextField nameP;
	
	@FXML
	public TextField barcodeP;

	@FXML
	public TableView<Product> tableEdit;
	
	@FXML
	public TableColumn<Product, String> nameEdit;
	
	@FXML
	public TableColumn<Product, BigDecimal> priceEdit;
	
	@FXML
	public TableColumn<Product, String> barcodeEdit;
	
	@FXML
	public TextField barcodeSe;


	@FXML
	public TextField nameEditable;
	
	@FXML
	public TextField priceEditable;
	
	public ObservableList<Product> items;


	public Connection connection;
	public Statement stmt = null;
	public String sql;
	public String sql1;
	public BigDecimal priceNow = new BigDecimal(0).setScale(2);
	public BigDecimal sum = new BigDecimal(0).setScale(2);
	public BigDecimal priceUp = new BigDecimal(0).setScale(2);
	public String nameUp;
	public int idRow;



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

	
	Stage stage = new Stage();
	
	public managerController(){
		this.items = FXCollections.observableArrayList();
		//load_table();
	}
	
	public void openPadd1(){
		try{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSta.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
            stage.setTitle("Add");
            stage.setScene(new Scene(root1));
            stage.show();
            System.out.println("here was started an opening");

        	 } catch (IOException ex) {
        		 System.out.println("hereeeeeeeeeeeeeeeeeeeeeeeeee");
                 //close();
             }             

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
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void exitP(){
		
	}
	
	public void load_products(){
		try{
		  Class.forName(DATABASE_DRIVER);
          connection = DriverManager.getConnection(DATABASE_URL, getProperties());
          stmt = connection.createStatement();
          sql = "SELECT id, name, price, barcode FROM products";
          ResultSet rs = stmt.executeQuery(sql);
          while(rs.next()){
        	  
              // Retrieve by column name
      		//System.out.printf("Valid");
                 
      		try {
      			Product curProd = new Product();
      			//sum = rs.getBigDecimal("price".replaceAll(",", ""));
      			curProd.ID = rs.getInt("id");
      			curProd.barCode = rs.getString("barcode");
      			curProd.setName(rs.getString("name"));
      			curProd.setPrice(rs.getBigDecimal("price"));
      		    this.items.add(curProd);
      		    
        //  			curProd.setPrice(rs.getBigDecimal("price"));
      		}catch(Exception e) {
      		 e.printStackTrace();
      			Alert alert = new Alert(AlertType.ERROR);
      			alert.setTitle("Error");
      			alert.setHeaderText("Error");
      			alert.setContentText("Ooops");
      			alert.showAndWait();
      		 
      		}
          }
		} catch(Exception  ex){
			
		}
	}
	
	public void load_table(){
		//this.tableEdit.setItems(this.items);
		load_products(); 
		nameEdit.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		priceEdit.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
		//barcodeEdit.setCellValueFactory(new PropertyValueFactory<Product, String>("barcode"));
    	   	
		this.tableEdit.setItems(this.items);

		tableEdit.setRowFactory( tv -> {
		    TableRow<Product> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Product rowData = row.getItem();
		        	this.nameEditable.setText(rowData.getName());
		        	this.priceEditable.setText(rowData.getPrice().toString());
		        	idRow = rowData.ID;
		        }
		    });
		    return row ;
		 });
	}

	public void update(){
		try{
			  Class.forName(DATABASE_DRIVER);
	          connection = DriverManager.getConnection(DATABASE_URL, getProperties());
	          stmt = connection.createStatement();
	          BigDecimal X1 = new BigDecimal(priceEditable.getText().replaceAll(",", "."));
	          String name1 = this.nameEditable.getText();
	          sql = "UPDATE products SET name='"+name1+"', price='"+X1+"' WHERE id='"+idRow+"'";
	          stmt.executeQuery(sql);
	          Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Information Dialog");
	    		alert.setHeaderText("Information");
	    		alert.setContentText("Продуктат беше успешно добавен!");
	          this.items.clear();
	          load_table();
		} catch(Exception e){
			
		}
	}
	
	
}