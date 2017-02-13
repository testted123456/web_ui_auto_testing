
package mina.example3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 文件传输客户端
 * 
 * @author dujinkai
 *
 */
public class FileTransClient {
	public static void main(String[] args) throws Exception {
		ObjectSerializationCodecFactory objectSerializationCodecFactory = new ObjectSerializationCodecFactory();
		objectSerializationCodecFactory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
		objectSerializationCodecFactory.setEncoderMaxObjectSize(Integer.MAX_VALUE);
		// 客户端的实现
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(objectSerializationCodecFactory));
		connector.getFilterChain().addLast("logging", new LoggingFilter());
		connector.setHandler(new FileClientHandle());

		connector.getSessionConfig().setUseReadOperation(true);

		ConnectFuture cf = connector.connect(new InetSocketAddress("localhost", 8998));

		cf.awaitUninterruptibly();
		IoSession session = cf.getSession();
		session.write(getFileUploadRequest());

		Object result = session.read().awaitUninterruptibly().getMessage();

		if ("success".equals(result)) {
			connector.dispose();
		}
	}

	private static FileUploadRequest getFileUploadRequest() throws Exception {
		FileUploadRequest fileUploadRequest = new FileUploadRequest();

		fileUploadRequest.setFileName("djk.jpg");

		File file = new File("E://djk.jpg");

		InputStream is = new FileInputStream(file);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] bytes = new byte[1024];

		int length = 0;

		while (-1 != (length = is.read(bytes))) {
			baos.write(bytes, 0, length);
		}

		fileUploadRequest.setBytes(baos.toByteArray());

		is.close();
		baos.close();

		return fileUploadRequest;
	}
}
