package pool;

import java.util.ArrayList;

//http://www.infoq.com/cn/articles/ConcurrentLinkedQueue
public class AABBPool {
	private static AABBPool instance;

	public static AABBPool get() {
		return instance == null ? instance = new AABBPool() : instance;
	}

	private int nextIndex;
	private ArrayList<AABB> objectList;

	private AABBPool() {
	}

	public void clean() {
		this.nextIndex = 0;
	}

	public AABB get(float x, float y, float z, float w, float h, float d) {
		AABB object;
		if (nextIndex >= objectList.size()) {
			object = new AABB(x, y, z, w, h, d);
			objectList.add(object);
		} else {
			object = objectList.get(nextIndex);
			object.setBounds(x, y, z, w, h, d);
		}
		++nextIndex;
		return object;
	}

	public AABB create(float x, float y, float z, float w, float h, float d) {
		return new AABB(x, y, z, w, h, d);
	}
}