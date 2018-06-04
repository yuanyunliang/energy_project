/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.framework.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import com.baomidou.framework.exception.SpringWindException;

/**
 * <p>
 * 文件流处理工具类
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-16
 */
public class StreamUtil {

	private final static int BUFFER_SIZE = 4096;

	/**
	 * 将Reader中的内容复制到Writer中
	 */
	public static int copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[16];
		int count = 0;
		int readSize;
		while ((readSize = input.read(buffer, 0, 16)) >= 0) {
			output.write(buffer, 0, readSize);
			count += readSize;
		}
		output.flush();
		return count;
	}

	/**
	 * 从流中读取内容
	 * 
	 * @param in
	 *            输入流
	 * @param charset
	 *            字符集
	 * @return 内容
	 * @throws IOException
	 */
	public static String getStringFromStream(InputStream in, String charset) throws IOException {
		StringBuilder content = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
		String line = null;
		while ((line = reader.readLine()) != null) {
			content.append(line);
		}
		return content.toString();
	}

	/**
	 * 将InputStream转换成String
	 * 
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		String string = null;
		int count = 0;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}

		data = null;
		try {
			string = new String(outStream.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将InputStream转换成某种字符编码的String
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding) {
		String string = null;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		try {
			while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
				outStream.write(data, 0, count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = null;
		try {
			string = new String(outStream.toByteArray(), encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream StringTOInputStream(String in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
		return is;
	}

	/**
	 * 将String转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static byte[] StringTObyte(String in) {
		byte[] bytes = null;
		try {
			bytes = InputStreamTOByte(StringTOInputStream(in));
		} catch (IOException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 *            InputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static InputStream byteTOInputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String byteTOString(byte[] in) {
		InputStream is = null;
		try {
			is = byteTOInputStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InputStreamTOString(is, "UTF-8");
	}

	/**
	 * 将byte数组转换成String
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String getString(String in) {
		String is = null;
		try {
			is = byteTOString(StringTObyte(in));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 
	 * InputStream 转换成byte[]
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public byte[] getBytes(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[BUFFER_SIZE];
		int len = 0;
		while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
			baos.write(b, 0, len);
		}
		baos.flush();
		return baos.toByteArray();
	}

	/**
	 * 根据文件路径创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return
	 */
	public static FileInputStream getFileInputStream(String filepath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输入流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @return
	 */
	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileInputStream;
	}

	/**
	 * 根据文件对象创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param file
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			System.out.print("错误信息:文件不存在");
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	/**
	 * 根据文件路径创建文件输出流处理 以字节为单位（非 unicode ）
	 * 
	 * @param path
	 * @param append
	 *            true:文件以追加方式打开,false:则覆盖原文件的内容
	 * @return
	 */
	public static FileOutputStream getFileOutputStream(String filepath, boolean append) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filepath, append);
		} catch (FileNotFoundException e) {
			throw new SpringWindException(e);
		}
		return fileOutputStream;
	}

	public static File getFile(String filepath) {
		return new File(filepath);
	}

	public static ByteArrayOutputStream getByteArrayOutputStream() {
		return new ByteArrayOutputStream();
	}

}
