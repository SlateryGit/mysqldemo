package com.shop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface money_of_bigmanageRepository extends CrudRepository<money_of_bigmange,Integer> {
	money_of_bigmange findById(int id);
	
	public List<money_of_bigmange> findByUid(int uid);
	
	
}
