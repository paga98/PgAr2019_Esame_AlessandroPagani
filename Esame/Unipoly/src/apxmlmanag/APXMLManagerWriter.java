package apxmlmanag;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class APXMLManagerWriter<T extends IAPXMLWriteable<T>> extends APXMLManagerBase<T> {
	//private attributes
	private T context;
	private boolean visualizeNumRowsInitially;
	
	//getters and setters
	public T getContext(){
		return this.context;
	}
	public void setContext(T context) {
		this.context = context;
	}
	
	public boolean visualizeNumRowsInitially() {
		return this.visualizeNumRowsInitially;
	}
	public void visualizeNumRowsInitially(boolean v) {
		this.visualizeNumRowsInitially = v;
	}
	
	//constructor
	public APXMLManagerWriter(T context, String filePath, String rootName,boolean visualizeNumRowsInitially) {
		super(rootName,filePath);
		setContext(context);
		visualizeNumRowsInitially(visualizeNumRowsInitially);
		//create document
		super.setDocument(getDocumentBuilder().newDocument());
		super.getDocument().setXmlStandalone(true);
	}
	
	//public method
	public boolean write(ArrayList<T> list_to_write) {
		//check if is all OK
		if(is_ok()) {
			 //create root
	        // root element
	        Element root = getDocument().createElement(super.getRootName());
	        if(visualizeNumRowsInitially) {
		        Attr attr = getDocument().createAttribute("numero");
	            attr.setValue(Integer.toString(list_to_write.size()));
	            root.setAttributeNode(attr);
	        }
	        getContext().parse(getDocument(),root,list_to_write);
	        //save file
	        try {
	        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        	Transformer transformer = transformerFactory.newTransformer();
	        	DOMSource domSource = new DOMSource(getDocument());
	        	StreamResult streamResult = new StreamResult(new File(this.getFilePath()));
	            transformer.transform(domSource, streamResult);
	            System.out.println("Scrittura terminata");
	        }
	        catch(Exception ex) {
	        	return false;
	        }
	        return true;
		}else return false;
	}
}