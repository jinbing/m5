package math;

import java.util.StringTokenizer;


public class Vector4f {
	public static final Vector4f ZERO = new Vector4f(0, 0, 0, 0);
	
	public float x, y, z, w;
	
	public Vector4f(float x, float y, float z, float w) {
		set(x, y, z, w);
	}

	public Vector4f(Vector3f vec, float w) {
		set(vec.x, vec.y, vec.z, w);
	}

	public Vector4f(Vector4f vec) {
		set(vec);
	}
	
	public Vector4f add(float x, float y, float z, float w) {
		this.x += x;
		this.y += y;
		this.z += z;
		this.w += w;
		return this;
	}
	
	public Vector4f add(Vector4f vec) {
		return add(vec.x, vec.y, vec.z, vec.w);
	}
	
	public Vector4f sub(float x, float y, float z, float w) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		this.w -= w;
		return this;
	}
	
	public Vector4f sub(Vector4f vec) {
		return sub(vec.x, vec.y, vec.z, vec.w);
	}
	
	public Vector4f mult(float f) {
		return mult(f, f, f, f);
	}
	
	public Vector4f mult(float x, float y, float z, float w) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		this.w *= w;
		return this;
	}
	
	public Vector4f mult(Vector4f vec) {
		return mult(vec.x, vec.y, vec.z, vec.w);
	}
	
	public Vector4f divide(float f) {
		return divide(f, f, f, f);
	}

	public Vector4f divide(Vector4f vec) {
		return divide(vec.x, vec.y, vec.z, vec.w);
	}

	public Vector4f divide(float x, float y, float z, float w) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		this.w /= w;
		return this;
	}

	public float length() {
		return (float)Math.sqrt(x * x + y * y + z * z);
	}
	

	public Vector4f set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}
	
	public Vector4f set(Vector4f vec) {
		return set(vec.x, vec.y, vec.z, vec.w);
	}
	
	public static float distance(Vector4f a, Vector4f b) {
		return sub(a, b).length();
	}
	
	public Vector4f copy() {
		return new Vector4f(this);
	}

	public float[] toArray() {
		return new float[] { x, y, z, w };
	}

	public Vector4f reset() {
		x = y = z = w = 0;
		return this;
	}
	
	public Vector4f normalize() {
		float length = length();
		if (length > 0) {
			x /= length;
			y /= length;
			z /= length;
			w /= length;
		}
		return this;
	}
	

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vector4f)) {
			return false;
		}
		Vector4f v = (Vector4f) o;
		return x == v.x && y == v.y && z == v.z && w == v.w;
	}

	
	public String println() {
		return "" + x + " " + y + " " + z + " " + w;
	}
	
	public static Vector4f parse(StringTokenizer tokenizer) {
		float x = Float.valueOf(tokenizer.nextToken());
		float y = Float.valueOf(tokenizer.nextToken());
		float z = Float.valueOf(tokenizer.nextToken());
		float w = Float.valueOf(tokenizer.nextToken());
		return new Vector4f(x, y, z, w);
	}

	//--------------------------------------------------------------todo
	public static Vector4f cross(Vector4f a, Vector4f b) {
		float ww = a.w * b.w - a.x * b.x - a.y * b.y - a.z * b.z;
		float xx = a.w * b.x + a.x * b.w + a.z * b.y - a.y * b.z;
		float yy = a.w * b.y + a.y * b.w + a.x * b.z - a.z * b.x;
		float zz = a.w * b.z + a.z * b.w + a.y * b.x - a.x * b.y;
		return new Vector4f(xx, yy, zz, ww);
	}

	public static Vector4f add(Vector4f a, Vector4f b) {
		return new Vector4f(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
	}
	
	public static Vector4f sub(Vector4f a, Vector4f b) {
		return new Vector4f(a.x - b.x, a.y - b.y, a.z - b.z, a.w - b.w);
	}
	
	public static Vector4f mult(Vector4f a, Vector4f b) {
		return new Vector4f(a.x * b.x, a.y * b.y, a.z * b.z, a.w * b.w);
	}
	
	public static float dot(Vector4f a, Vector4f b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
}



/*	public Vector4f set(Vector2f vec) {
	return set(vec, 0, 0);
}

public Vector4f set(Vector2f vec, float z, float w) {
	return set(vec.x, vec.y, z, w);
}

public Vector4f set(Vector3f vec) {
	return set(vec, 0);
}

public Vector4f set(Vector3f vec, float w) {
	return set(vec.x, vec.y, vec.z, w);
}*/
