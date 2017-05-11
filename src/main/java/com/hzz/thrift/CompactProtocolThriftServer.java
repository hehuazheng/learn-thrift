package com.hzz.thrift;

import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.hzz.thrift.impl.SimpleServiceImpl;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import thrift.api.SimpleService;

/**
 * Created by hejf on 2017/3/14.
 */
public class CompactProtocolThriftServer {
    public static void main(String[] args) throws TException {
        TProtocolFactory factory = new TCompactProtocol.Factory();
        int port = 9001;
        SimpleServiceImpl impl = new SimpleServiceImpl();
        SimpleService.Processor<SimpleServiceImpl> processor = new SimpleService.Processor<>(impl);
        ThriftServerDef serverDef = (((new ThriftServerDefBuilder().listen(port)).withProcessor(processor)).protocol(factory)).build();
        final NettyServerTransport transport = new NettyServerTransport(serverDef);
        System.out.println("starting simple server");
        transport.start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    transport.stop();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        System.out.println("启动成功");
    }
}
