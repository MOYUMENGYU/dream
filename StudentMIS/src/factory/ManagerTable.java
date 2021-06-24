package factory;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Manager;
import mis.tool.SqlOperation;
/**
 * 管理员信息表格实现类
 * 创建和更新
 * @author 魔宇
 */
public class ManagerTable implements UserTable{
	/**
	 * 返回一个表格模型
	 */
	@Override
	public JTable createTable() {
		String sql="select*from tb_manager";
		JTable table=new JTable();
		//表格模型：用来操作数据
		DefaultTableModel tableModel=new DefaultTableModel();
		//从数据库中查询出信息
		ArrayList<Manager> managerList=SqlOperation.queryManager(sql);
		//表头
		Object[] column= {"管理编号","姓名","账号","密码","电话号码","电子邮箱","地址","备注"};
		Object[]rowData=new Object[column.length];
		table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		tableModel.setColumnIdentifiers(column);
		//将信息填入表格模型
		for(Manager manager:managerList) {
			rowData[0]=manager.getNumber();
			rowData[1]=manager.getName();
			rowData[2]=manager.getAccount();
			rowData[3]=manager.getPassword();
			rowData[4]=manager.getPhone();
			rowData[5]=manager.getMail();
			rowData[6]=manager.getAddress();
			rowData[7]=manager.getRemark();
			tableModel.addRow(rowData);
		}
		//应用表格模型
		table.setModel(tableModel);
		return table;
	}
	/**
	 * 更新表格：改变表格模型中的数据，在将模型应用到表格中
	 */
	@Override
	public void plushTable(JTable table) {
		//获取原表格的表格模型
		DefaultTableModel newTableModel=(DefaultTableModel) table.getModel();
		String sql="select*from tb_manager";
		//再次获取数据库中的信息
		ArrayList<Manager> managerList=SqlOperation.queryManager(sql);
		Object[] column= {"管理编号","姓名","账号","密码","电话号码","电子邮箱","地址","备注"};
		Object[][] rowData=new Object[managerList.size()][column.length];
		int i=0;
		//将信息填入表格模型
		for(Manager manager:managerList) {
			rowData[i][0]=manager.getNumber();
			rowData[i][1]=manager.getName();
			rowData[i][2]=manager.getAccount();
			rowData[i][3]=manager.getPassword();
			rowData[i][4]=manager.getPhone();
			rowData[i][5]=manager.getMail();
			rowData[i][6]=manager.getAddress();
			rowData[i][7]=manager.getRemark();
			i++;
		}
		//更新表格模型数据
		newTableModel.setDataVector(rowData,column);
		//更新表格组件
		table.updateUI();
	}
}
