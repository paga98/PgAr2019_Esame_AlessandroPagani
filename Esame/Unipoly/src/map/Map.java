package map;

import java.util.ArrayList;

import box.*;
import java.util.Scanner;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import apxmlmanag.APXMLManagerReader;
import apxmlmanag.IAPXMLReadable;

public class Map implements IAPXMLReadable<Map>{
	public static final String ROOT_NAME = "map";
	
	//properties
	private int id;
	private String name;
	private ArrayList<Box> listOfBoxes;
	private ArrayList<Group> listOfGroups;
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Box> getListOfBoxes() {
		return listOfBoxes;
	}
	public void setListOfBoxes(ArrayList<Box> listOfBoxes) {
		this.listOfBoxes = listOfBoxes;
	}
	
	public ArrayList<Group> getListOfGroups() {
		return listOfGroups;
	}
	public void setListOfGroups(ArrayList<Group> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}
	//constructors
	public Map(String name) {
		setId(name.hashCode());
		setName(name);
		setListOfBoxes(new ArrayList<Box>());
		setListOfGroups(new ArrayList<Group>());
	}
	public Map() {
		//retrieve from file
		getListOfMapFromXMLFile();
	}
	
	//private methods for utils
	private Group getGroupSelectedFromConsole(Scanner s){
		//stampa a schermo la lista dei gruppi
		System.out.println("List of groups: \n Write:");
		ArrayList<Group> tempList = getListOfGroups();
		for(int i=0;i<tempList.size();i++) {
			System.out.println(i + " for "+tempList.get(i).getName());
		}
		//dall'index prende l'istanza di Group
		int index = Integer.parseInt(s.nextLine());
		return tempList.get(index);
	}
	
	//segnalare a schermo i tipi di Box
	private void printTypesOfBox() {
		System.out.println("-"+Station.TAG);
		System.out.println("-"+Probability.TAG);
		System.out.println("-"+Unexpected.TAG);
		System.out.println("-"+Property.TAG);
	}
	
	//metodo per creare la box di default
	private void createStartDefaultBox() {
		System.out.println("Creation of start box...");
		Box b = new Box(0,"Start");
		getListOfBoxes().add(b);
		System.out.println("Start box created.");
	}
	
	//metodo per creare tutte le altre box
	private Box createBox(Scanner s) {
		Box returnedBox = null;
		System.out.println("Name: ");
		String name = s.nextLine();
		System.out.println("Type: ");
		printTypesOfBox();
		String type = s.nextLine();
		switch(type) {
			case Station.TAG:
				returnedBox = new Station(name);
				break;
			case Probability.TAG:
				System.out.println("Message: ");
				String message = s.nextLine();
				System.out.println("Earn I€€€: ");
				int money = Integer.parseInt(s.nextLine());
				try {
					returnedBox = new Probability(name,money,message);
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				break;
			case Unexpected.TAG:
				System.out.println("Message: ");
				String message1 = s.nextLine();
				System.out.println("Lose I€€€: ");
				int money1 = Integer.parseInt(s.nextLine());
				try {
					returnedBox = new Unexpected(name,money1,message1);
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
				break;
			case Property.TAG:
				Group g = getGroupSelectedFromConsole(s);
				System.out.println("Price base: ");
				int pBase = Integer.parseInt(s.nextLine());
				System.out.println("Price House: ");
				int priceHome = Integer.parseInt(s.nextLine());
				System.out.println("Price Hotel: ");
				int priceHotel = Integer.parseInt(s.nextLine());
				System.out.println("Earning base: ");
				int earningBase = Integer.parseInt(s.nextLine());
				System.out.println("Earning Home: ");
				int earningHome = Integer.parseInt(s.nextLine());
				System.out.println("Earning Hotel: ");
				int earningHotel = Integer.parseInt(s.nextLine());
				returnedBox = new Property(name,pBase,priceHome,priceHotel,earningBase,earningHome,earningHotel,g);
				break;
		}
		return returnedBox;
	}
	
	//public method for init (MENU)
	public void initMapBoxesForMenu() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("Creation of groups:");
		while(true) {
			System.out.println("Name: ");
			String name = s.nextLine();
			System.out.println("Moltiplier: ");
			String moltiplier = s.nextLine();
			int moltipli = Integer.parseInt(moltiplier);
			Group g = new Group(name,moltipli);
			getListOfGroups().add(g);
			System.out.println("Continue? (yes/no");
			String yornot = s.nextLine();
			if(yornot.equals("no")) break;
			else continue;
		}
		System.out.println("Successfully created");
		System.out.println("Creation of Boxes:");
		createStartDefaultBox();
		while(true) {
			Box b = createBox(s);
			getListOfBoxes().add(b);
			System.out.println("Continue? (yes/no");
			String yornot = s.nextLine();
			if(yornot.equals("no")) break;
			else continue;
		}
		System.out.println("Boxes created.");
	}
	
	
	//methods for parsing XML
	//non completato
	private Map getListOfMapFromXMLFile(){
		System.out.println("Write here PATH of file xml:");
		Scanner s = new Scanner(System.in);
		String path = s.nextLine();
		APXMLManagerReader<Map> xmlreader = new APXMLManagerReader<Map>(this,path,ROOT_NAME);
		return xmlreader.read().get(0);
	}
	
	@Override
	public ArrayList<Map> parse(Element root) {
		String name = root.getAttribute("title");
		Map map = new Map(name);
		int size = Integer.parseInt(root.getAttribute("size"));
		ArrayList<Box> list = new ArrayList<Box>(size);
		NodeList nodeList = root.getChildNodes();
		return null;
		
	}
}
