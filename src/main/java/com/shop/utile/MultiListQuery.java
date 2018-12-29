package com.shop.utile;
import javax.annotation.Resource;


import com.shop.repository.UUserRepository;
import com.shop.repository.flowerRepository;
import com.shop.repository.moneymanageRepository;
import com.shop.repository.ordermanageRepository;
public class MultiListQuery {
	@Resource 
	private UUserRepository ur;
	
	@Resource 
	private flowerRepository fr;
	
	@Resource 
	private moneymanageRepository mr;
	
	@Resource
	private ordermanageRepository or;
	
	public MultiListQuery() {
		
	}
	public void findByReceiverAndAccpetAndSentGreatThan(int rid,int acc,int sent){
		/*
		List<All_info> als = new LinkedList<>();
		List<ordermanage> ls=new LinkedList<>();
		ls=or.findByReceiverAndAcceptAndSentGreaterThan(rid, acc, sent);
		//ordermanage order;
		for(ordermanage order:ls) {
			
			All_info ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			//ain.add(order.getSender(),u.getName());
			u=ur.findById(order.getReceiver());
			//ain.add(u.getId(), u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			als.add(ain);
		}
		return als;*/
	}
}
