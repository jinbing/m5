package log;

public enum LogLevel {
	TRACE(5, "TRACE"), 
	DEBUG(4, "DEBUG"), 
	INFO(3, "INFO"), 
	WARNING(2, "WARNING"), 
	ERROR(1, "ERROR");

	private final int ID;
	private final String name;

	LogLevel(int id, String name) {
		this.ID = id;
		this.name = name;
	}

	public int getId() {
		return ID;
	}

	public String getName() {
		return name;
	}
}