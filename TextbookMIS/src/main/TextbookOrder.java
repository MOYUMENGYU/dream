package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import dao.OrderTextbook;
import dao.PurchaseOrder;
import dao.Textbook;
import sql.Sql;
import sql.SqlConnection;

/**
 * 教材订购
 * @author 魔宇
 *
 */

public class TextbookOrder implements SqlConnection{
	//数据库
	Sql sql=new Sql();
	//订单
	PurchaseOrder purchase=new PurchaseOrder();
	//存储订单的数据表
	HashMap<String,PurchaseOrder> purchaseOrder;
	int id=1;
	//订单登记
	public void orderMessageLogin() {
		System.out.println("=============================================请进行订购信息登记=================================================");
		Scanner s=new Scanner(System.in);
		Scanner n=new Scanner(System.in);
		//订单号
		System.out.print("订单号(六位数字):");
		boolean nf=true;
		String number="";
		while(nf) {
			number=s.nextLine();
			if(number.length()==6&&number.matches("\\d+")) {
				purchase.setNumber(number);
				nf=false;
			}else {
				nf=true;
				System.out.print("请正确输入6位数字的订单号:");
			}
		}
		//订单填写日期
		System.out.print("订单日期(yyyy/mm/dd)：");
		boolean df=true;
		String date="";
		while(df) {
			date=s.nextLine();
			if(date.matches("\\d{4}/\\d{2}/\\d{2}")) {
				df=false;
			}else {
				System.out.print("请输入正确日期格式:");
				df=true;
			}
		}
		purchase.setDate(date);
		//卖方负责人信息
		System.out.print("卖方负责人:");
		String sellname=s.nextLine();
		purchase.getSeller().setName(sellname);
		
		System.out.print("卖方单位:");
		String sellCompany=s.nextLine();
		purchase.getSeller().setCompany(sellCompany);
		
		System.out.print("卖方电话(11位手机号码或8位电话号码):");
		boolean pf=true;
		String sellPhone="";
		while(pf) {
			sellPhone =s.nextLine();
			if((sellPhone.length()==8|sellPhone.length()==11)&&sellPhone.matches("\\d+")) {
				pf=false;
			}else {
				System.out.print("请输入正确11位电话号码或8位电话号码:");
				pf=true;
			}
		}
		purchase.getSeller().setPhone(sellPhone);
		
		System.out.print("卖方地址:");
		String sellAddress=s.nextLine();
		purchase.getSeller().setAddress(sellAddress);
		
		//买方负责人信息
		System.out.print("买方负责人:");
		String buyname=s.nextLine();
		purchase.getBuyer().setName(buyname);
		
		System.out.print("买方单位:");
		String buyCompany=s.nextLine();
		purchase.getBuyer().setCompany(buyCompany);
		
		System.out.print("买方电话(11位手机号码或8位电话号码):");
		boolean pff=true;
		String buyPhone="";
		while(pff) {
			buyPhone =s.nextLine();
			if((buyPhone.length()==8|buyPhone.length()==11)&&buyPhone.matches("\\d+")) {
				pff=false;
			}else {
				System.out.print("请输入正确11位电话号码或8位电话号码:");
				pff=true;
			}
		}
		purchase.getBuyer().setPhone(buyPhone);
		
		System.out.print("买方地址:");
		String buyAddress=s.nextLine();
		purchase.getBuyer().setAddress(buyAddress);
		
		boolean flag=true;
		while(flag) {
			//订购的教材信息登记
			orderBookMessage();
			System.out.print("是否还需要订购其他教材?(yes/no):");
			boolean f=true;
			while(f) {
				String order=s.nextLine();
				if(order.equals("yes")) {
					id+=1;
					f=false;
					flag=true;
				}else if(order.equals("no")) {
					id=1;
					f=false;
					flag=false;
				}else {
					System.out.println("请正确输入,是否还需要订购其他教材?(yes/no)");
					f=true;
				}
			}
		}
		System.out.println("收货地址:");
		String address=s.nextLine();
		purchase.setAddress(address);
		
		System.out.println("交货日期(yyyy/mm/dd):");
		boolean dff=true;
		String time="";
		while(dff) {
			time=s.nextLine();
			if(date.matches("\\d{4}/\\d{2}/\\d{2}")) {
				dff=false;
			}else {
				System.out.print("请输入正确日期格式:");
				dff=true;
			}
		}
		purchase.setTime(time);
		sql.getPurchaseOrder().put(number, purchase);
	}
	//订单上教材的信息登记
	public void orderBookMessage() {
		HashMap<String,Textbook> natural=sql.getNatural();
		HashMap<String,Textbook> social=sql.getSocial();
		Scanner s=new Scanner(System.in);
		Scanner n=new Scanner(System.in);
		OrderTextbook orderText=new OrderTextbook();
		System.out.println("序号:"+id);
		orderText.setId(id);
		
		System.out.print("教材编号(A/B编号1-100,例:A编号1):");
		boolean bf=true;
		String bookNumber="";
		while(bf) {
			bookNumber=s.nextLine();
			 if(natural.containsKey(bookNumber)) {
				 bf=false;
			 }else if(social.containsKey(bookNumber)) {
				 bf=false;
			 }else {
				 System.out.print("书库中没有此图书,请重新确认所需的图书:");
				 bf=true;
			 }
		}
		orderText.setBookNumber(bookNumber);
		
		System.out.print("教材名称(A/B教材名称1-100,例:A教材名称1):");
		boolean nf=true;
		String name="";
		while(nf) {
			 name=s.nextLine();
			 if(natural.containsKey(bookNumber)) {
				 if(natural.get(bookNumber).getName().equals(name)) {
					 nf=false;
				 }else {
					 System.out.print("请确认输入的教材名称是否正确:");
					   nf=true;
				 }
			  }
			 if(social.containsKey(bookNumber)) {
				  if(social.get(bookNumber).getName().equals(name)) {
						nf=false;
				   }else {
						System.out.print("请确认输入的教材名称是否正确:");
						  nf=true;
					}
			 }
		}
		orderText.setName(name);
		System.out.print("订购数量:");
		boolean numf=true;
		int num=0;
		while(numf) {
				num=n.nextInt();
				if(natural.containsKey(bookNumber)) {
					 if(natural.get(bookNumber).getStock()>=num) {
						 orderText.setNumber(num);
						 numf=false;
					 }else {
						 System.out.print("该图书库存不足,现库存有"+natural.get(bookNumber).getStock()+"本,请重新输入订购数量:");
						 numf=true;
					 }
				 }
				 if(social.containsKey(bookNumber)) {
					 if(social.get(bookNumber).getStock()>=num) {
						 orderText.setNumber(num);
						 numf=false;
					 }else {
						 System.out.print("该图书库存不足,现有库存有"+social.get(bookNumber).getStock()+"本,请重新输入订购数量:");
						 numf=true;
					 }
				 }
		}		
		System.out.print("教材单价:");
		double price=n.nextDouble();
		orderText.setPrice(price);
		double sum=price*num;
		System.out.printf("金额:%.2f\n",sum);
		orderText.setSum(sum);
		
		System.out.print("备注:");
		String remark=s.nextLine();
		orderText.setRemark(remark);
		//当完成教材的订购登记，从库存中减掉订购的数量
		if(natural.containsKey(bookNumber)) {
			 long newStock=natural.get(bookNumber).getStock()-num;
			 natural.get(bookNumber).setStock(newStock);
		 }
		 if(social.containsKey(bookNumber)) {
			 long newStock=social.get(bookNumber).getStock()-num;
			 social.get(bookNumber).setStock(newStock);
		 }
		purchase.getOrderBookMessage().add(orderText);
	}
	//打印订单
	public void showOrderMessage(String number) {
		purchaseOrder=sql.getPurchaseOrder();
		System.out.println("========================================================================================================================");
			System.out.printf("订单号:%-8s\t",purchaseOrder.get(number).getNumber());
			System.out.printf("订单日期:%-8s\t\n",purchaseOrder.get(number).getDate());
			System.out.printf("卖方负责人:%-8s\t",purchaseOrder.get(number).getSeller().getName());
			System.out.printf("卖方单位:%-8s\t",purchaseOrder.get(number).getSeller().getCompany());
			System.out.printf("卖方电话:%-8s\t",purchaseOrder.get(number).getSeller().getPhone());
			System.out.printf("卖方地址:%-8s\t\n",purchaseOrder.get(number).getSeller().getAddress());
			System.out.printf("买方负责人:%-8s\t",purchaseOrder.get(number).getBuyer().getName());
			System.out.printf("买方单位:%-8s\t",purchaseOrder.get(number).getBuyer().getCompany());
			System.out.printf("买方电话:%-8s\t",purchaseOrder.get(number).getBuyer().getPhone());			
			System.out.printf("买方地址:%-8s\t\n",purchaseOrder.get(number).getBuyer().getAddress());
			ArrayList<OrderTextbook> orderBookMessage=purchaseOrder.get(number).getOrderBookMessage();
			for(OrderTextbook book:orderBookMessage) {
				System.out.printf("序号:%-8d\t",book.getId());
				System.out.printf("教材编号:%-8d\t",book.getNumber());
				System.out.printf("教材名称:%-8s\t",book.getName());
				System.out.printf("订购数量:%-8d\t",book.getNumber());
				System.out.printf("教材单价:%-8.2f\t",book.getPrice());
				System.out.printf("金额:%-8f.2\t",book.getSum());
				System.out.printf("备注:%-8s\t\n",book.getRemark());
			}
			System.out.printf("收货地址:%-8s\t",purchaseOrder.get(number).getAddress());
			System.out.printf("交货时间:%-8s\t\n",purchaseOrder.get(number).getTime());
			System.out.println("===========================================================================================================================");
		}
	//打印所有的订单
	public void showPurchase() {
		purchaseOrder=sql.getPurchaseOrder();
		if(!purchaseOrder.isEmpty()) {
			Iterator<String> iterator=purchaseOrder.keySet().iterator();
			while(iterator.hasNext()) {
				String number=iterator.next();
				showOrderMessage(number);
			}
		}else {
			System.out.println("=============================================还没有订单！=================================================");
		}
	}
	//订单查询
	public void checkPurchase() {
		purchaseOrder=sql.getPurchaseOrder();
		System.out.print("请输入查询订单编号(回车退出查询):");
		Scanner scanner=new Scanner(System.in);
		String number=scanner.nextLine();
		if(purchaseOrder.containsKey(number)) {
			System.out.println("查询结果:");
			showOrderMessage(number);
		}else if(number.equals("")){
			System.out.println("=============================================已退出查询！=================================================");
		}else {
			System.out.println("=============================================查询订单不存在！=================================================");
		}
	}
	@Override
	public void sqlConnection(Sql sql) {
		this.sql=sql;
	}
}
