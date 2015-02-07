package math;

public class MathUtil {
	public static Vector3f AXIS_x = new Vector3f(1.0f, 0.0f, 0.0f);
	public static Vector3f AXIS_Y = new Vector3f(0.0f, 1.0f, 0.0f);
	public static Vector3f AXIS_Z = new Vector3f(0.0f, 0.0f, 1.0f);

	public final static float D_090 = 090F;
	public final static float D_180 = 180F;
	public final static float D_360 = 360F;	

	public final static float R_PI = 3.14159265359f;
	public final static float R_PI_HALF = R_PI / 2;

	public final static float REGREE_TO_RADIANS= R_PI / D_180;
	public final static float RADIANS_TO_REGREE = D_180 / R_PI;

	public static float radians(float degrees) {
		return REGREE_TO_RADIANS * degrees;
	}

	public static float degrees(float radians) {
		return RADIANS_TO_REGREE * radians;
	}

	public static float clamp(float value, float low, float high) {
		return Math.min(Math.max(value, low), high);
	}

	public static int floor(double par0) {
		int i = (int) par0;
		return par0 < (double) i ? i - 1 : i;
	}

	public static int fold2(int fold) {
		int ret = 2;
		while (ret < fold) {
			ret *= 2;
		}
		return ret;
	}

	public static int ceilOfSqrt(int in) {
		return (int) Math.ceil(Math.sqrt(in));
	}

	public static int ceilPower2(int in) {
		int j = in - 1;
		j |= j >> 1;
		j |= j >> 2;
		j |= j >> 4;
		j |= j >> 8;
		j |= j >> 16;
		return j + 1;
	}

	public static int ceilPower2OfSqrt(int in) {
		return ceilPower2(ceilOfSqrt(in));
	}

	public static int power(int a, int b) {
		int p = 1;
		for (int i = 0; i < b; i++) {
			p = p * a;
		}
		return p;
	}
}
