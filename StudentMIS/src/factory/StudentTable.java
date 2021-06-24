package factory;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Student;
import mis.tool.SqlOperation;

public class StudentTable implements UserTable{
	@Override
	public JTable createTable() {
		String sql="select*from tb_student";
		JTable table=new JTable();
		DefaultTableModel tableModel=new DefaultTableModel();
		ArrayList<Student> studentList=SqlOperation.queryStudent(sql);
		Object[] column= {"学号","姓名","性别","账号","密码","电话号码","电子邮箱","地址","学院","专业","班级","备注"};
		Object[]rowData=new Object[column.length];
		table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		tableModel.setColumnIdentifiers(column);
		for(Student student:studentList) {
			rowData[0]=student.getNumber();
			rowData[1]=student.getName();
			rowData[2]=student.getSex();
			rowData[3]=student.getAccount();
			rowData[4]=student.getPassword();
			rowData[5]=student.getPhone();
			rowData[6]=student.getMail();
			rowData[7]=student.getAddress();
			rowData[8]=student.getCollage();
			rowData[9]=student.getMajor();
			rowData[10]=student.getClassNumber();
			rowData[11]=student.getRemark();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
		return table;
	}

	@Override
	public void plushTable(JTable table) {
		String sql="select*from tb_student";
		DefaultTableModel newTableModel=(DefaultTableModel) table.getModel();
		ArrayList<Student> studentList=SqlOperation.queryStudent(sql);
		Object[] column= {"学号","姓名","性别","账号","密码","电话号码","电子邮箱","地址","学院","专业","班级","备注"};
		Object[][] rowData=new Object[studentList.size()][column.length];
		int i=0;
		for(Student student:studentList) {
			rowData[i][0]=student.getNumber();
			rowData[i][1]=student.getName();
			rowData[i][2]=student.getSex();
			rowData[i][3]=student.getAccount();
			rowData[i][4]=student.getPassword();
			rowData[i][5]=student.getPhone();
			rowData[i][6]=student.getMail();
			rowData[i][7]=student.getAddress();
			rowData[i][8]=student.getCollage();
			rowData[i][9]=student.getMajor();
			rowData[i][10]=student.getClassNumber();
			rowData[i][11]=student.getRemark();
			i++;
		}
		newTableModel.setDataVector(rowData,column);
		table.updateUI();
	}
}
