package mina.example3;

import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * 
 * @author dujinkai
 *
 */
public class FileTransServer {
	public static void main(String[] args) throws Exception {
		ObjectSerializationCodecFactory objectSerializationCodecFactory = new ObjectSerializationCodecFactory();

		objectSerializationCodecFactory.setDecoderMaxObjectSize(Integer.MAX_VALUE);
		objectSerializationCodecFactory.setEncoderMaxObjectSize(Integer.MAX_VALUE);

		NioSocketAcceptor accept = new NioSocketAcceptor();

		accept.getFilterChain().addLast("codec", new ProtocolCodecFilter(objectSerializationCodecFactory));

		accept.getFilterChain().addLast("logging", new LoggingFilter());
		accept.setHandler(new FileIOHandle());

		accept.bind(new InetSocketAddress(8998));
	}
}
