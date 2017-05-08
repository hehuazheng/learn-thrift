package com.hzz.thrift;

import com.facebook.nifty.core.NettyServerTransport;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.core.ThriftServerDefBuilder;
import com.hzz.thrift.impl.SimpleServiceImpl;
import org.apache.thrift.TProcessor;
import thrift.api.SimpleService;

public class NiftyServerDemo {
    public static void main(String[] args) {
        SimpleServiceImpl impl = new SimpleServiceImpl();
        TProcessor processor = new SimpleService.Processor<>(impl);;
        ThriftServerDef thriftDev = new ThriftServerDefBuilder().listen(9000).withProcessor(processor).build();
        final NettyServerTransport transport = new NettyServerTransport(thriftDev);
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
    }
}
