package box;

public class BoxAdvance extends Box {
	//static final fields
		public static final int MAX = 1000000;
		public static final int MIN = 1;
		//---
		
		//properties
		private int money;
		private String message;
		//---
		
		//getters and setters
		public int getMoney() {
			return money;
		}
		public void setMoney(int money) {
			this.money = money;
		}

		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		//---
		
		//constructor
		public BoxAdvance(String name,int money,String message) throws NumberTooLongException {
			super(name.hashCode(),name);
			if(!checkIfIsOK(money)) throw new NumberTooLongException();
			setMoney(money);
			setMessage(message);
		}
		//---
		
		//private methods for utility
		private boolean checkIfIsOK(int money) {
			if(money>=MIN && money <=MAX) return true;
			else return false;
		}
		//----
}
