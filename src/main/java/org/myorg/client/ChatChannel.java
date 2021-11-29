package org.myorg.client;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.VersionedProtocol;

public interface ChatChannel extends VersionedProtocol {
	
	public static long versionId = 65L;

	BooleanWritable exchange(Text msg);

}