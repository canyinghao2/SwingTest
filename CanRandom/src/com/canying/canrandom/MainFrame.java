package com.canying.canrandom;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class MainFrame {

	int num = 0;
	int selectNum = 0;
	int m = 0;
	boolean f = false;
	private static List<JLabel> list = new ArrayList<JLabel>();
	private static List<JLabel> listSelcet = new ArrayList<JLabel>();
	private JFrame frame;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	public void clear() {

		textArea.setText("");

		for (JLabel jLabel : list) {
			frame.getContentPane().remove(jLabel);
		}
		for (JLabel jLabel : listSelcet) {
			frame.getContentPane().remove(jLabel);
		}
		num = 0;
		selectNum = 0;
		list.clear();
		listSelcet.clear();
		frame.repaint();
	}

	public void add() {
		String text = textArea.getText();
		if ("".equals(text)) {
			JOptionPane.showMessageDialog(null, "内容为空", "请输入内容",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String[] strs = null;
			if (text.contains(",")) {
				strs = text.split(",");
			} else if (text.contains("，")) {

				strs = text.split("，");
			} else {
				strs = new String[] { text };
			}

			for (String string : strs) {
				if (!string.isEmpty()) {
					JLabel label = new JLabel((num + 1) + "." + string);
					label.setBounds(185, 80 + num * 25, 210, 20);
					label.setFont(new java.awt.Font("Dialog", 1, 14));
					label.setForeground(Color.BLACK);
					list.add(label);
					frame.getContentPane().add(label);

					num++;
				}

			}

			frame.repaint();

		}
	}

	public void random() {
		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(null, "请先添加数字或名称", "未添加项目",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			m = 0;

			int random = (int) (Math.random() * list.size() * 5 + list.size());
			if (selectNum == 0) {
				
				int size=list.size();
				if (size>4) {
					size=4;
				}
				int some = (int) (Math.random() * size);

				if (some == 0) {
					random = list.size() * 5;
					
					
				}

			}

			final int ran = random;
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {

					for (JLabel jLabel : list) {
						jLabel.setFont(new java.awt.Font("Dialog", 1, 18));
						jLabel.setForeground(Color.BLACK);
					}

					int size = list.size();
					JLabel jLabel = list.get(m % size);
					jLabel.setFont(new java.awt.Font("Dialog", 1, 25));
					jLabel.setForeground(Color.red);

					m++;

					frame.repaint();
					if (m == ran) {
						listSelcet.add(jLabel);
						list.remove(jLabel);
						jLabel.setBounds(405, 80 + 25 * selectNum, 210, 20);

						selectNum++;
						frame.repaint();
						cancel();

					}

				}
			}, 50, 100);

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("文件");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("清空");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();

			}
		});
		;

		JMenuItem menuItem_1 = new JMenuItem("添加");
		menu.add(menuItem_1);

		menuItem_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add();

			}
		});

		JMenuItem menuItem_2 = new JMenuItem("随机选择");
		menu.add(menuItem_2);

		menuItem_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				random();

			}
		});

		JMenu menu_1 = new JMenu("退出");
		menuBar.add(menu_1);

		menu_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);

			}
		});
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("请在下方输入一组数字或名称，中间用逗号隔开");
		label.setBounds(6, 17, 311, 16);
		frame.getContentPane().add(label);

		textArea = new JTextArea();
		textArea.setBounds(6, 45, 134, 243);
		textArea.setLineWrap(true);

		frame.getContentPane().add(textArea);

		JButton button = new JButton("清空");
		button.setBounds(6, 300, 60, 29);
		frame.getContentPane().add(button);
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
			}
		});

		JButton button_1 = new JButton("添加");
		button_1.setBounds(80, 300, 60, 29);
		frame.getContentPane().add(button_1);
		button_1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				add();

			}
		});

		JButton button_2 = new JButton("随机选择");
		button_2.setBounds(405, 40, 117, 29);
		frame.getContentPane().add(button_2);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				random();

			}

		});

		JLabel label_1 = new JLabel("下方将显示随机选择的项");
		label_1.setBounds(185, 45, 177, 16);
		frame.getContentPane().add(label_1);

	}
}
