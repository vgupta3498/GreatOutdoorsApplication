package com.cg.go.dao;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.go.entity.GrowthReportEntity;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface IGrowthReportRepository extends JpaRepository<GrowthReportEntity, Long>{
    
	GrowthReportEntity findByCurrentdate(LocalDate currentdate);   
}