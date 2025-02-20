package com.cg.go.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.go.entity.ProductEntity;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, String>{
	
	List<ProductEntity> findByCategory(String category);
	
	List<ProductEntity> findByProductNameContaining(String productName);

    List<ProductEntity> findByProductName(String productName);

    public List<ProductEntity> findByPriceLessThan(double price);
}
