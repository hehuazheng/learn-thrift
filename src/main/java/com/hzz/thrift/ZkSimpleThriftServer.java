package com.hzz.thrift;

import com.hzz.thrift.impl.SimpleServiceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.zookeeper.CreateMode;
import thrift.api.SimpleService;

import java.net.InetAddress;

/**
 * Created by hejf on 2017/3/14.
 */
public class ZkSimpleThriftServer {
    public static SimpleService.Processor<SimpleServiceImpl> processor;

    public static void main(String[] args) throws Exception {
        int port = 9000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }


        InetAddress inetAddress = InetAddress.getLocalHost();
        String hostinfo = inetAddress.getHostAddress() + ":" + port;
        System.out.println("host is " + hostinfo);
        final CuratorFramework client = CuratorFrameworkFactory.newClient("dev:2181", new RetryOneTime(5000));
        client.start();
        client.blockUntilConnected();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/thriftservices/simpleservice/" + hostinfo);

        SimpleServiceImpl impl = new SimpleServiceImpl();
        processor = new SimpleService.Processor<>(impl);
        TServerTransport transport = new TServerSocket(port);
        TServer server = new TSimpleServer(new TServer.Args(transport).processor(processor));
        System.out.println("starting simple server");
        server.serve();

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("close zk client");
                client.close();
            }
        });
    }
}
