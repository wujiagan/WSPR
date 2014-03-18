package process;

import java.io.File;

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
		
	}
}
