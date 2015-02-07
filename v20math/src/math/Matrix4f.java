package math;

public class Matrix4f {
	public float[] data;
	private float[] IDENTITY_DATA = {1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1}; 

	public Matrix4f() {
		this.data = new float[16];
	}
	
	public Matrix4f(Matrix4f mat) {
		this.data = new float[16];
		System.arraycopy(mat.data, 0, this.data,0,16);
	}

	public void toIdentity() {
		System.arraycopy(IDENTITY_DATA, 0, this.data,0,16);
	}

	public void toTranslate(Vector3f v) {
		System.arraycopy(IDENTITY_DATA, 0, this.data,0,16);
		data[12] = v.x;
		data[13] = v.y;
		data[14] = v.z;
	}

	public void toTranslate(float dx, float dy, float dz) {
		System.arraycopy(IDENTITY_DATA, 0, this.data,0,16);
		data[12] = dx;
		data[13] = dy;
		data[14] = dz;
	}

	public void toRotate(float angle, Vector3f v) {
		v.normalize();
		this.toRotate(angle, v.x, v.y, v.z);
	}

	public void toRotate(float angle, float x, float y, float z) {
		float s = (float) Math.sin(angle);
		float c = (float) Math.cos(angle);
		float m = 1 - c;
		data[0x0] = m * x * x + c;
		data[0x1] = m * x * y - z * s;
		data[0x2] = m * z * x + y * s;
		data[0x3] = 0;
		data[0x4] = m * x * y + z * s;
		data[0x5] = m * y * y + c;
		data[0x6] = m * y * z - x * s;
		data[0x7] = 0;
		data[0x8] = m * z * x - y * s;
		data[0x9] = m * y * z + x * s;
		data[0xA] = m * z * z + c;
		data[0xB] = 0;
		data[0xC] = 0;
		data[0xD] = 0;
		data[0xE] = 0;
		data[0xF] = 1;
	}

	void toFrustum(float left, float right, float bottom, float top, float znear, float zfar) {
		float width = right - left;
		float height = top - bottom;
		float deep = zfar - znear;
		data[0x0] = 2 * znear / width;
		data[0x1] = 0;
		data[0x2] = 0;
		data[0x3] = 0;
		data[0x4] = 0;
		data[0x5] = 2 * znear / height;
		data[0x6] = 0;
		data[0x7] = 0;
		data[0x8] = (right + left) / width;
		data[0x9] = (top + bottom) / height;
		data[0xA] = (-zfar - znear) / deep;
		data[0xB] = -1.0f;
		data[0xC] = 0;
		data[0xD] = 0;
		data[0xE] = (-2 * znear * zfar) / deep;
		data[0xF] = 0;
	}

	public void toPerspective(float fov, float aspect, float znear, float zfar) {
		float ymax, xmax;
		ymax = (float) (znear * Math.tan(fov * Math.PI / 360.0));
		xmax = ymax * aspect;
		toFrustum(-xmax, xmax, -ymax, ymax, znear, zfar);
	}

	public void toOrtho(float left, float right, float bottom, float top, float near, float far) {
		data[0x0] = 2 / (right - left);
		data[0x1] = 0;
		data[0x2] = 0;
		data[0x3] = 0;
		data[0x4] = 0;
		data[0x5] = 2 / (top - bottom);
		data[0x6] = 0;
		data[0x7] = 0;
		data[0x8] = 0;
		data[0x9] = 0;
		data[0xA] = -2 / (far - near);
		data[0xB] = 0;
		data[0xC] = -(right + left) / (right - left);
		data[0xD] = -(top + bottom) / (top - bottom);
		data[0xE] = -(far + near) / (far - near);
		data[0xF] = 1;
	}

	public void toOrtho(int width, int height) {
		toOrtho(0, width, 0, height, -1, 1);
	}	
	
	public void scale(Vector3f vec) {
		data[0x00] = data[0x00 ]* vec.x;
		data[0x01] = data[0x01 ]* vec.x;
		data[0x02] = data[0x02 ]* vec.x;
		data[0x03] = data[0x03 ]* vec.x;
		
		data[0x04] = data[0x04 ]* vec.y;
		data[0x05] = data[0x05 ]* vec.y;
		data[0x06] = data[0x06 ]* vec.y;
		data[0x07] = data[0x07 ]* vec.y;
		
		data[0x08] = data[0x08 ]* vec.z;
		data[0x09] = data[0x09 ]* vec.z;
		data[0x0a] = data[0x0a ]* vec.z;
		data[0x0b] = data[0x0b ]* vec.z;
	}

