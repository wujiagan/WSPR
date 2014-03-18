package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class WsprGUI extends JFrame{
	private int width = 300, height = 300;
	private Font font = new Font("宋体", 0, 20);
	private JButton wsdl_btn = new JButton("wsdl");
	private JButton query_btn = new JButton("query");
	private JButton run_btn = new JButton("run");
	private JButton quit_btn = new JButton("quit");
	private JFileChooser fileChooser = new JFileChooser();
	
	private File input;
	
	public WsprGUI() {
		initComponent();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	private void open_wsdl() {
		fileChooser.showOpenDialog(this);
		input = fileChooser.getSelectedFile();
		System.out.println(input);
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
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(1);
			}
		});
		
		this.setLayout(null);
		wsdl_btn.setBounds(100, 30, 100, 30);
		wsdl_btn.setFont(font);
		wsdl_btn.addActionListener(new WsdlBtnListener());
		this.add(wsdl_btn);
		query_btn.setBounds(100, 90, 100, 30);
		query_btn.setFont(font);
		this.add(query_btn);
		run_btn.setBounds(100, 150, 100, 30);
		run_btn.setFont(font);
		this.add(run_btn);
		quit_btn.setBounds(100, 210, 100, 30);
		quit_btn.setFont(font);
		this.add(quit_btn);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	class WsdlBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			open_wsdl();
		}
	}
	
}