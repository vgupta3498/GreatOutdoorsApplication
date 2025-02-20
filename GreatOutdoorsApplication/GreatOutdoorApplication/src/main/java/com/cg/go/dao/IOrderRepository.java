package com.cg.go.dao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.go.entity.OrderEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.OrderException;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, String> {
	
	public List<OrderEntity> findByUserId(Userdata user);
	
	public List<OrderEntity> findByDispatchDate(LocalDate dispatchDate);
	
}
