package math;

import java.util.StringTokenizer;

public class Vector3f {
	public static final Vector3f ZERO = new Vector3f(0, 0, 0);
	public static final Vector3f ONE = new Vector3f(1, 1, 1);

	public float x, y, z;

	public Vector3f(float x, float y, float z) {
		set(x, y, z);
	}

	public Vector3f add(Vector3f vec) {
		return add(vec.x, vec.y, vec.z);
	}

	public Vector3f add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3f sub(Vector3f vec) {
		return sub(vec.x, vec.y, vec.z);
	}

	public Vector3f sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector3f mult(float f) {
		return mult(f, f, f);
	}

	public Vector3f mult(Vector3f vec) {
		return mult(vec.x, vec.y, vec.z);
	}

	public Vector3f mult(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector3f divide(float f) {
		return divide(f, f, f);
	}

	public Vector3f divide(Vector3f vec) {
		return divide(vec.x, vec.y, vec.z);
	}

	public Vector3f divide(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vector3f set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public float[] get() {
		return new float[] { this.x, this.y, this.z };
	}

	// -----------------------------------------------------------------------------------------
	public Vector3f copy() {
		return new Vector3f(x, y, z);
	}

	public float[] toArray() {
		return new float[] { x, y, z };
	}

	public Vector3f reset() {
		x = y = z = 0;
		return this;
	}

	public Vector3f normalize() {
		float length = length();
		if (length > 0) {
			x /= length;
			y /= length;
			z /= length;
		}
		return this;
	}

	public boolean equals(Object o) {
		if (o instanceof Vector3f) {
			Vector3f v = (Vector3f) o;
			return x == v.x && y == v.y && z == v.z;
		}

		return false;
	}

	public String println() {
		return "" + x + " " + y + " " + z;
	}

	public static Vector3f parse(StringTokenizer tokenizer) {
		float x = Float.valueOf(tokenizer.nextToken());
		float y = Float.valueOf(tokenizer.nextToken());
		float z = Float.valueOf(tokenizer.nextToken());
		return new Vector3f(x, y, z);
	}

	// --------------------------------------------------------------only for
	// vector 3f
	public static Vector3f cross(Vector3f a, Vector3f b) {
		return new Vector3f(a.y * b.z - b.y * a.z, a.z * b.x - b.z * a.x, a.x * b.y - b.x * a.y);
	}

	public static float dot(Vector3f a, Vector3f b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	public static Vector3f add(Vector3f a, Vector3f b) {
		return new Vector3f(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector3f sub(Vector3f a, Vector3f b) {
		return new Vector3f(a.x - b.x, a.y - b.y, a.z - b.z);
	}

	public static Vector3f mult(Vector3f a, Vector3f b) {
		return new Vector3f(a.x * b.x, a.y * b.y, a.z * b.z);
	}

	public static float distance(Vector3f a, Vector3f b) {
		return sub(a, b).length();
	}
}


/*	
public Vector3f set(Vector2f vec) {
	return set(vec.x, vec.y, 0);
}

public Vector3f set(Vector2f vec, float z) {
	return set(vec.x, vec.y, z);
}

public Vector3f set(Vector3f vec) {
	return set(vec.x, vec.y, vec.z);
}

public Vector3f set(Vector4f vec) {
	return set(vec.x, vec.y, vec.z);
}
	
    public double getRadiansX(){
   	 return Math.toRadians(x);
   }
   
   public double getRadiansY(){
  	 return Math.toRadians(y);
  }
*/
