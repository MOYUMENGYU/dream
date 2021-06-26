package sql;

import java.util.HashMap;

import dao.PurchaseOrder;
import dao.SellMessage;
import dao.Textbook;

/**
 * 模拟数据库
 * @author 魔宇
 *
 */

public class Sql {
	
	//自然科学库
	 HashMap<String,Textbook> natural=new HashMap<>();
	//社会科学库
	 HashMap<String,Textbook> social=new HashMap<>();
	//订购单
	 HashMap<String,PurchaseOrder> purchaseOrder=new HashMap<>();
	//销售单
	 HashMap<String,SellMessage> sellMessage=new HashMap<>();
	 
	public Sql() {
		init();
	}
	public HashMap<String, Textbook> getNatural() {
		return natural;
	}
	public void setNatural(HashMap<String, Textbook> natural) {
		this.natural = natural;
	}
	public HashMap<String, Textbook> getSocial() {
		return social;
	}
	public void setSocial(HashMap<String, Textbook> social) {
		this.social = social;
	}
	public HashMap<String, PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(HashMap<String, PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public HashMap<String, SellMessage> getSellMessage() {
		return sellMessage;
	}
	public void setSellMessage(HashMap<String, SellMessage> sellMessage) {
		this.sellMessage = sellMessage;
	}
	//初始化两个书库
	public void init() {
		for(int i=1;i<=100;i++) {
			Textbook textbook=new Textbook();
			textbook.setNumber("A编号"+i);
			textbook.setName("A教材名称"+i);
			textbook.setType("A类型"+i);
			textbook.setType("A作者"+i);
			textbook.setCompany("A出版社"+i);
			textbook.setPrive("A单价"+i);
			textbook.setStock(1000);
			natural.put(textbook.getNumber(),textbook);
		}
		for(int i=1;i<=100;i++) {
			Textbook textbook=new Textbook();
			textbook.setNumber("B编号"+i);
			textbook.setName("B教材名称"+i);
			textbook.setType("B类型"+i);
			textbook.setType("B作者"+i);
			textbook.setCompany("B出版社"+i);
			textbook.setPrive("B单价"+i);
			textbook.setStock(1000);
			social.put(textbook.getNumber(),textbook);
		}
		
	}
	
}
