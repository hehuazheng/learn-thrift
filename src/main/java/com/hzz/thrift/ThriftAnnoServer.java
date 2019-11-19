package com.hzz.thrift;

import com.facebook.nifty.core.NettyServerConfig;
import com.facebook.nifty.core.ThriftServerDef;
import com.facebook.swift.codec.ThriftCodecManager;
import com.facebook.swift.service.ThriftEventHandler;
import com.facebook.swift.service.ThriftServer;
import com.facebook.swift.service.ThriftServiceProcessor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.hzz.thrift.api.CustomData;
import com.hzz.thrift.api.CustomType;
import com.hzz.thrift.api.ThriftAnnoService;
import org.apache.thrift.TException;

import java.util.List;
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
        ThriftServerDef serverDef = ThriftServerDef.newBuilder().listen(1234).withProcessor(processor).using(taskExecutor).build();
        NettyServerConfig serverConfig = NettyServerConfig.newBuilder().setBossThreadExecutor(bossExecutor).setWorkerThreadExecutor(workExecutor).build();
        ThriftServer server = new ThriftServer(serverConfig, serverDef);
        server.start();
        System.out.println("服务启动");
    }


    static class ThriftAnnoServiceImpl implements ThriftAnnoService {

        @Override
        public String change(String v) throws TException {
            return "change " + v;
        }

        @Override
        public List<CustomData> getTypes() {
            CustomData cd1 = new CustomData();
            cd1.setId(1);
            cd1.setType(CustomType.TYPE1);

            CustomData cd2 = new CustomData();
            cd2.setId(2);
            cd2.setType(CustomType.TYPE2);

            CustomData cd3 = new CustomData();
            cd3.setId(3);
            cd3.setType(CustomType.TYPE3);

            return Lists.newArrayList(cd1, cd2, cd3);
        }
    }
}
