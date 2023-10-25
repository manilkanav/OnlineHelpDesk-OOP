package com.dbUtil;

import java.util.UUID;

import javax.servlet.http.Part;

public class CommonUtils {
	public static String generateUniqueFileName(Part filePart) {
		String fileName = filePart.getSubmittedFileName();
		String extension = fileName.substring(fileName.lastIndexOf("."));
		String uniqueFileName = UUID.randomUUID().toString() + extension;
		return uniqueFileName;
	}
}
