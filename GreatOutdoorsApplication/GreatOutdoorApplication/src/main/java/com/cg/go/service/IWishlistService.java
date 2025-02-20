package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.Wishlist;
import com.cg.go.exception.WishlistException;

public interface IWishlistService {
	
	List<Wishlist> addWishlistItem(int userId,String productId)  throws WishlistException;
	 
    void deleteWishlist(int userId) throws WishlistException;
    
    void deleteWishlistItem(String productId, int userId) throws WishlistException;
    
    Wishlist findWishlistItem(String productId, int userId) throws WishlistException;
    
    Wishlist findWishlist(int userId) throws WishlistException;
    
    List<Wishlist> findAllItems() throws WishlistException ;
}
 