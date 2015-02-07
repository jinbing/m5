import java.io.File;

import util.FileUtil;

public class FiltUtilTest {

	public static void main(String[] args) {
		FiltUtilTest o = new FiltUtilTest();
		o.testGetRelativeName();
	}

	void testGetRelativeName() {
		String p = "C:\\Users\\jinbing\\Downloads\\minetest\\minetest-001\\minetest-4e249fb3fbf75f0359758760d88e22aa5b14533c\\src";
		String f = "C:\\Users\\jinbing\\Downloads\\minetest\\minetest-001\\minetest-4e249fb3fbf75f0359758760d88e22aa5b14533c\\src\\client.h";
		File ff = new File(f);
		File fp = new File(p);
		System.out.println(FileUtil.getRelativeName(fp, ff));

	}
}
