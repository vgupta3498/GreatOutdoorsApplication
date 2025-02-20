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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.exception.SalesReportException;
import com.cg.go.service.ISalesReportService;
 
//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class SalesReportController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    ISalesReportService salesreportService;
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesReportController.class);	
    
    
    		//Getting All Sales Reports
    		@SuppressWarnings({ "rawtypes", "unchecked" })
    		@GetMapping(path= "/getallsalesreport")
    		public ResponseEntity<List<SalesReportEntity>> findAllSalesReport()throws SalesReportException{
    			LOGGER.info("SalesReportEntity findAllSalesReport() started");
    			List<SalesReportEntity> list=salesreportService.findAllSalesReport();
    			LOGGER.info("SalesReportEntity findAllSalesReport() ended");
    			if(list.isEmpty()) {
    				return new ResponseEntity("Not available",HttpStatus.NOT_FOUND);           
    			}
    			else {
    				return new ResponseEntity<List<SalesReportEntity>>(list, HttpStatus.OK);            
    			}     
    		}
   
                      
            //Deleting Sales Report
            @DeleteMapping(path="/deletesalesreportByReportId/{salesReportId}")
            public ResponseEntity<List<SalesReportEntity>> removeSalesReportById(@PathVariable Long salesReportId) throws SalesReportException {
            	LOGGER.info("SalesReportEntity removeSalesReportById() started");
                List<SalesReportEntity> list=salesreportService.deleteSalesReportById(salesReportId);
                LOGGER.info("SalesReportEntity removeSalesReportById() ended");
                return new ResponseEntity<List<SalesReportEntity>>(list, HttpStatus.OK);
            }
            
            
            //Finding Sales Report Of a Product
            @GetMapping(path= "/getsalesreportByProductId/{productId}")///{price}  , @PathVariable Integer price
            public ResponseEntity<SalesReportEntity> getSalesReportByProductId(@PathVariable String productId) throws SalesReportException{
            	LOGGER.info("SalesReportEntity getSalesReportByProductId() started");
            	SalesReportEntity salesReport=salesreportService.findSalesReportByProductId(productId);
            	LOGGER.info("SalesReportEntity getSalesReportByProductId() ended");            
                return new ResponseEntity<SalesReportEntity> (salesReport, HttpStatus.OK);     
            }
            
               
            //Deleting All Sales Report
            @DeleteMapping(path="/deleteallsalesreport")
            public void removeAllSalesreport() throws SalesReportException{
            	LOGGER.info("SalesReportEntity removeAllSalesreport() started");
                salesreportService.deleteAllSalesReport();
                LOGGER.info("SalesReportEntity removeAllSalesreport() ended");
            }
	}