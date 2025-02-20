package com.cg.go.controller;
import java.util.List;
import javax.validation.Valid;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cg.go.entity.ProductEntity;
import com.cg.go.exception.ProductException;
import com.cg.go.service.IProductService;

//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class ProductController
{
		//annotation provides more fine-grained control over where and how autowiring should be accomplished.
		//automatic injection of beans.
		@Autowired
		IProductService productService;
		public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);	
		
        //Finding all products
		@GetMapping(path= "/getallproducts")
		public ResponseEntity<List<ProductEntity>> findAllProducts(){			
			LOGGER.info("ProductEntity findAllProducts() started");
			List<ProductEntity> list= productService.findAllProducts();
			LOGGER.info("ProductEntity findAllProducts() ended");			
			if(list.isEmpty()) {
				throw new ProductException("No Products are present ");
			}
			else {
				return new ResponseEntity<List<ProductEntity>>(list, HttpStatus.OK);	 
			}			
		}
		
		
		//Finding Product By ProductId
		@GetMapping(path= "/findProductById/{productId}")
		public ResponseEntity<ProductEntity> findByProductId(@PathVariable String productId){
			LOGGER.info("ProductEntity findByProductId() started");
			ProductEntity product = productService.findByProductId(productId);
			LOGGER.info("ProductEntity findByProductId() ended");			
			return new ResponseEntity<ProductEntity>(product, HttpStatus.OK);
		}
		
		
		//Finding Product By Category
		@GetMapping(path= "/findProductCategory/{category}")
		public ResponseEntity<List<ProductEntity>> findByProductCategory(@PathVariable String category){
			LOGGER.info("ProductEntity findByProductCategory() started");
			List<ProductEntity> product = productService.findByProductCategory(category);
			LOGGER.info("ProductEntity findByProductCategory() ended");			
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);
		}
		
		
		//Adding New Product		
		@PostMapping(path="/addProduct")
		public ResponseEntity<List<ProductEntity>> addProduct(@Valid @RequestBody ProductEntity productEntity) throws ProductException{
			LOGGER.info("ProductEntity addProduct()started");
			List<ProductEntity> product=productService.addProduct(productEntity);
			LOGGER.info("ProductEntity addProduct()ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);			
		}
		
		
		//Updating Product		
		@PutMapping(path="/updateProduct")
		public ResponseEntity<List<ProductEntity>> updateProduct(@Valid @RequestBody ProductEntity productEntity) throws ProductException{
			LOGGER.info("ProductEntity updateProduct() started");
			List<ProductEntity> product= productService.updateProduct(productEntity);
			LOGGER.info("ProductEntity updateProduct() ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);			
		}
		
		
		//Updating Product Quantity
		@PutMapping(path="/updateProductquantity/{productid}/{quantity}")
		public ResponseEntity<List<ProductEntity>> updateProductQuantity(@PathVariable Integer quantity, String productId) {
			LOGGER.info("ProductEntity updateByProductQuantity() started");
			List<ProductEntity> product= productService.updateByProductQuantity(quantity,  productId);
			LOGGER.info("ProductEntity updateByProductQuantity() ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);	
		}
		
		
		//Deleting a Product
		@DeleteMapping(path="/deleteByProductId/{productId}")
		public ResponseEntity<List<ProductEntity>> deleteByProductId(@PathVariable String productId)  {
			LOGGER.info("ProductEntity deleteByProductId()started");
			List<ProductEntity> product= productService.deleteByProductId(productId);
			LOGGER.info("ProductEntity deleteByProductId() ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);
		}
		
		
		//Searching Product By ProductName
		@GetMapping(path= "/searchbyname/{productName}")
		public ResponseEntity<List<ProductEntity>> searchByName(@PathVariable String productName){
			LOGGER.info("ProductEntity searchByName() started");
			List<ProductEntity> product= productService.searchByName(productName);
			LOGGER.info("ProductEntity searchByName() ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);			
		}	
		
		
		//Searching Product By Keyword
		@GetMapping(path= "/searchbykeyword/{productName}")
		public ResponseEntity<List<ProductEntity>> searchByKeyword(@PathVariable String productName) {
			LOGGER.info("ProductEntity searchByKeyword() started");
			List<ProductEntity> product= productService.searchByKeyword(productName);
			LOGGER.error("Exception occured");
			LOGGER.info("ProductEntity searchByKeyword() ended");		
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);	
		}
			
		
		//Filter by price
		@GetMapping(path= "/filter/{maxprice}")
		public ResponseEntity<List<ProductEntity>> filterByPrice(@PathVariable double maxprice){
			LOGGER.info("ProductEntity filterByPrice() started");
			List<ProductEntity> product= productService.filterByPrice(maxprice);
			LOGGER.info("ProductEntity filterByPrice() ended");
			return new ResponseEntity<List<ProductEntity>>(product, HttpStatus.OK);			
		}
}