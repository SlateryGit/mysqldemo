package com.shop.repository;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.shop.entity.*;
public interface ordermanageRepository extends CrudRepository<ordermanage,Integer> {
	ordermanage findById(int id);
	
	public List<ordermanage> findBySender(int sender);
	
	public List<ordermanage> findByReceiver(int receiver);
	
	public List<ordermanage> findByAccept(int accept);
	
	public List<ordermanage> findByReceiverAndAccept(int receiver,int accept);
	
	public List<ordermanage> findByReceiverAndAcceptAndSentGreaterThan(int receiver,int accept,int sent);
	
	//@Query(value="",nativeQuery = true)
	public List<ordermanage> findByValueGreaterThanAndFinishAfter(double value,Timestamp datetime);
	
	@Query(value="select sender from ordermanage where value > ?1 and finish_time > ?2 and sent > ?3",nativeQuery = true)
	public List<Integer> findSender(double value,Timestamp datetime,int sent);
	
	@Query(value="select * from ordermanage where value > ?1 and finish_time > ?2 and sent > ?3 ORDER BY sender",nativeQuery = true)
	public List<ordermanage> findSenderinfo(double value,Timestamp datetime,int sent);

	@Query(value="select sender from ordermanage where value > 0 and finish_time > ?1 and sent > -1 GROUP BY (sender) ORDER BY SUM(value)desc",nativeQuery = true)
	public List<Integer> findSenderList(Timestamp datetime);
	
	String s="select * from ordermanage where value > 0 and sender = ?1 finish_time > ?2 and sent > -1 ORDER BY value";
	
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and finish_time > ?2 and sent > -1 ORDER BY (value)desc",nativeQuery = true)
	public List<ordermanage> getSenderOrder(int  sid,Timestamp datetime);

	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and finish_time > ?2 and sent > -1 ORDER BY (value)desc",nativeQuery = true)
	public List<ordermanage> getReceiverOrder(int  sid,Timestamp datetime);

	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (finish_time)",nativeQuery = true)
	public List<ordermanage> getSenderOrderByTime(int  sid);
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (finish_time)desc",nativeQuery = true)
	public List<ordermanage> getSenderOrderByTimeDesc(int  sid);
	
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (value)",nativeQuery = true)
	public List<ordermanage> getSenderOrderByValue(int  sid);
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (value)desc",nativeQuery = true)
	public List<ordermanage> getSenderOrderByValueDesc(int  sid);
	
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (quantity)",nativeQuery = true)
	public List<ordermanage> getSenderOrderByQuan(int  sid);
	@Query(value="select * from ordermanage where value > 0 and sender = ?1 and sent > -1 ORDER BY (quantity)desc",nativeQuery = true)
	public List<ordermanage> getSenderOrderByQuanDesc(int  sid);
	
	
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (finish_time)",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByTime(int  rid);
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (finish_time)desc",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByTimeDesc(int  rid);
	
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (value)",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByValue(int  rid);
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (value)desc",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByValueDesc(int  rid);
	
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (quantity)",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByQuan(int  rid);
	@Query(value="select * from ordermanage where value > 0 and receiver = ?1 and sent > -1 ORDER BY (quantity)desc",nativeQuery = true)
	public List<ordermanage> getReceiverOrderByQuanDesc(int  rid);
	
}