	public static Matrix4f get3DOrtho(float left, float right, float bottom, float top, float near, float far) {
		Matrix4f a = new Matrix4f();
		a.toOrtho(left, right, bottom, top, near, far);
		return a;
	}
	
	public static Matrix4f get2DMatrix(int width, int height) {
		Matrix4f a = new Matrix4f();
		a.toOrtho(width, height);
		return a;
	}

	public static Matrix4f get3DMatrix(Vector3f p, float rxDegree, float ryDegree, int width, int height, float fov, float znear, int radius) {
		return Matrix4f.get3DMatrix(p, rxDegree, ryDegree, width, height, fov, znear, radius, false);
	}
	
	public static Matrix4f getItemMatrix(int screenWidth, int screenHeight) {
		Matrix4f a = new Matrix4f();
		Matrix4f b = new Matrix4f();
		float aspect = (float) screenWidth / screenHeight;
		float size = 32;
		float box = screenHeight / size /2;
		float xoffset = 1 - size / screenWidth * 2;
		float yoffset = 1 - size / screenHeight * 2;

		a.toIdentity();

		b.toTranslate( 0, 0, -1);
		a = mul(b, a);
		
		b.toRotate(MathUtil.R_PI / 4, 0, 1, 0);
		a = mul(b, a);

		b.toRotate(-MathUtil.R_PI / 10, 1, 0, 0);
		a = mul(b, a);

		b.toOrtho(-box * aspect, box * aspect, -box, box, -1, 1);
		a = mul(b, a);

		b.toTranslate(-xoffset, -yoffset, 0);
		a = mul(b, a);

		return a;
	}
	
	public static Matrix4f get3DMatrix(Vector3f p, float rxDegree, float ryDegree, int width, int height, float fov, float znear, float zfar, boolean isOrtho) {
		float rx =MathUtil.radians(rxDegree);
		float ry = MathUtil.radians(ryDegree);
		Matrix4f a = new Matrix4f();
		Matrix4f b = new Matrix4f();
		float aspect = (float) width / height;
		//float zfar = radius * 16 + 64;
		a.toIdentity();

		b.toTranslate(-p.x, -p.y, -p.z);
		a = mul(b, a);
		
		b.toRotate(-ry, -(float) Math.cos(rx), 0, -(float) Math.sin(rx));
		a = mul(b, a);

		b.toRotate(-rx, 0, 1, 0);
		a = mul(b, a);
		
		if(isOrtho){
			int size=32;
			b.toOrtho(-size*aspect, size*aspect, -size, size, -100,200);
		}else{
			b.toPerspective(fov, aspect, znear, zfar);
		}

		a = mul(b, a);
		
		b.toIdentity();
		return a;
	}

	public static Matrix4f mul(Matrix4f left, Matrix4f right) {
		Matrix4f dest = new Matrix4f();
		float[] RD = right.data;
		float[] LD = left.data;
		float[] DD = dest.data;

		DD[0x0] = LD[0x0] * RD[0x0] + LD[0x4] * RD[0x1] + LD[0x8] * RD[0x2] + LD[0xC] * RD[0x3];
		DD[0x1] = LD[0x1] * RD[0x0] + LD[0x5] * RD[0x1] + LD[0x9] * RD[0x2] + LD[0xD] * RD[0x3];
		DD[0x2] = LD[0x2] * RD[0x0] + LD[0x6] * RD[0x1] + LD[0xA] * RD[0x2] + LD[0xE] * RD[0x3];
		DD[0x3] = LD[0x3] * RD[0x0] + LD[0x7] * RD[0x1] + LD[0xB] * RD[0x2] + LD[0xF] * RD[0x3];
		DD[0x4] = LD[0x0] * RD[0x4] + LD[0x4] * RD[0x5] + LD[0x8] * RD[0x6] + LD[0xC] * RD[0x7];
		DD[0x5] = LD[0x1] * RD[0x4] + LD[0x5] * RD[0x5] + LD[0x9] * RD[0x6] + LD[0xD] * RD[0x7];
		DD[0x6] = LD[0x2] * RD[0x4] + LD[0x6] * RD[0x5] + LD[0xA] * RD[0x6] + LD[0xE] * RD[0x7];
		DD[0x7] = LD[0x3] * RD[0x4] + LD[0x7] * RD[0x5] + LD[0xB] * RD[0x6] + LD[0xF] * RD[0x7];
		DD[0x8] = LD[0x0] * RD[0x8] + LD[0x4] * RD[0x9] + LD[0x8] * RD[0xA] + LD[0xC] * RD[0xB];
		DD[0x9] = LD[0x1] * RD[0x8] + LD[0x5] * RD[0x9] + LD[0x9] * RD[0xA] + LD[0xD] * RD[0xB];
		DD[0xA] = LD[0x2] * RD[0x8] + LD[0x6] * RD[0x9] + LD[0xA] * RD[0xA] + LD[0xE] * RD[0xB];
		DD[0xB] = LD[0x3] * RD[0x8] + LD[0x7] * RD[0x9] + LD[0xB] * RD[0xA] + LD[0xF] * RD[0xB];
		DD[0xC] = LD[0x0] * RD[0xC] + LD[0x4] * RD[0xD] + LD[0x8] * RD[0xE] + LD[0xC] * RD[0xF];
		DD[0xD] = LD[0x1] * RD[0xC] + LD[0x5] * RD[0xD] + LD[0x9] * RD[0xE] + LD[0xD] * RD[0xF];
		DD[0xE] = LD[0x2] * RD[0xC] + LD[0x6] * RD[0xD] + LD[0xA] * RD[0xE] + LD[0xE] * RD[0xF];
		DD[0xF] = LD[0x3] * RD[0xC] + LD[0x7] * RD[0xD] + LD[0xB] * RD[0xE] + LD[0xF] * RD[0xF];
		return dest;
	}
	
