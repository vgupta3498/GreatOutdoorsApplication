package com.cg.go.entity;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class CartItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ")
    @SequenceGenerator(sequenceName = "cart_seq", allocationSize = 1, name = "CART_SEQ")
	private long cartId;
	@OneToOne
	private Userdata userId;
	//annotation to map a collection of a basic type(String, Integer etc) and embeddable types(User Defined class)
	@ElementCollection
	private Map<ProductEntity, Integer> products =new HashMap<ProductEntity, Integer>(); // product ,quantity	
	private double cartTotalPrice;	
	private long totalQuantity;
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public Map<ProductEntity, Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<ProductEntity, Integer> products) {
		this.products = products;
	}
	public double getCartTotalPrice() {
		return cartTotalPrice;
	}
	public void setCartTotalPrice(double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}
	public long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	@Override
	public String toString() {
		return "CartItemEntity [cartId=" + cartId + ", userId=" + userId + ", products=" + products
				+ ", cartTotalPrice=" + cartTotalPrice + ", totalQuantity=" + totalQuantity + "]";
	}
	public CartItemEntity(long cartId, Userdata userId, Map<ProductEntity, Integer> products, double cartTotalPrice,
			long totalQuantity) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.products = products;
		this.cartTotalPrice = cartTotalPrice;
		this.totalQuantity = totalQuantity;
	}
	public Userdata getUserId() {
		return userId;
	}
	public void setUserId(Userdata userId) {
		this.userId = userId;
	}
	public CartItemEntity() {
		super();
	}
	
	
	
	
}
