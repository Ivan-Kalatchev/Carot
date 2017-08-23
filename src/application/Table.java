package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
	
	private SimpleStringProperty nameOfP;
	private SimpleIntegerProperty pr;
	 
	    private Table(String Name, Integer priceOfP) {
	        this.nameOfP = new SimpleStringProperty(Name);
	        this.pr = new SimpleIntegerProperty(priceOfP);
	    }
	 
	    public String getName() {
	        return nameOfP.get();
	    }
	    public void setName(String Name) {
	        nameOfP.set(Name);
	    }
	        
	    public int getPriceOfP() {
	        return pr.get();
	    }
	    public int setPriceOfP(Integer priceOfP) {
	    	pr.set(priceOfP);
			return priceOfP;
	    }
}