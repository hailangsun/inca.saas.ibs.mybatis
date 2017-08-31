package com.inca.saas.ibs.util.export;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompressor {
	static final int BUFFER = 8192;

	private File zipFile;

	public ZipCompressor(String pathName) {
		zipFile = new File(pathName);
	}

	public static void main(String[] args) {
		ZipCompressor zc = new ZipCompressor("C:\\Users\\Administrator\\Desktop\\tree.tar");
		File file = new File("C:\\Users\\Administrator\\Desktop\\tree.tar");
		File[] files = new File[2];
		File file1 = new File("C:\\Users\\Administrator\\Desktop\\tree.css");
		files[0] = file1;
		File file2 = new File("C:\\Users\\Administrator\\Desktop\\ant.jar");
		files[1] = file2;
		zc.compressFiles(files);
	}

	public void compress(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "�����ڣ�");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			String basedir = "";
			compress(file, out, basedir);
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void compress(File file, ZipOutputStream out, String basedir) {
		if (file.isDirectory()) {
			this.compressDirectory(file, out, basedir);
		} else {
			this.compressFile(file, out, basedir);
		}
	}

	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* �ݹ� */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String compressFiles(File[] srcfile) {
		File zipFile = new File("d:\\123\\abc.zip");
		if (!zipFile.exists()) {
			try {
				zipFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte[] buf = new byte[1024];
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				ZipEntry te = new ZipEntry(srcfile[i].getName());
				// te.setSize(srcfile[i].length());
				out.putNextEntry(te);
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return zipFile.getName();
	}

}