package service.manager;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManager {
	
	private static String scanPackageForAnno = "services";
	private static Map<Command, Method> services;
	
	static{init();}
	
	public static void init(){
		System.out.println("Service Registration, please wait...");
		long currentTimeInMillis = System.currentTimeMillis();

		services = new ConcurrentHashMap<>();
		
		List<Method> serviceMethods = AnnotationScanner.getMethodsForAnnotation(scanPackageForAnno, Service.class);
		for(Method m : serviceMethods){
			Command command = m.getAnnotation(Service.class).value();
			if(services.containsKey(command))
				throw new RuntimeException("ServiceId already in use = " + command);
			
			services.put(command, m);
			System.out.println(command.intVal + " = " + m.getName());
		}
		
		System.out.println("Service Registration, completed in " + (System.currentTimeMillis() - currentTimeInMillis) + " millis");
	}
	
	//Doing this via the server manager, because this is the general point for doing parsing, like automatically creating a json object for the method to call etc etc
	public static void executeService(String someObjectCanBeAnything){
		Command command = Command.values[Integer.parseInt(someObjectCanBeAnything.substring(0, 1))];
		try{
			System.out.println("ServiceManager.executeService()" + command);
			services.get(command).invoke(null, someObjectCanBeAnything);
		}catch(Exception ex){
			//Generally this is where errors are reported to the user in some fashion, one could easily create an object with an error code and return the message
			//this allows for global handling of exceptions
			ex.printStackTrace();
		}
	}
}
