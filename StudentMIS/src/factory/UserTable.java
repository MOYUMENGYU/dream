package factory;

import javax.swing.JTable;
/**
 * 工厂方法模式：需要创建和更新各种各样的表，所以使用工厂方法模式
 * @author 魔宇
 *
 */
public interface UserTable {
	//从数据库中查询信息后，使其在窗口中以表格呈现
	public JTable createTable();
	//对信息更改后进行更新操作
	public void plushTable(JTable table);

}
