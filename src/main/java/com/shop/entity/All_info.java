package com.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 多表查询时用来构造集合的实体类
 * 
 * @author slate
 *
 */
@SuppressWarnings("serial")
public class All_info implements Serializable{
	
	private int id;
	private int sid;
	private int rid;
	private int fid;
	private int oid;
	private String sname;
	private String rname;
	private String fname;
	private int sim;

	private int rim;
	private double fprice;
	private String fpic;
	private String fmeans;
	private int ftype;
	private String fstype;
	private String raddress;
	private Timestamp ofinish_time;
	private int oquantity;
	private double ovalue;
	private int ois_sent;
	private int ois_accept;
	private String oss;
	private String osa;
	private String memo;
	private String[] type= {"default","礼品","实物鲜花","虚拟鲜花"};
	private String[] OSS= {"未支付","运送中","已派送","虚拟鲜花"};
	private String[] OSA= {"未回礼","含羞草","向阳花","虚拟鲜花"};
	public All_info() {
		
	}
	public void add(int sid,int sim, String sname) {
		//super();
		this.sid = sid;
		this.sim = sim;
		
		this.sname = sname;
	}
	
	

	public void add(int rid,int rim, String rname, String raddress) {
		//super();
		this.rid = rid;
		this.rim = rim;
		this.rname = rname;
		this.raddress = raddress;
	}
	public void add(int fid, String fname, double fprice, String fpic, String fmeans, int ftype) {
		//super();
		this.fid = fid;
		this.fname = fname;
		this.fprice = fprice;
		this.fpic = fpic;
		this.fmeans = fmeans;
		this.ftype = ftype;
		this.fstype=type[ftype];
	}
	
	public All_info(int sid, int fid, int oid, Timestamp ofinish_time, int oquantity, double ovalue, int ois_sent,
			int ois_accept, String memo) {
		super();
		this.sid = sid;
		this.fid = fid;
		this.oid = oid;
		this.ofinish_time = ofinish_time;
		this.oquantity = oquantity;
		this.ovalue = ovalue;
		this.ois_sent = ois_sent;
		this.oss=OSS[ois_sent+1];
		this.osa=OSA[ois_accept+1];
		
		this.ois_accept = ois_accept;
		this.memo = memo;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getSim() {
		return sim;
	}
	public void setSim(int sim) {
		this.sim = sim;
	}
	public int getRim() {
		return rim;
	}
	public void setRim(int rim) {
		this.rim = rim;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFstype() {
		return fstype;
	}
	public void setFstype(String fstype) {
		this.fstype = fstype;
	}
	public double getFprice() {
		return fprice;
	}

	public void setFprice(double fprice) {
		this.fprice = fprice;
	}

	public String getFpic() {
		return fpic;
	}

	public void setFpic(String fpic) {
		this.fpic = fpic;
	}

	public String getFmeans() {
		return fmeans;
	}

	public void setFmeans(String fmeans) {
		this.fmeans = fmeans;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public Timestamp getOfinish_time() {
		return ofinish_time;
	}

	public void setOfinish_time(Timestamp ofinish_time) {
		this.ofinish_time = ofinish_time;
	}

	public int getOquantity() {
		return oquantity;
	}

	public void setOquantity(int oquantity) {
		this.oquantity = oquantity;
	}

	public double getOvalue() {
		return ovalue;
	}

	public void setOvalue(double ovalue) {
		this.ovalue = ovalue;
	}

	public int getOis_sent() {
		return ois_sent;
	}

	public void setOis_sent(int ois_sent) {
		this.ois_sent = ois_sent;
	}

	public int getOis_accept() {
		return ois_accept;
	}

	public void setOis_accept(int ois_accept) {
		this.ois_accept = ois_accept;
	}

	public String getOss() {
		return oss;
	}
	public void setOss(String oss) {
		this.oss = oss;
	}
	public String getOsa() {
		return osa;
	}
	public void setOsa(String osa) {
		this.osa = osa;
	}
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "All_info [id=" + id + ", sid=" + sid + ", rid=" + rid + ", fid=" + fid + ", oid=" + oid + ", sname="
				+ sname + ", rname=" + rname + ", fname=" + fname + ", fprice=" + fprice + ", fpic=" + fpic
				+ ", fmeans=" + fmeans + ", ftype=" + ftype + ", raddress=" + raddress + ", ofinish_time="
				+ ofinish_time + ", oquantity=" + oquantity + ", ovalue=" + ovalue + ", ois_sent=" + ois_sent
				+ ", ois_accept=" + ois_accept + ", memo=" + memo + "]";
	}
	
}
