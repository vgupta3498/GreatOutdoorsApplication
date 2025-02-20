package com.cg.go.service;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.ICartRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.entity.CartItemEntity;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.CartException;
import com.cg.go.pojo.Cart;
//indication of holding the business logic
@Service
public class CartServiceImpl implements ICartService{
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	ICartRepository cartRepository;
	@Autowired
	IProductRepository productRepository;
	@Autowired
	IUserRepository userRepository;
	public static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

	//Overriding methods from cart service(parent class)
	@Override 
	@Transactional
	public CartItemEntity findCartlist(int userId) throws CartException {
		LOGGER.info("CartItemEntity findCartlist() started");
		//Finding user by userId
		Userdata user=userRepository.findById(userId).orElse(null);
		if(user!=null) {
			 //Finding cart by user
			CartItemEntity cart= cartRepository.findByUserId(user);
			LOGGER.info("CartItemEntity findCartlist() ended");
			if(cart!=null) {
				return cart;
			}
			else {
				throw new CartException("User does not have any cart");
			}
		}
		else {
			throw new CartException("User is not present");
		}
	}

	
	@Override
	@Transactional
	public List<CartItemEntity> addCartItem(Cart cart) throws CartException {
		LOGGER.info("CartItemEntity addCartItem() started");
		//Finding user by userId which is passed through pojo object
		Userdata user=userRepository.findById(cart.getUserId()).orElse(null);
		if(user!=null)
		{
		//Finding cartItem for user
		CartItemEntity entity=cartRepository.findByUserId(user);
		//Finding product by productId which is passed through pojo object
		ProductEntity product=productRepository.findById(cart.getProductId()).orElse(null);
		if(product!=null) {
		if(entity==null) {
			  //Creating a cartItem object
			entity=new CartItemEntity();
			entity.setUserId(user);
			//Adding product to map
		entity.getProducts().put(product,cart.getProductQuantity());
		double price=0;
		long quantity=0;
		//Iterating map
		for(ProductEntity p:entity.getProducts().keySet()) {
			 //Calculating quantity of products in cart
			quantity=quantity+entity.getProducts().get(p);
			//Calculating price of products in cart
			price=price+(p.getPrice()*entity.getProducts().get(p));
		}
		entity.setTotalQuantity(quantity);
		entity.setCartTotalPrice(price);
		 //Adding cart
		cartRepository.save(entity);
		LOGGER.info("CartItemEntity addCartItem() ended");
		return cartRepository.findAll();}
		else {
			throw new CartException("Cart exists for the user try updating!");
			}
		}		
		else {
			throw new CartException("No product is found");
			}
		}	
	else {
		throw new CartException("No user is found");
		}
	}
	
	
	@Override
	@Transactional
	public List<CartItemEntity> deleteCartlist(int userId) throws CartException{
		LOGGER.info("CartItemEntity deleteCartlist() started");
		 //Finding user by userId
		Userdata user=userRepository.findById(userId).orElse(null);
		if(user!=null)
		{
			//Finding cart by user
		CartItemEntity cart=cartRepository.findByUserId(user);
		if(cart!=null) {
			  //Deleting the cart
    	cartRepository.delete(cart);
    	LOGGER.info("CartItemEntity deleteCartlist() ended");
		return cartRepository.findAll();
		}
		else {
			throw new CartException("Cart not found for user is found");
			}
		}
		else {
			throw new CartException("No user is found");
			}		
	}
	
	
	@Override
	@Transactional
	public CartItemEntity updateCartItem(Cart cart) throws CartException {
		LOGGER.info("CartItemEntity updateCartItem() started");
		 //Finding user by userId
		Userdata user=userRepository.findById(cart.getUserId()).orElse(null);
		if(user!=null)
		{
			//Finding cart by user
		CartItemEntity entity=cartRepository.findByUserId(user);
		if(entity!=null) {	
			//Finding product by productId 
		ProductEntity product=productRepository.findById(cart.getProductId()).orElse(null);
		if(product!=null) {
			 //Adding product to map
		entity.getProducts().put(product,cart.getProductQuantity());
		double price=entity.getCartTotalPrice();
		long quantity=entity.getTotalQuantity();
		for(ProductEntity p:entity.getProducts().keySet()) {
			//Calculating total Quantity
			quantity=quantity+entity.getProducts().get(p);
			//Calculating total price
			price=price+(p.getPrice()*entity.getProducts().get(p));
		}
		entity.setTotalQuantity(quantity);
		entity.setCartTotalPrice(price);
		 //Updating Cart
		cartRepository.save(entity);
		LOGGER.info("CartItemEntity updateCartItem() ended");
		return entity;
		}		
		else {
			throw new CartException("Product not found. Please add a new one");			
			}
		}
		else {
			throw new CartException("Cart not found. Please add a new one");
			}
		}
		else {
			throw new CartException("User not found.");
		}		
	}

	
	@Override
	@Transactional
	public CartItemEntity findCartItem(String productId, int userId) throws CartException {
		LOGGER.info("CartItemEntity findCartItem() started");
		//Finding user by userId
		Userdata user=userRepository.findById(userId).orElse(null);
		  //Finding cart by user
		CartItemEntity cart=cartRepository.findByUserId(user);
		LOGGER.info("CartItemEntity findCartItem() ended");
		if(cart!=null){//throw cart not found for that userid exception
			 //Iterating Map
    		Map<ProductEntity,Integer> products=cart.getProducts();
			for(ProductEntity p:products.keySet()) {
				//Searching for the Product in map
				if(p.getProductId().equals(productId)) {
					return cart;
		        }	
				else {
					throw new CartException("Product not found in cart.");
				}
	        }
		}
		else {
			throw new CartException("Cart not found for user.");
		}
		//optional-throw cartitem not found exception
			return null;                                                                
	}

	
	@Override
	@Transactional
	public List<CartItemEntity> deleteCartItem(Long cartId, String productId) throws CartException {
		LOGGER.info("CartItemEntity deleteCartItem() started");
		//Finding cart by cartId
		CartItemEntity cart=cartRepository.findById(cartId).orElse(null);        
		Map<ProductEntity,Integer> productmap=cart.getProducts();
		//Iterating map
		for(ProductEntity p:productmap.keySet()) {
			//Searching for the product in map
			if(p.getProductId().equals(productId)) {                            
				  //Deleting the product from map
				productmap.remove(p);
				cart.setProducts(productmap); 
			}
			else {
				throw new CartException("Product not found in cart.");
			}
		}
		cartRepository.save(cart);
		LOGGER.info("CartItemEntity deleteCartItem() ended");
		return cartRepository.findAll();
	}
}
