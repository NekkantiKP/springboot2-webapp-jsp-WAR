package net.guides.springboot2.springboot2webappjsp.controllers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "PRODUCT_TBL")
public class Product {

    @Id
    private int id;
    
    @NotEmpty(message = "Product name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message="Alpha Numeric only")
    private String name;
   
    @NotNull(message = "Quantity name cannot be blank")
    private int quantity;
    
    private double price;
	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
    
}
