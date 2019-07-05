package com.hzz.thrift;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.hzz.thrift.swift.SimpleParameter;
import com.hzz.thrift.swift.SimpleService;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutionException;

/**
 * @author: hezz
 */
public class MyThriftClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TException {
        ThriftClientManager clientManager = new ThriftClientManager();
        SimpleService simpleService = clientManager.createClient(new FramedClientConnector(HostAndPort.fromParts("localhost", 1234)), SimpleService.class).get();
        SimpleParameter sp = new SimpleParameter();
        sp.setP1("p1");
        System.out.println(simpleService.m1(sp));
        clientManager.close();
    }
}
