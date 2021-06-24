package factory;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Teacher;
import mis.tool.SqlOperation;

public class TeacherTable implements UserTable{

	@Override
	public JTable createTable() {
		String sql="select*from tb_teacher";
		JTable table=new JTable();
		DefaultTableModel tableModel=new DefaultTableModel();
		ArrayList<Teacher> teacherList=SqlOperation.queryTeacher(sql);
		Object[] column= {"教师编号","姓名","性别","账号","密码","电话号码","电子邮箱","地址","备注"};
		Object[]rowData=new Object[column.length];
		table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		tableModel.setColumnIdentifiers(column);
		for(Teacher teacher:teacherList) {
			rowData[0]=teacher.getNumber();
			rowData[1]=teacher.getName();
			rowData[2]=teacher.getSex();
			rowData[3]=teacher.getAccount();
			rowData[4]=teacher.getPassword();
			rowData[5]=teacher.getPhone();
			rowData[6]=teacher.getMail();
			rowData[7]=teacher.getAddress();
			rowData[8]=teacher.getRemark();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
		return table;
	}

	@Override
	public void plushTable(JTable table) {
		String sql="select*from tb_teacher";
		DefaultTableModel newTableModel=(DefaultTableModel) table.getModel();
		ArrayList<Teacher> teacherList=SqlOperation.queryTeacher(sql);
		Object[] column= {"教师编号","姓名","性别","账号","密码","电话号码","电子邮箱","地址","备注"};
		Object[][] rowData=new Object[teacherList.size()][column.length];
		int i=0;
		for(Teacher teacher:teacherList) {
			rowData[i][0]=teacher.getNumber();
			rowData[i][1]=teacher.getName();
			rowData[i][2]=teacher.getSex();
			rowData[i][3]=teacher.getAccount();
			rowData[i][4]=teacher.getPassword();
			rowData[i][5]=teacher.getPhone();
			rowData[i][6]=teacher.getMail();
			rowData[i][7]=teacher.getAddress();
			rowData[i][8]=teacher.getRemark();
			i++;
		}
		newTableModel.setDataVector(rowData,column);
		table.updateUI();
	}
}
