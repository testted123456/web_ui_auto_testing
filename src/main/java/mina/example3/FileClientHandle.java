package mina.example3;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class FileClientHandle extends IoHandlerAdapter {
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);

		System.out.println("Result:" + message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

		System.out.println("Begin to send Message" + message);
		super.messageSent(session, message);
	}
}