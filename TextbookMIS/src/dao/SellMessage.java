package dao;

import java.util.ArrayList;

/**
 * 销售信息表
 * @author 魔宇
 *
 */

public class SellMessage {
	private String number;
	private String date;
	private String seller;
	private PersonCharge buyer=new PersonCharge();
	private  ArrayList<OrderTextbook> sellBookMessage=new ArrayList<>();
	private String address;
	private String time;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public PersonCharge getBuyer() {
		return buyer;
	}
	public void setBuyer(PersonCharge buyer) {
		this.buyer = buyer;
	}
	public ArrayList<OrderTextbook> getSellBookMessage() {
		return sellBookMessage;
	}
	public void setSellBookMessage(ArrayList<OrderTextbook> sellBookMessage) {
		this.sellBookMessage = sellBookMessage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
