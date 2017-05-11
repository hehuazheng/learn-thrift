package com.hzz.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.api.SimpleParameter;
import thrift.api.SimpleService;

/**
 * Created by hejf on 2017/3/14.
 */
public class CompactProtocolThriftClient {
    public static void main(String[] args) throws TException {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 9001));
        transport.open();
        TProtocol protocol = new TCompactProtocol(transport);
        long start = System.currentTimeMillis();
        SimpleService.Client client = new SimpleService.Client(protocol);
        SimpleParameter sp = new SimpleParameter("a", "b", "c");
        String res = client.m1(sp);
        System.out.println(res + " within " + (System.currentTimeMillis() - start));
        transport.close();
    }
}
