package math;


public enum DirectionEnum {
	SOUTH	(0,new Vector3f(0,0,1)), 
	NORTH	(1,new Vector3f(0,0,-1)), 
	EAST	(2,new Vector3f(1,0,0)), 
	WEST	(3,new Vector3f(-1,0,0)),
	UP		(4,new Vector3f(0,1,0)) ,
	DOWN	(5,new Vector3f(0,-1,0));

	private int ID;
	private Vector3f normal;

	DirectionEnum(int ID, Vector3f normal) {
		this.ID = ID;
		this.normal = normal;
	}

	public int getID() {
		return ID;
	}

	public Vector3f getNormal() {
		return normal;
	}
}
