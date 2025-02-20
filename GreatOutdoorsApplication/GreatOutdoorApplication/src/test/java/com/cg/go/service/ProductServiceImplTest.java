package com.cg.go.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.go.dao.IProductRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.exception.ProductException;

//useful when we need to bootstrap the entire container ,can be used as an alternative to the standard spring-test
@SpringBootTest
class ProductServiceImplTest {
	    
		//allow us to inject mocked dependencies in the annotated class mocked object
	    @InjectMocks
	    ProductServiceImpl productServiceImpl;
	  //It is used to mock the objects that helps in minimizing the repetitive mock objects
	    @Mock
	    IProductRepository productRepo;
	  //annotation provides more fine-grained control over where and how autowiring should be accomplished.
	    @Autowired
		ProductServiceImpl service;
	    
		@Autowired
		IProductRepository productRepository;
	 

		//tells JUnit that the public void method to which it is attached can be run as a test case
	    @Test
	    public void addProductMockTest() {
	        ProductEntity product = new ProductEntity();
	        product.setProductId("1");
			product.setProductName("SpiceJet");
			product.setImage("AUI89");
			product.setCategory("jhgfd");
			product.setColor("jhgfd");
			product.setManufacturer("jhgfd");
			product.setPrice(100.0);
			product.setQuantity(1);
			product.setSpecification("jhgfd");			
			List<ProductEntity> products=productServiceImpl.addProduct(product);
			assertNotNull(products,"Product added");	        
	       // Mockito.when(productRepo.save(product)).thenReturn(product);
	       // assertThat(productServiceImpl.addProduct(product)).isEqualTo(product);
	    }
	    
	    @Test
	    public void updateProductMockTest() {
	    		ProductEntity product = new ProductEntity();
	    		product.setProductId("1");
				product.setProductName("SpiceJet");
				product.setImage("AUI89");
				product.setCategory("jhgfd");
				product.setColor("jhgfd");
				product.setManufacturer("jhgfd");
				product.setPrice(100.0);
				product.setQuantity(1);
				product.setSpecification("jhgfd");
				Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
				product.setQuantity(10);
				List<ProductEntity> products=productServiceImpl.updateProduct(product);
				assertNotNull(products,"Product updated");
	        
			/*
			 * Mockito.when(productRepo.save(product)).thenReturn(product);        //list ka naam de chl jayega
			 * 
			 * assertThat(productServiceImpl.updateProduct(product)).isEqualTo(product);
			 */      
	    }
	    
	    @Test
	    public void deleteByProductIdMockTest() {
	    	ProductEntity product = new ProductEntity();
	    	  product.setProductId("1");
				product.setProductName("SpiceJet");
				product.setImage("AUI89");
				product.setCategory("jhgfd");
				product.setColor("jhgfd");
				product.setManufacturer("jhgfd");
				product.setPrice(100.0);
				product.setQuantity(1);
				product.setSpecification("jhgfd");
				Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
				Mockito.when(productRepo.existsById(product.getProductId())).thenReturn(false);
				assertFalse(productRepo.existsById(product.getProductId()));    
	    }
	    
	    
	    @Test
	    public void findByProductIdMockTest() {
	    	ProductEntity product = new ProductEntity();
	        product.setProductId("1");
			product.setProductName("SpiceJet");
			product.setImage("AUI89");
			product.setCategory("jhgfd");
			product.setColor("jhgfd");
			product.setManufacturer("jhgfd");
			product.setPrice(100.0);
			product.setQuantity(1);
			product.setSpecification("jhgfd");
	        Mockito.when(productRepo.findById(product.getProductId())).thenReturn(Optional.of(product));
	        assertThat(productServiceImpl.findByProductId(product.getProductId())).isEqualTo(product);
	    }
   
	    
	    @Test
	    public void findAllProductsMockTest() {
	    	ProductEntity product1 = new ProductEntity();
	        product1.setProductId("1");
			product1.setProductName("SpiceJet");
			product1.setImage("AUI89");
			product1.setCategory("jhgfd");
			product1.setColor("jhgfd");
			product1.setManufacturer("jhgfd");
			product1.setPrice(100.0);
			product1.setQuantity(1);
			product1.setSpecification("jhgfd");			
			ProductEntity product2 = new ProductEntity();
	        product2.setProductId("1");
			product2.setProductName("SpiceJet");
			product2.setImage("AUI89");
			product2.setCategory("jhgfd");
			product2.setColor("jhgfd");
			product2.setManufacturer("jhgfd");
			product2.setPrice(100.0);
			product2.setQuantity(1);
			product2.setSpecification("jhgfd");	        
	        List<ProductEntity> plist = new ArrayList<>();
	        plist.add(product1); plist.add(product2);	        
	        Mockito.when(productRepo.findAll()).thenReturn(plist);
	        assertThat(productServiceImpl.findAllProducts()).isEqualTo(plist);
	    }
	    
	    
	    
	    
	    
	    
	    
