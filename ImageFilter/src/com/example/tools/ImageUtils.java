package com.example.tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;

public class ImageUtils {

	static public void saveBitmapToJpg(Bitmap bitmap, String fileName) {
		FileUtils.mkdirs(fileName);
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public Bitmap creatFitinImage(String fileName){
		
		return null;
	}
}
