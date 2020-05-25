package services;

import service.manager.Command;
import service.manager.Service;

public class TestServices {
	
	@Service(Command.PRINT_FOO)
	public static String printFoo(String someContent){
		return someContent + " was processed via foo";
	}
	
	@Service(Command.PRINT_BAR)
	public static String printBar(String someContent){
		return someContent + " was processed via bar";
	}
	
	public static String notservicemethod(){
		return "";
	}
}
