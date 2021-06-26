package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import dao.OrderTextbook;
import dao.PurchaseOrder;
import dao.SellMessage;
import dao.Textbook;
import sql.Sql;
import sql.SqlConnection;

/**
 * 教材统计
 * @author 魔宇
 *
 */
public class TextCount implements SqlConnection{
	Sql sql=new Sql();
	//每日统计，统计每日的订单量，销售量，每天书库的库存量
	public void dayCount() {
		System.out.println("=============================================每日统计结果=================================================");
		HashMap<String,PurchaseOrder> countPurchase=sql.getPurchaseOrder();
		System.out.printf("订单量:%-5s\t",countPurchase.size());
		Iterator<String> iterator=countPurchase.keySet().iterator();
		String name="";
		int num=0;
		double sum=0;
		while(iterator.hasNext()) {
			String number=iterator.next();
			ArrayList<OrderTextbook> textbook=countPurchase.get(number).getOrderBookMessage();
			for(OrderTextbook book:textbook) {
				if(!name.contains(book.getName())) {
					name+=" "+book.getName();
				}
				num+=book.getNumber();
				sum+=book.getSum();
			}
		}
		System.out.printf("订购的教材:%-5s\t",name);
		System.out.printf("教材订购总量:%-5s\t",num);
		System.out.printf("教材订购总金额:%-5s\t\n",sum);
		System.out.println();
		sellCount();
		libraryCount();
		System.out.println("==============================================================================================");
	}
	//以书库为单位统计
	public void libraryCount() {
		HashMap<String,Textbook> natural=sql.getNatural();
		HashMap<String,Textbook> social=sql.getSocial();
		long naturalSum=natural.size();
		long socailSum=social.size();
		System.out.println();
		System.out.printf("自然科学书库图书种类:%-5d\t",naturalSum);
		System.out.printf("社会科学书库图书种类:%-5d\t",socailSum);
		long bookSum1=0;
		Iterator<String> iterator=natural.keySet().iterator();
		while(iterator.hasNext()) {
			String number=iterator.next();
			bookSum1+=natural.get(number).getStock();
		}
		long bookSum2=0;
		Iterator<String> iterator2=social.keySet().iterator();
		while(iterator2.hasNext()) {
			String number=iterator2.next();
			bookSum2+=social.get(number).getStock();
		}
		System.out.printf("自然科学书库总存量:%-5d\t",bookSum1);
		System.out.printf("社会科学书库总存量:%-5d\t",bookSum2);
		System.out.println();
	}
	//销售统计
	public void sellCount() {
		HashMap<String,SellMessage> countSell=sql.getSellMessage();
		System.out.println();
		System.out.printf("销售量:%-5d\t",countSell.size());	
		Iterator<String> iterator2=countSell.keySet().iterator();
		String name2="";
		int num2=0;
		double sum2=0;
		while(iterator2.hasNext()) {
			String number=iterator2.next();
			ArrayList<OrderTextbook> textbook=countSell.get(number).getSellBookMessage();
			for(OrderTextbook book:textbook) {
				if(!name2.contains(book.getName())) {
					name2+=" "+book.getName();
				}
				num2+=book.getNumber();
				sum2+=book.getSum();
			}
		}
		System.out.printf("销售的教材:%-5s\t",name2);
		System.out.printf("教材销售总量:%-5d\t",num2);
		System.out.printf("教材销售总金额:%-5.2f\t\n",sum2);
		System.out.println();
	}
	@Override
	public void sqlConnection(Sql sql) {
		this.sql=sql;
	}
}
