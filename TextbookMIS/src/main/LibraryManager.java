package main;

import java.util.HashMap;
import java.util.Iterator;

import dao.Textbook;
import sql.Sql;
import sql.SqlConnection;

/**
 * 库存管理
 * @author 魔宇
 *
 */

public class LibraryManager implements SqlConnection{
	Sql sql=new Sql();
	HashMap<String,Textbook> natural;
	HashMap<String,Textbook> socail;
	public void libraryMessage() {
	    natural=sql.getNatural();
	    System.out.println("=============================================自然科学书库=================================================");
		System.out.println("有图书种类:"+natural.size());
		Iterator<String> iterator=natural.keySet().iterator();
		long sum=0;
		while(iterator.hasNext()) {
			String number=iterator.next();
			sum+=natural.get(number).getStock();
		}
		System.out.println("总书量:"+sum);
		System.out.println("=============================================================================================================");
		socail=sql.getSocial();
		System.out.println("=============================================社会科学书库=================================================");
		System.out.println("有图书种类:"+socail.size());
		Iterator<String> iterator2=socail.keySet().iterator();
		long sum2=0;
		while(iterator2.hasNext()) {
			String number=iterator2.next();
			sum2+=socail.get(number).getStock();
		}
		System.out.println("总书量:"+sum2);
		System.out.println("===========================================================================================================");
	}
	public void libraryWarning() {
		natural=sql.getNatural();
		socail=sql.getSocial();
		Iterator<String> iterator=natural.keySet().iterator();
		int sum1=0;
		while(iterator.hasNext()) {
			String number=iterator.next();
			if(natural.get(number).getStock()>0&&natural.get(number).getStock()<50) {
				System.out.println(natural.get(number).getName()+"库存不足50本,请及时补充库存");
				sum1++;
			}
		}
		if(sum1>0) {
			System.out.println("共有"+sum1+"种图书库存不足");
		}else if(sum1==0) {
			System.out.println("自然科学书库图书充足");
		}
		int sum2=0;
		Iterator<String> iterator2=socail.keySet().iterator();
		while(iterator2.hasNext()) {
			String number=iterator2.next();
			if(socail.get(number).getStock()>0&&socail.get(number).getStock()<50) {
				System.out.println(socail.get(number).getName()+"库存不足50本,请及时补充库存");
			}
		}
		if(sum2>0) {
			System.out.println("社会科学书库共有"+sum1+"种图书库存不足");
		}else if(sum2==0) {
			System.out.println("社会科学书库图书充足");
		}
	}
	@Override
	public void sqlConnection(Sql sql) {
		this.sql=sql;
	}
	

}
