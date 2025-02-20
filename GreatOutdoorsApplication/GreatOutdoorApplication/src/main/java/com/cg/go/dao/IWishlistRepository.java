package com.cg.go.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.entity.Wishlist;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface IWishlistRepository extends JpaRepository<Wishlist, Long> {
   
	public Wishlist findByUserId(Userdata user);

    public Wishlist findByProductIds(ProductEntity product);

}
 