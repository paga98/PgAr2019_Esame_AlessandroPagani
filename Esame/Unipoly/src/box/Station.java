package box;

public class Station extends Box {
	public static final String TAG = "stazione";
	
	public Station(String name) {
		super(name.hashCode(),name);
	}
	public Station(int id,String name) {
		super(id,name);
	}
}
