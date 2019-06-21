package game;

import java.util.Scanner;

import box.*;
import map.Map;
import user.User;

import java.util.ArrayList;
import java.util.Random;

public class GameInfo {
	//money for lose and for win
	public static final int FOR_WIN = 1000000;
	public static final int FOR_LOSE = 0;
	// true-- game on, false --> there is a winner, game stop
	private static boolean status = true;
	private static int userIndexTurn = 0;
	
	private Map map;
	private ArrayList<Map> listAllMaps;
	private ArrayList<User> listOfUsers;

	public static boolean getStatus() {
		return status;
	}
	public static void setStatus(boolean s) {
		status = s;
	}
	
	public GameInfo() {listOfUsers=new ArrayList<User>();listAllMaps = new ArrayList<Map>();}
	
	
	//genera numeri random da 1 a 6 compresi (dado)
	private int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(5) + 1;
		return number;
	}
	void initializeGame() {
		Scanner s = new Scanner(System.in);
		//INIT MAP
		System.out.println("Initialization...");
		//init users
		initializeUsers(s);
		while(true) {
			//init map
			Map tempMap = null;
			System.out.println("Write:");
			System.out.println("1: Get Map from file");
			System.out.println("2: Create new Map");
			String op1 = s.nextLine();
			//create map from file
			if(op1.equals("1")) tempMap = new Map();
			//create new map 
			else if(op1.equals("2")) {
				System.out.println("Creation of Map:");
				System.out.println("Name: ");
				String name = s.nextLine();
				tempMap = new Map(name);
				System.out.println("Map created.");
				tempMap.initMapBoxesForMenu();
				System.out.println("Totally created.");
			}else continue;
			listAllMaps.add(tempMap);
			System.out.println("Continue? (yes/no)");
			String yornot = s.nextLine();
			if(yornot.equals("no")) break;
			else continue;
		}
		
		//selection of map
		System.out.println("Selection of map.");
		System.out.println("What map you wanna select?");
		listOfMapTConsole();
		int mapselectedIndex = Integer.parseInt(s.nextLine());
		//now set selected map
		this.map = listAllMaps.get(mapselectedIndex);
		System.out.println("You have selected the map "+this.map.getName());
	}
	
	//method that show all names of map
	private void listOfMapTConsole() {
		for(int i=0;i<listAllMaps.size();i++) {
			System.out.println(i+" for "+listAllMaps.get(i).getName());
		}
	}
	
	private void initializeUsers(Scanner s) {
		int initBalance = 0;
		//do la possibilità all'utente di scegliere la balance iniziale
		System.out.println("Initial balance for all users: ");
		try {
			initBalance = Integer.parseInt(s.nextLine());
		}catch(Exception ex) {
			System.out.println("Insert a valid number positive");
			initializeUsers(s);
		}
		while(true) {
			System.out.println("Name: ");
			String name = s.nextLine();
			//setto l'utente e vedo se vuole aggiungere nuovi utenti o no
			listOfUsers.add(new User(name,initBalance));
			System.out.println("Continue? (yes/no)");
			String yornot = s.nextLine();
			if(yornot.equals("no")) break;
			else continue;
		}
	}
	
	public void loop() {
		checkIfIsOneUser();
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		if(userIndexTurn==listOfUsers.size()) {
			//se la index della lista degli utenti raggiunge il size degli utenti
			//ritorna 0
			userIndexTurn = 0;
		}
		User u = listOfUsers.get(userIndexTurn);
		System.out.println("Turn of "+u.getName());
		System.out.println("Roll the die? (press any key and click enter)");
		s.nextLine();
		//posplus --> posizioni da aggiungere a quella precedente
		int posplus = randomNumber();
		System.out.println("The number is: ..."+ posplus);
		changePosition(posplus,u);
		userIndexTurn++;
	}
	
	//controlla se un solo giocatore è presente 
	//se si, è il vincitore e il gioco si ferma
	private boolean checkIfIsOneUser() {
		int i = 0;
		for(User u : listOfUsers) {
			if(u.getStatus()) i++;
		}
		if(i<=1) { status = false; return false; }
		return true;
	}
	
	//metodo per cambiare posizione
	//toAdd --> posizioni in +
	//user
	public void changePosition(int toAdd,User u) {
		//this method do the action of rispettive box in index
		ArrayList<Box> list = map.getListOfBoxes();
		int userPosition = u.getPosition();
		int i_rem = (list.size()-1) - (userPosition+toAdd);
		if(i_rem>=0) {
			//tutto ok
			u.setPosition(userPosition + toAdd);
		}
		else {
			//ricomincia da 1 + i_rem
			u.setPosition(userPosition + Math.abs(toAdd));
		}
		//prende la Box e 'switcha' il proprio Type 
		Box b = map.getListOfBoxes().get(u.getPosition());
		if(b instanceof Station) {
			int id = b.getId();
			//trovo una lista di stazioni (n-1) meno quella base
			ArrayList<Station> tempList = new ArrayList<Station>();
			for(Box bstation : map.getListOfBoxes()) {
				if(bstation instanceof Station && bstation.getId()!=id) {
					//parse Box --> Station
					tempList.add((Station)bstation);
				}
			}
			System.out.println("Where you wanna go?");
			System.out.println("Write:");
			for(int i=0;i<tempList.size();i++) {
				System.out.println(i+" for "+tempList.get(i).getName());
			}
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			//prendo l'index della templist
			int indexOfTempList = Integer.parseInt(s.nextLine());
			userMoveToStation(u,tempList.get(indexOfTempList));
		}
		else if(b instanceof Probability) {
			Probability inst2 = (Probability)b;
			inst2.gainMoney(u);
		}
		else if(b instanceof Unexpected) {
			Unexpected inst3 = (Unexpected)b;
			inst3.loseMoney(u);
		}
		else if(b instanceof Property) {
			Property inst4 = (Property)b;
			inst4.buy(u);
		}
		//per tutto il resto eseguo dei metodi propri delle rispettive classi
	}
	
	//metodo per muovere un user da una stazione ad un'altra
	private void userMoveToStation(User u,Station s) {
		//trovo la index dall'oggetto
		int index = map.getListOfBoxes().indexOf((Box)s);
		//setto la posizione
		u.setPosition(index);
	}
	
}
