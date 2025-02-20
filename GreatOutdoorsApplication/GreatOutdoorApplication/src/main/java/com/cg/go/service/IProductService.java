package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.ProductEntity;
import com.cg.go.exception.ProductException;

public interface IProductService {
	
	List<ProductEntity> findAllProducts();

	ProductEntity findByProductId(String id);

	List<ProductEntity> findByProductCategory(String category);

	List<ProductEntity> addProduct(ProductEntity productEntity);

	List<ProductEntity> updateProduct(ProductEntity productEntity);

	List<ProductEntity> updateByProductQuantity(Integer quantity,String productId) throws ProductException;

	List<ProductEntity> deleteByProductId(String id) throws ProductException;

	List<ProductEntity> searchByName(String productName);

	List<ProductEntity> searchByKeyword(String productName);
	
	List<ProductEntity> filterByPrice(double maxPrice);

}
