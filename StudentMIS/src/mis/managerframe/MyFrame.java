package mis.managerframe;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame{
	Toolkit toolkit;
	Dimension dimension;
	int x;
	int y;
	public MyFrame() {
		toolkit=Toolkit.getDefaultToolkit();
		dimension=toolkit.getScreenSize();
		x=(int)(dimension.width*0.6);
		y=(int)(dimension.height*0.6);
		setSize(x,y);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
