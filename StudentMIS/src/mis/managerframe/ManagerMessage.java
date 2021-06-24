package mis.managerframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bean.Manager;
import factory.ManagerTableFactory;
import factory.TableFactoryAbstract;
import factory.UserTable;
import mis.tool.SqlOperation;

/**
 * 管理员信息界面
 * @author 魔宇
 *
 */
public class ManagerMessage implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	//获取工厂
	TableFactoryAbstract factory=new ManagerTableFactory();
	//生产表格
	UserTable userTable=factory.createUserTable();
	JTable table=userTable.createTable();
	
	public JPanel managerMessagePane() {
		//将表格添加到滚动面板中
		JScrollPane srollPane=new JScrollPane(table);
		JPanel panel=new JPanel(new BorderLayout());
		panel.add(srollPane,BorderLayout.CENTER);
		JPanel button_pane=new JPanel();
		JButton revise_button=new JButton("修改");
		JButton keep_button=new JButton("编辑");
		JButton remove_button=new JButton("删除");
		JButton lookup_button=new JButton("查找");
		JButton return_button=new JButton("返回");
		revise_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
			}
		});
		/**
		 * 被表格监听器弄心态崩了，监听器与表格更新冲突，原本好好的，
		 * 突然就各种问题，不开心，所以现在把它给灭了，改为使用对话框进行数据具体修改
		 */
		keep_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectRow=table.getSelectedRow();
				if(selectRow>=0) {
					Manager manager=new Manager();
					manager.setNumber((String)table.getValueAt(selectRow,0));
					manager.setName((String)table.getValueAt(selectRow,1));
					manager.setAccount((String)table.getValueAt(selectRow,2));
					manager.setPassword((String)table.getValueAt(selectRow,3));
					manager.setPhone((String)table.getValueAt(selectRow,4));
					manager.setAddress((String)table.getValueAt(selectRow,5));
					manager.setMail((String)table.getValueAt(selectRow,6));
					manager.setRemark((String)table.getValueAt(selectRow,7));
					//对话框，在对话框中的可以修改信息
					LookupResultManager lookupResult=new LookupResultManager();
					lookupResult.setTable(table);
					lookupResult.add(lookupResult.checkResultPanel(manager));
					lookupResult.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(pane,"请先选择编辑行");
				}
			}
		});
		remove_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSelect=table.getSelectedRow();
				if(rowSelect>=0) {
					int order=JOptionPane.showConfirmDialog(pane,"是否删除","提示",JOptionPane.OK_CANCEL_OPTION);
					if(order==JOptionPane.OK_OPTION) {
						SqlOperation.deleteManager((String)table.getModel().getValueAt(rowSelect,0));
						//更新表格，不再需要知道要更新数据库中的那个表
						userTable.plushTable(table);
					}
				}else {
					JOptionPane.showMessageDialog(pane,"请先选择数据行");
				}
			}
		});
		return_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(false);
				card.show(pane,"navigation");
			}
		});
		lookup_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LookupDialogManager lookup=new LookupDialogManager();
				lookup.setTable(table);
				lookup.setVisible(true);
			}
		});
		button_pane.add(revise_button);
		button_pane.add(keep_button);
		button_pane.add(remove_button);
		button_pane.add(lookup_button);
		button_pane.add(return_button);
		panel.add(button_pane,BorderLayout.SOUTH);
		return panel;
	}
	@Override
	public void setPane(JPanel pane) {
		this.pane=pane;
	}
	@Override
	public void setcard(CardLayout card) {
		this.card=card;
	}
}
