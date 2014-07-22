package com.example.robolectricsample.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ZipUtil {
	static final String DOT=".";
	static final String TAG="ZIP";
	
	
	/**
	 * 把多個檔案壓縮成一個檔
	 * @author Daniel
	 * @param desPath 最後壓縮檔的路徑
	 * @param files 等者要被壓縮的檔案集合
	 */
	public static void proccessMutipleFileToOneZip(String desPath,ArrayList<File> files){
		ZipOutputStream zos = null;
		ZipEntry ze = null;
		int readLen = 0;
		byte[] buf = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(desPath));
			for(File f : files){
				fis = new FileInputStream(f);  
	            bis = new BufferedInputStream(fis);
	            ze = new ZipEntry(f.getName());//這是壓縮包名裡的文件名
	            zos.putNextEntry(ze);//寫入新的 ZIP 文件條目並將流定位到條目數據的開始處
	            while((readLen=bis.read(buf))!=-1){  
	                zos.write(buf,0,readLen);  
	                zos.flush();  
	            }  
	            fis.close();  
	            bis.close(); 
			}
			zos.flush();
			zos.close();
		} catch (Exception e) {
			Log.e(TAG, "Extracting file: Error opening zip file - IOException: ", e);
		}
	}
	
	public Bitmap getBtimapFromZip(final String zipFilePath, final String imageFileInZip){
	    Log.d(TAG, "Getting image '" + imageFileInZip + "' from '" + zipFilePath +"'");
	    Bitmap result = null;
	    try {
	        FileInputStream fis = new FileInputStream(zipFilePath);
	        ZipInputStream zis = new ZipInputStream(fis);
	        ZipEntry ze = null;
	        while ((ze = zis.getNextEntry()) != null) {
	            if (ze.getName().equals(imageFileInZip)) {
	                result = BitmapFactory.decodeStream(zis);
	                break;
	            }
	        }
	        fis.close();
	        zis.close();
	    } catch (FileNotFoundException e) {
	        Log.e(TAG, "Extracting file: Error opening zip file - FileNotFoundException: ", e);
	    } catch (IOException e) {
	        Log.e(TAG, "Extracting file: Error opening zip file - IOException: ", e);
	    }
	    return result;
	}
	
	public static Map<String, byte[]> unzipFilesFromStream(InputStream is,
			String type) throws Exception {
		Map<String, byte[]> result = new HashMap<String, byte[]>();
		ZipInputStream zipStream = new ZipInputStream(is);
		ZipEntry zipEntry;
		byte[] temp = null,beRead=null;
		int zipsize=0,nowpos=0,beReadThisTime=0;
		while ((zipEntry = zipStream.getNextEntry()) != null) {
			if (zipEntry.getName().endsWith(DOT + type)) {
				zipsize = (int) zipEntry.getSize();
				temp = new byte[zipsize];
				beRead = new byte[zipsize];
				nowpos = 0;
				beReadThisTime = 0;
				while (-1 != (beReadThisTime = zipStream.read(temp))) {
					System.arraycopy(temp, 0, beRead, nowpos, beReadThisTime);
					nowpos += beReadThisTime;
					temp = new byte[zipsize];
				}
				result.put(zipEntry.getName(), beRead);
			}
			zipStream.closeEntry();
		}
		zipStream.close();
		return result;
	}

	public static byte[] unzipFileFromStream(InputStream is, String filename)
			throws Exception {
		byte[] result = null;
		ZipInputStream zipStream = new ZipInputStream(is);
		ZipEntry zipEntry;
		int zipsize = 0,nowpos=0,beReadThisTime=0;
		byte[] temp = null,beRead = null;
		while ((zipEntry = zipStream.getNextEntry()) != null) {
			if (zipEntry.getName().equals(filename)) {
				zipsize = (int) zipEntry.getSize();
				temp = new byte[zipsize];
				beRead = new byte[zipsize];
				nowpos = 0;
				beReadThisTime = 0;
				while (-1 != (beReadThisTime = zipStream.read(temp))) {
					System.arraycopy(temp, 0, beRead, nowpos, beReadThisTime);
					nowpos += beReadThisTime;
					temp = new byte[zipsize];
				}
				result = beRead;
			}
			zipStream.closeEntry();
		}
		zipStream.close();
		return result;
	}
}
