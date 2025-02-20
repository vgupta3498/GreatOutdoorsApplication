package com.cg.go.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IProductRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.exception.ProductException;
//indication of holding the business logic
@Service
public class ProductServiceImpl implements IProductService {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
	@Autowired
	IProductRepository productrepository;
	public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);


	@Override
	@Transactional
	public List<ProductEntity> findAllProducts() {
		LOGGER.info("ProductEntity findAllProducts() started");
		//Finding all products
		List<ProductEntity> list = productrepository.findAll();
		LOGGER.info("ProductEntity findAllProducts() ended");
		if(list.isEmpty()) {
			throw new ProductException("Product list is empty");
		}
		else {
			return list;
		}		
	}

	
	@Override
	@Transactional
	public ProductEntity findByProductId(String productId) {
		LOGGER.info("ProductEntity findByProductId() started");
		//Finding product by productId
		Optional<ProductEntity> product=productrepository.findById(productId);
		LOGGER.info("ProductEntity findByProductId() ended");
		if(product.isPresent()) {
			return product.get();
		}
		else {
			throw new ProductException("No product found");
		}
	}

	
	@Override
	@Transactional
	public List<ProductEntity> findByProductCategory(String category) {
		LOGGER.info("ProductEntity findByProductCategory() started");
		//Finding product by category
		List<ProductEntity> product=productrepository.findByCategory(category);
		LOGGER.info("ProductEntity findByProductCategory() ended");
		if(product.isEmpty()) {
			throw new ProductException("No product found");
		}
		else {
			return product;
		}
	}

	
	@Override
	@Transactional
	public List<ProductEntity> addProduct(ProductEntity productEntity)  {
		LOGGER.info("ProductEntity addProduct() started");
		//Finding product by productId
		ProductEntity product=productrepository.findById(productEntity.getProductId()).orElse(null);
		LOGGER.info("ProductEntity addProduct() ended");
		if(product==null) {
		//Adding product
		productrepository.save(productEntity);
		return productrepository.findAll();
		}
		else {
			throw new ProductException("Product id already present. try entering some new one!");
		}
	}

	
	@Override
	@Transactional
	public List<ProductEntity> updateProduct(ProductEntity productEntity)  {
		LOGGER.info("ProductEntity updateProduct() started");
		//Finding product for updating by productId
		ProductEntity product=productrepository.findById(productEntity.getProductId()).orElse(null);
		LOGGER.info("ProductEntity updateProduct() ended");
		if(product!=null) {
		//Updating product
		productrepository.save(productEntity);
		return productrepository.findAll();
		}
		else {
			throw new ProductException("No product with this id is present. try entering some new one!");
		}
	}

	
	@Override
	@Transactional
	public  List<ProductEntity> updateByProductQuantity(Integer quantity, String productId) throws ProductException {
		LOGGER.info("ProductEntity updateByProductQuantity() started");
		//Finding product for updating by productId
		Optional<ProductEntity> product1=productrepository.findById(productId);		
		if(product1.isPresent()) {
			//Updating Quantity
			product1.get().setQuantity(quantity);
		}
		else {
			throw new ProductException("No product found");
		}
		//Updating Product
		productrepository.save(product1.get());
		LOGGER.info("ProductEntity updateByProductQuantity() ended");
		return productrepository.findAll();
	}

	
	@Override
	@Transactional
	public List<ProductEntity> deleteByProductId(String productId) throws ProductException  {
		LOGGER.info("ProductEntity deleteByProductId() started");
		//Finding product for deleting by productId
		Optional<ProductEntity> product1=productrepository.findById(productId);
		if(product1.isPresent()) {
			//Deleting product
			productrepository.deleteById(productId);
		}
		else {
			throw new ProductException("No product found");
		}
		LOGGER.info("ProductEntity deleteByProductId() ended");
		return productrepository.findAll();
	}

	
	@Override
	@Transactional
	public List<ProductEntity> searchByName(String productName) {
		LOGGER.info("ProductEntity searchByName() started");
		 //Finding products by product name
		List<ProductEntity> list= productrepository.findByProductName(productName);
		LOGGER.info("ProductEntity searchByName() ended");
		if(list.isEmpty()) {
			throw new ProductException("No product name found");
		}
		else {
			return list;
		}
	}

	
	@Override
	@Transactional
	public List<ProductEntity> searchByKeyword(String productName) {
		LOGGER.info("ProductEntity searchByKeyword() started");
		//Searching products by keyword
		List<ProductEntity> list=productrepository.findByProductNameContaining(productName);
		LOGGER.info("ProductEntity searchByKeyword() ended");
		if(list.isEmpty()) {
			throw new ProductException("No matching product found");
		}
		else {
			return list;
		}		
	}

	
	@Override
	@Transactional
	public List<ProductEntity> filterByPrice(double maxPrice) {
		LOGGER.info("ProductEntity filterByPrice() started");
		//Filtering products under given price
		List<ProductEntity> list=productrepository.findByPriceLessThan(maxPrice);
		LOGGER.info("ProductEntity filterByPrice() ended");
		if(list.isEmpty()) {
			throw new ProductException("No product is present less than the price");
		}
		else {
			return list;
			}
	}
}


