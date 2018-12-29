package com.shop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface moneymanageRepository extends CrudRepository<moneymanage,Integer> {
	moneymanage findById(int id);
	
	public List<moneymanage> findByUid(int uid);
	
	
}
