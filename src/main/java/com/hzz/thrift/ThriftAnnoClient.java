package com.hzz.thrift;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.hzz.thrift.api.ThriftAnnoService;
import org.apache.thrift.TException;

import java.util.concurrent.ExecutionException;

/**
 * @author: hezz
 */
public class ThriftAnnoClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TException {
        ThriftClientManager clientManager = new ThriftClientManager();
        ThriftAnnoService simpleService = clientManager.createClient(new FramedClientConnector(HostAndPort.fromParts("localhost", 1234)), ThriftAnnoService.class).get();
        System.out.println(simpleService.change("bbcc"));
        clientManager.close();
    }
}
