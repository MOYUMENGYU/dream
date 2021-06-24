package mis.studentPersonFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bean.DataScore;
import mis.managerframe.Layout;

public class StudentDataScore implements Layout{
	
	CardLayout card=new CardLayout();
	JPanel pane=new JPanel(card);
	
	public JPanel checkResultPanel(DataScore score) {	
		JPanel panel=new JPanel(new GridLayout(5,2));
		if(score!=null) {
			JLabel number_label=new JLabel("学     号:");
			JTextField number_text=new JTextField(10);
			number_text.setText(score.getNumber());
			number_text.setEnabled(false);
			JPanel number_pane=new JPanel();
			number_pane.add(number_label);
			number_pane.add(number_text);
			
			JLabel name_label=new JLabel("姓     名:");
			JTextField name_text=new JTextField(10);
			name_text.setText(score.getName());
			name_text.setEnabled(false);
			JPanel name_pane=new JPanel();
			name_pane.add(name_label);
			name_pane.add(name_text);
			
			JLabel major_label=new JLabel("专     业:");
			JTextField major_text=new JTextField(10);
			major_text.setText(score.getMajor());
			major_text.setEnabled(false);
			JPanel major_pane=new JPanel();
			major_pane.add(major_label);
			major_pane.add(major_text);
			
			JLabel java_label=new JLabel("     Java:");
			JTextField java_text=new JTextField(10);
			java_text.setText(String.valueOf(score.getJava()));
			java_text.setEnabled(false);
			JPanel java_pane=new JPanel();
			java_pane.add(java_label);
			java_pane.add(java_text);
			
			JLabel English_label=new JLabel("大学英语:");
			JTextField English_text=new JTextField(10);
			English_text.setText(String.valueOf(score.getEnglish()));
			English_text.setEnabled(false);
			JPanel English_pane=new JPanel();
			English_pane.add(English_label);
			English_pane.add(English_text);
			
			JLabel math_label=new JLabel("高等数学");
			JTextField math_text=new JTextField(10);
			math_text.setText(String.valueOf(score.getMath()));
			math_text.setEnabled(false);
			JPanel math_pane=new JPanel();
			math_pane.add(math_label);
			math_pane.add(math_text);
			
			JLabel physical_label=new JLabel("大学物理:");
			JTextField physical_text=new JTextField(10);
			physical_text.setText(String.valueOf(score.getPhysical()));
			physical_text.setEnabled(false);
			JPanel physical_pane=new JPanel();
			physical_pane.add(physical_label);
			physical_pane.add(physical_text);
			
			JLabel remark_label=new JLabel("备    注:");
			JTextField remark_text=new JTextField(10);
			remark_text.setText(score.getRemark());
			remark_text.setEnabled(false);
			JPanel remark_pane=new JPanel();
			remark_pane.add(remark_label);
			remark_pane.add(remark_text);
			
			panel.add(number_pane);
			panel.add(name_pane);
			panel.add(major_pane);
			panel.add(java_pane);
			panel.add(English_pane);
			panel.add(math_pane);
			panel.add(physical_pane);
			panel.add(remark_pane);
			
		}else if(score==null) {
			JLabel tip_label=new JLabel("您的成绩没有找到！");
			tip_label.setFont(new Font(null,Font.BOLD,18));
			tip_label.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(tip_label);
		}
		JButton return_button=new JButton("返回");
		return_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(pane,"studentNavigation");
			}
		});
		JPanel button_pane=new JPanel();
		button_pane.add(return_button);
		
		JPanel panell=new JPanel(new BorderLayout());
		panell.add(panel,BorderLayout.CENTER);
		panell.add(button_pane,BorderLayout.SOUTH);
		return panell;
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
