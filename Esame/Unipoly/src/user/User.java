package user;

public class User {
	//properties
	private int id;
	private String name;
	private int balance;
	private boolean status;//true --> in game
	private int position = 0;
	
	public static final int INITIAL_BALANCE = 1000;
	
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
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	//constructors
	public User(int id,String name,int balance) {
		setId(id);
		setName(name);
		setBalance(balance);
		setStatus(true);
	}
	public User(String name,int balance) {
		setId(name.hashCode());
		setName(name);
		setBalance(balance);
		setStatus(true);
	}
}
