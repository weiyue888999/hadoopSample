package org.myorg.client;

import org.apache.hadoop.ipc.VersionedProtocol;

import java.io.IOException;

public class AbstractChatChannel implements VersionedProtocol {

	@Override
	public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
		if (protocol.equals(ChatChannel.class.getName())) {
			return ChatChannel.versionId;
		} else {
			throw new IOException("Unknown protocol : " + protocol);
		}
	}
}