package mina.example3;

import java.io.Serializable;

/**
 * 文件上传对象
 * 
 * @author dujinkai
 * 
 */
public class FileUploadRequest implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1547212123169330600L;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件字节
	 */
	private byte[] bytes;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		return "FileUploadRequest [fileName=" + fileName + "]";
	}

}