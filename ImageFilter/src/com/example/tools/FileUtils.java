package com.example.tools;

import java.io.File;

public class FileUtils {

	static public boolean mkdirs(String fileName) {
		File srcDir = new File(fileName);
		srcDir.mkdirs();
		
		return srcDir.isDirectory();

		// String path[] = fileName.split("/");
		// String name = "";
		// if (path.length > 0) {
		// for (int i = 0; i < path.length; i++) {
		// name += "/";
		// name += path[i];
		// File srcDir = new File(fileName);
		// if (srcDir.exists()) {
		// continue;
		// }
		// srcDir.mkdir();
		// srcDir.mkdirs();
		// }
		// }
		//
	}
}
