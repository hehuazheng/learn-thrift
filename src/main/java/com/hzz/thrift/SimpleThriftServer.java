package com.hzz.thrift;

import com.hzz.thrift.impl.SimpleServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import thrift.api.SimpleService;

/**
 * Created by hejf on 2017/3/14.
 */
public class SimpleThriftServer {
    public static SimpleService.Processor<SimpleServiceImpl> processor;

    public static void main(String[] args) throws TTransportException {
        SimpleServiceImpl impl = new SimpleServiceImpl();
        processor = new SimpleService.Processor<>(impl);
        TServerTransport transport = new TServerSocket(9001);
        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        System.out.println("starting simple server");
        server.serve();
    }
}
