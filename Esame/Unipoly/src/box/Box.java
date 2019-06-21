package box;


public class Box {
	//properties
	private int id;
	private String name;
	//--
	
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
	//---
	
	//constructor
	public Box(int id,String name) {
		setId(id);
		setName(name);
	}
	//--
}
