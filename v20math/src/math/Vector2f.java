package math;

public class Vector2f {
	public static final Vector2f ZERO = new Vector2f(0, 0);
	public float x, y;

	public Vector2f(float x, float y) {
		set(x, y);
	}

	public Vector2f set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public String println() {
		return "" + x + " " + y;
	}

	public float[] toArray() {
		return new float[] { x, y };
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vector2f) {
			Vector2f v = (Vector2f) o;
			return x == v.x && y == v.y;
		}

		return false;
	}
}
