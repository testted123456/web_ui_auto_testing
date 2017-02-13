
package mina.example3;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 文件业务处理
 * 
 * @author dujinkai
 *
 */
public class FileIOHandle implements IoHandler {

	public void exceptionCaught(IoSession arg0, Throwable arg1) throws Exception {

	}

	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		System.out.println("Receive Message:" + arg1);

		FileUploadRequest fileUploadRequest = null;

		if (arg1 instanceof FileUploadRequest) {
			fileUploadRequest = (FileUploadRequest) arg1;
			String fileName = fileUploadRequest.getFileName();
			byte[] bytes = fileUploadRequest.getBytes();

			FileOutputStream fileOutputStream = new FileOutputStream(new File("D://") + fileName);

			fileOutputStream.write(bytes);

			fileOutputStream.flush();

			fileOutputStream.close();

			arg0.write("success");

		} else if (arg1 instanceof String) {
			arg0.write("ok do not need to upload file");
		} else {
			System.out.println("Receive Error Message");
			arg0.write("error");
		}
	}

	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		System.out.println("Being to sent message :" + arg1);
	}

	public void sessionClosed(IoSession arg0) throws Exception {
		System.out.println("Session close");
	}

	public void sessionCreated(IoSession arg0) throws Exception {
		System.out.println("Session create");
	}

	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {

	}

	public void sessionOpened(IoSession arg0) throws Exception {
		System.out.println("Session open");
	}

}
