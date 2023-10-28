package com.dbUtil;


import java.util.UUID;

import javax.servlet.http.Part;

public class CommonUtils {
	public static String generateUniqueFileName(Part filePart) {
		String fileName = filePart.getSubmittedFileName();
		
		if (fileName != null && !fileName.isEmpty()) {
	        int lastDotIndex = fileName.lastIndexOf(".");
	        
	        if (lastDotIndex >= 0) {
	            String extension = fileName.substring(lastDotIndex);
	            String uniqueFileName = UUID.randomUUID().toString() + extension;
	            return uniqueFileName;
	        } else {
	            // Handle the case where there's no file extension
	            // You can choose to return an error message or handle it as needed
	            return null;
	        }
	    } else {
	        // Handle the case where the Part doesn't contain a submitted file name
	        // You can return an error message or handle it as needed
	        return null;
	    }
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
