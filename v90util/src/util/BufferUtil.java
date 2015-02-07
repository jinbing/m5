package util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil {
	public static synchronized ByteBuffer createByteBuffer(int size) {
		return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
	}

	public static IntBuffer createIntBuffer(int size) {
		return createByteBuffer(size << 2).asIntBuffer();
	}

	public static FloatBuffer createFloatBuffer(int size) {
		return createByteBuffer(size << 2).asFloatBuffer();
	}

	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer buffer = createFloatBuffer(array.length);
		buffer.clear();
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	
	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer buffer = createIntBuffer(array.length);
		buffer.clear();
		buffer.put(array);
		buffer.flip();
		return buffer;
	}
	
	public static int readInt(byte[] buffer, int offset) {
		int value = ((buffer[offset]) << 24);
		value = value | ((buffer[offset + 1] & 255) << 16);
		value = value | ((buffer[offset + 2] & 255) << 8);
		value = value | ((buffer[offset + 3] & 255));
		return value;
	}
}
