package com.shop.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface UUserRepository extends CrudRepository<Userinfo,Integer> {
	Userinfo findById(int id);
	
	public List<Userinfo> findByPhone(String phone);
	
	public List<Userinfo> findByName(String name);
	
	public List<Userinfo> findByNameLike(String name);
}
