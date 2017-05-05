package com.hzz.thrift;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import thrift.api.SimpleParameter;
import thrift.api.SimpleService;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class ZkThriftClient {
    static List<String> children;

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("dev:2181", new RetryOneTime(5000));
        client.start();
        client.blockUntilConnected();
        final String path = "/thriftservices/simpleservice";
        children = client.getChildren().forPath(path);
        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                children =  curatorFramework.getChildren().forPath(path);
                System.out.println(new Date() + " " + children);
            }
        });
        childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        for(int i = 0; i < 10; i++) {
            String server = select();
            long start = System.currentTimeMillis();
            System.out.println("user server " + server);
            String[] serverInfo = server.split(":");
            SimpleService.Client client1 = createClient(serverInfo[0], Integer.parseInt(serverInfo[1]));
            SimpleParameter sp = new SimpleParameter("a" + i, "b", "c");
            String res = client1.m1(sp);
            client1.getInputProtocol().getTransport().close();
            System.out.println(i + " res is " + res + " takes time " + (System.currentTimeMillis() - start));
        }
        client.close();
    }

    static String select() {
        if(children != null && children.size() > 0) {
            int rnd = new Random().nextInt(children.size());
            return children.get(rnd);
        }
        return null;
    }

    static SimpleService.Client createClient(String host, int port) throws TTransportException {
        TTransport transport = new TSocket(host, port);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);

        SimpleService.Client client = new SimpleService.Client(protocol);
        return client;
    }
}
