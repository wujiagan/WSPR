package process;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Path;
import model.WebService;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class WSHSP {
	/**
	 * this is the absolute path of the wsdl files
	 */
	private File testDataDir;
	
	/**
	 * this is the absolute path of the query file
	 */
	private File goal;
	
	private SAXReader reader = null;		//建立SAX解析读取
	
	private Map<String, List<WebService>> inputList = new HashMap<String, List<WebService>>();
	private Map<String, List<WebService>> outputList = new HashMap<String, List<WebService>>();
	List<WebService> webServiceList = null;
	
	public WSHSP() {
		this.reader = new SAXReader();
	}
	
	public File getTestDataDir() {
		return testDataDir;
	}

	public void setTestDataDir(File testDataDir) {
		this.testDataDir = testDataDir;
	}

	public File getGoal() {
		return goal;
	}
	
	public void setGoal(File goal) {
		this.goal = goal;
	}
	

	public Map<String, List<WebService>> getInputList() {
		return inputList;
	}

	public void setInputList(Map<String, List<WebService>> inputList) {
		this.inputList = inputList;
	}

	/**
	 * 从wsdl文件中读取并保存在WebService实例中
	 * @param file
	 * @param service_name
	 * @return
	 * @throws DocumentException 
	 */
	public WebService readIeeeWsdl(File file, String service_name) throws DocumentException{
		WebService service = new WebService(service_name);
		Document doc = null;
		doc = reader.read(file);		//读取文档
		Element root = doc.getRootElement();
		for(Iterator iter = root.elementIterator("message"); iter.hasNext();) {//遍历根节点以下所有子节点
			Element massage = (Element) iter.next();
			if(massage.attribute("name").getValue().equals(service_name + "_Request")) {
				for(Iterator theInput = massage.elementIterator("part"); theInput.hasNext();){
					Element inputPart = (Element) theInput.next();
					service.addInput(inputPart.attributeValue("name"));
				}		
			}
			if(massage.attribute("name").getValue().equals(service_name + "_Response")) {
				for(Iterator theInput = massage.elementIterator("part"); theInput.hasNext();){
					Element inputPart = (Element) theInput.next();
					service.addOutput(inputPart.attributeValue("name"));
				}
			}
		}
		//System.out.println(service);
		return service;
	}
	
	/**
	 * 遍历数据集
	 * @param dir
	 * @throws DocumentException 
	 */
	public List<WebService> loadWSDL(File dir) throws DocumentException {
		List<WebService> list = new LinkedList<WebService>(); 
		for(File file : dir.listFiles()) {
			String fileName = file.getName();
			String serviceName = fileName.substring(0, fileName.lastIndexOf('.'));
			list.add(readIeeeWsdl(file, serviceName));
		}
		return list;
	}
	
	/**
	 * 提取query文件信息
	 * @param goalFile
	 * @return
	 * @throws DocumentException 
	 */
	public WebService readIeeeGoal(File goalFile) throws DocumentException {
		if(!goalFile.exists()){
			System.out.println("Failure to open " + goalFile + ".");
			System.exit(1);
		}
		WebService service = null;
		Document doc = null;
		doc = reader.read(goalFile);		//读取文档
		Element root = doc.getRootElement();
		for(Iterator iter = root.elementIterator("CompositionRoutine"); iter.hasNext();) {//遍历根节点以下所有子节点
			Element element = (Element) iter.next();
			String name = element.attribute("name").getValue();
			String input = element.elementText("Provided");
			String output = element.elementText("Resultant");
			service = new WebService(name);
			for(String val : input.split(",")) {
				service.addInput(val.trim());
			}
			for(String val : output.split(",")) {
				service.addOutput(val.trim());
			}
		}
		return service;
	}
	
	/**
	 * check the input file and the goal file, if not null, then invoke the process() 
	 * @throws Exception 
	 */
	public void run() throws Exception {
		if(this.goal == null || this.testDataDir == null) {
			if(this.testDataDir == null)
				System.out.println("You need to enter the directory for the test data");
			if(this.goal == null)
				System.out.println("You need to enter a filename for the goal wsdl");
		}else {
			process();
			System.out.println("end");
		}
	}
	
	public void composeService(WebService goalService) {
		System.out.println("{ goal service:" + goalService.getName() + "}");
		System.out.print("goal input: [ ");
		Iterator<String> itor = goalService.getInputList().keySet().iterator();
		while(itor.hasNext()) {
			String aInput = itor.next();
			System.out.print(aInput + ", ");
		}
		System.out.println("]");
		System.out.print("goal output: [ ");
		itor = goalService.getOutputList().keySet().iterator();
		while(itor.hasNext()) {
			String aOutput = itor.next();
			System.out.print(aOutput + ", ");
		}
		System.out.println("]");
		
		System.out.println("searching ...");
		Path myPath = new Path(this.webServiceList, goalService);

	}
	
	public void process() throws Exception {
		System.out.println("Loading the goal file \""+this.goal+"\"...");
		WebService service = readIeeeGoal(this.goal);
		System.out.print("the goal:" + service);
		System.out.println("Done loaded the goal file of " + this.goal);
		webServiceList = loadWSDL(this.testDataDir);
		Iterator<WebService> itor = webServiceList.iterator();
		while(itor.hasNext()) {
			WebService ws = itor.next();
			Iterator<String> inputItor = ws.getInputList().keySet().iterator();
			while(inputItor.hasNext()) {
				String aInput = inputItor.next();
				if(inputList.containsKey(aInput)) {
					inputList.get(aInput).add(ws);
				}else {
					List<WebService> newList = new LinkedList<WebService>();
					newList.add(ws);
					inputList.put(aInput, newList);
				}
			}
			Iterator<String> outputItor = ws.getInputList().keySet().iterator();
			while(outputItor.hasNext()) {
				String aOutput = outputItor.next();
				if(inputList.containsKey(aOutput)) {
					inputList.get(aOutput).add(ws);
				}else {
					List<WebService> newList = new LinkedList<WebService>();
					newList.add(ws);
					outputList.put(aOutput, newList);
				}
			}
		}
		System.out.println("Loading the WSDL files in \"" + this.testDataDir + "\"...");
	}
}
