package mis.managerframe;

import java.awt.CardLayout;

import javax.swing.JPanel;

/**
 * 布局
 * @author 魔宇
 *
 */

/**
 * 设计接口，使各个面板添加到
 * 同一个使用卡片布局的面板上
 * @author 魔宇
 *
 */
public interface Layout {
	public abstract void setPane(JPanel pane);
	public abstract void setcard(CardLayout card);

}
