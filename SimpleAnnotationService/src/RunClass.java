import service.manager.ServiceManager;

public class RunClass {
	
	public static void main(String[] args){
		long nowMillis = System.currentTimeMillis();
		ServiceManager.executeService("0 <- For this example the frst character is the command");
		ServiceManager.executeService("2 Another Example");
		System.out.println("RunClass.main()" + (System.currentTimeMillis() - nowMillis));
	}
}
