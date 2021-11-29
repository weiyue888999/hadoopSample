package org.myorg.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientMain {

    public static void main(String[] args) {

        Configuration conf = new Configuration();

        InetSocketAddress address  = new InetSocketAddress("localhost",9999);

        try {
            ChatChannel channel = (ChatChannel) RPC.getProxy(ChatChannel.class,
            		ChatChannel.versionId, address,  conf);

            BooleanWritable succ = channel.exchange(new Text("hello,world"));
            System.out.println(succ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
