package org.myorg.client;

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.Text;

public class DefaultChatChannelServer extends AbstractChatChannel implements ChatChannel {

    @Override
    public BooleanWritable exchange(Text msg) {
        System.out.println("receive msg:"+msg);
        return new BooleanWritable(true);
    }

}
