package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import dao.OrderTextbook;
import dao.PurchaseOrder;
import dao.SellMessage;
import dao.Textbook;
import sql.Sql;
import sql.SqlConnection;

/**
 * 教材销售
 * @author 魔宇
 *
 */
public class SellRecord implements SqlConnection{
	Sql sql=new Sql();
	//销售表
	SellMessage sell=new SellMessage();
	//存储销售表
	HashMap<String,SellMessage> sellMessage;
	int id=1;
	//销售单登记
	public void orderMessageLogin() {
		System.out.println("=============================================请进行销售信息录入=================================================");
		Scanner s=new Scanner(System.in);
		Scanner n=new Scanner(System.in);
		//订单号，进行判断
		System.out.print("销售单号(六位数字):");
		boolean nf=true;
		String number="";
		while(nf) {
			number=s.nextLine();
			if(number.length()==6&&number.matches("\\d+")) {
				sell.setNumber(number);
				nf=false;
			}else {
				nf=true;
				System.out.print("请正确输入6位数字的订单号:");
			}
		}
		//订单填写日期
		System.out.print("交易完成日期(yyyy/mm/dd):");
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
		sell.setDate(date);
		//卖方负责人信息
		System.out.print("卖方负责人:");
		String sellname=s.nextLine();
		sell.setSeller(sellname);
		//买方负责人信息
		System.out.print("买方负责人:");
		String buyName=s.nextLine();
		sell.getBuyer().setName(buyName);
		
		System.out.print("买方单位:");
		String buyCompany=s.nextLine();
		sell.getBuyer().setCompany(buyCompany);;
		
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
		sell.getBuyer().setPhone(buyPhone);
		
		System.out.print("买方地址:");
		String buyAddress=s.nextLine();
		sell.getBuyer().setAddress(buyAddress);
		
		boolean flag=true;
		while(flag) {
			//销售图书信息的登记
			orderBookMessage();
			System.out.print("是否还订购了其他教材?(yes/no)");
			boolean f=true;
			while(f) {
				String order=s.nextLine();
				if(order.equals("yes")) {
					id+=1;
					f=false;
					flag=true;
				}else if(order.equals("no")) {
					f=false;
					flag=false;
				}else {
					System.out.println("请正确输入,是否还订购了其他教材?(yes/no)");
					f=true;
				}
			}
		}
		System.out.print("收货地址:");
		String address=s.nextLine();
		sell.setAddress(address);
		
		System.out.print("交货时间(yyyy/mm/dd):");
		boolean dff=true;
		String time="";
		while(dff) {
			time=s.nextLine();
			if(time.matches("\\d{4}/\\d{2}/\\d{2}")) {
				dff=false;
			}else {
				System.out.print("请输入正确日期格式:");
				dff=true;
			}
		}
		sell.setTime(time);
		sql.getSellMessage().put(number, sell);
	}
	//销售图书信息登记(在销售单中)
	public void orderBookMessage() {
		//获取书库
		HashMap<String,Textbook> natural=sql.getNatural();
		HashMap<String,Textbook> social=sql.getSocial();
		Scanner s=new Scanner(System.in);
		Scanner n=new Scanner(System.in);
		OrderTextbook orderText=new OrderTextbook();
		
		System.out.println("序号:"+id);
		orderText.setId(id);
		
		System.out.print("教材编号(A/B编号1-100):");
		boolean bf=true;
		String bookNumber="";
		while(bf) {
			bookNumber=s.nextLine();
			 if(natural.containsKey(bookNumber)) {
				 bf=false;
			 }else if(social.containsKey(bookNumber)) {
				 bf=false;
			 }else {
				 System.out.print("请检查教材编号是否输入正确,请重新输入:");
				 bf=true;
			 }
		}
		orderText.setBookNumber(bookNumber);
		
		System.out.print("教材名称(A/B教材名称1-100)):");
		boolean nf=true;
		String name="";
		while(nf) {
			 name=s.nextLine();
			 if(natural.containsKey(bookNumber)) {
				 if(natural.get(bookNumber).getName().equals(name)) {
					 nf=false;
				 }else {
					 System.out.print("请检查教材名称是否输如正确,请重新输入:");
					 nf=true;
				 }
			 }
			 if(social.containsKey(bookNumber)) {
				 if(social.get(bookNumber).getName().equals(name)) {
					nf=false;
				 }else {
					 System.out.print("请检查教材名称是否输如正确,请重新输入:");
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
					 if(natural.get(bookNumber).getStock()>num) {
						 orderText.setNumber(num);
						 numf=false;
					 }else {
						 System.out.print("请重新确认订购数量是否正确:");
						 numf=true;
					 }
				 }
				 if(social.containsKey(bookNumber)) {
					 if(social.get(bookNumber).getStock()>num) {
						 orderText.setNumber(num);
						 numf=false;
					 }else {
						 System.out.print("请重新确认订购数量是否正确:");
						 numf=true;
					 }
				 }
		}
		orderText.setNumber(num);
		
		System.out.print("教材单价:");
		boolean fl=true;
		double price=n.nextDouble();
		System.out.printf("请正确输入教材单价:");
		orderText.setPrice(price);
		
		double sum=price*num;
		System.out.printf("金额:%.2f\n",sum);
		orderText.setSum(sum);
		
		System.out.print("备注:");
		String remark=s.nextLine();
		orderText.setRemark(remark);
		//从书库中减掉销售掉的数量
		if(natural.containsKey(bookNumber)) {
			 long newStock=natural.get(bookNumber).getStock()-num;
			 natural.get(bookNumber).setStock(newStock);
		 }
		 if(social.containsKey(bookNumber)) {
			 long newStock=social.get(bookNumber).getStock()-num;
			 social.get(bookNumber).setStock(newStock);
		 }
		sell.getSellBookMessage().add(orderText);
	}
	//打印销售单
	public void showSellMessage(String number) {
		sellMessage=sql.getSellMessage();
		    System.out.println("===========================================================================================================================");
			System.out.printf("订单号:%-8s\t",sellMessage.get(number).getNumber());
			System.out.printf("订单日期:%-8s\t",sellMessage.get(number).getDate());
			System.out.printf("卖方负责人:%-8s\t\n",sellMessage.get(number).getSeller());
			System.out.printf("买方负责人:%-8s\t",sellMessage.get(number).getBuyer().getName());
			System.out.printf("买方单位:%-8s\t",sellMessage.get(number).getBuyer().getCompany());
			System.out.printf("买方电话:%-8s\t",sellMessage.get(number).getBuyer().getPhone());			
			System.out.printf("买方地址:%-8s\t\n",sellMessage.get(number).getBuyer().getAddress());
			ArrayList<OrderTextbook> orderBookMessage=sellMessage.get(number).getSellBookMessage();
			for(OrderTextbook book:orderBookMessage) {
				System.out.printf("序号:%-8s\t",book.getId());
				System.out.printf("教材编号:%-8s\t",book.getNumber());
				System.out.printf("教材名称:%-8s\t",book.getName());
				System.out.printf("订购数量:%-8s\t",book.getNumber());
				System.out.printf("教材单价:%-8s\t",book.getPrice());
				System.out.printf("金额:%-8s\t",book.getSum());
				System.out.printf("备注:%-8s\t\n",book.getRemark());
			}
			System.out.printf("收货地址:%-8s\t",sellMessage.get(number).getAddress());
			System.out.printf("交货时间:%-8s\t\n",sellMessage.get(number).getTime());
			System.out.println("===========================================================================================================================");
		}
	//打印所有的销售单
	public void showSell() {
		sellMessage=sql.getSellMessage();
		if(!sellMessage.isEmpty()) {
			Iterator<String> iterator=sellMessage.keySet().iterator();
			while(iterator.hasNext()) {
				String number=iterator.next();
				showSellMessage(number);
			}
		}else {
			System.out.println("=============================================还没有销售单录入!=================================================");
		}
	}
	//销售查询
	public void checkSell() {
		sellMessage=sql.getSellMessage();
		System.out.print("请输入查询订单编号(回车退出查询):");
		Scanner scanner=new Scanner(System.in);
		String number=scanner.nextLine();
		if(sellMessage.containsKey(number)) {
			System.out.println("查询结果:");
			showSellMessage(number);
		}else if(number.equals("")) {
			System.out.println("=============================================已退出查询！=================================================");
		}else {
			System.out.println("=============================================查询销售单不存在！=================================================");
		}
	}
	@Override
	public void sqlConnection(Sql sql) {
		this.sql=sql;
	}

}
