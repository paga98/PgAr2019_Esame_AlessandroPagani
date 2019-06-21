package box;

public class Group {
	//properties
	private String name;
	private int multiplier;
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	//constructor
	public Group(String name, int multiplier) {
		setName(name);
		setMultiplier(multiplier);
	}
}
