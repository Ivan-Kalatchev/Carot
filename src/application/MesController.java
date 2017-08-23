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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MesController{
	
	LogInController logcon = new LogInController();
	
	public MesController(){
		this.Titems = FXCollections.observableArrayList();
	}
	
	 //just a comment
	public void initialize(){
		//columnPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("suma"));
		this.sTableV.setItems(this.Titems);
		subTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("sub"));
    	fromTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("fromsTable"));
    	toTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("tosTable"));
    	
    	this.sTableV.setItems(this.Titems);

		sTableV.setRowFactory( tv -> {
		    TableRow<sTable> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            //sTable rowData = row.getItem();
		            
		           

		        }
		    });
		    return row ;
		 });
		}
	
	
	@FXML
	public TextField to;
	
	@FXML
	public TextField toGet;
	
	@FXML
	public TextField fromGet;
	
	@FXML
	public Text fromwhomes;
	
	@FXML
	public TextField sub;
	
	@FXML
	public TextArea mesTextArea;
	
	@FXML
	public TableView<sTable> sTableV;

	@FXML
	public TableColumn<sTable, String> subTable;

	@FXML
	public TableColumn<sTable, String> fromTable;
	
	@FXML
	public TableColumn<sTable, String> toTable;
	
	public ObservableList<sTable> Titems;

	
	public Connection connection;
	public Statement stmt = null;
	public String sql;
	public Stage satge2;
	
	private static final String DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DATABASE_URL = "jdbc:sqlserver://demo.skyware-group.com;databaseName=Mani";
    private static final String USERNAME = "Mani";
    private static final String PASSWORD = "HuiVeiP92006";
    private static final String MAX_POOL = "250";
    
   
    
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }
    
    private Properties properties;
	
	public void send() throws ClassNotFoundException, SQLException{
		
		Class.forName(DATABASE_DRIVER);
		connection = DriverManager.getConnection(DATABASE_URL, getProperties());
		stmt = connection.createStatement(); 
		
		sql = "INSERT INTO mes (textmes, sub, towho, fromwho, checked) VALUES (?, ?, ?, ?, ?)"; 
		System.out.println(logcon.getcashier());
		PreparedStatement preparedStatement_i = connection.prepareStatement(sql);
		//java.sql.Timestamp sqlDate = new java.sql.Timestamp(new Date().getTime());
		preparedStatement_i.setString(1, this.mesTextArea.getText());
		preparedStatement_i.setString(2, this.sub.getText());
		preparedStatement_i.setString(3, this.to.getText());
		preparedStatement_i.setString(4, "");
		preparedStatement_i.setBoolean(5, false);
		preparedStatement_i.execute();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Information");
		alert.setContentText("Message have been send");

		alert.showAndWait();
		this.mesTextArea.setText("");
		this.sub.setText("");
		this.to.setText("");
		
	}
	
	public void exit(){
		((Stage) this.sub.getScene().getWindow()).close();
	}
	
	public void exitm(){
		((Stage) this.fromwhomes.getScene().getWindow()).close();
	}
	Stage stage2 = new Stage();
	public void newm(){
		try{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewMes.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
            stage2.setTitle("Log in");
            stage2.setScene(new Scene(root1));
            stage2.show();

        	 } catch (IOException ex) {
                 System.out.println(ex.getMessage());
                 //close();
             }
		
	}
	
	public void openMesStage(){
		try{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GetMes.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
            stage2.setTitle("Log in");
            stage2.setScene(new Scene(root1));
            stage2.show();

        	 } catch (IOException ex) {
                 System.out.println(ex.getMessage());
                 //close();
             }
		
	}
	
	public void openMes(){
		
		  try {
			Class.forName(DATABASE_DRIVER);
			 connection = DriverManager.getConnection(DATABASE_URL, getProperties());
	          stmt = connection.createStatement(); 
	          sql = "SELECT id, sub, textmes, fromwho, towho, checked FROM mes WHERE fromwho = '"+ this.fromGet.getText() +"' AND towho = '"+ this.toGet.getText() +"'"; 
	          ResultSet rs = stmt.executeQuery(sql);
	            if(rs.next()){
	        	  
	        	  try{
	              	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SMes.fxml"));
	          		Parent root1 = (Parent) fxmlLoader.load();
	                  stage2.setTitle("Log in");
	                  stage2.setScene(new Scene(root1));
	                  
	                  stage2.show();
						//refT();
	              	 } catch (IOException ex) {
	                       System.out.println(ex.getMessage());
	                       //close();
	                   }
	          }
	          while (rs.next()){
	        	  sTable tableItem = new sTable();
	        	  tableItem.setFromsTable(rs.getString("fromwho"));
	        	  tableItem.setSub(rs.getString("sub"));
	        	  tableItem.setTosTable(rs.getString("towho"));
	        	  this.Titems.add(tableItem);
	        	  refT();
	        	 // System.out.println(rs.getString("textmes"));
	        	  
	          }
	          
	       
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
         
	}
	
	public void refT(){
		//columnPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("suma"));
		this.sTableV.setItems(this.Titems);
		subTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("sub"));
    	fromTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("fromsTable"));
    	toTable.setCellValueFactory(new PropertyValueFactory<sTable, String>("tosTable"));
    	
    	this.sTableV.setItems(this.Titems);

		sTableV.setRowFactory( tv -> {
		    TableRow<sTable> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            //sTable rowData = row.getItem();
		            
		           

		        }
		    });
		    return row ;
		 });
		}
	
}