package dao;

import java.util.ArrayList;

/**
 * 订购单
 * @author 魔宇
 *
 */

public class PurchaseOrder {
	private String number;
	private String date;
	private PersonCharge seller=new PersonCharge();
	private PersonCharge buyer=new PersonCharge();
	private  ArrayList<OrderTextbook> orderBookMessage=new ArrayList<>();
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
	public PersonCharge getSeller() {
		return seller;
	}
	public void setSeller(PersonCharge seller) {
		this.seller = seller;
	}
	public PersonCharge getBuyer() {
		return buyer;
	}
	public void setBuyer(PersonCharge buyer) {
		this.buyer = buyer;
	}
	
	public ArrayList<OrderTextbook> getOrderBookMessage() {
		return orderBookMessage;
	}
	public void setOrderBookMessage(ArrayList<OrderTextbook> orderBookMessage) {
		this.orderBookMessage = orderBookMessage;
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
