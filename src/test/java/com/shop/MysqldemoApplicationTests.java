package com.shop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shop.entity.All_info;
import com.shop.entity.Userinfo;
import com.shop.entity.flower;
import com.shop.entity.ordermanage;
import com.shop.repository.UUserRepository;
import com.shop.repository.flowerRepository;
import com.shop.repository.friendRepository;
import com.shop.repository.moneymanageRepository;
import com.shop.repository.ordermanageRepository;
import com.shop.utile.MD5Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqldemoApplicationTests {

	@Resource 
	private UUserRepository ur;
	@Resource 
	private flowerRepository fr;
	
	@Resource 
	private moneymanageRepository mr;
	
	@Resource 
	private friendRepository friend;
	@Resource
	private ordermanageRepository or;
	@Test
	public void contextLoads() {
	}
	@Test
	public void Testsave() {
		Userinfo u=new Userinfo();
		//u.setId(null);
		u.setAge(18);
		u.setMoney(1000);
		u.setName("张"+4);
		u.setAddress("beijing"+4);
		this.ur.save(u);
		System.out.print(u);
	}
	
	@Test
	public void Testall() {
		
		
		List<All_info> als = new LinkedList<>();
		List<ordermanage> ls=or.findByReceiverAndAcceptAndSentGreaterThan(1, -1, -1);
		//ordermanage order;
		for(ordermanage order:ls) {
			
			All_info ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			//Userinfo u=ur.findById(order.getSender());
			//ain.add(order.getSender(),u.getName());
			//u=ur.findById(order.getReceiver());
			//ain.add(u.getId(), u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			als.add(ain);
		}
		
		for(All_info ain:als) {
			System.out.println(ain);
		}
	}
	
	
	@Test 
	public void test5() {
		List<Userinfo> list=new ArrayList<Userinfo>();
		for(int i=0;i<5;i++) {
			Userinfo u=new Userinfo();
			u.setId(2);
			u.setAge(18);
			u.setMoney(1500);
			u.setName("张"+i);
			u.setAddress("beijin"+i);
			list.add(u);
			u=ur.save(u);
			
		}
	}
	@Test
	public void testfr() {
		List<Integer> fl=  friend.findfriends(1);
		for(Integer f:fl)
			System.out.println(f);
			//System.out.println();
		
	
	}
	@Test
	public void testdr() {
			Calendar cd=Calendar.getInstance();
		 
		 cd.add(Calendar.DATE, -1);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		
		List<Integer> fl=  or.findSender(0.0,subt,-1);
		for(Integer f:fl)
			System.out.println(f);
			//System.out.println();
		
	
	}
	@Test
	public void tessr() {
		Calendar cd=Calendar.getInstance();
		cd.add(Calendar.DATE, -2);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		List<ordermanage> fl=  or.findSenderinfo(0.0,subt,-1);
		
		for(ordermanage f:fl) {
			System.out.println(f);
		}
	}	
	@Test 
	public void man() {
		Calendar cd=Calendar.getInstance();
		cd.add(Calendar.DATE, -1);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		List<ordermanage> orderlist=  or.findSenderinfo(0.0,subt,-1);
		List<All_info> als=new LinkedList<>();
		for(ordermanage order:orderlist) {
			All_info ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			ain.add(order.getSender(),u.getIsmale(),u.getName());
			u=ur.findById(order.getReceiver());
			ain.add(u.getId(), u.getIsmale(),u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			ain.setId(0);
			als.add(ain);
		}
		//for(All_info test:als) {
			//System.out.println(test);
		//}
		
		
		
		
		
		//int sec_male=0;
		List<List<All_info>> Giants=new LinkedList<>();
		List<All_info> giant=null;
		for(All_info one:als) {
			//if(one.getSim()==sec_male)
			int flag=0;
			if(giant==null) {
				//空 
				giant=new  LinkedList<>();
				//List<All_info> giant=new  LinkedList<>();
				one.setId(one.getSid()+100);
				giant.add(one);
				flag=one.getSid();
				//Giants.add(giant);
				
			}else if(flag==one.getSid()) {
				one.setId(one.getSid());
				giant.add(one);
			}else if(flag!=one.getSid()){
				one.setId(one.getSid());
				Giants.add(giant);
				giant.clear();
				giant.add(one);
				flag=one.getSid();
			}else {
				System.out.println("Cannot find"+one.getSid());
			}
			
		}
		Giants.add(giant);
		
		
		for(List<All_info> g:Giants) {
			System.out.println("G:");
			List<All_info> gi=g;
			for(All_info order:gi) {
				System.out.println(order);
			}
		}
		
	}

	@Test
	public void sqlt() {
		Calendar cd=Calendar.getInstance();
		 
		cd.add(Calendar.DATE, -2);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		
		List<Integer> fl=  or.findSenderList(subt);
		for(Integer f:fl)
			System.out.println(f);
	}
	
	@Test
	public void sqw() {
		int male=0;
		Calendar cd=Calendar.getInstance();
		//cd.setTime(Date.);
		cd.add(Calendar.DATE, -1);
		//cd.set(Calendar.HOUR, 0);
		//cd.set(Calendar.MINUTE, 0);
		////cd.set(Calendar.SECOND, 0);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		Userinfo sender;
		List<Integer> Gaint=or.findSenderList(subt);
		int senderid=0;
		for(Integer sid:Gaint) {
			sender=ur.findById(sid.intValue());
			if(sender.getIsmale()==male) {senderid=sid.intValue(); break;}
		}
		//System.out.println(senderid);
		if(senderid==0) {
			//map.put("canbuy", null);
			//map.put("showtype", 0);
		//
			//map.put("type", "今日男神缺席");
			//return "u_personStar";
		}
		List<All_info> als=new LinkedList<>();
		List<ordermanage> orders=or.getSenderOrder(1, subt);
		All_info ain=new All_info();
		for(ordermanage order:orders) {
			 ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			ain.add(order.getSender(),u.getIsmale(),u.getName());
			u=ur.findById(order.getReceiver());
			ain.add(u.getId(), u.getIsmale(),u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			
			als.add(ain);
			//System.out.println(order);
		}
		
		if(!als.isEmpty()&&!orders.isEmpty()) {
			//Userinfo u=ur.findById(orders.get(0).getSender());
		}
		for(All_info f:als)
			System.out.println(f);
		
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void md5() {
		MD5Utils m=new MD5Utils();
		System.out.println(m.MD5Encode("33445566", "utf-8"));
		
	}
}
	

