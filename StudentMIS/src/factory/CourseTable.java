package factory;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.Course;
import mis.tool.SqlOperation;
/**
 * 课程信息表实现类
 * @author 魔宇
 *
 */
public class CourseTable implements UserTable{

	@Override
	public JTable createTable() {
		String sql="select*from tb_course";
		JTable table=new JTable();
		DefaultTableModel tableModel=new DefaultTableModel();
		ArrayList<Course> courseList=SqlOperation.queryCourse(sql);
		Object[] column= {"课程编号","课程名称","任课老师","学分","时长"};
		Object[]rowData=new Object[column.length];
		table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		tableModel.setColumnIdentifiers(column);
		for(Course course:courseList) {
			rowData[0]=course.getNumber();
			rowData[1]=course.getName();
			rowData[2]=course.getTeacher();
			rowData[3]=course.getCredit();
			rowData[4]=course.getHour();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
		return table;
	}

	@Override
	public void plushTable(JTable table) {
		String sql="select*from tb_course";
		DefaultTableModel newTableModel=(DefaultTableModel) table.getModel();
		ArrayList<Course> courseList=SqlOperation.queryCourse(sql);
		Object[] column= {"课程编号","课程名称","任课老师","学分","时长"};
		Object[][] rowData=new Object[courseList.size()][column.length];
		int i=0;
		for(Course course:courseList) {
			rowData[i][0]=course.getNumber();
			rowData[i][1]=course.getName();
			rowData[i][2]=course.getTeacher();
			rowData[i][3]=course.getCredit();
			rowData[i][4]=course.getHour();
			i++;
		}
		newTableModel.setDataVector(rowData,column);
		table.updateUI();
	}
}
