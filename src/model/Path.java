package model;

import java.util.Iterator;
import java.util.List;

public class Path {
	/**
	 * Whole set of web services
	 */
	private List<WebService> availableServices;
	
	/**
	 * Output of this service is our goal
	 */
	private WebService goalService;
	
	private WebService pathService;
	
	public Path(List<WebService> availableServices, WebService goalService) {
		this.availableServices = availableServices;
		this.goalService = goalService;
		this.pathService = new WebService("Path: ");
		for(Iterator<String> itor = this.goalService.inputKeyIterator(); itor.hasNext();) {
			this.pathService.addInput(itor.next());
		}
	}
	
	public void searchServices() {
		int depth = 1;
		
	}
	
	public boolean forwardReasoning(int depth) {
		return false;
	}
	
	public boolean backwardPlanning() {
		return false;
	}
	
	public int findNext(int depth) {
		return 0;
	}
}
