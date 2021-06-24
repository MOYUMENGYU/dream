package factory;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bean.DataScore;
import mis.tool.SqlOperation;
/**
 * 大数据专业的成绩单的信息表实现类
 * @author 魔宇
 *
 */
public class DataScoreTable implements UserTable{

	@Override
	public JTable createTable() {
		String sql="select *from tb_datascore";
		JTable table=new JTable();
		DefaultTableModel tableModel=new DefaultTableModel();
		ArrayList<DataScore> scoreList=SqlOperation.queryDataScore(sql);
		Object[] column= {"学号","姓名","专业","java","大学英语","高等数学","大学物理","备注"};
		Object[]rowData=new Object[column.length];
		table=new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		tableModel=(DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);
		tableModel.setColumnIdentifiers(column);
		for(DataScore score:scoreList) {
			rowData[0]=score.getNumber();
			rowData[1]=score.getName();
			rowData[2]=score.getMajor();
			rowData[3]=score.getJava();
			rowData[4]=score.getEnglish();
			rowData[5]=score.getMath();
			rowData[6]=score.getPhysical();
			rowData[7]=score.getRemark();
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);
		return table;
	}

	@Override
	public void plushTable(JTable table) {
		String sql="select*from tb_datascore";
		DefaultTableModel newTableModel=(DefaultTableModel) table.getModel();
		ArrayList<DataScore> scoreList=SqlOperation.queryDataScore(sql);
		Object[] column= {"学号","姓名","专业","java","大学英语","高等数学","大学物理","备注"};
		Object[][] rowData=new Object[scoreList.size()][column.length];
		int i=0;
		for(DataScore score:scoreList) {
			rowData[i][0]=score.getNumber();
			rowData[i][1]=score.getName();
			rowData[i][2]=score.getMajor();
			rowData[i][3]=score.getJava();
			rowData[i][4]=score.getEnglish();
			rowData[i][5]=score.getMath();
			rowData[i][6]=score.getPhysical();
			rowData[i][7]=score.getRemark();
			i++;
		}
		newTableModel.setDataVector(rowData,column);
		table.updateUI();
	}
}
