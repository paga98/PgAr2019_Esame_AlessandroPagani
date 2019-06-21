package apxmlmanag;

public interface IAPXMLReadable<T>{
	java.util.ArrayList<T> parse(org.w3c.dom.Element element);
}