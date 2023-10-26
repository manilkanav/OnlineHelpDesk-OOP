package com.dbUtil;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Part;

public class CommonUtils {
	public static String generateUniqueFileName(Part filePart) {
		String fileName = filePart.getSubmittedFileName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String uniqueFileName = UUID.randomUUID().toString() + extension;
		
		return uniqueFileName;
	}
	
	public static String getSubmittedFileName(Part part) {
		for(String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	
	
}
