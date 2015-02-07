package resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import util.FileUtil;

public class ResourcePack {
	private File basePath;
	private Map<String, File> fileMap;

	public ResourcePack(File basePath) {
		this.fileMap = new HashMap<>();
		
		this.basePath = basePath;
		this.readAssetsDir(basePath);
	}

	private void readAssetsDir(File filePath) {
		if (filePath.isDirectory()) {
			File[] files = filePath.listFiles();
			for (File item : files) {
				readAssetsDir(item);
			}
		} else {
			String relativeName = FileUtil.getRelativeName(basePath, filePath);
			fileMap.put(relativeName, filePath);
		}
	}

	public InputStream getInputStream(String relativeName) throws IOException {
		InputStream inputstream = FileUtil.getResourceAsStream(basePath, relativeName);

		if (inputstream != null) {
			return inputstream;
		} else {
			throw new FileNotFoundException(relativeName);
		}
	}
}

/*
File file = this.resourceFileMap.get(relativeName);
if (file != null) {
	return new FileInputStream(file);
} else {
	throw new FileNotFoundException(relativeName);
}
*/