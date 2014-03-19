package process;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import webService.WebService;

public class WSHSP {
	/**
	 * this is the absolute path of the wsdl files
	 */
	private File input;
	
	/**
	 * this is the absolute path of the query file
	 */
	private File goal;
	
	public WSHSP() {
		
	}
	public File getInput() {
		return input;
	}
	public void setInput(File input) {
		this.input = input;
	}
	public File getGoal() {
		return goal;
	}
	public void setGoal(File goal) {
		this.goal = goal;
	}
	
	public List<WebService> readIeeeGoal(File goalFile) throws DocumentException {
		if(!goalFile.exists()){
			System.out.println("Failure to open " + goalFile + ".");
			System.exit(1);
		}
		SAXReader reader = new SAXReader();		//建立SAX解析读取
		Document doc = null;
		doc = reader.read(goalFile);		//读取文档
		Element root = doc.getRootElement();
		return null;
	}
	
	/**
	 * check the input file and the goal file, if not null, then invoke the process() 
	 */
	public void run() {
		if(this.goal == null || this.input == null) {
			if(this.input == null)
				System.out.println("You need to enter the directory for the test data");
			if(this.goal == null)
				System.out.println("You need to enter a filename for the goal wsdl");
		}else {
			process();
			System.out.println("end");
		}
	}
	
	public void process() {
		System.out.println("Loading the goal file \""+this.goal+"\"...");
	}
}
