package com.cg.go.service;
import java.time.LocalDate;
import java.util.List;
import com.cg.go.entity.GrowthReportEntity;
import com.cg.go.exception.GrowthReportException;
import com.cg.go.exception.SalesReportException;

public interface IGrowthReportService {

    List<GrowthReportEntity> findAllGrowthReport();

    List<GrowthReportEntity> addGrowthReport(LocalDate currentdate) throws SalesReportException; 
    
    List<GrowthReportEntity> deleteAllGrowthReport() throws GrowthReportException; 

    List<GrowthReportEntity> deleteGrowthReportById(Long growthReportId) throws GrowthReportException; 
}
 