package box;

import java.util.Scanner;

import game.GameInfo;
import user.User;

public class Property extends Box {
	public static final String TAG = "proprieta";
	
	//properties
	private User owner;
	private int priceBase;
	private int priceHome;
	private int priceHotel;
	private int earningBase;
	private int earningHome;
	private int earningHotel;
	private Group group;
	private PropertyStat propertyStat;
	
	//getters and setters
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public int getPriceBase() {
		return priceBase;
	}
	public void setPriceBase(int priceBase) {
		this.priceBase = priceBase;
	}
	public int getPriceHome() {
		return priceHome;
	}
	public void setPriceHome(int priceHome) {
		this.priceHome = priceHome;
	}
	public int getPriceHotel() {
		return priceHotel;
	}
	public void setPriceHotel(int priceHotel) {
		this.priceHotel = priceHotel;
	}
	public int getEarningBase() {
		return earningBase;
	}
	public void setEarningBase(int earningBase) {
		this.earningBase = earningBase;
	}
	public int getEarningHome() {
		return earningHome;
	}
	public void setEarningHome(int earningHome) {
		this.earningHome = earningHome;
	}
	public int getEarningHotel() {
		return earningHotel;
	}
	public void setEarningHotel(int earningHotel) {
		this.earningHotel = earningHotel;
	}
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	//constructors
	
	public PropertyStat getPropertyStat() {
		return propertyStat;
	}
	public void setPropertyStat(PropertyStat propertyStat) {
		this.propertyStat = propertyStat;
	}
	public Property(String name,int priceBase,int priceHome,int priceHotel,int earnBase,int earnHome,int earnHotel,Group group) {
		super(name.hashCode(),name);
		setOwner(null);
		setPriceBase(priceBase);
		setPriceHome(priceHome);
		setPriceHotel(priceHotel);
		setEarningBase(earnBase);
		setEarningHome(earnHome);
		setEarningHotel(earnHotel);
		setGroup(group);
		setPropertyStat(PropertyStat.base);
	}
	
	
	
	//public  methods
	//metodo che viene invocato nel momento in cui
	//un utente sale su una proprieta'
	@SuppressWarnings("resource")
	public void buy(User user) {
		Scanner s = new Scanner(System.in);
		//se non esiste un proprietario
		if(getOwner()== null) {
			//do la possibilità di acquistare la proprietà
			System.out.println("You wanna buy this property? "+this.getName());
			String result = s.nextLine();
			if(result.equals("no")|| !result.equals("yes")) return;
			//se non si interrompe, la vuole acquistare.
			if(!pay(user,getPriceBase(),false))return;
			//setto l'owner solamente se gli è possibile acquistarla.
			setOwner(owner);
			System.out.println("You have bought this property");
		}
		//se esiste
		else {
			//e sei te il proprietario
			if(getOwner().getId()==user.getId()) {
				//ti do la possibilità di acquistare:
				ownerBuy(user,s);
			}
			else {
				//devi pagare se non sei il proprietario
				PropertyStat pstat = getPropertyStat();
				if(pstat.equals(PropertyStat.base)) {
					// pagamento
					pay(user,getPriceBase(),false);
					//riscossione
					gain(getPriceBase());
				}
				else if(pstat.equals(PropertyStat.home)) {
					//pagamento
					pay(user,getPriceHome(),false);
					//riscossione
					gain(getPriceHome());
				}
				else {
					//pagamento
					pay(user,getPriceHotel(),false);
					//riscossione
					gain(getPriceHotel());
				}
			}
		}
	}
	
	private void ownerBuy(User user,Scanner s) {
		int price = 0;
		//una casa se sei nello stato base
		if(getPropertyStat().equals(PropertyStat.base)) {
			price = getPriceHome();
			System.out.println("You can buy house here. You wanna buy? (y/n)");
			String op = s.nextLine();
			if(op.equals("y")||op.equals("yes")) {
				if(pay(user,price,true)){
					this.setPropertyStat(PropertyStat.home);
					System.out.println("Successfully bought");
				}
				else {
					System.out.println("Failed");
				}
			}
		}
		//un hotel se sei nello stato home
		else if(getPropertyStat().equals(PropertyStat.home)) {
			price = getPriceHotel();
			System.out.println("You can buy hotel here. You wanna buy? (y/n)");
			String op = s.nextLine();
			if(op.equals("y")||op.equals("yes")) {
				if(pay(user,price,true)) {
					this.setPropertyStat(PropertyStat.hotel);
					System.out.println("Successfully bought");
				}
				else {
					System.out.println("Failed");
				}
			}
		}
		//niente se hai già tutto
	}
	
	private void gain(int money) {
		String name = this.getOwner().getName();
		System.out.println("The gamer "+name+" gain "+money+"I€€€.");
		int balance = this.getOwner().getBalance();
		this.getOwner().setBalance(balance + money);
		if(this.getOwner().getBalance()>=GameInfo.FOR_WIN) {
			
			GameInfo.setStatus(false);
			System.out.println("The gamer "+name+" win.");
			//il gioco è finito
		}
	}
	
	public boolean pay(User user,int moneyToLeave,boolean owner) {
		int balance = user.getBalance()-moneyToLeave;
		if(owner)
			if(!checkIfCanBuy(user.getBalance(),getPriceBase()))
				return false;
		user.setBalance((balance - getPriceBase()));
		System.out.println("The gamer "+user.getName()+" pay "+moneyToLeave+"I€€€.");
		if(user.getBalance()<=0)user.setStatus(false);
		return true;
	}
	
	private boolean checkIfCanBuy(int balance, int moneyToLeave) {
		if(balance<=0){
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			System.out.println("Are you sure to do this action? If you do, you will lose. (yes/no)");
			String result = s.nextLine();
			if(result.equals("no")|| !result.equals("yes")) return false;
		}
		return true;
	}
}
