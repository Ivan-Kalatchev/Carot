package application;

import java.math.BigDecimal;

public class Product {
	
	public int ID;
	
	private String name;
	
	private BigDecimal price;
	
	public String barCode;
	
	private BigDecimal suma;
	
	private BigDecimal br;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSuma() {
		return suma.setScale(2);
	}

	public void setSuma(BigDecimal suma) {
		this.suma = suma;
	}

	public BigDecimal getBr() {
		return br.setScale(2);
	}

	public void setBr(BigDecimal br) {
		this.br = br;
	}
	
	
}