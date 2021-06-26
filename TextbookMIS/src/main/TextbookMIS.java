package main;

import java.util.Scanner;

import sql.Sql;

/**
 * 教材管理系统主类
 * 
 * @author 魔宇
 *
 */
public class TextbookMIS{
	//数据库
    Sql sql=new Sql();
    TextbookOrder bookOrder;
	SellRecord sellRecord;
	TextCount textCount;
	LibraryManager library;
	public static void main(String[] args) {
		System.out.println("=============================================欢迎来到教材管理系统=================================================");
		//系统导航导航
		TextbookMIS mis=new TextbookMIS();
		mis.service();		
	}
	public void service() {
		System.out.println();
		System.out.println("                                                  "+"=系统导航=");
		System.out.println("                          "+"1.教材订购 2.教材出售 3.教材统计 4.库存管理 5.退出教材管理系统");
		System.out.print("                                        "+"请选择需要的服务(输入服务序号):");
		Scanner scanner=new Scanner(System.in);
		int order=scanner.nextInt();
			switch(order) {
			case 1:
				order();
				service();
				break;
			case 2:
				sell();
				service();
				break;
			case 3:
				count();
				service();
				break;
			case 4:
				library();
				service();
				break;
			case 5:
				System.out.println("=============================================已退出教材管理系统=================================================\n");
				System.exit(0);
			}
	}
	//教材订购
	public void order() {
		bookOrder=new TextbookOrder();
		bookOrder.sqlConnection(sql);
		System.out.println();
		System.out.println("                                                  "+"=教材订购=");
		System.out.println("                                 "+"1.订购信息登记 2.订单信息 3.订购信息查询 4.退出教材订购");
		System.out.print("                                         "+"请选择需要的服务(输入服务序号):");
		Scanner s=new Scanner(System.in);
		int order1=s.nextInt();
		switch(order1) {
		case 1:
			bookOrder.orderMessageLogin();
			System.out.println("=============================================订购信息登记完成！=================================================");
			order();
			break;
		case 2:
			bookOrder.showPurchase();
			order();
			break;
		case 3:
			bookOrder.checkPurchase();
			order();
			break;
		case 4:
			break;
		}
	}
	//教材销售
	public void sell(){
		sellRecord=new SellRecord();
		sellRecord.sqlConnection(sql);
		System.out.println();
		System.out.println("                                                  "+"=教材出售=");
		System.out.println("                                    "+"1.出售录入 2.销售表信息 3.销售信息查询 4.退出教材出售");
		System.out.print("                                         "+"请选择需要的服务(输入服务序号):");
		Scanner s=new Scanner(System.in);
		int order=s.nextInt();
		switch(order) {
		case 1:
			sellRecord.orderMessageLogin();
			System.out.println("=============================================出售录入完成！=================================================");
			sell();
			break;
		case 2:
			sellRecord.showSell();
			sell();
			break;
		case 3:
			sellRecord.checkSell();
			sell();
			break;
		case 4:
			break;
		}
	}
	//教材统计
	public void count() {
		textCount=new TextCount();
		textCount.sqlConnection(sql);
		System.out.println();
		System.out.println("                                                  "+"=教材统计=");
		System.out.println("                               "+"1.每日统计 2.按库位号统计 3.销售统计 4.退出教材统计");
		System.out.print("                                            "+"请选择需要的服务(输入服务序号):");
		Scanner s=new Scanner(System.in);
		int order=s.nextInt();
		switch(order) {
		case 1:
			textCount.dayCount();
			count();
			break;
		case 2:
			textCount.libraryCount();
			count();
			break;
		case 3:
			textCount.sellCount();
			count();
			break;
		case 4:
			break;
		}
	}
	//库存管理功能
	public void library() {
		library=new LibraryManager();
		library.sqlConnection(sql);
		System.out.println();
		System.out.println("                                                  "+"=库存管理=");
		System.out.println("                                       "+"1.库存信息 2.库存预警 3.退出库存管理");
		System.out.print("                                           "+"请选择需要的服务(输入服务序号):");
		Scanner s=new Scanner(System.in);
		int order=s.nextInt();
		switch(order) {
		case 1:
			library.libraryMessage();
			library();
			break;
		case 2:
			library.libraryWarning();
			library();
			break;
		case 3:
			break;
		}
	}
}
