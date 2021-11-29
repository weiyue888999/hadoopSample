package org.myorg.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {

        DefaultChatChannelServer server = new DefaultChatChannelServer();
        Configuration conf = new Configuration();
        try {
            RPC.Server rpcServer = RPC.getServer(server,"127.0.0.1",9999,5,true,conf);
            rpcServer.start();
            System.out.println(".........");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
