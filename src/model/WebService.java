package model;

import java.util.HashMap;
import java.util.Map;

public class WebService {
	private String name;
	private Map<String, Object> inputList = new HashMap<String, Object>();
	private Map<String, Object> outputList = new HashMap<String, Object>();
	
	public WebService(String name) {
		this.name = name;
	}
	
	public void addInput(String inputVal) {
		inputList.put(inputVal, null);
	}
	
	public void addOutput(String outputVal) {
		outputList.put(outputVal, null);
	}
	
	public Map<String, Object> getInputList() {
		return this.inputList;
	}
	
	public Map<String, Object> getOutputList() {
		return this.outputList;
	}
	
	public int getNumInputs() {
		return this.inputList.size();
	}
	
	public int getNumOutputs() {
		return this.outputList.size();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "service name:" + this.name + "\ninput:" + this.inputList.keySet() + "\noutput:" +this.outputList.keySet();
	}
	
	 
}
