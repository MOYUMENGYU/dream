package main;
/**
 * 添加图书
 * @author 魔宇
 *
 */

import sql.Sql;
import sql.SqlConnection;

public class AddTextbook implements SqlConnection{
	Sql sql=new Sql();
	@Override
	public void sqlConnection(Sql sql) {
		this.sql=sql;
	}
	

}
