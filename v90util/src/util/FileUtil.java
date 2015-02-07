package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
	public static String getRelativeName(File basePath, File filePath) {
		return basePath.toURI().relativize(filePath.toURI()).getPath();
	}

	public static String read(Class<?> clazz, String file) {
		try {
			return readFully(clazz.getResourceAsStream(file));
		} catch (Exception e) {
			throw new RuntimeException("Failure reading file " + file, e);
		}
	}
	
	public static String read(String fileName) {
		try {
			return readFully(getInputStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException("Failure reading file " + fileName, e);
		}
	}
	
	public static InputStream getInputStream(String fileName) {
		InputStream inputStream = getStreamByClassLoader(fileName);

		if (inputStream == null) {
			throw new IllegalArgumentException("Can't locate the file: " + fileName);
		}
		return inputStream;
	}

	// ------------------------------------------------------------------private
	private static String readFully(InputStream is) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder s = new StringBuilder();
			String l;
			while ((l = r.readLine()) != null) {
				s.append(l).append('\n');
			}

			return s.toString();
		} catch (Exception exc) {
			throw new RuntimeException("Failure reading input stream", exc);
		}
	}

	private static InputStream getStreamByClassLoader(String fileName) {
		InputStream inputStream = null;
		try {
 			inputStream = createInputStream(Thread.currentThread().getContextClassLoader(), fileName);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Be silent with this exception, writer to log file
		}

		if (inputStream != null) {
			return inputStream;
		}

		try {
			inputStream = createInputStream(FileUtil.class.getClassLoader(), fileName);
		} catch (Exception ex) {
			// todo
		}
		
		if (inputStream != null) {
			return inputStream;
		}

		try {
			inputStream = getResourceAsStream(".", fileName);
		} catch (Exception ex) {
			// todo
		}		

		if (inputStream == null) {
			throw new IllegalArgumentException("Can't locate the file: " + fileName);
		}

		return inputStream;
	}

	private static InputStream createInputStream(ClassLoader classLoader, String fileName) throws Exception {
		return classLoader.getResourceAsStream(SystemUtil.SEPARATOR_FILE + fileName);
	}
	
	private static InputStream getResourceAsStream(String root, String ref) {
		try {
			File file = new File(new File(root), ref);
			if (!file.exists()) {
				file = new File(ref);
			}
			return new FileInputStream(file);
		} catch (IOException e) {
			return null;
		}
	}

	public static InputStream getResourceAsStream(File rootFile, String ref) {
		try {
			File file = new File(rootFile, ref);
			if (!file.exists()) {
				file = new File(ref);
			}
			return new FileInputStream(file);
		} catch (IOException e) {
			return null;
		}
	}

}
