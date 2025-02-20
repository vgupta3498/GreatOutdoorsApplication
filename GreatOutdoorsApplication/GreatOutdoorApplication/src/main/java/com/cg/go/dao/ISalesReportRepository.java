package com.cg.go.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.go.entity.ProductEntity;
import com.cg.go.entity.SalesReportEntity;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface ISalesReportRepository extends JpaRepository<SalesReportEntity, Long> {

	public SalesReportEntity findByProddetails(ProductEntity product);	
}
