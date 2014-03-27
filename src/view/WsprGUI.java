package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import process.WSHSP;

public class WsprGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 300, height = 300;
	private Font font = new Font("宋体", 0, 20);
	private JButton wsdlBtn = new JButton("wsdl");
	private JButton queryBtn = new JButton("query");
	private JButton runBtn = new JButton("run");
	private JButton quitBtn = new JButton("quit");
	private JFileChooser fileChooser = new JFileChooser();
	
	private WSHSP app = new WSHSP();
	
	public WsprGUI() {
		initComponent();
	}
	
	private void openWsdl() {
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fileChooser.showOpenDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnValue) {
			app.setTestDataDir(fileChooser.getSelectedFile());
			System.out.println(app.getTestDataDir());
		}
	}
	
	private void openQuery() {
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		int returnValue = fileChooser.showOpenDialog(this);
		if(JFileChooser.APPROVE_OPTION == returnValue) {
			app.setGoal(fileChooser.getSelectedFile());
			System.out.println(app.getGoal());
		}
	}
	
	private void run() {
		try {
			app.run();
		} catch (Exception e) {
		}
	}
	
	private void quit() {
		System.exit(1);
	}
	
	/**
	 * initialize the operate window
	 */
	private void initComponent() {
		this.setTitle("WSPR");
		this.setSize(width, height);
		this.setFont(new Font("宋体", 0, 20));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width-this.getSize().width)/2,
				(screen.height-this.getSize().height)/2);
		this.addWindowListener(new WSPRWindowListener());
		
		this.setLayout(null);
		wsdlBtn.setBounds(100, 30, 100, 30);
		wsdlBtn.setFont(font);
		wsdlBtn.addActionListener(new WsdlBtnListener());
		this.add(wsdlBtn);
		queryBtn.setBounds(100, 90, 100, 30);
		queryBtn.setFont(font);
		queryBtn.addActionListener(new QueryBtnListener());
		this.add(queryBtn);
		runBtn.setBounds(100, 150, 100, 30);
		runBtn.setFont(font);
		runBtn.addActionListener(new RunBtnListener());
		this.add(runBtn);
		quitBtn.setBounds(100, 210, 100, 30);
		quitBtn.setFont(font);
		quitBtn.addActionListener(new QuitBtnListener());
		this.add(quitBtn);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private class WsdlBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			openWsdl();
		}
	}
	
	private class QueryBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			openQuery();
		}
	}
	
	private class RunBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			run();
		}
	}
	
	private class QuitBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}
	}
	
	private class WSPRWindowListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			quit();
		}
	}
	
}
