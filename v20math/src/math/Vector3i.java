package math;

import java.util.StringTokenizer;

public class Vector3i {
	public int x, y, z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(Vector3i a) {
		x += a.x;
		y += a.y;
		z += a.z;
	}

	public void mult(Vector3i a) {
		x *= a.x;
		y *= a.y;
		z *= a.z;
	}

	public String println() {
		return "" + x + " " + y + " " + z;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Vector3i)) {
			return false;
		}

		Vector3i v = (Vector3i) o;
		return x == v.x && y == v.y && z == v.z;
	}

	public static Vector3i parse(StringTokenizer tokenizer) {
		int x = Integer.valueOf(tokenizer.nextToken());
		int y = Integer.valueOf(tokenizer.nextToken());
		int z = Integer.valueOf(tokenizer.nextToken());
		return new Vector3i(x, y, z);
	}

	public static Vector3i add(Vector3i a, Vector3i b) {
		return new Vector3i(a.x + b.x, a.y + b.y, a.z + b.z);
	}

	public static Vector3i mult(Vector3i a, Vector3i b) {
		return new Vector3i(a.x * b.x, a.y * b.y, a.z * b.z);
	}
}

/*
 * 	
	public Vector3f increment() {
		return new Vector3f(x++, y++, z++);
	}
	
 * */
