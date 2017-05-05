package com.hzz.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.api.SimpleParameter;
import thrift.api.SimpleService;

/**
 * Created by hejf on 2017/3/14.
 */
public class SimpleThriftClient {
    public static void main(String[] args) throws TException {
        TTransport transport = new TSocket("localhost", 9001);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        SimpleService.Client client = new SimpleService.Client(protocol);
        SimpleParameter sp = new SimpleParameter("a", "b", "c");
        String res = client.m1(sp);
        System.out.println(res);
        transport.close();
    }
}
