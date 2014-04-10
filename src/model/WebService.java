package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WebService {
	private String name;
	private Map<String, List<WebService>> inputList = new HashMap<String, List<WebService>>();
	private Map<String, List<WebService>> outputList = new HashMap<String, List<WebService>>();
	
	public WebService(String name) {
		this.name = name;
	}
	
	public void addInput(String inputVal) {
		inputList.put(inputVal, null);
	}
	
	public void addOutput(String outputVal) {
		outputList.put(outputVal, null);
	}
	
	public int getNumInputs() {
		return this.inputList.size();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, List<WebService>> getInputList() {
		return inputList;
	}

	

	public void setInputList(Map<String, List<WebService>> inputList) {
		this.inputList = inputList;
	}

	public Map<String, List<WebService>> getOutputList() {
		return outputList;
	}

	public void setOutputList(Map<String, List<WebService>> outputList) {
		this.outputList = outputList;
	}

	public int getNumOutputs() {
		return this.outputList.size();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj instanceof WebService) {
			WebService otherService = (WebService)obj;
			return this.name.equals(otherService.getName());
		}
		return false;
	}

	public boolean belongInputTo(Object obj) {
		if(this == obj)
			return true;
		if(obj instanceof WebService) {
			WebService otherService = (WebService)obj;
			return this.inputList.keySet().containsAll(otherService.getInputList().keySet());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		return "service name:" + this.name + "\ninput:" + this.inputList.keySet() + "\noutput:" +this.outputList.keySet();
	}
	
	public Iterator<String> inputKeyIterator() {
		return this.inputList.keySet().iterator();
	}
	
	public Iterator<String> outputKeyIterator() {
		return this.outputList.keySet().iterator();
	}
}
