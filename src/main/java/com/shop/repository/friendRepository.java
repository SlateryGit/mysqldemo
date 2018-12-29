package com.shop.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface friendRepository extends CrudRepository<friend,Integer> {
	
	//https://blog.csdn.net/wo541075754/article/details/82733278
	@Query(value="select friendid as friends from friend  where myid = ?1\n"+
			"UNION ALL \n" + 
			"select myid as friends from friend where friendid = ?1\n"
			,nativeQuery = true)
	public List<Integer> findfriends(int uid);
	
	public List<friend> findByMyid(int myid);
	public List<friend> findByFriendid(int frid);
	

}
