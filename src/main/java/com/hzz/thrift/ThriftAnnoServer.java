package com.hzz.thrift;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.nifty.duplex.TDuplexProtocolFactory;
import com.facebook.nifty.duplex.TProtocolPair;
import com.facebook.nifty.duplex.TTransportPair;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TTransport;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: hezz
 */
public class ThriftAnnoServer {
    public static void main(String[] args) {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(new ThriftCodecManager(), ImmutableList.<ThriftEventHandler>of(), new ThriftAnnoServiceImpl());
        ExecutorService taskExecutor = Executors.newFixedThreadPool(1);
        ExecutorService bossExecutor = Executors.newCachedThreadPool();
        ExecutorService workExecutor = Executors.newCachedThreadPool();
        ThriftServerDef serverDef = ThriftServerDef.newBuilder().listen(1234).protocol(new TDuplexProtocolFactory() {
            @Override
            public TProtocolPair getProtocolPair(TTransportPair tTransportPair) {
                return TProtocolPair.fromSeparateProtocols(
                        new MyProtocol(tTransportPair.getInputTransport()),
//                        new MyProtocol(tTransportPair.getInputTransport()),
                        new TBinaryProtocol(tTransportPair.getOutputTransport()));
            }
        }).withProcessor(processor).using(taskExecutor).build();
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder().setBossThreadExecutor(bossExecutor).setWorkerThreadExecutor(workExecutor).build();
        ThriftServer server = new ThriftServer(serverConfig, serverDef);
        server.start();
        System.out.println("服务启动");
    }


    static class ThriftAnnoServiceImpl implements ThriftAnnoService {
        @Override
        public String change(String v) throws TException {
            System.out.println("received " + v);
            return "change " + v;
        }
    }
}
