package TestDesign_singleton;

public class UseSingletonClassDemo {
	 
		
		public static void main(String[] args) {
			
			SingletonClassDemo s1 = SingletonClassDemo.getInstance();
			s1.showMessage();
			System.out.println(s1);
			
			SingletonClassDemo s2 = SingletonClassDemo.getInstance();
			s2.showMessage();
			System.out.println(s2);
			
		}
		
	}

