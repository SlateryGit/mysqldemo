package com.shop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface AAdminRepository extends CrudRepository<Admininfo,Integer> {
	Admininfo findById(int id);
	
	public List<Admininfo> findByPhone(String phone);

	
}
