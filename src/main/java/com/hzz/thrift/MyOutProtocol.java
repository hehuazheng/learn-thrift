package com.hzz.thrift;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.transport.TTransport;
import org.slf4j.MDC;

/**
 * @author: hezz
 */
public class MyOutProtocol extends TBinaryProtocol {
    public MyOutProtocol(TTransport trans) {
        super(trans);
    }

    @Override
    public void writeMessageBegin(TMessage message) throws TException {
        super.writeMessageBegin(message);
        TStruct struct = new TStruct("trace");
        writeStructBegin(struct);
        TField f = new TField("traceId", TType.STRING, (short)(Short.MAX_VALUE-1));
        writeFieldBegin(f);
        String traceId = MDC.get("traceId");
        if(StringUtils.isEmpty(traceId)) {
            traceId = "trace001";
        }
        writeString(traceId);
        writeFieldEnd();

        f = new TField("user", TType.STRING, Short.MAX_VALUE);
        writeFieldBegin(f);
        String user = MDC.get("user");
        if(StringUtils.isEmpty(user)) {
            user = "user001";
        }
        writeString(user);
        writeFieldEnd();
        writeStructEnd();
    }
}
