package com.cg.go.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.dao.IProductRepository;
import com.cg.go.dao.ISalesReportRepository;
import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.exception.SalesReportException;
//indication of holding the business logic
@Service
public class SalesReportServiceImpl implements ISalesReportService {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    ISalesReportRepository salesreportRepository;
    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IProductRepository productRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesReportServiceImpl.class);
 

    @Override
    @Transactional
    public List<SalesReportEntity> findAllSalesReport() throws SalesReportException {
    	LOGGER.info("SalesReportEntity findAllSalesReport() started");
    	 //Finding all sales report
    	List<SalesReportEntity> sales=salesreportRepository.findAll();
    	LOGGER.info("SalesReportEntity findAllSalesReport() ended");
		if(sales.isEmpty()) {
			throw new SalesReportException("Sales report list is empty");	
		}
		else {
			return salesreportRepository.findAll();
		}
    }    

    
	@Override
	@Transactional
    public SalesReportEntity findSalesReportByProductId(String productId) throws SalesReportException {
		LOGGER.info("SalesReportEntity findSalesReportByProductId() started");
		//Finding product by productId
    	ProductEntity product=productRepository.findById(productId).orElse(null);
    	LOGGER.info("SalesReportEntity findSalesReportByProductId() ended");
    	if(product!=null) {
    	//Finding sales for product
        SalesReportEntity salesReport=salesreportRepository.findByProddetails(product); 
        if(salesReport==null) {
        	throw new SalesReportException("Sales report is not present for this product");   
        }
        else {
        	return salesReport;
        }
    	}	
    	else {
        	throw new SalesReportException("This product is not available ");	
    	}
	}
    
  
	@Override
	@Transactional
    public void deleteAllSalesReport() throws SalesReportException {
		LOGGER.info("SalesReportEntity deleteAllSalesReport() started");
		//Finding all sales reports
		List<SalesReportEntity> list = salesreportRepository.findAll();
		LOGGER.info("SalesReportEntity deleteAllSalesReport() ended");
		if(list.isEmpty()) {
			throw new SalesReportException("Sales report list is empty");
		}
		else {
		//Delete all sales reports
        salesreportRepository.deleteAll();
		}   
    }

   
	@Override
	@Transactional
    public List<SalesReportEntity> deleteSalesReportById(Long salesReportId) throws SalesReportException{  
		LOGGER.info("SalesReportEntity deleteSalesReportById() started");
		//Finding sales report by sales report id
        Optional<SalesReportEntity> salesReport=salesreportRepository.findById(salesReportId);
        if(salesReport.isPresent()) {
        	//Deleting sales report
            salesreportRepository.deleteById(salesReportId);
        }
        else {
			throw new SalesReportException("Sales report is not present for this id");
            }   
        LOGGER.info("SalesReportEntity deleteSalesReportById() ended");
        return salesreportRepository.findAll();
    }
}