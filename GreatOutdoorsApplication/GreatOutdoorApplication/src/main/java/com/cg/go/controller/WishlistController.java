package com.cg.go.controller;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.Wishlist;
import com.cg.go.exception.WishlistException;
import com.cg.go.service.IWishlistService;
 
//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class WishlistController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    IWishlistService wishService;
    public static final Logger LOGGER = LoggerFactory.getLogger(WishlistController.class);
    
    //Creating a Wishlist
    @PostMapping(path="/insertwishlist/{userId}/{productId}")
    public ResponseEntity<List<Wishlist>> addWishlistItem(int userId,String productId)  throws WishlistException {
    	LOGGER.info("Wishlist addWishlistItem() started");
    	List<Wishlist> list= wishService.addWishlistItem(userId,productId);
    	LOGGER.info("Wishlist addWishlistItem() ended");
        return new ResponseEntity<List<Wishlist>>(list, HttpStatus.OK);
    }
    
    
    //Deleting Wishlist
    @DeleteMapping(path="/deletewishlistbyuserid/{userId}")
    public ResponseEntity<String> deleteWishlist(int userId) throws WishlistException{
    	LOGGER.info("Wishlist deleteWishlist() started");
        wishService.deleteWishlist(userId);
        LOGGER.info("Wishlist deleteWishlist() ended");
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }
    
    
    //Deleting a Product From Wishlist
    @DeleteMapping(path="/deleteproductinwishlist/{productId}/{userId}")
    public ResponseEntity<String> deleteWishlistItem(@PathVariable String productId,@PathVariable int userId) throws WishlistException{
    	LOGGER.info("Wishlist deleteWishlistItem() started");
        wishService.deleteWishlistItem( productId, userId);
        LOGGER.info("Wishlist deleteWishlistItem() ended");
          return new ResponseEntity<String>("Done", HttpStatus.OK);
    }
    
    
    //Getting a Product From Wishlist
    @GetMapping(path="/getwishlistwithproduct/{productId}/{userId}")
    public ResponseEntity<Wishlist> findWishlistItem(String productId, int userId) throws WishlistException{
    	LOGGER.info("Wishlist findWishlistItem() started");
        Wishlist wish=wishService.findWishlistItem( productId, userId);
        LOGGER.info("Wishlist findWishlistItem() ended");
        return new ResponseEntity<Wishlist>(wish,HttpStatus.OK);
    }
    
    
    //Getting a Wishlist
    @GetMapping(path="/getwishlist/{userId}")
    public ResponseEntity<Wishlist> findWishlist(int userId) throws WishlistException{
    	LOGGER.info("Wishlist findWishlist() started");
        Wishlist wish=wishService.findWishlist(userId);
        LOGGER.info("Wishlist findWishlist() ended");
        return new ResponseEntity<Wishlist>(wish,HttpStatus.OK);
    }
    
    
    //Getting All Wishlist
    @GetMapping(path="/getallwishlist")
    public ResponseEntity<List<Wishlist>> findAllItems() throws WishlistException{
    	LOGGER.info("Wishlist findAllItems() started");
        List<Wishlist> wish=wishService.findAllItems();
        LOGGER.info("Wishlist findAllItems() ended");
        return new ResponseEntity<List<Wishlist>>(wish,HttpStatus.OK);
    }
}