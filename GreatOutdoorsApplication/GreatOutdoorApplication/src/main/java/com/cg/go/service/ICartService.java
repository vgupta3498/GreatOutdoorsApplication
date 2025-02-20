package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.CartItemEntity;
import com.cg.go.exception.CartException;
import com.cg.go.pojo.Cart;

public interface ICartService {

	CartItemEntity findCartlist(int userId) throws CartException;
	
	CartItemEntity findCartItem(String productId, int userId) throws CartException;

	List<CartItemEntity> deleteCartItem(Long cartId,String productId) throws CartException;

	List<CartItemEntity> deleteCartlist(int userId) throws CartException;
	
	List<CartItemEntity> addCartItem(Cart cart) throws CartException;
	
	CartItemEntity updateCartItem(Cart cart) throws CartException;

}