	    //JUNIT TESTING
		@Test
		public void findByProductIdUnitTest() {
			ProductEntity product=service.findByProductId("7000");
			assertNotNull(product, "Product found");
			assertEquals("food",product.getCategory(),"Category is food");
		}
		
		
		@Test
		public void findByProductIdFailUnitTest() {
			assertThrows(ProductException.class, () -> service.findByProductId("700"),"Product not found exception occured");
		}
		
		
		@Test
		public void findAllProductsUnitTest() {
			List<ProductEntity> list =service.findAllProducts();			
//			list.add(p);
//			when(productRepository.findAll()).thenReturn(list);
//			List<ProductEntity> plist = impl.findAllProducts();
			assertNotNull(list,"List is present");
		}
		
		
		@Test
		public void addProductUnitTest()
		{
			ProductEntity product=productRepository.findById("7503").orElse(null);
			assertNull(product,"id is not present can enter the data");
			product=new ProductEntity();
			product.setProductId("7503");
			product.setProductName("SpiceJet");
			product.setImage("AUI89");
			product.setCategory("jhgfd");
			product.setColor("jhgfd");
			product.setManufacturer("jhgfd");
			product.setPrice(100.0);
			product.setQuantity(1);
			product.setSpecification("jhgfd");
			List<ProductEntity> products=service.addProduct(product);
			assertNotNull(products,"Product added");
			/*
			 * ProductEntity product=getProduct();
			 * when(productRepository.save(product)).thenReturn(product);
			 * assertNotNull(impl.addProduct(product));
			 */
		}	
		
		
		@Test
		public void findByProductCategoryUnitTest() {
			List<ProductEntity> product=service.findByProductCategory("food");
			assertNotNull(product, "Product found");
		}
		
		
		@Test
		public void findByProductCategoryFailUnitTest() {
			assertThrows(ProductException.class, () -> service.findByProductCategory("books"),"Product not found exception occured");
		}
		
		
		@Test
		public void updateProductUnitTest(){
			ProductEntity product=productRepo.findById("7500").orElse(null);
			//assertNotNull(product,"id is  present can update the data");
			product=new ProductEntity();
			product.setProductId("7500");
			product.setProductName("SpiceJet");
			product.setImage("AUI89");
			product.setCategory("jhgfd");
			product.setColor("jhgfd");
			product.setManufacturer("jhgfd");
			product.setPrice(300.0);
			product.setQuantity(1);
			product.setSpecification("jhgfd");
			List<ProductEntity> products=service.updateProduct(product);
			assertNotNull(products,"Product updated");
		}
		
		
		@Test
		public void updateProductQuantityUnitTest(){
			ProductEntity product=productRepo.findById("7501").orElse(null);
			//assertNotNull(product,"id is  present can update the quantity");
			//product.setQuantity(100);
			List<ProductEntity> products=service.updateByProductQuantity(2000,"7501");
			assertNotNull(products,"Product updated");
		}
		
		
		@Test 
		public void deleteProductByIdUnitTest() {
			ProductEntity product=productRepo.findById("7503").orElse(null);
			// assertNotNull(product,"id is  present can delete"); 
			List<ProductEntity> products=service.deleteByProductId("7503");
			assertNotNull(products,"Product deleted");
		}
		 
		
		@Test
		public void searchByProductNameUnitTest() {
			List<ProductEntity> product=service.searchByName("choclate");
			assertNotNull(product, "Product found");
		}
		
		
		@Test
		public void searchByProductNameFailUnitTest() {
			assertThrows(ProductException.class, () -> service.searchByName("Nike"),"Product not found exception occured");
		}
		
		
		@Test
		public void searchByKeywordUnitTest() {
			List<ProductEntity> product=service.searchByKeyword("oc");
			assertNotNull(product, "Product found");
		}
		
		
		@Test
		public void searchByKeywordFailUnitTest() {
			assertThrows(ProductException.class, () -> service.searchByKeyword("nik"),"Product not found exception occured");
		}
		
		
		@Test
		public void filterByPriceUnitTest() {
			List<ProductEntity> product=service.filterByPrice(100.0);
			assertNotNull(product, "Product found");
		}
		
		
		@Test
		public void filterByPriceFailUnitTest() {
			assertThrows(ProductException.class, () -> service.filterByPrice(10.0),"Product not found exception occured");
		}
}