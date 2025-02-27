/**
 * Main frame of the Muninn application.
 * 
 * Copyright 2011 Mattias Nilsson, Niklas Olsson
 * 
 * This file is part of Muninn.
 *
 * Muninn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Muninn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Muninn.  If not, see <http://www.gnu.org/licenses/>.
 */
package muninn.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * @author Mattias Nilsson
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	private static final int DIVIDER_POS = 11*HEIGHT/18;
	
	private static final int DIVIDER_SIZE = 4;
	private static final String FRAME_TITLE = "Muninn";

	private StatusBar statusBar = new StatusBar();
	private Toolbar toolbar = new Toolbar();
	private TextPanel textPanel = new TextPanel();
	private GraphPanel graphPanel = new GraphPanel();
	
	public MainFrame() {
		initFrame();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}
	
	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(FRAME_TITLE);
		setSize(WIDTH, HEIGHT);
		
		createMenu();
		
		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);
		add(toolbar, BorderLayout.WEST);
		
		JSplitPane graphText = new JSplitPane();
		
		graphText.setOrientation(JSplitPane.VERTICAL_SPLIT);
		graphText.add(graphPanel, JSplitPane.TOP);
		graphText.add(textPanel, JSplitPane.BOTTOM);
		graphText.setDividerLocation(DIVIDER_POS);
		graphText.setDividerSize(DIVIDER_SIZE);
		add(graphText, BorderLayout.CENTER);
	}
	
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem quitItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}
	
	private void exit() {
		System.exit(0);
	}
}