package TestDesign_singleton;

public class SingletonClassDemo {

	
	private static SingletonClassDemo instance = null;
	private SingletonClassDemo() {}
	public static SingletonClassDemo getInstance() {
	    if (instance == null) {
	        instance = new SingletonClassDemo();
	    }
	    return instance;
	}
	public void showMessage() {
	    System.out.println("Hello World!");
	}
	
	
}	
