package com.hzz.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.transport.TTransport;

/**
 * @author: hezz
 */
public class MyProtocol extends TBinaryProtocol {

    public MyProtocol(TTransport trans) {
        super(trans);
    }

    @Override
    public TMessage readMessageBegin() throws TException {
        TMessage message = super.readMessageBegin();
        try {
            readStructBegin();
            readFieldBegin();
            String tag = readString();
            System.out.println("traceId is " + tag);
            readFieldEnd();

            readFieldBegin();
            String user = readString();
            System.out.println("user is " + user);
            readFieldEnd();
            readStructEnd();
        } catch (TException e) {
            e.printStackTrace();
        }
        return message;
    }
}
