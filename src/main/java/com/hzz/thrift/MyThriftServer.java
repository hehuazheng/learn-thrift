package com.hzz.thrift;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.hzz.thrift.swift.SimpleParameter;
import com.hzz.thrift.swift.SimpleService;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: hezz
 */
public class MyThriftServer {
    public static void main(String[] args) {
        ThriftServiceProcessor processor = new ThriftServiceProcessor(new ThriftCodecManager(), ImmutableList.<ThriftEventHandler>of(), new SimpleServiceImpl());
        ExecutorService taskExecutor = Executors.newFixedThreadPool(1);
        ExecutorService bossExecutor = Executors.newCachedThreadPool();
        ExecutorService workExecutor = Executors.newCachedThreadPool();
        ThriftServerDef serverDef = ThriftServerDef.newBuilder().listen(1234).withProcessor(processor).using(taskExecutor).build();
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder().setBossThreadExecutor(bossExecutor).setWorkerThreadExecutor(workExecutor).build();
        ThriftServer server = new ThriftServer(serverConfig, serverDef);
        server.start();
        System.out.println("服务启动");
    }

    static class SimpleServiceImpl implements SimpleService {
        @Override
        public String m1(SimpleParameter param) throws TException {
            System.out.println("input parameter is " + param.getP1());
            return "(" + param.getP1() + ")";
        }
    }
}
