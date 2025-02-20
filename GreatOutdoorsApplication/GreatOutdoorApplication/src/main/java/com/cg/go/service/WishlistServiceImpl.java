package com.cg.go.service;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.IUserRepository;
import com.cg.go.dao.IWishlistRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.entity.Wishlist;
import com.cg.go.exception.WishlistException;
//indication of holding the business logic
@Service
public class WishlistServiceImpl implements IWishlistService{
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    IWishlistRepository wishRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IProductRepository productRepository;   
    public static final Logger LOGGER = LoggerFactory.getLogger(WishlistServiceImpl.class);
    
    @Override
    @Transactional
    public List<Wishlist> addWishlistItem(int userId,String productId)  throws WishlistException{
    	LOGGER.info("Wishlist addWishlistItem() started");
    	 //Finding user by userId
        Userdata user=userRepository.findById(userId).orElse(null);
        LOGGER.info("Wishlist addWishlistItem() ended");
         if(!(user==null)) {
        	//Finding product by productId
        	 ProductEntity product=productRepository.findById(productId).orElse(null);
             if(!(product==null)) {    
            	//Finding WishList for user
            	 Wishlist wish1= wishRepository.findByUserId(user);
            	 if(wish1!=null) {
            		 if(!(wish1.getProductIds().contains(product))) {
            			 wish1.getProductIds().add(product);
            			//Adding WishList
                     wishRepository.save(wish1);   
            		 }
                 }
                 else {
                	 Wishlist wish=new Wishlist();
                	 wish.setUserId(user);
                	 wish.getProductIds().add(product);
                	 wishRepository.save(wish);
                 }
                     return wishRepository.findAll();
             }
                 else {
                	 throw new WishlistException("This product is not present");
                 }
         }
         	else {
         		throw new WishlistException("This user is not present");
         	}
    }
 

    @Override
    @Transactional
    public void deleteWishlist(int userId) throws WishlistException {
    	LOGGER.info("Wishlist deleteWishlist() started");
        Userdata user=userRepository.findById(userId).orElse(null);
         Wishlist wish= wishRepository.findByUserId(user);
         LOGGER.info("Wishlist deleteWishlist() ended");
         if(wish!=null) {
         wishRepository.delete(wish);
         }
         else {
        	 throw new WishlistException("No wish list present for this user");
         }
     }


    @Override
    @Transactional
    public void deleteWishlistItem(String productId, int userId) throws WishlistException {
    	LOGGER.info("Wishlist deleteWishlistItem() started");
        Userdata user=userRepository.findById(userId).orElse(null);
         Wishlist wish= wishRepository.findByUserId(user);
         LOGGER.info("Wishlist deleteWishlistItem() ended");
         if(wish!=null) {
        	 ProductEntity product=productRepository.findById(productId).orElse(null);
        	 if(product!=null) {
        		 List<ProductEntity> products=wish.getProductIds();
        		 products.remove(product);
        		 wish.setProductIds(products);
        		 wishRepository.save(wish);
        	 }
        	 else {
        		 throw new WishlistException("User has not added this product in wish list");
        	 }
         }
         else {
        	 throw new WishlistException("No wish list present for this user");
         }        
    }
 

    @Override
    @Transactional
    public Wishlist findWishlistItem(String productId, int userId) throws WishlistException {
    	LOGGER.info("Wishlist findWishlistItem() started");
        Userdata user=userRepository.findById(userId).orElse(null);
         Wishlist wish= wishRepository.findByUserId(user);
         LOGGER.info("Wishlist findWishlistItem() ended");
         if(wish!=null) {
         ProductEntity product=productRepository.findById(productId).orElse(null);
         if(wish.getProductIds().contains(product)) {
             return wish;           
         }
         else {
        	 throw new WishlistException("User has not added this product in wish list");            
         }
         }
         else {
        	 throw new WishlistException("No wish list present for this user");
         }
    }
 

    @Override
    @Transactional
    public Wishlist findWishlist(int userId) throws WishlistException {
    	LOGGER.info("Wishlist findWishlist() started");
    	 Userdata user=userRepository.findById(userId).orElse(null);
         Wishlist wish= wishRepository.findByUserId(user);
         LOGGER.info("Wishlist findWishlist() ended");
         if(wish!=null) {
        	 return wish;
        	 }
         else {
        	 throw new WishlistException("No wish list present for this user");
         }
     }
 

    @Override
    @Transactional
    public List<Wishlist> findAllItems() throws WishlistException {
    	LOGGER.info("Wishlist findAllItems() started");
    	List<Wishlist> list = wishRepository.findAll();
    	LOGGER.info("Wishlist findAllItems() ended");
		if(list.isEmpty()) {
			throw new WishlistException("Wishlist is empty");
			}
		else {
			return list;
		}
     }
}