package com.cg.go.controller;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.CartItemEntity;
import com.cg.go.exception.CartException;
import com.cg.go.pojo.Cart;
import com.cg.go.service.CartServiceImpl;


//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods 
@RequestMapping("/api/v1")
public class CartController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	CartServiceImpl cartService;
	public static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	//Getting Cart By UserId
	@GetMapping(path = "/getcartbyuserid/{userId}")
    public ResponseEntity<CartItemEntity> getById(@PathVariable int userId) throws CartException {
		LOGGER.info("CartEntity getById() started");
		CartItemEntity cart = cartService.findCartlist(userId);
		LOGGER.info("CartEntity getById() ended");
        if(cart==null) {
            return new ResponseEntity("Sorry! Product not available!", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(cart, HttpStatus.OK);        
        }
	}
	
	
	//Adding New Cart
	@PostMapping(path="/addcart")
    public ResponseEntity<List<CartItemEntity>> addcart(@Valid @RequestBody Cart cartItem) throws CartException {
		LOGGER.info("CartEntity addcart() started");
		List<CartItemEntity> list=cartService.addCartItem(cartItem);
		LOGGER.info("CartEntity addcart() ended");
        return new ResponseEntity<List<CartItemEntity>>(list,HttpStatus.OK);
	}
	
	
	//Deleting Cart By UserId
	@DeleteMapping(path="/deletecart/{userId}")
    public ResponseEntity<List<CartItemEntity>> deletecart(@PathVariable int userId) throws CartException{
		LOGGER.info("CartEntity deletecart() started");
        List<CartItemEntity> list = cartService.deleteCartlist(userId);
        LOGGER.info("CartEntity deletecart() ended");
        return new ResponseEntity<List<CartItemEntity>>(list, HttpStatus.OK);
    }
	
	
	//Updating Cart
	@PutMapping(path="/updatecart")
    public ResponseEntity<CartItemEntity> updateCartItem(@Valid @RequestBody Cart cart) throws CartException {
		LOGGER.info("CartEntity updateCartItem() started");
       CartItemEntity entity= cartService.updateCartItem(cart);
       LOGGER.info("CartEntity updateCartItem() ended");
       return new ResponseEntity<CartItemEntity>(entity,HttpStatus.OK);
    }
	
	
	//Finding Product In a Cart For Particular UserId 
	@GetMapping(path="/findcartitem/{productId}/{userId}")
    public ResponseEntity<CartItemEntity> findCartItem(@PathVariable String productId,@PathVariable int userId) throws CartException{
		LOGGER.info("CartEntity findCartItem() started");
        CartItemEntity cart = cartService.findCartItem(productId,userId);
        LOGGER.info("CartEntity findCartItem() ended");
        return new ResponseEntity<CartItemEntity>(cart, HttpStatus.OK);
    }
	
	
	//Deleting Product In a Cart
	@DeleteMapping(path="/deletecartitem/{cartId}/{productId}")
	public ResponseEntity<List<CartItemEntity>> deleteCartItem( @PathVariable long cartId,@PathVariable String productId) throws CartException {
		LOGGER.info("CartEntity deleteCartItem() started");
		List<CartItemEntity> list=cartService.deleteCartItem(cartId,productId);
		LOGGER.info("CartEntity deleteCartItem() ended");
        return new ResponseEntity<List<CartItemEntity>>(list,HttpStatus.OK);
    }
}
