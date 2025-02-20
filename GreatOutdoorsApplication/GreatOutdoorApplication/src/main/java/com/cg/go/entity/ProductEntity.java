package com.cg.go.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ProductEntity {

	@Id
	@NotEmpty(message="Id should not be null")
	private String productId;//7000
	@NotEmpty(message="Enter product name")
	private String productName;
	@NotNull(message="Enter price")
	@Min(value=1,message="Add quantity more than 0")
	private Double price;
	@NotEmpty(message="Upload image")
	private String image;
	@NotEmpty(message="Enter colour")
	private String color;
	@NotEmpty(message="Enter category")
	private String category;
	@NotNull(message="Please enter Quantity")
	@Min(value=1,message="Add quantity more than 0")
	private Integer quantity;
	@NotEmpty(message="Please enter manufacturer")
	private String manufacturer;
	@NotEmpty(message="Please enter specification")
	private String specification;	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public ProductEntity(String productId, String productName, Double price, String image, String color,
			String category, Integer quantity, String manufacturer, String specification) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.image = image;
		this.color = color;
		this.category = category;
		this.quantity = quantity;
		this.manufacturer = manufacturer;
		this.specification = specification;
	}
	public ProductEntity() {
		super();
	}
	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", image=" + image + ", color=" + color + ", category=" + category + ", quantity=" + quantity
				+ ", manufacturer=" + manufacturer + ", specification=" + specification + "]";
	}
	
}
