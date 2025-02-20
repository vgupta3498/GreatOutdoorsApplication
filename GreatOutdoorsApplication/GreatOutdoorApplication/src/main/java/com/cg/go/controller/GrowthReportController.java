package com.cg.go.controller;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.go.entity.GrowthReportEntity;
import com.cg.go.exception.GrowthReportException;
import com.cg.go.exception.SalesReportException;
import com.cg.go.service.IGrowthReportService; 

//is a specialized version of the controller. It includes the @Controller and @ResponseBody annotations
@RestController
//is used to map web requests to Spring Controller methods
@RequestMapping("/api/v1")
public class GrowthReportController {
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    IGrowthReportService growthreportService;
	public static final Logger LOGGER = LoggerFactory.getLogger(GrowthReportController.class);	

    	//Find All Growth Reports
    	@SuppressWarnings({ "unchecked", "rawtypes" })
    	@GetMapping(path= "/getallgrowthreport")
    	public ResponseEntity<List<GrowthReportEntity>> findAllGrowthReport(){
    		LOGGER.info("GrowthReportEntity findAllGrowthReport() started");
    		List<GrowthReportEntity> list=growthreportService.findAllGrowthReport();
    		LOGGER.info("GrowthReportEntity findAllGrowthReport() ended");
    		if(list.isEmpty()) {
    			return new ResponseEntity("Not available",HttpStatus.NOT_FOUND);
    		}
    		else {
    			return new ResponseEntity<List<GrowthReportEntity>>(list, HttpStatus.OK);
    		}            
    	}
        
    
        //Creating New Growth Report For CurrentDate
        @PostMapping(path="/insertgrowthreport/{currentdate}")
        public ResponseEntity<List<GrowthReportEntity>> addGrowthReport(@PathVariable (name= "currentdate")@DateTimeFormat(pattern = "dd-MM-yyyy")  LocalDate k) throws SalesReportException {
        	LOGGER.info("GrowthReportEntity addGrowthReport() started");
            List<GrowthReportEntity> list=growthreportService.addGrowthReport(k);
            LOGGER.info("GrowthReportEntity addGrowthReport() ended");
            return new ResponseEntity<List<GrowthReportEntity>>(list, HttpStatus.OK);
        }
        
        
        //Deleting a Growth Report
        @DeleteMapping(path="/deletegrowthreportbyid/{growthReportId}")
        public ResponseEntity<List<GrowthReportEntity>> deleteGrowthReportById(@PathVariable Long growthReportId) throws GrowthReportException{
        	LOGGER.info("GrowthReportEntity deleteGrowthReportById() started");
            List<GrowthReportEntity> list = growthreportService.deleteGrowthReportById(growthReportId);
            LOGGER.info("GrowthReportEntity deleteGrowthReportById() ended");
            return new ResponseEntity<List<GrowthReportEntity>>(list, HttpStatus.OK);
        }
        
        
        //Deleting All Growth Reports
        @DeleteMapping(path="/deleteallgrowthreport")
        public ResponseEntity<List<GrowthReportEntity>> deleteAllGrowthReport() throws GrowthReportException{
        	LOGGER.info("GrowthReportEntity deleteAllGrowthReport() started");
            List<GrowthReportEntity> list = growthreportService.deleteAllGrowthReport();
            LOGGER.info("GrowthReportEntity deleteAllGrowthReport() ended");
            return new ResponseEntity<List<GrowthReportEntity>>(list, HttpStatus.OK);
        }
}