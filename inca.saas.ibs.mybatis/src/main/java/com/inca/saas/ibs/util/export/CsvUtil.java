package com.inca.saas.ibs.util.export;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CsvUtil {
	
	final static Logger logger = LoggerFactory.getLogger(CsvUtil.class);
	
	public static void buildCsvFile(HttpServletResponse response, File file) throws Exception {
		response.reset();// 不加这一句的话会出现下载错误
		String filename = file.getName();
		filename = java.net.URLEncoder.encode(filename, "UTF-8"); 
		response.setHeader("Content-disposition", "attachment; filename=" + filename);// 设定输出文件头
		response.setContentType("text/x-plain");// 定义输出类型

		FileInputStream fis = new java.io.FileInputStream(file);
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);

		byte[] cache = new byte[4096];
		for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
			byteOutputStream.write(cache, 0, offset);
		}

		byte[] bt = null;
		bt = byteOutputStream.toByteArray();

		OutputStream out = response.getOutputStream();
		out.write(bt);
		out.flush();
		out.close();
		fis.close();
		if (file.exists()) {
			file.delete();
		}
	}

	public static void buildFastCsvFile(HttpServletResponse response, File file) throws Exception {
		response.reset();// 不加这一句的话会出现下载错误
		String filename = file.getName();
		String zipName = filename.substring(0, filename.indexOf("."))+".zip";
		zipName = java.net.URLEncoder.encode(zipName, "UTF-8"); 
		response.setHeader("Content-disposition", "attachment; filename=" + zipName);// 设定输出文件头
		response.setContentType("text/x-plain");// 定义输出类型
		
		File zipFile = null;
		FileInputStream fis = null;
		OutputStream out = null;
		try {
			zipFile = getZipFile(file);
			fis = new java.io.FileInputStream(zipFile);
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);

			byte[] cache = new byte[4096];
			for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
				byteOutputStream.write(cache, 0, offset);
			}

			byte[] bt = null;
			bt = byteOutputStream.toByteArray();

			out = response.getOutputStream();
			out.write(bt);
			out.flush();
		} catch (Exception e) {
		} finally {
			if(out!=null){
				out.close();
			}
			if(fis!=null){
				fis.close();
			}
			if(!StringUtils.isEmpty(file) && file.isFile()){
				file.delete();
			}
			if(!StringUtils.isEmpty(zipFile) && zipFile.isFile()){
				zipFile.delete();
			}
		}
		
	}
	
	private static File getZipFile(File file) {
		if (!file.exists() || !file.isFile()) {
			return null;
		}
		try {
			logger.info("开始压缩文件！");
			String fileName = file.getName();
			String zipName = fileName.substring(0, fileName.indexOf("."))+".zip";
			File zipFile = new File(zipName);
			ZipCompressor zip = new ZipCompressor(zipFile.getAbsolutePath());
			zip.compress(file.getAbsolutePath());
			logger.error("压缩成功！");
			return zipFile;
		} catch (Exception e) {
			logger.error("压缩出错！", e);
		}
		return null;
	}
}
