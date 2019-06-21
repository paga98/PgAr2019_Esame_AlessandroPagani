package box;

import user.User;
import game.GameInfo;

public class Unexpected extends BoxAdvance {
	public static final String TAG = "imprevisto";
	public Unexpected(String name, int money, String message) throws NumberTooLongException {
		super(name, money, message);
	}

	//public methods
	public void loseMoney(User user) {
		int currUserBalance = user.getBalance();
		int diff = currUserBalance - super.getMoney();
		if(diff>GameInfo.FOR_LOSE) {
			user.setBalance(diff);
			System.out.println("You have lose "+super.getMoney()+" I€€€.");
		}else {
			user.setBalance(0);
			//condizione di sconfitta
			user.setStatus(false);
			System.out.println("User '"+user.getName()+"' has lose the game.");
		}
	}
	//----
	
}
