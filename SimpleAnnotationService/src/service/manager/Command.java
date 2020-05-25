package service.manager;

public enum Command {
	PRINT_FOO(0),
	PRINT_BAR(2),//Non sequential
	PRINT_BAZ(3);
	
	public int intVal;
	Command(int intVal){
		this.intVal = intVal;
	}
	
	/**
	 * Functionality to ascertain an enum from an int
	 * The static block initializes the array indexes to correspond with it's according ordinal value
	 * Simply use Command.values[index] or get the int value by e.g. Command.PRINT_FOO.intVal;
	 * */
	public static Command values[];
	static{
		int maxVal = -1;
		for(Command cmd : Command.values())
			if(maxVal < cmd.intVal)
				maxVal = cmd.intVal;
		
		values = new Command[maxVal + 1];
		
		for(Command cmd : Command.values())
			values[cmd.intVal] = cmd;
	}
}
