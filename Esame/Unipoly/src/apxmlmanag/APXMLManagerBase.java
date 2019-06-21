package apxmlmanag;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class APXMLManagerBase<T>
{
	//private attributes
	private String filePath;
	private Document document;
	private boolean is_ok = true;
	private String rootName;
	private DocumentBuilder documentBuilder;
	
	//getters and setters
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public boolean is_ok() {
		return is_ok;
	}
	public void is_ok(boolean is_ok) {
		this.is_ok = is_ok;
	}
	public String getRootName() {
		return rootName;
	}
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
	public DocumentBuilder getDocumentBuilder() {
		return documentBuilder;
	}
	public void setDocumentBuilder(DocumentBuilder documentBuilder) {
		this.documentBuilder = documentBuilder;
	}
	
	
	//constructor
	public APXMLManagerBase(String rootName,String filePath){
		setRootName(rootName);
		setFilePath(filePath);
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			setDocumentBuilder(documentFactory.newDocumentBuilder());
		}
		catch(Exception e) {
			is_ok(false);
		}
	}
	
}