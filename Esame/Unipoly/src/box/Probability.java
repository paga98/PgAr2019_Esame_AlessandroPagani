package box;

import game.GameInfo;
import user.User;

public class Probability extends BoxAdvance{
	public static final String TAG = "probabilita";
	
	public Probability(String name, int money, String message) throws NumberTooLongException {
		super(name, money, message);
	}

		//public methods
		public void gainMoney(User user) {
			int currUserBalance = user.getBalance();
			int sum = currUserBalance + super.getMoney();
			user.setBalance(sum);
			if(sum<=GameInfo.FOR_WIN) {
				System.out.println("You have win "+super.getMoney()+" I€€€.");
			}else {
				//condizione di VITTORIA
				GameInfo.setStatus(false);
				System.out.println("User '"+user.getName()+"' has win the game.");
			}
		}
		//----
}
