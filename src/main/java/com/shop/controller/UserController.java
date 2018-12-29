package com.shop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shop.entity.All_info;
import com.shop.entity.Userinfo;
import com.shop.entity.flower;
import com.shop.entity.friend;
import com.shop.entity.money_of_bigmange;
import com.shop.entity.moneymanage;
import com.shop.entity.ordermanage;

import com.shop.repository.UUserRepository;
import com.shop.repository.flowerRepository;
import com.shop.repository.friendRepository;
import com.shop.repository.money_of_bigmanageRepository;
import com.shop.repository.moneymanageRepository;
import com.shop.repository.ordermanageRepository;
import com.shop.utile.*;
import com.shop.config.*;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource 
	private UUserRepository ur;
	
	@Resource 
	private flowerRepository fr;
	
	@Resource 
	private moneymanageRepository mr;
	
	@Resource 
	private money_of_bigmanageRepository mbr;
	
	@Resource
	private ordermanageRepository or;
	
	@Resource
	private friendRepository friendr;
	//private MultiListQuery MQ=new MultiListQuery();
	private List <All_info> tempa=new LinkedList<>();
	private String temphone;
	private int tempid;
	private Userinfo tempu=new Userinfo();
	private Userinfo tempr=new Userinfo();
	private ordermanage tempo=new ordermanage();
	private MD5Utils m=new MD5Utils();
	@RequestMapping("/reg")
	public String reg(ModelMap map) {
		Userinfo user=new Userinfo();
		user.setPhone("13000000000");
		map.put("userinfo", user);
		map.put("tips", null);
		return "u_reg";
	}

	
	//@ResponseBody
	@RequestMapping(value="/agreeReg",method=RequestMethod.POST)
	public String agreeRegcheck(@ModelAttribute Userinfo user,Model model,ModelMap map) {//ModelMap map
		boolean flag=true;
		String phone=(user.getPhone()) ;
		if(phone=="") {
			flag=false;
			map.put("tips", "手机号不能为空");
		}
		//手机为空 待添加
		String s=user.getSignature();
		if(s=="") {
			flag=false;
			map.put("tips", "验证码不能为空");
		}
		//验证码为空 待添加
		user.setSignature("");
		
		List<Userinfo> ls=ur.findByPhone(phone);
		if(!ls.isEmpty()) {//手机重复 待添加
			Userinfo orgin=ls.get(0);
			if(orgin.getPassword()==null||orgin.getPassword()=="") {
				map.put("tips", "手机号已被注册和送礼，请完成账号的初始化");
				tempu=orgin;
				return "u_initReg";
			}
			
			flag=false;
			map.put("tips", "手机号重复");
		}
		
		if(!flag) return "u_reg";
		user.setPhone(phone);
		model.addAttribute("userinfo", user);
		//map.put("phone", phone);
		temphone=phone;
		return "u_agreeReg";
	}
	
	@RequestMapping(value="/initReg",method=RequestMethod.POST)
	public String initReg(Model model,ModelMap map,
					@RequestParam (value="birthday")String day,@RequestParam (value="npw")String npw,
					@RequestParam (value="rpw")String rpw) throws ParseException {
		
		
		
		boolean flag=true;
		//@SuppressWarnings("static-access")
		//String md5get=m.MD5Encode(opw, "utf8");
		//String realpw=tempu.getPassword();
		//if(realpw==null) realpw="";
		if(npw==""||rpw=="") {
			map.put("tips", "重复输入的密码不能为空");
			flag=false;
		}else if(npw.length()<6||rpw.length()>22) {
			map.put("tips", "密码长度不能小于6位或过长");
			flag=false;
		}else if(npw.compareTo(rpw)!=0) {
			map.put("tips", "2次输入的密码不一致");
			flag=false;
		}else if(day==null||day=="") {
			map.put("tips", "生日不能为空");
			flag=false;
		}
		
		if(!flag) return "u_initReg";
		
		@SuppressWarnings("static-access")
		String nmd5=m.MD5Encode(npw, "utf8");
		tempu.setPassword(nmd5);
		
		Date birthday=Date.valueOf(day);
		
		//CalculateAgeUtil calc=new CalculateAgeUtil();
		
		tempu.setAge(CalculateAgeUtil.getAgeByBirth(birthday.toString()));
		
		
		tempu=ur.save(tempu);
		
		map.put("error","成功加密储存，点击登录");
		
		return "u_login";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/finshReg",method=RequestMethod.POST)
	public String finish(@ModelAttribute Userinfo user,Model model,ModelMap map) throws ParseException {
		boolean flag=true;
		String name,pw,rpw;
		name=user.getName();
		pw=user.getPassword();
		rpw=user.getIcon();
		
		
		if(name==null||name=="") {
			flag=false;
			map.put("tips", "姓名不能为空");
		}else if(pw==null||pw=="") {
			map.put("tips", "密码不能为空");
			flag=false;
		}else if(rpw==null||rpw=="") {
			map.put("tips", "重复输入的密码不能为空");
			flag=false;
		}else if(pw.length()<6||rpw.length()>22) {
			map.put("tips", "密码长度不能小于6位或过长");
			flag=false;
		}else if(pw.compareTo(rpw)!=0) {
			map.put("tips", "2次输入的密码不一致");
			flag=false;
		}else if(user.getBirthday()==null) {
			map.put("tips", "生日不能为空");
			flag=false;
		}
		
		if(!flag) {
			user.setPhone(temphone);
			model.addAttribute("userinfo", user);
			return "u_agreeReg";
		}
		model.addAttribute("list", user);
		user.setIcon("");
		//MD5Utils m=new MD5Utils();
		String md5pw=m.MD5Encode(pw,"utf8");
		user.setPassword(md5pw);
		
		
		Date b=user.getBirthday();
		
		 String birthday=b.toString();
		 Calendar cd=Calendar.getInstance();
		 cd.setTime(b);
		 cd.add(Calendar.DATE, +1);//发现时间少了1 在这里+1  可能是时区关系
		 
		 java.util.Date utilDate=cd.getTime();
		 java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		 
		 //   <label class="error-label" th:if="${errorMap}" th:text="${errorMap['validateCodeError']}"></label>
		 
		user.setBirthday(sqlDate);
		 
		
		
		user.setAge(CalculateAgeUtil.getAgeByBirth(birthday));
		user.setBigmoney(0);
		user.setMoney(0);
		
		if(temphone!=null)
			user.setPhone(temphone);
		
		//user=ur.save(user);
		tempu=user;
		
		//map.put("tips", "注册成功，进入登陆界面");
		
		return "u_IconReg";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/IconReg",method=RequestMethod.POST)
	public String IconReg(Model model,ModelMap map,@RequestParam (value="Icon")MultipartFile file,
						@RequestParam (value="photo")MultipartFile photo ) throws ParseException {
		
		if (!file.isEmpty()) {
			  try {
				if(tempu==null) return "u_agreeReg";
				 Calendar cd=Calendar.getInstance();
				java.util.Date ts=cd.getTime();
				String suffix= "_"+ String.valueOf(ts.getHours())+
			    		 		String.valueOf(ts.getMinutes()) +
			    		 		String.valueOf(ts.getSeconds());
				BufferedOutputStream out = new BufferedOutputStream(
			     new FileOutputStream(new File("src/main/resources/img/Icon/" + temphone + suffix
			    		 + ".jpg")));//保存图片到目录下
			   out.write(file.getBytes());
			   out.flush();
			   out.close();
			   String filename = "/Icon/" + temphone + suffix+ ".jpg";
			   tempu.setIcon(filename);
			  // tempu=ur.save(tempu);
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return "upload error," + e.getMessage();
			  } catch (IOException e) {
			   e.printStackTrace();
			   return "upload error" + e.getMessage();
			  }
		}
		if (!photo.isEmpty()) {
			  try {
				if(tempu==null) return "u_agreeReg";
				 Calendar cd=Calendar.getInstance();
				java.util.Date ts=cd.getTime();
				String suffix= "_"+ String.valueOf(ts.getHours())+
			    		 		String.valueOf(ts.getMinutes()) +
			    		 		String.valueOf(ts.getSeconds());
				BufferedOutputStream out = new BufferedOutputStream(
			     new FileOutputStream(new File("src/main/resources/img/photo/" + temphone + suffix
			    		 + ".jpg")));//保存图片到目录下
			   out.write(file.getBytes());
			   out.flush();
			   out.close();
			   String filename = "/photo/" + temphone + suffix+ ".jpg";
			   tempu.setPhoto(filename);
			  // tempu=ur.save(tempu);
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return "upload error," + e.getMessage();
			  } catch (IOException e) {
			   e.printStackTrace();
			   return "upload error" + e.getMessage();
			  }
		}
		tempu=ur.save(tempu);
		return "u_login";
	}

	
	@RequestMapping("/login")
	public String login( Model model,ModelMap map) {
		//https://blog.csdn.net/qq_17505335/article/details/79145919
		return "u_login";
	}
	
	@PostMapping("/loginPost")
    public String loginPost(String account,
            String password, HttpSession session,ModelMap map) {
        
		//Map<String, Object> map = new HashMap<>();
        List<Userinfo> ls;
		
        ls=ur.findByPhone(account);
        MD5Utils m=new MD5Utils();
		//String md5pw=m.MD5Encode(pw,"utf8");
        String s=password;
		if(ls.isEmpty()) {
			map.put("error", "手机错误");
            return "u_login";
			
		}
		Userinfo u=ls.get(0);
		@SuppressWarnings("static-access")
		String md5get=m.MD5Encode(s, "utf8");
		if(!(password.length()>2||u.getPassword().equals(md5get)||(u.getPassword().equals(s)))) {
				map.put("success", false);
	            map.put("error", "密码错误");
	            return "u_login";
            }
        
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        map.put("error", "test");
        tempu=ls.get(0);
        map.put("user", ls.get(0));
        tempid=ls.get(0).getId();
        temphone=account;
        return "index";
    }
	@RequestMapping("/index")
	public String index(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
			
		return "index";
	}
	@RequestMapping("/send")
	public String send(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		if(tempu!=null) 
			map.put("user", tempu);
			map.put("exist", false);
			map.put("vali", "信息不完整,无法送礼");
		return "u_send_name";
	}
	
	
	@RequestMapping(value="/findUser",method=RequestMethod.POST)
	public String findUser(Model model,ModelMap map,
					@RequestParam (value="uname")String uname,
					@RequestParam (value="utel")String utel,
					@RequestParam (value="uaddress")String uaddress) throws ParseException {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		List<Userinfo> ls = null;
		if(uname!=null) {
			ls	=ur.findByName(uname);
			if(!ls.isEmpty())
				tempr=ls.get(0);
		}
		
		//else ls.add(ur.findById(Integer.valueOf(uid)));
		if(ls.isEmpty()) {//no such user
			map.put("exist", false);
			
			map.put("receiver_name", uname);
			map.put("receiver_address", uaddress);
			map.put("receiver_phone", utel);
			Userinfo receiver=new Userinfo();
			receiver.setAddress(uaddress);
			receiver.setName(uname);
			receiver.setPhone(utel);
			if(uname==""||utel==""||uaddress=="")
				map.put("vali", "信息不完整,无法送礼");
			else
				map.put("vali", null);
			tempr=receiver;
		}
		else if(ls.get(0).getPhone().equals(temphone)) {
			map.put("exist", false);
			map.put("vali", "目标为自己,无法送礼");
		}else
			map.put("exist", true);
		
		
		map.put("reslist", ls);
		return "u_send_name";
	}
	
	@RequestMapping(value="gotoshop")
	public String gotoshop(Model model,ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		List<flower> items=fr.findByTypeAndIdGreaterThan(1, 2);

		map.put("items", items);
		map.put("canbuy", null);
		map.put("showtype", 1);
		map.put("type", "礼品");
		return "u_shop";
	}
	@RequestMapping(value="gotoshop",method=RequestMethod.POST)
	public String gotoshopSelect(Model model,ModelMap map,
					@RequestParam (value="gifttype")String showtype) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		List<flower> items;
		int type=Integer.valueOf(showtype);
		if(type>3||type<1) type=1;
		if(type==3)
			items=fr.findByTypeAndIdGreaterThan(3, 2);
			else
			items=fr.findByType(type);
		map.put("items", items);
		
		map.put("showtype", type);
		switch (type){
		case 1:
			map.put("type", "礼品"); break;
		case 2:
			map.put("type", "实物鲜花");break;
		case 3:
			map.put("type", "虚拟鲜花");break;
		default:
			map.put("type", "找不到");
		}
		
		
		map.put("canbuy", null);
		return "u_shop";
	}
	
	@RequestMapping(value="confirmSend",method=RequestMethod.POST)
	public String confirmSend(Model model,ModelMap map,
					@RequestParam (value="id")String fid,
					@RequestParam (value="quantity")String fqt) {
		Userinfo receiver=tempr;
		Userinfo sender=tempu;
		if(receiver==null||receiver.getPhone()==null||sender==null)
		{
			sender=ur.findById(tempid);
			if(receiver==null||receiver.getPhone()==null||sender!=null)
				return "u_login";
		}
		
		if(fid.length()==0) fid="0";
		int flowerid=Integer.valueOf(fid);
		
		flower item=fr.findById(flowerid);
		if(item==null||item.getName()==null||fqt==null||fqt=="") {
			List<flower> items=fr.findByTypeAndIdGreaterThan(1, 2);
			map.put("items", items);
			map.put("showtype", 1);
			map.put("type", "礼品");
			map.put("canbuy", "无此商品");
			return "u_shop";
		}
		
		int quantity=Integer.valueOf(fqt);
		
		double value=quantity*item.getPrice();
		
		Timestamp subt= new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		ordermanage order=new ordermanage();
		order.setFlowerid(flowerid);
		order.setQuantity(quantity);
		order.setValue(value);
		order.setReceiver(receiver.getId());
		order.setSender(sender.getId());
		order.setSubmit_time(subt);
		order.setAccept(-1);
		order.setSent(-1);
		//order=or.save(order);
		tempo=order;
		int mymoney=sender.getMoney();
		if(mymoney<value)
			map.put("canbuy","余额不足，无法购买");
		else map.put("canbuy",null);
			
		map.put("order", order);
		map.put("mymoney", mymoney);
		map.put("item", item);
		int type=item.getType();
		switch (type){
		case 1:
			map.put("type", "礼品"); break;
		case 2:
			map.put("type", "实物鲜花");break;
		case 3:
			map.put("type", "虚拟鲜花");break;
		default:
			map.put("type", "找不到");
		}
		map.put("receiver", receiver);
		return "u_send_confirm";
	}
	@RequestMapping(value="pay",method=RequestMethod.POST)
	public String pay(Model model,ModelMap map,
					@RequestParam (value="memo")String memo) {
		Userinfo payer=tempu;
		
		ordermanage order=tempo;
		if(payer==null||order==null||tempr==null) {
			map.put("help","登陆过期，支付失败");
			return "u_help";
		}
		double cost=order.getValue();
		int left=tempu.getMoney()-(int)cost;
		tempu.setMoney(left);
		order.setFinish_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		if(memo==null||memo.equals("")||memo.equals("可添加留言"))
			order.setMemo("这位大佬很忙，没有留言呢 ");
		else order.setMemo(memo);
		order.setSent(0);
		
		flower flowtype=fr.findById(order.getFlowerid());
		if(flowtype!=null&&flowtype.getType()==3) {
			tempr.setMoney((int)cost+tempr.getMoney());
			order.setSent(1);
			ur.save(tempr);
		
		}
		or.save(order);
		tempo=null;
		tempu=ur.save(tempu);
		tempid=tempu.getId();
		tempr=null;
		
		String help="支付成功，剩余花芽："+left;
		map.put("help",help);
		return "u_help";
	}
	
	@RequestMapping("/thank")
	public String thank(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		List<All_info> als = new LinkedList<>();
		List<ordermanage> ls=new LinkedList<>();
		ls=or.findByReceiverAndAcceptAndSentGreaterThan(tempid, -1, -1);
		//ordermanage order;
		for(ordermanage order:ls) {
			
			//System.out.println(order);
			All_info ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			ain.add(order.getSender(),u.getIsmale(),u.getName());
			u=ur.findById(order.getReceiver());
			ain.add(u.getId(), u.getIsmale(),u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			
			als.add(ain);
		}
		tempa=als;
		map.put("orders", als);
		map.put("canbuy", null);
		map.put("showtype", -1);
		map.put("type", "待回礼");
		
		return "u_thank";
	}
	
	@RequestMapping(value="thank_oid",method=RequestMethod.POST)
	public String thankByOid(Model model,ModelMap map,
					@RequestParam (value="oid")String oid) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		if(oid==null||oid=="") oid="-1";
		int orderid=Integer.valueOf(oid);
		ordermanage o=or.findById(orderid);
	
		if(o==null||o.getAccept()>-1||o.getReceiver()!=tempid) {
			map.put("orders", tempa);
			
			map.put("canbuy", oid+"订单不存在或已完成回礼");
			map.put("showtype", -1);
			map.put("type", "待回礼");
			return "u_thank";
		}
		tempo=o;
		List<flower> items=new LinkedList<>();
		items.add(fr.findById(1));
		items.add(fr.findById(2));
		map.put("items", items);
		map.put("showtype", 3);
		map.put("type", "虚拟鲜花");
		
		
		
		return "u_thank_fr";
	}
	
	@RequestMapping(value="thank_finish",method=RequestMethod.POST)
	public String than_finish(Model model,ModelMap map,
					@RequestParam (value="thanktype")String ac) {
		ordermanage o=tempo;
		int myid=o.getReceiver();
		int sid=o.getSender();
		o.setAccept(Integer.valueOf(ac));
		String suffix="";
		if(ac.equals("1")) {//unband friend to be
			 
			
			List<Integer> fs= friendr.findfriends(myid);
			boolean isf=false;
			for(Integer i:fs) if(i.intValue()==sid) isf=true;
			if(!isf) {
				friend f1=new friend(o.getReceiver(),o.getSender());
				suffix=",成功和对方建立为好友，可以去我的查看对方信息";
				friendr.save(f1);
			}
		}
		
		or.save(o);
		tempo=null;
		String help=o.getId()+"订单回礼成功"+suffix;
		map.put("help",help);
		return "u_help";
	}
	
	@RequestMapping("/man")
	public String GodFa(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		int male=1;
		Calendar cd=Calendar.getInstance();
		//cd.setTime(Date.);
		cd.add(Calendar.DATE, -1);
		
		cd.set(Calendar.HOUR, 16);
		cd.set(Calendar.MINUTE, 0);
		cd.set(Calendar.SECOND, 0);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		Userinfo sender;
		List<Integer> Gaint=or.findSenderList(subt);
		int senderid=0;
		for(int sid:Gaint) {
			sender=ur.findById(sid);
			if(sender.getIsmale()==male) {senderid=sid; break;}
		}
		if(senderid==0) {
			map.put("canbuy", null);
			map.put("showtype", 0);
		
			map.put("type", "今日男神缺席");
			return "u_personStar";
		}
		List<All_info> als=new LinkedList<>();
		List<ordermanage> orders=or.getSenderOrder(senderid, subt);
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
		}
		List<All_info> alsr=new LinkedList<>();
		List<ordermanage> ordersr=or.getReceiverOrder(senderid, subt);
		//All_info ainr=new All_info();
		
		for(ordermanage order:ordersr) {
			 ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			ain.add(order.getSender(),u.getIsmale(),u.getName());
			u=ur.findById(order.getReceiver());
			ain.add(u.getId(), u.getIsmale(),u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			
			alsr.add(ain);
		}
		
		
		
		
		if(!als.isEmpty()&&!orders.isEmpty()) {
			Userinfo u=ur.findById(orders.get(0).getSender());
		
		if(u!=null)
		map.put("u", u);
		else map.put("u", null);
		map.put("orders", als);
		map.put("geters", alsr);
		map.put("canbuy", "数据统计时间截至今日23:59");
		}else {
			map.put("canbuy", null);
			
		}
		map.put("showtype", 1);
		map.put("type", "今日男神");
		
		
		return "u_personStar";
	}
	@RequestMapping("/woman")
	public String Godness(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		int male=0;
		Calendar cd=Calendar.getInstance();
		//cd.setTime(Date.);
		cd.add(Calendar.DATE, -1);
		
		cd.set(Calendar.HOUR, 16);
		cd.set(Calendar.MINUTE, 0);
		cd.set(Calendar.SECOND, 0);
		Timestamp subt= new Timestamp(cd.getTimeInMillis());
		Userinfo sender;
		List<Integer> Gaint=or.findSenderList(subt);
		int senderid=0;
		for(int sid:Gaint) {
			sender=ur.findById(sid);
			if(sender.getIsmale()==male) {senderid=sid; break;}
		}
		if(senderid==0) {
			map.put("canbuy", null);
			map.put("showtype", 0);
		
			map.put("type", "今日女神缺席");
			return "u_personStar";
		}
		List<All_info> als=new LinkedList<>();
		List<ordermanage> orders=or.getSenderOrder(senderid, subt);
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
		}
		List<All_info> alsr=new LinkedList<>();
		List<ordermanage> ordersr=or.getReceiverOrder(senderid, subt);
		//All_info ainr=new All_info();
		
		for(ordermanage order:ordersr) {
			 ain=new All_info(order.getSender(),order.getFlowerid(),order.getId(),order.getFinish_time(),
					order.getQuantity(),order.getValue(),order.getSent(),order.getAccept(),order.getMemo());
			Userinfo u=ur.findById(order.getSender());
			ain.add(order.getSender(),u.getIsmale(),u.getName());
			u=ur.findById(order.getReceiver());
			ain.add(u.getId(), u.getIsmale(),u.getName(), u.getAddress());
			
			flower f=fr.findById(order.getFlowerid());
			ain.add(f.getId(),f.getName(),f.getPrice(),f.getPic(),f.getMeans(),f.getType());
			
			alsr.add(ain);
		}
		if(!als.isEmpty()&&!orders.isEmpty()) {
			Userinfo u=ur.findById(orders.get(0).getSender());
		
		
		map.put("u", u);
		map.put("orders", als);
		map.put("geters", alsr);
		map.put("canbuy", "数据统计时间截至今日23:59");
		}else {
			map.put("canbuy", null);
			
		}
		map.put("showtype", 0);
		map.put("type", "今日女神");
		
		
		return "u_personStar";
	}

	@RequestMapping("/my")
	public String myRecords(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		List<All_info> als=new LinkedList<>();
		List<ordermanage> orders=or.getSenderOrderByTimeDesc(tempid);
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
		}
		
		tempa=als;
		map.put("orders", als);
		map.put("canbuy", "默认我的最新送礼订单");
		map.put("showtype", -1);
		map.put("type", "送出的订单");
		
		return "u_personinfo";
	}
	@RequestMapping(value="my",method=RequestMethod.POST)
	public String my_select(Model model,ModelMap map,
					@RequestParam (value="recordtype")String recordtype,
					@RequestParam (value="ranktype")String ranktype,
					@RequestParam (value="isDesc")String isDesc) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		if(recordtype==null||recordtype.equals("")) recordtype="1";//value=1 >送出的订单
		if(ranktype==null||ranktype.equals("")) ranktype="1";//value=1 >时间
		if(isDesc==null||isDesc.equals("")) isDesc="1";
		int sr=Integer.valueOf(recordtype);
		int rk=Integer.valueOf(ranktype);
		int Desc=Integer.valueOf(isDesc);
		List<All_info> als=new LinkedList<>();
		List<ordermanage> orders;
		String sDesc,sctype="送出的礼物";
		sDesc="已选择";
		if(sr==1) {
			sDesc+="送出的";
			map.put("isr", false);
			
			if(Desc==0) {
				switch(rk) {
				case 1: orders=or.getSenderOrderByTime(tempid);sDesc+="时间";break;
				case 2: orders=or.getSenderOrderByValue(tempid);sDesc+="金额";break;
				default:orders=or.getSenderOrderByQuan(tempid);sDesc+="数量";break;
				}
				sDesc+="降序";
			}else {
				switch(rk) {
				case 1: orders=or.getSenderOrderByTimeDesc(tempid);sDesc+="时间";break;
				case 2: orders=or.getSenderOrderByValueDesc(tempid);sDesc+="金额";break;
				default:orders=or.getSenderOrderByQuanDesc(tempid);sDesc+="数量";break;
				}
				sDesc+="升序";
			}
		}else {
			sctype="收到的礼物";
			sDesc+="收到的";
			map.put("isr", true);
			
			if(Desc==0) {
				switch(rk) {
				case 1: orders=or.getReceiverOrderByTime(tempid);sDesc+="时间";break;
				case 2: orders=or.getReceiverOrderByValue(tempid);sDesc+="金额";break;
				default:orders=or.getReceiverOrderByQuan(tempid);sDesc+="数量";break;
				}
				sDesc+="降序";
			}else {
				switch(rk) {
				case 1: orders=or.getReceiverOrderByTimeDesc(tempid);sDesc+="时间";break;
				case 2: orders=or.getReceiverOrderByValueDesc(tempid);sDesc+="金额";break;
				default:orders=or.getReceiverOrderByQuanDesc(tempid);sDesc+="数量";break;
				}
				sDesc+="升序";
			}
		}
		
		sDesc+="的订单";
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
		}
		
		
		
		//if(isDesc==null) sDesc="false";
		//else  sDesc="check";
		map.put("orders", als);
		map.put("canbuy", sDesc);
		map.put("showtype", -1);
		map.put("type", sctype);
		return "u_personinfo";
	}
	
	@RequestMapping(value="/{id}")
	public String gotoid(@PathVariable("id") Integer id,ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		if(id.intValue()==tempid) {
		 map.put("user", tempu);
		 return "index";
		}
		
		
		List<Integer> fs= friendr.findfriends(tempid);
		boolean isf=false;
		for(Integer i:fs) if(i.equals(id)) isf=true;
		
		if(!isf) map.put("friendtype",false);
		else map.put("friendtype",true);
			
			Userinfo friend=ur.findById(id.intValue());
			if(friend==null) {
				map.put("help", "不要尝试搜索无关用户哦！");
				return "u_help";
			}
			map.put("user", friend);
			
		
		
		
		return "u_myfriend";
	}
	
	@RequestMapping("/popup")
	public String gopurchase(ModelMap map)
	{
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		int money,bigm;
		bigm=tempu.getBigmoney();
		money=tempu.getMoney();
		map.put("left", "当前剩余"+money+"花芽，"+bigm+"花冠");
		map.put("tips", "其中10元可充值100花芽、1500花芽可变为1花冠、1花冠可提现5元");
		
		
		return "u_popup";
	}
	@RequestMapping(value="/topup",method=RequestMethod.POST)
	public String topup(Model model,ModelMap map,
					@RequestParam (value="buyreal")String buyreal) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		if(buyreal=="") {
			map.put("help", "购买失败");
		}else {
			int num=Integer.valueOf(buyreal);
			Calendar cd=Calendar.getInstance();
			//cd.setTime(Date.);
			//cd.add(Calendar.DATE, -1);
			Timestamp subt= new Timestamp(cd.getTimeInMillis());
			moneymanage m=new moneymanage();
			int big=num*10;
			int mymoney=tempu.getMoney();
			tempu.setMoney(mymoney+big);
			m.setPrice(num);
			m.setGotmoney(big);
			m.setUid(tempid);
			m.setTime(subt);
			mr.save(m);
			
			
			tempu=ur.save(tempu);
			map.put("help", "购买成功,剩余"+(mymoney+big)+"花芽");
			
			
		}
		
		
		return "u_help";
	}
	@RequestMapping(value="/transbig",method=RequestMethod.POST)
	public String transbig(Model model,ModelMap map,
					@RequestParam (value="transbig")String buyreal) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		if(buyreal=="") {
			map.put("help", "转换失败");
		}else {
			int num=Integer.valueOf(buyreal);
			//Calendar cd=Calendar.getInstance();
			//cd.setTime(Date.);
			//cd.add(Calendar.DATE, -1);
			//Timestamp subt= new Timestamp(cd.getTimeInMillis());
			int big=num/1500;
			int mymoney=tempu.getMoney();
			if(mymoney<num) {
				map.put("help", "转换失败,花芽数量不足");
				return "u_help";
			}
			tempu.setMoney(mymoney-num);
			
			tempu.setBigmoney(tempu.getBigmoney()+big);
			tempu=ur.save(tempu);
			map.put("help", "转换成功,剩余"+(mymoney-num)+"花芽");
		}
		
		
		return "u_help";
	}
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public String withdraw(Model model,ModelMap map,
					@RequestParam (value="getreal")String buyreal) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		if(buyreal=="") {
			map.put("help", "提现失败");
		}else {
			int num=Integer.valueOf(buyreal);
			Calendar cd=Calendar.getInstance();
			//cd.setTime(Date.);
			//cd.add(Calendar.DATE, -1);
			Timestamp subt= new Timestamp(cd.getTimeInMillis());
			int real=num*5;
			int mybmoney=tempu.getBigmoney();
			if(mybmoney<num) {
				map.put("help", "转换失败,花冠数量不足");
				return "u_help";
			}
			tempu.setMoney(mybmoney-num);
			
			money_of_bigmange bm=new money_of_bigmange();
			bm.setGotrealmoney(real);
			bm.setLeftBigmoney(mybmoney-num);
			bm.setTime(subt);
			bm.setUid(tempid);
			mbr.save(bm);
			tempu=ur.save(tempu);
			map.put("help", "提现成功,剩余"+(mybmoney-num)+"花冠");
			
			
		}
		
		
		return "u_help";
	}
	
	@RequestMapping("/changeinfo")
	public String changeinfo(ModelMap map) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		map.put("tips",null);
		
		return "u_changeinfo";
	}
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public String cpw(Model model,ModelMap map,
					@RequestParam (value="opw")String opw,@RequestParam (value="npw")String npw,
					@RequestParam (value="rpw")String rpw) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		
		boolean flag=true;
		@SuppressWarnings("static-access")
		String md5get=m.MD5Encode(opw, "utf8");
		String realpw=tempu.getPassword();
		if(realpw==null) realpw="";
		if(opw==null||opw=="") {
			map.put("tips", "密码不能为空");
			//flag=false;
		}else if(npw==""||rpw=="") {
			map.put("tips", "重复输入的密码不能为空");
			flag=false;
		}else if(npw.length()<6||rpw.length()>22) {
			map.put("tips", "密码长度不能小于6位或过长");
			flag=false;
		}else if(npw.compareTo(rpw)!=0) {
			map.put("tips", "2次输入的密码不一致");
			flag=false;
		}else if(!(realpw.equals(md5get)||(realpw.equals(opw)   ))) {
			map.put("tips", "密码错误");
			flag=false;
		}
		if(!flag) return "u_changeinfo";
		
		@SuppressWarnings("static-access")
		String nmd5=m.MD5Encode(npw, "utf8");
		tempu.setPassword(nmd5);
		tempu=ur.save(tempu);
		
		map.put("tips","成功加密储存");
		
		return "u_changeinfo";
	}
	@RequestMapping(value="/changenickname",method=RequestMethod.POST)
	public String cnn(Model model,ModelMap map,
					@RequestParam (value="nickname")String opw) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		tempu.setNickname(opw);
		tempu=ur.save(tempu);
		map.put("tips","成功修改");
		
		return "u_changeinfo";
	}
	@RequestMapping(value="/changesign",method=RequestMethod.POST)
	public String csn(Model model,ModelMap map,
					@RequestParam (value="sign")String opw) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		tempu.setSignature(opw);
		tempu=ur.save(tempu);
		map.put("tips","成功修改");
		
		return "u_changeinfo";
	}
	@RequestMapping(value="/changeads",method=RequestMethod.POST)
	public String cad(Model model,ModelMap map,
					@RequestParam (value="ads")String opw) {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		tempu.setAddress(opw);
		tempu=ur.save(tempu);
		map.put("tips","成功修改");
		
		return "u_changeinfo";
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/changeIcon",method=RequestMethod.POST)
	public String changeIcon(Model model,ModelMap map,@RequestParam (value="Icon")MultipartFile file) throws ParseException {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		
		if (!file.isEmpty()) {
			  try {
				if(tempu==null) return "u_agreeReg";
				 Calendar cd=Calendar.getInstance();
				java.util.Date ts=cd.getTime();
				String suffix= "_"+ String.valueOf(ts.getHours())+
			    		 		String.valueOf(ts.getMinutes()) +
			    		 		String.valueOf(ts.getSeconds());
				BufferedOutputStream out = new BufferedOutputStream(
			     new FileOutputStream(new File("src/main/resources/img/Icon/" + temphone + suffix
			    		 + ".jpg")));//保存图片到目录下
			   out.write(file.getBytes());
			   out.flush();
			   out.close();
			   String filename = "/Icon/" + temphone + suffix+ ".jpg";
			   tempu.setIcon(filename);
			  // tempu=ur.save(tempu);
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return "upload error," + e.getMessage();
			  } catch (IOException e) {
			   e.printStackTrace();
			   return "upload error" + e.getMessage();
			  }
		}
		
		tempu=ur.save(tempu);
		map.put("tips","成功修改");
		
		return "u_changeinfo";
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/changephoto",method=RequestMethod.POST)
	public String changephoto(Model model,ModelMap map,@RequestParam (value="photo")MultipartFile file) throws ParseException {
		if(temphone!=null&&temphone.length()!=0) {
			List<Userinfo> ls;
			ls=ur.findByPhone(temphone);
			if(!ls.isEmpty())
				map.put("user", ls.get(0));
			else  return "u_login";
		}else return "u_login";
		
		if (!file.isEmpty()) {
			  try {
				if(tempu==null) return "u_agreeReg";
				 Calendar cd=Calendar.getInstance();
				java.util.Date ts=cd.getTime();
				String suffix= "_"+ String.valueOf(ts.getHours())+
			    		 		String.valueOf(ts.getMinutes()) +
			    		 		String.valueOf(ts.getSeconds());
				BufferedOutputStream out = new BufferedOutputStream(
			     new FileOutputStream(new File("src/main/resources/img/photo/" + temphone + suffix
			    		 + ".jpg")));//保存图片到目录下
			   out.write(file.getBytes());
			   out.flush();
			   out.close();
			   String filename = "/photo/" + temphone + suffix+ ".jpg";
			   tempu.setIcon(filename);
			  // tempu=ur.save(tempu);
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			   return "upload error," + e.getMessage();
			  } catch (IOException e) {
			   e.printStackTrace();
			   return "upload error" + e.getMessage();
			  }
		}
		
		tempu=ur.save(tempu);
		map.put("tips","成功修改");
		
		return "u_changeinfo";
	}
	
	
	@RequestMapping("/error")
	public String error() {
		return "u_error";
	}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
       temphone="";
        return "u_login";
    }

	
	@RequestMapping("/add")
	public String getUserList(ModelMap map) {
		List<Userinfo> list=new ArrayList<Userinfo>();
		for(int i=0;i<5;i++) {
			Userinfo u=new Userinfo();
			u.setId(i+1);
			u.setAge(18);
			u.setMoney(1000);
			u.setName("张"+i);
			u.setAddress("beijin"+i);
			//list.add(u);
			//u=ur.save(u);
			
		}
		
		
		
		
		map.addAttribute("list",list);
		return "index";
	}
}
