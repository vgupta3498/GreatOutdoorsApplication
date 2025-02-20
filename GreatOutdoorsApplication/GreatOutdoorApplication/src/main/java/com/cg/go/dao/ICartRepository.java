package com.cg.go.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.go.entity.CartItemEntity;
import com.cg.go.entity.Userdata;
import com.cg.go.exception.CartException;
//class provides the mechanism for storage, retrieval, search, update and delete operation on objects
@Repository
public interface ICartRepository extends JpaRepository<CartItemEntity, Long>{

	@Query(value="select c from CartItemEntity c where c.userId=:userId")
	List<CartItemEntity> findCartlist(int userId);
	
	@Query(value="delete from CartItemEntity where userId=:userId",nativeQuery = true)
	void deleteCartlist(int userId) throws CartException;
	
	void deleteByUserId(int userId);
	
	CartItemEntity findByUserId(Userdata user);
}
