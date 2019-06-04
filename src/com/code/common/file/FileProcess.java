package com.code.common.file;

public class FileProcess {
	public static void deletefile(String filepathname) {
		try {
			java.io.File myDelFile = new java.io.File(filepathname);
	        boolean result = myDelFile.delete();
	        int tryCount = 0;
	        while (!result && tryCount++ < 10) {
	            System.gc();    //回收资源
	            result = myDelFile.delete();
	        }
			
		} catch (Exception e) {
		}
	}

}
