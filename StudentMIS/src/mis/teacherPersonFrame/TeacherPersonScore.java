package mis.teacherPersonFrame;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mis.managerframe.Layout;

public class TeacherPersonScore implements Layout{
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	String order="数据科学与大数据技术";
	public JPanel scoreMessagePane() {
		String[]majors= {"数据科学与大数据技术","计算机科学与技术","软件工程"};
		JLabel major=new JLabel("专业:");
		JComboBox<String> combo=new JComboBox<>(majors);
		JButton check_button=new JButton("查看");
		JButton return_button=new JButton("返回");
		JPanel panel=new JPanel();
		panel.add(major);
		panel.add(combo);
		panel.add(check_button);
		panel.add(return_button);
		combo.addItemListener(e->{
			order=(String)combo.getSelectedItem();
		});
		check_button.addActionListener(e->{
			if(order.equals("数据科学与大数据技术")) {
				card.show(pane,"teacherScore");
			}
		});
		return_button.addActionListener(e->{
			card.show(pane,"teacherNavigation");
		});
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
