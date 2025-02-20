package com.cg.go.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.go.dao.IGrowthReportRepository;
import com.cg.go.dao.IOrderRepository;
import com.cg.go.entity.GrowthReportEntity;
import com.cg.go.entity.OrderEntity;
import com.cg.go.exception.GrowthReportException;
import com.cg.go.exception.SalesReportException;

//indication of holding the business logic
@Service
public class GrowthReportServiceImpl implements IGrowthReportService{
	//annotation provides more fine-grained control over where and how autowiring should be accomplished.
	//automatic injection of beans
    @Autowired
    IGrowthReportRepository growthreportRepository;
    @Autowired
    IOrderRepository orderRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(GrowthReportServiceImpl.class);
    

    @Override
    @Transactional
    public List<GrowthReportEntity> findAllGrowthReport() {   
    	LOGGER.info("GrowthReportEntity findAllGrowthReport() started");
        return growthreportRepository.findAll();
    }
 

    @Override
    @Transactional
    public List<GrowthReportEntity> addGrowthReport(LocalDate currentdate) throws SalesReportException {
    	LOGGER.info("GrowthReportEntity addGrowthReport() started");
    	//Checking if there is an existing growthReport for date
        if(growthreportRepository.findByCurrentdate(currentdate)==null) {
        GrowthReportEntity growthReport=new GrowthReportEntity();
        growthreportRepository.save(growthReport);
        //Finding all orders on that date
        List<OrderEntity> orders=orderRepository.findByDispatchDate(currentdate);
        //Calculating previous days revenue
        Double previousPrice=growthreportRepository.findById(growthReport.getGrowthReportId()-1).orElse(null).getRevenue();
        //Calculating today's revenue
        Double price=orders.stream().map(e->e.getTotalPrice()).reduce((a,b)->a+b).orElse(null);
        //Calculating the amount change from previous day
        Double amountChange=price-previousPrice;
        Double percentage =0.0;
        //calculating growth percentage
        if(amountChange<0) {
        	percentage=price/previousPrice;
        }
        else {       
         percentage=(Math.abs(amountChange)/price)*100;
        }
        //Setting colour code based on growth percentage
        String colour="";
        if(percentage<0) {
            colour="Red";
        }
        else if(percentage<50) {
            colour="orange";
        }
        else {
            colour="Green";
        }
        growthReport.setCurrentdate(currentdate);
        growthReport.setRevenue(price);
        growthReport.setAmountChange(amountChange);
        growthReport.setPercentageGrowth(percentage);
        growthReport.setColorCode(colour);
        //Adding growth report
        growthreportRepository.save(growthReport); 
        return growthreportRepository.findAll();
        }
        else {
        	throw new SalesReportException("No sales found for this date");
        }
        
    } 

    
    @Override
    @Transactional
    public List<GrowthReportEntity> deleteAllGrowthReport() throws GrowthReportException {
    	LOGGER.info("GrowthReportEntity deleteAllGrowthReport() started");
    	List<GrowthReportEntity> growth = growthreportRepository.findAll();
    	LOGGER.info("GrowthReportEntity deleteAllGrowthReport() ended");
		if(growth.isEmpty()) {
			throw new GrowthReportException("Growth report list is empty");
		}
		else {
		//Deleting all growth reports
        growthreportRepository.deleteAll();
        return growthreportRepository.findAll();
		}
    }
    
    
    @Override
    @Transactional
    public List<GrowthReportEntity> deleteGrowthReportById(Long growthReportId) throws GrowthReportException {
    	LOGGER.info("GrowthReportEntity deleteGrowthReportById() started");
    	//Finding growth report by growth report id
        Optional<GrowthReportEntity> growthreportentity=growthreportRepository.findById(growthReportId);
        LOGGER.info("GrowthReportEntity deleteGrowthReportById() ended");
        if(growthreportentity.isPresent()) {
        	//Deleting growth report
            growthreportRepository.deleteById(growthReportId);
        }
        else {
        	throw new GrowthReportException("No growth report present for this id");
        }    
        return growthreportRepository.findAll();
    }
}