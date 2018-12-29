package com.shop.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shop.config.WebSecurityConfig;
import com.shop.entity.Admininfo;
//import com.shop.entity.ordermanage;
import com.shop.repository.AAdminRepository;
import com.shop.repository.flowerRepository;
import com.shop.repository.moneymanageRepository;
import com.shop.repository.ordermanageRepository;
import com.shop.utile.MD5Utils;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource 
	private AAdminRepository ad;
	
	@Resource 
	private flowerRepository fr;
	
	@Resource 
	private moneymanageRepository mr;
	
	@Resource
	private ordermanageRepository or;
	
	//private MultiListQuery MQ=new MultiListQuery();
	private String temphone;
	public int tempid;
	private Admininfo tempu=new Admininfo();
	//private Admininfo tempr=new Admininfo();
	//private ordermanage tempo=new ordermanage();
	
	@RequestMapping("/reg")
	public String reg(ModelMap map) {
		Admininfo admin=new Admininfo();
		admin.setPhone("13000000000");
		map.put("admininfo", admin);
		
		return "a_reg";
	}

	
	//@ResponseBody
	@RequestMapping(value="/agreeReg",method=RequestMethod.POST)
	public String agreeRegcheck(@ModelAttribute Admininfo admin,Model model,ModelMap map) {//ModelMap map
		boolean flag=true;
		String phone=(admin.getPhone()) ;
		if(phone=="") {
			flag=false;
		}
		//手机为空 待添加
		
		List<Admininfo> ls=ad.findByPhone(phone);
		if(!ls.isEmpty()) {//手机重复 待添加
			flag=false;
		}
		
		if(!flag) return "a_reg";
		admin.setPhone(phone);
		model.addAttribute("admininfo", admin);
		//map.put("phone", phone);
		temphone=phone;
		return "a_agreeReg";
	}
	
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/finshReg",method=RequestMethod.POST)
	public String finish(@ModelAttribute Admininfo admin,Model model,ModelMap map) throws ParseException {
		boolean flag=true;
		String name,pw,rpw;
		name=admin.getName();
		pw=admin.getPassword();
		rpw=admin.getIcon();
		
		if(name==null||name=="") {
			flag=false;
		}else if(pw==null||pw=="") {
			flag=false;
		}else if(rpw==null||rpw=="") {
			flag=false;
		}else if(pw.length()<6||rpw.length()>22) {
			flag=false;
		}else if(pw.compareTo(rpw)!=0) {
			flag=false;
		}
		
		if(!flag) {
			admin.setPhone(temphone);
			model.addAttribute("admininfo", admin);
			return "a_agreeReg";
		}
		model.addAttribute("list", admin);
		admin.setIcon("");
		 
		 //   <label class="error-label" th:if="${errorMap}" th:text="${errorMap['validateCodeError']}"></label>
		 
		//gotphone=map.get("phone").toString();
		if(temphone!=null)
			admin.setPhone(temphone);
		
		//admin=ad.save(admin);
		tempu=admin;
		map.put("tips", "注册成功，进入登陆界面");
		
		return "a_IconReg";
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
			   String filename = "\\Icon\\" + temphone + suffix+ ".jpg";
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
			   String filename = "\\photo\\" + temphone + suffix+ ".jpg";
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
		tempu=ad.save(tempu);
		return "u_login";
	}
	
	
	@RequestMapping("/login")
	public String login( Model model,ModelMap map) {
		//https://blog.csdn.net/qq_17505335/article/details/79145919
		
		
		
		return "a_login";
	}
	
	@PostMapping("/loginPost")
    public String loginPost(String account,
            String password, HttpSession session) {
        
		Map<String, Object> map = new HashMap<>();
        List<Admininfo> ls;
		
        ls=ad.findByPhone(account);
        MD5Utils m=new MD5Utils();
        String s=password;
        if (ls.isEmpty()) {
        	map.put("error", "TEL错误");
        	return "a_login";       	
        }
        	
        	
        	
    
			Admininfo u=ls.get(0);
			
			@SuppressWarnings("static-access")
			String md5get=m.MD5Encode(s, "utf8");
			
			if(!(u.getPassword().equals(md5get)||(u.getPassword().equals(s)   ))) {
				map.put("success", false);
	            map.put("error", "密码错误");
	            return "a_login";
            }
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        
        map.put("error", "test");
        tempu=u;
        
        map.put("user", u);
        map.put("name", u.getName());
        tempid=ls.get(0).getId();
        temphone=account;
        return "a_index";
        
    }
	
	@RequestMapping("/error")
	public String error() {
		return "a_error";
	}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        temphone="";
        return "a_login";
    }

	
	@RequestMapping("/add")
	public String getAdminList(ModelMap map) {
		List<Admininfo> list=new ArrayList<Admininfo>();
		for(int i=0;i<5;i++) {
			Admininfo a=new Admininfo();
			a.setId(i+1);
			a.setAge(18);
			a.setName("张"+i);
			
		}
		
		
		
		
		map.addAttribute("list",list);
		return "index";
	}
	
	@RequestMapping("/img/a_login.jpg")
    @ResponseBody
    public String jpglogin() {
        return "<img src=\"img/a_login.jpg\">";
    }
	@RequestMapping("/img/welcome.jpg")
    @ResponseBody
    public String jpgwelcome() {
        return "<img src=\"img/welcomen.jpg\">";
    }
	
	@RequestMapping("/img/a_menu.jpg")
    @ResponseBody
    public String jpgmenu() {
        return "<img src=\"img/a_menu.jpg\">";
    }
	
	@RequestMapping("/modify")
	public String modify() {
		return "a_modify";
	}
	
	@RequestMapping("/delete")
	public String delete() {
		return "a_delete";
	}
	
	@RequestMapping("/modsearch")
	public String modsearch() {
		return "a_modsearch";
	}
	
	@RequestMapping("/menu")
	public String menu() {
		return "a_menu";
	}
	
	@RequestMapping("/messageManage")
	public String messageManage() {
		return "a_messageManage";
	}
	
}
