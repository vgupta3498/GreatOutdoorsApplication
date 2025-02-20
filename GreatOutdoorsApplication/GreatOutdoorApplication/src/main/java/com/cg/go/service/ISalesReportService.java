package com.cg.go.service;
import java.util.List;
import com.cg.go.entity.SalesReportEntity;
import com.cg.go.exception.SalesReportException;

public interface ISalesReportService {

	List<SalesReportEntity> findAllSalesReport() throws SalesReportException;

	List<SalesReportEntity> deleteSalesReportById(Long salesReportId) throws SalesReportException;

	SalesReportEntity findSalesReportByProductId(String productId) throws SalesReportException;

	void deleteAllSalesReport() throws SalesReportException;

}