	public static Vector4f mul(Matrix4f matrix, Vector4f inPostion) {
		float[] matrixData = matrix.data;
		float[] result = new float[4];

		float total;
		for (int r = 0; r < 4; r++) {
			total = 0;
			total += matrixData[0 * 4 + r] * inPostion.x;
			total += matrixData[1 * 4 + r] * inPostion.y;
			total += matrixData[2 * 4 + r] * inPostion.z;
			total += matrixData[3 * 4 + r] * inPostion.w;
			result[r] = total;
		}
		return new Vector4f(result[0], result[1], result[2], result[3]);
	}

	public static void apply(float[] data, Matrix4f matrix, int count, int offset, int stride) {
		for (int i = 0; i < count; i++) {
			int d = offset + stride * i;
			Vector4f v1 = new Vector4f(data[d], data[d + 1], data[d + 2], 0);
			Vector4f v2 = mul(matrix, v1);
			data[d] = v2.x;
			data[d + 1] = v2.y;
			data[d + 2] = v2.z;
		}
	}

	//fast-extraction-viewing-frustum-planes-from-world-view-projection-matrix.pdf
	//http://gamedevs.org/uploads/fast-extraction-viewing-frustum-planes-from-world-view-projection-matrix.pdf
	public static float[][] getFrustumPlanes(float znear,float zfar, Matrix4f matrix) {
		float[][] planes = new float[6][4];
		float[] m = matrix.data;
		//left ax+by+cz+d>0 clip insid
		float a=m[3] + m[0];
		float b=m[7] + m[4];
		float c=m[11] + m[8];
		float d=m[15] + m[12];
		float n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[0] = new float[] {a/n, b/n, c/n,d/n};
		
		//right
		a = m[3] - m[0];
		b = m[7] - m[4];
		c = m[11] - m[8];
		d = m[15] - m[12];
		n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[1] = new float[] {a/n, b/n, c/n,d/n};
		
		
		//bot
		a = m[3] + m[1];
		b = m[7] + m[5];
		c = m[11] + m[9];
		d = m[15] + m[13];
		n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[2] = new float[] {a/n, b/n, c/n,d/n};
		
		//top
		a = m[3] - m[1];
		b = m[7] - m[5];
		c = m[11] - m[9];
		d = m[15] - m[13];
		n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[3] = new float[] {a/n, b/n, c/n,d/n};
		
		//near
		a = znear * m[3] + m[2];
		b = znear * m[7] + m[6];
		c = znear * m[11] + m[10];
		d = znear * m[15] + m[14];
		n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[4] = new float[] {a/n, b/n, c/n,d/n};
		
		//far
		a = zfar * m[3] - m[2];
		b = zfar * m[7] - m[6];
		c = zfar * m[11] - m[10];
		d = zfar * m[15] - m[14];
		n = (float)Math.sqrt(a * a + b * b + c * c);
		planes[5] = new float[] {a/n, b/n, c/n,d/n};
		
		return planes;
	}
}
