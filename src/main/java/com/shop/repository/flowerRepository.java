package com.shop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface flowerRepository extends CrudRepository<flower,Integer> {
	flower findById(int id);
	
	public List<flower> findByType(int type);
	
	public List<flower> findByTypeAndIdGreaterThan(int type,int id);
	
	public List<flower> findByName(String name);
	
	public List<flower> findByNameLike(String name);
}
