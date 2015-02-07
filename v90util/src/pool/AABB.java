package pool;

public class AABB {
	public float x, y, z, w, h, d;

	public AABB(float x, float y, float z, float w, float h, float d) {
		this.setBounds(x, y, z, w, h, d);
	}

	public void setBounds(float x, float y, float z, float w, float h, float d) {
		this.x = x;
		this.y = y;
		this.z = z;

		this.w = w;
		this.h = h;
		this.d = d;
	}
	
	public String toString(){
		return x+" "+y+" "+z+" "+w+" "+h+" "+d;
	}
}
