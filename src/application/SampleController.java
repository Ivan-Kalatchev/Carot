package application;

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

public class SampleController {
	
	Main main = new Main();
	managerController mc = new managerController();

	public SampleController() {
		this.items = FXCollections.observableArrayList();

	}

	public void initialize(){
		columnPrice.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("suma"));
		columnbr.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("br"));
    	columnsum.setCellValueFactory(new PropertyValueFactory<Product, BigDecimal>("price"));
    	columnName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
    	
		this.tableProducts.setItems(this.items);

		tableProducts.setRowFactory( tv -> {
		    TableRow<Product> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Product rowData = row.getItem();
		            this.items.remove(rowData);
		           // this.prS.setText("");
		            BigDecimal price1 = this.items.stream().map(i -> i.getSuma()).reduce(BigDecimal.ZERO, BigDecimal::add);
		            this.price.setText(price1.toString());
		            //this.clientDisplayFood.setText("");

		        }
		    });
		    return row ;
		 });

	}


	@FXML
	public TextField text;

	@FXML
	public TextField text1;

	@FXML
	public Text price;

	@FXML
	public Text foods;

	@FXML
	public Text clientDisplayFood;


	@FXML
	public TableView<Product> tableProducts;

	@FXML
	public TableColumn<Product, String> columnName;

	@FXML
	public TableColumn<Product, BigDecimal> columnPrice;
	
	@FXML
	public TableColumn<Product, BigDecimal> columnsum;

	@FXML
	public TableColumn<Product, BigDecimal> columnbr;

	@FXML
	public Text prS;

	@FXML
	public Button ok;

	public ObservableList<Product> items;

	Stage stage = new Stage();
	Stage stagec = new Stage();

	public Connection connection;
	public Statement stmt = null;
	public String sql;
	public String sql1;
	public BigDecimal priceNow = new BigDecimal(0).setScale(2);
	public BigDecimal sum = new BigDecimal(0).setScale(2);




	private static final String DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://horton.elephantsql.com:5432/zjvzuxmo";
    private static final String USERNAME = "";
    private static final String PASSWORD = "QGv_uDa0V1ubqXD_08_BPgNjcLZx13E2";
    private static final String MAX_POOL = "10";

    // init connection object
    //private Connection connection;
    // init properties object

    public void on_key(KeyEvent e) {
    	System.out.printf("Fire");
    	if(e.getCode() == KeyCode.ENTER)
        {
            connect();
        }
    }

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

    // connect database
    public void zeronum(){
    	this.text.setText(this.text.getText() + "0");
    }
    
    public void onenum(){
    	this.text.setText(this.text.getText() + "1");
    }
    
    public void twonum(){
    	this.text.setText(this.text.getText() + "2");
    }
    
    public void trinum(){
    	this.text.setText(this.text.getText() + "3");
    }
    
    public void fournum(){
    	this.text.setText(this.text.getText() + "4");
    }
    
    public void fivenum(){
    	this.text.setText(this.text.getText() + "5");
    }
    
    public void sixnum(){
    	this.text.setText(this.text.getText() + "6");
    }
    
    public void sevennum(){
    	this.text.setText(this.text.getText() + "7");
    }
    
    public void eightnum(){
    	this.text.setText(this.text.getText() + "8");
    }
    
    public void ninenum(){
    	this.text.setText(this.text.getText() + "9");
    }
    
    public void dotnum(){
    	this.text.setText(this.text.getText() + ".");
    }
    
    public void starnum(){
    	this.text.setText(this.text.getText() + "*");
    }
    
    public void connect() {
    	 try {
	            Class.forName(DATABASE_DRIVER);
	            connection = DriverManager.getConnection(DATABASE_URL, getProperties());
	            stmt = connection.createStatement(); 
	            Pattern pattern = Pattern.compile("(?<DrChast>(?<Vsichko>(?<Cialo>\\d*)?(?<Drobno>\\.\\d+)?)(\\*)+)?(?<Bar>\\d{10})+"); 
	            Matcher matcher = pattern.matcher(this.text.getText());
	            if (matcher.matches()) {
	            	
	            
	            	String Barcodem = matcher.group("Bar");
	            	String X = matcher.group("Vsichko");
	            	BigDecimal X1 = new BigDecimal(X.replaceAll(",", "."));
	            
	            	sql = "SELECT id, name, price, barcode FROM products WHERE barcode = '" + Barcodem + "'"; 
		            String po = matcher.group("Vsichko");
		            BigDecimal po1 = new BigDecimal(po.replaceAll(",", "."));
		                  // System.out.println(money.multiply(po1));
		            ResultSet rs = stmt.executeQuery(sql);
	            
		            try {
	            		rs.next();
	            		
	            		Product curProd = new Product();
		                // Retrieve by column name
	            		System.out.printf("Valid");
		                   
	            		try {
	            			sum = rs.getBigDecimal("price".replaceAll(",", ""));
	            			curProd.ID = rs.getInt("id");
	            			curProd.barCode = rs.getString("BarCode");
	            			curProd.setName(rs.getString("name"));
	            			curProd.setSuma(sum.multiply(po1));
	            			curProd.setBr(X1);
	            			curProd.setPrice(rs.getBigDecimal("price"));
		          //  			curProd.setPrice(rs.getBigDecimal("price"));
	            		}catch(Exception e) {
	            		 
	            			Alert alert = new Alert(AlertType.ERROR);
	            			alert.setTitle("Error");
	            			alert.setHeaderText("Error");
	            			alert.setContentText("Ooops");
	            			alert.showAndWait();
	            		 
	            		}
		            	//Scanner matcherS = new Scanner(System.in);
		                //Pattern pattern1 = Pattern.compile("\\p{Digit}\\*\\d\\d\\d\\d\\d\\d\\d\\d\\d");
		                
		               // System.out.printf("Enter the command:");
		                //String input = matcherS.next();
		               

		                  

		                
		                
		            	this.text.setText("");
		                System.out.print("ID: " + curProd.ID);
		                System.out.println(", Name: " + curProd.getName());
		                System.out.println(", Price: " + curProd.getPrice());
		                System.out.println(", BarCode: " + curProd.barCode);
		               // Pattern pattern = pattern.compile(this.text.getText());



		                System.out.printf("\n \n");
		                System.out.println("-------------------------------------------");
		                System.out.println("----------------Prize of product with id :::::     " + curProd.ID + "    is::::      " + curProd.getPrice() + "        --------------------------- \n");
		                System.out.println("-------------------------------------------");
		               		//String textNow = this.foods.getText();
		               		//String prSNow = prS.getText();
		               				//this.prS.setText("" + curProd.getPrice());
		               				//this.clientDisplayFood.setText(curProd.getName());
		               				//this.priceNow = priceNow.add(curProd.getPrice());
		               		//this.foods.setText(textNow + "\n Име на продукта: " + curProd.name + " || Цена: " + curProd.price);
		               			//this.price.setText(priceNow + "лв.");
		               		//this.items.add(curProd);

		               	System.out.printf("We are adding!");


		               	this.items.add(curProd);
		               	BigDecimal price =
		               			this.items.stream().map(i -> i.getSuma()).reduce(BigDecimal.ZERO, BigDecimal::add);
		               	System.out.printf("Total: " + price.toString() + "\n");
		               	this.price.setText(price.toString());


		             // <== .... ==> //

		            // <== .... ==> //
		               	rs.close();
		               	stmt.close();
		               	connection.close();
	            		
		            } catch(Exception ex) {
		            	ex.printStackTrace();
		            	Alert alert = new Alert(AlertType.ERROR);
		            	alert.setTitle("Error");
		            	alert.setHeaderText("Error");
		        		alert.setContentText("Ooops, there is no such item in the database!");
		        		alert.showAndWait();
	            	}
	            }
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
    
    public void openManager(){

    	try{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Maniger.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
            stage.setTitle("Manager");
            stage.setScene(new Scene(root1));
            stage.show();
            mc.load_table();
        	 } catch (IOException ex) {
                 System.out.println("here");
                 //close();
             }             
    }

    public void pay(){
    	if(!(this.items.toArray().length == 0)){
    	//this.foods.setText("");
    	//this.price.setText("");
    	//this.prS.setText("");
   	
    	try{
    		
    		int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    		Class.forName(DATABASE_DRIVER);
    		connection = DriverManager.getConnection(DATABASE_URL, getProperties());
    		stmt = connection.createStatement(); 
         //sql = "INSERT INTO table_name (column1) VALUES (value1, value2, value3, ...)"; 
        // ResultSet rs = stmt.executeQuery(sql);
         
    		sql = "INSERT INTO bills (bill_date, cashier_id) VALUES (?, ?)"; 
         //String sql1 = "SELECT cashier_id FROM bills"; 
         
    		PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		java.sql.Timestamp sqlDate = new java.sql.Timestamp(new Date().getTime());
    		preparedStatement.setTimestamp(1, sqlDate);
    		preparedStatement.setInt(2, randomNum);
         
         //preparedStatement.execute();
         //int id = preparedStatement.executeUpdate();
    		int affectedRows = preparedStatement.executeUpdate();

    		if (affectedRows == 0) {
    			throw new SQLException("Creating user failed, no rows affected.");
    		}

    		try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
    			if (generatedKeys.next()) {
    				System.out.println(generatedKeys.getInt(1));
    				
    				for(int i = 0; i <this.items.toArray().length; i++){
        				System.out.println(this.items.get(i).barCode);
        				sql = "INSERT INTO bill_items (bill_id, quantity, product_id, unit_price) VALUES (?, ?, ?, ?)"; 

        	    		PreparedStatement preparedStatement_i = connection.prepareStatement(sql);
        	    		//java.sql.Timestamp sqlDate = new java.sql.Timestamp(new Date().getTime());
        	    		preparedStatement_i.setBigDecimal(2, this.items.get(i).getBr());
        	    		preparedStatement_i.setInt(1, generatedKeys.getInt(1));
        	    		preparedStatement_i.setInt(3, this.items.get(i).ID);
        	    		preparedStatement_i.setBigDecimal(4, this.items.get(i).getPrice());
        	    		preparedStatement_i.execute();
        	    		
        			}
        			
        			
        			
        			
        			
    			}
            else {
                 throw new SQLException("Creating user failed, no ID obtained.");
            }
    			
             
    			
             //System.out.println(this.items.toArray().hashCode());
         }
         //rs.next();
         //int id = rs.getInt(0);
         //System.out.println(id);
         //preparedStatement.setInt(1, cashier_id);
         //rs.next();
    	} catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	
    	
    	this.items.clear();
    	iz();
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("Please eneter products");
			alert.showAndWait();
    	}
    }

    public void iz(){
     	BigDecimal price1 =
       			this.items.stream().map(i -> i.getSuma()).reduce(BigDecimal.ZERO, BigDecimal::add);
    	this.price.setText(price1.toString());
    }
    
    public void close(){
    	this.stage.close();
    }
    
    public void newe(){
    	this.items.clear();
    	 BigDecimal price1 = this.items.stream().map(i -> i.getSuma()).reduce(BigDecimal.ZERO, BigDecimal::add);
         this.price.setText(price1.toString());
    }
    

    public void log_out(){
    	((Stage) this.text.getScene().getWindow()).close();
    	main.start(stage);
    }


    public void open(){


    	try{
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Mes.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
            stage.setTitle("Log in");
            stage.setScene(new Scene(root1));
            stage.show();

        	 } catch (IOException ex) {
                 System.out.println("here");
                 //close();
             }             

    }

   public void openCD() throws IOException{
    	 //System.out.printf("No internet connection!");
   //     	FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("ClientDisplay.fxml"));
   // 		Parent root = (Parent) fxmlLoad.load();
  //          stagec.setTitle("Log in");
   //         stagec.setScene(new Scene(root));
   //         stagec.show();
    }

}
