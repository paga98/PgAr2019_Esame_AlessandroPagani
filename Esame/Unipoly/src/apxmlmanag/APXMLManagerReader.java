package apxmlmanag;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.w3c.dom.Element;


public class APXMLManagerReader<T extends IAPXMLReadable<T>> extends APXMLManagerBase<T> {
	//private attributes
	private Element root;
	private T context;
	
	//getters and setters
	public Element getElementRoot() {
		return this.root;
	}
	public void setElementRoot(Element root) {
		this.root = root;
	}
	public T getContext() {
		return this.context;
	}
	public void setContext(T context) {
		this.context = context;
	}
	
	//constructor
	public APXMLManagerReader(T context,String filePath, String rootName) {
		super(rootName,filePath);
		setContext(context);
		init();
	}
	//private methods
	private void init() {
		try {
			InputStream f = new FileInputStream(super.getFilePath());
			super.setDocument(super.getDocumentBuilder().parse(f));
			setElementRoot(super.getDocument().getDocumentElement());
			f.close();
		}
		catch(Exception e) {
			is_ok(false);
		}
	}
	
	//public methods
	public ArrayList<T> read() {
		if(super.getDocument()!=null) {
			if(root.getTagName().equals(super.getRootName())) {
				return this.context.parse(root);
			}
			else return null;
		}
		else return null;
	}
}